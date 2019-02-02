import Client.Controller;
import Client.Model;
import Client.View;

public class Starter {

    public static void main(String[] args){
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(controller);

        model.addObserver(view);
        view.createView();
    }
}
