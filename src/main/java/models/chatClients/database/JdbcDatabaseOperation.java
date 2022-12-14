package models.chatClients.database;

import models.chatClients.ApiChatClient;
import models.chatClients.Message;

import java.sql.*;
import java.util.List;

public class JdbcDatabaseOperation implements DatabaseOperations{
    private final Connection connection;
    private ApiChatClient api;

    public JdbcDatabaseOperation(String driver, String url)
        throws  ClassNotFoundException,SQLException{
            Class.forName(driver);
            this.connection = DriverManager.getConnection(url);
            api= new ApiChatClient();
        }





    @Override
    public void addMessage(Message message) {
        try{
            Statement statement = connection.createStatement();
            String sql=
                    "INSERT INTO ChatMessages (author, text, created) "+
                            "VALUES ("
                            +"'" +message.getAuthor() +"',"
                            + "'" + message.getText() + "',"
                            + "'" + Timestamp.valueOf(message.getCreated()) +"'"
                            +")";
            statement.executeUpdate(sql);
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Message> getMessages() {
        return api.getMessages();

    }
}
