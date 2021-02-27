package client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.IOException;
import java.net.Socket;

public class Game {

    private Socket clientSocket;
    private String ipAddressServer;
    private int portServer;
    private Prompt promptToInside;






    public Game(){
        promptToInside = new Prompt(System.in, System.out);
        Menus newMenu = new Menus();
        ipAddressAndPort();
        startClientSocket();
        newMenu.mainMenu();
    }




    private void ipAddressAndPort(){
        StringInputScanner question1 = new StringInputScanner();
        question1.setMessage("IP Address: ");
        ipAddressServer = promptToInside.getUserInput(question1);
        IntegerInputScanner question2 = new IntegerInputScanner();
        question2.setMessage("Port: ");
        portServer = promptToInside.getUserInput(question2);
    }

    private void startClientSocket(){
        try {
            clientSocket= new Socket(ipAddressServer,portServer);
            System.out.println("Connecting to server...");
        } catch (IOException e) {
            System.out.println("ERROR startClientSocket() in Game. (Can't instanciate Client Socket");
            e.printStackTrace();
        }
    }

    private void gameCanStart(){

        // Enviar para o servidor o canStartGame do menu para verificar se ambos os players podem começar o jogo.
        // Se não, wait.
        // Se sim, iniciar o jogo.

    }



}
