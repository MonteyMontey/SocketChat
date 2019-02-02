package Client;

public class Controller {
    private Model model;

    public Controller(Model model){
        this.model = model;
    }

    void sendMessage(String message){
        // send message to server here
        model.appendMessage(message);
    }
}
