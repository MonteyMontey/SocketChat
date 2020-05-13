package Client;

import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;

public class Model extends Observable {
    private String chat = "";
    private String username = "";
    private BufferedReader reader;
    private PrintWriter writer;

    public Model() {
        try {
            Socket socket = new Socket("localhost", 1234);
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (UnknownHostException e) {
            System.out.println("Server not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    boolean registerUser(String username) {
        String loginJson = createLoginJson(username);
        writer.println(loginJson);

        try {
            String response;
            do {
                response = reader.readLine();
            } while (response == null);

            JSONObject responseJson = new JSONObject(response);
            System.out.println(responseJson.toString());
            return responseJson.getBoolean("loginSuccessful");
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


    void startListeningForMessages() {
        new ClientListeningThread(reader, this).start();
        System.out.println("<<< Client now listening for messages >>>");
    }

    void receivedServerMessage(String message) {
        JSONObject serverJson = new JSONObject(message);
        System.out.println(serverJson);
        if ("newMessage".equals(serverJson.getString("type"))) {
            appendMessageToLocalChat(serverJson.getString("message"), serverJson.getString("sender"));
        }
    }

    private void appendMessageToLocalChat(String message, String username) {
        if (!message.equals("")) {
            chat = chat + username + ": " + message + "\n";
            setChanged();
            notifyObservers(chat);
        }
    }

    void sendMessage(String message) {
        if (!message.equals("")) {
            writer.println(createSendMessageJson(message));
        }
    }

    void setUsername(String username) {
        this.username = username;
    }


    private String createLoginJson(String username) {
        JSONObject loginJson = new JSONObject();
        // JSONObject automatically escapes weird user names that contain e.g. quotation marks
        loginJson.put("type", "login");
        loginJson.put("username", username);
        return loginJson.toString();
    }

    private String createSendMessageJson(String message) {
        JSONObject sendMessageJson = new JSONObject();
        sendMessageJson.put("type", "uploadMessage");
        sendMessageJson.put("sender", this.username);
        sendMessageJson.put("message", message);
        return sendMessageJson.toString();
    }
}
