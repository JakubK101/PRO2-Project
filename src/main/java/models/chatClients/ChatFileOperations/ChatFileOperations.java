package models.chatClients.ChatFileOperations;

import models.chatClients.Message;

import java.util.List;

public interface ChatFileOperations {
    List<Message> loadMessages();
    void writeMessages(List<Message> messages);

    List<String> loadLoggedUsers();
    void writeLoggedUsers(List<String> users);
}
