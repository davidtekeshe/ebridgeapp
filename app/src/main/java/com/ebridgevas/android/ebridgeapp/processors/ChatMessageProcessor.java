//package com.ebridgevas.android.ebridgeapp.processors;
//
//import com.ebridgevas.android.ebridgeapp.MessageProcessor;
//import com.ebridgevas.android.ebridgeapp.model.Message;
//import com.ebridgevas.android.ebridgeapp.persistence.PersistenceService;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//
///**
// * Maintains a list of active messages
// *    i.e. a list of latest 100 messages.
// * When an active message is selected, all the prior messages
// *    from the peer are pulled from the database
// * New messages received from the wire are added on top the list,
// *    displacing existing or rearranging the list
// *
// * @author david@tekeshe.com
// */
//public class ChatMessageProcessor implements MessageProcessor{
//
//    private static final Integer MAXIMUM_ACTIVE_MESSAGES = 10;
//
//    private ArrayList<Message> activeMessageList;
//
//    public ChatMessageProcessor(PersistenceService persistenceService) {
//        this.activeMessageList = persistenceService.readChatMessages(MAXIMUM_ACTIVE_MESSAGES);
//        sort();
//    }
//
//    @Override
//    public void process(Message message) {
//        addMessage(message);
//        // does the participant dialog exist
//
//            // ack
//
//                // is chat screen for this participant opened
//
//                    // ack
//                        // ring a low tone bell
//                        // replace dialog message with this.message these nodes
//                        // notify nodesAdapter
//                        // reload list. new message at the top
//                        // getCurrentChatList and add this.message
//                        // notify chatAdapter
//
//        // nack
//            // ring a high tone bell
//            // replace dialog message with this.message these nodes
//            // notify nodesAdapter
//            // reload list. new message at the top
//            // increment counter on dialog entry
//            // increment counter on icon
//            // nack
//
//        // is participant in db
//
//        // ack
//        // ring a high tone bell
//        // load participant dialog. remove the 100th entry
//        // replace dialog message with this.message these nodes
//        // notify nodesAdapter
//        // reload list. new message at the top
//        // increment counter on dialog entry
//        // increment counter on icon
//
//        // nack
//        // ring a high tone bell
//        // add participant to db and flag as unauthorised
//        // load participant dialog. remove the 100th entry
//        // replace dialog message with this.message these nodes
//        // notify nodesAdapter
//        // reload list. new message at the top
//        // increment counter on dialog entry
//        // increment counter on icon
//
//        // on displat chat add: "Add Contact or Block Unknown"
//        // change status to "active" or "delete" participant
//
//    }
//
//    private void sort() {
//        Collections.sort(activeMessageList, new Comparator<Message>() {
//            @Override
//            public int compare(Message thisMessage, Message thatMessage) {
//                return thisMessage.getId() > thatMessage.getId() ? -1 : 1; // reverse
//            }
//        });
//    }
//
//    public void addMessage(Message message){
//
//        if (activeMessageList.contains(message)){
//            activeMessageList.remove(message);
//        } else {
//            int lastIdx = activeMessageList.size() - 1;
//            activeMessageList.remove(lastIdx);
//        }
//
//        activeMessageList.add(message);
//    }
//
//    public ArrayList<Message> getActiveMessageList() {
//        sort();
//        return activeMessageList;
//    }
//}
