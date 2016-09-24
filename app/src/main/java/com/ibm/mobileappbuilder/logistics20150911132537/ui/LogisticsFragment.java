

package com.ibm.mobileappbuilder.logistics20150911132537.ui;

import android.os.Bundle;

import com.ibm.mobileappbuilder.logistics20150911132537.R;

import java.util.ArrayList;
import java.util.List;

import ibmmobileappbuilder.MenuItem;

import ibmmobileappbuilder.actions.StartActivityAction;
import ibmmobileappbuilder.util.Constants;

/**
 * LogisticsFragment menu fragment.
 */
public class LogisticsFragment extends ibmmobileappbuilder.ui.MenuFragment {

    /**
     * Default constructor
     */
    public LogisticsFragment(){
        super();
    }

    // Factory method
    public static LogisticsFragment newInstance(Bundle args) {
        LogisticsFragment fragment = new LogisticsFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
      public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
                }

    // Menu Fragment interface
    @Override
    public List<MenuItem> getMenuItems() {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        items.add(new MenuItem()
            .setLabel("FRIENDS")
            .setIcon(R.drawable.png_drivers956)
            .setAction(new StartActivityAction(FriendsMenuItem1Activity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("Cars")
            .setIcon(R.drawable.png_vehicles748)
            .setAction(new StartActivityAction(VehiclesActivity.class, Constants.DETAIL))
        );
        items.add(new MenuItem()
            .setLabel("Location")
            .setIcon(R.drawable.png_defaultmenuicon)
            .setAction(new StartActivityAction(LocationActivity.class, Constants.DETAIL))
        );
        return items;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_list;
    }

    @Override
    public int getItemLayout() {
        return R.layout.logistics_item;
    }
}

