package Client;

public class ClientLauncher {
    public static void main(String[] args) {
        Model model = new Model();
        new Controller(model);
    }
}
