package com.ebridgevas.android.ebridgeapp.database;

public class MessageData {
    private String message;

    public MessageData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageData{" +
                "message='" + message + '\'' +
                '}';
    }
}
