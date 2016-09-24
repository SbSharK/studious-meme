
package com.ibm.mobileappbuilder.logistics20150911132537.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.VehiclesDSService;
import com.ibm.mobileappbuilder.logistics20150911132537.presenters.VehiclesDetailPresenter;
import com.ibm.mobileappbuilder.logistics20150911132537.R;
import ibmmobileappbuilder.behaviors.FabBehaviour;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.util.ColorUtils;
import ibmmobileappbuilder.util.Constants;
import ibmmobileappbuilder.util.image.ImageLoader;
import ibmmobileappbuilder.util.image.PicassoImageLoader;
import ibmmobileappbuilder.util.StringUtils;
import java.net.URL;
import static ibmmobileappbuilder.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.VehiclesDSItem;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.VehiclesDS;

public class VehiclesDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<VehiclesDSItem>  {

    private CrudDatasource<VehiclesDSItem> datasource;
    public static VehiclesDetailFragment newInstance(Bundle args){
        VehiclesDetailFragment fr = new VehiclesDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public VehiclesDetailFragment(){
        super();
    }

    @Override
    public Datasource<VehiclesDSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = VehiclesDS.getInstance(new SearchOptions());
        return datasource;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        // the presenter for this view
        setPresenter(new VehiclesDetailPresenter(
                (CrudDatasource) getDatasource(),
                this));
        // Edit button
        addBehavior(new FabBehaviour(this, R.drawable.ic_edit_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DetailCrudPresenter<VehiclesDSItem>) getPresenter()).editForm(getItem());
            }
        }));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.vehiclesdetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final VehiclesDSItem item, View view) {
        
        ImageView view0 = (ImageView) view.findViewById(R.id.view0);
        URL view0Media = ((AppNowDatasource) getDatasource()).getImageUrl(item.carImage);
        if(view0Media != null){
          ImageLoader imageLoader = new PicassoImageLoader(view0.getContext());
          imageLoader.load(imageLoaderRequest()
                                   .withPath(view0Media.toExternalForm())
                                   .withTargetView(view0)
                                   .fit()
                                   .build()
                    );
        	
        } else {
          view0.setImageDrawable(null);
        }
    }

    @Override
    protected void onShow(VehiclesDSItem item) {
        // set the title for this fragment
        getActivity().setTitle(item.nameofCar + " " + item.model);
    }

    @Override
    public void navigateToEditForm() {
        Bundle args = new Bundle();

        args.putInt(Constants.ITEMPOS, 0);
        args.putParcelable(Constants.CONTENT, getItem());
        args.putInt(Constants.MODE, Constants.MODE_EDIT);

        Intent intent = new Intent(getActivity(), VehiclesDSItemFormActivity.class);
        intent.putExtras(args);
        startActivityForResult(intent, Constants.MODE_EDIT);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.delete_menu, menu);

        MenuItem item = menu.findItem(R.id.action_delete);
        ColorUtils.tintIcon(item, R.color.textBarColor, getActivity());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_delete){
            ((DetailCrudPresenter<VehiclesDSItem>) getPresenter()).deleteItem(getItem());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

