package com.ebridgevas.android.ebridgeapp.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.ebridgevas.android.ebridgeapp.R;
import com.ebridgevas.android.ebridgeapp.model.EBridgeAppModel;
import com.ebridgevas.android.ebridgeapp.model.Product;
import com.ebridgevas.android.ebridgeapp.model.ProductCategory;
import com.ebridgevas.android.ebridgeapp.model.ProductRange;
import com.ebridgevas.android.ebridgeapp.util.NotificationCenter;
import com.ebridgevas.android.ebridgeapp.widget.ProductRangeListAdapter;
import com.ebridgevas.android.ebridgeapp.widget.SecondLevelAdapter;

import java.util.ArrayList;

public class ProductRangeListFragment extends Fragment {

    private final static String TAG = "MainActivity";
    public final static String ARG_INITIAL_POSITION = "ARG_INITIAL_POSITION";
    public final static String ARG_PROVIDER_CODE = "ARG_PROVIDER_CODE";
    public final static String ARG_CATEGORY_CODE = "ARG_CATEGORY_CODE";
    public final static String ARG_PRODUCT_CODE = "ARG_PRODUCT_CODE";
    public final static String ARG_PRODUCT_DESCRIPTION = "ARG_PRODUCT_DESCRIPTION";
    public final static String ARG_PRODUCT_DATE = "ARG_PRODUCT_DATE";
    private ExpandableListView mExpandableListView;
    private ProductRangeListAdapter adapter;
    private final ArrayList<Product> products = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_events_listview, container, false);
        mExpandableListView = (ExpandableListView) view.findViewById(R.id.marketSelectionList);

        Bundle args = getArguments();
        String providerCode = args.getString(ServicesFragment.ARG_PROVIDER_CODE);
        String serviceCode = args.getString(ServicesFragment.ARG_SERVICE_CODE);
        String categoryCode = args.getString(ARG_CATEGORY_CODE);
        String productCode = args.getString(ARG_PRODUCT_CODE);

//        final ArrayList<ProductRange> ranges
//                = EBridgeAppModel.getProductRange(providerCode, serviceCode, categoryCode, productCode);

        Product product = new Product(null, null, null, null,"No match selected", null);
        products.add(product);
        adapter = new ProductRangeListAdapter(getActivity(), products);
        mExpandableListView.setAdapter(adapter);
        mExpandableListView.expandGroup(0);

        NotificationCenter.getInstance().addObserver(new ProductSelectionHandler(), NotificationCenter.PRODUCT_SELECTED);

        return view;
    }

    private class ProductSelectionHandler implements NotificationCenter.NotificationCenterDelegate {

        @Override
        public void didReceivedNotification(int id, Object... args) {
//            Log.i("TEST", "product code : " + args[0] + ", description: " + args[1]);
//            ProductRange range
//                    = new ProductRange(
//                            null, null, null, null, null, null,"" + args[1], "");
//            ranges.add(range);
            products.clear();
            Product product = (Product) args[0];
            products.add(product);

            ArrayList<ProductRange> ranges
                    = EBridgeAppModel.getProductRange(
                    product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                    product.getProductCode());
            Log.i("TEST", "Adding ranges of size : " + ranges.size());
            adapter.setProductRanges(ranges);

            new Handler(Looper.getMainLooper()).post(new Runnable() {

                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
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
}
