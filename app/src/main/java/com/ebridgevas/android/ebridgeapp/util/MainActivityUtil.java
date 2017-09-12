package com.ebridgevas.android.ebridgeapp.util;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.ebridgevas.android.ebridgeapp.BaseActivity;
//import com.ebridgevas.android.ebridgeapp.ChatsFragment;
import com.ebridgevas.android.ebridgeapp.R;
//import com.ebridgevas.android.ebridgeapp.ServicesFragment;
//import com.ebridgevas.android.ebridgeapp.activity.NodeListFragment;
import com.ebridgevas.android.ebridgeapp.nineoldandroids.ViewHelper;
import com.ebridgevas.android.ebridgeapp.nineoldandroids.ViewPropertyAnimator;
import com.ebridgevas.android.ebridgeapp.observablescrollview.CacheFragmentStatePagerAdapter;
import com.ebridgevas.android.ebridgeapp.observablescrollview.ObservableScrollViewCallbacks;
import com.ebridgevas.android.ebridgeapp.observablescrollview.ScrollState;
import com.ebridgevas.android.ebridgeapp.observablescrollview.ScrollUtils;
import com.ebridgevas.android.ebridgeapp.observablescrollview.Scrollable;
import com.ebridgevas.android.ebridgeapp.services.ApiService;
import com.ebridgevas.android.ebridgeapp.services.impl.NetworkServiceStub;
import com.ebridgevas.android.ebridgeapp.widget.SlidingTabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivityUtil {

    /*
    private void initDatabase(ContentResolver contentResolver){

        contentResolver.delete(ServicesContentProvider.CONTENT_URI, null, null);

        saveState("service_list", "600961112345", "Suggestion Box", "For Chitatai. For others select 'Companies'", "suggestionbox", "");
        saveState("service_list","600961112346", "Bus Tickets", "Make bookings and buy tickets", "bus", "");
        saveState(
                "service_list", "600961112347", "Airtime / Data Bundles", "Buy or transfer airtime, data bundles",
                "telecel", "");
        saveState("service_list","600961112348", "Buying", "What do you want to buy?", "buyorsell", "");
        saveState("service_list","600961112349", "Selling", "What are you selling?", "buyorsell", "");
        saveState("service_list","600961112350", "Shop Manager", "Manage your remote shops", "shop8", "");
        saveState("service_list","600961112351", "Soccer Betting", "Make money through betting", "money", "");

        saveState("service_list","600961112380", "Balance Enquiry", "Account balance and expiration", "enquiry", "");
        saveState("service_list","600961112381", "Recharge", "Using a recharge voucher", "recharge", "");
        saveState("service_list","600961112382", "Bundle", "Convert credit to a bundle", "bundle", "");
        saveState("service_list","600961112383", "Buy Credit", "Using Telecash or Ecocash ", "money", "");

        saveDialog("chat_list", "" + System.currentTimeMillis() + 1, "David", "Hi there", "david", "ss", "10:38 AM");
        saveDialog("chat_list", "" + System.currentTimeMillis() + 2, "Feli", "Ndeipi", "feli", "s", "YESTERDAY");
        saveDialog(
                "chat_list", "" + System.currentTimeMillis() + 3, "Mercy", "Zviri sei samanyika", "mercy", "s",
                "30/04/2016");


        saveDialog("service_provider_list", "600961112352", "eBridge", "Systems Integrators", "ebridge", "active", "");
        saveDialog("service_provider_list", "600961112353", "Chitaitai", "Chemical Engineers", "chitaitai", "s", "");
        saveDialog("service_provider_list", "600961112354", "Jo's Chickens", "Leading Chicken Abboitors", "joschickens", "active", "");
        saveDialog("service_provider_list", "600961112355", "MmCellink", "Mobile phone shop", "mmcellink", "active", "");
//        saveDialog("service_provider_list", "600961112356", "Greyhound / CitiLiner", "Bus Booking & Ticket Sales", "greyhound", "active", "");
//        saveDialog("service_provider_list", "600961112357", "Pioneer", "Bus Booking & Ticket Sales", "pioneer_logo", "active", "");
        saveDialog("service_provider_list", "600961112358", "Telecel", "Airtime and Data Bundles", "telecel", "active", "");
//        saveDialog("service_provider_list", "600961112359", "Soccer Shop", "Make money through betting", "soccer_shop", "active", "");
        saveDialog("service_provider_list", "600961112360", "Africom", "Airtime and Data Bundles", "africom", "active", "");
    }

    private void saveDialog(String dialogType, String dialogId, String dialogTitle, String narration, String avator, String status,
            String dialogDate){
//        String category = (String)mCategory.getSelectedItem();
//        String summary = mTitleText.getBody().toString();
//        String description = mBodyText.getBody().toString();

        // only save if either summary or description is available
//        if (description.length() == 0 && summary.length() == 0) {
//            return;
//        }

        ContentValues values = new ContentValues();
        values.put(ServicesContract.ServiceEntry.COLUMN_NAME_DIALOG_TYPE, dialogType);
        values.put(ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_ID, dialogId);
        values.put(ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_TITLE, dialogTitle);
        values.put(ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_NARRATION, narration);
        values.put(ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_AVATOR, avator);
        values.put(ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_STATUS, status);
        values.put(ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_ACTION, dialogDate);

//        if (serviceEntryUri == null) {
        // new service
        Log.i("Activity", "before db insert");
        serviceEntryUri = getContentResolver().insert(ServicesContentProvider.CONTENT_URI, values);
        Log.i("Activity", "after db insert : " + serviceEntryUri);

//        } else {
        // update comment
//            getContentResolver().update(serviceEntryUri, values, null, null);
//        }
    }

    private void saveState(String dialogType, String serviceId, String serviceTitle, String narration, String avator, String action){
//        String category = (String)mCategory.getSelectedItem();
//        String summary = mTitleText.getBody().toString();
//        String description = mBodyText.getBody().toString();

        // only save if either summary or description is available
//        if (description.length() == 0 && summary.length() == 0) {
//            return;
//        }

        ContentValues values = new ContentValues();
        values.put(ServicesContract.ServiceEntry.COLUMN_NAME_DIALOG_TYPE, dialogType);
        values.put(ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_ID, serviceId);
        values.put(ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_TITLE, serviceTitle);
        values.put(ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_NARRATION, narration);
        values.put(ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_AVATOR, avator);
        values.put(ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_ACTION, action);

//        if (serviceEntryUri == null) {
        // new service
        Log.i("Activity", "before db insert");
        serviceEntryUri = getContentResolver().insert(ServicesContentProvider.CONTENT_URI, values);
        Log.i("Activity", "after db insert : " + serviceEntryUri);

//        } else {
        // update comment
//            getContentResolver().update(serviceEntryUri, values, null, null);
//        }
    }

case 0: {
                    f = new ViewPagerTabScrollViewFragment();
                    if (0 <= mScrollY) {
                        Bundle args = new Bundle();
                        args.putInt(ViewPagerTabScrollViewFragment.ARG_SCROLL_Y, mScrollY);
                        f.setArguments(args);
                    }
                    break;
                }
                case 1: {
                    f = new ViewPagerTabListViewFragment();
                    if (0 < mScrollY) {
                        Bundle args = new Bundle();
                        args.putInt(ViewPagerTabListViewFragment.ARG_INITIAL_POSITION, 1);
                        f.setArguments(args);
                    }
                    break;
                }
                case 2: {
                    f = new ViewPagerTabRecyclerViewFragment();
                    if (0 < mScrollY) {
                        Bundle args = new Bundle();
                        args.putInt(ViewPagerTabRecyclerViewFragment.ARG_INITIAL_POSITION, 1);
                        f.setArguments(args);
                    }
                    break;
                }
                case 3:
                default: {
                    f = new ViewPagerTabGridViewFragment();
                    if (0 < mScrollY) {
                        Bundle args = new Bundle();
                        args.putInt(ViewPagerTabGridViewFragment.ARG_INITIAL_POSITION, 1);
                        f.setArguments(args);
                    }
                    break;
                }
    */

    /*
    **
     * This adapter provides two types of fragments as an example.
     * {@linkplain #createItem(int)} should be modified if you use this example for your app.
     *
    private static class NavigationAdapter extends CacheFragmentStatePagerAdapter {

        Map<Integer, Fragment> mFragments;
        private static final String[] TITLES = new String[]{"SERVICES", "CHATS", "YELLOW PAGE"};
        private final ApiService mNetworkService;
        private int mScrollY;
        public NavigationAdapter(FragmentManager fm, ApiService networkService, Map<Integer, Fragment> fragments) {
            super(fm);
            mNetworkService = networkService;
            mFragments = fragments;
        }

        public void setScrollY(int scrollY) {
            mScrollY = scrollY;
        }

        @Override
        protected Fragment createItem(int position) {
            final int pattern = position % 3;
            return mFragments.get(pattern);
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }
    }
     */

    /*
     * MainActivity.java
     *
     *
     *
     * package com.ebridgevas.android.ebridgeapp;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.ebridgevas.android.ebridgeapp.activity.NodeListFragment;
import com.ebridgevas.android.ebridgeapp.model.ChatsNodeEvent;
import com.ebridgevas.android.ebridgeapp.model.NodeData;
import com.ebridgevas.android.ebridgeapp.model.NodeType;
import com.ebridgevas.android.ebridgeapp.model.ServicesNodeEvent;
import com.ebridgevas.android.ebridgeapp.model.TreeNodeEvent;
import com.ebridgevas.android.ebridgeapp.nineoldandroids.ViewHelper;
import com.ebridgevas.android.ebridgeapp.nineoldandroids.ViewPropertyAnimator;
import com.ebridgevas.android.ebridgeapp.observablescrollview.CacheFragmentStatePagerAdapter;
import com.ebridgevas.android.ebridgeapp.observablescrollview.ObservableScrollViewCallbacks;
import com.ebridgevas.android.ebridgeapp.observablescrollview.ScrollState;
import com.ebridgevas.android.ebridgeapp.observablescrollview.ScrollUtils;
import com.ebridgevas.android.ebridgeapp.observablescrollview.Scrollable;
import com.ebridgevas.android.ebridgeapp.services.ApiService;
import com.ebridgevas.android.ebridgeapp.services.impl.NetworkServiceStub;
import com.ebridgevas.android.ebridgeapp.widget.SlidingTabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

*
 * ViewPager + SlidingTab + ListView/ScrollView
 * This class shows how to handle scroll events for serveral different fragments.
 * <p/>
 * SlidingTabLayout and SlidingTabStrip are from google/iosched:
 * https://github.com/google/iosched.
 *
    public class MainActivity extends BaseActivity implements ObservableScrollViewCallbacks {

        private View mHeaderView;
        private View mToolbarView;
        private int mBaseTranslationY;
        private ViewPager mPager;

        //    @Inject
        ApiService mNetworkService;

        // @Inject
        private NavigationAdapter mPagerAdapter;

        private Uri serviceEntryUri;

        private final static String TAG_FRAGMENT = "TAG_FRAMENT";

        private final static String TAG = "MainActivity";

        // @Inject
        private ServicesFragment mServicesFragment;
        // @Inject
        private ChatsFragment mChatsFragment;
        // Inject
        private NodeListFragment mNodeListFragment;

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);


            setContentView(R.layout.activity_viewpagertab);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            LinearLayout layout = (LinearLayout)findViewById(R.id.header);
            layout.setBackgroundColor(Color.rgb(138, 7, 7));

            // ContentResolver contentResolver = getContentResolver();
            // initDatabase(getContentResolver());
            mNetworkService = new NetworkServiceStub();
            mHeaderView = findViewById(R.id.header);
            ViewCompat.setElevation(mHeaderView, getResources().getDimension(R.dimen.toolbar_elevation));
            mToolbarView = findViewById(R.id.toolbar);

            Map<Integer, Fragment> fragments = new HashMap<>();
            fragments.put(0, mServicesFragment);
            fragments.put(1, mChatsFragment);
            fragments.put(2, mChatsFragment); // TODO mNodeListFragment

            mPagerAdapter = new NavigationAdapter(getSupportFragmentManager(), fragments);
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


//        this.setTitle("Registration");
//        Intent intent = new Intent(this, RegistrationActivity.class);
//        startActivity(intent);

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

        public static ArrayList<String> getDialogsData() {
            ArrayList<String> items = new ArrayList<>();
            for ( String item : Arrays.asList(new String[]{"Suggestion Box", "Orders", "Shop Manager"})){
                items.add(item);
            }

            return items;
        }

        public ApiService getNetworkService() {
            return mNetworkService;
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }


         *
         * TODO Class description
         * TODO Test cases
         *
         * This adapter provides two types of fragments as an example.
         * {@linkplain #createItem(int)} should be modified if you use this example for your app.
         *
        private static class NavigationAdapter extends CacheFragmentStatePagerAdapter {

            Map<Integer, Fragment> mFragments;
            private static final String[] TITLES = new String[]{"SERVICES", "CHATS", "YELLOW PAGE"};

            public NavigationAdapter(FragmentManager fm, Map<Integer, Fragment> fragments) {
                super(fm);
                mFragments = fragments;
            }

            public void setScrollY(int scrollY) {
                //mScrollY = scrollY;
            }

            @Override
            protected Fragment createItem(int position) {
                final int pattern = position % 3;
                return mFragments.get(pattern);
            }

            @Override
            public int getCount() {
                return TITLES.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return TITLES[position];
            }
        }
    }

    */
}
