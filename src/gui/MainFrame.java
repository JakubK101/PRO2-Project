package gui;

import models.chatClients.ChatClient;
import models.chatClients.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    JTextField txtInputPanel, txtInputMessage;

    ChatClient chatClient;
    JTextArea txtChat;
    public MainFrame(int width, int height,ChatClient chatClient){
        setSize(width,height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("PRO2-chatclient");
        this.chatClient= chatClient;

        initGui();
        setVisible(true);

    }
   public void initGui(){
        JPanel panelMain = new JPanel(new BorderLayout());





        panelMain.add(initLoginPanel(), BorderLayout.NORTH);
        panelMain.add(initChatPanel(), BorderLayout.CENTER);
        panelMain.add(initMessagePanel(),BorderLayout.SOUTH);
        panelMain.add(initLoggedUsersPanel(), BorderLayout.EAST);

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
                System.out.println("Login " + txtInputPanel.getText());
                //chatClient.login(txtInputPanel.getText());

                if (chatClient.isAuthenticated()){
                    chatClient.logout();
                    btnLogin.setText("Login");
                    txtInputPanel.setEditable(true);
                    txtChat.setEnabled(false);
                }
                else{
                    String username= txtInputPanel.getText();
                    if (username.length()<1){
                        JOptionPane.showMessageDialog(null,"Enter your username","error",JOptionPane.ERROR_MESSAGE);

                        return;
                    }

                    chatClient.login(username);
                    btnLogin.setText("Logout");
                    txtChat.setEnabled(true);
                    txtInputPanel.setEditable(false);

                }
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
        chatClient.addActionListenerMessagesChanged(e->{
            refreshMessages();
        });
        return panelChat;
    }
    private  JPanel initMessagePanel(){
        JPanel panelmessage = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtInputMessage = new JTextField("", 50);
        panelmessage.add(txtInputMessage);

        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(e -> {
            String messageText = txtInputMessage.getText();
            System.out.println("btnSend clicked " + txtInputMessage.getText());
            if (messageText.length()<1){
                return;
            }
            if (!chatClient.isAuthenticated()){
                return;
            }
            chatClient.sendMessage(messageText);

            txtInputMessage.setText("");

            //refreshMessages();
        });
        panelmessage.add(btnSend);



        return panelmessage;
    }
    private void refreshMessages(){
        if (!chatClient.isAuthenticated()){
            return;
        }
        txtChat.setText("");
        for(Message msg : chatClient.getMessages()){
            txtChat.append(msg.toString());
            txtChat.append("\n");
        }
    }
    private JPanel initLoggedUsersPanel(){
        JPanel panel = new JPanel();

       /* Object[][] data = new Object[][]{
                {"1,1","1,2"},
                {"2,1","3,4"}
        };
        String[] columnNames = new String[] {"Col A", "Col B"};
        JTable tblLoggedUsers =new JTable(data,columnNames);*/
        JTable tblLoggedUsers =new JTable();

        LoggedUsersTableModel loggedUsersTableModel = new LoggedUsersTableModel(chatClient);
        tblLoggedUsers.setModel(loggedUsersTableModel);
        chatClient.addActionListenerLoggedUsersChanged(e->{
            loggedUsersTableModel.fireTableDataChanged();
        });


        JScrollPane scrollPane = new JScrollPane(tblLoggedUsers);
        scrollPane.setPreferredSize(new Dimension(250,500));
        panel.add(scrollPane);

        return panel;
    }


}
