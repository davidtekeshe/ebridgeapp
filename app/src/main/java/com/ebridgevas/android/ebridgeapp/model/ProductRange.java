package com.ebridgevas.android.ebridgeapp.model;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * TODO Class description
 */
public class ProductRange {
    private final String providerCode;
    private final String serviceCode;
    private final String categoryCode;
    private final String productCode;
    private final String skuCode;
    private final String eanCode;
    private final String description;
    private final String price;
    private final ArrayList<Sku> skus;

    public ProductRange(
            String providerCode,
            String serviceCode,
            String categoryCode,
            String productCode,
            String skuCode,
            String eanCode,
            String description,
            String price) {
        this.providerCode = providerCode;
        this.serviceCode = serviceCode;
        this.categoryCode = categoryCode;
        this.productCode = productCode;
        this.skuCode = skuCode;
        this.eanCode = eanCode;
        this.description = description;
        this.price = price;
        skus = new ArrayList<>();
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

    public String getEanCode() {
        return eanCode;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public ArrayList<Sku> getSkus() {
        return skus;
    }

}
