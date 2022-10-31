import gui.MainFrame;
import models.chatClients.ChatClient;
import models.chatClients.ChatFileOperations.ChatFileOperations;
import models.chatClients.ChatFileOperations.JsonChatFileOperation;
import models.chatClients.Message;
import models.chatClients.ToFileChatClient;
import models.chatClients.inMemoryChatClient;

public class Main {
    public static void main(String[] args) {
        ChatFileOperations chatFileOperations = new JsonChatFileOperation();

        ChatClient client = new ToFileChatClient(chatFileOperations);


        System.out.println("Hello world!");

        //ChatClient client = new inMemoryChatClient();

        MainFrame window = new MainFrame(800,600, client);








    }







    public void test(){
        ChatClient client = new inMemoryChatClient();
        client.login("keslja");
        System.out.println(client.isAuthenticated());
        client.sendMessage("Hello");
        client.sendMessage("Hello 2");

        for(Message msg:
                client.getMessages()){
            System.out.println(msg.getText());
        }
        client.logout();

    }
}