package com.ebridgevas.android.ebridgeapp.model;

/**
 * TODO Class description
 */
public class ProductCategory {

    private final String providerCode;
    private final String serviceCode;
    private final String categoryCode;
    private final String description;
    private int orderIndex;

    public ProductCategory(
            String providerCode,
            String serviceCode,
            String categoryCode,
            String description,
            int orderIndex) {
        this.providerCode = providerCode;
        this.serviceCode = serviceCode;
        this.categoryCode = categoryCode;
        this.description = description;
        this.orderIndex = orderIndex;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public String getDescription() {
        return description;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }
}
