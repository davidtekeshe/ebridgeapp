package com.ebridgevas.android.ebridgeapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ServiceData implements Parcelable, Serializable {

    private String id;
    private String parentId;
    private Byte dataType;
    private Map<String, String> params;

    public ServiceData() {
    }

    public ServiceData(String id, String parentId, Byte dataType, Map<String, String> params) {
        this.id = id;
        this.parentId = parentId;
        this.dataType = dataType;
        this.params = params;
    }

//    protected ServiceData(Parcel in) {
//        id = in.readString();
//        parentId = in.readString();
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Byte getDataType() {
        return dataType;
    }

    public void setDataType(Byte dataType) {
        this.dataType = dataType;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    public static final Creator<ServiceData> CREATOR = new Creator<ServiceData>() {

        @Override
        public ServiceData createFromParcel(Parcel in) {
            ClassLoader loader = null;
            try {
                loader = Class.forName("com.ebridgevas.android.ebridgeapp.data.ServiceData").getClassLoader();
                return (ServiceData)in.readValue(loader);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
//            return new ServiceData(in);
        }

        @Override
        public ServiceData[] newArray(int size) {
            return new ServiceData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(id);
//        dest.writeString(parentId);

        dest.writeValue(this);
    }

    public static void main(String[] args) {
        ServiceData data = new ServiceData();
        data.setId("id 1");
        data.setParentId("parent 1");
        data.setDataType((byte) 2);
        Map<String, String> params = new HashMap<>();
        params.put("title", "title1");
        params.put("narration", "narration1");
        data.setParams(params);

        Parcel parcel = Parcel.obtain();
        parcel.writeValue(data);

        ClassLoader loader = null;
        try {
            loader = Class.forName("com.ebridgevas.android.ebridgeapp.data.ServiceData").getClassLoader();
            ServiceData result =  (ServiceData)parcel.readValue(loader);
            System.out.print("result: " + result);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}