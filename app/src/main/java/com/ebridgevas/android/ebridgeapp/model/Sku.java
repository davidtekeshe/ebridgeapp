package com.ebridgevas.android.ebridgeapp.model;

import java.math.BigDecimal;

/**
 * TODO Class description
 */
public class Sku {
    private final String providerCode;
    private final String serviceCode;
    private final String categoryCode;
    private final String productCode;
    private final String skuCode;
    private final String description;
    private final String price;

    public Sku(
            String providerCode,
            String serviceCode,
            String categoryCode,
            String productCode,
            String skuCode,
            String description,
            String price) {
        this.providerCode = providerCode;
        this.serviceCode = serviceCode;
        this.categoryCode = categoryCode;
        this.productCode = productCode;
        this.skuCode = skuCode;
        this.description = description;
        this.price = price;
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

    public String getProductCode() {
        return productCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Sku{" +
                "providerCode='" + providerCode + '\'' +
                ", serviceCode='" + serviceCode + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", productCode='" + productCode + '\'' +
                ", skuCode='" + skuCode + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
