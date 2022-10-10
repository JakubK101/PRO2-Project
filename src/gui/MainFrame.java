package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    JTextField txtInputPanel, txtInputMessage;
    JTextArea txtChat;
    public MainFrame(int width, int height){
        setSize(width,height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("PRO2-chatclient");

        initGui();
        setVisible(true);

    }
   public void initGui(){
        JPanel panelMain = new JPanel(new BorderLayout());





        panelMain.add(initLoginPanel(), BorderLayout.NORTH);
        panelMain.add(initChatPanel(), BorderLayout.CENTER);
        panelMain.add(initMessagePanel(),BorderLayout.SOUTH);


        add(panelMain);



    }
    private JPanel initLoginPanel(){
        JPanel panelLogin = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelLogin.add(new JLabel("username"));
        txtInputPanel = new JTextField("",30);
        panelLogin.add(txtInputPanel);

        JButton btnLogin = new JButton("Login");
        panelLogin.add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Login" + txtInputPanel.getText());
            }
        });



        return panelLogin;
    }
    private  JPanel initChatPanel(){
        JPanel panelChat = new JPanel();
        panelChat.setLayout(new BoxLayout(panelChat,BoxLayout.X_AXIS));

        txtChat= new JTextArea();
        txtChat. setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtChat);
        panelChat.add(scrollPane);


        /*for(int i =0;i<50;i++){
            txtChat.append("Message" +i +"\n");
        }*/

        return panelChat;
    }
    private  JPanel initMessagePanel(){
        JPanel panelmessage = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtInputMessage = new JTextField("", 50);
        panelmessage.add(txtInputMessage);

        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(e -> {
            System.out.println("btnSend clicked " + txtInputMessage.getText());
            txtChat.append(txtInputMessage.getText() + "\n");
            txtInputMessage.setText("");
        });
        panelmessage.add(btnSend);



        return panelmessage;
    }


}
