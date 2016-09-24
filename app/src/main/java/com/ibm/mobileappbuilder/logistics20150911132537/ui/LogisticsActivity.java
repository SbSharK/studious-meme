

package com.ibm.mobileappbuilder.logistics20150911132537.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ibm.mobileappbuilder.logistics20150911132537.R;

import ibmmobileappbuilder.ui.BaseListingActivity;
/**
 * LogisticsActivity list activity
 */
public class LogisticsActivity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.logisticsActivity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return LogisticsFragment.class;
    }

}

