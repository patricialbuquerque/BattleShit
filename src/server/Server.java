package server;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ClientHandler clientHandler;
    private int portServer;
    private Prompt promptToInside;



    public Server(){
        promptToInside = new Prompt(System.in, System.out);
        prepareServerSocket();
        prepareClientSocket();
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

    private void prepareClientSocket() {
        try {
            clientSocket = serverSocket.accept();
            System.out.println("Connection to client accepted.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}