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
import com.ibm.mobileappbuilder.logistics20150911132537.ds.DriversScreen1DSService;
import com.ibm.mobileappbuilder.logistics20150911132537.presenters.FriendsMenuItem1Presenter;
import com.ibm.mobileappbuilder.logistics20150911132537.R;
import ibmmobileappbuilder.behaviors.FabBehaviour;
import ibmmobileappbuilder.behaviors.SearchBehavior;
import ibmmobileappbuilder.behaviors.SelectionBehavior;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.ui.ListGridFragment;
import ibmmobileappbuilder.util.ColorUtils;
import ibmmobileappbuilder.util.Constants;
import ibmmobileappbuilder.util.FilterUtils;
import ibmmobileappbuilder.util.image.ImageLoader;
import ibmmobileappbuilder.util.image.PicassoImageLoader;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.util.ViewHolder;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static ibmmobileappbuilder.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.DriversScreen1DSItem;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.DriversScreen1DS;
import ibmmobileappbuilder.mvp.view.CrudListView;
import ibmmobileappbuilder.ds.CrudDatasource;
import android.content.Intent;
import ibmmobileappbuilder.util.Constants;

import static ibmmobileappbuilder.util.NavigationUtils.generateIntentToAddOrUpdateItem;

/**
 * "FriendsMenuItem1Fragment" listing
 */
public class FriendsMenuItem1Fragment extends ListGridFragment<DriversScreen1DSItem> implements CrudListView<DriversScreen1DSItem> {

    private CrudDatasource<DriversScreen1DSItem> datasource;

    
    ArrayList<String> firstname_values;
    
    ArrayList<String> lastname_values;
    
    ArrayList<String> phone_values;
    // "Add" button
    private FabBehaviour fabBehavior;

    public static FriendsMenuItem1Fragment newInstance(Bundle args) {
        FriendsMenuItem1Fragment fr = new FriendsMenuItem1Fragment();

        fr.setArguments(args);
        return fr;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter(new FriendsMenuItem1Presenter(
            (CrudDatasource) getDatasource(),
            this
        ));
        addBehavior(new SearchBehavior(this));
        // Multiple selection
        SelectionBehavior<DriversScreen1DSItem> selectionBehavior = new SelectionBehavior<>(
            this,
            R.string.remove_items,
            R.drawable.ic_delete_alpha);

        selectionBehavior.setCallback(new SelectionBehavior.Callback<DriversScreen1DSItem>() {
            @Override
            public void onSelected(List<DriversScreen1DSItem> selectedItems) {
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
      searchOptionsBuilder
          .withSortAscending(true)
          .withSortColumn("firstname");
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
        return R.layout.friendsmenuitem1_item;
    }

    @Override
    protected Datasource<DriversScreen1DSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
      datasource = DriversScreen1DS.getInstance(getSearchOptions());
      return datasource;
    }

    @Override
    protected void bindView(DriversScreen1DSItem item, View view, int position) {
        
        ImageLoader imageLoader = new PicassoImageLoader(view.getContext());
        ImageView image = ViewHolder.get(view, R.id.image);
        URL imageMedia = ((AppNowDatasource) getDatasource()).getImageUrl(item.picture);
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
        
        if (item.firstname != null){
            title.setText(item.firstname);
            
        }
        
        TextView subtitle = ViewHolder.get(view, R.id.subtitle);
        
        if (item.lastname != null){
            subtitle.setText(item.lastname);
            
        }
    }

    @Override
    protected void itemClicked(final DriversScreen1DSItem item, final int position) {
        fabBehavior.hide(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                getPresenter().detail(item, position);
            }
        });
    }

    @Override
    public void showDetail(DriversScreen1DSItem item, int position) {
        Bundle args = new Bundle();
        args.putInt(Constants.ITEMPOS, position);
        args.putParcelable(Constants.CONTENT, item);
        Intent intent = new Intent(getActivity(), FriendsMenuItem1DetailActivity.class);
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
                        DriversScreen1DSItemFormActivity.class
                ), Constants.MODE_CREATE
        );
    }

    @Override
    public void showEdit(DriversScreen1DSItem item, int position) {
    startActivityForResult(
                generateIntentToAddOrUpdateItem(item,
                        position,
                        getActivity(),
                        DriversScreen1DSItemFormActivity.class
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
            Intent intent = new Intent(getActivity(), FriendsMenuItem1FilterActivity.class);

            // pass current values to filter activity
                        
            intent.putStringArrayListExtra("firstname_values", firstname_values);
            
            intent.putStringArrayListExtra("lastname_values", lastname_values);
            
            intent.putStringArrayListExtra("phone_values", phone_values);

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
                        
            firstname_values = data.getStringArrayListExtra("firstname_values");
            
            lastname_values = data.getStringArrayListExtra("lastname_values");
            
            phone_values = data.getStringArrayListExtra("phone_values");
            // apply filter to datasource
            clearFilters();

                        
            if(firstname_values != null && firstname_values.size() > 0)
                addStringFilter("firstname", firstname_values);
            
            if(lastname_values != null && lastname_values.size() > 0)
                addStringFilter("lastname", lastname_values);
            
            if(phone_values != null && phone_values.size() > 0)
                addStringFilter("phone", phone_values);
            // and finally refresh the list
            refresh();

            // and redraw menu (to refresh tinted icons, like the search icon)
            getActivity().invalidateOptionsMenu();
        }
    }
}

