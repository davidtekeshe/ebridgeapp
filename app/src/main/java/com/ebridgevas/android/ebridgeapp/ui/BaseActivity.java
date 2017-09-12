package com.ebridgevas.android.ebridgeapp.ui;

import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * TODO Class description
 *
 */
public abstract class BaseActivity extends android.app.Activity {

    /**
     * TODO Method description
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView( getLayoutId() );

        ButterKnife.bind(this);

    }

    /**
     * TODO Method description
     *
     * @return
     */
    protected abstract int getLayoutId();
}

