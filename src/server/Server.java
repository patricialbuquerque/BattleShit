package server;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket socketPlayer1;
    private Socket socketPlayer2;
    private int portServer;
    private Prompt promptToInside;

    public Server(){
        promptToInside = new Prompt(System.in, System.out);
        prepareServerSocket();
        System.out.println("Teste1");
        prepareClientSocket1();
        System.out.println("teste2");
        prepareClientSocket2();
        System.out.println("teste3");
        sendMessage1("Teste para player1");
        sendMessage2("Teste para player2");

        continuarLigado();
    }

    private void continuarLigado(){
        while (true){
        }
    }

    private void sendMessage1(String messageToSend){
        try {
            PrintWriter out = new PrintWriter(socketPlayer1.getOutputStream(), true);
            out.println(messageToSend);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage2(String messageToSend){
        try {
            PrintWriter out = new PrintWriter(socketPlayer2.getOutputStream(), true);
            out.println(messageToSend);
        } catch (IOException e) {
            e.printStackTrace();
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
}