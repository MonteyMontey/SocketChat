package Client;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    public void update(Observable observable, Object object){

    }

    public static void createView(){
        JFrame viewFrame = new JFrame("View");
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewFrame.setResizable(false);

        JTextField messageInput = new JTextField();
        messageInput.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));

        messageInput.setPreferredSize(new Dimension(680,50));
        JButton sendButton = new JButton("Send");
        sendButton.setPreferredSize(new Dimension(100,50));
        sendButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));

        JPanel userPanel = new JPanel(new FlowLayout());
        userPanel.add(messageInput);
        userPanel.add(sendButton);

        JPanel chatPanel = new JPanel(new BorderLayout());
        JTextArea jtextArea = new JTextArea();
        jtextArea.setEditable(false);
        jtextArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
        JScrollPane jscrollPane = new JScrollPane(jtextArea);

        chatPanel.add(jscrollPane, BorderLayout.CENTER);

        viewFrame.getContentPane().add(BorderLayout.SOUTH, userPanel);
        viewFrame.getContentPane().add(BorderLayout.CENTER, chatPanel);

        viewFrame.setSize(new Dimension(800,1000));
        viewFrame.setLocationRelativeTo(null);
        viewFrame.setVisible(true);
    }
}
