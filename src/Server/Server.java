package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/* https://www.codejava.net/java-se/networking/java-socket-server-examples-tcp-ip */

public class Server {
    public static void main (String[] args){
        try (ServerSocket serverSocket = new ServerSocket(1234)){
            System.out.println("<<< Server listening for clients >>>");
            while (true){
                Socket clientSocketConnection = serverSocket.accept(); /* execution stops here until new client connects
                 which is the reasons why threads are needed -> server needs to interact with multiple clients*/
                System.out.println("New Client connected!");
                new ServerThread(clientSocketConnection).start();
            }
        }
        catch (IOException e){
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
