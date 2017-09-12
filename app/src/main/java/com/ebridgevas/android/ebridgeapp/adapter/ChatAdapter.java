package com.ebridgevas.android.ebridgeapp.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebridgevas.android.ebridgeapp.R;
import com.ebridgevas.android.ebridgeapp.model.ChatMessage;
import com.ebridgevas.android.ebridgeapp.model.Message;
import com.ebridgevas.android.ebridgeapp.util.AndroidUtilities;
import com.ebridgevas.android.ebridgeapp.widget.Emoji;

public class ChatAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private ArrayList<Message> messages;
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("h:mm a", Locale.US);

    public ChatAdapter(Activity activity, ArrayList<Message> messages) {
        this.messages = messages;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        Message message = messages.get(position);
        InboundViewHolder inboundViewHolder;
        OutboundViewHolder outboundViewHolder;

        Log.i("TEST", "message: " + message);
        Log.i("TEST", "conventView " + convertView);

        if (message.isMine()) {
            try {
//            if ( convertView == null ) {
                view = inflater.inflate(R.layout.outbound_message_bundle, null, false);

                TextView messageTextView = (TextView) view.findViewById(R.id.outbound_text);
                TextView timeTextView = (TextView) view.findViewById(R.id.outbound_time);
                ImageView messageStatus = (ImageView) view.findViewById(R.id.outbound_status);
                outboundViewHolder = new OutboundViewHolder(messageTextView, timeTextView, messageStatus);

//            } else {
//                view = convertView;
//                outboundViewHolder = (OutboundViewHolder) view.getTag();
//            }

                outboundViewHolder
                        .getMessageTextView()
                        .setText(
                                Html.fromHtml(
                                        Emoji.replaceEmoji(
                                                message.getBody(),
                                                outboundViewHolder.getMessageTextView().getPaint()
                                                        .getFontMetricsInt(), AndroidUtilities.dp(16))
                                                + " &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;" +
                                                "&#160;&#160;&#160;&#160;&#160;&#160;&#160;"));
                outboundViewHolder.getTimeTextView().setText(SIMPLE_DATE_FORMAT.format(message.getDate()));
            } catch (Exception e) {
                Log.e("XMPP", e.getMessage());
            }

        } else {

//            if (convertView == null) {
                view = inflater.inflate(R.layout.inbound_message_bubble, null, false);
                TextView inboundText = (TextView) view.findViewById(R.id.inbound_text);
                TextView inboundTime = (TextView) view.findViewById(R.id.inbound_time);
                inboundViewHolder = new InboundViewHolder(inboundText, inboundTime);

                view.setTag(inboundViewHolder);
//            } else {
//                view = convertView;
//                inboundViewHolder = (InboundViewHolder)view.getTag();
//            }

            inboundViewHolder.getMessageTextView().setText(
                    Html.fromHtml(Emoji.replaceEmoji(
                            message.getBody(),
                            inboundViewHolder.getMessageTextView().getPaint().getFontMetricsInt(),
                            AndroidUtilities.dp(16))
                            + " &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;" ));
            inboundViewHolder.getTimeTextView().setText(SIMPLE_DATE_FORMAT.format(message.getDate()));

//            if (message.getMessageStatus() == Status.DELIVERED) {
//                holder2.messageStatus.setImageDrawable(context.getResources().getDrawable(R.drawable.message_got_receipt_from_target));
//            } else if (message.getMessageStatus() == Status.SENT) {
//                holder2.messageStatus.setImageDrawable(context.getResources().getDrawable(R.drawable.message_got_receipt_from_server));
//
//            }
        }

//        convertView = inflater.inflate(R.layout.inbound_message_bubble, null);
//        TextView msg = (TextView) vi.findViewById(R.id.message_text);
//        msg.setText(message.getBody());
//        FrameLayout layout = (FrameLayout) vi
//                .findViewById(R.id.bubble_layout);
//        LinearLayout parent_layout = (LinearLayout) vi
//                .findViewById(R.id.bubble_layout_parent);
//
//        if (message.isMine()) {
//            //align right
//            layout.setBackgroundResource(R.drawable.bubble2);
//            parent_layout.setGravity(Gravity.RIGHT);
//        } else {
//            // align left
//            layout.setBackgroundResource(R.drawable.bubble1);
//            parent_layout.setGravity(Gravity.LEFT);
//        }
//        msg.setTextColor(Color.BLACK);

        return view;
    }

//    public View getView(int position, View convertView, ViewGroup parent) {
//        ChatMessage message = messages.get(position);
//        View vi = convertView;
//        if (convertView == null)
//            vi = inflater.inflate(R.layout.inbound_message_bubble, null);
//
//        TextView msg = (TextView) vi.findViewById(R.id.message_text);
//        msg.setText(message.getBody());
//        FrameLayout layout = (FrameLayout) vi
//                .findViewById(R.id.bubble_layout);
//        LinearLayout parent_layout = (LinearLayout) vi
//                .findViewById(R.id.bubble_layout_parent);
//
//        if (message.isMine()) {
//            //align right
//            layout.setBackgroundResource(R.drawable.bubble2);
//            parent_layout.setGravity(Gravity.RIGHT);
//        } else {
//            // align left
//            layout.setBackgroundResource(R.drawable.bubble1);
//            parent_layout.setGravity(Gravity.LEFT);
//        }
//        msg.setTextColor(Color.BLACK);
//        return vi;
//    }

    public void add(Message message) {
        messages.add(message);
    }

    private class MessageViewHolder {
        private final TextView messageTextView;
        private final TextView timeTextView;

        public MessageViewHolder(TextView messageTextView, TextView timeTextView) {
            this.messageTextView = messageTextView;
            this.timeTextView = timeTextView;
        }

        public TextView getMessageTextView() {
            return messageTextView;
        }

        public TextView getTimeTextView() {
            return timeTextView;
        }
    }

    private class InboundViewHolder extends MessageViewHolder {
        public InboundViewHolder(TextView messageTextView, TextView timeTextView) {
            super(messageTextView, timeTextView);
        }
    }

    private class OutboundViewHolder extends MessageViewHolder {

        private final ImageView messageStatus;

        public OutboundViewHolder(TextView messageTextView, TextView timeTextView, ImageView messageStatus) {
            super(messageTextView, timeTextView);
            this.messageStatus = messageStatus;
        }

        public ImageView getMessageStatus() {
            return messageStatus;
        }
    }
}