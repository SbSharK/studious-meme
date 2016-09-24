package com.ibm.mobileappbuilder.logistics20150911132537.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.VehiclesDSService;
import com.ibm.mobileappbuilder.logistics20150911132537.presenters.VehiclesPresenter;
import com.ibm.mobileappbuilder.logistics20150911132537.R;
import ibmmobileappbuilder.behaviors.FabBehaviour;
import ibmmobileappbuilder.behaviors.SearchBehavior;
import ibmmobileappbuilder.behaviors.SelectionBehavior;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.ui.ListGridFragment;
import ibmmobileappbuilder.util.ColorUtils;
import ibmmobileappbuilder.util.Constants;
import ibmmobileappbuilder.util.image.ImageLoader;
import ibmmobileappbuilder.util.image.PicassoImageLoader;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.util.ViewHolder;
import java.net.URL;
import java.util.List;
import static ibmmobileappbuilder.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.VehiclesDSItem;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.VehiclesDS;
import ibmmobileappbuilder.mvp.view.CrudListView;
import ibmmobileappbuilder.ds.CrudDatasource;
import android.content.Intent;
import ibmmobileappbuilder.util.Constants;

import static ibmmobileappbuilder.util.NavigationUtils.generateIntentToAddOrUpdateItem;

/**
 * "VehiclesFragment" listing
 */
public class VehiclesFragment extends ListGridFragment<VehiclesDSItem> implements CrudListView<VehiclesDSItem> {

    private CrudDatasource<VehiclesDSItem> datasource;

    // "Add" button
    private FabBehaviour fabBehavior;

    public static VehiclesFragment newInstance(Bundle args) {
        VehiclesFragment fr = new VehiclesFragment();

        fr.setArguments(args);
        return fr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new VehiclesPresenter(
            (CrudDatasource) getDatasource(),
            this
        ));
        addBehavior(new SearchBehavior(this));
        // Multiple selection
        SelectionBehavior<VehiclesDSItem> selectionBehavior = new SelectionBehavior<>(
            this,
            R.string.remove_items,
            R.drawable.ic_delete_alpha);

        selectionBehavior.setCallback(new SelectionBehavior.Callback<VehiclesDSItem>() {
            @Override
            public void onSelected(List<VehiclesDSItem> selectedItems) {
                getPresenter().deleteItems(selectedItems);
            }
        });
        addBehavior(selectionBehavior);
        // FAB button
        fabBehavior = new FabBehaviour(this, R.drawable.ic_add_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().addForm();
            }
        });
        addBehavior(fabBehavior);
    }

    protected SearchOptions getSearchOptions() {
      SearchOptions.Builder searchOptionsBuilder = SearchOptions.Builder.searchOptions();
      return searchOptionsBuilder.build();
    }


    /**
    * Layout for the list itselft
    */
    @Override
    protected int getLayout() {
        return R.layout.fragment_list;
    }

    /**
    * Layout for each element in the list
    */
    @Override
    protected int getItemLayout() {
        return R.layout.vehicles_item;
    }

    @Override
    protected Datasource<VehiclesDSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
      datasource = VehiclesDS.getInstance(getSearchOptions());
      return datasource;
    }

    @Override
    protected void bindView(VehiclesDSItem item, View view, int position) {
        
        ImageLoader imageLoader = new PicassoImageLoader(view.getContext());
        ImageView image = ViewHolder.get(view, R.id.image);
        URL imageMedia = ((AppNowDatasource) getDatasource()).getImageUrl(item.carImage);
        if(imageMedia != null){
          imageLoader.load(imageLoaderRequest()
                          .withPath(imageMedia.toExternalForm())
                          .withTargetView(image)
                          .fit()
                          .build()
          );
        	
        }
        else {
          imageLoader.load(imageLoaderRequest()
                          .withResourceToLoad(R.drawable.ic_ibm_placeholder)
                          .withTargetView(image)
                          .build()
          );
        }
        
        
        TextView title = ViewHolder.get(view, R.id.title);
        
        if (item.nameofCar != null){
            title.setText(item.nameofCar);
            
        }
        
        TextView subtitle = ViewHolder.get(view, R.id.subtitle);
        
        if (item.model != null){
            subtitle.setText(item.model);
            
        }
    }

    @Override
    protected void itemClicked(final VehiclesDSItem item, final int position) {
        fabBehavior.hide(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                getPresenter().detail(item, position);
            }
        });
    }

    @Override
    public void showDetail(VehiclesDSItem item, int position) {
        Bundle args = new Bundle();
        args.putInt(Constants.ITEMPOS, position);
        args.putParcelable(Constants.CONTENT, item);
        Intent intent = new Intent(getActivity(), VehiclesDetailActivity.class);
        intent.putExtras(args);

        if (!getResources().getBoolean(R.bool.tabletLayout)) {
            startActivityForResult(intent, Constants.DETAIL);
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void showAdd() {
        startActivityForResult(generateIntentToAddOrUpdateItem(null,
                        0,
                        getActivity(),
                        VehiclesDSItemFormActivity.class
                ), Constants.MODE_CREATE
        );
    }

    @Override
    public void showEdit(VehiclesDSItem item, int position) {
    startActivityForResult(
                generateIntentToAddOrUpdateItem(item,
                        position,
                        getActivity(),
                        VehiclesDSItemFormActivity.class
                ), Constants.MODE_EDIT
        );
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        // inflate menu options and tint icon
        inflater.inflate(R.menu.filter_menu, menu);
        ColorUtils.tintIcon(menu.findItem(R.id.filter),
                            R.color.textBarColor,
                            getActivity());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.filter){
            Intent intent = new Intent(getActivity(), VehiclesFilterActivity.class);

            // pass current values to filter activity
            
            // launch filter screen
            startActivityForResult(intent, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            // store the incoming selection
                        // apply filter to datasource
            clearFilters();

                        // and finally refresh the list
            refresh();

            // and redraw menu (to refresh tinted icons, like the search icon)
            getActivity().invalidateOptionsMenu();
        }
    }
}

