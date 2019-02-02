package Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args){

        // mvc gui
        Model model = new Model();
        new Controller(model);


        /*try (Socket socket = new Socket("localhost", 1234)){

            //InputStream input = socket.getInputStream();
            //BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            writer.println("test");

        }
        catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        }
        catch (IOException e){
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }*/
    }
}
