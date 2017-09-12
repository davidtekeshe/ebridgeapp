package com.ebridgevas.android.ebridgeapp.model;

import android.util.Log;

import com.google.gson.Gson;

import org.jivesoftware.smack.chat.Chat;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author david@tekeshe.com
 */
public class Message implements Comparable<Message>{

    private final long id;
    private final String from;
    private final String to;
    private final String participantId;
    private final String threadID;
    private final String messageThreadID;
    private final String stanzaId;
    private final String subject;
    private final String body;
    private final MessageType messageType;
    private final long date;
    private String status;
    private final boolean mine;

    public Message( long id,
                    String from,
                    String to,
                    String participantId,
                    String threadID,
                    String messageThreadID,
                    String stanzaId,
                    String body,
                    String subject,
                    MessageType messageType,
                    long date,
                    boolean mine
            ) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.date = date;
        this.participantId = participantId;
        this.threadID = threadID;
        this.messageThreadID = messageThreadID;
        this.stanzaId = stanzaId;
        this.body = body;
        this.subject = subject;
        this.messageType = messageType;
        this.mine = mine;
    }

    public long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getParticipantId() {
        return participantId;
    }

    public String getThreadID() {
        return threadID;
    }

    public String getMessageThreadID() {
        return messageThreadID;
    }

    public String getStanzaId() {
        return stanzaId;
    }

    public String getBody() {
        return body;
    }

    public String getSubject() {
        return subject;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public long getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public boolean isMine() {
        return mine;
    }

    @Override
    public String toString() {
        return "Message{" +
                ", id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", participantId='" + participantId + '\'' +
                ", threadID='" + threadID + '\'' +
                ", messageThreadID='" + messageThreadID + '\'' +
                ", stanzaId='" + stanzaId + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", messageType=" + messageType +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public int compareTo(Message o) {
//        return this.id >= o.getId() ? 1 : -1;
        return this.from.compareTo(o.getFrom());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (from != null ? !from.equals(message.from) : message.from != null) return false;
        return !(to != null ? !to.equals(message.to) : message.to != null);

    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }

    public static Message fromChatMessage(long id, Chat chat, org.jivesoftware.smack.packet.Message message) {

        String from = message.getFrom();
        String to = message.getTo();
        String participantId = chat.getParticipant();
        String threadID = chat.getThreadID();
        String messageThreadID = message.getThread();
        String stanzaId = message.getStanzaId();
        String body = message.getBody();
//        Payload payload = new Gson().fromJson(body, Payload.class);
        String text = body; //payload.getMessageText();
//        Set<Body> bodies = bodies(message.getBodies());
        String subject = message.getSubject();
        MessageType messageType = MessageType.CHAT_ENTRY; //MessageType.getByDescription(payload.getMessageType());
        long date = System.currentTimeMillis();
        Message result = new Message(
                id, from, to, participantId, threadID, messageThreadID, stanzaId, text, subject, messageType, date,
                false);
        Log.i("XMPP", "received message : " + result);

        return result;
    }

    private static MessageType  toMessageType(org.jivesoftware.smack.packet.Message.Type type) {
        return MessageType.CHAT_ENTRY;
    }

    private static class Payload implements Serializable {
        private String messageType;
        private String messageText;

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        public String getMessageText() {
            return messageText;
        }

        public void setMessageText(String messageText) {
            this.messageText = messageText;
        }
    }
}
