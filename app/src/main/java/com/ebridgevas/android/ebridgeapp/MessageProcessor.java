package com.ebridgevas.android.ebridgeapp;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.packet.Message;

/**
 * TODO Class description
 */
public interface MessageProcessor {
    void process(Chat chat, Message message);
}
