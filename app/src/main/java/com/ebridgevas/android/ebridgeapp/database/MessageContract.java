package com.ebridgevas.android.ebridgeapp.database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

public class MessageContract {

    private MessageContract(){
    }

    public static abstract class MessageEntry implements BaseColumns {
        public static final String TABLE_NAME = "messages";
        public static final String COLUMN_NAME_PARTNER_ID = "partnerid";
//        public static final String COLUMN_NAME_DIALOG_ID = "dialogid";
        public static final String COLUMN_NAME_MESSAGE_ID = "messageid";
        public static final String COLUMN_NAME_MESSAGE_TYPE = "messagetype";
        public static final String COLUMN_NAME_MESSAGE_TEXT = "messagetext";
        public static final String COLUMN_NAME_MESSAGE_STATUS = "messagestatus";
        public static final String COLUMN_NAME_MESSAGE_DATE = "messagedate";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String BIGINT_TYPE = " BIGINT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MessageEntry.TABLE_NAME + " (" +
                    MessageEntry._ID + " INTEGER PRIMARY KEY, " +
                    MessageEntry.COLUMN_NAME_PARTNER_ID + TEXT_TYPE + COMMA_SEP +
//                    MessageEntry.COLUMN_NAME_DIALOG_ID + BIGINT_TYPE + COMMA_SEP +
                    MessageEntry.COLUMN_NAME_MESSAGE_ID + BIGINT_TYPE + COMMA_SEP +
                    MessageEntry.COLUMN_NAME_MESSAGE_TYPE + TEXT_TYPE + COMMA_SEP +
                    MessageEntry.COLUMN_NAME_MESSAGE_TEXT + TEXT_TYPE + COMMA_SEP +
                    MessageEntry.COLUMN_NAME_MESSAGE_STATUS + TEXT_TYPE + COMMA_SEP +
                    MessageEntry.COLUMN_NAME_MESSAGE_DATE + BIGINT_TYPE  +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MessageEntry.TABLE_NAME;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(
                ServicesContract.class.getName(), "Upgrading database from version "
                        + oldVersion + " to " + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + MessageEntry.TABLE_NAME);
        onCreate(db);
    }
}
