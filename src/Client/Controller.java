package Client;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model){
        this.model = model;
        view = new View(this, model);
        view.createView();
    }

    void sendMessage(String message){
        // send message to server here
        model.appendMessage(message);
        view.clearMessageInput();
    }
}
