package com.ebridgevas.android.ebridgeapp.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author david@tekeshe.com
 */

public class SequentialIDGenerator {

    private AtomicLong atomicLong;
    private static SequentialIDGenerator instance = null;

    private SequentialIDGenerator(long initialValue){
        atomicLong = new AtomicLong(initialValue);
    }

    public static SequentialIDGenerator instance(long initialValue){
        return new SequentialIDGenerator(initialValue);
    }

    public long generate() {
        return atomicLong.getAndIncrement();
    }
}
