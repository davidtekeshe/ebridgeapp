package com.ebridgevas.android.ebridgeapp.datasource;

import android.util.Log;

import com.ebridgevas.android.ebridgeapp.model.NodeData;

import java.util.ArrayList;
import java.util.List;

public abstract class GetNodes {

    private static final String TAG = "GetNodes";

    public void getNodes(final Listener listener, ArrayList<NodeData> nodes) {
        Log.i(TAG, "GetNodes(Listener, " + nodes);
        setListener(listener);
        getNodes(nodes);
    }

    public abstract void getNodes(ArrayList<NodeData> nodes);

    protected Listener mListener = new NullListener();

    protected void setListener(Listener listener) {
        if (listener != null) {
            mListener = listener;
        }
    }

    public interface Listener {
        void onNodesListReceived(ArrayList<NodeData> nodes);
        void onNodesListError(Exception e);
        void onNoInternetAvailable();
    }

    private class NullListener implements Listener {
        @Override
        public void onNodesListReceived(ArrayList<NodeData> nodes) {}
        @Override
        public void onNodesListError(Exception e) {}
        @Override
        public void onNoInternetAvailable() {}
    }
}
