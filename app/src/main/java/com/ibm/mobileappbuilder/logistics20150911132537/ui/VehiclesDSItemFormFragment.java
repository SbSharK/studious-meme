
package com.ibm.mobileappbuilder.logistics20150911132537.ui;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.View;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.VehiclesDSItem;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.VehiclesDSService;
import com.ibm.mobileappbuilder.logistics20150911132537.presenters.VehiclesFormPresenter;
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
import com.ibm.mobileappbuilder.logistics20150911132537.ds.VehiclesDSItem;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.VehiclesDS;

public class VehiclesDSItemFormFragment extends FormFragment<VehiclesDSItem> {

    private CrudDatasource<VehiclesDSItem> datasource;

    public static VehiclesDSItemFormFragment newInstance(Bundle args){
        VehiclesDSItemFormFragment fr = new VehiclesDSItemFormFragment();
        fr.setArguments(args);

        return fr;
    }

    public VehiclesDSItemFormFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        // the presenter for this view
        setPresenter(new VehiclesFormPresenter(
                (CrudDatasource) getDatasource(),
                this));

            }

    @Override
    protected VehiclesDSItem newItem() {
        return new VehiclesDSItem();
    }

    private VehiclesDSService getRestService(){
        return VehiclesDSService.getInstance();
    }

    @Override
    protected int getLayout() {
        return R.layout.vehicles_form;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final VehiclesDSItem item, View view) {
        
        bindString(R.id.vehiclesds_nameofcar, item.nameofCar, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.nameofCar = s.toString();
            }
        });
        
        
        bindString(R.id.vehiclesds_model, item.model, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.model = s.toString();
            }
        });
        
        
        bindImage(R.id.vehiclesds_carimage,
            item.carImage != null ?
                getRestService().getImageUrl(item.carImage) : null,
            0,
            new ImagePicker.Callback(){
                @Override
                public void imageRemoved(){
                    item.carImage = null;
                    item.carImageUri = null;
                    ((ImagePicker) getView().findViewById(R.id.vehiclesds_carimage)).clear();
                }
            }
        );
        
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            ImagePicker picker = null;
            Uri imageUri = null;
            VehiclesDSItem item = getItem();

            if((requestCode & ImagePicker.GALLERY_REQUEST_CODE) == ImagePicker.GALLERY_REQUEST_CODE) {
              imageUri = data.getData();
              switch (requestCode - ImagePicker.GALLERY_REQUEST_CODE) {
                        
                        case 0:   // carImage field
                            item.carImageUri = imageUri;
                            item.carImage = "cid:carImage";
                            picker = (ImagePicker) getView().findViewById(R.id.vehiclesds_carimage);
                            break;
                        
                default:
                  return;
              }

              picker.setImageUri(imageUri);
            } else if((requestCode & ImagePicker.CAPTURE_REQUEST_CODE) == ImagePicker.CAPTURE_REQUEST_CODE) {
				      switch (requestCode - ImagePicker.CAPTURE_REQUEST_CODE) {
                        
                        case 0:   // carImage field
                            picker = (ImagePicker) getView().findViewById(R.id.vehiclesds_carimage);
                            imageUri = fromFile(picker.getImageFile());
                        		item.carImageUri = imageUri;
                            item.carImage = "cid:carImage";
                            break;
                        
                default:
                  return;
              }
              picker.setImageUri(imageUri);
            }
        }
    }
}

