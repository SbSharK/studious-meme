package com.ibm.mobileappbuilder.logistics20150911132537.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import ibmmobileappbuilder.actions.StartActivityAction;
import ibmmobileappbuilder.behaviors.SearchBehavior;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.restds.GeoPoint;
import ibmmobileappbuilder.ui.Filterable;
import ibmmobileappbuilder.util.Constants;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.MapDSItem;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.MapDS;
import ibmmobileappbuilder.behaviors.SearchBehavior;

/**
 * "LocationFragment" listing
 */
public class LocationFragment extends ibmmobileappbuilder.maps.ui.MapFragment<MapDSItem> {
    private SearchBehavior searchBehavior;
    private Datasource<MapDSItem> datasource;
    private SearchOptions searchOptions;

    public static LocationFragment newInstance(Bundle args){
        LocationFragment fr = new LocationFragment();
        fr.setArguments(args);

        return fr;
    }

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // If searchable behavior
        setHasOptionsMenu(true);
        searchBehavior = new SearchBehavior(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        searchBehavior.onCreateOptionsMenu(menu, inflater);
    }
	  @Override
    protected Datasource<MapDSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
        searchOptions = SearchOptions.Builder.searchOptions().build();
       datasource = MapDS.getInstance(searchOptions);
        return datasource;
    }

    @Override
    protected int getMapType() {
        return GoogleMap.MAP_TYPE_TERRAIN;
    }

    @Override
    protected String getLocationField() {
        return "location";
    }

    @Override
    protected Marker createAndBindMarker(GoogleMap map, MapDSItem item) {
        return map.addMarker(new MarkerOptions()
                        .position(
                                new LatLng(getLocationForItem(item).coordinates[1],
                                        getLocationForItem(item).coordinates[0]))
                        // Binding
                        .title("Whereto?")
        );
    }


    protected GeoPoint getLocationForItem(MapDSItem item) {
        return new GeoPoint();
    }

    @Override
    public void navigateToDetail(MapDSItem item) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.CONTENT, item);
        new StartActivityAction(LocationDetailActivity.class, bundle).execute(getActivity());
    }
}

