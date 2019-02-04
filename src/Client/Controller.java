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
        model.sendMessage(message);
        view.clearMessageInput();
    }

    void login(String username){
        if (!username.equals("")){
            model.loginUser(username);

            model.setUsername(username);
            view.switchToChat();
        }
    }
}
