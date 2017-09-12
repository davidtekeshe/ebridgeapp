//package com.ebridgevas.android.ebridgeapp;
//
//import android.os.Bundle;
//import android.support.v4.app.FragmentTransaction;
//
//import com.ebridgevas.android.ebridgeapp.model.ChatsNodeEvent;
//import com.ebridgevas.android.ebridgeapp.model.NodeData;
//
//import org.greenrobot.eventbus.Subscribe;
//
//public class ChatsFragment extends ViewPagerTabListViewFragment {
//
//    @Subscribe
//    public void onTreeNodeEvent(ChatsNodeEvent treeNodeEvent) {
////        Log.i(TAG, "Event Id : " + treeNodeEvent.getNodeType().getId() + ", id : ");
////        if ( event.getNodeType().getId() != mTabId ) {
////            return;
////        }
//        mTreeNodeEvent = treeNodeEvent;
////        mListView.setAdapter(new CustomListAdapter(getActivity(), event));
//    }
//
//    public void registerSelectionEvent(NodeData item) {
//        Chats chats = new Chats();
//        Bundle args = new Bundle();
//
//        args = new Bundle();
//        args.putString("TITLE", item.getParams().get("title"));
//        args.putString("AVATOR", item.getParams().get("avator"));
//        chats.setArguments(args);
//        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//
//        //                        logFrag.setConnectionID(connectionID);
//        transaction.replace(R.id.root, chats, TAG_FRAGMENT);
//        //                        SlidingTabLayout tabs = (SlidingTabLayout)getActivity().findViewById(R.id.sliding_tabs);
//        //                        ViewGroup.LayoutParams tabParams = tabs.getLayoutParams();
//        //                        tabParams.height = 0;
//
//        //                        FrameLayout container = (FrameLayout)getActivity().findViewById(R.id.container);
//        //                        container.setPadding(container.getPaddingLeft(), 0, container.getPaddingRight(), container.getPaddingBottom());
//
//        //                        LinearLayout header = (LinearLayout)getActivity().findViewById(R.id.header);
//        //                        ViewGroup.LayoutParams params = header.getLayoutParams();
//        //                        params.height = ActionBar.LayoutParams.WRAP_CONTENT;
//
//        transaction.addToBackStack(null);
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        transaction.commit();
//    }
//}
