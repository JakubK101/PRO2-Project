package models.chatClients.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbInicializer {
    private  final String driver;
    private final String url;

    public  DbInicializer(String driver, String url)
    {
        this.driver=driver;
        this.url=url;
    }

    public void init(){
        try{
            //Načteme driver
            Class.forName(driver);
            //otevřeme spojení
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();

            String sql= "CREATE TABLE ChatMessages "
                    +"(id INT NOT NULL GENERATED ALWAYS AS IDENTITY "
                    +"CONSTRAINT ChatMessages_PK PRIMARY KEY, "
                    +"author VARCHAR(50), "
                    +"text VARCHAR(1000), "
                    +"created timestamp)";
            statement.execute(sql);
            statement.close();
            connection.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
