package com.ebridgevas.android.ebridgeapp.database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

public class DialogsContract {

    private DialogsContract(){
    }

    public static abstract class DialogEntry implements BaseColumns {
        public static final String TABLE_NAME = "dialogs";
        public static final String COLUMN_NAME_DIALOG_ID = "dialogid";
        public static final String COLUMN_NAME_DIALOG_TITLE = "dialogtitle";
        public static final String COLUMN_NAME_DIALOG_NARRATION = "dialognarration";
        public static final String COLUMN_NAME_DIALOG_AVATOR = "dialogavator";
        public static final String COLUMN_NAME_DIALOG_STATUS = "dialogstatus";
        public static final String COLUMN_NAME_DIALOG_DATE = "dialogdate";
    }

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DialogEntry.TABLE_NAME + " (" +
                    DialogEntry._ID + " INTEGER PRIMARY KEY, " +
                    DialogEntry.COLUMN_NAME_DIALOG_ID + TEXT_TYPE + COMMA_SEP +
                    DialogEntry.COLUMN_NAME_DIALOG_TITLE + TEXT_TYPE + COMMA_SEP +
                    DialogEntry.COLUMN_NAME_DIALOG_NARRATION + TEXT_TYPE + COMMA_SEP +
                    DialogEntry.COLUMN_NAME_DIALOG_AVATOR + TEXT_TYPE + COMMA_SEP +
                    DialogEntry.COLUMN_NAME_DIALOG_STATUS + TEXT_TYPE + COMMA_SEP +
                    DialogEntry.COLUMN_NAME_DIALOG_DATE + TEXT_TYPE  +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DialogEntry.TABLE_NAME;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(
                ServicesContract.class.getName(), "Upgrading database from version "
                        + oldVersion + " to " + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + DialogEntry.TABLE_NAME);
        onCreate(db);
    }
}
