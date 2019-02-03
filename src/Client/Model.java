package Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

public class Model extends Observable {
    private String chat = "";
    private String username = "";
    private BufferedReader reader;
    private PrintWriter writer;

    public Model(){
        try {
            Socket socket = new Socket("localhost", 1234);
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        }
        catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        }
        catch (IOException e){
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }

        new ClientListeningThread(reader, this).start();
    }

    void loginUser(String username){
        // define json structure
    }

    void appendMessageToLocalChat(String message){
        if (!message.equals("")){
            chat = chat+username+": "+message+"\n";
            setChanged();
            notifyObservers(chat);
        }
    }

    void sendMessage(String message){
        if (!message.equals("")){
            writer.println(message);
            appendMessageToLocalChat(message);
        }
    }

    void serverMessageReceived(String message){

    }


    void setUsername(String username){
        this.username = username;
    }
}
