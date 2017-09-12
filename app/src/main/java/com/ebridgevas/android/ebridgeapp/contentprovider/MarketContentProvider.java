package com.ebridgevas.android.ebridgeapp.contentprovider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.ebridgevas.android.ebridgeapp.database.DialogsContract;
import com.ebridgevas.android.ebridgeapp.database.MarketContract;
import com.ebridgevas.android.ebridgeapp.database.ServicesDbHelper;

import java.util.Arrays;
import java.util.HashSet;

public class MarketContentProvider extends ContentProvider {

    private ServicesDbHelper mDbHelper;

    // For UriMatcher
    private static final int MARKET = 31;
    private static final int MARKET_ID = 41;

    private static final String AUTHORITY = "com.ebridgevas.android.ebridgeapp.contentprovider";

    private static final String BASE_PATH = "market";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    private static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/market";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/market";

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, MARKET);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", MARKET_ID);
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new ServicesDbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        // Using SQLIteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // check if the caller has requested a column which does not exists
        checkColumns(projection);

        // Set the table
        queryBuilder.setTables(MarketContract.MarketEntry.TABLE_NAME);

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case MARKET:
                break;
            case MARKET_ID:
                // adding the ID to the orginal query
                queryBuilder.appendWhere(MarketContract.MarketEntry.COLUMN_NAME_MARKET_ID + "="
                    + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        // make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = mDbHelper.getWritableDatabase();
        long id = 0;
        switch (uriType) {
            case MARKET:
                id = sqlDB.insert(MarketContract.MarketEntry.TABLE_NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = mDbHelper.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case MARKET:
                rowsDeleted = sqlDB.delete(MarketContract.MarketEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case MARKET_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(
                            MarketContract.MarketEntry.TABLE_NAME,
                            MarketContract.MarketEntry.COLUMN_NAME_MARKET_ID + "=" + id, null);
                } else {
                    rowsDeleted = sqlDB.delete(MarketContract.MarketEntry.TABLE_NAME,
                            MarketContract.MarketEntry.COLUMN_NAME_MARKET_ID + "=" + id +
                            " and " + selection, selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknow URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = mDbHelper.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType){
            case MARKET:
                rowsUpdated = sqlDB.update(MarketContract.MarketEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case MARKET_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(MarketContract.MarketEntry.TABLE_NAME, values,
                            MarketContract.MarketEntry.COLUMN_NAME_MARKET_ID + "=" + id, null);
                } else {
                    rowsUpdated = sqlDB.update(MarketContract.MarketEntry.TABLE_NAME,
                            values, MarketContract.MarketEntry.COLUMN_NAME_MARKET_ID + "=" + id +
                            " and " + selection, selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    private void checkColumns(String[] projection) {
        String[] available = {
                MarketContract.MarketEntry.COLUMN_NAME_MARKET_ID,
                MarketContract.MarketEntry.COLUMN_NAME_MARKET_CLASS_ID,
                MarketContract.MarketEntry.COLUMN_NAME_MARKET_TYPE_ID,
                MarketContract.MarketEntry.COLUMN_NAME_MARKET_DATE,
                MarketContract.MarketEntry.COLUMN_NAME_MARKET_NAME
        };

        if (projection != null) {
            HashSet<String> requestedColumns = new HashSet<>(Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<>(Arrays.asList(available));

            // check if all columns which are requested are available
            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException("Unknown columns in projection");
            }
        }
    }
}
