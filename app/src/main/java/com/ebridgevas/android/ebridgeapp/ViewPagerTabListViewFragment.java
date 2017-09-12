//
//package com.ebridgevas.android.ebridgeapp;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ListView;
//
//import com.ebridgevas.android.ebridgeapp.di.DaggerRootComponent;
//import com.ebridgevas.android.ebridgeapp.di.MainModule;
//import com.ebridgevas.android.ebridgeapp.di.RootComponent;
//import com.ebridgevas.android.ebridgeapp.EBridgeApplication;
//import com.ebridgevas.android.ebridgeapp.model.NodeData;
//import com.ebridgevas.android.ebridgeapp.model.NodeType;
//import com.ebridgevas.android.ebridgeapp.model.ServicesNodeEvent;
//import com.ebridgevas.android.ebridgeapp.model.TreeNodeEvent;
//import com.ebridgevas.android.ebridgeapp.observablescrollview.ObservableListView;
//import com.ebridgevas.android.ebridgeapp.services.impl.NetworkServiceStub;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.inject.Inject;
//
//public class ViewPagerTabListViewFragment extends BaseFragment {
//
//    public static final String ARG_INITIAL_POSITION = "ARG_INITIAL_POSITION";
//    public static final String ARG_TAB_ID = "ARG_TAB_ID";
//    private ListView listView;
//    private Byte mTabId;
//    protected TreeNodeEvent mTreeNodeEvent;
//
//    private static final String TAG = "ViewPagerTabListView";
//
//    private static final Map<Integer, String> ROOT_NODES;
//    static  {
//        ROOT_NODES = new HashMap<>();
//        ROOT_NODES.put(0, "600-000002-0001");
//        ROOT_NODES.put(1, "600-000001-0020");
//        ROOT_NODES.put(2, "600-000001-0001");
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.fragment_listview, container, false);
//        Activity parentActivity = getActivity();
//        listView = (ObservableListView) view.findViewById(R.id.scroll);
////        setDummyDataWithHeader(listView, inflater.inflate(R.layout.padding, listView, false));
//        Bundle args = getArguments();
////        Integer tabId = null;
//        if (args != null && args.containsKey(ARG_TAB_ID)) {
//            mTabId = Byte.valueOf(args.getString(ARG_TAB_ID));
//        }
//
//        Log.i(TAG, "Tab Id : " + mTabId);
//
//        String nodeId = ROOT_NODES.get(mTabId.intValue());
//        NodeType nodeType = NodeType.getById(mTabId);
//        mTreeNodeEvent = new ServicesNodeEvent(nodeType, mNetworkServiceStub.getChildren(nodeId));
//        setDialogsDataWithHeader(mListView, inflater.inflate(R.layout.padding, mListView, false), mTreeNodeEvent);
//
//
//        if (parentActivity instanceof ObservableScrollViewCallbacks) {
//            // Scroll to the specified position after layout
//            if (args != null && args.containsKey(ARG_INITIAL_POSITION)) {
//                final int initialPosition = args.getInt(ARG_INITIAL_POSITION, 0);
//                ScrollUtils.addOnGlobalLayoutListener(
//                        listView, new Runnable() {
//                            @Override
//                            public void run() {
//                                // scrollTo() doesn't work, should use setSelection()
//                                listView.setSelection(initialPosition);
//                            }
//                        });
//            }
//
//            // TouchInterceptionViewGroup should be a parent view other than ViewPager.
//            // This is a workaround for the issue #117:
//            // https://github.com/ksoichiro/Android-ObservableScrollView/issues/117
//            listView.setTouchInterceptionViewGroup((ViewGroup) parentActivity.findViewById(R.id.root));
//
//            listView.setScrollViewCallbacks((ObservableScrollViewCallbacks) parentActivity);
//        }
//
//        return mListView;
//    }
//
//    @Override
//    public void registerSelectionEvent(NodeData item) {
//    }
//
//}
