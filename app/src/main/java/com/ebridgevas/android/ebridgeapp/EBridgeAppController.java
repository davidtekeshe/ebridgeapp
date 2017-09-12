//package com.ebridgevas.android.ebridgeapp;
//
//    import com.ebridgevas.android.ebridgeapp.model.Message;
//    import com.ebridgevas.android.ebridgeapp.util.SequentialIDGenerator;
//
//    import java.util.*;
//
///**
// * EBridgeApp Controller
// *
// * Knows how to:
// * - initialise eBridge App
// *   - read config data to setup main activity with its tabs:
// *      yellow pages, chat, services (default or selected)
// *   - setup each service category
// *      - yellow pages
// *      - chats
// *      - services
// *   - process incoming data by delegating to relevant service category
// *      - detect service category for incoming message
// *      - perform pre-processing
// *      - call relevant processor
// *
// * @author david@tekeshe.com
// */
//public class EBridgeAppController {
//
//    private final static List<String> TEST_USER_IDS;
//    static {
//        TEST_USER_IDS = new ArrayList<>();
////        TEST_USER_IDS.add("263733803480");
////        TEST_USER_IDS.add("263736194422");
////        TEST_USER_IDS.add("263733661588");
//
//        for (int idx = 0; idx < 200; ++idx) {
//            TEST_USER_IDS.add("263733803480" + idx);
//        }
//    }
//
//    private static List<Message> databaseMessages = new ArrayList<>();
//    private static List<Message> latestMessages = new ArrayList<>();
//
//    public void init(){
//
//        // init chat system
//
//        // init yellow pages
//
//        // init services
//    }
//
//    public void loadData() {
//
//        // load chat init data
//        latestMessages = new ArrayList<>();
//        SequentialIDGenerator generator = SequentialIDGenerator.instance(0);
//        for (long idx = 0; idx < 100; idx++) {
//            long id = generator.generate();
//            String to = "27729745087";
////            int random = new Random().nextInt(3);
//            String from = TEST_USER_IDS.get((int)idx);
//
//            Message message = new Message(id, from, to,  "message #" + id);
//
//            if (latestMessages.contains(message)) {
//                latestMessages.remove(message);
//            }
//
//            latestMessages.add(message);
//        }
//
//        sort();
//
//        for (long idx = 100; idx < 200; idx++) {
//            long id = generator.generate();
//            String to = "27729745087";
////            int random = new Random().nextInt(3);
//            String from = TEST_USER_IDS.get((int) idx);
//
//            Message message = new Message(id, from, to, "message #" + id);
//
//            if (databaseMessages.contains(message)) {
//                databaseMessages.remove(message);
//            }
//
//            databaseMessages.add(message);
//        }
//
//        // load yellow pages
//
//        // load services
//
//    }
//
//    private static void sort() {
//        MessageComparator comparator = new MessageComparator();
//        Collections.sort(latestMessages, comparator);
//    }
//
//    private static class MessageComparator implements Comparator<Message> {
//
//        @Override
//        public int compare(Message thisMessage, Message thatMessage) {
//            return thisMessage.getId() >= thatMessage.getId() ? -1 : 1;
//        }
//    }
//
//    public static boolean isFirstMessageInMemory(Message message) {
//
//        return ! latestMessages.contains(message);
//    }
//
//    public static boolean isFirstMessageInDatabase(Message message) {
//
//        return ! databaseMessages.contains(message);
//    }
//
//    public static void replaceMessage(Message message) {
//        if ( ! isFirstMessageInMemory(message)) {
//            latestMessages.remove(message);
//            latestMessages.add(message);
//        } else if (! isFirstMessageInDatabase(message)) {
//            addMessage(message);
//        } else {
//            addMessage(message);
//        }
//
//        sort();
//    }
//
//    public static void addMessage(Message message) {
//        if (latestMessages.size() == 100) {
//            Message messageToRemove = latestMessages.get(99);
//            latestMessages.remove(messageToRemove);
//        }
//        latestMessages.add(message);
//        sort();
//    }
//
//    public static void main(String[] args) {
//        EBridgeAppController controller = new EBridgeAppController();
//        controller.loadData();
//        for (Message message : latestMessages) {
//            System.out.println(message);
//        }
//
//        SequentialIDGenerator generator = SequentialIDGenerator.instance(100);
//        long id = generator.generate();
//        System.out.println("new id : " + id);
//        Message message = new Message(id, "263733803480151", "27729745087", "message #100");
//        boolean isFirstMessage = isFirstMessageInMemory(message);
//        System.out.println("isFirstMessage : " + isFirstMessage);
//        replaceMessage(message);
//
//        for (Message m : latestMessages) {
//            System.out.println(m);
//        }
//    }
//
//}
//
