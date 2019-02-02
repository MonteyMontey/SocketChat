package Client;

import java.util.Observable;

public class Model extends Observable {
    private String chat = "";

    void appendMessage(String message){
        chat = chat+"\n"+"Dominik: "+message;
        setChanged();
        notifyObservers(chat);
    }
}
