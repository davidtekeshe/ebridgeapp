package com.ebridgevas.android.ebridgeapp.model;

import java.util.Comparator;
import java.util.Map;

public class NodeData implements Comparable<NodeData> {
    
    private String mId;
    private String mParentId;
    private MessageType mNodeType;
    private Map<String, String> mParams;

    private Long mIndex;

    public NodeData() {
    }

    public NodeData(String mId) {
        this.mId = mId;
    }

    public NodeData(String id, String parentId, Map<String, String> params, Integer index) {
        mId = id;
        mParentId = parentId;
        mNodeType = null;
        mParams = params;
        mIndex = Long.parseLong("" + index);
    }

    public NodeData(String id, String parentId, MessageType nodeType, Map<String, String> params, Long index) {
        mId = id;
        mParentId = parentId;
        mNodeType = nodeType;
        mParams = params;
        mIndex = index;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getParentId() {
        return mParentId;
    }

    public void setParentId(String parentId) {
        mParentId = parentId;
    }

    public MessageType getNodeType() {
        return mNodeType;
    }

    public void setNodeType(MessageType nodeType) {
        mNodeType = nodeType;
    }

    public Map<String, String> getParams() {
        return mParams;
    }

    public void setParams(Map<String, String> params) {
        mParams = params;
    }

    public Long getIndex() {
        return mIndex;
    }

    public void setIndex(Long index) {
        mIndex = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeData nodeData = (NodeData) o;

        return mId != null && mId.equals(nodeData.mId);
    }

    @Override
    public int hashCode() {
        return mId != null ? mId.hashCode() : 0;
    }

    @Override
    public int compareTo(NodeData o) {
        return this.mIndex.compareTo(o.getIndex());
    }

    public static class NodeDataComparator implements Comparator<NodeData> {

        @Override
        public int compare(NodeData o1, NodeData o2) {
            return o1.getIndex().compareTo(o2.getIndex());
        }
    }

    @Override
    public String toString() {
        return "NodeData{" +
                "mId='" + mId + '\'' +
                ", mParentId='" + mParentId + '\'' +
                ", mNodeType=" + mNodeType +
                ", mParams=" + mParams +
                ", mIndex=" + mIndex +
                '}';
    }
}
