package com.ebridgevas.android.ebridgeapp.model;

import com.google.gson.Gson;

public class ServiceDTO implements Comparable<ServiceDTO> {

    private String dialogType;
    private String serviceId;
    private String serviceTitle;
    private String narration;
    private String avator;
    private String action;

    public ServiceDTO() {
    }

    public ServiceDTO(String serviceId) {
        this.serviceId = serviceId;
    }

    public ServiceDTO(
            String dialogType,
            String serviceId,
            String serviceTitle,
            String narration,
            String avator,
            String action) {
        this.dialogType = dialogType;
        this.serviceId = serviceId;
        this.serviceTitle = serviceTitle;
        this.narration = narration;
        this.avator = avator;
        this.action = action;
    }

    public String getDialogType() {
        return dialogType;
    }

    public void setDialogType(String dialogType) {
        this.dialogType = dialogType;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public int compareTo(ServiceDTO another) {
        return this.serviceId.compareTo(another.getServiceId());
    }
}