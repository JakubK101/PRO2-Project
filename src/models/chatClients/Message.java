package models.chatClients;

import java.time.LocalDateTime;

public class Message {
    private String author;
    private String text;
    private LocalDateTime created;

    public static final int USER_LOGGED_IN =1;
    public static final int USER_LOGGED_OUT =2;

    private  static final  String AUTHOR_SYSTEM = "System";

    public Message(int type, String userName){
        this.author=AUTHOR_SYSTEM;
        created = LocalDateTime.now();
        if (type==USER_LOGGED_IN){
            text=userName+" has joined chat\n ";

        }
        else if(type ==USER_LOGGED_OUT){
            text=userName+" has left chat \n ";
        }
    }
    public Message(String author, String text) {
        this.author = author;
        this.text = text;
        created= LocalDateTime.now();
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    @Override
    public String toString() {
        if (author.equals(AUTHOR_SYSTEM)){
            return text;
        }

        String s = author +"[" + created+ "]\n";
        s+=text+"\n";
        return s;
    }
}
