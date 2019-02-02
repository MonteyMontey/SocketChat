package Client;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model){
        this.model = model;
        view = new View(this, model);
        view.createLogin();
    }

    void sendMessage(String message){
        model.appendMessage(message);
        view.clearMessageInput();
    }

    void login(String username){
        if (!username.equals("")){
            model.setUsername(username);
            view.switchToChat();
        }
    }
}
