package com.ebridgevas.android.ebridgeapp.model;

public class ChatMessage {

    private String mBody;
    private String mSender;
    private String mReceiver;
    private String mSenderName;
    private String mDate;
    private String mTime;
    private String mMsgid;
    private boolean mIsMine;

    public ChatMessage(
                String body,
                String sender,
                String receiver,
                String senderName,
                String date,
                String time,
                String msgid,
                boolean isMine) {
        mBody = body;
        mSender = sender;
        mReceiver = receiver;
        mSenderName = senderName;
        mDate = date;
        mTime = time;
        mMsgid = msgid;
        mIsMine = isMine;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody = body;
    }

    public String getSender() {
        return mSender;
    }

    public void setSender(String sender) {
        mSender = sender;
    }

    public String getReceiver() {
        return mReceiver;
    }

    public void setReceiver(String receiver) {
        mReceiver = receiver;
    }

    public String getSenderName() {
        return mSenderName;
    }

    public void setSenderName(String senderName) {
        mSenderName = senderName;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getMsgid() {
        return mMsgid;
    }

    public void setMsgid(String msgid) {
        mMsgid = msgid;
    }

    public boolean isMine() {
        return mIsMine;
    }

    public void setIsMine(boolean isMine) {
        mIsMine = isMine;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "mBody='" + mBody + '\'' +
                ", mSender='" + mSender + '\'' +
                ", mReceiver='" + mReceiver + '\'' +
                ", mSenderName='" + mSenderName + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mTime='" + mTime + '\'' +
                ", mMsgid='" + mMsgid + '\'' +
                ", mIsMine=" + mIsMine +
                '}';
    }
}