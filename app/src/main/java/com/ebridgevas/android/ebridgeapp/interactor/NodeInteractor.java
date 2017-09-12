package com.ebridgevas.android.ebridgeapp.interactor;

import com.ebridgevas.android.ebridgeapp.datasource.GetNodes;
import com.ebridgevas.android.ebridgeapp.model.NodeData;

import java.util.ArrayList;

public class NodeInteractor extends GetNodes implements Interactor, GetNodes.Listener {

    GetNodes mGetNodes;

    private static final String TAG = "Interactor";

    public NodeInteractor(GetNodes getNodes) {
        mGetNodes = getNodes;
    }

    @Override
    public void getNodes(ArrayList<NodeData> nodes) {
        this.execute(nodes);
    }

    @Override
    public void execute(ArrayList<NodeData> nodes) {
        mGetNodes.getNodes(this, nodes);
    }

    @Override
    public void onNodesListReceived(ArrayList<NodeData> nodes) {
        mListener.onNodesListReceived(nodes);
    }

    @Override
    public void onNodesListError(Exception e) {
        mListener.onNodesListError(e);
    }

    @Override
    public void onNoInternetAvailable() {
        mListener.onNoInternetAvailable();
    }
}
