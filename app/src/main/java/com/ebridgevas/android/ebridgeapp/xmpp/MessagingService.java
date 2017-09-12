package com.ebridgevas.android.ebridgeapp.xmpp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.util.Log;

import com.ebridgevas.android.ebridgeapp.EBridgeApplication;
import com.ebridgevas.android.ebridgeapp.services.impl.NetworkServiceStub;
import com.ebridgevas.android.ebridgeapp.services.impl.PersistanceServiceStub;
import com.ebridgevas.android.ebridgeapp.services.impl.PersistenceServiceimpl;

import org.jivesoftware.smack.packet.Message;

/**
 * TODO Class description
 */
public class MessagingService extends Service {

    private static final String DOMAIN = "ebridgevas.com";
    private static final String USERNAME = "david";
    private static final String PASSWORD = "laJune2018";
    public static ConnectivityManager cm;
    public static XMPPClient xmppClient;

    private NetworkServiceStub networkService;
    private PersistenceServiceimpl persistenceService;
    private OnServiceListener serviceListener;

    @Override
    public IBinder onBind(final Intent intent) {
        return new LocalBinder<>(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        networkService = ((EBridgeApplication)getApplication()).getNetworkService();

        persistenceService = new PersistenceServiceimpl();

        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        xmppClient = XMPPClient.getInstance(MessagingService.this, DOMAIN, USERNAME, PASSWORD);
        xmppClient.connect("onCreate");
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags,
            final int startId) {
        return Service.START_NOT_STICKY;
    }

    @Override
    public boolean onUnbind(final Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        xmppClient.connection.disconnect();
    }

    public NetworkServiceStub getNetworkService() {
        return networkService;
    }

    public static boolean isNetworkConnected() {
        return cm.getActiveNetworkInfo() != null;
    }

    public interface OnServiceListener {
        public void onDataReceived(Message message);
    }

    public OnServiceListener getServiceListener() {
        return serviceListener;
    }

    public void setServiceListener(OnServiceListener serviceListener) {
        this.serviceListener = serviceListener;
    }


}
