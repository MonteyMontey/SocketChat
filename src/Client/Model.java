package Client;

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
    }

    public void setUsername(String username){
        this.username = username;
    }
}
