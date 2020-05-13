package Client;

import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("ALL")
public class View implements Observer {

    private Controller controller;

    private JFrame loginFrame;
    private JFrame chatFrame;

    private JTextArea chat;
    private JTextField messageInput;

    View(Controller controller, Model model) {
        this.controller = controller;
        model.addObserver(this);
    }

    public void update(Observable observable, Object arg) {
        chat.setText(arg.toString());
    }


    void createLogin() {
        loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setResizable(false);

        JTextField usernameInput = new JTextField();
        usernameInput.setPreferredSize(new Dimension(200, 50));
        usernameInput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 50));
        loginButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        loginButton.addActionListener(e -> {
            try {
                controller.login(usernameInput.getText());
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        });
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.add(usernameInput);
        loginPanel.add(loginButton);

        loginFrame.getContentPane().add(loginPanel);
        loginFrame.setSize(new Dimension(400, 200));
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    private void createChat() {
        chatFrame = new JFrame("Chat");
        chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatFrame.setResizable(false);

        messageInput = new JTextField();
        messageInput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));

        messageInput.setPreferredSize(new Dimension(680, 50));
        JButton sendButton = new JButton("Send");
        sendButton.setPreferredSize(new Dimension(100, 50));
        sendButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
        sendButton.addActionListener(e -> {
            try {
                controller.sendMessage(messageInput.getText());
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
        });

        JPanel userPanel = new JPanel(new FlowLayout());
        userPanel.add(messageInput);
        userPanel.add(sendButton);

        JPanel chatPanel = new JPanel(new BorderLayout());
        chat = new JTextArea();
        chat.setEditable(false);
        chat.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
        JScrollPane jscrollPane = new JScrollPane(chat);

        chatPanel.add(jscrollPane, BorderLayout.CENTER);

        chatFrame.getContentPane().add(BorderLayout.SOUTH, userPanel);
        chatFrame.getContentPane().add(BorderLayout.CENTER, chatPanel);

        chatFrame.setSize(new Dimension(800, 1000));
        chatFrame.setLocationRelativeTo(null);
        chatFrame.setVisible(true);
    }

    void clearMessageInput() {
        messageInput.setText("");
    }

    void switchToChat() {
        loginFrame.setVisible(false);
        loginFrame = null;
        createChat();
    }

    void alertUsernameAlreadyConnected() {
        JOptionPane.showMessageDialog(loginFrame,
                "Username already chosen by another user.",
                "Invalid username",
                JOptionPane.ERROR_MESSAGE);
    }

    void alertUsernameCannotBeEmpty() {
        JOptionPane.showMessageDialog(loginFrame,
                "Username must have at lest one character.",
                "Invalid username",
                JOptionPane.ERROR_MESSAGE);
    }
}
