package com.ebridgevas.android.ebridgeapp.xmpp;

import java.lang.ref.WeakReference;
import android.os.Binder;

/**
 * TODO Class description
 */
public class LocalBinder<S> extends Binder {

    private final WeakReference<S> service;

    public LocalBinder(final S service) {
        this.service = new WeakReference<S>(service);
    }

    public S getService() {
        return service.get();
    }

}
