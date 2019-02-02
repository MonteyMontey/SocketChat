import Client.Controller;
import Client.Model;

public class Starter {

    public static void main(String[] args){
        Model model = new Model();
        Controller controller = new Controller(model);
    }
}
