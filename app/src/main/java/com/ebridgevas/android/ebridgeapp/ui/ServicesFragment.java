package com.ebridgevas.android.ebridgeapp.ui;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ebridgevas.android.ebridgeapp.ChatFragment;
import com.ebridgevas.android.ebridgeapp.R;
//import com.ebridgevas.android.ebridgeapp.adapter.NodeListItemClickHandler;
import com.ebridgevas.android.ebridgeapp.adapter.ServiceAdapter;
import com.ebridgevas.android.ebridgeapp.model.NodeData;
import com.ebridgevas.android.ebridgeapp.model.Service;
import com.ebridgevas.android.ebridgeapp.observablescrollview.ObservableListView;
import com.ebridgevas.android.ebridgeapp.presenter.NodeListPresenter;
import com.ebridgevas.android.ebridgeapp.util.NotificationCenter;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.packet.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class ServicesFragment extends BaseFragment
        implements NodeListPresenter.View, LoaderManager.LoaderCallbacks<Cursor> {

    public static final String ARG_PROVIDER_CODE = "ARG_PROVIDER_CODE";
    public static final String ARG_SERVICE_CODE = "ARG_SERVICE_CODE";
//    private ArrayList<Service> services;

    public static final String ARG_INITIAL_POSITION = "ARG_INITIAL_POSITION"; // extends ViewPagerTabListViewFragment { // extends BaseActivity implements NodeListPresenter.View { // extends ViewPagerTabListViewFragment {
    private ListView listView;

    @BindView(R.id.node_list)
    RecyclerView mRecyclerView;

//    @Bind(R.id.node_list_progressBar)
//    ProgressBar mProgressBar;

//    RVRendererAdapter<NodeData> adapter;

//    NodeListPresenterImpl mPresenter;

//    public final static String ARG_NODE_LIST_PARENT_ID = "NODE_LIST_PARENT_ID";
//    public final static String ARG_PARENT_DESCRIPTION = "PARENT_DESCRIPTION";

    private static final String TAG = "NodeListFragment";

    private ServiceAdapter nodeAdapter;

//    public void onMessageReceived(Message message) {

//        Map<String, String> params = new HashMap<>();
//        params.put("node-text-top-left", message.getFrom());
//        params.put("node-text-bottom-left", message.getBody());
//        params.put("thumbnail", "feli");
//        final NodeData data = new NodeData("600-10000-30005", "600-10000-10002", params, 2);
//        nodes.add(data);
//        Log.i(TAG, "node data : " + data);
////        getActivity().runOnUiThread(new Runnable() {
////            @Override
////            public void run() {
////                Toast.makeText(getActivity(), data.toString(), Toast.LENGTH_LONG);
////                nodeAdapter.notifyDataSetChanged();
////            }
////        });
//
//        new Handler(Looper.getMainLooper()).post(new Runnable() {
//
//            @Override
//            public void run() {
////                Toast.makeText(getActivity(), data.toString(), Toast.LENGTH_LONG).show();
//                nodeAdapter.notifyDataSetChanged();
//            }
//        });
//    }

    private class MessageReceiver implements NotificationCenter.NotificationCenterDelegate {

        @Override
        public void didReceivedNotification(int id, Object... args) {
            Chat chat = (Chat) args[0];
            Message message = (Message) args[1];
//            messageProcessor.process(chat, message);

            Map<String, String> params = new HashMap<>();
            params.put("node-text-top-left", message.getFrom());
            params.put("node-text-bottom-left", message.getBody());
            params.put("thumbnail", "feli");
            final NodeData data = new NodeData("600-10000-30005", "600-10000-10002", params, 2);
//            nodes.add(data);
            Log.i(TAG, "node data : " + data);


            new Handler(Looper.getMainLooper()).post(new Runnable() {

                @Override
                public void run() {
                    nodeAdapter.notifyDataSetChanged();
                }
            });
        }
    }


    /**
     * TODO Method description
     * <p/>
     * //     * @param inflater3
     *
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listview, container, false);
        Activity parentActivity = getActivity();
        listView = (ObservableListView) view.findViewById(R.id.scroll);

//        String parentId = null;
//        String parentDescription = null;
//        Bundle args = getArguments();
//        if (args != null && args.containsKey(ARG_NODE_LIST_PARENT_ID)) {
//            parentId = args.getString(ARG_NODE_LIST_PARENT_ID);
//        }
//
//        if (args != null && args.containsKey(ARG_PARENT_DESCRIPTION)) {
//            parentDescription = args.getString(ARG_PARENT_DESCRIPTION);
//        }



//        Log.i(TAG, "parentId : " + parentId);

//        NetworkServiceStub networkService = ((EBridgeApplication) getActivity().getApplication()).getNetworkService();
//        String processorName = networkService.getParent(parentId).getParams().get("processor");
//        Log.i("NodeClick", "processorName: " + processorName);

//        try {
//            messageProcessor = (MessageProcessor)Class.forName(processorName).newInstance();
//        } catch (java.lang.InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

//        nodes = networkService.getChildren(parentId);

        // populate list
        View headerView = inflater.inflate(R.layout.padding, listView, false);
        listView.addHeaderView(headerView);
//        setDialogsData(listView, nodes);


        getLoaderManager().initLoader(0, null, this);
//        listView.setBackgroundColor(
//                treeNodeEvent.getNodeType() == NodeType.YELLOW_PAGE_ENTRY ? Color.rgb(255, 255, 204) : Color.WHITE);
        nodeAdapter = new ServiceAdapter(getActivity());
        listView.setAdapter(nodeAdapter);

        listView.setDivider(null);
        listView.setDividerHeight(0);

//        new NodeListItemClickHandler(getActivity(), "parentDescription").handle(listView);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.i("NodeClick", "AdapterView.OnItemClickListener()");
                        Service service = (Service) parent.getAdapter().getItem(position);

//                        Log.i("NodeClick", "description : " + description);
//                        Log.i("NodeClick", " item : " + item);

//                        String processorName = "com.ebridgevas.android.ebridgeapp."+ item.getParams().get("processor");
//                        Log.i("NodeClick", "processorName: " + processorName);

                        Fragment fragment = new ProductBrowserFragment();
                        Bundle args = new Bundle();
                        args.putString("TITLE", service.getServiceName());
                        args.putString("AVATOR", service.getServiceLogo());
                        args.putString(ServicesFragment.ARG_PROVIDER_CODE, service.getProviderEanCode());
                        args.putString(ServicesFragment.ARG_SERVICE_CODE, service.getEanCode());
//                            args.putString(ARG_PARENT_ID, item.getId());
//                            args.putString(ChatFragment.ARG_PEER_ID, item.getParams().get("id"));
                        fragment.setArguments(args);
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.root, fragment, "NodeClick");
                        transaction.addToBackStack(null);
                        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        transaction.commit();

                        /*
                        switch (description) {

                            case "SERVICES":
                            default:
                                MarketBrowserFragment marketBrowserFragment = new MarketBrowserFragment();
                                Bundle args = new Bundle();
                                args.putString("TITLE", item.getParams().get("node-text-top-left"));
                                args.putString("AVATOR", item.getParams().get("thumbnail"));
                                marketBrowserFragment.setArguments(args);
                                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.root, marketBrowserFragment, "NodeClick");
                                transaction.addToBackStack(null);
                                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                transaction.commit();
                            break;
                        }
                        */
                        /*
                        registerSelectionEvent(item);


                        Log.i(".ebridge.BaseFragment", "Clicked : " + ((ServiceDTO)adapter.getItem(position)).toString());


                        switch (tabId) {

                            case 0:
                                MarketBrowserFragment marketBrowserFragment = new MarketBrowserFragment();
                                Bundle args = new Bundle();
                                args.putString("TITLE", item.getParams().get("title"));
                                args.putString("AVATOR", item.getParams().get("avator"));
                                marketBrowserFragment.setArguments(args);
                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.root, marketBrowserFragment, TAG_FRAGMENT);
                                transaction.addToBackStack(null);
                                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                transaction.commit();
                                break;
                            case 1:
                                Chats chats = new Chats();
                                args = new Bundle();
                                args.putString("TITLE", item.getServiceTitle());
                                args.putString("AVATOR", item.getAvator());
                                chats.setArguments(args);
                                transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //                        logFrag.setConnectionID(connectionID);
                                transaction.replace(R.id.root, chats, TAG_FRAGMENT);
        //                        SlidingTabLayout tabs = (SlidingTabLayout)getActivity().findViewById(R.id.sliding_tabs);
        //                        ViewGroup.LayoutParams tabParams = tabs.getLayoutParams();
        //                        tabParams.height = 0;

        //                        FrameLayout container = (FrameLayout)getActivity().findViewById(R.id.container);
        //                        container.setPadding(container.getPaddingLeft(), 0, container.getPaddingRight(), container.getPaddingBottom());

        //                        LinearLayout header = (LinearLayout)getActivity().findViewById(R.id.header);
        //                        ViewGroup.LayoutParams params = header.getLayoutParams();
        //                        params.height = ActionBar.LayoutParams.WRAP_CONTENT;

                                transaction.addToBackStack(null);
                                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                transaction.commit();
                            break;

                        }
                        */

                    }
                });
        // populate list ends

//        if (parentActivity instanceof ObservableScrollViewCallbacks) {
//            // Scroll to the specified position after layout
//            if (args != null && args.containsKey(ARG_INITIAL_POSITION)) {
//                final int initialPosition = args.getInt(ARG_INITIAL_POSITION, 0);
//                ScrollUtils.addOnGlobalLayoutListener(
//                        listView, new Runnable() {
//                            @Override
//                            public void run() {
//                                // scrollTo() doesn't work, should use setSelection()
//                                listView.setSelection(initialPosition);
//                            }
//                        });
//            }
//
//            // TouchInterceptionViewGroup should be a parent view other than ViewPager.
//            // This is a workaround for the issue #117:
//            // https://github.com/ksoichiro/Android-ObservableScrollView/issues/117
////            listView.setTouchInterceptionViewGroup((ViewGroup) parentActivity.findViewById(R.id.root));
//
////            listView.setScrollViewCallbacks((ObservableScrollViewCallbacks) parentActivity);
//        }


        // Register with NotificationCenter
     //   NotificationCenter.getInstance().addObserver(new MessageReceiver(), NotificationCenter.MESSAGE_RECEIVED);
        return view;

        /*

        mPresenter = new NodeListPresenterImpl(nodes, getContext());


        NodeRenderer.OnNodeClicked  nodeClickListener = new NodeRenderer.OnNodeClicked() {

            @Override
            public void onPictureClicked(NodeData nodeData) {
                Log.i(TAG, "onPictureClicked : " + nodeData);
                mPresenter.onNodePictureClicked(nodeData);
            }

            @Override
            public void onBackgroundClicked(NodeData nodeData) {
                Log.i(TAG, "onBackgroundClicked : " + nodeData);
                mPresenter.onNodeListRowClicked(nodeData);
            }
        };

        adapter = new RVRendererAdapter<>(
                new NodeRendererBuilder(getContext(), nodeClickListener),
                new ListAdapteeCollection<>(new ArrayList<NodeData>())
        );

        View view = inflater.inflate(R.layout.fragment_node_list, container, false);
        ButterKnife.bind(this, view);

        initializeRecycleView();
        mPresenter.setView(this);
        Log.i(TAG, "parentId : " + parentId);
        mPresenter.initialize( parentId );
        */
//        return view;
    }

    /*
    private void setDialogsData(ListView listView, ArrayList<NodeData> nodes) {

//        ArrayList<ServiceDTO> data = getDialogsData(tabId, getActivity().getContentResolver());

        getLoaderManager().initLoader(0, null, this);
        listView.setBackgroundColor(
                treeNodeEvent.getNodeType() == NodeType.YELLOW_PAGE_ENTRY ? Color.rgb(255, 255, 204) : Color.WHITE);
        listView.setAdapter(
                new CustomListAdapter(getActivity(), treeNodeEvent)
        );
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        NodeData item = (NodeData) parent.getAdapter().getItem(position);

                        Log.i(".ebridge.BaseFragment", " item : " + item);
                        registerSelectionEvent(item);
//                        Log.i(".ebridge.BaseFragment", "Clicked : " + ((ServiceDTO)adapter.getItem(position)).toString());


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
//                        }

                    }
                });
    }
    */
//    private void initializeRecycleView() {
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mRecyclerView.setAdapter(adapter);
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_node_list;
    }

    /**
     * TODO Method description
     */
    @Override
    public void showNodeList(List<NodeData> nodes) {
//        Log.i(TAG, "showNodeList : " + nodes);
//        for (NodeData nodeData : nodes)
//            adapter.add(nodeData);
//
//        adapter.notifyDataSetChanged();
    }

    /**
     * TODO Method description
     */
    @Override
    public void showNodelListError(Exception e) {
//        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    /**
     * TODO Method description
     */
    @Override
    public void showNoInternetMessage() {
        // TODO Toast.makeText(this, R.string.no_internet, Toast.LENGTH_SHORT).show();
    }

    /**
     * TODO Method description
     */
    @Override
    public void showLoading() {
        //mProgressBar.setVisibility(View.VISIBLE);
    }

    /**
     * TODO Method description
     */
    @Override
    public void hideLoading() {
        //mProgressBar.setVisibility(View.GONE);
    }

    /**
     * TODO Method description
     */
    @Override
    public void showNodeDataPictureMessage(NodeData nodeData) {

    }

    /**
     * TODO Method description
     *
     * @param nodeData
     */
    @Override
    public void showNodeDataClickedMessage(NodeData nodeData) {
        // TODO Goto child notes display
        Log.i(TAG, "showNodeDataClickedMessage");
    }

    @Override
    protected Object clone()
            throws CloneNotSupportedException {
        return super.clone();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.i(TAG, "onCreateLoader ( " + id + ")");
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.i(TAG, "onLoadFinished ()");

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.i(TAG, "onLoaderReset ()");

    }
}
