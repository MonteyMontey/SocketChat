package Client;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    private Controller controller;

    private JTextArea chat;

    public View(Controller controller){
        this.controller = controller;
    }

    public void update(Observable observable, Object arg){
        chat.setText(arg.toString());
        //chat.update(chat.getGraphics());
    }


    public void createView(){
        JFrame viewFrame = new JFrame("View");
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewFrame.setResizable(false);

        JTextField messageInput = new JTextField();
        messageInput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));

        messageInput.setPreferredSize(new Dimension(680,50));
        JButton sendButton = new JButton("Send");
        sendButton.setPreferredSize(new Dimension(100,50));
        sendButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
        sendButton.addActionListener(e -> controller.sendMessage(messageInput.getText()));

        JPanel userPanel = new JPanel(new FlowLayout());
        userPanel.add(messageInput);
        userPanel.add(sendButton);

        JPanel chatPanel = new JPanel(new BorderLayout());
        chat = new JTextArea();
        chat.setEditable(false);
        chat.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
        JScrollPane jscrollPane = new JScrollPane(chat);

        chatPanel.add(jscrollPane, BorderLayout.CENTER);

        viewFrame.getContentPane().add(BorderLayout.SOUTH, userPanel);
        viewFrame.getContentPane().add(BorderLayout.CENTER, chatPanel);

        viewFrame.setSize(new Dimension(800,1000));
        viewFrame.setLocationRelativeTo(null);
        viewFrame.setVisible(true);
    }
}
