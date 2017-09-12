package com.ebridgevas.android.ebridgeapp;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.ebridgevas.android.ebridgeapp.contentprovider.ServicesContentProvider;
import com.ebridgevas.android.ebridgeapp.database.ServicesContract;

public class DialogsActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int ACTIVITY_CREATE = 0;
    private static final int ACTIVITY_EDIT = 1;
    private static final int DELETE_ID = Menu.FIRST + 1;

    private SimpleCursorAdapter mAdapter;

    /** Called when the activity is first created.*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dialogs);
        this.getListView().setDividerHeight(2);
        fillData();
        registerForContextMenu(getListView());
    }

    // create the menu based on the XML definition
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    // Reaction to the menu selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
        switch(item.getItemId()) {
            case R.id.action_insert:
                createServiceItem();
                return true;

        }
        */
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case DELETE_ID:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
                Uri uri = Uri.parse(ServicesContentProvider.CONTENT_URI + "/" + info.id);
                getContentResolver().delete(uri, null, null);
                fillData();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void createServiceItem() {
        Intent i = new Intent(this, ChatActivity.class);
        startActivity(i);
    }

    // Opens the second activity if an entry is clicked
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent i = new Intent(this, ChatActivity.class);
        Uri uri = Uri.parse(ServicesContentProvider.CONTENT_URI + "/" + id);
        i.putExtra(ServicesContentProvider.CONTENT_ITEM_TYPE, uri);

        startActivity(i);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, DELETE_ID, 0, R.string.menu_delete);
    }

    // creates a new loader after the initLoader() call
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_ID,
                ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_TITLE,
                ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_NARRATION,
                ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_PROVIDER_ID,
                ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_STATUS};

        CursorLoader cursorLoader
                = new CursorLoader(this, ServicesContentProvider.CONTENT_URI, projection, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // data is not available anymore, delete reference
        mAdapter.swapCursor(null);
    }

    private void fillData() {

        // Fields from the database (projection)
        // Must include the _id column for the adapter to work
        String[] from = new String[] {
                ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_TITLE
//                ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_NARRATION,
//                ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_PROVIDER_ID,
//                ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_STATUS
            };

        // Fields on the UI to which we map
        int[] to = new int[]{};

        getLoaderManager().initLoader(0, null, this);
        mAdapter = new SimpleCursorAdapter(this, R.layout.activity_dialogs_row, null, from, to, 0);
        setListAdapter(mAdapter);
    }
}
