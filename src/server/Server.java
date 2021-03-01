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
    private boolean player1Open;
    private boolean player2Open;

    public Server(){
        promptToInside = new Prompt(System.in, System.out);
        prepareServerSocket();
        prepareClientSocket1();
        System.out.println("Ready Player 1");
        prepareClientSocket2();
        System.out.println("Ready Player 2");
        thread = new Thread(this);
        thread.start();
        player1Open = true;
        player2Open = false;
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
                System.out.println("Recebi ready do p1");
                readyPlayer1 = true;
                sendMessageToPlayer1("Connected to server.");
                return;
            } else
            if(inputPlayer1.readLine().equals("/defeat")){
                System.out.println("Recebi defeat do p1");
                sendMessageToPlayer2("/defeat");
                return;
            } else
            if(inputPlayer1.readLine().equals("/disconnect")){
                System.out.println("Recebi disconnect do p1");
                sendMessageToPlayer2("/disconnect");
                return;
            } else
            System.out.println("P1 to P2 " + inputPlayer1.readLine());
            sendMessageToPlayer2(inputPlayer1.readLine());
            return;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveMessageFromPlayer2(){
        try {
            inputPlayer2 = new BufferedReader(new InputStreamReader(socketPlayer2.getInputStream()));
            if(inputPlayer2.readLine().equals("/ready")){
                System.out.println("Recebi ready do p2");
                readyPlayer2 = true;
                sendMessageToPlayer2("Connected to server.");
                return;
            } else
            if(inputPlayer2.readLine().equals("/defeat")){
                System.out.println("recebi defeat do P2");
                sendMessageToPlayer1("/defeat");
                return;
            } else
            if(inputPlayer2.readLine().equals("/disconnect")){
                System.out.println("recebi disconnect do P2");
                sendMessageToPlayer1("/disconnect");
                return;
            } else

            System.out.println("P1 to P2 " + inputPlayer2.readLine());
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
        while (!serverSocket.isClosed()){
            readyToStartGame();
            System.out.println("Teste1");
            receiveMessageFromPlayer1();

            System.out.println("Teste2");
            receiveMessageFromPlayer2();
            System.out.println("Teste3");
        }
    }
}