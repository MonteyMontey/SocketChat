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
//        model.sendMessage(message);
//        view.clearMessageInput();
    }

    void login(String username){
        if (!username.equals("")){
            // maybe have this as a callback later somehow
            if (model.registerUser(username)){
                model.setUsername(username);
                view.switchToChat();
            }
        }
    }
}
