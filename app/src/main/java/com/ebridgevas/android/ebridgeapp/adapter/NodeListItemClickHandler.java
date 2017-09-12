//package com.ebridgevas.android.ebridgeapp.adapter;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentTransaction;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ListView;
//
//import com.ebridgevas.android.ebridgeapp.ChatFragment;
//import com.ebridgevas.android.ebridgeapp.MarketBrowserFragment;
//import com.ebridgevas.android.ebridgeapp.R;
//import com.ebridgevas.android.ebridgeapp.model.NodeData;
//
///**
// * TODO Method description
// */
//public class NodeListItemClickHandler {
//
//    public static final String ARG_PARENT_ID = "ARG_PARENT_ID";
//
//    private FragmentActivity activity;
//    private String description;
//
//    public NodeListItemClickHandler(FragmentActivity activity, String description) {
//        this.activity = activity;
//        this.description = description;
//    }
//
//    public void handle(ListView listView) {
//
//        listView.setOnItemClickListener(
//                new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Log.i("NodeClick", "AdapterView.OnItemClickListener()");
//                        NodeData item = (NodeData) parent.getAdapter().getItem(position);
//
//                        Log.i("NodeClick", "description : " + description);
//                        Log.i("NodeClick", " item : " + item);
//
//                        String processorName = "com.ebridgevas.android.ebridgeapp."+ item.getParams().get("processor");
//                        Log.i("NodeClick", "processorName: " + processorName);
//
//                        try {
//                            Fragment fragment = (Fragment)Class.forName(processorName).newInstance();
//                            Bundle args = new Bundle();
//                            args.putString("TITLE", item.getParams().get("node-text-top-left"));
//                            args.putString("AVATOR", item.getParams().get("thumbnail"));
//                            args.putString(ARG_PARENT_ID, item.getId());
//                            args.putString(ChatFragment.ARG_PEER_ID, item.getParams().get("id"));
//                            fragment.setArguments(args);
//                            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
//                            transaction.replace(R.id.root, fragment, "NodeClick");
//                            transaction.addToBackStack(null);
//                            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                            transaction.commit();
//
//                        } catch (InstantiationException e) {
//                            e.printStackTrace();
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        } catch (ClassNotFoundException e) {
//                            e.printStackTrace();
//                        }
//                        /*
//                        switch (description) {
//
//                            case "SERVICES":
//                            default:
//                                MarketBrowserFragment marketBrowserFragment = new MarketBrowserFragment();
//                                Bundle args = new Bundle();
//                                args.putString("TITLE", item.getParams().get("node-text-top-left"));
//                                args.putString("AVATOR", item.getParams().get("thumbnail"));
//                                marketBrowserFragment.setArguments(args);
//                                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
//                                transaction.replace(R.id.root, marketBrowserFragment, "NodeClick");
//                                transaction.addToBackStack(null);
//                                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                                transaction.commit();
//                            break;
//                        }
//                        */
//                        /*
//                        registerSelectionEvent(item);
//
//
//                        Log.i(".ebridge.BaseFragment", "Clicked : " + ((ServiceDTO)adapter.getItem(position)).toString());
//
//
//                        switch (tabId) {
//
//                            case 0:
//                                MarketBrowserFragment marketBrowserFragment = new MarketBrowserFragment();
//                                Bundle args = new Bundle();
//                                args.putString("TITLE", item.getParams().get("title"));
//                                args.putString("AVATOR", item.getParams().get("avator"));
//                                marketBrowserFragment.setArguments(args);
//                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                                transaction.replace(R.id.root, marketBrowserFragment, TAG_FRAGMENT);
//                                transaction.addToBackStack(null);
//                                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                                transaction.commit();
//                                break;
//                            case 1:
//                                Chats chats = new Chats();
//                                args = new Bundle();
//                                args.putString("TITLE", item.getServiceTitle());
//                                args.putString("AVATOR", item.getAvator());
//                                chats.setArguments(args);
//                                transaction = getActivity().getSupportFragmentManager().beginTransaction();
//        //                        logFrag.setConnectionID(connectionID);
//                                transaction.replace(R.id.root, chats, TAG_FRAGMENT);
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
//                                transaction.addToBackStack(null);
//                                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                                transaction.commit();
//                            break;
//
//                        }
//                        */
//
//                    }
//                });
//    }
//}
