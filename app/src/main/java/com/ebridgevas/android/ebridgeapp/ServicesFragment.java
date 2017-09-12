//package com.ebridgevas.android.ebridgeapp;
//
//import android.os.Bundle;
//import android.support.v4.app.FragmentTransaction;
//import android.util.Log;
//
//import com.ebridgevas.android.ebridgeapp.activity.MainActivity;
//import com.ebridgevas.android.ebridgeapp.model.NodeData;
//import com.ebridgevas.android.ebridgeapp.model.NodeType;
//import com.ebridgevas.android.ebridgeapp.model.ServicesNodeEvents;
//import com.ebridgevas.android.ebridgeapp.model.TreeNodeEvent;
//
//import java.util.List;
//
//public class ServicesFragment extends ViewPagerTabListViewFragment implements Cloneable {
//
//
//    private ServicesNodeEvents mServicesNodeEvents;
//
//    public void setServicesNodeEvents(ServicesNodeEvents servicesNodeEvents) {
//        mServicesNodeEvents = servicesNodeEvents;
//    }
////
////    @Subscribe
////    public void onTreeNodeEvent(ServicesNodeEvents servicesNodeEvents) {
//////        Log.i(TAG, "Event Id : " + treeNodeEvent.getNodeType().getId() + ", id : ");
//////        if ( event.getNodeType().getId() != mTabId ) {
//////            return;
//////        }
////
//////        mListView.setAdapter(new CustomListAdapter(getActivity(), event));
////        mServicesNodeEvents = servicesNodeEvents;
////    }
//
//    public void setNodeType(NodeType nodeType) {
//
////        List<NodeData> children = mApiService.getChildren("600-000001-0001");
//
//        ServicesNodeEvents events = new ServicesNodeEvents();
//        NodeType tabId = NodeType.SERVICE_LIST_ENTRY;
//        List<NodeData> children = mNetworkServiceStub.getChildren("600-000002-0001");
//        events.getEvents().put(tabId, children);
//
//        tabId = NodeType.CHAT_ENTRY;
//        children = mNetworkServiceStub.getChildren("600-000001-0001");
//        events.getEvents().put(tabId, children);
//
//        Log.i("Frag ", "nodetype : " + nodeType);
//        Log.i("Frag", "" + mServicesNodeEvents.getEvents().get(nodeType));
//        mTreeNodeEvent = new TreeNodeEvent(nodeType, mServicesNodeEvents.getEvents().get(nodeType));
//    }
//
//    public void registerSelectionEvent(NodeData item) {
//        ServiceFragment marketBrowserFragment = new ServiceFragment();
//        Bundle args = new Bundle();
//        args.putString("TITLE", item.getParams().get("title"));
//        args.putString("AVATOR", item.getParams().get("avator"));
//        marketBrowserFragment.setArguments(args);
//
//        String itemId = item.getId();
//
//        MainActivity activity = (MainActivity)getActivity();
////        ApiService networkService = activity.getNetworkService();
////        Log.i("ServicesFragment", "networkService : \n\n\n#########" + networkService);
//        List<NodeData> nodes = null; //networkService.getChildren(itemId);
//        Log.i("ServicesFragment", "\n\n\n######### nodes : " + nodes);
//        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.root, marketBrowserFragment, TAG_FRAGMENT);
//        transaction.addToBackStack(null);
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        transaction.commit();
//    }
//
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }
//}
