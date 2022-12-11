import gui.MainFrame;
import models.chatClients.*;
import models.chatClients.ChatFileOperations.ChatFileOperations;
import models.chatClients.ChatFileOperations.JsonChatFileOperation;
import models.chatClients.database.DatabaseOperations;
import models.chatClients.database.DbInicializer;
import models.chatClients.database.JdbcDatabaseOperation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String databaseDriver = "org.apache.derby.jdbc.EmbeddedDriver";
        String databaseUrl = "jdbc:derby:ChatClientDb_skA";


        try {

            DbInicializer dbInicializer = new DbInicializer(databaseDriver, databaseUrl);
            dbInicializer.init();


            ChatClient client = new ApiChatClient();


            Class<ApiChatClient> reflectionExample = ApiChatClient.class;
            List<Field> fields = getAllFields(reflectionExample);


            for (Field f : fields) {
                System.out.println(f.getName() + " : " + f.getType());
            }

            System.out.println("Hello world!");

            //ChatClient client = new inMemoryChatClient();

            MainFrame window = new MainFrame(800, 600, client);
        }catch (Exception e){
            e.printStackTrace();
        }








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
    private static List<Field> getAllFields(Class<?> cls){
        List<Field> fieldList = new ArrayList<>();
        for(Field f:cls.getDeclaredFields()){
            fieldList.add(f);
        }
        return fieldList;
    }
}