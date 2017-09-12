package com.ebridgevas.android.ebridgeapp.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ebridgevas.android.ebridgeapp.R;
import com.ebridgevas.android.ebridgeapp.model.EBridgeAppModel;
import com.ebridgevas.android.ebridgeapp.model.NodeData;
import com.ebridgevas.android.ebridgeapp.model.Product;
import com.ebridgevas.android.ebridgeapp.model.ProductCategory;
import com.ebridgevas.android.ebridgeapp.model.ProductRange;
import com.ebridgevas.android.ebridgeapp.model.Sku;
import com.ebridgevas.android.ebridgeapp.services.impl.NetworkServiceStub;
import com.ebridgevas.android.ebridgeapp.ui.ProductBrowserFragment;
import com.ebridgevas.android.ebridgeapp.ui.ProductListFragment;
import com.ebridgevas.android.ebridgeapp.ui.ProductRangeListFragment;
import com.ebridgevas.android.ebridgeapp.ui.ServicesFragment;
import com.ebridgevas.android.ebridgeapp.util.NotificationCenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SecondLevelAdapter extends BaseExpandableListAdapter {

    private Activity context;
//    private ArrayList<NodeData> nodes;
    private ArrayList<ProductCategory> categories;
//    private NetworkServiceStub networkService;

    public SecondLevelAdapter(Activity context, ArrayList<ProductCategory> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public int getGroupCount() {
        return categories.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_market_selection_third_row, null);
        }
        TextView textView = (TextView)convertView.findViewById(R.id.eventsListEventRowText);
        textView.setText(categories.get(groupPosition).getDescription());

        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
            ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_market_selection_second_row, null);
        }


        ProductCategory category = categories.get(groupPosition);
        ArrayList<Product> products
                = EBridgeAppModel.getProducts(
                        category.getProviderCode(), category.getServiceCode(), category.getCategoryCode());

//        ArrayList<NodeData> children = networkService.getChildren(parentId);
        final Product product = products.get(childPosition);

        TextView productDescriptionView = (TextView)convertView.findViewById(R.id.productDescription);
        productDescriptionView.setText(product.getDescription());

        TextView productDateView = (TextView)convertView.findViewById(R.id.productDate);
        productDateView.setText(new SimpleDateFormat("MMM dd, HH:mm").format(product.getProductDate()));

        ArrayList<Sku> skus = product.getSkus();
        // product 1
        final Sku sku = skus.get(0);
        TextView productDescription1View = (TextView)convertView.findViewById(R.id.productDescription1);
        productDescription1View.setText(sku.getDescription());

        View.OnClickListener product1ClickHandler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        context, "home wins " +  ", sku: " + sku,
                        Toast.LENGTH_LONG).show();
            }
        };
        productDescription1View.setOnClickListener(product1ClickHandler);

        TextView productValue1View = (TextView)convertView.findViewById(R.id.productValue1);
        productValue1View.setText(sku.getPrice());
        productValue1View.setOnClickListener(product1ClickHandler);


        final Sku sku2 = skus.get(1);
        TextView productDescription2View = (TextView)convertView.findViewById(R.id.productDescription2);
        productDescription2View.setText(sku2.getDescription());
        View.OnClickListener product2ClickHandler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        context, "draw " +  ", sku: " + sku2,
                        Toast.LENGTH_LONG).show();
            }
        };
        productDescription2View.setOnClickListener(product2ClickHandler);

        TextView productValue2View = (TextView)convertView.findViewById(R.id.productValue2);
        productValue2View.setText(sku2.getPrice());
        productValue2View.setOnClickListener(product2ClickHandler);

        final Sku sku3 = skus.get(2);
        TextView productDescription3View = (TextView)convertView.findViewById(R.id.productDescription3);
        productDescription3View.setText(sku3.getDescription());
        View.OnClickListener product3ClickHandler = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        context, "away wins " +  ", sku: " + sku3,
                        Toast.LENGTH_LONG).show();
            }
        };
        productDescription3View.setOnClickListener(product3ClickHandler);

        TextView productValue3View = (TextView)convertView.findViewById(R.id.productValue3);
        productValue3View.setText(sku3.getPrice());
        productValue3View.setOnClickListener(product3ClickHandler);

        TextView productRangeCountView = (TextView)convertView.findViewById(R.id.productRangeCount);
        int rangeCount = product.getProductRanges().size();
        productRangeCountView.setText(rangeCount + " >");
//        textView.setText(mNode.getChildAt(groupPosition).getChildAt(childPosition).data);

        productRangeCountView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ProductRange> ranges = product.getProductRanges();
//                viewPager.setCurrentItem(1);


                NotificationCenter.getInstance().postNotificationName(
                        NotificationCenter.PRODUCT_SELECTED, product);

//                Fragment fragment = new ProductRangeListFragment();
//                Bundle args = new Bundle();
//                args.putString(ProductRangeListFragment.ARG_PROVIDER_CODE, product.getProviderCode());
//                args.putString(ProductRangeListFragment.ARG_CATEGORY_CODE, product.getCategoryCode());
//                args.putString(ProductRangeListFragment.ARG_PRODUCT_CODE, product.getProductCode());
//                args.putString(ProductRangeListFragment.ARG_PRODUCT_DESCRIPTION, product.getDescription());
//                args.putLong(  ProductRangeListFragment.ARG_PRODUCT_DATE, product.getProductDate().getTime());
//                fragment.setArguments(args);
//
//                FragmentTransaction transaction
//                        = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.root, fragment, "SecondLevelAdapter");
//                transaction.addToBackStack(null);
//                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                transaction.commit();

//                Toast.makeText(context, "show other odds " + v.getId() + ", product" + product.getDescription() + ", range count : " + ranges.size(), Toast.LENGTH_LONG).show();

            }
        });

//        TextView eventDate = (TextView)convertView.findViewById(R.id.counter);
//        eventDate.setText("May 28, 20:30");

//        TextView odds = (TextView)convertView.findViewById(R.id.subTitle);
//        odds.setText("1 5/10 2 3/4 2 4/8");

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        ProductCategory category = categories.get(groupPosition);
        ArrayList<Product> products
                = EBridgeAppModel.getProducts(
                category.getProviderCode(), category.getServiceCode(), category.getCategoryCode());
        return products.size();
//        return networkService.getChildrenCount(nodes.get(groupPosition).getId());
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
