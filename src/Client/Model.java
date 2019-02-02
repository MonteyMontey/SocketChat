package Client;

import java.util.Observable;

public class Model extends Observable {
    private String chat = "";

    void appendMessage(String message){
        chat = chat+"Dominik: "+message+"\n";
        setChanged();
        notifyObservers(chat);
    }
}
