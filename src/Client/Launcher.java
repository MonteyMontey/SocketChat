package Client;

public class Launcher {
    public static void main(String[] args) {
        Model model = new Model();
        new Controller(model);
    }
}
