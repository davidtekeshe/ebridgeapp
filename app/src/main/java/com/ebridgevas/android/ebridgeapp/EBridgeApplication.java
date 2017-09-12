package com.ebridgevas.android.ebridgeapp;

import android.app.Application;
import android.os.Handler;

import com.ebridgevas.android.ebridgeapp.messaging.mqtt.Connection;
import com.ebridgevas.android.ebridgeapp.services.impl.NetworkServiceStub;
import com.ebridgevas.android.ebridgeapp.util.NativeLoader;


/**
 * TODO Class description.
 */
public class EBridgeApplication extends Application {

    private static EBridgeApplication instance;
    public static volatile Handler applicationHandler = null;

    private NetworkServiceStub networkService;
    private Connection connection;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationHandler = new Handler(instance.getMainLooper());
        NativeLoader.initNativeLibs(EBridgeApplication.instance());

        networkService = new NetworkServiceStub();

    }

    public static EBridgeApplication instance() {
        return instance;
    }

    public NetworkServiceStub getNetworkService() {
        return networkService;
    }
}
