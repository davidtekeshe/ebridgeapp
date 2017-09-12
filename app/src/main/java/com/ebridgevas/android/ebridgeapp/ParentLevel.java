//package com.ebridgevas.android.ebridgeapp;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseExpandableListAdapter;
//import android.widget.TextView;
//
//import com.ebridgevas.android.ebridgeapp.util.TreeNode;
//import com.ebridgevas.android.ebridgeapp.widget.SecondLevelAdapter;
//import com.ebridgevas.android.ebridgeapp.widget.SecondLevelExpandableListView;
//
//public class ParentLevel extends BaseExpandableListAdapter {
//
//    private Context mContext;
//
//    public static final int SECOND_LEVEL_COUNT = 4;
//    public static final int FIRST_LEVEL_COUNT = 6;
//
//    private TreeNode<String> mTreeNode;
//
//    public ParentLevel(Context context, TreeNode<String> treeNode) {
//        mContext = context;
//        this.mTreeNode = treeNode;
//    }
//
//    @Override
//    public Object getChild(int groupPosition, int childPosition) {
//        return childPosition;
//    }
//
//    @Override
//    public long getChildId(int groupPosition, int childPosition) {
//        return childPosition;
//    }
//
//    @Override
//    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
//            ViewGroup parent) {
//
//        SecondLevelExpandableListView listView = new SecondLevelExpandableListView(mContext);
//        listView.setAdapter(new SecondLevelAdapter(mContext, mTreeNode.getChildAt(groupPosition)));
//        listView.setGroupIndicator(null);
//
//        return listView;
//    }
//
//    @Override
//    public int getChildrenCount(int groupPosition) {
//        return 1; // i.e. one listview
//    }
//
//    @Override
//    public int getGroupCount() {
//        return mTreeNode.count();
//    }
//
//    @Override
//    public Object getGroup(int groupPosition) {
//        return groupPosition;
//    }
//
//    @Override
//    public long getGroupId(int groupPosition) {
//        return groupPosition;
//    }
//
//    @Override
//    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//
//        if (convertView == null ) {
//            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.fragment_market_selection_first_row, null);
//        }
//
//        TextView textView = (TextView)convertView.findViewById(R.id.eventsListEventRowText);
//        textView.setText( mTreeNode.getChildAt(groupPosition).data);
//
//        return convertView;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return true;
//    }
//
//    @Override
//    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        return true;
//    }
//}
