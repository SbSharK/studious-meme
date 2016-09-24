

package com.ibm.mobileappbuilder.logistics20150911132537.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Arrays;

import com.ibm.mobileappbuilder.logistics20150911132537.R;

import ibmmobileappbuilder.ui.BaseFragment;
import ibmmobileappbuilder.ui.FilterActivity;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;

import com.ibm.mobileappbuilder.logistics20150911132537.ds.DriversScreen1DS;
import ibmmobileappbuilder.dialogs.ValuesSelectionDialog;
import ibmmobileappbuilder.views.ListSelectionPicker;
import java.util.ArrayList;

/**
 * FriendsMenuItem1FilterActivity filter activity
 */
public class FriendsMenuItem1FilterActivity extends FilterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // set title
        setTitle(R.string.friendsMenuItem1FilterActivity);
    }

    @Override
    protected Fragment getFragment() {
        return new PlaceholderFragment();
    }

    public static class PlaceholderFragment extends BaseFragment {
        private SearchOptions.Builder searchOptionsBuilder = SearchOptions.Builder.searchOptions();
        private SearchOptions searchOptions;

        // filter field values
            
    ArrayList<String> firstname_values;
    
    ArrayList<String> lastname_values;
    
    ArrayList<String> phone_values;

        public PlaceholderFragment() {
              searchOptions = SearchOptions.Builder.searchOptions().build();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.friendsmenuitem1_filter, container, false);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            // Get saved values
            Bundle bundle = savedInstanceState;
            if(bundle == null) {
                bundle = getArguments();
            }
            // get initial data
                        
            firstname_values = bundle.getStringArrayList("firstname_values");
            
            lastname_values = bundle.getStringArrayList("lastname_values");
            
            phone_values = bundle.getStringArrayList("phone_values");

            // bind pickers
                        
            final ListSelectionPicker firstname_view = (ListSelectionPicker) view.findViewById(R.id.firstname_filter);
            ValuesSelectionDialog firstname_dialog = (ValuesSelectionDialog) getFragmentManager().findFragmentByTag("firstname");
            if (firstname_dialog == null)
                firstname_dialog = new ValuesSelectionDialog();
            
            // configure the dialog
            firstname_dialog.setColumnName("firstname")
                .setDatasource(DriversScreen1DS.getInstance(searchOptions))
                .setSearchOptions(searchOptions)
                .setTitle("firstname")
                .setHaveSearch(true)
                .setMultipleChoice(true);
            
            // bind the dialog to the picker
            firstname_view.setSelectionDialog(firstname_dialog)
                .setTag("firstname")
                .setSelectedValues(firstname_values)
                .setSelectedListener(new ListSelectionPicker.ListSelectedListener() {
                @Override
                public void onSelected(ArrayList<String> selected) {
                    firstname_values = selected;
                }
            });
            
            final ListSelectionPicker lastname_view = (ListSelectionPicker) view.findViewById(R.id.lastname_filter);
            ValuesSelectionDialog lastname_dialog = (ValuesSelectionDialog) getFragmentManager().findFragmentByTag("lastname");
            if (lastname_dialog == null)
                lastname_dialog = new ValuesSelectionDialog();
            
            // configure the dialog
            lastname_dialog.setColumnName("lastname")
                .setDatasource(DriversScreen1DS.getInstance(searchOptions))
                .setSearchOptions(searchOptions)
                .setTitle("lastname")
                .setHaveSearch(true)
                .setMultipleChoice(true);
            
            // bind the dialog to the picker
            lastname_view.setSelectionDialog(lastname_dialog)
                .setTag("lastname")
                .setSelectedValues(lastname_values)
                .setSelectedListener(new ListSelectionPicker.ListSelectedListener() {
                @Override
                public void onSelected(ArrayList<String> selected) {
                    lastname_values = selected;
                }
            });
            
            final ListSelectionPicker phone_view = (ListSelectionPicker) view.findViewById(R.id.phone_filter);
            ValuesSelectionDialog phone_dialog = (ValuesSelectionDialog) getFragmentManager().findFragmentByTag("phone");
            if (phone_dialog == null)
                phone_dialog = new ValuesSelectionDialog();
            
            // configure the dialog
            phone_dialog.setColumnName("phone")
                .setDatasource(DriversScreen1DS.getInstance(searchOptions))
                .setSearchOptions(searchOptions)
                .setTitle("phone")
                .setHaveSearch(true)
                .setMultipleChoice(true);
            
            // bind the dialog to the picker
            phone_view.setSelectionDialog(phone_dialog)
                .setTag("phone")
                .setSelectedValues(phone_values)
                .setSelectedListener(new ListSelectionPicker.ListSelectedListener() {
                @Override
                public void onSelected(ArrayList<String> selected) {
                    phone_values = selected;
                }
            });

            // Bind buttons
            Button okBtn = (Button) view.findViewById(R.id.ok);
            okBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();

                    // send filter result back to caller
                                        
                    intent.putStringArrayListExtra("firstname_values", firstname_values);
                    
                    intent.putStringArrayListExtra("lastname_values", lastname_values);
                    
                    intent.putStringArrayListExtra("phone_values", phone_values);

                    getActivity().setResult(RESULT_OK, intent);
                    getActivity().finish();
                }
            });

            Button cancelBtn = (Button) view.findViewById(R.id.reset);
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Reset values
                                        
                    firstname_values = new ArrayList<String>();
                    firstname_view.setSelectedValues(null);
                    
                    lastname_values = new ArrayList<String>();
                    lastname_view.setSelectedValues(null);
                    
                    phone_values = new ArrayList<String>();
                    phone_view.setSelectedValues(null);
                }
            });
        }

        @Override
        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);

            // save current status
                        
            bundle.putStringArrayList("firstname_values", firstname_values);
            
            bundle.putStringArrayList("lastname_values", lastname_values);
            
            bundle.putStringArrayList("phone_values", phone_values);
        }
    }

}

