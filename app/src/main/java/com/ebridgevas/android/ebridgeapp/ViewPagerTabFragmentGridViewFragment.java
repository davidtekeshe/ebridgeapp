//
//package com.ebridgevas.android.ebridgeapp;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.ebridgevas.android.ebridgeapp.model.NodeData;
//import com.ebridgevas.android.ebridgeapp.observablescrollview.ObservableGridView;
//import com.ebridgevas.android.ebridgeapp.observablescrollview.ObservableScrollViewCallbacks;
//
///**
// * Fragment for ViewPagerTabFragmentActivity.
// * ScrollView callbacks are handled by its parent fragment, not its parent activity.
// */
//public class ViewPagerTabFragmentGridViewFragment extends BaseFragment {
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_gridview, container, false);
//
//        final ObservableGridView gridView = (ObservableGridView) view.findViewById(R.id.scroll);
//        setDummyData(gridView);
//
//        Fragment parentFragment = getParentFragment();
//        ViewGroup viewGroup = (ViewGroup) parentFragment.getView();
//        if (viewGroup != null) {
//            gridView.setTouchInterceptionViewGroup((ViewGroup) viewGroup.findViewById(R.id.container));
//            if (parentFragment instanceof ObservableScrollViewCallbacks) {
//                gridView.setScrollViewCallbacks((ObservableScrollViewCallbacks) parentFragment);
//            }
//        }
//        return view;
//    }
//
//    @Override
//    public void registerSelectionEvent(NodeData item) {
//
//    }
//}
