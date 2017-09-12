package com.ebridgevas.android.ebridgeapp.model;

import java.util.List;

public class TreeNodeEvent {
    private final MessageType mNodeType;
    private final List<NodeData> mChildren;

    public TreeNodeEvent(MessageType nodeType, List<NodeData> children) {
        mNodeType = nodeType;
        mChildren = children;
    }

    public MessageType getNodeType() {
        return mNodeType;
    }

    public List<NodeData> getChildren() {
        return mChildren;
    }
}
