package Client;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

public class Model extends Observable {
    private String chat = "";
    private String username = "";

    void appendMessage(String message){
        if (!message.equals("")){
            chat = chat+username+": "+message+"\n";
            setChanged();
            notifyObservers(chat);
        }

        sendMessageToServer(message);
    }

    private void sendMessageToServer(String message){
        // try(Socket socket..){} is closing socket after scope
        try (Socket socket = new Socket("localhost", 1234)){

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            writer.println(message);
        }
        catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        }
        catch (IOException e){
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setUsername(String username){
        this.username = username;
    }
}
