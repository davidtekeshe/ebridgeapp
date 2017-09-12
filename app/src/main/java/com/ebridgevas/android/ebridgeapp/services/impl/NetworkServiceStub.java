package com.ebridgevas.android.ebridgeapp.services.impl;

import com.ebridgevas.android.ebridgeapp.data.ServiceRequest;
import com.ebridgevas.android.ebridgeapp.data.ServiceResponse;
import com.ebridgevas.android.ebridgeapp.model.NodeData;
import com.ebridgevas.android.ebridgeapp.model.ServiceDTO;
import com.ebridgevas.android.ebridgeapp.model.Tree;
import com.ebridgevas.android.ebridgeapp.services.ApiService;
import com.ebridgevas.android.ebridgeapp.util.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkServiceStub implements ApiService {

    private Tree<NodeData> tree;

    private TreeNode<ServiceDTO> mTree;
    private Map<String, NodeData> data;

    public ArrayList<NodeData> getChildren(String parentId) {
        NodeData node = getParent(parentId);
        ArrayList<NodeData> children = new ArrayList<>(tree.getSuccessors(node));
        Collections.sort(children, new NodeData.NodeDataComparator());

        return children;
    }

    public Integer getChildrenCount(String parentId) {
        NodeData node = getParent(parentId);
        return tree.getSuccessors(node).size();
    }

    public NodeData getParent(String parentId) {
        NodeData node = null;
        if (parentId == null) {
            node = tree.getHead();
        } else {
            NodeData element = new NodeData(parentId);
            node = tree.getTree(element).getHead();
        }

        return node;
    }

    public NetworkServiceStub() {
        tree = buildTree();
//        String json = "{'dialogType':'root','serviceId':'60096119611','serviceTitle':'eBridge','narration':'eBridge App','avator':'avator','action':'action'}";
//        mTree = new TreeNode<>(new Gson().fromJson(json, ServiceDTO.class));
//        json = "{'dialogType':'yellow_page_listing','serviceId':'600961112352','serviceTitle':'eBridge','narration':'Registra of this Yellow Page','avator':'ebridge','action':'action'}";
//        mTree.addChild(new Gson().fromJson(json, ServiceDTO.class));
//
//        json = "{'dialogType':'yellow_page_listing','serviceId':'600961112353','serviceTitle':'Chitatai','narration':'Chemical Engineers','avator':'chitaitai','action':'action'}";
//        mTree.addChild(new Gson().fromJson(json, ServiceDTO.class));
//
//        json = "{'dialogType':'yellow_page_listing','serviceId':'600961112354','serviceTitle':'Jos Chickens','narration':'Leading Chicken Abboitors','avator':'joschickens','action':'action'}";
//        mTree.addChild(new Gson().fromJson(json, ServiceDTO.class));
//
//        json = "{'dialogType':'yellow_page_listing','serviceId':'600961112355','serviceTitle':'MmCellink','narration':'Mobile phone shop','avator':'mmcellink','action':'action'}";
//        mTree.addChild(new Gson().fromJson(json, ServiceDTO.class));
//
//        json = "{'dialogType':'yellow_page_listing','serviceId':'600961112356','serviceTitle':'Telecel','narration':'Mobile Communications','avator':'telecel','action':'action'}";
//        TreeNode<ServiceDTO> telecel = mTree.addChild(new Gson().fromJson(json, ServiceDTO.class));
//
//        json = "{'dialogType':'yellow_page_listing','serviceId':'600961112360','serviceTitle':'Mobile Account Manager','narration':'Data Bundles, Airtime Transfer, Balances','avator':'telecel','action':'action'}";
//        TreeNode<ServiceDTO> telecelBundles = telecel.addChild(new Gson().fromJson(json, ServiceDTO.class));
//
//        json = "{'dialogType':'yellow_page_listing','serviceId':'600961112361','serviceTitle':'Balance','narration':'','avator':'','action':''}";
//        TreeNode<ServiceDTO> balance = telecelBundles.addChild(new Gson().fromJson(json, ServiceDTO.class));
//
//        json = "{'dialogType':'dynamic_list_item','serviceId':'600961112365','serviceTitle':'Main Account','narration':'','avator':'','action':'/ebridge/services/balanceEnquiry'}";
//        TreeNode<ServiceDTO> balance1 = balance.addChild(new Gson().fromJson(json, ServiceDTO.class));
//
//        json = "{'dialogType':'dynamic_list_item','serviceId':'600961112366','serviceTitle':'Data Bundle','narration':'','avator':'','action':'/ebridge/services/balanceEnquiry'}";
//        TreeNode<ServiceDTO> balance2 = balance.addChild(new Gson().fromJson(json, ServiceDTO.class));
//
//        json = "{'dialogType':'dynamic_list_item','serviceId':'600961112367','serviceTitle':'Facebook Bundle','narration':'','avator':'','action':'/ebridge/services/balanceEnquiry'}";
//        TreeNode<ServiceDTO> balance3 = balance.addChild(new Gson().fromJson(json, ServiceDTO.class));
//
//        json = "{'dialogType':'dynamic_list_item','serviceId':'600961112368','serviceTitle':'Whatsapp Bundle','narration':'','avator':'','action':'/ebridge/services/balanceEnquiry'}";
//        TreeNode<ServiceDTO> balance4 = balance.addChild(new Gson().fromJson(json, ServiceDTO.class));
//
//        json = "{'dialogType':'yellow_page_listing','serviceId':'600961112362','serviceTitle':'Bundles','narration':'','avator':'','action':''}";
//        TreeNode<ServiceDTO> bundles = telecelBundles.addChild(new Gson().fromJson(json, ServiceDTO.class));
//
//        json = "{'dialogType':'yellow_page_listing','serviceId':'600961112363','serviceTitle':'Transfer','narration':'','avator':'','action':''}";
//        TreeNode<ServiceDTO> transfer = telecelBundles.addChild(new Gson().fromJson(json, ServiceDTO.class));
//
//        json = "{'dialogType':'yellow_page_listing','serviceId':'600961112364','serviceTitle':'Buy','narration':'','avator':'','action':''}";
//        TreeNode<ServiceDTO> buy = telecelBundles.addChild(new Gson().fromJson(json, ServiceDTO.class));
//
//        json = "{'dialogType':'yellow_page_listing','serviceId':'600961112357','serviceTitle':'Africom','narration':'Data Communications','avator':'africom','action':'action'}";
//        mTree.addChild(new Gson().fromJson(json, ServiceDTO.class));
//
//        json = "{'dialogType':'yellow_page_listing','serviceId':'600961112358','serviceTitle':'Soccer Shop','narration':'Make money through betting','avator':'soccer_shop','action':'action'}";
//        mTree.addChild(new Gson().fromJson(json, ServiceDTO.class));

//        String json = "[ {'parentId':'0',          'dialogType':'root','serviceId':'60096119611','serviceTitle':'eBridge','narration':'eBridge App','avator':'avator','action':'action'}, " +
//                        "{'parentId':'60096119611','dialogType':'yellow_page_listing','serviceId':'600961112352','serviceTitle':'eBridge','narration':'Registra of this Yellow Page','avator':'ebridge','action':'action'}," +
//                        "{'parentId':'60096119611','dialogType':'yellow_page_listing','serviceId':'600961112353','serviceTitle':'Chitatai','narration':'Chemical Engineers','avator':'chitaitai','action':'action'}," +
//                        "{'parentId':'60096119611','dialogType':'yellow_page_listing','serviceId':'600961112354','serviceTitle':'Jos Chickens','narration':'Leading Chicken Abboitors','avator':'joschickens','action':'action'}," +
//                        "{'parentId':'60096119611','dialogType':'yellow_page_listing','serviceId':'600961112355','serviceTitle':'MmCellink','narration':'Mobile phone shop','avator':'mmcellink','action':'action'}," +
//                        "{'parentId':'60096119611','dialogType':'yellow_page_listing','serviceId':'600961112356','serviceTitle':'Telecel','narration':'Mobile Communications','avator':'telecel','action':'action'}," +
//
//                        "{'parentId':'600961112356','dialogType':'yellow_page_listing','serviceId':'600961112360','serviceTitle':'Mobile Account Manager','narration':'Data Bundles, Airtime Transfer, Balances','avator':'telecel','action':'action'}," +
//
//                        "{'parentId':'600961112360','dialogType':'yellow_page_listing','serviceId':'600961112361','serviceTitle':'Balance','narration':'','avator':'','action':''}," +
//
//                        "{'parentId':'600961112361','dialogType':'dynamic_list_item','serviceId':'600961112365','serviceTitle':'Main Account','narration':'','avator':'','action':'/ebridge/services/balanceEnquiry'}," +
//                        "{'parentId':'600961112361','dialogType':'dynamic_list_item','serviceId':'600961112366','serviceTitle':'Data Bundle','narration':'','avator':'','action':'/ebridge/services/balanceEnquiry'}," +
//                        "{'parentId':'600961112361','dialogType':'dynamic_list_item','serviceId':'600961112367','serviceTitle':'Facebook Bundle','narration':'','avator':'','action':'/ebridge/services/balanceEnquiry'}," +
//                        "{'parentId':'600961112361','dialogType':'dynamic_list_item','serviceId':'600961112368','serviceTitle':'Whatsapp Bundle','narration':'','avator':'','action':'/ebridge/services/balanceEnquiry'}," +
//
//                        "{'parentId':'600961112360','dialogType':'yellow_page_listing','serviceId':'600961112362','serviceTitle':'Bundles','narration':'','avator':'','action':''}," +
//                        "{'parentId':'600961112360','dialogType':'yellow_page_listing','serviceId':'600961112363','serviceTitle':'Transfer','narration':'','avator':'','action':''}," +
//                        "{'parentId':'600961112360','dialogType':'yellow_page_listing','serviceId':'600961112364','serviceTitle':'Buy','narration':'','avator':'','action':''}," +
//                        "{'parentId':'60096119611','dialogType':'yellow_page_listing','serviceId':'600961112357','serviceTitle':'Africom','narration':'Data Communications','avator':'africom','action':'action'}," +
//                        "{'parentId':'60096119611','dialogType':'yellow_page_listing','serviceId':'600961112358','serviceTitle':'Soccer Shop','narration':'Make money through betting','avator':'soccer_shop','action':'action'}]";

//        json = "{'600961112356':['600961112360'],'600961112361':['600961112365','600961112366','600961112367','600961112368'],'600961119611':['600961112352','600961112353','600961112354','600961112355','600961112356','600961112357','600961112358'],'600961112360':['600961112361','600961112362','600961112363','600961112364']}";
//        map= new HashMap<>(); // new Gson().fromJson(json, Map.class);
//        map.put("600-000001-0001", Arrays.asList(new String[]{"600-000001-0002", "600-000002-0001", "600-000003-0001", "600-000004-0001", "600-000005-0001", "600-000006-0001", "600-000007-0001"}));
//        map.put("600961112356", Arrays.asList(new String[]{"600961112360"}));
//        map.put("600961112360", Arrays.asList(new String[]{"600961112361","600961112362", "600961112363", "600961112364"}));
//        map.put("600961112361", Arrays.asList(new String[]{"600961112365", "600961112366", "600961112367", "600961112368"}));

//        System.out.println(new Gson().toJson(map));

//        json = "{'600961112358':{'id':'600961112358','parentId':'60096119611','dataType':1,'params':{'title':'Soccer Shop','narration':'Make money through betting','avator':'telecel'}},'600961112356':{'id':'600961112356','parentId':'60096119611','dataType':1,'params':{'title':'Telecel','narration':'Mobile Communications','avator':'telecel'}}}";
//        data = new HashMap<>(); // new Gson().fromJson(json, Map.class);

        /*
        // ROOT NODE
        Map<String, String> params = new HashMap<>();
        data.put("600-0000001-0001", new NodeData("600-000000-0000", null, NodeType.ROOT, params, 0L));


        params = new HashMap<>();
        params.put("title", "SERVICES");
        params.put("sub-title", "");
        params.put("avator", "");
        data.put("600-000001-0000", new NodeData("600-000001-0002", "600-000001-0001", NodeType.YELLOW_PAGE_ENTRY, params, 20L));

        // LEVEL 1 NODES


        params = new HashMap<>();
        params.put("title", "eBridge");
        params.put("sub-title", "This mobile app developers");
        params.put("avator", "ebridge");
        data.put("600-000001-0002", new NodeData("600-000001-0002", "600-000001-0001", NodeType.YELLOW_PAGE_ENTRY, params, 20L));

        params = new HashMap<>();
        params.put("title", "Telecel");
        params.put("sub-title", "Mobile Communications");
        params.put("avator", "telecel");
        data.put("600-000002-0001", new NodeData("600-000002-0001", "600-000001-0001", NodeType.YELLOW_PAGE_ENTRY, params, 21L));

        params = new HashMap<>();
        params.put("title", "Soccer Shop");
        params.put("sub-title", "Make money through betting");
        params.put("avator", "soccer_shop");
        data.put(
                "600-000003-0001",
                new NodeData("600-000003-0001", "600-000001-0001", NodeType.YELLOW_PAGE_ENTRY, params, 22L));

        params = new HashMap<>();
        params.put("title", "Chitatai");
        params.put("sub-title", "Chemical Engineers");
        params.put("avator", "chitaitai");
        data.put(
                "600-000004-0001",
                new NodeData("600-000004-0001", "600-000001-0001", NodeType.YELLOW_PAGE_ENTRY, params, 23L));


        params = new HashMap<>();
        params.put("title", "Jos Chickens");
        params.put("sub-title", "Leading Chicken Abboitors");
        params.put("avator", "joschickens");
        data.put(
                "600-000005-0001",
                new NodeData("600-000005-0001", "600-000001-0001", NodeType.YELLOW_PAGE_ENTRY, params, 24L));

        params = new HashMap<>();
        params.put("title", "MmCellink");
        params.put("sub-title", "Mobile Phone Shop");
        params.put("avator", "mmcellink");
        data.put(
                "600-000006-0001",
                new NodeData("600-000006-0001", "600-000001-0001", NodeType.YELLOW_PAGE_ENTRY, params, 25L));

        params = new HashMap<>();
        params.put("title", "Africom");
        params.put("sub-title", "Data Communications");
        params.put("avator", "africom");
        data.put(
                "600-000007-0001",
                new NodeData("600-000007-0001", "600-000001-0001", NodeType.YELLOW_PAGE_ENTRY, params, 26L));

        params = new HashMap<>();
        params.put("title", "Mobile Account Manager");
        params.put("sub-title", "Data Bundles, Airtime Transfer, Balances");
        params.put("avator", "telecel");
        data.put("600-000002-0002", new NodeData("600-000002-0002", "600-000002-0001", NodeType.SERVICE_LIST_ENTRY, params, 0L));

        // Telecel Account Manager Menu
        params = new HashMap<>();
        params.put("title", "Balance");
        data.put("600-000002-0003", new NodeData("600-000002-0003", "600-000002-0002", NodeType.SERVICE_MENU_ENTRY, params, 1L));

        params = new HashMap<>();
        params.put("title", "Bundles");
        data.put("600-000002-0004", new NodeData("600-000002-0004", "600-000002-0002", NodeType.SERVICE_MENU_ENTRY, params, 2L));

        params = new HashMap<>();
        params.put("title", "Transfer");
        data.put("600-000002-0005", new NodeData("600-000002-0005", "600-000002-0002", NodeType.SERVICE_MENU_ENTRY, params, 3L));

        params = new HashMap<>();
        params.put("title", "Buy");
        data.put("600-000002-0006", new NodeData("600-000002-0006", "600-000002-0002", NodeType.SERVICE_MENU_ENTRY, params, 4L));

        // Balance enquiry entries
        params = new HashMap<>();
        params.put("title", "Main Account");
        params.put("balance", "$12.22");
        params.put("expiryDate", "7 July 16");
        params.put("lastUpdated", "8 June 23:33");
        data.put("600-000002-0007", new NodeData("600-000002-0007", "600-000002-0003", NodeType.BALANCE_ENQUIRY_ENTRY, params, 5L));

        params.put("title", "Data Bundle");
        params.put("balance", "12MB");
        params.put("expiryDate", "7 July 16");
        params.put("lastUpdated", "8 June 23:33");
        data.put("600-000002-0008", new NodeData("600-000002-0008", "600-000002-0003", NodeType.BALANCE_ENQUIRY_ENTRY, params, 6L));

        params.put("title", "Facebook Bundle");
        params.put("balance", "112MB");
        params.put("expiryDate", "7 July 16");
        params.put("lastUpdated", "8 June 23:33");
        data.put("600-000002-0009", new NodeData("600-000002-0009", "600-000002-0003", NodeType.BALANCE_ENQUIRY_ENTRY, params, 7L));

        params.put("title", "Whatsapp Bundle");
        params.put("balance", "112MB");
        params.put("expiryDate", "7 July 16");
        params.put("lastUpdated", "8 June 23:33");
        data.put(
                "600-000002-0010", new NodeData(
                        "600-000002-0010", "600-000002-0003", NodeType.BALANCE_ENQUIRY_ENTRY, params, 8L));

//        System.out.println("\ndata:\n" + new Gson().toJson(data));

//        Map<String, List<String>> index = map(data);
//        System.out.println("\n\nindex\n" + new Gson().toJson(getMap()));

        params = new HashMap<>();
        params.put("title", "Mai Nicole");
        params.put("sub-title", "Muri sei");
        params.put("avator", "mercy");
        data.put("600-000001-0021", new NodeData("600-000001-0021", "600-000001-0020", NodeType.YELLOW_PAGE_ENTRY, params, 0L));

        params = new HashMap<>();
        params.put("title", "Feli");
        params.put("sub-title", "Ndeipi");
        params.put("avator", "feli");
        data.put("600-000002-0022", new NodeData("600-000002-0022", "600-000001-0020", NodeType.YELLOW_PAGE_ENTRY, params, 1L));

        params = new HashMap<>();
        params.put("title", "George Tekeshe");
        params.put("sub-title", "Hi");
        params.put("avator", "mercy");
        data.put("600-000003-0023",new NodeData("600-000003-0023", "600-000001-0020", NodeType.YELLOW_PAGE_ENTRY, params, 2L));
        */
    }

    @Override
    public Map<String, NodeData> getData() {
        return data;
    }

    @Override
    public Map<String, List<String>> getMap() {

        Map<String, List<String>> map = new HashMap<>();
        for (NodeData ChildNodeData : data.values()) {
            List<String> children = map.get(ChildNodeData.getParentId());
            if (children == null) {
                children = new ArrayList<>();
                map.put(ChildNodeData.getParentId(), children);
            }
            children.add(ChildNodeData.getId());
        }

        return map;
    }

    public Tree<NodeData> getTree() {
        return tree;
    }

    /*
    private static final Map<ServiceRequest, ServiceResponse> SERVICE_DATA;
    private static final Map<ServiceRequest, ServiceResponse> MATCH_BETTING_MARKETS;
    private static final Map<ServiceRequest, ServiceResponse> ALL_MARKETS;

    static {
        SERVICE_DATA = new HashMap<>();
        ServiceResponse response = new ServiceResponse();
        response.setServiceId("6009611063000");
        List<ServiceDTO> serviceDataList = response.getServiceDTOs();
//        ServiceDTO serviceDTO = new ServiceDTO("");
    }
    static {
        MATCH_BETTING_MARKETS = new HashMap<>();
        ServiceResponse response = new ServiceResponse();
        response.setServiceId("6009611060123");
        List<Market> markets = response.getMarkets();

        Market market =
            new Market("361578128", "Match Betting", "Sunderland v Everton", date("2016-05-11"), time("19:45:00"),
                        date("2016-05-11"), time("19:45:00"), date("2016-05-07"), time("19:30:28"));

        List<Participant> participants = market.getParticipants();
        participants.add(
           new Participant(
                   "1183081193", "Sunderland", "4/5", new BigDecimal("1.80"), date("2016-05-09"), time("17:40:19") )
        );
        participants.add(
            new Participant(
                    "1183081194", "Draw", "5/2", new BigDecimal("3.50"), date("2016-05-10"), time("09:08:16") )
        );
        participants.add(
            new Participant(
                    "1183081195", "Everton", "7/2", new BigDecimal("4.50"), date("2016-05-10"), time("17:40:19") )
        );
        markets.add(market);

        market =
                new Market("363618266", "Match Betting", "Arsenal v Aston Villa", date("2016-05-15"), time("15:00:00"),
                        date("2016-05-15"), time("15:00:00"), date("2016-05-09"), time("08:51:46"));

        participants = market.getParticipants();
        participants.add(
                new Participant(
                        "1189186022", "Arsenal", "1/6", new BigDecimal("1.17"), date("2016-05-09"), time("08:51:47") )
        );
        participants.add(
                new Participant(
                        "1189186023", "Draw", "9/2", new BigDecimal("5.50"), date("2016-05-09"), time("08:51:46") )
        );
        participants.add(
                new Participant(
                        "1189186025", "Aston Villa", "11/1", new BigDecimal("12.00"), date("2016-05-09"), time("08:51:47") )
        );
        markets.add(market);

        response.setMarkets(markets);

        MATCH_BETTING_MARKETS.put(new ServiceRequest("6009611060123", "1", "295"), response);
    }

    static {
        ALL_MARKETS = new HashMap<>();
        ServiceResponse response = new ServiceResponse();
        response.setServiceId("6009611060124");
        List<Market> markets = response.getMarkets();

        Market market =
                new Market("363618266", "Match Betting", "Arsenal v Aston Villa", date("2016-05-15"), time("15:00:00"),
                        date("2016-05-15"), time("15:00:00"), date("2016-05-09"), time("08:51:46"));

        List<Participant> participants = market.getParticipants();
        participants.add(
                new Participant(
                        "1189186022", "Arsenal", "1/6", new BigDecimal("1.17"), date("2016-05-09"), time("08:51:47") )
        );
        participants.add(
                new Participant(
                        "1189186023", "Draw", "9/2", new BigDecimal("5.50"), date("2016-05-09"), time("08:51:46") )
        );
        participants.add(
                new Participant(
                        "1189186025", "Aston Villa", "11/1", new BigDecimal("12.00"), date("2016-05-09"), time("08:51:47") )
        );
        markets.add(market);


        market =
                new Market("1189186268", "1st Half Betting", "Arsenal v Aston Villa", date("2016-05-15"), time("15:00:00"),
                        date("2016-05-15"), time("15:00:00"), date("2016-05-09"), time("08:51:46"));

        participants = market.getParticipants();
        participants.add(
                new Participant(
                        "1189186266", "Arsenal", "2/5", new BigDecimal("1.40"), date("2016-05-09"), time("08:51:47") )
        );
        participants.add(
                new Participant(
                        "1189186267", "Draw", "12/5", new BigDecimal("3.40"), date("2016-05-09"), time("08:51:46") )
        );
        participants.add(
                new Participant(
                        "1189186025", "Aston Villa", "17/2", new BigDecimal("9.50"), date("2016-05-09"), time("08:51:47") )
        );
        markets.add(market);


        response.setMarkets(markets);

        ALL_MARKETS.put(new ServiceRequest("6009611060124", "1", "295", "363618266"), response);
    }
    */

    @Override
    public ServiceResponse process(ServiceRequest serviceRequest) {

        switch(serviceRequest.getServiceId()) {
            case "6009611060200":
                return null; //SERVICE_DATA.get(serviceRequest);
            case "6009611060123": // match betting
                return null; //MATCH_BETTING_MARKETS.get(serviceRequest);
            case "6009611060124": // match betting
                return null; //ALL_MARKETS.get(serviceRequest);
            default:
                return null;
        }
    }

    /*
    private List<ServiceDTO> getChildNodesOf(String serviceId) {
        TreeNode<ServiceDTO> parentNode = mTree.findNode(new ServiceDTO(serviceId));
        return parentNode.childrenData();
    }

    private static Date date(String date) {
        try {
            return new Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Time time(String time) {
        try {
            return new Time(new SimpleDateFormat("HH:mm:ss").parse(time).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    */
//    public TreeNode buildTree() {
//
//        // Root node
//        Map<String, String> params = new HashMap<>();
//        params.put("node-type", "app-title");
//        params.put("node-text-top-left", "eBridge");
//        NodeData nodeData = new NodeData("600-000000-0000", null, params, 0);
//        TreeNode treeNode = new TreeNode(nodeData);
//
//        // Level One2016
//        params = new HashMap<>();
//        params.put("node-type", "node-menu");
//        params.put("node-text-top-left", "SERVICES");
//        nodeData = new NodeData("600-000000-0001", "600-000000-0000", params, 0);
//        TreeNode<NodeData> serviceNode = treeNode.addChild(nodeData);
//
//        params = new HashMap<>();
//        params.put("node-type", "node-list");
//        params.put("node-text-top-left", "Soccer Shop");
//        params.put("node-text-bottom-left", "Make money through betting");
//        params.put("node-thumbnal", "soccer_shop");
//        nodeData = new NodeData("600-000000-1001", "600-000000-0001", params, 0);
//        TreeNode<NodeData> bettingService = serviceNode.addChild(nodeData);
//
//        params = new HashMap<>();
//        params.put("node-type", "node-list");
//        params.put("node-text-top-left", "Bus Tickets");
//        params.put("node-text-bottom-left", "Book and pay for bus tickets");
//        params.put("node-thumbnal", "bus");
//        nodeData = new NodeData("600-000000-1002", "600-000000-0001", params, 0);
//        TreeNode<NodeData> busTickets = serviceNode.addChild(nodeData);
//
//        params = new HashMap<>();
//        params.put("node-type", "node-list");
//        params.put("node-text-top-left", "Telecel Account Manager");
//        params.put("node-text-bottom-left", "Data bundles and Airtime Transfer");
//        params.put("node-thumbnal", "telecel");
//        nodeData = new NodeData("600-000000-1002", "600-000000-0001", params, 0);
//        TreeNode<NodeData> telecel = serviceNode.addChild(nodeData);
//
//        params = new HashMap<>();
//        params.put("node-type", "node-menu");
//        params.put("node-text-top-left", "CHATS");
//        nodeData = new NodeData("600-000000-0001", "600-000000-0000", params, 0);
//        treeNode.addChild(nodeData);
//
//        params = new HashMap<>();
//        params.put("node-type", "node-menu");
//        params.put("node-text-top-left", "YELLOW PAGES");
//        nodeData = new NodeData("600-000000-0001", "600-000000-0000", params, 0);
//        treeNode.addChild(nodeData);
//
//        return treeNode;
//    }

    public Tree<NodeData> buildTree() {

        Map<String, String> params = new HashMap<>();
        params.put("node-text-top-left", "eBridge");
        NodeData data = new NodeData("600-10000-10000", null, params, 0);
        tree = new Tree(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "SERVICES");
        data = new NodeData("600-10000-10001", "600-10000-10000", params, 0);
        Tree<NodeData> serviceTree = tree.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "CHATS");
        params.put("processor", "ChatProcessor");
        data = new NodeData("600-10000-10002", "600-10000-10000", params, 1);
        Tree<NodeData> chats = tree.addLeaf(data);

        // Chats
        params = new HashMap<>();
        params.put("node-text-top-left", "Mercy Timburwa");
        params.put("node-text-bottom-left", "Muri sei");
        params.put("thumbnail", "mercy");
        params.put("processor", "ChatFragment");
        params.put("id", "david");
        data = new NodeData("600-10000-30002", "600-10000-10002", params, 0);
        chats.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "Tekeshe Group");
        params.put("node-text-bottom-left", "Hey");
        params.put("thumbnail", "feli");
        params.put("processor", "ChatFragment");
        params.put("id", "stanley");
        data = new NodeData("600-10000-30003", "600-10000-10002", params, 1);
        chats.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "Feli Chihwai");
        params.put("node-text-bottom-left", "Ndeipi");
        params.put("thumbnail", "feli");
        params.put("processor", "ChatFragment");
        params.put("id", "feli");
        data = new NodeData("600-10000-30004", "600-10000-10002", params, 2);
        chats.addLeaf(data);


        params = new HashMap<>();
        params.put("node-text-top-left", "YELLOW PAGES");
        data = new NodeData("600-10000-10003", "600-10000-10000", params, 2);
        Tree<NodeData> yellowPages = tree.addLeaf(data);

        // Yellow pages
        params = new HashMap<>();
        params.put("node-text-top-left", "eBridge");
        params.put("node-text-bottom-left", "Mobile commerce consultants");
        params.put("thumbnail", "ebridge");
        data = new NodeData("600-10000-30003", "600-10000-10003", params, 1);
        yellowPages.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "Soccer Shop");
        params.put("node-text-bottom-left", "Betting services");
        params.put("thumbnail", "soccer_shop");
        data = new NodeData("600-10000-30004", "600-10000-10003", params, 1);
        yellowPages.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "Chitaitai");
        params.put("node-text-bottom-left", "Chemical Engineers");
        params.put("thumbnail", "chitaitai");
        data = new NodeData("600-10000-30003", "600-10000-10003", params, 1);
        yellowPages.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "MmCellink");
        params.put("node-text-bottom-left", "Mobile phones shop");
        params.put("thumbnail", "mmcellink");
        data = new NodeData("600-10000-30003", "600-10000-10003", params, 1);
        yellowPages.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "Jo's Chickens");
        params.put("node-text-bottom-left", "Chicken abattoirs");
        params.put("thumbnail", "joschickens");
        data = new NodeData("600-10000-30003", "600-10000-10003", params, 1);
        yellowPages.addLeaf(data);

        // Services
        params = new HashMap<>();
        params.put("node-text-top-left", "Soccer Betting");
        params.put("node-text-bottom-left", "Make money from betting");
        params.put("thumbnail", "soccer_shop");
        params.put("processor", "MarketBrowserFragment");
        data = new NodeData("600-10000-10004", "600-10000-10001", params, 1);
        Tree<NodeData> betting = serviceTree.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "Bus Tickets");
        params.put("node-text-bottom-left", "Book and pay bus tickets");
        params.put("thumbnail", "bus");
        data = new NodeData("600-10000-10005", "600-10000-10001", params, 2);
        serviceTree.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "Suggestion Box");
        params.put("node-text-bottom-left", "We appreciate your input");
        params.put("thumbnail", "suggestionbox");
        data = new NodeData("600-10000-10006", "600-10000-10001", params, 5);
        serviceTree.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "Shop Manager");
        params.put("node-text-bottom-left", "Manage remote shops");
        params.put("thumbnail", "shop");
        data = new NodeData("600-10000-10007", "600-10000-10001", params, 6);
        serviceTree.addLeaf(data);

        // betting
        params = new HashMap<>();
        params.put("node-text-top-left", "MATCHES");
        data = new NodeData("600-10000-10010", "600-10000-10004", params, 0);
        Tree<NodeData> events = betting.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "ODDS");
        data = new NodeData("600-10000-10011", "600-10000-10004", params, 0);
        betting.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "BETS");
        data = new NodeData("600-10000-10013", "600-10000-10004", params, 0);
        betting.addLeaf(data);

        // events
        params = new HashMap<>();
        params.put("node-text-top-left", "England");
        data = new NodeData("600-10000-30010", "600-10000-10010", params, 0);
        Tree<NodeData> englandEvents = events.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "Spain");
        data = new NodeData("600-10000-30011", "600-10000-10010", params, 0);
        Tree<NodeData> spanishEvents = events.addLeaf(data);

        // leagues
        params = new HashMap<>();
        params.put("node-text-top-left", "England - Premiership");
        data = new NodeData("600-50000-30011", "600-10000-30010", params, 0);
        Tree<NodeData> premiership = englandEvents.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "England - Championship");
        data = new NodeData("600-10000-30012", "600-10000-30010", params, 0);
        Tree<NodeData> championship = englandEvents.addLeaf(data);

        // premiership

        params = new HashMap<>();
        params.put("node-text-top-left", "Arsenal v Aston Villa");
        data = new NodeData("600-10000-40011", "600-50000-30011", params, 0);
        Tree<NodeData> match1 = premiership.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "Totenham v New Castle");
        data = new NodeData("600-10000-40012", "600-50000-30011", params, 0);
        Tree<NodeData> match2 = premiership.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "Swanse v Man City");
        data = new NodeData("600-10000-40013", "600-50000-30011", params, 0);
        Tree<NodeData> match3 = premiership.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "Liverpool v Southampton");
        data = new NodeData("600-10000-40014", "600-50000-30011", params, 0);
        Tree<NodeData> match4 = premiership.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "Man United v Bourbeth");
        data = new NodeData("600-10000-40015", "600-50000-30011", params, 0);
        Tree<NodeData> match5 = premiership.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "Watford v Westham");
        data = new NodeData("600-10000-40016", "600-50000-30011", params, 0);
        Tree<NodeData> match6 = premiership.addLeaf(data);

        // championship
        params = new HashMap<>();
        params.put("node-text-top-left", "Burnley v Reading");
        data = new NodeData("600-10000-40012", "600-10000-30012", params, 0);
        Tree<NodeData> cmatch1 = championship.addLeaf(data);

        // laliga
        params = new HashMap<>();
        params.put("node-text-top-left", "Spain - La Liga");
        data = new NodeData("600-10000-40011", "600-10000-30011", params, 0);
        Tree<NodeData> laliga = spanishEvents.addLeaf(data);

        return tree;
    }

    public Tree tree() {
        return tree;
    }


//    public List<NodeData> getChildren(String parentId) {
//
//        List<String> childIds = getMap().get(parentId);
//        List<NodeData> children = new ArrayList<>();
//        for (String id : childIds) {
//            NodeData child = data.get(id);
//            children.add(child);
//        }
//
//        return children;
//    }

    public static void main(String[] args) {
//
//        NetworkServiceStub service = new NetworkServiceStub();
//        TreeNode treeNode = service.buildTree();
//
//        // Root Node
//        NodeData nodeData = (NodeData)treeNode.data;
//        System.out.println("App Title : " + nodeData.getParams().get("node-text-top-left"));
//
//        // Main Menu Titles
//        TreeNode<NodeData> serviceNode = treeNode.getChildAt(0);
//        System.out.print(serviceNode.data.getParams().get("node-text-top-left") + "\t\t");
//
//        TreeNode<NodeData> chatsNode = treeNode.getChildAt(1);
//        System.out.print(chatsNode.data.getParams().get("node-text-top-left") + "\t\t");
//
//        TreeNode<NodeData> yellowPageNode = treeNode.getChildAt(1);
//        System.out.println(yellowPageNode.data.getParams().get("node-text-top-left") + "\t\t");
//
//        System.out.println(serviceNode.data.getParams().get("node-text-top-left") + "\t\t");
//
//        for ( TreeNode<NodeData> children : serviceNode.children) {
//            System.out.println(children.data.getParams().get("node-text-top-left") + "\t\t" +
//                    children.data.getParams().get("node-text-bottom-left") + "\t\t");
//        }
//
//        ServiceRequest request = new ServiceRequest("6009611060123", "1", "295");
//        System.out.println(request);
//        ServiceResponse response = service.process(request);
//        System.out.println(response);
//
//        request = new ServiceRequest("6009611060124", "1", "295", "363618266");
//        System.out.println(request);
//        response = service.process(request);
//        System.out.println(response);

        // Yellow pages
//        List<ServiceDTO> nodes = service.getChildNodesOf("60096119611");
//        System.out.println("Yellow Page - \n" + nodes + "\n");
//
//        // Telecel services
//        nodes = service.getChildNodesOf("600961112356");
//        System.out.println("Telecel - \n" + nodes + "\n");
//
//        nodes = service.getChildNodesOf("600961112360");
//        System.out.println("Account Manager - \n" + nodes + "\n");
//
//        nodes = service.getChildNodesOf("600961112361");
//        System.out.println("Balance - \n" + nodes + "\n");

//        List<NodeData> yellowPageList = service.getChildren("600-000002-0002");
//        for (NodeData entry: yellowPageList) {
//            Map<String, String> param = entry.getParams();
//            System.out.println("name: " + param.get("title"));
////            System.out.println("name: " + param.get("title") + "\nnarration: " + param.get("sub-title") +
////                    "\navator: " + param.get("avator"));
//        }


    }
}
