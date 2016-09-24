
package com.ibm.mobileappbuilder.logistics20150911132537.ui;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.View;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.DriversScreen1DSItem;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.DriversScreen1DSService;
import com.ibm.mobileappbuilder.logistics20150911132537.presenters.FriendsMenuItem1FormPresenter;
import com.ibm.mobileappbuilder.logistics20150911132537.R;
import ibmmobileappbuilder.ui.FormFragment;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.views.ImagePicker;
import ibmmobileappbuilder.views.TextWatcherAdapter;
import java.io.IOException;
import java.io.File;

import static android.net.Uri.fromFile;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.DriversScreen1DSItem;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.DriversScreen1DS;

public class DriversScreen1DSItemFormFragment extends FormFragment<DriversScreen1DSItem> {

    private CrudDatasource<DriversScreen1DSItem> datasource;

    public static DriversScreen1DSItemFormFragment newInstance(Bundle args){
        DriversScreen1DSItemFormFragment fr = new DriversScreen1DSItemFormFragment();
        fr.setArguments(args);

        return fr;
    }

    public DriversScreen1DSItemFormFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        // the presenter for this view
        setPresenter(new FriendsMenuItem1FormPresenter(
                (CrudDatasource) getDatasource(),
                this));

            }

    @Override
    protected DriversScreen1DSItem newItem() {
        return new DriversScreen1DSItem();
    }

    private DriversScreen1DSService getRestService(){
        return DriversScreen1DSService.getInstance();
    }

    @Override
    protected int getLayout() {
        return R.layout.friendsmenuitem1_form;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final DriversScreen1DSItem item, View view) {
        
        bindString(R.id.driversscreen1ds_firstname, item.firstname, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.firstname = s.toString();
            }
        });
        
        
        bindString(R.id.driversscreen1ds_lastname, item.lastname, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.lastname = s.toString();
            }
        });
        
        
        bindString(R.id.driversscreen1ds_email, item.email, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.email = s.toString();
            }
        });
        
        
        bindString(R.id.driversscreen1ds_address, item.address, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.address = s.toString();
            }
        });
        
        
        bindString(R.id.driversscreen1ds_phone, item.phone, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.phone = s.toString();
            }
        });
        
        
        bindImage(R.id.driversscreen1ds_picture,
            item.picture != null ?
                getRestService().getImageUrl(item.picture) : null,
            0,
            new ImagePicker.Callback(){
                @Override
                public void imageRemoved(){
                    item.picture = null;
                    item.pictureUri = null;
                    ((ImagePicker) getView().findViewById(R.id.driversscreen1ds_picture)).clear();
                }
            }
        );
        
        
        bindString(R.id.driversscreen1ds_college, item.college, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.college = s.toString();
            }
        });
        
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            ImagePicker picker = null;
            Uri imageUri = null;
            DriversScreen1DSItem item = getItem();

            if((requestCode & ImagePicker.GALLERY_REQUEST_CODE) == ImagePicker.GALLERY_REQUEST_CODE) {
              imageUri = data.getData();
              switch (requestCode - ImagePicker.GALLERY_REQUEST_CODE) {
                        
                        case 0:   // picture field
                            item.pictureUri = imageUri;
                            item.picture = "cid:picture";
                            picker = (ImagePicker) getView().findViewById(R.id.driversscreen1ds_picture);
                            break;
                        
                default:
                  return;
              }

              picker.setImageUri(imageUri);
            } else if((requestCode & ImagePicker.CAPTURE_REQUEST_CODE) == ImagePicker.CAPTURE_REQUEST_CODE) {
				      switch (requestCode - ImagePicker.CAPTURE_REQUEST_CODE) {
                        
                        case 0:   // picture field
                            picker = (ImagePicker) getView().findViewById(R.id.driversscreen1ds_picture);
                            imageUri = fromFile(picker.getImageFile());
                        		item.pictureUri = imageUri;
                            item.picture = "cid:picture";
                            break;
                        
                default:
                  return;
              }
              picker.setImageUri(imageUri);
            }
        }
    }
}

