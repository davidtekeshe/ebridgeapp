package com.ebridgevas.android.ebridgeapp.model;

import java.util.HashMap;
import java.util.Map;

public class Node {

    private final String nodeId;
    private Map<String, String> params;

//    private final String nodeName;
//    private final String nodePicture;
//    private final String lastMessage;
//    private final String mTicker;
//    private final String mCounter;
//    private final String mSubCounter;


    public Node(String nodeId) {
        this.nodeId = nodeId;
        params = new HashMap<>();
    }

    public Node(String nodeId, Map<String, String> params) {
        this.nodeId = nodeId;
        this.params = params;
    }

    public Map<String, String> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }
}
