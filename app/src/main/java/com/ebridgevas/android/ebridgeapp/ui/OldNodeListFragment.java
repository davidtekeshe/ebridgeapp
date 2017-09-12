//
//package com.ebridgevas.android.ebridgeapp.activity;
//
//import android.graphics.Color;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentTransaction;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ListView;
//
//import com.ebridgevas.android.ebridgeapp.R;
//import com.ebridgevas.android.ebridgeapp.ServiceFragment;
//import com.ebridgevas.android.ebridgeapp.adapter.CustomListAdapter;
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
//import com.ebridgevas.android.ebridgeapp.util.TreeNode;
//
//import java.util.List;
//
//import javax.inject.Inject;
//
//public class OldNodeListFragment extends Fragment implements Cloneable {
//
//    public static final String ARG_INITIAL_POSITION = "ARG_INITIAL_POSITION";
//    protected final static String TAG_FRAGMENT = "TAG_FRAMENT";
//
//    public static final String ARG_TAB_ID = "ARG_TAB_ID";
//    private ListView mListView;
//    private Integer mTabId;
//    protected TreeNodeEvent mTreeNodeEvent;
//
//    private static final String TAG = "ViewPagerTabListView";
//
//    @Inject
//    NetworkServiceStub mNetworkServiceStub;
//
//    RootComponent mRootComponent;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.fragment_listview, container, false);
//        component().inject(this);
//        mListView = (ObservableListView) view.findViewById(R.id.scroll);
//        Bundle args = getArguments();
//        if (args != null && args.containsKey(ARG_TAB_ID)) {
//            mTabId = args.getInt(ARG_TAB_ID);
//        }
//
//        Log.i(TAG, "Tab Id : " + mTabId);
//
//        TreeNode<NodeData> rootNode = mNetworkServiceStub.buildTree();
//
//        TreeNode<NodeData> thisNode = rootNode.getChildAt( mTabId );
//        List<NodeData> children = thisNode.childrenData();
//        NodeType nodeType = NodeType.getById(Byte.valueOf( "" + mTabId));
//        mTreeNodeEvent = new ServicesNodeEvent(nodeType, children );
//        View headerView = inflater.inflate(R.layout.padding, mListView, false);
//        mListView.addHeaderView(headerView);
//
//        mListView.setBackgroundColor(
//                mTreeNodeEvent.getNodeType() == NodeType.YELLOW_PAGE_ENTRY ? Color.rgb(255, 255, 204) : Color.WHITE);
//        mListView.setAdapter(
//                new CustomListAdapter(getActivity(), mTreeNodeEvent)
//        );
//        mListView.setOnItemClickListener(
//                new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        NodeData item = (NodeData) parent.getAdapter().getItem(position);
//
//                        registerSelectionEvent(item);
//
//
//                        switch (mTabId) {
//
//                            case 0:
//                            default:
//                                TabbedFragment tabbedFragment = new TabbedFragment();
//                                Bundle args = new Bundle();
//                                args.putString("TITLE", item.getParams().get("node-text-top-left"));
//                                args.putString("AVATOR", item.getParams().get("node-thumbnal"));
//                                tabbedFragment.setArguments(args);
//                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                                transaction.replace(R.id.root, tabbedFragment, TAG_FRAGMENT);
//                                transaction.addToBackStack(null);
//                                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                                transaction.commit();
//                                break;
////                            case 1:
////                                Chats chats = new Chats();
////                                args = new Bundle();
////                                args.putString("TITLE", item.getServiceTitle());
////                                args.putString("AVATOR", item.getAvator());
////                                chats.setArguments(args);
////                                transaction = getActivity().getSupportFragmentManager().beginTransaction();
////        //                        logFrag.setConnectionID(connectionID);
////                                transaction.replace(R.id.root, chats, TAG_FRAGMENT);
////        //                        SlidingTabLayout tabs = (SlidingTabLayout)getActivity().findViewById(R.id.sliding_tabs);
////        //                        ViewGroup.LayoutParams tabParams = tabs.getLayoutParams();
////        //                        tabParams.height = 0;
////
////        //                        FrameLayout container = (FrameLayout)getActivity().findViewById(R.id.container);
////        //                        container.setPadding(container.getPaddingLeft(), 0, container.getPaddingRight(), container.getPaddingBottom());
////
////        //                        LinearLayout header = (LinearLayout)getActivity().findViewById(R.id.header);
////        //                        ViewGroup.LayoutParams params = header.getLayoutParams();
////        //                        params.height = ActionBar.LayoutParams.WRAP_CONTENT;
////
////                                transaction.addToBackStack(null);
////                                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
////                                transaction.commit();
////                            break;
//                        }
//
//                    }
//                });
//        /*
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
//        */
//        return mListView;
//    }
//
////    @Override
//    public void registerSelectionEvent(NodeData item) {
//        ServiceFragment marketBrowserFragment = new ServiceFragment();
//        Bundle args = new Bundle();
//        args.putString("TITLE", item.getParams().get("node-text-top-left"));
//        args.putString("AVATOR", item.getParams().get("node-thumbnal"));
//        marketBrowserFragment.setArguments(args);
//
//        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.root, marketBrowserFragment, TAG_FRAGMENT);
//        transaction.addToBackStack(null);
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        transaction.commit();
//    }
//
//    private RootComponent component(){
//        if ( mRootComponent == null) {
//            mRootComponent = DaggerRootComponent
//                    .builder()
//                    .mainModule(new MainModule((EBridgeApplication) getActivity().getApplication())).build();
//
//        }
//        return mRootComponent;
//    }
//
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }
//}
