package com.ebridgevas.android.ebridgeapp.widget;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ebridgevas.android.ebridgeapp.R;
import com.ebridgevas.android.ebridgeapp.model.EBridgeAppModel;
import com.ebridgevas.android.ebridgeapp.model.Product;
import com.ebridgevas.android.ebridgeapp.model.ProductCategory;
import com.ebridgevas.android.ebridgeapp.model.ProductRange;
import com.ebridgevas.android.ebridgeapp.model.Sku;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProductRangeListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Product> products;
    private ArrayList<ProductRange> ranges;

    public ProductRangeListAdapter(Activity context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    public void setProductRanges(ArrayList<ProductRange> ranges){
        Log.i("TEST", "setProductRanges (" + ranges.size() + ")");
       this.ranges = ranges;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public int getGroupCount() {
        return products.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_product_range_header_row, null);
        }

        TextView productDescriptionView = (TextView)convertView.findViewById(R.id.productDescription);
        Product product = products.get(groupPosition);
        productDescriptionView.setText(product.getDescription());

        TextView productDateView = (TextView)convertView.findViewById(R.id.productDate);
        Date date = product.getProductDate();
        productDateView.setText(date != null ? new SimpleDateFormat("MMM dd, HH:mm").format(date) : "");

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

        Log.i("TEST", "getChildView()");

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_market_selection_second_row, null);
        }

        Log.i("TEST", "getChild(" + childPosition + ")");
        ProductRange range = ranges.get(childPosition);
        Log.i("TEST", "range : " + range.getDescription() );

//        ArrayList<NodeData> children = networkService.getChildren(parentId);
//        final Sku sku = skus.get(childPosition);

        TextView productDescriptionView = (TextView)convertView.findViewById(R.id.productDescription);
        productDescriptionView.setText(range.getDescription());

        TextView productDateView = (TextView)convertView.findViewById(R.id.productDate);
        productDateView.setText("");

        ArrayList<Sku> skus = range.getSkus();

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
        productRangeCountView.setText("");

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
//        ProductCategory category = categories.get(groupPosition);
//        ArrayList<Product> products
//                = EBridgeAppModel.getProducts(
//                category.getProviderCode(), category.getServiceCode(), category.getCategoryCode());
//        return products.size();
//        return networkService.getChildrenCount(nodes.get(groupPosition).getId());
        return ranges != null ? ranges.size() : 0;
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
