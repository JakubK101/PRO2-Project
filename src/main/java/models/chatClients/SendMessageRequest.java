package models.chatClients;

import com.google.gson.annotations.Expose;

public class SendMessageRequest {


    private String token;

    private String text;

    public SendMessageRequest(String token, String text) {
        this.token = token;
        this.text = text;
    }
}
