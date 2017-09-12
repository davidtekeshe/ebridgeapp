package com.ebridgevas.android.ebridgeapp.services;

import com.ebridgevas.android.ebridgeapp.data.ServiceRequest;
import com.ebridgevas.android.ebridgeapp.data.ServiceResponse;

public interface PersistanceService {
    ServiceResponse process(ServiceRequest serviceRequest);

}
