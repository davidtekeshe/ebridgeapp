package com.ebridgevas.android.ebridgeapp;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.ebridgevas.android.ebridgeapp.util.TreeNode;

public class ExpandableListAdapterCurrent extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    private TreeNode<String> treeNode;

//    public ExpandableListAdapter(
//                Context context,
//                List<String> listDataHeader,
//                HashMap<String, List<String>> listChildData ) {
//
//        this._context = context;
//        this._listDataHeader = listDataHeader;
//        this._listDataChild = listChildData;
//    }

    public ExpandableListAdapterCurrent(Context context, TreeNode<String> treeNode) {
        this._context = context;
        this.treeNode = treeNode;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
//        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
        return ((TreeNode<String>)getGroup(groupPosition)).children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        // return this._listDataHeader.get(groupPosition);
        Log.i("ExpandableListAdapter", "getGroup( " + groupPosition + ")");
        TreeNode<String> group = treeNode.getChildAt(groupPosition);
        Log.i("ExpandableListAdapter", "group : " + group );
        return group;
    }

    @Override
    public int getGroupCount() {
//        return this._listDataHeader.size();
        int count = treeNode.children.size();
        Log.i("ExpandableListAdapter", "getGroupCount() : " + count);
        return count;
    }

    @Override
    public long getGroupId(int groupPosition) {
        Log.i("ExpandableListAdapter", "getGroupId() : " + groupPosition);
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        /*
        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater
                    = (LayoutInflater) this._context
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = infalInflater.inflate(R.layout.betting_league_group_list, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
        */

        Log.i("ExpandableListAdapter", "getGroupView( groupPosition :" + groupPosition + ")");
        String headerTitle = ((TreeNode<String>) getGroup(groupPosition)).data;
        Log.i("ExpandableListAdapter", "headerTitle  :" + headerTitle );
        if (convertView == null) {
            LayoutInflater infalInflater
                    = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = infalInflater.inflate(R.layout.betting_league_group_list, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.listHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
//        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
        return ((TreeNode<String>)getGroup(groupPosition)).getChildAt(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {

        /*
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater
                    = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = infalInflater.inflate(R.layout.market_list_item, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        return convertView;
        */

        Log.i("ExpandableListAdapter", "getChildView( groupPosition :" + groupPosition +
                        ", childPosition : " + childPosition  + ")");
        TreeNode<String> child = (TreeNode<String>) getChild(groupPosition, childPosition);
        Log.i("ExpandableListAdapter", "child  :" + child );

        final String childText = child.data;
//        if (convertView == null) {
            LayoutInflater infalInflater
                    = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (child.hasChildren()) {
                convertView = infalInflater.inflate(R.layout.group_list, null);
            } else {
                convertView = infalInflater.inflate(R.layout.list_item, null);
            }
//        }

        if (child.hasChildren()) {
            ExpandableListView listView = (ExpandableListView)convertView.findViewById(R.id.groupList);
            TreeNode<String> group = (TreeNode<String>)getGroup(groupPosition);
            Log.i("ExpandableListAdapter", "group  :" + group );
            ExpandableListAdapterCurrent listAdapter = new ExpandableListAdapterCurrent(_context, child);
            listView.setAdapter(listAdapter);

            Log.i("ExpandableListAdapter", "child  :" + child);

        } else {
            TextView textView = (TextView)convertView.findViewById(R.id.listItem);
            textView.setText(childText);
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
