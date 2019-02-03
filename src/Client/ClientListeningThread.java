package Client;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientListeningThread extends Thread {
    private BufferedReader socketListener;
    private Model model;

    public ClientListeningThread(BufferedReader socketListener, Model model){
        this.socketListener = socketListener;
        this.model = model;
    }

    public void run(){
        try {
            String message;
            while (true){
                message = socketListener.readLine();
                if (message != null){
                    model.serverMessageReceived(message);
                }
            }
        }
        catch (IOException e){
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
