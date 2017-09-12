package com.ebridgevas.android.ebridgeapp.model.xmpp;

/**
 * TODO Class message
 */
public class ChatMessage {

    private String message;
    private String senderId;
    private String receiverId;
    private String senderName;
    private String date;
    private String time;
    private String messageId;
    private boolean isMine;

    public ChatMessage( String message,
                        String senderId,
                        String receiverId,
                        String senderName,
                        String date,
                        String time,
                        String messageId,
                        boolean isMine) {
        this.message = message;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.senderName = senderName;
        this.date = date;
        this.time = time;
        this.messageId = messageId;
        this.isMine = isMine;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }
}
