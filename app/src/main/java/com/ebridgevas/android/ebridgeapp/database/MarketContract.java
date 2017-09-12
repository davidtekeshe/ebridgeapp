package com.ebridgevas.android.ebridgeapp.database;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * @author david@tekeshe.com
 *
 */
        /*
        market:

        private String classId;
        private String typeId;
        private String id;
        private Date date;
        private String name;

        List of Participants:

        private String id;
        private String marketId;
        private String name;
        private String odds;
        private BigDecimal oddsDecimal;

        create table market (market_class_id varchar(20), market_type_id varchar(20), market_id varchar(20),
                market_date long, market_name varchar(120), primary key (market_class_id, market_type_id, market_id)
        create table participant (market_class_id varchar(20), market_type_id varchar(20), market_id varchar(20),
                participant_id varchar(20), participant_name varchar(120), odds varchar(10), odds_decimal decimal (5,2),
                primary key (market_class_id, market_type_id, market_id, participant_id)
        */
public class MarketContract {

    private MarketContract() {
    }

    public static abstract class MarketEntry implements BaseColumns {
        public static final String TABLE_NAME = "market";
        public static final String COLUMN_NAME_MARKET_CLASS_ID = "market_class_id";
        public static final String COLUMN_NAME_MARKET_TYPE_ID = "market_type_id";
        public static final String COLUMN_NAME_MARKET_ID = "market_id";
        public static final String COLUMN_NAME_MARKET_DATE = "market_date";
        public static final String COLUMN_NAME_MARKET_NAME = "market_name";
    }


    private static final String TEXT_TYPE = " TEXT";
    private static final String BIGINT_TYPE = " BIGINT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MarketEntry.TABLE_NAME + " (" +
                    MarketEntry._ID + " INTEGER PRIMARY KEY, " +
                    MarketEntry.COLUMN_NAME_MARKET_CLASS_ID + TEXT_TYPE + COMMA_SEP +
                    MarketEntry.COLUMN_NAME_MARKET_TYPE_ID + TEXT_TYPE + COMMA_SEP +
                    MarketEntry.COLUMN_NAME_MARKET_ID + TEXT_TYPE + COMMA_SEP +
                    MarketEntry.COLUMN_NAME_MARKET_DATE + BIGINT_TYPE + COMMA_SEP +
                    MarketEntry.COLUMN_NAME_MARKET_NAME + TEXT_TYPE +
                    " )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MarketEntry.TABLE_NAME;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(
                MarketContract.class.getName(), "Upgrading database from version "
                        + oldVersion + " to " + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + MarketEntry.TABLE_NAME);
        onCreate(db);
    }
}
