package com.ebridgevas.android.ebridgeapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.ebridgevas.android.ebridgeapp.R;
import com.ebridgevas.android.ebridgeapp.model.EBridgeAppModel;
import com.ebridgevas.android.ebridgeapp.model.ProductCategory;
import com.ebridgevas.android.ebridgeapp.widget.SecondLevelAdapter;

import java.util.ArrayList;

//import com.ebridgevas.android.ebridgeapp.MarketBrowserFragment;

public class ProductListFragment extends Fragment {

    private final static String TAG = "MainActivity";
    public static String ARG_INITIAL_POSITION = "ARG_INITIAL_POSITION";
    private ExpandableListView mExpandableListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_events_listview, container, false);
        mExpandableListView = (ExpandableListView) view.findViewById(R.id.marketSelectionList);

        Bundle args = getArguments();
        String providerCode = args.getString(ServicesFragment.ARG_PROVIDER_CODE);
        String serviceCode = args.getString(ServicesFragment.ARG_SERVICE_CODE);

        final ArrayList<ProductCategory> categories = EBridgeAppModel.getProductCategories(providerCode, serviceCode);

//        NetworkServiceStub networkService = ((EBridgeApplication)getActivity().getApplication()).getNetworkService();
//        Log.i("EventList", "#### parentId : " + parentId);
//        ArrayList<NodeData> countries = networkService.getChildren(parentId);
//        ArrayList<NodeData> nodes = new ArrayList<>();
//        for (NodeData country : countries) {
//            Log.i("EventList", "#### country id : " + country.getId() +
//                    ", country : " + country.getParams().get("node-text-top-left"));
//            ArrayList<NodeData> items = networkService.getChildren(country.getId());
//            for (NodeData item : items) {
//                Log.i("EventList", "#### league id : " + item.getId() +
//                        ", league : " + item.getParams().get("node-text-top-left"));
//            }
//            nodes.addAll(items);
//        }


//        ProductBrowserFragment parent = (ProductBrowserFragment) getParentFragment();
//        Log.i("TEST", "parent : " + parent);
//        ViewPager viewPager = parent.getPager();
//        Log.i("TEST", "viewPager : " + viewPager);

        SecondLevelAdapter adapter = new SecondLevelAdapter(getActivity(), categories);
        mExpandableListView.setAdapter(adapter);
//        mExpandableListView.setOnItemClickListener(new ExpandableListView.OnItemClickListener() {
//
//            @Override
//            public boolean onChildClick(
//                    ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                ProductCategory category = categories.get(groupPosition);
//                Product product = EBridgeAppModel.getProducts(
//                                    category.getProviderCode(), category.getServiceCode(), category.getCategoryCode())
//                                    .get(childPosition);
//                ArrayList<Sku> skus = product.getSkus();
//                Sku selected = null;
//                Log.i("TEST", "id : " + id);
//                switch ((int)id) {
//                    case R.id.productDescription1:
//                    case R.id.productValue1:
//                        selected = skus.get(0);
//                        break;
//                    case R.id.productDescription2:
//                    case R.id.productValue2:
//                        selected = skus.get(1);
//                        break;
//                    case R.id.productDescription3:
//                    case R.id.productValue3:
//                        selected = skus.get(2);
//                        break;
//
//                }
//                Toast.makeText(getContext(), "sku : " + selected, Toast.LENGTH_LONG).show();
//
//                return false;
//            }
//        });

        return view;
    }
}
