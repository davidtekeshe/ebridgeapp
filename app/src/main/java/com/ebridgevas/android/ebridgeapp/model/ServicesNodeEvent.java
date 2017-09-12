package com.ebridgevas.android.ebridgeapp.model;

import java.util.List;

public class ServicesNodeEvent extends TreeNodeEvent {
    public ServicesNodeEvent(MessageType nodeType, List<NodeData> children) {
        super(nodeType, children);
    }
}
