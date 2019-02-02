package Server;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{
    private Socket clientSocketConnection;

    public ServerThread(Socket clientSocketConnection){
        this.clientSocketConnection = clientSocketConnection;
    }

    public void run(){
        try {
            /* Decorator Pattern, InputStream only reads data at a low level, in byte arrays. Which is why you need
            * to wrap it with Buffered Reader to add functionality to read it as a String*/
            InputStream input = clientSocketConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            //OutputStream output = clientSocketConnection.getOutputStream();
            //PrintWriter writer = new PrintWriter(output, true);

            String message;
            while (true){
                message = reader.readLine();
                if (message != null){
                    System.out.println(message);
                }
            }
        }
        catch (IOException e){
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
