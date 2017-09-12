package com.ebridgevas.android.ebridgeapp;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

public class RegistrationActivity extends BaseActivity {

    private final static String TAG_FRAGMENT = "TAG_FRAMENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.ebridge_actionbar_layout);
//        setTitle("Registration");

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        PhoneEntryFragment fragment = new PhoneEntryFragment();
        transaction.replace(R.id.registrationRoot, fragment, TAG_FRAGMENT);
        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
    }
}
