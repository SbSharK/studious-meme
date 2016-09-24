
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
import com.ibm.mobileappbuilder.logistics20150911132537.ds.DriversScreen1DSService;
import com.ibm.mobileappbuilder.logistics20150911132537.presenters.FriendsMenuItem1DetailPresenter;
import com.ibm.mobileappbuilder.logistics20150911132537.R;
import ibmmobileappbuilder.actions.ActivityIntentLauncher;
import ibmmobileappbuilder.actions.MailAction;
import ibmmobileappbuilder.actions.MapsAction;
import ibmmobileappbuilder.actions.PhoneAction;
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
import com.ibm.mobileappbuilder.logistics20150911132537.ds.DriversScreen1DSItem;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.DriversScreen1DS;

public class FriendsMenuItem1DetailFragment extends ibmmobileappbuilder.ui.DetailFragment<DriversScreen1DSItem>  {

    private CrudDatasource<DriversScreen1DSItem> datasource;
    public static FriendsMenuItem1DetailFragment newInstance(Bundle args){
        FriendsMenuItem1DetailFragment fr = new FriendsMenuItem1DetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public FriendsMenuItem1DetailFragment(){
        super();
    }

    @Override
    public Datasource<DriversScreen1DSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = DriversScreen1DS.getInstance(new SearchOptions());
        return datasource;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        // the presenter for this view
        setPresenter(new FriendsMenuItem1DetailPresenter(
                (CrudDatasource) getDatasource(),
                this));
        // Edit button
        addBehavior(new FabBehaviour(this, R.drawable.ic_edit_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DetailCrudPresenter<DriversScreen1DSItem>) getPresenter()).editForm(getItem());
            }
        }));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.friendsmenuitem1detail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final DriversScreen1DSItem item, View view) {
        
        ImageView view0 = (ImageView) view.findViewById(R.id.view0);
        URL view0Media = ((AppNowDatasource) getDatasource()).getImageUrl(item.picture);
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
        if (item.firstname != null && item.lastname != null){
            
            TextView view1 = (TextView) view.findViewById(R.id.view1);
            view1.setText(item.firstname + " " + item.lastname + " ");
            
        }
        if (item.phone != null){
            
            TextView view2 = (TextView) view.findViewById(R.id.view2);
            view2.setText(item.phone);
            bindAction(view2, new PhoneAction(
            new ActivityIntentLauncher()
            , item.phone));
        }
        if (item.email != null){
            
            TextView view3 = (TextView) view.findViewById(R.id.view3);
            view3.setText(item.email);
            bindAction(view3, new MailAction(
            new ActivityIntentLauncher()
            , item.email));
        }
        if (item.address != null){
            
            TextView view4 = (TextView) view.findViewById(R.id.view4);
            view4.setText(item.address);
            bindAction(view4, new MapsAction(
            new ActivityIntentLauncher()
            , "http://maps.google.com/maps?q=" + item.address));
        }
    }

    @Override
    protected void onShow(DriversScreen1DSItem item) {
        // set the title for this fragment
        getActivity().setTitle("Friends");
    }

    @Override
    public void navigateToEditForm() {
        Bundle args = new Bundle();

        args.putInt(Constants.ITEMPOS, 0);
        args.putParcelable(Constants.CONTENT, getItem());
        args.putInt(Constants.MODE, Constants.MODE_EDIT);

        Intent intent = new Intent(getActivity(), DriversScreen1DSItemFormActivity.class);
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
            ((DetailCrudPresenter<DriversScreen1DSItem>) getPresenter()).deleteItem(getItem());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

