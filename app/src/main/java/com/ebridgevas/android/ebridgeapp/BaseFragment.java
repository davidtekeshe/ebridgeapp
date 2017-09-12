//
//package com.ebridgevas.android.ebridgeapp;
//
//import android.app.Activity;
//import android.content.ContentResolver;
//import android.content.res.TypedArray;
//import android.database.Cursor;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.util.TypedValue;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.GridView;
//import android.widget.ListView;
//import android.widget.SimpleCursorAdapter;
//
//import android.support.v4.app.LoaderManager;
//import android.support.v4.content.Loader;
//
//import com.ebridgevas.android.ebridgeapp.adapter.CustomListAdapter;
//import com.ebridgevas.android.ebridgeapp.model.NodeData;
//import com.ebridgevas.android.ebridgeapp.model.NodeType;
//import com.ebridgevas.android.ebridgeapp.model.ServiceDTO;
//import com.ebridgevas.android.ebridgeapp.model.TreeNodeEvent;
//import com.ebridgevas.android.ebridgeapp.observablescrollview.ObservableGridView;
//
//import java.util.ArrayList;
//
//public abstract class BaseFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
//
//    private SimpleCursorAdapter mAdapter;
//
//    protected final static String TAG_FRAGMENT = "TAG_FRAMENT";
//
//    public static ArrayList<String> getDummyData() {
//        return BaseActivity.getDummyData();
//    }
//
//    public static ArrayList<ServiceDTO> getDialogsData(Integer tabId, ContentResolver resolver) {
//        return BaseActivity.getDialogsData(tabId, resolver);
//    }
//
//    protected int getActionBarSize() {
//        Activity activity = getActivity();
//        if (activity == null) {
//            return 0;
//        }
//        TypedValue typedValue = new TypedValue();
//        int[] textSizeAttr = new int[]{R.attr.actionBarSize};
//        int indexOfAttrTextSize = 0;
//        TypedArray a = activity.obtainStyledAttributes(typedValue.data, textSizeAttr);
//        int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
//        a.recycle();
//        return actionBarSize;
//    }
//
//    protected int getScreenHeight() {
//        Activity activity = getActivity();
//        if (activity == null) {
//            return 0;
//        }
//        return activity.findViewById(android.R.id.content).getHeight();
//    }
//
//    protected void setDummyData(ListView listView) {
//        listView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, getDummyData()));
//    }
//
//    protected void setDummyDataWithHeader(ListView listView, View headerView) {
//        listView.addHeaderView(headerView);
//        setDummyData(listView);
//    }
//
//    protected void setDialogsData(ListView listView, final TreeNodeEvent treeNodeEvent) {
//
////        ArrayList<ServiceDTO> data = getDialogsData(tabId, getActivity().getContentResolver());
//
//        getLoaderManager().initLoader(0, null, this);
//        listView.setBackgroundColor(
//                treeNodeEvent.getNodeType() == NodeType.YELLOW_PAGE_ENTRY ? Color.rgb(255, 255, 204) : Color.WHITE);
//        listView.setAdapter(
//                new CustomListAdapter(getActivity(), treeNodeEvent)
//        );
//        listView.setOnItemClickListener(
//                new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        NodeData item = (NodeData) parent.getAdapter().getItem(position);
//
//                        Log.i(".ebridge.BaseFragment", " item : " + item);
//                        registerSelectionEvent(item);
////                        Log.i(".ebridge.BaseFragment", "Clicked : " + ((ServiceDTO)adapter.getItem(position)).toString());
//
//
////                        switch (tabId) {
////
////                            case 0:
////                                MarketBrowserFragment marketBrowserFragment = new MarketBrowserFragment();
////                                Bundle args = new Bundle();
////                                args.putString("TITLE", item.getParams().get("title"));
////                                args.putString("AVATOR", item.getParams().get("avator"));
////                                marketBrowserFragment.setArguments(args);
////                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
////                                transaction.replace(R.id.root, marketBrowserFragment, TAG_FRAGMENT);
////                                transaction.addToBackStack(null);
////                                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
////                                transaction.commit();
////                                break;
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
////                        }
//
//                    }
//                });
//    }
//
//    public void registerSelectionEvent(NodeData item){
//    }
//
//    protected void setDialogsDataWithHeader(ListView listView, View headerView, TreeNodeEvent treeNodeEvent) {
//        listView.addHeaderView(headerView);
//        setDialogsData(listView, treeNodeEvent);
//    }
//
//    protected void setDummyData(GridView gridView) {
//        gridView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, getDummyData()));
//
//    }
//
//    protected void setDummyDataWithHeader(ObservableGridView gridView, View headerView) {
//        gridView.addHeaderView(headerView);
//        setDummyData(gridView);
//    }
//
//    protected void setDummyData(RecyclerView recyclerView) {
//        recyclerView.setAdapter(new SimpleRecyclerAdapter(getActivity(), getDummyData()));
//    }
//
//    protected void setDummyDataWithHeader(RecyclerView recyclerView, View headerView) {
//        recyclerView.setAdapter(new SimpleHeaderRecyclerAdapter(getActivity(), getDummyData(), headerView));
//    }
//
//    @Override
//    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        return null;
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//
//    }
//}
