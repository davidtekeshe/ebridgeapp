package com.ebridgevas.android.ebridgeapp.data;

import com.ebridgevas.android.ebridgeapp.model.ServiceDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * market-id: 361578128
 * category: match-betting
 * market-desc: Sunderland v Everton
 */
public class ServiceResponse {

    private String serviceId;
    private List<Market> mMarkets;
    private List<ServiceDTO> mServiceDTOs;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public List<Market> getMarkets() {
        if (mMarkets == null) {
            mMarkets = new ArrayList<>();
        }
        return mMarkets;
    }

    public void setMarkets(List<Market> markets) {
        mMarkets = markets;
    }

    public List<ServiceDTO> getServiceDTOs() {
        return mServiceDTOs == null ? new ArrayList<ServiceDTO>() : mServiceDTOs;
    }

    public void setServiceDTOs(List<ServiceDTO> serviceDTOs) {
        mServiceDTOs = serviceDTOs;
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "serviceId:'" + serviceId + '\'' +
                ", markets:" + mMarkets +
                '}';
    }
}
