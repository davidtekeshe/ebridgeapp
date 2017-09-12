package com.ebridgevas.android.ebridgeapp.presenter;

import com.ebridgevas.android.ebridgeapp.model.NodeData;

import java.util.List;

import javax.inject.Inject;

public abstract class NodeListPresenter extends Presenter<NodeListPresenter.View> {

    public abstract void onNodePictureClicked(NodeData nodeData);
    public abstract void onNodeListRowClicked(NodeData nodeData);

    public interface View {
        void showNodeList(List<NodeData> nodes);
        void showNodelListError(Exception e);
        void showNoInternetMessage();

        void showLoading();
        void hideLoading();

        void showNodeDataPictureMessage(NodeData nodeData);
        void showNodeDataClickedMessage(NodeData nodeData);
    }
}
