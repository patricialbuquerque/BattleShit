package client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Game {

    private Socket clientSocket;
    private String ipAddressServer;
    private int portServer;
    private Prompt promptToInside;


    public Game(){
        promptToInside = new Prompt(System.in, System.out);
        ipAddressAndPort();
        startClientSocket();

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

    public void start(){
        readyCheckOtherPlayer();

    }

    private void readyCheckOtherPlayer(){
        try {
            PrintWriter readyCheck = new PrintWriter(clientSocket.getOutputStream(), true);
            readyCheck.println("Ready to Start Game?");
        } catch (IOException e) {
            System.out.println("cannot communicate with server");
            e.printStackTrace();
        }


    }



}
