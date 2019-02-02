package Client;

import java.util.Observable;

public class Model extends Observable {
    private String chat = "";

    void appendMessage(String message){
        if (!message.equals("")){
            chat = chat+"Dominik: "+message+"\n";
            setChanged();
            notifyObservers(chat);
        }
    }
}
