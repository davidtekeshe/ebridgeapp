//package com.ebridgevas.android.ebridgeapp.adapter;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.Color;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.ebridgevas.android.ebridgeapp.R;
//import com.ebridgevas.android.ebridgeapp.model.NodeData;
//import com.ebridgevas.android.ebridgeapp.model.NodeType;
//import com.ebridgevas.android.ebridgeapp.model.TreeNodeEvent;
//import com.ebridgevas.android.ebridgeapp.widget.CircleImageView;
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//import java.util.Map;
//
//public class CustomListAdapter extends BaseAdapter {
//
//    private Activity mActivity;
//    private LayoutInflater mInflater;
////    private List<ServiceDTO> mDialogs;
////    private Integer mTabId;
//
//    private TreeNodeEvent mTreeNodeEvent;
//
//    public CustomListAdapter(Activity activity, ArrayList<NodeData> nodes/* List<ServiceDTO> dialogs, Integer tabId*/) {
//        mActivity = activity;
////        mDialogs = dialogs;
////        mTabId = tabId;
//        mTreeNodeEvent = nodes;
//    }
//
//    @Override
//    public int getCount() {
////        return mDialogs.size();
//        return mTreeNodeEvent.getChildren().size();
//    }
//
//    @Override
//    public Object getItem(int position) {
////        return mDialogs.get(position);
//        return mTreeNodeEvent.getChildren().get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        /*
//         * NodeRender ==================================================================================================
//         */
//        if (mInflater == null) {
//            mInflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//
//        if (convertView == null) {
//            convertView = mInflater.inflate(R.layout.list_row, null);
//        }
//
//        CircleImageView imageThumbnail = (CircleImageView)convertView.findViewById(R.id.thumbnail);
//        TextView title = (TextView) convertView.findViewById(R.id.title);
//        TextView subTitle = (TextView) convertView.findViewById(R.id.subTitle);
//        TextView counter = (TextView) convertView.findViewById(R.id.counter);
//        TextView subCounter = (TextView) convertView.findViewById(R.id.subCounter);
//
//        // getting dialog data for the row
////        ServiceDTO s = mDialogs.get(position);
//        NodeData nodeData = mTreeNodeEvent.getChildren().get(position);
//        Map<String, String> params = nodeData.getParams();
//        // thumbnail image
////        int resId = mActivity.getResources().getIdentifier(s.getAvator(), "drawable", mActivity.getPackageName());
//        Log.i("CustomListAdapter", "params : " + params.get("avator"));
//
//        if (params.get("node-thumbnal") != null) {
//            int resId = mActivity.getResources().getIdentifier(
//                    params.get("node-thumbnal"), "drawable", mActivity.getPackageName());
//            Picasso.with(mActivity.getApplicationContext()).load(resId).into(imageThumbnail);
//        }
//        // title
////        title.setText(s.getServiceTitle());
//        title.setText(params.get("node-text-top-left"));
//
//        // sub Title
////        subTitle.setText(s.getNarration());
//        subTitle.setText(params.get("node-text-bottom-left") != null ? params.get("node-text-bottom-left") : "");
//
//        // counter
//        counter.setText(params.get("counter") != null ? params.get("counter") : "");
//
//        // sub Counter
//        subCounter.setText(params.get("sub-counter") != null ? params.get("sub-counter") : "");
//
////        convertView.setBackgroundColor(mTabId == 2 ? Color.rgb(255, 255, 204) : Color.WHITE);
//        convertView.setBackgroundColor(
//                mTreeNodeEvent.getNodeType() == NodeType.YELLOW_PAGE_ENTRY ? Color.rgb(255, 255, 204) : Color.WHITE);
//
//        return convertView;
//    }
//}
