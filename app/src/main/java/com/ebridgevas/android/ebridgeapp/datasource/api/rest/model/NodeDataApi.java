package com.ebridgevas.android.ebridgeapp.datasource.api.rest.model;

import com.ebridgevas.android.ebridgeapp.model.MessageType;

import java.util.Map;

/**
 * TODO Class description
 */
public class NodeDataApi {
    private String mId;
    private String mParentId;
    private MessageType mNodeType;
    private Map<String, String> mParams;
    private String md5;

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

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
