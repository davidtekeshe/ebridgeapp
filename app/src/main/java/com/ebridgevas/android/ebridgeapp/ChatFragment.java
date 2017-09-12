package com.ebridgevas.android.ebridgeapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ebridgevas.android.ebridgeapp.adapter.ChatAdapter;
import com.ebridgevas.android.ebridgeapp.model.ChatMessage;
import com.ebridgevas.android.ebridgeapp.model.Message;
import com.ebridgevas.android.ebridgeapp.model.MessageType;
import com.ebridgevas.android.ebridgeapp.model.NodeData;
import com.ebridgevas.android.ebridgeapp.util.*;
import com.ebridgevas.android.ebridgeapp.widget.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ChatFragment extends Fragment implements OnClickListener, BackPressedFragment {

    private EditText msg_edittext;
    private String user1 = "david";
    private String peerId;
    public final static String ARG_PEER_ID = "PEER_ID";
    private Random random;
    public static ArrayList<Message> messages;
    public static ChatAdapter chatAdapter;
    ListView msgListView;

    public ChatFragment() {
        messages = new ArrayList<>();

        /*
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
                    long date
            ) {

//         */
//        Message message = new Message(
//                System.currentTimeMillis(), "irene", "david", "1", "","","","Hi there","",
//                MessageType.CHAT_ENTRY,
//                System.currentTimeMillis(), false
//        );
//
//        messages.add(message);
//
//        message = new Message(
//                System.currentTimeMillis(), "david", "irene", "1", "","","","Hi irene","",
//                MessageType.CHAT_ENTRY,
//                System.currentTimeMillis(), true
//        );
//
//        messages.add(message);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.chat_layout, container, false);
        random = new Random();
        Bundle args = getArguments();
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(args.getString("TITLE"));
        TextView chatTitle = (TextView)view.findViewById(R.id.chatTitle);
        chatTitle.setText(args.getString("TITLE"));
        peerId = args.getString(ARG_PEER_ID);
        // thumbnail image
        CircleImageView chatThumbnail = (CircleImageView)view.findViewById(R.id.chatThumbnail);
        int resId = getActivity().getResources().getIdentifier(args.getString("AVATOR"), "drawable", getActivity().getPackageName());
        Picasso.with(getActivity().getApplicationContext()).load(resId).into(chatThumbnail);
//        chatThumbnail.setImageBitmap(null);

        OnClickListener backPressedLister = new OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        };

        ImageView backArrow = (ImageView)view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(backPressedLister);
        chatTitle.setOnClickListener(backPressedLister);
        chatThumbnail.setOnClickListener(backPressedLister);

        msg_edittext = (EditText) view.findViewById(R.id.messageEditText);
        msgListView = (ListView) view.findViewById(R.id.msgListView);
        ImageView sendButton = (ImageView) view.findViewById(R.id.sendMessageButton);
        sendButton.setOnClickListener(this);

        // Set autoscroll of listview when a new message arrives
        msgListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
//        msgListView.setStackFromBottom(true);

        chatAdapter = new ChatAdapter(getActivity(), messages);
        msgListView.setAdapter(chatAdapter);

        // Register with NotificationCenter
        NotificationCenter.getInstance().addObserver(new MessageReceiver(), NotificationCenter.MESSAGE_RECEIVED);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    }

    private class MessageReceiver implements NotificationCenter.NotificationCenterDelegate {

        @Override
        public void didReceivedNotification(int id, Object... args) {

            Message message = (Message)args[1];

            /*
            final ChatMessage chatMessage
                    = new ChatMessage(
                    message.getBody(),
                    user1,
                    chat.getParticipant(),
                    "David Tekeshe",
                    DateUtils.getCurrentDate(),
                    DateUtils.getCurrentTime(),
                    "" + random.nextInt(1000),
                    false);
            */
//            chatAdapter.add(message);
            messages.add(message);
            new Handler(Looper.getMainLooper()).post(new Runnable() {

                @Override
                public void run() {
                    chatAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    public void sendTextMessage(View v) {
        String text = msg_edittext.getEditableText().toString();
        if (!text.equalsIgnoreCase("")) {

            /*
            final ChatMessage chatMessage
                    = new ChatMessage(
                            message,
                            user1,
                            peerId,
                            "David Tekeshe",
                            DateUtils.getCurrentDate(),
                            DateUtils.getCurrentTime(),
                            "" + random.nextInt(1000),
                           true);

            msg_edittext.setText("");
            chatAdapter.add(chatMessage);
            chatAdapter.notifyDataSetChanged();
            */

            Message message = new Message(
                    System.currentTimeMillis(), user1, peerId, "1", "","","",text,"",
                    MessageType.CHAT_ENTRY,
                    System.currentTimeMillis(), true
            );
            msg_edittext.setText("");

            messages.add(message);
            chatAdapter.notifyDataSetChanged();

            // sending message
            Log.i("XMPP", "" + message);

            NotificationCenter.getInstance().postNotificationName(
                    NotificationCenter.SEND_MESSAGE, message);
//            MessagingService messagingService = ((MainActivity) getActivity()).getMessagingService();
//            messagingService.xmppClient.sendMessage(chatMessage);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendMessageButton:
                sendTextMessage(v);

        }
    }

    @Override
    public void onPopBackStack() {
        /* Your code goes here, do anything you want. */
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
