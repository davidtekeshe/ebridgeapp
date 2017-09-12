
package com.ebridgevas.android.ebridgeapp;

import android.content.ContentResolver;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.ebridgevas.android.ebridgeapp.database.ServicesContract;
import com.ebridgevas.android.ebridgeapp.model.ServiceDTO;

import java.util.ArrayList;

public abstract class BaseActivity extends AppCompatActivity {
    private static final int NUM_OF_ITEMS = 100;
    private static final int NUM_OF_ITEMS_FEW = 3;

    protected int getActionBarSize() {
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[]{R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedArray a = obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        return actionBarSize;
    }

    protected int getScreenHeight() {
        return findViewById(android.R.id.content).getHeight();
    }

    public static ArrayList<String> getDummyData() {
        return getDummyData(NUM_OF_ITEMS);
    }

    public static ArrayList<ServiceDTO> getDialogsData(Integer tabId, ContentResolver resolver) {


        /*
        switch(tabId) {

            case 0:

                return fillData(
                        Uri.parse("content://com.ebridgevas.android.ebridgeapp.contentprovider/services/'service_list'"), resolver);

            case 1:

                return fillData(
                        Uri.parse("content://com.ebridgevas.android.ebridgeapp.contentprovider/services/'chat_list'"), resolver);

            case 2:

                return fillData(
                        Uri.parse("content://com.ebridgevas.android.ebridgeapp.contentprovider/services/'service_provider_list'"), resolver);
            default:
                return null;
        }
        */

        return null;
    }

    public static ArrayList<String> getDummyData(int num) {
        ArrayList<String> items = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            items.add("Item " + i);
        }
        return items;
    }

    protected void setDummyData(ListView listView) {
        setDummyData(listView, NUM_OF_ITEMS);
    }

    protected void setDummyDataFew(ListView listView) {
        setDummyData(listView, NUM_OF_ITEMS_FEW);
    }

    protected void setDummyData(ListView listView, int num) {
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getDummyData(num)));
    }

    protected void setDummyDataWithHeader(ListView listView, int headerHeight) {
        setDummyDataWithHeader(listView, headerHeight, NUM_OF_ITEMS);
    }

    protected void setDummyDataWithHeader(ListView listView, int headerHeight, int num) {
        View headerView = new View(this);
        headerView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, headerHeight));
        headerView.setMinimumHeight(headerHeight);
        // This is required to disable header's list selector effect
        headerView.setClickable(true);
        setDummyDataWithHeader(listView, headerView, num);
    }

    protected void setDummyDataWithHeader(ListView listView, View headerView, int num) {
        listView.addHeaderView(headerView);
        setDummyData(listView, num);
    }

    protected void setDummyData(GridView gridView) {
        gridView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getDummyData()));
    }

    protected void setDummyData(RecyclerView recyclerView) {
        setDummyData(recyclerView, NUM_OF_ITEMS);
    }

    protected void setDummyDataFew(RecyclerView recyclerView) {
        setDummyData(recyclerView, NUM_OF_ITEMS_FEW);
    }

    protected void setDummyData(RecyclerView recyclerView, int num) {
        recyclerView.setAdapter(new SimpleRecyclerAdapter(this, getDummyData(num)));
    }

    protected void setDummyDataWithHeader(RecyclerView recyclerView, int headerHeight) {
        View headerView = new View(this);
        headerView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, headerHeight));
        headerView.setMinimumHeight(headerHeight);
        // This is required to disable header's list selector effect
        headerView.setClickable(true);
        setDummyDataWithHeader(recyclerView, headerView);
    }

    protected void setDummyDataWithHeader(RecyclerView recyclerView, View headerView) {
        recyclerView.setAdapter(new SimpleHeaderRecyclerAdapter(this, getDummyData(), headerView));
    }

    private static ArrayList<ServiceDTO> fillData(Uri uri, ContentResolver resolver){

        Log.i("MainActivity", "fillData() called");

        String[] projection = {
                ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_ID,
                ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_TITLE,
                ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_NARRATION,
                ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_AVATOR,
                ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_ACTION,
                ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_PROVIDER_ID,
                ServicesContract.ServiceEntry.COLUMN_NAME_DIALOG_TYPE

        };

        Cursor cursor = resolver.query(uri, projection, null, null, null);
        Log.i("Activity", "filling data, cursor : " + cursor);
        ArrayList<ServiceDTO> services = new ArrayList<>();
        while (cursor.moveToNext()) {

//            String category =
//                    cursor.getString(cursor.getColumnIndexOrThrow(ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_ID));
//
//            for (int i = 0; i < mCategory.getCount(); i++) {
//
//                String s = (String) mCategory.getItemAtPosition(i);
//                if (s.equalsIgnoreCase(category)) {
//                    mCategory.setSelection(i);
//                }
//            }

//            mTitleText.setText(
//                    cursor.getString(
//                            cursor.getColumnIndexOrThrow(ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_TITLE)));
//
//            mBodyText.setText(
//                    cursor.getString(
//                            cursor.getColumnIndexOrThrow(ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_NARRATION)));

            services.add(
                    new ServiceDTO(
                            cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                            ServicesContract.ServiceEntry.COLUMN_NAME_DIALOG_TYPE)),
                            cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                            ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_ID)),
                            cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                            ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_TITLE)),
                            cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                            ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_NARRATION)),
                            cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                            ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_AVATOR)),
                            cursor.getString(
                                    cursor.getColumnIndexOrThrow(
                                            ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_ACTION))));


            // always close the cursor

        }

        cursor.close();

        return services;
    }
}
