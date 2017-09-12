package com.ebridgevas.android.ebridgeapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ebridgevas.android.ebridgeapp.R;
import com.ebridgevas.android.ebridgeapp.model.Message;
import com.ebridgevas.android.ebridgeapp.model.NodeData;
import com.ebridgevas.android.ebridgeapp.widget.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

public class NodeAdapter extends BaseAdapter {

    private Activity mActivity;
    private LayoutInflater mInflater;
//    private List<ServiceDTO> mDialogs;
//    private Integer mTabId;

    private ArrayList<NodeData> nodes;

    public NodeAdapter(Activity activity, ArrayList<NodeData> nodes /* List<ServiceDTO> dialogs, Integer tabId*/) {
        mActivity = activity;
//        mDialogs = dialogs;
//        mTabId = tabId;
        this.nodes = nodes;
    }

    @Override
    public int getCount() {
//        return mDialogs.size();
        return nodes.size();
    }

    @Override
    public Object getItem(int position) {
//        return mDialogs.get(position);
        return nodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        NodeData nodeData = nodes.get(position);
        Map<String, String> params = nodeData.getParams();


        if (mInflater == null) {
            mInflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_row, null);
        }

        Log.i("CustomListAdapter", "position : " + position);
        Log.i("CustomListAdapter", "nodes : " + nodes.size());
        Log.i("CustomListAdapter", "params : " + params.get("thumbnail"));
        Log.i("CustomListAdapter", "partner : " + params.get("node-text-top-left"));
        Log.i("CustomListAdapter", "last message : " + params.get("node-text-bottom-left"));

        CircleImageView imageThumbnail = (CircleImageView)convertView.findViewById(R.id.thumbnail);
        if (params.get("thumbnail") != null) {
            int resId = mActivity.getResources().getIdentifier(
                    params.get("thumbnail"), "drawable", mActivity.getPackageName());
            Picasso.with(mActivity.getApplicationContext()).load(resId).into(imageThumbnail);
        }

        ((TextView) convertView.findViewById(R.id.node_text_top_left)).setText(params.get("node-text-top-left"));
        ((TextView) convertView.findViewById(R.id.node_text_bottom_left)).setText(params.get("node-text-bottom-left"));
        Log.i("CustomListAdapter", "done");
//        TextView counter = (TextView) convertView.findViewById(R.id.counter);
//        TextView subCounter = (TextView) convertView.findViewById(R.id.subCounter);

//        convertView.setBackgroundColor(mTabId == 2 ? Color.rgb(255, 255, 204) : Color.WHITE);
//        convertView.setBackgroundColor(
//                nodes.getNodeType() == NodeType.YELLOW_PAGE_ENTRY ? Color.rgb(255, 255, 204) : Color.WHITE);

        return convertView;
    }
}
