package com.ebridgevas.android.ebridgeapp.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Knows all the data required by the system
 */
public class EBridgeAppModel {

    public static ArrayList<Service> getServices() {
        ArrayList<Service> services = new ArrayList<>();
        services.add(new Service("60096110000", "60096110001", "soccer_shop", "Data Bundle Purchase",
                "Convert your airtime to data bundles", 1));
        services.add(new Service("60096110002", "60096110003", "suggestionbox", "Balance Enquiry",
                "Check your account balances", 2));
        return services;
    }

    public static List<String> getServiceTitles(String providerCode, String serviceCode) {
        return Arrays.asList(new String[]{"MATCHES", "BETS", "TRANSACTIONS"});
    }

    public static ArrayList<ProductCategory> getProductCategories(String providerCode, String serviceCode) {
        ArrayList<ProductCategory> categories = new ArrayList<>();
        categories.add(new ProductCategory("60096110001", "60096110000", "60096110008", "England - Premiership", 0));
        categories.add(new ProductCategory("60096110001", "60096110000", "60096110009", "England - Championship", 1));
        categories.add(new ProductCategory("60096110001", "60096110000", "60096110008", "Spain - La Liga", 2));
        return categories;
    }

    public static ArrayList<Product> getProducts(String providerCode, String serviceCode, String categoryCode) {

        SimpleDateFormat DATE_FORMAT_1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        ArrayList<Product> products = new ArrayList<>();
        Product product = new Product("60096110001", "60096110000", "60096110008", "1000001", "Arsenal v Aston Villa",
                new Date());
        ArrayList<Sku> skus = product.getSkus();
        skus.add(new Sku(product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                product.getProductCode(), "11111112", "1", "14/55"));
//        products.add(product);
        skus.add(new Sku(product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                product.getProductCode(), "11111113", "x", "1/5"));
//        products.add(product);
        skus.add(new Sku(product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                product.getProductCode(), "11111114", "2", "4/5"));
//        products.add(product);

        ArrayList<ProductRange> ranges = product.getProductRanges();
        ProductRange range = new ProductRange(product.getProviderCode(), product.getServiceCode(),
                product.getCategoryCode(),
                product.getProductCode(), "11111114", "22222222", "15 minute betting", "9/5");
        ranges.add(range);

        range = new ProductRange(product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                product.getProductCode(), "11111114", "22222223", "30 minute betting", "8/5");
        ranges.add(range);
        range = new ProductRange(product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                product.getProductCode(), "11111114", "22222224", "45 minute betting", "7/5");
        ranges.add(range);
        range = new ProductRange(product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                product.getProductCode(), "11111114", "22222225", "60 minute betting", "6/5");
        ranges.add(range);
        range = new ProductRange(product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                product.getProductCode(), "11111114", "22222226", "75 minute betting", "5/5");
        ranges.add(range);
        range = new ProductRange(product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                product.getProductCode(), "11111114", "22222224", "90 minute betting", "4/5");
        ranges.add(range);
        products.add(product);

        product = new Product("60096110001", "60096110000", "60096110008", "1000002", "Totenham v New Castle",
                new Date());
        skus = product.getSkus();
        skus.add(new Sku(product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                product.getProductCode(), "11111113", "1", "24/55"));
        skus.add(new Sku(product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                product.getProductCode(), "11111113", "x", "5/5"));
        skus.add(new Sku(product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                product.getProductCode(), "11111114", "2", "9/5"));
        products.add(product);

        product = new Product("60096110001", "60096110000", "60096110008", "1000003", "Swanse v Man City",
                new Date());
        skus = product.getSkus();
        skus.add(new Sku(product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                product.getProductCode(), "11111115", "1", "34/55"));
        skus.add(new Sku(product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                product.getProductCode(), "11111116", "x", "2/5"));
        skus.add(new Sku(product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                product.getProductCode(), "11111117", "2", "3/5"));
        products.add(product);

        product = new Product("60096110001", "60096110000", "60096110008", "1000004", "Liverpool v Southampton",
                new Date());
        skus = product.getSkus();
        skus.add(new Sku(product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                product.getProductCode(), "11111118", "1", "44/55"));
        skus.add(new Sku(product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                product.getProductCode(), "11111119", "x", "5/5"));
        skus.add(new Sku(product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                product.getProductCode(), "11111120", "2", "6/5"));
        products.add(product);

        product = new Product("60096110001", "60096110000", "60096110008", "1000005", "AMan United v Bourbeth",
                new Date());
        skus = product.getSkus();
        skus.add(new Sku(product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                product.getProductCode(), "11111126", "1", "54/55"));
        skus.add(new Sku(product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                product.getProductCode(), "11111123", "x", "7/5"));
        skus.add(new Sku(product.getProviderCode(), product.getServiceCode(), product.getCategoryCode(),
                product.getProductCode(), "11111124", "2", "8/5"));
        products.add(product);

        return products;
    }

    public static HashMap<String, ArrayList> getProducts(String providerCode, String serviceCode) {
        HashMap<String, ArrayList> products = new HashMap<>();

        ArrayList<ProductCategory> categories = new ArrayList<>();
        categories.add(new ProductCategory("60096110001", "60096110000", "60096110008", "England - Premiership", 0));
        categories.add(new ProductCategory("60096110001", "60096110000", "60096110009", "England - Championship", 1));
        categories.add(new ProductCategory("60096110001", "60096110000", "60096110008", "Spain - La Liga", 2));

        products.put("60096110008", categories);
        return products;
    }

    public static ArrayList<ProductRange> getProductRange(String providerCode, String serviceCode, String categoryCode,
            String productCode) {

        ArrayList<ProductRange> ranges = new ArrayList<>();
        ProductRange range = new ProductRange(providerCode, serviceCode, categoryCode,
                productCode, "11111114", "22222222", "15 minute betting", "9/5");

        ArrayList<Sku> skus = range.getSkus();
        skus.add(new Sku(range.getProviderCode(), range.getServiceCode(), range.getCategoryCode(),
                range.getProductCode(), "11111113", "1", "24/55"));
        skus.add(new Sku(range.getProviderCode(), range.getServiceCode(), range.getCategoryCode(),
                range.getProductCode(), "11111113", "x", "5/5"));
        skus.add(new Sku(range.getProviderCode(), range.getServiceCode(), range.getCategoryCode(),
                range.getProductCode(), "11111114", "2", "9/5"));
        ranges.add(range);

        range = new ProductRange(providerCode, serviceCode, categoryCode,
                productCode, "11111115", "22222223", "30 minute betting", "8/5");
        skus = range.getSkus();
        skus.add(new Sku(range.getProviderCode(), range.getServiceCode(), range.getCategoryCode(),
                range.getProductCode(), "11111113", "1", "24/55"));
        skus.add(new Sku(range.getProviderCode(), range.getServiceCode(), range.getCategoryCode(),
                range.getProductCode(), "11111113", "x", "5/5"));
        skus.add(new Sku(range.getProviderCode(), range.getServiceCode(), range.getCategoryCode(),
                range.getProductCode(), "11111114", "2", "9/5"));
        ranges.add(range);

        range = new ProductRange(providerCode, serviceCode, categoryCode,
                productCode, "11111116", "22222224", "45 minute betting", "7/5");
        skus = range.getSkus();
        skus.add(new Sku(range.getProviderCode(), range.getServiceCode(), range.getCategoryCode(),
                range.getProductCode(), "11111113", "1", "24/55"));
        skus.add(new Sku(range.getProviderCode(), range.getServiceCode(), range.getCategoryCode(),
                range.getProductCode(), "11111113", "x", "5/5"));
        skus.add(new Sku(range.getProviderCode(), range.getServiceCode(), range.getCategoryCode(),
                range.getProductCode(), "11111114", "2", "9/5"));
        ranges.add(range);

        range = new ProductRange(providerCode, serviceCode, categoryCode,
                productCode, "11111117", "22222225", "60 minute betting", "6/5");
        skus = range.getSkus();
        skus.add(new Sku(range.getProviderCode(), range.getServiceCode(), range.getCategoryCode(),
                range.getProductCode(), "11111113", "1", "24/55"));
        skus.add(new Sku(range.getProviderCode(), range.getServiceCode(), range.getCategoryCode(),
                range.getProductCode(), "11111113", "x", "5/5"));
        skus.add(new Sku(range.getProviderCode(), range.getServiceCode(), range.getCategoryCode(),
                range.getProductCode(), "11111114", "2", "9/5"));
        ranges.add(range);

        range = new ProductRange(providerCode, serviceCode, categoryCode,
                productCode, "11111118", "22222226", "75 minute betting", "5/5");
        skus = range.getSkus();
        skus.add(new Sku(range.getProviderCode(), range.getServiceCode(), range.getCategoryCode(),
                range.getProductCode(), "11111113", "1", "24/55"));
        skus.add(new Sku(range.getProviderCode(), range.getServiceCode(), range.getCategoryCode(),
                range.getProductCode(), "11111113", "x", "5/5"));
        skus.add(new Sku(range.getProviderCode(), range.getServiceCode(), range.getCategoryCode(),
                range.getProductCode(), "11111114", "2", "9/5"));
        ranges.add(range);

        range = new ProductRange(providerCode, serviceCode, categoryCode,
                productCode, "11111119", "22222224", "90 minute betting", "4/5");
        skus = range.getSkus();
        skus.add(new Sku(range.getProviderCode(), range.getServiceCode(), range.getCategoryCode(),
                range.getProductCode(), "11111113", "1", "24/55"));
        skus.add(new Sku(range.getProviderCode(), range.getServiceCode(), range.getCategoryCode(),
                range.getProductCode(), "11111113", "x", "5/5"));
        skus.add(new Sku(range.getProviderCode(), range.getServiceCode(), range.getCategoryCode(),
                range.getProductCode(), "11111114", "2", "9/5"));
        ranges.add(range);

        return ranges;
    }
}
