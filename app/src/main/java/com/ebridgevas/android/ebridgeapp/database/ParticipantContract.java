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
public class ParticipantContract {

    private ParticipantContract() {
    }

    public static abstract class ParticipantEntry implements BaseColumns {
        public static final String TABLE_NAME = "market";
        public static final String COLUMN_NAME_MARKET_CLASS_ID = "market_class_id";
        public static final String COLUMN_NAME_MARKET_TYPE_ID = "market_type_id";
        public static final String COLUMN_NAME_MARKET_ID = "market_id";
        public static final String COLUMN_NAME_PARTICIPANT_ID = "participant_id";
        public static final String COLUMN_NAME_PARTICIPANT_NAME = "participant_name";
        public static final String COLUMN_NAME_ODDS = "odds";
        public static final String COLUMN_NAME_ODDS_DECIMAL = "odds_decimal";
    }


    private static final String TEXT_TYPE = " TEXT";
    private static final String DECIMAL_TYPE = " DECIMAL(5,2)";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ParticipantEntry.TABLE_NAME + " (" +
                    ParticipantEntry._ID + " INTEGER PRIMARY KEY, " +
                    ParticipantEntry.COLUMN_NAME_MARKET_CLASS_ID + TEXT_TYPE + COMMA_SEP +
                    ParticipantEntry.COLUMN_NAME_MARKET_TYPE_ID + TEXT_TYPE + COMMA_SEP +
                    ParticipantEntry.COLUMN_NAME_MARKET_ID + TEXT_TYPE + COMMA_SEP +
                    ParticipantEntry.COLUMN_NAME_PARTICIPANT_ID + TEXT_TYPE + COMMA_SEP +
                    ParticipantEntry.COLUMN_NAME_PARTICIPANT_NAME + TEXT_TYPE + COMMA_SEP +
                    ParticipantEntry.COLUMN_NAME_ODDS + TEXT_TYPE + COMMA_SEP +
                    ParticipantEntry.COLUMN_NAME_ODDS_DECIMAL + DECIMAL_TYPE +
                    " )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ParticipantEntry.TABLE_NAME;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(
                ParticipantContract.class.getName(), "Upgrading database from version "
                        + oldVersion + " to " + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + ParticipantEntry.TABLE_NAME);
        onCreate(db);
    }
}
