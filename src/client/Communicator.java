package client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import util.Symbols;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Communicator implements Runnable{

    private Socket clientSocket;
    private String ipAddressServer;
    private int portServer;
    private Prompt promptToInside;
    private BufferedReader input;
    private ExecutorService thread;
    private Menus menu;
    private PrintWriter output;
    private String keyToShoot;



    public Communicator(Menus menu){
        promptToInside = new Prompt(System.in, System.out);
        ipAddressAndPort();
        startClientSocket();
        thread = Executors.newFixedThreadPool(2);
        thread.execute(this);
        this.menu = menu;
        keyToShoot = "";
    }

    public void ipAddressAndPort(){
        StringInputScanner question1 = new StringInputScanner();
        question1.setMessage("IP Address: ");
        ipAddressServer = promptToInside.getUserInput(question1);
        IntegerInputScanner question2 = new IntegerInputScanner();
        question2.setMessage("Port: ");
        portServer = promptToInside.getUserInput(question2);
    }

    public void startClientSocket(){
        try {
            clientSocket= new Socket(ipAddressServer,portServer);
            System.out.println("Connecting to server...");
        } catch (IOException e) {
            System.out.println("ERROR startClientSocket() in Game. (Can't instanciate Client Socket");
            e.printStackTrace();
        }
    }

    public void begin(){
        readyCheckOtherPlayer();
    }

    private void readyCheckOtherPlayer(){
        try {
            PrintWriter readyCheck = new PrintWriter(clientSocket.getOutputStream(), true);
            readyCheck.println("/ready");
            System.out.println("Ready to start.");
        } catch (IOException e) {
            System.out.println("cannot communicate with server");
            e.printStackTrace();
        }
    }

    public void menuPlayerShoot(){
        String[] options = {"Shoot", "Exit"};

        switch (menuMaker(options, "Shoot the enemy:")){
            case 1:
                StringInputScanner question = new StringInputScanner();
                question.setMessage("Choose the position to shoot Admiral!");
                keyToShoot = promptToInside.getUserInput(question);
                try {
                    output = new PrintWriter(clientSocket.getOutputStream(),true);
                    output.println(keyToShoot);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                System.exit(0);
                break;
        }
    }

   /* public void menuPlayerWait(){

        String[] options = {"Waiting", "Exit"};
        switch (menuMaker(options, "Waiting for the opponent!")){
            case 1:
                System.out.println("Wait for the opponent.");
                menuPlayerWait();
            case 2:
                System.exit(0);
        }

    }*/

    public void menuDefeat(){




    }

    public void menuVictory(){

    }

    private int menuMaker(String[] menuOptions, String setMessageToDisplay){
        MenuInputScanner scanner = new MenuInputScanner(menuOptions);
        scanner.setMessage(setMessageToDisplay);
        return promptToInside.getUserInput(scanner);
    }

    @Override
    public void run() {
        while (!clientSocket.isClosed()){
            System.out.println("teste1");
            try {
                System.out.println("teste2");
                input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                System.out.println("teste3");
                System.out.println(input.readLine());
                switch (input.readLine()){
                    case "/player1Start":
                        menuPlayerShoot();
                        break;

                    case "/player2Start":


                        break;

                    case "/defeat":
                        menuDefeat();
                        break;

                    case "/disconnect":
                        System.out.println("The other player disconnected.");
                        System.exit(0);

                    case "/water":
                        // passar a keytoShoop com o value da água para a grid inimigo.
                        System.out.println("We missed a target Admiral!");
                        menu.setEnemyGridValue(keyToShoot,Symbols.SEA_SHOT);
                        menu.showPlayerGrid();
                        menu.showEnemyGrid();

                        break;

                    case "/ship":
                        //passar a keyToShoot com a value do ship para a grid inimigo.
                        System.out.println("BANG!!!! We hit a ship!");
                        menu.setEnemyGridValue(keyToShoot,Symbols.SHIP_SHOT);
                        menu.showPlayerGrid();
                        menu.showEnemyGrid();

                        break;

                    default:
                        String key = input.readLine();
                        String value = menu.getbattleFieldValue(key);
                        if (value.equals(Symbols.SEA)){
                            menu.setBattleFieldValue(key,Symbols.SEA_SHOT);
                            output = new PrintWriter (clientSocket.getOutputStream(),true);
                            output.println("/water");
                            menuPlayerShoot();
                        }
                        if (value.equals(Symbols.SHIP)){
                            menu.setBattleFieldValue(key,Symbols.SHIP_SHOT);
                            output = new PrintWriter (clientSocket.getOutputStream(), true);
                            output.println("/ship");
                            menuPlayerShoot();
                        }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}