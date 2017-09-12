//package com.ebridgevas.android.ebridgeapp.di;
//
//import android.content.Context;
//
//import com.ebridgevas.android.ebridgeapp.activity.ChatDialogListFragment;
//import com.ebridgevas.android.ebridgeapp.activity.ServiceNodeListFragment;
//import com.ebridgevas.android.ebridgeapp.activity.YellowPageNodeListFragment;
//import com.ebridgevas.android.ebridgeapp.model.NodeData;
//import com.ebridgevas.android.ebridgeapp.presenter.impl.NodeListPresenterImpl;
//import com.ebridgevas.android.ebridgeapp.renderers.NodeRenderer;
//import com.ebridgevas.android.ebridgeapp.renderers.NodeRendererBuilder;
//import com.ebridgevas.android.ebridgeapp.services.impl.NetworkServiceStub;
//import com.pedrogomez.renderers.ListAdapteeCollection;
//import com.pedrogomez.renderers.RVRendererAdapter;
//
//import java.util.ArrayList;
//
//import dagger.Module;
//import dagger.Provides;
//
///**
// * TODO Class description
// *
// */
//@Module
//public class NodeListModule {
//
//    RVRendererAdapter<NodeData> adapter;
//    private final Context context;
//
//    public NodeListModule(final Context context, final NodeRenderer.OnNodeClicked listener) {
//       this.context = context;
//       adapter = new RVRendererAdapter<>(
////                            LayoutInflater.from(mContext),
//                            new NodeRendererBuilder(context, listener),
//                            new ListAdapteeCollection<>(new ArrayList<NodeData>())
//                    );
//
//    }
//
////    @Provides
////    NodeListPresenterImpl provideNodeListPresenter() {
//////        return new NodeListPresenterImpl(nodes, context);
////    }
//
////    @Provides
//    RVRendererAdapter<NodeData> provideNodeAdapter(final NodeListPresenterImpl presenter) {
//
////        NodeRenderer.OnNodeClicked  nodeClickListener = new NodeRenderer.OnNodeClicked() {
////
////            @Override
////            public void onPictureClicked(NodeData nodeData) {
//////                Log.i(TAG, "onPictureClicked : " + nodeData);
////                presenter.onNodePictureClicked(nodeData);
////            }
////
////            @Override
////            public void onBackgroundClicked(NodeData nodeData) {
//////                Log.i(TAG, "onBackgroundClicked : " + nodeData);
////                presenter.onNodeListRowClicked(nodeData);
////            }
////        };
////        RVRendererAdapter<NodeData>  mAdapter = new RVRendererAdapter<>(
//////                            LayoutInflater.from(mContext),
////                new NodeRendererBuilder(context, nodeClickListener),
////                new ListAdapteeCollection<>(new ArrayList<NodeData>())
////        );
////        return mAdapter;
//
//        return null;
//    }
//
//
//    @Provides
//    ServiceNodeListFragment provideServiceNodeListFragment() {
//
//        ServiceNodeListFragment fragment = new ServiceNodeListFragment();
////        Bundle args = new Bundle();
////        args.putByte(ViewPagerTabListViewFragment.ARG_TAB_ID, NodeType.SERVICE_LIST_ENTRY.getId());
//
//        // Initialize fragments.
//        // Please be sure to pass scroll position to each fragments using setArguments.
////        if (0 <= mScrollY) {
////            args.putInt(ViewPagerTabListViewFragment.ARG_INITIAL_POSITION, 1);
////        }
////        fragment.setArguments(args);
//
////        EventBus.getDefault().register(fragment);
//
////        ServicesNodeEvents events = new ServicesNodeEvents();
////        NodeType tabId = NodeType.SERVICE_LIST_ENTRY;
////        List<NodeData> children = mApiService.getChildren("600-000002-0001");
////        events.getEvents().put(tabId, children);
////
////        tabId = NodeType.CHAT_ENTRY;
////        children = mApiService.getChildren("600-000001-0001");
////        events.getEvents().put(tabId, children);
//
////        EventBus.getDefault().post(events);
//
////        fragment.setServicesNodeEvents(events);
//        return fragment;
//    }
//
//    @Provides
//    ChatDialogListFragment provideChatDialogListFragment() {
//
//        ChatDialogListFragment fragment = new ChatDialogListFragment();
//        return fragment;
//    }
//
//    @Provides
//    YellowPageNodeListFragment provideYellowPageNodeListFragment() {
//
//        YellowPageNodeListFragment fragment = new YellowPageNodeListFragment();
//        return fragment;
//    }
//
//    @Provides
////    @Singleton
//    NetworkServiceStub providesApiService() {
//        NetworkServiceStub mApiService = new NetworkServiceStub();
//        return mApiService;
//    }
//
//}
