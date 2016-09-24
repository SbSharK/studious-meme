
package com.ibm.mobileappbuilder.logistics20150911132537.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import com.ibm.mobileappbuilder.logistics20150911132537.R;
import ibmmobileappbuilder.behaviors.ShareBehavior;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.MapDSItem;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.MapDS;

public class LocationDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<MapDSItem> implements ShareBehavior.ShareListener  {

    private Datasource<MapDSItem> datasource;
    public static LocationDetailFragment newInstance(Bundle args){
        LocationDetailFragment fr = new LocationDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public LocationDetailFragment(){
        super();
    }

    @Override
    public Datasource<MapDSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = MapDS.getInstance(new SearchOptions());
        return datasource;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.locationdetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final MapDSItem item, View view) {
        if (item.location != null){
            
            TextView view1 = (TextView) view.findViewById(R.id.view1);
            view1.setText(item.location.toString());
            
        }
    }

    @Override
    protected void onShow(MapDSItem item) {
        // set the title for this fragment
        getActivity().setTitle(null);
    }
    @Override
    public void onShare() {
        MapDSItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, (item.location != null ? item.location.toString() : "" ));
        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}

