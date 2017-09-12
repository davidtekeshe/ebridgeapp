//package com.ebridgevas.android.ebridgeapp.presenter.impl;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.ebridgevas.android.ebridgeapp.datasource.GetNodes;
//import com.ebridgevas.android.ebridgeapp.datasource.datafile.GetNodesImpl;
//import com.ebridgevas.android.ebridgeapp.di.DaggerNodeListComponent;
//import com.ebridgevas.android.ebridgeapp.di.DaggerRootComponent;
//import com.ebridgevas.android.ebridgeapp.di.MainModule;
//import com.ebridgevas.android.ebridgeapp.di.NodeListComponent;
//import com.ebridgevas.android.ebridgeapp.di.NodeListModule;
//import com.ebridgevas.android.ebridgeapp.di.RootComponent;
//import com.ebridgevas.android.ebridgeapp.EBridgeApplication;
//import com.ebridgevas.android.ebridgeapp.interactor.NodeInteractor;
//import com.ebridgevas.android.ebridgeapp.model.NodeData;
//import com.ebridgevas.android.ebridgeapp.presenter.NodeListPresenter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.inject.Inject;
//
///**
// * TODO Class description
// *
// */
//public class NodeListPresenterImpl extends NodeListPresenter implements GetNodes.Listener {
//
//    protected Context mContext;
//
//    protected NodeInteractor interactor;
//
//    private ArrayList<NodeData> nodes;
//
//    private static final String TAG = "Presenter";
//
//    public NodeListPresenterImpl(ArrayList<NodeData> nodes, Context context) {
//        this.nodes = nodes;
//        mContext = context;
//    }
//
//    @Override
//    public void initialize(String parentId) {
//        Log.i(TAG, "initialize (" + parentId + ")");
//        view.showLoading();
//        interactor = new NodeInteractor(new GetNodesImpl());
//        interactor.getNodes(this, nodes);
//    }
//
//    @Override
//    public void resume() {
//    }
//
//    @Override
//    public void pause() {
//    }
//
//    @Override
//    public void destroy() {
//    }
//
//    @Override
//    public void onNodesListReceived(ArrayList<NodeData> nodes) {
//        Log.i(TAG, "onNodesListReceived : " + nodes);
//        view.showNodeList(nodes);
//        view.hideLoading();
//    }
//
//    @Override
//    public void onNodesListError(Exception e) {
//        Log.i(TAG, "onNodesListError : " + e.getMessage());
//        view.showNodelListError(e);
//        view.hideLoading();
//    }
//
//    @Override
//    public void onNoInternetAvailable() {
//        view.showNoInternetMessage();
//        view.hideLoading();
//    }
//
//    @Override
//    public void onNodePictureClicked(NodeData nodeData) {
//        Log.i(TAG, "onNodePictureClicked : " + nodeData);
//        view.showNodeDataPictureMessage(nodeData);
//    }
//
//    @Override
//    public void onNodeListRowClicked(NodeData nodeData) {
//        Log.i(TAG, "onNodeListRowClicked : " + nodeData);
//        view.showNodeDataClickedMessage(nodeData);
//    }
//}
