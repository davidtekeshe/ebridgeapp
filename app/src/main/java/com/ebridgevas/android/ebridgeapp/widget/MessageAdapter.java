package com.ebridgevas.android.ebridgeapp.widget;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ebridgevas.android.ebridgeapp.R;
import com.ebridgevas.android.ebridgeapp.database.MessageData;

import java.util.List;

public class MessageAdapter extends ArrayAdapter {
    private final Activity mActivity;
    private final List mMessages;

    public MessageAdapter(Activity activity, List objs) {
        super(activity, R.layout.message_list, objs);
        mActivity = activity;
        mMessages = objs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        MessageView messageView = null;

        if (rowView == null) {
            // Get a new instance of the row layout view
            LayoutInflater inflater = mActivity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.message_list, null);

            // Hold the view objects in an object
            // so they don't need to be re-fetched
            messageView = new MessageView();
            messageView.message = (TextView)rowView.findViewById(R.id.message_text);

            // Cache the view objects in the tag
            // so they can be re-accessed later
            rowView.setTag(messageView);
        } else {
            messageView = (MessageView)rowView.getTag();
        }

        // Transfer the stock data from the data object
        // to the view objects
        MessageData currentMessage = (MessageData)mMessages.get(position);
        messageView.message.setText(currentMessage.getMessage());

        return rowView;
    }

    protected static class MessageView {
        protected TextView message;
    }
}
