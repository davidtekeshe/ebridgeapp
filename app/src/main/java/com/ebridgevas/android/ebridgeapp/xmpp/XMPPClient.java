package com.ebridgevas.android.ebridgeapp.xmpp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jivesoftware.smackx.receipts.DeliveryReceiptManager;
import org.jivesoftware.smackx.receipts.DeliveryReceiptManager.AutoReceiptMode;
import org.jivesoftware.smackx.receipts.ReceiptReceivedListener;

import android.content.ContentResolver;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.ebridgevas.android.ebridgeapp.model.ChatMessage;
import com.ebridgevas.android.ebridgeapp.util.NotificationCenter;
import com.google.gson.Gson;

/**
 * XMPP Client
 */
public class XMPPClient {

    private static Map<String, Chat> chats;

    private MessagingService context;
    private ContentResolver contentResolver;

    public static boolean connected = false;
    public boolean loggedin = false;
    public static boolean isconnecting = false;
    public static boolean isToasted = true;
    private boolean chatCreated = false;
    private static String serverAddress;
    public static XMPPTCPConnection connection;
    public static String userId;
    public static String password;
    private Gson gson;
    public static boolean instanceCreated = false;


    private ChatManagerListenerImpl chatManagerListener;
    private static MMessageListener messageListener;

    private String text = "";
    private String message = "";
    private String receiver = "";

    static {
        try {
            Class.forName("org.jivesoftware.smack.ReconnectionManager");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static XMPPClient instance = null;

    private XMPPClient(MessagingService context, String serverAdress, String userId, String password) {
        this.serverAddress = serverAdress;
        this.userId = userId;
        this.password = password;
        this.context = context;
        contentResolver = context.getContentResolver();

        chats = new HashMap<>();

        init();

    }

    public static XMPPClient getInstance(MessagingService context, String server, String userName, String password) {

        if (instance == null) {
            instance = new XMPPClient(context, server, userName, password);
            instanceCreated = true;
        }
        return instance;
    }

    public void init() {
        gson = new Gson();
        messageListener = new MMessageListener(context);
        chatManagerListener = new ChatManagerListenerImpl();

        // Register with NotificationCenter
        NotificationCenter.getInstance().addObserver(new MessageSender(), NotificationCenter.SEND_MESSAGE);

        initialiseConnection();
    }

    private void initialiseConnection() {

        XMPPTCPConnectionConfiguration.Builder config = XMPPTCPConnectionConfiguration.builder();
        config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
        config.setServiceName(serverAddress);
        config.setHost(serverAddress);
        config.setPort(5222);
        config.setDebuggerEnabled(true);
        XMPPTCPConnection.setUseStreamManagementResumptiodDefault(true);
        XMPPTCPConnection.setUseStreamManagementDefault(true);
        connection = new XMPPTCPConnection(config.build());
        XMPPConnectionListener connectionListener = new XMPPConnectionListener();
        connection.addConnectionListener(connectionListener);
    }

    public void disconnect() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                connection.disconnect();
            }
        }).start();
    }

    public void connect(final String caller) {

        AsyncTask<Void, Void, Boolean> connectionThread = new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected synchronized Boolean doInBackground(Void... arg0) {
                if (connection.isConnected())
                    return false;
                isconnecting = true;
                if (isToasted)
                    new Handler(Looper.getMainLooper()).post(new Runnable() {

                        @Override
                        public void run() {

                            Toast.makeText(
                                    context,
                                    caller + "=>connecting....",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                Log.d("Connect() Function", caller + "=>connecting....");

                try {
                    connection.connect();
                    DeliveryReceiptManager dm = DeliveryReceiptManager
                            .getInstanceFor(connection);
                    dm.setAutoReceiptMode(AutoReceiptMode.always);
                    dm.addReceiptReceivedListener(new ReceiptReceivedListener() {

                        @Override
                        public void onReceiptReceived(final String fromid,
                                final String toid, final String msgid,
                                final Stanza packet) {

                        }
                    });
                    connected = true;

                } catch (IOException e) {
                    if (isToasted)
                        new Handler(Looper.getMainLooper())
                                .post(new Runnable() {

                                    @Override
                                    public void run() {

                                        Toast.makeText(
                                                context,
                                                "(" + caller + ")"
                                                        + "IOException: ",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });

                    Log.e("(" + caller + ")", "IOException: " + e.getMessage());
                } catch (SmackException e) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(
                                    context,
                                    "(" + caller + ")" + "SMACKException: ",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                    Log.e(
                            "(" + caller + ")",
                            "SMACKException: " + e.getMessage());
                } catch (XMPPException e) {
                    if (isToasted)

                        new Handler(Looper.getMainLooper())
                                .post(new Runnable() {

                                    @Override
                                    public void run() {

                                        Toast.makeText(
                                                context,
                                                "(" + caller + ")"
                                                        + "XMPPException: ",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                    Log.e(
                            "connect(" + caller + ")",
                            "XMPPException: " + e.getMessage());

                }
                return isconnecting = false;
            }
        };
        connectionThread.execute();
    }

    public static void login() {

        try {
            connection.login(userId, password);
            Log.i("LOGIN", "Yey! We're connected to the Xmpp server!");

        } catch (XMPPException | SmackException | IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }
    }

    private class ChatManagerListenerImpl implements ChatManagerListener {

        @Override
        public void chatCreated(final Chat chat, final boolean createdLocally) {
            if (!createdLocally)
                chat.addMessageListener(messageListener);

        }
    }

    public static void sendMessage(com.ebridgevas.android.ebridgeapp.model.Message outboundMessage) {

//        String body = gson.toJson(message);
        Chat chat = chats.get(outboundMessage.getTo());

        if (chat == null) {
            chat = ChatManager.getInstanceFor(connection)
                    .createChat(
                            outboundMessage.getTo() + "@" + serverAddress, messageListener);
            chats.put(outboundMessage.getTo(), chat);
        }

        final Message message = new Message();
        message.setBody(outboundMessage.getBody());
        message.setStanzaId("" + outboundMessage.getTo());
        message.setType(Message.Type.chat);

        try {
            if (!connection.isAuthenticated()) {
                // TODO Make this call recursive until authenticated.
                login();
            }
            chat.sendMessage(message);
        } catch (NotConnectedException e) {
            Log.e("xmpp.SendMessage()", "msg Not sent!-Not Connected!");
        } catch (Exception e) {
            Log.e("xmpp.SendMessage()","msg Not sent!" + e.getMessage());
        }
    }

    public class XMPPConnectionListener implements ConnectionListener {

        @Override
        public void connected(final XMPPConnection connection) {

            Log.d("xmpp", "Connected!");
            connected = true;
            if (!connection.isAuthenticated()) {
                login();
            }
        }

        @Override
        public void connectionClosed() {
            if (isToasted)

                new Handler(Looper.getMainLooper()).post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                        Toast.makeText(context, "ConnectionCLosed!",
                                Toast.LENGTH_SHORT).show();

                    }
                });
            Log.d("xmpp", "ConnectionCLosed!");
            connected = false;
            chatCreated = false;
            loggedin = false;
        }

        @Override
        public void connectionClosedOnError(Exception arg0) {
            if (isToasted)

                new Handler(Looper.getMainLooper()).post(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(context, "ConnectionClosedOn Error!!",
                                Toast.LENGTH_SHORT).show();

                    }
                });
            Log.d("xmpp", "ConnectionClosedOn Error!");
            connected = false;

            chatCreated = false;
            loggedin = false;
        }

        @Override
        public void reconnectingIn(int arg0) {

            Log.d("xmpp", "Reconnectingin " + arg0);

            loggedin = false;
        }

        @Override
        public void reconnectionFailed(Exception e) {

            if (isToasted)

                new Handler(Looper.getMainLooper()).post(new Runnable() {

                    @Override
                    public void run() {

                        Toast.makeText(context, "ReconnectionFailed!", Toast.LENGTH_SHORT).show();
                    }
                });
            Log.d("xmpp", "ReconnectionFailed!");
            connected = false;

            chatCreated = false;
            loggedin = false;
        }

        @Override
        public void reconnectionSuccessful() {
            if (isToasted)

                new Handler(Looper.getMainLooper()).post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                        Toast.makeText(context, "REConnected!",
                                Toast.LENGTH_SHORT).show();

                    }
                });
            Log.d("xmpp", "ReconnectionSuccessful");
            connected = true;

            chatCreated = false;
            loggedin = false;
        }

        @Override
        public void authenticated(XMPPConnection xmppConnection, boolean arg1) {
            Log.d("xmpp", "Authenticated!");
            loggedin = true;

            ChatManager.getInstanceFor(connection).addChatListener(chatManagerListener);

            chatCreated = false;
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }).start();
            if (isToasted)

                new Handler(Looper.getMainLooper()).post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub

                        Toast.makeText(context, "Connected!",
                                Toast.LENGTH_SHORT).show();

                    }
                });
        }
    }

    public void register(String userId, String password) {
        AccountManager accountManager = AccountManager.getInstance(connection);
        accountManager.sensitiveOperationOverInsecureConnection(true);
        try {
            accountManager.createAccount(userId, password);
        } catch (SmackException.NoResponseException | XMPPException.XMPPErrorException | NotConnectedException e) {
            e.printStackTrace();
        }
    }

    private class MMessageListener implements ChatMessageListener {

        public MMessageListener(Context contxt) {
        }

        @Override
        public void processMessage(final Chat chat, final Message chatMessage) {

            Log.i("XMPPClient", "Xmpp message received: '" + chatMessage);
            if (chatMessage.getBody() == null) {
                Log.i("XMPPClient", "Body is empty skipping");
                return;
            }

            com.ebridgevas.android.ebridgeapp.model.Message message =
                    com.ebridgevas.android.ebridgeapp.model.Message.fromChatMessage(1L, chat, chatMessage);

            NotificationCenter.getInstance().postNotificationName(
                    NotificationCenter.MESSAGE_RECEIVED, contentResolver, message);

//            context.getServiceListener().onDataReceived(message);

            /*
            if (message.getType() == Message.Type.chat && message.getBody() != null) {
                String payload = "body: " + message.getBody() + ", from: " + message.getFrom() +
                        ", to : " + message.getTo() + ", thread : " + message.getThread() + ", stanzaId : " +
                        message.getStanzaId();
                Log.i("XMPP", payload);

                String participantId = chat.getParticipant();
                String threadID = chat.getThreadID();
                String messageThreadID = message.getThread();
                String stanzaId = message.getStanzaId();
                String to = message.getTo();
                String from = message.getFrom();
                String text = message.getBody();
                String subject = message.getSubject();
                Set<Message.Body> bodies = message.getBodies();
                Message.Type type = message.getType();


                NotificationCenter.getInstance().postNotificationName(
                        NotificationCenter.MESSAGE_RECEIVED, contentResolver, chat, message);

                context.getServiceListener().onDataReceived(message);
                //final ChatMessage chatMessage = gson.fromJson(message.getBody(), ChatMessage.class);
                //processMessage(chatMessage);
            }
            */
        }
    }

    private static class MessageSender implements NotificationCenter.NotificationCenterDelegate {

        @Override
        public void didReceivedNotification(int id, Object... args) {
            com.ebridgevas.android.ebridgeapp.model.Message outboundMessage
                    = (com.ebridgevas.android.ebridgeapp.model.Message) args[0];

            sendMessage(outboundMessage);
        }
    }
}
