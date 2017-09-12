//package com.ebridgevas.android.ebridgeapp.di;
//
//import android.content.Context;
//
////import com.ebridgevas.android.ebridgeapp.ChatsFragment;
////import com.ebridgevas.android.ebridgeapp.ServicesFragment;
////import com.ebridgevas.android.ebridgeapp.ViewPagerTabListViewFragment;
//import com.ebridgevas.android.ebridgeapp.activity.NodeListFragment;
//import com.ebridgevas.android.ebridgeapp.datasource.datafile.GetNodesImpl;
//import com.ebridgevas.android.ebridgeapp.EBridgeApplication;
//import com.ebridgevas.android.ebridgeapp.interactor.NodeInteractor;
//import com.ebridgevas.android.ebridgeapp.presenter.NodeListPresenter;
//import com.ebridgevas.android.ebridgeapp.presenter.impl.NodeListPresenterImpl;
//import com.ebridgevas.android.ebridgeapp.services.impl.NetworkServiceStub;
//
//import javax.inject.Named;
//import javax.inject.Singleton;
//
//import dagger.Module;
//import dagger.Provides;
//
////import com.ebridgevas.android.ebridgeapp.activity.NodeListFragment;
//
///**
// * TODO Class description
// *
// */
//
//@Module
//public class MainModule {
//
//    private EBridgeApplication application;
//    NodeInteractor interactor;
//
//
//    public MainModule(EBridgeApplication application) {
//        this.application = application;
////        interactor = new NodeInteractor(new GetNodesImpl(application)); // new GetNodesMockImpl()
//
//    }
//
//    @Provides
//    @Named("applicationContext")
//    Context provideApplicationContext() {
//        return application.getApplicationContext();
//    }
//
//    @Provides
//    NodeInteractor provideNodeInteractor() {
//        return interactor;
//    }
//
//
////    @Provides
////    public NodeListPresenter providesNodeListPresenter(){
////        NodeListPresenter presenter = new NodeListPresenterImpl(mApplication);
////        return presenter;
////    }
////    @Provides
////    ChatsFragment provideChatsFragment() {
////
////        ChatsFragment fragment = new ChatsFragment();
////
////        Bundle args = new Bundle();
////        args.putByte(ViewPagerTabListViewFragment.ARG_TAB_ID, NodeType.CHAT_ENTRY.getId());
////
//////        if (0 < mScrollY) {
////            args.putInt(ViewPagerTabListViewFragment.ARG_INITIAL_POSITION, 1);
//////        }
////        fragment.setArguments(args);
////
//////        EventBus.getDefault().register(fragment);
//////        List<NodeData> children = mApiService.getChildren("600-000001-0001");
//////
//////        NodeType tabId = NodeType.CHAT_ENTRY;
//////        ChatsNodeEvent chatsNodeEvent = new ChatsNodeEvent(tabId, children);
//////        EventBus.getDefault().post(chatsNodeEvent);
////
////        return fragment;
////    }
//
//    /*
//    @Provides
//    NodeListFragment provideNodeListFragment() {
//
//        fragment = new NodeListFragment();
//
//        Bundle args = new Bundle();
//        args.putByte(ViewPagerTabListViewFragment.ARG_TAB_ID, NodeType.YELLOW_PAGE_ENTRY.getId());
//        if (0 < mScrollY) {
//            args.putInt(ViewPagerTabListViewFragment.ARG_INITIAL_POSITION, 1);
//        }
//        fragment.setArguments(args);
//
//        EventBus.getDefault().register(fragment);
//        List<NodeData> children = mNetworkService.getChildren("600-000001-0001");
//
//        NodeType tabId = NodeType.YELLOW_PAGE_ENTRY;
//        YellowPageNodeEvent yellowPageNodeEvent = new YellowPageNodeEvent(tabId, children);
//        EventBus.getDefault().post(yellowPageNodeEvent);
//
//
//
//        return null;
//    }
//    */
//}
