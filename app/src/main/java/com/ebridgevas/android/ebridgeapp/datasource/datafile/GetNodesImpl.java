package com.ebridgevas.android.ebridgeapp.datasource.datafile;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.ebridgevas.android.ebridgeapp.R;
import com.ebridgevas.android.ebridgeapp.datasource.GetNodes;
import com.ebridgevas.android.ebridgeapp.datasource.api.GetNodeDataListResponse;
import com.ebridgevas.android.ebridgeapp.datasource.api.rest.model.NodeDataApiEntry;
import com.ebridgevas.android.ebridgeapp.model.NodeData;
import com.ebridgevas.android.ebridgeapp.model.Tree;
import com.ebridgevas.android.ebridgeapp.services.impl.NetworkServiceStub;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * TODO Class description
 *
 */
public class GetNodesImpl extends GetNodes {

//    @Inject
//    NetworkServiceStub mNetworkServiceStub;

    private Context mContext = null;

    private static final String TAG = "GetNodes";

    public GetNodesImpl() {
    }

    @Override
    public void getNodes(ArrayList<NodeData> list) {
        try {
            Log.i(TAG, "getNodes(" + list + ")");
//            NodeData nodeData = new NodeData(parentId, null, null, 0);
//            Tree<NodeData> tree = new NetworkServiceStub().tree();
//            Collection<NodeData> children = tree.getSuccessors(nodeData);
//
//            final List<NodeData> nodes = new ArrayList<>(children);
//            Collections.sort(nodes, new NodeData.NodeDataComparator());
            final ArrayList<NodeData> nodes = list;
            Log.i(TAG, "nodes : " + nodes);
//            final List<NodeData> nodes = mNetworkServiceStub.getTree(parentId); //parseNodesFromRawFile(R.raw.users);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mListener.onNodesListReceived(nodes);
                }
            }, 3000);

        } catch (Exception e) {
            mListener.onNodesListError(e);
        }
    }
//
//    private List<NodeData> parseNodesFromRawFile(int resId) throws IOException {
//
//        InputStream inputStream = mContext.getResources().openRawResource(resId);
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//
//        int x = -1;
//        byte[] buffer = new byte[1024];
//        while ( (x = inputStream.read(buffer, 0, buffer.length)) != -1) {
//            bos.write(buffer, 0, x);
//        }
//        inputStream.close();
//
//        String json = bos.toString();
//        return getNodeDataFromJson(json);
//    }

//    private List<NodeData> getNodeDataFromJson(String json) {
//
//        /*
//        GetNodeDataListResponse response = new Gson().fromJson(json, GetNodeDataListResponse.class);
//        List<NodeData> nodeDataList = new ArrayList<>();
//        for (NodeDataApiEntry entry : response.getResults()) {
//            nodeDataList.add(entry.parseNodeData());
//        }
//
//        return nodeDataList;
//        */
//    }
}
