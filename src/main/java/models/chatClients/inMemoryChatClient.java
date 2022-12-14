package models.chatClients;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class inMemoryChatClient implements ChatClient{
    private String loggedUser;
    private List<String> loggedUsers;
    private List<Message> messages;

    private List<ActionListener> listenerLoggedUsersChanged = new ArrayList<>();
    private List<ActionListener> listenerMessagesChanged = new ArrayList<>();

    public inMemoryChatClient(){
        loggedUsers = new ArrayList<>();
        messages = new ArrayList<>();
    }

    @Override
    public void sendMessage(String text) {
        messages.add(new Message(loggedUser, text));
        raiseEventMessagesChanged();
    }

    @Override
    public void login(String username) {
        loggedUser = username;
        loggedUsers.add(username);
        addSystemMessage(Message.USER_LOGGED_IN,username);
        raiseEventLoggedUsersChanged();

    }

    @Override
    public void logout() {
        addSystemMessage(Message.USER_LOGGED_OUT,loggedUser);
        loggedUsers.remove(loggedUser);
        loggedUser = null;
        raiseEventLoggedUsersChanged();
    }

    @Override
    public boolean isAuthenticated() {
        return loggedUser!=null;
    }

    @Override
    public List<String> getLoggedUsers() {
        return loggedUsers;
    }

    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public void addActionListenerLoggedUsersChanged(ActionListener toAdd) {
        listenerLoggedUsersChanged.add(toAdd);
    }

    @Override
    public void addActionListenerMessagesChanged(ActionListener toAdd) {
        listenerMessagesChanged.add(toAdd);
    }

    private void raiseEventLoggedUsersChanged(){
        for (ActionListener al:
                listenerLoggedUsersChanged){{
        al.actionPerformed(new ActionEvent(this,1,"users changed"));}
        }
    }
    private void raiseEventMessagesChanged(){
        for (ActionListener al: listenerMessagesChanged){
            al.actionPerformed(new ActionEvent(this,1,"message changed"));
        }
    }
    private void addSystemMessage(int type, String userName){
        messages.add(new Message(type,userName));
        raiseEventMessagesChanged();

    }
}
