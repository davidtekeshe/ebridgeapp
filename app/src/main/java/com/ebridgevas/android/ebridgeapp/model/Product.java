package com.ebridgevas.android.ebridgeapp.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Product offer by a service provider
 */
public class Product {

    private final String providerCode;
    private final String serviceCode;
    private final String categoryCode;
    private final String productCode;
    private final String description;
    private final Date productDate;

    private final ArrayList<Sku> skus;
    private final ArrayList<ProductRange> productRanges;

    public Product(
            String providerCode,
            String serviceCode,
            String categoryCode,
            String productCode,
            String description,
            Date productDate) {
        this.providerCode = providerCode;
        this.serviceCode = serviceCode;
        this.categoryCode = categoryCode;
        this.productCode = productCode;
        this.description = description;
        this.productDate = productDate;
        skus = new ArrayList<>();
        productRanges = new ArrayList<>();
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

    public String getDescription() {
        return description;
    }

    public Date getProductDate() {
        return productDate;
    }

    public ArrayList<Sku> getSkus() {
        return skus;
    }

    public ArrayList<ProductRange> getProductRanges() {
        return productRanges;
    }
}
