package com.ebridgevas.android.ebridgeapp.data;

/**
 * Sample: {service-id: 6009611060123, category-id: 1, sub-category-id: 295}
 */
public class ServiceRequest {

    /**
     * Universal Resource Indicator
     * e.g. /ebridge/soccer-betting/events/
     * 6009611060123
     */
    private String mServiceId;
    private String mCategoryId;
    private String mSubCategoryId;
    private String mEventId;

    public ServiceRequest() {
    }

    public ServiceRequest(String serviceId, String categoryId, String subCategoryId) {
       this(serviceId, categoryId, subCategoryId, "");
    }

    public ServiceRequest(String serviceId, String categoryId, String subCategoryId, String eventId) {
        mServiceId = serviceId;
        mCategoryId = categoryId;
        mSubCategoryId = subCategoryId;
        mEventId = eventId;
    }

    public String getServiceId() {
        return mServiceId;
    }

    public void setServiceId(String serviceId) {
        mServiceId = serviceId;
    }

    public String getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(String categoryId) {
        mCategoryId = categoryId;
    }

    public String getSubCategoryId() {
        return mSubCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        mSubCategoryId = subCategoryId;
    }

    public String getEventId() {
        return mEventId;
    }

    public void setEventId(String eventId) {
        mEventId = eventId;
    }

    @Override
    public String toString() {
        return "{" +
                "serviceId:'" + mServiceId + '\'' +
                ", categoryId:'" + mCategoryId + '\'' +
                ", subCategoryId:'" + mSubCategoryId + '\'' +
                ", eventId:'" + mEventId + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(new ServiceRequest("6009611060123", "1", "295"));
    }

    @Override
    public boolean equals(Object another) {
        return mServiceId.equals(((ServiceRequest)another).getServiceId())
                && mCategoryId.equals(((ServiceRequest)another).getCategoryId())
                && mSubCategoryId.equals(((ServiceRequest)another).getSubCategoryId())
                && mEventId.equals(((ServiceRequest)another).getEventId());
    }

    public int hashCode() {
        return mServiceId.hashCode() + mCategoryId.hashCode() + mSubCategoryId.hashCode()
                + mEventId.hashCode();
    }
}
