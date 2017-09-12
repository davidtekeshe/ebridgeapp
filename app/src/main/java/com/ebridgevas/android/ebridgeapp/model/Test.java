package com.ebridgevas.android.ebridgeapp.model;


import java.util.*;

public class Test {

    public static Tree buildTree() {
        Map<String, String> params = new HashMap<>();
        params.put("node-text-top-left", "eBridge");
        NodeData data = new NodeData("600-10000-10000", null, params, 0);
        Tree tree = new Tree(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "SERVICES");
        data = new NodeData("600-10000-10001", "600-10000-10000", params, 0);
        Tree<NodeData> serviceTree = tree.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "CHATS");
        data = new NodeData("600-10000-10002", "600-10000-10000", params, 1);
        tree.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "YELLOW PAGES");
        data = new NodeData("600-10000-10003", "600-10000-10000", params, 2);
        tree.addLeaf(data);

        // Services
        params = new HashMap<>();
        params.put("node-text-top-left", "Soccer Betting");
        data = new NodeData("600-10000-10004", "600-10000-10001", params, 5);
        Tree<NodeData> betting = serviceTree.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "Bus Tickets");
        data = new NodeData("600-10000-10005", "600-10000-10001", params, 4);
        serviceTree.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "Suggestion Box");
        data = new NodeData("600-10000-10006", "600-10000-10001", params, 3);
        serviceTree.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "Shop Manager");
        data = new NodeData("600-10000-10007", "600-10000-10001", params, 2);
        serviceTree.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "Buy or Sell");
        data = new NodeData("600-10000-10008", "600-10000-10001", params, 1);
        serviceTree.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "Telecel Data Bundles");
        data = new NodeData("600-10000-10009", "600-10000-10001", params, 0);
        serviceTree.addLeaf(data);

        // betting
        params = new HashMap<>();
        params.put("node-text-top-left", "EVENTS");
        data = new NodeData("600-10000-10010", "600-10000-10004", params, 0);
        betting.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "MARKETS");
        data = new NodeData("600-10000-10011", "600-10000-10004", params, 0);
        betting.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "BET");
        data = new NodeData("600-10000-10012", "600-10000-10004", params, 0);
        betting.addLeaf(data);

        params = new HashMap<>();
        params.put("node-text-top-left", "BETS");
        data = new NodeData("600-10000-10013", "600-10000-10004", params, 0);
        betting.addLeaf(data);

        return tree;
    }

//    static class NodeDataComparator implements Comparator<NodeData> {
//
//        @Override
//        public int compare(NodeData o1, NodeData o2) {
//            return o1.getIdx().compareTo(o2.getIdx());
//        }
//    }

    public static void main(String[] args) {
        Tree tree = buildTree();
        NodeData data = (NodeData)tree.getHead();
//        System.out.println("nodeData : " + data);

        NodeData searchNode = new NodeData("600-10000-10000", null, null, 0);
        Collection<NodeData> children = tree.getSuccessors(searchNode);
//        System.out.println(children);

        searchNode = new NodeData("600-10000-10001", null, null, 0);
        children = tree.getSuccessors(searchNode);
        List<NodeData> list = new ArrayList<>(children);
        Collections.sort(list, new NodeData.NodeDataComparator());
//        System.out.println(list);

        // 600-10000-10009
        searchNode = new NodeData("600-10000-10004", null, null, 0);
        children = tree.getSuccessors(searchNode);
        list = new ArrayList<>(children);
        Collections.sort(list, new NodeData.NodeDataComparator());
        System.out.println(list);
    }

}
