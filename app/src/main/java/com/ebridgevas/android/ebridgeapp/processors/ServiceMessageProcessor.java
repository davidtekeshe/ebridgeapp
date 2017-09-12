package com.ebridgevas.android.ebridgeapp.processors;

import com.ebridgevas.android.ebridgeapp.MessageProcessor;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.packet.Message;

/**
 * TODO Class description
 */
public class ServiceMessageProcessor implements MessageProcessor {

    @Override
    public void process(Chat chat, Message message) {

        Message.Type type = message.getType();

        if (type == Message.Type.normal) {

        }
    }
}
