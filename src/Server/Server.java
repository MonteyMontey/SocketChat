package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/* https://www.codejava.net/java-se/networking/java-socket-server-examples-tcp-ip */

public class Server {

    private Map<String, Socket> loggedInClients;
    private ServerSocket serverSocket;

    public Server(int port){
        loggedInClients = new HashMap<String, Socket>();
        startServer(port);
    }

    private void startServer(int port){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            acceptClients();
        }
        catch (IOException e){
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void acceptClients(){
        try {
            System.out.println("<<< Server listening for clients >>>");
            while (true){
                Socket clientSocketConnection = serverSocket.accept(); /* execution stops here until new client connects
                 which is the reasons why threads are needed -> server needs to interact with multiple clients*/

                System.out.println("New Client connected!");
                new ServerThread(clientSocketConnection, this).start();
            }
        }
        catch (IOException e){
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


/* what if two clients ask at the same time for validUsername and ig gets execute like: 1. validUsername? 2. validUsername?
3. saveUsername 4. saveUsername because there are different threads for each client*/