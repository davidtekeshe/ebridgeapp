package com.ebridgevas.android.ebridgeapp.datasource.api.rest.model;

import com.ebridgevas.android.ebridgeapp.model.NodeData;

/**
 * TODO Class description
 */
public class NodeDataApiEntry {

    private static final String SPACE = " ";
    private NodeDataApi mNodeData;

    public NodeDataApi getNodeData() {
        return mNodeData;
    }

    public NodeData parseNodeData() {
        NodeData n = new NodeData();

        n.setId(getNodeData().getId());
        n.setParentId(getNodeData().getParentId());
        n.setNodeType(getNodeData().getNodeType());
        n.setParams(getNodeData().getParams());

        return n;
    }
}
