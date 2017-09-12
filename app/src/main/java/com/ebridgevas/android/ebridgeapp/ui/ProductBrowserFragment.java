package com.ebridgevas.android.ebridgeapp.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.ebridgevas.android.ebridgeapp.EBridgeApplication;
import com.ebridgevas.android.ebridgeapp.ExpandableListAdapter;
import com.ebridgevas.android.ebridgeapp.R;
import com.ebridgevas.android.ebridgeapp.SettingsListAdapter;
import com.ebridgevas.android.ebridgeapp.data.BaseItem;
import com.ebridgevas.android.ebridgeapp.data.Category;
import com.ebridgevas.android.ebridgeapp.data.DataProvider;
import com.ebridgevas.android.ebridgeapp.model.EBridgeAppModel;
import com.ebridgevas.android.ebridgeapp.model.NodeData;
import com.ebridgevas.android.ebridgeapp.multilevellistview.ItemInfo;
import com.ebridgevas.android.ebridgeapp.multilevellistview.MultiLevelListAdapter;
import com.ebridgevas.android.ebridgeapp.multilevellistview.MultiLevelListView;
import com.ebridgevas.android.ebridgeapp.nineoldandroids.ViewHelper;
import com.ebridgevas.android.ebridgeapp.nineoldandroids.ViewPropertyAnimator;
import com.ebridgevas.android.ebridgeapp.observablescrollview.CacheFragmentStatePagerAdapter;
import com.ebridgevas.android.ebridgeapp.observablescrollview.Scrollable;
import com.ebridgevas.android.ebridgeapp.services.impl.NetworkServiceStub;
import com.ebridgevas.android.ebridgeapp.util.NotificationCenter;
import com.ebridgevas.android.ebridgeapp.util.TreeNode;
import com.ebridgevas.android.ebridgeapp.widget.CircleImageView;
import com.ebridgevas.android.ebridgeapp.widget.LevelBeamView;
import com.ebridgevas.android.ebridgeapp.widget.SlidingTabLayout;
import com.squareup.picasso.Picasso;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.packet.Message;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductBrowserFragment extends Fragment {

    public static final String ARG_PROVIDER_CODE = "ARG_PROVIDER_CODE";
    public static final String ARG_SERVICE_CODE = "ARG_SERVICE_CODE";

//    ExpandableListAdapter listAdapter;
//    ExpandableListView expListView;
//    List<String> listDataHeader;
//    HashMap<String, List<String>> listDataChild;
//
//    ExpandableListView bettingCountryGroupListView;
//    ExpandableListAdapter bettingCountryGroupListAdapter;
//
//    ExpandableListView bettingLeagueGroupListView;
//    ExpandableListAdapter bettingLeagueGroupListAdapter;
//
//    List<String> countryListDataHeader;
//    HashMap<String, List<String>>  leagueListDataHeader;
//    HashMap<String, List<String>> eventsListDataChild;

    private ExpandableListView expandableListView;

    private MultiLevelListView mListView;
    private Switch mMultipliedExpandingView;
    private Switch mAlwaysExpandedView;

    private boolean mAlwaysExpandend;

    private ExpandableListView expList;
    private ExpandableListAdapter expListAdapter;

    private Resources res;

    private SettingsListAdapter adapter;
    private ExpandableListView categoriesList;
    private ArrayList<Category> categories;

    private View mHeaderView;
    private View mToolbarView;
    private ViewPager mPager;
    private NavigationAdapter mPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_market_selection, container, false);
        /*
//        View view = inflater.inflate(R.layout.expandable_list_layout, container, false);

        Bundle args = getArguments();
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(args.getString("TITLE"));
        TextView chatTitle = (TextView)view.findViewById(R.id.chatTitle);
        chatTitle.setText(args.getString("TITLE"));
        // thumbnail image
        CircleImageView chatThumbnail = (CircleImageView)view.findViewById(R.id.chatThumbnail);
        int resId = getActivity()
                    .getResources()
                    .getIdentifier(args.getString("AVATOR"), "drawable", getActivity().getPackageName());

        Picasso.with(getActivity().getApplicationContext()).load(resId).into(chatThumbnail);
//        chatThumbnail.setImageBitmap(null);

        View.OnClickListener backPressedLister = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        };

        ImageView backArrow = (ImageView)view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(backPressedLister);
        chatTitle.setOnClickListener(backPressedLister);
        chatThumbnail.setOnClickListener(backPressedLister);
        */
        /* -------------------------------------------------------------------------------------------------------------
        // get the listView
        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(getContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        --------------------------------------------------------------------------------------------------------------*/

        // get country group list
        /*
        expandableListView = (ExpandableListView) view.findViewById(R.id.marketSelectionList);
        TreeNode<String> treeNode = buildTreeNode();
        expandableListView.setAdapter(new SecondLevelAdapter(getContext(), treeNode));
        */
//        listView.setGroupIndicator(null);
        // prepare data for this list. Group (countries) and children (leagues)

//        TreeNode<String> treeNode = buildTreeNode();
//
//        bettingCountryGroupListAdapter
//                = new ExpandableListAdapter(getContext(), treeNode);
//        bettingCountryGroupListView.setAdapter(bettingCountryGroupListAdapter);
//
        // get league group list
//        bettingLeagueGroupListView = (ExpandableListView) view.findViewById(R.id.bettingLeagueGroupList);
        // prepare data for this list. Group (leagues) and children (betting events)
//        bettingLeagueGroupListAdapter
//                = new ExpandableListAdapter(getContext(), leagueListDataHeader., eventsListDataChild);
        /*
        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                                                @Override
                                                public boolean onGroupClick(ExpandableListView parent, View v,
                                                        int groupPosition, long id) {
                                                    // Toast.makeText(getApplicationContext(),
                                                    // "Group Clicked " + listDataHeader.get(groupPosition),
                                                    // Toast.LENGTH_SHORT).show();
                                                    return false;
                                                }
                                            });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                                                 @Override
                                                 public void onGroupExpand(int groupPosition) {
                                                     Toast.makeText(
                                                             getContext().getApplicationContext(),
                                                             listDataHeader.get(groupPosition) + " Expanded",
                                                             Toast.LENGTH_SHORT).show();
                                                 }
                                             });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                                                   @Override
                                                   public void onGroupCollapse(int groupPosition) {
                                                       Toast.makeText(getContext().getApplicationContext(),
                                                               listDataHeader.get(groupPosition) + " Collapsed",
                                                               Toast.LENGTH_SHORT).show();

                                                   }
                                               });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

                                                @Override
                                                public boolean onChildClick(ExpandableListView parent, View v,
                                                        int groupPosition, int childPosition, long id) {
                                                    // TODO Auto-generated method stub
                                                    Toast.makeText(
                                                            getContext().getApplicationContext(),
                                                            listDataHeader.get(groupPosition)
                                                                    + " : "
                                                                    + listDataChild.get(
                                                                    listDataHeader.get(groupPosition)).get(
                                                                    childPosition), Toast.LENGTH_SHORT)
                                                            .show();
                                                    return false;
                                                }
                                            });
        */

//        configViews(view);

//
//        expList = (ExpandableListView) view.findViewById(R.id.expandableList);
//
//        expListAdapter = new ExpandableListAdapter(getContext());
//
//        //removes the standard group state indicator
//        expList.setGroupIndicator(null);
//
//		/*
//		 * Setting up mock data
//		 */
//        ArrayList<TravelItem> futureItems = new ArrayList<TravelItem>();
//        ArrayList<TravelItem> pastItems = new ArrayList<TravelItem>();
//
//        res = getResources();
//
//        for(int x = 0; x < res.getStringArray(R.array.future_titles).length; x++){
//            TravelItem future = new TravelItem();
//            future.setTitle(res.getStringArray(R.array.future_titles)[x]);
//            future.setDeparture(res.getStringArray(R.array.future_departures)[x]);
//            future.setDestination(res.getStringArray(R.array.future_destinations)[x]);
//            future.setDate(res.getStringArray(R.array.future_dates)[x]);
//            future.setTime(res.getStringArray(R.array.future_times)[x]);
//
//            futureItems.add(future);
//        }
//        for(int y = 0; y < res.getStringArray(R.array.past_titles).length; y++){
//            TravelItem past = new TravelItem();
//            past.setTitle(res.getStringArray(R.array.past_titles)[y]);
//            past.setDeparture(res.getStringArray(R.array.past_departures)[y]);
//            past.setDestination(res.getStringArray(R.array.past_destinations)[y]);
//            past.setDate(res.getStringArray(R.array.past_dates)[y]);
//
//            pastItems.add(past);
//        }
//
//        //send data to adapter
//        expListAdapter.setupTrips(pastItems, futureItems);
//        //tie the adapter to our expandable list view
//        expList.setAdapter(expListAdapter);
//
//		/*
//		 * if there is at least one child, expand the first
//		 * group by default.  (future travel plans)
//		 */
//        if(expListAdapter.getChildrenCount(0) >= 1){
//            expList.expandGroup(0);
//        }
//
//		/*
//		 * override the onGroupClick method to make sure the
//		 * first group (future travel plans) does not collapse
//		 * or expand.
//		 */
//        expList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//
//                                            @Override
//                                            public boolean onGroupClick(ExpandableListView parent, View v,
//                                                    int groupPosition, long id) {
//                                                if(groupPosition == 0){
//                                                    return true;
//                                                } else {
//                                                    return false;
//                                                }
//                                            }
//                                        });
/*
        categoriesList = (ExpandableListView)view.findViewById(R.id.categories);
        categories = Category.getCategories();
        adapter = new SettingsListAdapter(getContext(), categories, categoriesList);
        categoriesList.setAdapter(adapter);

        categoriesList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

                                                   @Override
                                                   public boolean onChildClick(ExpandableListView parent, View v,
                                                           int groupPosition, int childPosition, long id) {


                                                       TextView checkbox = (TextView)v.findViewById(R.id.list_item_text_child);
//                                                       checkbox.toggle();


                                                       // find parent view by tag
                                                       View parentView = categoriesList.findViewWithTag(categories.get(groupPosition).name);
                                                       if(parentView != null) {
                                                           TextView sub = (TextView)parentView.findViewById(R.id.list_item_text_subscriptions);

                                                           if(sub != null) {
                                                               Category category = categories.get(groupPosition);
//                                                               if(checkbox.isChecked()) {
//                                                                   // add child category to parent's selection list
//                                                                   category.selection.add(checkbox.getBody().toString());
//
//                                                                   // sort list in alphabetical order
//                                                                   Collections.sort(category.selection, new CustomComparator());
//                                                               }
//                                                               else {
                                                                   // remove child category from parent's selection list
                                                                   category.selection.remove(checkbox.getBody().toString());
//                                                               }

                                                               // display selection list
                                                               sub.setText(category.selection.toString());
                                                           }
                                                       }
                                                       return true;
                                                   }
                                               });
        */

        Toolbar toolbar = (Toolbar)view.findViewById(R.id.marketToolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
        Bundle args = getArguments();
        TextView chatTitle = (TextView)view.findViewById(R.id.chatTitle);
        chatTitle.setText(args.getString("TITLE"));

        String providerCode = args.getString(ServicesFragment.ARG_PROVIDER_CODE);
        String serviceCode = args.getString(ServicesFragment.ARG_SERVICE_CODE);

        String parentId = null;//args.getString(NodeListItemClickHandler.ARG_PARENT_ID);

        NetworkServiceStub networkService = ((EBridgeApplication)getActivity().getApplication()).getNetworkService();
        ArrayList<NodeData> nodes = networkService.getChildren(parentId);

        // thumbnail image
        CircleImageView chatThumbnail = (CircleImageView)view.findViewById(R.id.chatThumbnail);
        int resId = getActivity()
                .getResources()
                .getIdentifier(args.getString("AVATOR"), "drawable", getActivity().getPackageName());

        Picasso.with(getActivity().getApplicationContext()).load(resId).into(chatThumbnail);
//        chatThumbnail.setImageBitmap(null);

        View.OnClickListener backPressedLister = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        };

        ImageView backArrow = (ImageView)view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(backPressedLister);
        chatTitle.setOnClickListener(backPressedLister);
        chatThumbnail.setOnClickListener(backPressedLister);
        mHeaderView = view.findViewById(R.id.marketHeader);
//        ViewCompat.setElevation(mHeaderView, getResources().getDimension(R.dimen.toolbar_elevation));
        mToolbarView = view.findViewById(R.id.marketToolbar);

        mPagerAdapter
                = new NavigationAdapter(getActivity().getSupportFragmentManager(), nodes, providerCode, serviceCode);
        mPager = (ViewPager)view.findViewById(R.id.marketPager);
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(0);

        SlidingTabLayout slidingTabLayout = (SlidingTabLayout)view.findViewById(R.id.marketSlidingTabs);
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

        NotificationCenter.getInstance().addObserver(new ProductSelectionHandler(), NotificationCenter.PRODUCT_SELECTED);

        return view;
    }

    private class ProductSelectionHandler implements NotificationCenter.NotificationCenterDelegate {

        @Override
        public void didReceivedNotification(int id, Object... args) {
//            Log.i("TEST", "product code : " + args[0] + ", description: " + args[1]);
            mPager.setCurrentItem(1);
//            Chat chat = (Chat) args[0];
//            Message message = (Message) args[1];
////            messageProcessor.process(chat, message);
//
//            Map<String, String> params = new HashMap<>();
//            params.put("node-text-top-left", message.getFrom());
//            params.put("node-text-bottom-left", message.getBody());
//            params.put("thumbnail", "feli");
//            final NodeData data = new NodeData("600-10000-30005", "600-10000-10002", params, 2);
////            nodes.add(data);
//            Log.i(TAG, "node data : " + data);
//
//
//            new Handler(Looper.getMainLooper()).post(new Runnable() {
//
//                @Override
//                public void run() {
//                    nodeAdapter.notifyDataSetChanged();
//                }
//            });
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

    private static class NavigationAdapter extends CacheFragmentStatePagerAdapter {

        private List<String> serviceTitles;
        private String providerCode;
        private String serviceCode;
//        private ArrayList<NodeData> nodes;

        private int mScrollY;

        public void setScrollY(int scrollY) {
            mScrollY = scrollY;
        }

        public NavigationAdapter(   FragmentManager fragmentManager,
                                    ArrayList<NodeData> nodes,
                                    String providerCode,
                                    String serviceCode) {
            super(fragmentManager);
//            this.nodes = nodes;
            serviceTitles = EBridgeAppModel.getServiceTitles(providerCode, serviceCode);
            this.providerCode = providerCode;
            this.serviceCode = serviceCode;
        }

        @Override
        protected Fragment createItem(int position) {

            // Initialize fragments.
            // Please be sure to pass scroll position to each fragments using setArguments.

            Fragment mFragment;
            final int pattern = position % 3;

            switch(pattern) {
                case 0:
                    mFragment = new ProductListFragment();
                    Bundle args = new Bundle();
                    if (0 <= mScrollY) {
                        args.putInt(ProductListFragment.ARG_INITIAL_POSITION, 1);
                        args.putString(ServicesFragment.ARG_PROVIDER_CODE, providerCode);
                        args.putString(ServicesFragment.ARG_SERVICE_CODE, serviceCode);
                    }
                    mFragment.setArguments(args);
                    break;
                case 1:
                    mFragment = new ProductRangeListFragment();
                    args = new Bundle();
                    if (0 <= mScrollY) {
                        args.putInt(ProductListFragment.ARG_INITIAL_POSITION, 1);
                        args.putString(ServicesFragment.ARG_PROVIDER_CODE, providerCode);
                        args.putString(ServicesFragment.ARG_SERVICE_CODE, serviceCode);
                    }
                    mFragment.setArguments(args);
                    break;
                case 2:
//                    mFragment = new EventListViewFragment();
//                    args = new Bundle();
//                    if (0 <= mScrollY) {
//                        args.putInt(EventListViewFragment.ARG_INITIAL_POSITION, 1);
//                    }
//                    mFragment.setArguments(args);
//                    break;
//                case 3:
                default:
                    mFragment = new ProductListFragment();
                    args = new Bundle();
                    if (0 <= mScrollY) {
                        args.putInt(ProductListFragment.ARG_INITIAL_POSITION, 1);
                        args.putString(ServicesFragment.ARG_PROVIDER_CODE, providerCode);
                        args.putString(ServicesFragment.ARG_SERVICE_CODE, serviceCode);
                    }
                    mFragment.setArguments(args);
                    break;
            }

            return mFragment;
        }

        @Override
        public int getCount() {
            return serviceTitles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
           return serviceTitles.get(position);
//           return nodes.get(position).getParams().get("node-text-top-left");
        }
    }


//    private TreeNode<String> buildTreeNode() {
//
//        // countries
//        TreeNode<String> root = new TreeNode<String>("countries");
//        TreeNode<String> england = root.addChild("England");
//        TreeNode<String> spain = root.addChild("Spain");
////        TreeNode<String> italy = root.addChild("Italy");
////        TreeNode<String> germany = root.addChild("Germany");
//
//        // leagues
//        TreeNode<String> premiership = england.addChild("Premiership");
//        TreeNode<String> championship = england.addChild("Championship");
//        TreeNode<String> league03 = england.addChild("League3");
//
//        // events
//        premiership.addChild("Arsenal v Aston Villa");
//        premiership.addChild("Totenham v New Castle");
//        premiership.addChild("Swanse v Man City");
//        premiership.addChild("Liverpool v Southampton");
//        premiership.addChild("Man United v Bourbeth");
//        premiership.addChild("Watford v Westham");
//
//        championship.addChild("Burnley v Reading");
//
//        TreeNode<String> league1 = spain.addChild("La Liga");
//        TreeNode<String> league2 = spain.addChild("League 1");
////        TreeNode<String> league3 = spain.addChild("League 2");
//
//        // events
//        league1.addChild("Barcelona v Aston Villa");
//        league1.addChild("Real Madrid v New Castle");
//        league1.addChild("Ateletico v Man City");
//        league1.addChild("Valenica v Southampton");
//        league1.addChild("Depotivo v Bourbeth");
//        league1.addChild("Villa Real v Westham");
//
//        league2.addChild("Arsenal v Aston Villa");
//        league2.addChild("Totenham v New Castle");
//        league2.addChild("Swanse v Man City");
//        league2.addChild("Liverpool v Southampton");
//        league2.addChild("Man United v Bourbeth");
//        league2.addChild("Watford v Westham");
//
//        return root;
//    }

    private TreeNode<String> buildTreeNode() {

        // countries
        TreeNode<String> root = new TreeNode<String>("countries");
        TreeNode<String> england = root.addChild("England");
        TreeNode<String> spain = root.addChild("Spain");
//        TreeNode<String> italy = root.addChild("Italy");
//        TreeNode<String> germany = root.addChild("Germany");

        // leagues
        TreeNode<String> leagues = new TreeNode<String>("leagues");

        TreeNode<String> premiership = leagues.addChild("England - Premiership");
        TreeNode<String> championship = leagues.addChild("England - Championship");
        TreeNode<String> league03 = leagues.addChild("England - League3");

        // events
        premiership.addChild("Arsenal v Aston Villa");
        premiership.addChild("Totenham v New Castle");
        premiership.addChild("Swanse v Man City");
        premiership.addChild("Liverpool v Southampton");
        premiership.addChild("Man United v Bourbeth");
        premiership.addChild("Watford v Westham");

        championship.addChild("Burnley v Reading");

        TreeNode<String> league1 = leagues.addChild("Spain - La Liga");
        TreeNode<String> league2 = leagues.addChild("Spain - League 1");
//        TreeNode<String> league3 = spain.addChild("League 2");

        // events
        league1.addChild("Barcelona v Aston Villa");
        league1.addChild("Real Madrid v New Castle");
        league1.addChild("Ateletico v Man City");
        league1.addChild("Valenica v Southampton");
        league1.addChild("Depotivo v Bourbeth");
        league1.addChild("Villa Real v Westham");

        league2.addChild("Arsenal v Aston Villa");
        league2.addChild("Totenham v New Castle");
        league2.addChild("Swanse v Man City");
        league2.addChild("Liverpool v Southampton");
        league2.addChild("Man United v Bourbeth");
        league2.addChild("Watford v Westham");

        return leagues;
    }


    /*
     * Preparing the list data
     */
    /*
    private void prepareListData() {

        listDataHeader = new ArrayList<>();
        listDataHeader.add("England");
        listDataHeader.add("Spain");
        listDataHeader.add("Italy");
        listDataHeader.add("Germany");

        List<String> country1 = new ArrayList<String>();
        country1.add("ENG Premiership ");
        country1.add("ENG Championship");

        List<String> country2 = new ArrayList<String>();
        country2.add("ESP La Liga");
        country2.add("ESP League 1");

        List<String> country3 = new ArrayList<String>();
        country3.add("ESP La Liga");
        country3.add("ESP League 1");

        List<String> country4 = new ArrayList<String>();
        country4.add("ESP La Liga");
        country4.add("ESP League 1");

        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), country1);
        listDataChild.put(listDataHeader.get(1), country2);
        listDataChild.put(listDataHeader.get(2), country3);
        listDataChild.put(listDataHeader.get(3), country4);
    }
    */
    /*
    private void prepareListData() {

        countryListDataHeader = new ArrayList<>();
        countryListDataHeader.add("England");
        countryListDataHeader.add("Spain");
        countryListDataHeader.add("Italy");
        countryListDataHeader.add("Germany");


        leagueListDataHeader = new HashMap<>();
        // England leagues
        String england = countryListDataHeader.get(0);
        lleagueListDataHeader.get(england);
        leagueListDataHeader.put(england, new ArrayList<String>());
        leagueListDataHeader.get(england).add("ENG Premiership");
        leagueListDataHeader.get(countryListDataHeader.get(0)).add("ENG Championship");
        // Spanish leagues
        leagueListDataHeader.put(countryListDataHeader.get(1), new ArrayList<String>());
        leagueListDataHeader.get(countryListDataHeader.get(1)).add("ESP La Liga");
        leagueListDataHeader.get(countryListDataHeader.get(1)).add("ESP League 1");
        // Italian leagues
        leagueListDataHeader.put(countryListDataHeader.get(2), new ArrayList<String>());
        leagueListDataHeader.get(countryListDataHeader.get(2)).add("ESP La Liga");
        leagueListDataHeader.get(countryListDataHeader.get(2)).add("ESP League 1");
        // German leagues
        leagueListDataHeader.put(countryListDataHeader.get(3), new ArrayList<String>());
        leagueListDataHeader.get(countryListDataHeader.get(3)).add("ESP La Liga");
        leagueListDataHeader.get(countryListDataHeader.get(3)).add("ESP League 1");

        // English Premiership events
        eventsListDataChild = new HashMap<>();
        leagueListDataHeader.get(countryListDataHeader.get(0)).get(0)
        List<String> country1 = new ArrayList<String>();
        country1.add("ENG Premiership ");
        country1.add("ENG Championship");

        List<String> country2 = new ArrayList<String>();
        country2.add("ESP La Liga");
        country2.add("ESP League 1");

        List<String> country3 = new ArrayList<String>();
        country3.add("ESP La Liga");
        country3.add("ESP League 1");

        List<String> country4 = new ArrayList<String>();
        country4.add("ESP La Liga");
        country4.add("ESP League 1");

        listDataChild = new HashMap<>();
        listDataChild.put(listDataHeader.get(0), country1);
        listDataChild.put(listDataHeader.get(1), country2);
        listDataChild.put(listDataHeader.get(2), country3);
        listDataChild.put(listDataHeader.get(3), country4);
    }
    */

    public class CustomComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }



//    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private class ListAdapter extends MultiLevelListAdapter {

        private class ViewHolder {
            TextView nameView;
            TextView infoView;
            ImageView arrowView;
            LevelBeamView levelBeamView;
        }

        @Override
        public List<?> getSubObjects(Object object) {
            return DataProvider.getSubItems((BaseItem) object);
        }

        @Override
        public boolean isExpandable(Object object) {
            return DataProvider.isExpandable((BaseItem) object);
        }

        @Override
        public View getViewForObject(Object object, View convertView, ItemInfo itemInfo) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
//                convertView = LayoutInflater.from(getContext()).inflate(R.layout.data_item, null);
                viewHolder.infoView = (TextView) convertView.findViewById(R.id.dataItemInfo);
                viewHolder.nameView = (TextView) convertView.findViewById(R.id.dataItemName);
                viewHolder.arrowView = (ImageView) convertView.findViewById(R.id.dataItemArrow);
//                viewHolder.levelBeamView = (LevelBeamView) convertView.findViewById(R.id.dataItemLevelBeam);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.nameView.setText(((BaseItem) object).getName());
            viewHolder.infoView.setText(getItemInfoDsc(itemInfo));

            if (itemInfo.isExpandable() && !mAlwaysExpandend) {
                viewHolder.arrowView.setVisibility(View.VISIBLE);
                viewHolder.arrowView.setImageResource(itemInfo.isExpanded() ?
                                R.drawable.arrow_up : R.drawable.arrow_down);
            } else {
                viewHolder.arrowView.setVisibility(View.GONE);
            }

            //viewHolder.levelBeamView.setLevel(itemInfo.getLevel());

            return convertView;
        }
    }

    private String getItemInfoDsc(ItemInfo itemInfo) {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("level[%d], idx in level[%d/%d]",
                        itemInfo.getLevel() + 1, /*Indexing starts from 0*/
                        itemInfo.getIdxInLevel() + 1 /*Indexing starts from 0*/,
                        itemInfo.getLevelSize()));

        if (itemInfo.isExpandable()) {
            builder.append(String.format(", expanded[%b]", itemInfo.isExpanded()));
        }
        return builder.toString();
    }

    public ViewPager getPager() {
        return mPager;
    }
}
