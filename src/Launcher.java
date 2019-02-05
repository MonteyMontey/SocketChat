import Client.Controller;
import Client.Model;
import Server.Server;

public class Launcher {
    public static void main(String[] args){

        // Create Server
        new Server(1234);

        // Create client
        Model model = new Model();
        new Controller(model);
    }
}