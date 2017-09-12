package com.ebridgevas.android.ebridgeapp.services.impl;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.util.Log;

import com.ebridgevas.android.ebridgeapp.contentprovider.MarketContentProvider;
import com.ebridgevas.android.ebridgeapp.contentprovider.MessageContentProvider;
import com.ebridgevas.android.ebridgeapp.contentprovider.ParticipantContentProvider;
import com.ebridgevas.android.ebridgeapp.database.MarketContract;
import com.ebridgevas.android.ebridgeapp.database.MessageContract;
import com.ebridgevas.android.ebridgeapp.database.ParticipantContract;
import com.ebridgevas.android.ebridgeapp.model.Market;
import com.ebridgevas.android.ebridgeapp.model.Message;
import com.ebridgevas.android.ebridgeapp.model.MessageType;
import com.ebridgevas.android.ebridgeapp.model.Participant;
import com.ebridgevas.android.ebridgeapp.util.NotificationCenter;
import com.google.gson.Gson;

import java.util.ArrayList;

import static com.ebridgevas.android.ebridgeapp.model.MessageType.*;

/**
 * @author david@tekeshe.com
 */
public class PersistenceServiceimpl  {

    public PersistenceServiceimpl() {
        NotificationCenter.getInstance().addObserver(new MessageReceiver(), NotificationCenter.MESSAGE_RECEIVED);
    }

//    private Uri serviceEntryUri;

    private class MessageReceiver implements NotificationCenter.NotificationCenterDelegate {

        @Override
        public void didReceivedNotification(int id, Object... args) {

            ContentResolver contentResolver = (ContentResolver) args[0];
            Message message = (Message) args[1];
            persist(contentResolver, message);
        }
    }

    public void persist(ContentResolver contentResolver, Message message) {

        switch(message.getMessageType()) {

            case CHAT_ENTRY:
                persistChatMessage(contentResolver, message);
                break;
            default:
                break;
        }
    }

    private void persistChatMessage(ContentResolver contentResolver, Message message) {

//        String json = message.getBody();
//        ArrayList<Market> markets = new Gson().fromJson(json, ArrayList.class);

//        for (Market market : markets) {
            ContentValues values = new ContentValues();
            values.put(MessageContract.MessageEntry.COLUMN_NAME_PARTNER_ID, message.getParticipantId());
//            values.put(MessageContract.MessageEntry.COLUMN_NAME_DIALOG_ID, message.getId());
            values.put(MessageContract.MessageEntry.COLUMN_NAME_MESSAGE_ID, message.getId());
            values.put(MessageContract.MessageEntry.COLUMN_NAME_MESSAGE_TYPE, message.getId());
            values.put(MessageContract.MessageEntry.COLUMN_NAME_MESSAGE_TEXT, message.getBody());
            values.put(MessageContract.MessageEntry.COLUMN_NAME_MESSAGE_STATUS, message.getStatus());
            values.put(MessageContract.MessageEntry.COLUMN_NAME_MESSAGE_DATE, message.getDate());

//            Uri uri = Uri.parse(MarketContentProvider.CONTENT_URI + "/" + id);
//            persist(contentResolver, market);
//            if (serviceEntryUri == null) {
            // new service
            Log.i("Persistence", "before db insert");
//            contentResolver.insert(MessageContentProvider.CONTENT_URI, values);
//            Log.i("Persistence", "after db insert : " + serviceEntryUri);
//        } else {
//            getContentResolver().update(serviceEntryUri, values, null, null);
    }

//    private void persistMarkets() {
//
//        String json = message.getBody();
//        ArrayList<Market> markets = new Gson().fromJson(json, ArrayList.class);
//
//        for (Market market : markets) {
//            ContentValues values = new ContentValues();
//            values.put(MarketContract.MarketEntry.COLUMN_NAME_MARKET_CLASS_ID, market.getClassId());
//            values.put(MarketContract.MarketEntry.COLUMN_NAME_MARKET_TYPE_ID, market.getTypeId());
//            values.put(MarketContract.MarketEntry.COLUMN_NAME_MARKET_ID, market.getId());
//            values.put(MarketContract.MarketEntry.COLUMN_NAME_MARKET_NAME, market.getName());
//            values.put(MarketContract.MarketEntry.COLUMN_NAME_MARKET_DATE, market.getDate().getTime());
//
////            Uri uri = Uri.parse(MarketContentProvider.CONTENT_URI + "/" + id);
//            persist(contentResolver, market);
////            if (serviceEntryUri == null) {
//            // new service
//            Log.i("Persistence", "before db insert");
//            contentResolver.insert(MarketContentProvider.CONTENT_URI, values);
////            Log.i("Persistence", "after db insert : " + serviceEntryUri);
////        } else {
////            getContentResolver().update(serviceEntryUri, values, null, null);
//    }

    private void persist(ContentResolver contentResolver, Market market) {

        ArrayList<Participant> participants = market.getParticipants();
        for (Participant participant : participants) {
            ContentValues values = new ContentValues();

            values.put(ParticipantContract.ParticipantEntry.COLUMN_NAME_MARKET_CLASS_ID, market.getClassId());
            values.put(ParticipantContract.ParticipantEntry.COLUMN_NAME_MARKET_TYPE_ID, market.getTypeId());
            values.put(ParticipantContract.ParticipantEntry.COLUMN_NAME_MARKET_ID, market.getId());

            values.put(ParticipantContract.ParticipantEntry.COLUMN_NAME_PARTICIPANT_ID, participant.getId());
            values.put(ParticipantContract.ParticipantEntry.COLUMN_NAME_PARTICIPANT_NAME, participant.getName());
            values.put(ParticipantContract.ParticipantEntry.COLUMN_NAME_ODDS, participant.getOdds());
            values.put(
                    ParticipantContract.ParticipantEntry.COLUMN_NAME_ODDS_DECIMAL,
                    participant.getOddsDecimal().doubleValue());

            contentResolver.insert(ParticipantContentProvider.CONTENT_URI, values);
        }
    }
}
