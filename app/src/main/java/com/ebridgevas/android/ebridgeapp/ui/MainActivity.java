package com.ebridgevas.android.ebridgeapp.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.renderscript.ScriptGroup;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ebridgevas.android.ebridgeapp.ChatFragment;
import com.ebridgevas.android.ebridgeapp.DialogsActivity;
import com.ebridgevas.android.ebridgeapp.R;

import com.ebridgevas.android.ebridgeapp.nineoldandroids.ViewHelper;
import com.ebridgevas.android.ebridgeapp.nineoldandroids.ViewPropertyAnimator;
import com.ebridgevas.android.ebridgeapp.observablescrollview.CacheFragmentStatePagerAdapter;
import com.ebridgevas.android.ebridgeapp.observablescrollview.ObservableScrollViewCallbacks;
import com.ebridgevas.android.ebridgeapp.observablescrollview.ScrollState;
import com.ebridgevas.android.ebridgeapp.observablescrollview.ScrollUtils;
import com.ebridgevas.android.ebridgeapp.observablescrollview.Scrollable;
import com.ebridgevas.android.ebridgeapp.widget.SlidingTabLayout;

import com.ebridgevas.android.ebridgeapp.BaseActivity;
import com.ebridgevas.android.ebridgeapp.xmpp.LocalBinder;
import com.ebridgevas.android.ebridgeapp.xmpp.MessagingService;

import org.jivesoftware.smack.packet.Message;

/**
 * ViewPager + SlidingTab + ListView/ScrollView
 * This class shows how to handle scroll events for serveral different fragments.
 * <p/>
 * SlidingTabLayout and SlidingTabStrip are from google/iosched:
 * https://github.com/google/iosched.
 */
public class MainActivity
        extends BaseActivity
        implements ObservableScrollViewCallbacks {

    private final static String TAG = "MainActivity";
    private final static String TAG_FRAGMENT = "TAG_FRAMENT";

    private View mHeaderView;
    private View mToolbarView;
    private int mBaseTranslationY;
    private ViewPager mPager;

    private NavigationAdapter mPagerAdapter;

//    private Uri serviceEntryUri;

    private MessagingService messagingService;
    private boolean bounded;

    private final ServiceConnection connection =

            new ServiceConnection() {

                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    messagingService = ((LocalBinder<MessagingService>) service).getService();
                    messagingService.setServiceListener(onServiceListener);
                    Log.d(TAG, "onServiceConnected");
                    Toast.makeText(getApplicationContext(), "onServiceConnected", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    messagingService = null;
                    Log.d(TAG, "onServiceDisconnected");
                    Toast.makeText(getApplicationContext(), "onServiceDisconnected", Toast.LENGTH_LONG).show();
                }
            };

    private final MessagingService.OnServiceListener onServiceListener =  new MessagingService.OnServiceListener() {

        @Override
        public void onDataReceived(Message message) {
            String payload = "body: " + message.getBody() + ", from: " + message.getFrom() +
                    ", to : " + message.getTo() + ", thread : " + message.getThread() + ", stanzaId : " +
                    message.getStanzaId();
//            for (NodeListFragment fragment : mPagerAdapter.getFragments()) {
//                fragment.onMessageReceived(message);
//            }
            Log.i("XMPP", payload);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpagertab);

        doBindService();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayout layout = (LinearLayout)findViewById(R.id.header);

        // telecel
        layout.setBackgroundColor(Color.rgb(138, 7, 7));
        // whatsapp
        // 075E54
        // layout.setBackgroundColor(Color.rgb(7, 95, 84));

        // ContentResolver contentResolver = getContentResolver();
        // initDatabase(getContentResolver());
        mHeaderView = findViewById(R.id.header);
//        ViewCompat.setElevation(mHeaderView, getResources().getDimension(R.dimen.toolbar_elevation));
        mToolbarView = findViewById(R.id.toolbar);

//        NetworkServiceStub networkService = ((EBridgeApplication)getApplication()).getNetworkService();
//        ArrayList<NodeData> nodes = networkService.getChildren(null);

        mPagerAdapter = new NavigationAdapter( getSupportFragmentManager());
        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(1);

        SlidingTabLayout slidingTabLayout = (SlidingTabLayout)findViewById(R.id.sliding_tabs);
        slidingTabLayout.setCustomTabView(R.layout.tab_indicator, android.R.id.text1);
        slidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(mPager);

        // When the page is selected, other fragments' scrollY should be adjusted
        // according to the toolbar status(show/hidden)
        slidingTabLayout.setOnPageChangeListener(
                new ViewPager.OnPageChangeListener() {

                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageSelected(int position) {
                        propagateToolbarState(toolbarIsShown());
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });

        propagateToolbarState(toolbarIsShown());

//        NetworkServiceStub networkService = ((EBridgeApplication) getApplication()).getNetworkService();


        /*
        this.setTitle("Registration");
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
        */

//        publishButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MqttServiceDelegate.publish(
//                        MqttTestActivity.this,
//                        "the-topic-that-is-now-unused-in-service!",
//                        publishEditView.getBody().toString().getBytes());
//            }
//        });


        // Initialise Receivers
//        bindStatusReceiever();
//        bindMessageReceiver();

        // Start service if not started
//        MqttServiceDelegate.startService(this);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        doUnbindService();
//        MqttServiceDelegate.stopService(this);
//
//        unbindMessageReceiver();
//        unbindStatusReceiver();

        super.onDestroy();
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        if (dragging) {
            int toolbarHeight = mToolbarView.getHeight();
            float currentHeaderTranslationY = ViewHelper.getTranslationY(mHeaderView);
            if (firstScroll) {
                if (-toolbarHeight < currentHeaderTranslationY) {
                    mBaseTranslationY = scrollY;
                }
            }
            float headerTranslationY = ScrollUtils.getFloat(-(scrollY - mBaseTranslationY), -toolbarHeight, 0);
            ViewPropertyAnimator.animate(mHeaderView).cancel();
            ViewHelper.setTranslationY(mHeaderView, headerTranslationY);
        }
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        mBaseTranslationY = 0;

        Fragment fragment = getCurrentFragment();
        if (fragment == null) {
            return;
        }
        View view = fragment.getView();
        if (view == null) {
            return;
        }

        // ObservableXxxViews have same API
        // but currently they don't have any common interfaces.
        adjustToolbar(scrollState, view);
    }

    private void doBindService() {
        if (!bounded) {
            bindService(new Intent(this, MessagingService.class), connection, Context.BIND_AUTO_CREATE);
            bounded = true;
        } else {
            Log.i(TAG, "Service already bound. Skipping bindService");
            bounded = true;
        }
    }

    private void doUnbindService() {
        if (bounded) {
            unbindService(connection);
            bounded = false;
        } else {
            Log.i(TAG, "Service not bound. Skipping unbindService");
            bounded = false;
        }
    }

    public MessagingService getMessagingService() {
        return messagingService;
    }

    private void adjustToolbar(ScrollState scrollState, View view) {
        int toolbarHeight = mToolbarView.getHeight();
        final Scrollable scrollView = (Scrollable)view.findViewById(R.id.scroll);
        if (scrollView == null) {
            return;
        }
        int scrollY = scrollView.getCurrentScrollY();
        if (scrollState == ScrollState.DOWN) {
            showToolbar();
        } else if (scrollState == ScrollState.UP) {
            if (toolbarHeight <= scrollY) {
                hideToolbar();
            } else {
                showToolbar();
            }
        } else {
            // Even if onScrollChanged occurs without scrollY changing, toolbar should be adjusted
            if (toolbarIsShown() || toolbarIsHidden()) {
                // Toolbar is completely moved, so just keep its state
                // and propagate it to other pages
                propagateToolbarState(toolbarIsShown());
            } else {
                // Toolbar is moving but doesn't know which to move:
                // you can change this to hideToolbar()
                showToolbar();
            }
        }
    }

    private Fragment getCurrentFragment() {
        return mPagerAdapter.getItemAt(mPager.getCurrentItem());
    }

    private void propagateToolbarState(boolean isShown) {
        int toolbarHeight = mToolbarView.getHeight();

        // Set scrollY for the fragments that are not created yet
        mPagerAdapter.setScrollY(isShown ? 0 : toolbarHeight);

        // Set scrollY for the active fragments
        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
            // Skip current item
            if (i == mPager.getCurrentItem()) {
                continue;
            }

            // Skip destroyed or not created items
            Fragment f = mPagerAdapter.getItemAt(i);
            if (f == null) {
                continue;
            }

            View view = f.getView();
            if (view == null) {
                continue;
            }

            propagateToolbarState(isShown, view, toolbarHeight);
        }
    }

    private void propagateToolbarState(boolean isShown, View view, int toolbarHeight) {
        Scrollable scrollView = (Scrollable)view.findViewById(R.id.scroll);
        if (scrollView == null) {
            return;
        }

        if (isShown) {
            // Scroll up
            if (0 < scrollView.getCurrentScrollY()) {
                scrollView.scrollVerticallyTo(0);
            }
        } else {
            //Scroll down (to hide padding)
            if (scrollView.getCurrentScrollY() < toolbarHeight) {
                scrollView.scrollVerticallyTo(toolbarHeight);
            }
        }
    }

    private boolean toolbarIsShown() {
        return ViewHelper.getTranslationY(mHeaderView) == 0;
    }

    private boolean toolbarIsHidden() {
        return ViewHelper.getTranslationY(mHeaderView) == -mToolbarView.getHeight();
    }

    private void showToolbar() {
        float headerTranslationY = ViewHelper.getTranslationY(mHeaderView);
        if (headerTranslationY != 0 ) {
            ViewPropertyAnimator.animate(mHeaderView).cancel();
            ViewPropertyAnimator.animate(mHeaderView).translationY(0).setDuration(200).start();
        }
        propagateToolbarState(true);
    }

    private void hideToolbar() {
        float headerTranslationY = ViewHelper.getTranslationY(mHeaderView);
        int toolbarHeight = mToolbarView.getHeight();
        if (headerTranslationY != -toolbarHeight) {
            ViewPropertyAnimator.animate(mHeaderView).cancel();
            ViewPropertyAnimator.animate(mHeaderView).translationY(-toolbarHeight).setDuration(200).start();
        }
        propagateToolbarState(false);
    }
//
//    public static ArrayList<String> getDialogsData() {
//        ArrayList<String> items = new ArrayList<>();
//        for ( String item : Arrays.asList(new String[]{"Suggestion Box", "Orders", "Shop Manager"})){
//            items.add(item);
//        }
//
//        return items;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected()");
        Log.d(TAG, "selected : " + item.getItemId());
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_phone) {
            Log.d(TAG, "Phone selected");
            Toast.makeText(getApplicationContext(), "Calling", Toast.LENGTH_LONG).show();
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        switch ( id ) {
            case R.id.action_settings:
                return true;
            case R.id.action_chat:
                Toast.makeText(getApplicationContext(), "Chatting", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_phone:
                Toast.makeText(getApplicationContext(), "Calling", Toast.LENGTH_LONG).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // ------- Messaging -------

//    @Override
//    public void handleMessage(String topic, byte[] payload) {
//        String message = new String(payload);
//        Log.d(TAG, "handleMessage: topic= " + topic + ", message=" + message );
//
////        if(timestampView != null)timestampView.setText("When: "+getCurrentTimestamp());
////        if(topicView != null)topicView.setText("Topic: "+topic);
////        if(messageView != null)messageView.setText("Message: "+message);
//    }
//
//    @Override
//    public void handleStatus(MqttService.ConnectionStatus status, String reason) {
//        Log.d(TAG, "handleStatus: status="+status+", reason="+reason);
////        if(statusView != null)statusView.setText("Status: "+status.toString()+" ("+reason+")");
//    }

//    private void bindMessageReceiver(){
//        messageReceiver = new MessageReceiver();
//        messageReceiver.registerHandler(this);
//        registerReceiver(messageReceiver, new IntentFilter(MqttService.MQTT_MSG_RECEIVED_INTENT));
//    }
//
//    private void unbindMessageReceiver(){
//        if(messageReceiver != null) {
//            messageReceiver.unregisterHandler(this);
//            unregisterReceiver(messageReceiver);
//            messageReceiver = null;
//        }
//    }
//
//    private void bindStatusReceiever(){
//        statusReceiver = new StatusReceiver();
//        statusReceiver.registerHandler(this);
//        registerReceiver(statusReceiver, new IntentFilter(MqttService.MQTT_STATUS_INTENT));
//    }
//
//    private void unbindStatusReceiver(){
//        if(statusReceiver != null) {
//            statusReceiver.unregisterHandler(this);
//            unregisterReceiver(statusReceiver);
//            statusReceiver = null;
//        }
//    }

    // ------- Messaging Ends -------

    /**
     * TODO Class description
     * TODO Test cases
     *
     * This adapter provides two types of fragments as an example.
     * {@linkplain #createItem(int)} should be modified if you use this example for your app.
     */
    private static class NavigationAdapter extends CacheFragmentStatePagerAdapter {

        private int scrollY;

        private static final String[] TITLES = new String[]{"DIRECTORY", "SERVICES", "WALLET"};

        public NavigationAdapter( FragmentManager fm) {
            super(fm);
        }

        public void setScrollY(int scrollY) {
            this.scrollY = scrollY;
        }

        @Override
        protected Fragment createItem(int position) {
            final int tabId = position % 3;
            switch(tabId) {
                case 0:
                    return new BusinessFragment();
                case 1:
                    return new ServicesFragment();
                case 2:
                default:
                    return new DialogsFragment();

            }
            /*
            NodeData node = nodes.get(tabId);
            String nodeId = node.getId();
            Log.i(TAG, "parentId : " + nodeId);

            Bundle args = new Bundle();
            args.putString(NodeListFragment.ARG_NODE_LIST_PARENT_ID, nodeId);
            args.putString(NodeListFragment.ARG_PARENT_DESCRIPTION, node.getParams().get("node-text-top-left"));
            if ( 0 < scrollY) {
                args.putInt(NodeListFragment.ARG_INITIAL_POSITION, 1);
            }

            NodeListFragment fragment = new NodeListFragment();
            fragment.setArguments(args);
            fragments.add(fragment);
            return fragment;
            */

//            return mFragments[ tabId ];

//            NodeListFragment clone = null;
//            try {
//                clone = (NodeListFragment)(mFragment.clone());
//                Bundle args = new Bundle();
//                NodeData child = children.get(tabId);
//                Log.i(TAG, "parentid : " + child.getId());
//                args.putString(NodeListFragment.ARG_NODE_LIST_PARENT_ID, child.getId());
//                clone.setArguments(args);
//
//            } catch (CloneNotSupportedException e) {
//                e.printStackTrace();
//            }
//            return clone;
        }


        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position % 3];
        }
    }
}
