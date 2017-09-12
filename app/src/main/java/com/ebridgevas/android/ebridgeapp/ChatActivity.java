package com.ebridgevas.android.ebridgeapp;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ebridgevas.android.ebridgeapp.contentprovider.ServicesContentProvider;
import com.ebridgevas.android.ebridgeapp.database.ServicesContract;

/**
 * For capturing or editing a service entry
 */
public class ChatActivity extends Activity {
    private Spinner mCategory;
    private EditText mTitleText;
    private EditText mBodyText;

    private Uri serviceEntryUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        mCategory = (Spinner)findViewById(R.id.category);
        mTitleText = (EditText)findViewById(R.id.todo_edit_summary);
        mBodyText = (EditText)findViewById(R.id.todo_edit_description);
        Button confirmButton = (Button)findViewById(R.id.todo_edit_button);

        Bundle extras = getIntent().getExtras();

        // check from the saved instance
        serviceEntryUri = (savedInstanceState == null) ?
                null : (Uri)savedInstanceState.getParcelable(ServicesContentProvider.CONTENT_ITEM_TYPE);

        // or passed from the other activity
        if (extras != null) {
            serviceEntryUri = extras.getParcelable(ServicesContentProvider.CONTENT_ITEM_TYPE);

            fillData(serviceEntryUri);
        }

        confirmButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(mTitleText.getText().toString())) {
                            makeToast();
                        }
                        else {
                            setResult(RESULT_OK);
                            finish();
                        }
                    }
                });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState();
        outState.putParcelable(ServicesContentProvider.CONTENT_ITEM_TYPE, serviceEntryUri);
    }

    @Override
    protected void onPause(){
        super.onPause();
        saveState();
    }

    private void saveState(){
        String category = (String)mCategory.getSelectedItem();
        String summary = mTitleText.getText().toString();
        String description = mBodyText.getText().toString();

        // only save if either summary or description is available
        if (description.length() == 0 && summary.length() == 0) {
            return;
        }

        ContentValues values = new ContentValues();
        values.put(ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_ID, category);
        values.put(ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_TITLE, summary);
        values.put(ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_NARRATION, description);

        if (serviceEntryUri == null) {
            // new service
            serviceEntryUri = getContentResolver().insert(ServicesContentProvider.CONTENT_URI, values);
        } else {
            // update comment
            getContentResolver().update(serviceEntryUri, values, null, null);
        }
    }

    private void fillData(Uri uri){

        String[] projection = {
                ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_TITLE,
                ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_NARRATION,
                ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_PROVIDER_ID,
                ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_STATUS};

        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            String category =
                    cursor.getString(cursor.getColumnIndexOrThrow(ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_ID));

            for (int i = 0; i < mCategory.getCount(); i++) {

                String s = (String) mCategory.getItemAtPosition(i);
                if (s.equalsIgnoreCase(category)) {
                    mCategory.setSelection(i);
                }
            }

            mTitleText.setText(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_TITLE)));

            mBodyText.setText(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(ServicesContract.ServiceEntry.COLUMN_NAME_SERVICE_NARRATION)));

            // always close the cursor
            cursor.close();
        }
    }

    private void makeToast() {
        Toast.makeText(getApplicationContext(), "Please maintain the narration", Toast.LENGTH_LONG).show();
    }
}
