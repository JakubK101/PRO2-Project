package models.chatClients;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class inMemoryChatClient implements ChatClient{
    private String loggedUser;
    private List<String> loggedUsers;
    private List<Message> messages;

    public inMemoryChatClient(){
        loggedUsers = new ArrayList<>();
        messages = new ArrayList<>();
    }

    @Override
    public void sendMessage(String text) {
        messages.add(new Message(loggedUser, text));
    }

    @Override
    public void login(String username) {
        loggedUser = username;
        loggedUsers.add(username);
    }

    @Override
    public void logout() {
        loggedUsers.remove(loggedUser);
        loggedUser = null;
    }

    @Override
    public boolean isAuthenticated() {
        return loggedUsers!=null;
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

    }

    @Override
    public void addActionListenerMessagesChanged(ActionListener toAdd) {

    }
}
