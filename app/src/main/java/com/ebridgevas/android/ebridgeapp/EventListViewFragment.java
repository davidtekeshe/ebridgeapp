//package com.ebridgevas.android.ebridgeapp;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ExpandableListView;
//
//import com.ebridgevas.android.ebridgeapp.model.NodeData;
//import com.ebridgevas.android.ebridgeapp.services.impl.NetworkServiceStub;
//import com.ebridgevas.android.ebridgeapp.widget.SecondLevelAdapter;
//
//import java.util.ArrayList;
//
//public class EventListViewFragment extends Fragment {
//
//    private final static String TAG = "MainActivity";
//    public static String ARG_INITIAL_POSITION = "ARG_INITIAL_POSITION";
//    private ExpandableListView mExpandableListView;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.fragment_events_listview, container, false);
//        mExpandableListView = (ExpandableListView) view.findViewById(R.id.marketSelectionList);
//
//        Bundle args = getArguments();
//        String parentId = args.getString(MarketBrowserFragment.ARG_PARENT_ID);
//
//        NetworkServiceStub networkService = ((EBridgeApplication)getActivity().getApplication()).getNetworkService();
//        Log.i("EventList", "#### parentId : " + parentId);
//        ArrayList<NodeData> countries = networkService.getChildren(parentId);
//        ArrayList<NodeData> nodes = new ArrayList<>();
//        for (NodeData country : countries) {
//            Log.i("EventList", "#### country id : " + country.getId() +
//                    ", country : " + country.getParams().get("node-text-top-left"));
//            ArrayList<NodeData> items = networkService.getChildren(country.getId());
//            for (NodeData item : items) {
//                Log.i("EventList", "#### league id : " + item.getId() +
//                        ", league : " + item.getParams().get("node-text-top-left"));
//            }
//            nodes.addAll(items);
//        }
//        mExpandableListView.setAdapter(new SecondLevelAdapter(getActivity(), nodes));
//
//        return view;
//    }
//}
