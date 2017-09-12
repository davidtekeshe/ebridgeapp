package com.ebridgevas.android.ebridgeapp.model;

import java.util.List;

public class ChatsNodeEvent extends TreeNodeEvent {
    public ChatsNodeEvent(MessageType nodeType, List<NodeData> children) {
        super(nodeType, children);
    }
}
