package server;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {

    private ServerSocket serverSocket;
    private Socket socketPlayer1;
    private Socket socketPlayer2;
    private int portServer;
    private Prompt promptToInside;
    private BufferedReader inputPlayer1;
    private BufferedReader inputPlayer2;
    private Thread thread;
    private boolean readyPlayer1;
    private boolean readyPlayer2;
    private boolean gameStarted;

    public Server(){
        promptToInside = new Prompt(System.in, System.out);
        prepareServerSocket();
        prepareClientSocket1();
        System.out.println("Ready Player 1");
        prepareClientSocket2();
        System.out.println("Ready Player 2");
        thread = new Thread(this);
        thread.start();
    }



    private void sendMessageToPlayer1(String messageToSend){
        try {
            PrintWriter out = new PrintWriter(socketPlayer1.getOutputStream(), true);
            out.println(messageToSend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessageToPlayer2(String messageToSend){
        try {
            PrintWriter out = new PrintWriter(socketPlayer2.getOutputStream(), true);
            out.println(messageToSend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveMessageFromPlayer1() {
        try {
            inputPlayer1 = new BufferedReader(new InputStreamReader(socketPlayer1.getInputStream()));
            if(inputPlayer1.readLine().equals("/ready")){
                readyPlayer1 = true;
                sendMessageToPlayer1("Connected to server.");
            }
            if(inputPlayer1.readLine().equals("/defeat")){
                sendMessageToPlayer2("/defeat");
            }
            if(inputPlayer1.readLine().equals("/disconnect")){
                sendMessageToPlayer2("/disconnect");
            }
            sendMessageToPlayer2(inputPlayer1.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void receiveMessageFromPlayer2(){
        try {
            inputPlayer2 = new BufferedReader(new InputStreamReader(socketPlayer1.getInputStream()));
            if(inputPlayer2.readLine().equals("/ready")){
                readyPlayer2 = true;
                sendMessageToPlayer2("Connected to server.");
            }
            if(inputPlayer2.readLine().equals("/defeat")){
                sendMessageToPlayer1("/defeat");
            }
            if(inputPlayer2.readLine().equals("/disconnect")){
                sendMessageToPlayer1("/disconnect");
            }
            sendMessageToPlayer1(inputPlayer2.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readyToStartGame(){
        if(readyPlayer1 && readyPlayer2 && !gameStarted){
            sendMessageToPlayer1("/player1Start");
            sendMessageToPlayer2("/player2Start");
            gameStarted = true;
        }
    }

    private void prepareServerSocket() {
        IntegerInputScanner question1 = new IntegerInputScanner();
        question1.setMessage("Server Port: ");
        portServer = promptToInside.getUserInput(question1);

        try {
            serverSocket = new ServerSocket(portServer);
            System.out.println("Creating Server Socket");
        } catch (IOException e) {
            System.out.println("ERROR in prepareServerSocket() in Server");
            e.printStackTrace();
        }
    }

    private void prepareClientSocket1() {
        try {
            socketPlayer1 = serverSocket.accept();
            System.out.println("Connection to player1 accepted.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void prepareClientSocket2(){
        try {
            socketPlayer2 = serverSocket.accept();
            System.out.println("Connection to player2 accepted.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (serverSocket.isBound()){
            readyToStartGame();
            receiveMessageFromPlayer1();
            receiveMessageFromPlayer2();
        }
    }
}