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
import com.ebridgevas.android.ebridgeapp.model.EBridgeAppModel;
import com.ebridgevas.android.ebridgeapp.model.NodeData;
import com.ebridgevas.android.ebridgeapp.model.Service;
import com.ebridgevas.android.ebridgeapp.widget.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

public class ServiceAdapter extends BaseAdapter {

    private Activity mActivity;
    private LayoutInflater mInflater;

    private ArrayList<Service> services;

    public ServiceAdapter(Activity activity) {
        mActivity = activity;
        services = EBridgeAppModel.getServices();
        Log.i("TEST", "services : " + services.size());
    }

    @Override
    public int getCount() {
        return services.size();
    }

    @Override
    public Object getItem(int position) {
        return services.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (mInflater == null) {
            mInflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_row, null);
        }

        Service service = services.get(position);
        Log.i("TEST", "service : " + service);
        int resId = mActivity
                        .getResources()
                        .getIdentifier(service.getServiceLogo(), "drawable", mActivity.getPackageName());
        CircleImageView imageThumbnail = (CircleImageView)convertView.findViewById(R.id.thumbnail);
        Picasso.with(mActivity.getApplicationContext()).load(resId).into(imageThumbnail);

        ((TextView) convertView.findViewById(R.id.node_text_top_left))
                        .setText(service.getServiceName());

        ((TextView) convertView.findViewById(R.id.node_text_bottom_left))
                        .setText(service.getNarration());

        return convertView;
    }
}
