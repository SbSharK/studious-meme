

package com.ibm.mobileappbuilder.logistics20150911132537.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ibm.mobileappbuilder.logistics20150911132537.R;

import ibmmobileappbuilder.ui.BaseListingActivity;
/**
 * LocationActivity list activity
 */
public class LocationActivity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.locationActivity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return LocationFragment.class;
    }

}

