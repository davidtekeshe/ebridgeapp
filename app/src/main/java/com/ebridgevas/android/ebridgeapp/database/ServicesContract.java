package com.ebridgevas.android.ebridgeapp.database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

public final class ServicesContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public ServicesContract() {}

    public static abstract class ServiceEntry implements BaseColumns {
        public static final String TABLE_NAME = "service";
        public static final String COLUMN_NAME_SERVICE_ID = "serviceid";
        public static final String COLUMN_NAME_SERVICE_TITLE = "servicetitle";
        public static final String COLUMN_NAME_SERVICE_NARRATION = "servicenarration";
        public static final String COLUMN_NAME_SERVICE_AVATOR = "servicenavator";
        public static final String COLUMN_NAME_SERVICE_ACTION = "serviceaction";
        public static final String COLUMN_NAME_SERVICE_STATUS = "servicestatus";
        public static final String COLUMN_NAME_SERVICE_PROVIDER_ID = "serviceproviderid";
        public static final String COLUMN_NAME_DIALOG_TYPE = "dialogtype";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ServiceEntry.TABLE_NAME + " (" +
                ServiceEntry._ID + " INTEGER PRIMARY KEY, " +
                ServiceEntry.COLUMN_NAME_SERVICE_ID + TEXT_TYPE + COMMA_SEP +
                ServiceEntry.COLUMN_NAME_SERVICE_TITLE + TEXT_TYPE + COMMA_SEP +
                ServiceEntry.COLUMN_NAME_SERVICE_NARRATION + TEXT_TYPE + COMMA_SEP +
                ServiceEntry.COLUMN_NAME_SERVICE_AVATOR + TEXT_TYPE + COMMA_SEP +
                ServiceEntry.COLUMN_NAME_SERVICE_ACTION + TEXT_TYPE + COMMA_SEP +
                ServiceEntry.COLUMN_NAME_SERVICE_STATUS + TEXT_TYPE + COMMA_SEP +
                ServiceEntry.COLUMN_NAME_DIALOG_TYPE + TEXT_TYPE + COMMA_SEP +
                ServiceEntry.COLUMN_NAME_SERVICE_PROVIDER_ID + TEXT_TYPE  +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ServiceEntry.TABLE_NAME;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(ServicesContract.class.getName(), "Upgrading database from version "
                        + oldVersion + " to " + newVersion  + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + ServiceEntry.TABLE_NAME);
        onCreate(db);
    }
}
