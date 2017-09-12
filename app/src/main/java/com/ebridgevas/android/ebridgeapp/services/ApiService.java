package com.ebridgevas.android.ebridgeapp.services;

import com.ebridgevas.android.ebridgeapp.data.ServiceRequest;
import com.ebridgevas.android.ebridgeapp.data.ServiceResponse;
import com.ebridgevas.android.ebridgeapp.model.NodeData;

import java.util.List;
import java.util.Map;

public interface ApiService {

    ServiceResponse process(ServiceRequest serviceRequest);
    Map<String, NodeData> getData();
    Map<String, List<String>> getMap();
    List<NodeData> getChildren(String parentId);
}
