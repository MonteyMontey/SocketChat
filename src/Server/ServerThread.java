package Server;

import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ServerThread extends Thread {

    private Server server;
    private Socket clientSocketConnection;
    private String clientUsername;

    public ServerThread(Socket clientSocketConnection, Server server) {
        this.clientSocketConnection = clientSocketConnection;
        this.server = server;
    }

    public void run() {
        try {
            /* Decorator Pattern, InputStream only reads data at a low level, in byte arrays. Which is why you need
             * to wrap it with Buffered Reader to add functionality to read it as a String*/
            InputStream input = clientSocketConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = clientSocketConnection.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            String message;
            while (true) {
                message = reader.readLine();
                if (message != null) {
                    System.out.println(message);
                    JSONObject clientJson = new JSONObject(message);

                    switch (clientJson.getString("type")) {
                        case "login":
                            if (server.usernameAlreadyConnected(clientJson.getString("username"))) {
                                writer.println(server.createLoginResponse(false));
                            } else {
                                clientUsername = clientJson.getString("username");
                                server.storeClientConnection(clientSocketConnection, clientJson.getString("username"));
                                writer.println(server.createLoginResponse(true));
                            }
                            break;
                        case "uploadMessage":
                            String broadcastJson = server.createBroadcastJson(clientJson.getString("message"), clientJson.getString("sender"));
                            server.broadcastMessage(broadcastJson);
                            break;
                    }
                }
            }
        } catch (SocketException e) {
            server.clientDisconnected(clientUsername);
            System.out.println(clientUsername+ "client disconnected");
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}