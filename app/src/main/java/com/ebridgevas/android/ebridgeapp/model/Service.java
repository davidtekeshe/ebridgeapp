package com.ebridgevas.android.ebridgeapp.model;

/**
 * TODO Class description.
 */
public class Service {

    private final String eanCode;
    private final String providerEanCode;
    private final String serviceLogo;
    private final String serviceName;
    private final String narration;
    private int orderIndex;

    public Service(
            String eanCode,
            String providerEanCode,
            String serviceLogo,
            String serviceName,
            String narration,
            int orderIndex) {

        this.eanCode = eanCode;
        this.providerEanCode = providerEanCode;
        this.serviceLogo = serviceLogo;
        this.serviceName = serviceName;
        this.narration = narration;
        this.orderIndex = orderIndex;
    }

    public String getEanCode() {
        return eanCode;
    }

    public String getProviderEanCode() {
        return providerEanCode;
    }

    public String getServiceLogo() {
        return serviceLogo;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getNarration() {
        return narration;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    @Override
    public String toString() {
        return "Service{" +
                "eanCode='" + eanCode + '\'' +
                ", providerEanCode='" + providerEanCode + '\'' +
                ", serviceLogo='" + serviceLogo + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", narration='" + narration + '\'' +
                ", orderIndex=" + orderIndex +
                '}';
    }
}
