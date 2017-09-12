package com.ebridgevas.android.ebridgeapp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO Class description
 */
public class ServicesNodeEvents {

    private Map<MessageType, List<NodeData>> events;

    public ServicesNodeEvents() {
        this.events = new HashMap<>();
    }

    public Map<MessageType,  List<NodeData>> getEvents() {
        return events;
    }

    public void setEvents(Map<MessageType,  List<NodeData>> events) {
        this.events = events;
    }
}
