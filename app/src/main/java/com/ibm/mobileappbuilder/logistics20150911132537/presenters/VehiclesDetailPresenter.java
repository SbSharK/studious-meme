
package com.ibm.mobileappbuilder.logistics20150911132537.presenters;

import com.ibm.mobileappbuilder.logistics20150911132537.R;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.VehiclesDSItem;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.mvp.view.DetailView;

public class VehiclesDetailPresenter extends BasePresenter implements DetailCrudPresenter<VehiclesDSItem>,
      Datasource.Listener<VehiclesDSItem> {

    private final CrudDatasource<VehiclesDSItem> datasource;
    private final DetailView view;

    public VehiclesDetailPresenter(CrudDatasource<VehiclesDSItem> datasource, DetailView view){
        this.datasource = datasource;
        this.view = view;
    }

    @Override
    public void deleteItem(VehiclesDSItem item) {
        datasource.deleteItem(item, this);
    }

    @Override
    public void editForm(VehiclesDSItem item) {
        view.navigateToEditForm();
    }

    @Override
    public void onSuccess(VehiclesDSItem item) {
                view.showMessage(R.string.item_deleted, true);
        view.close(true);
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic, true);
    }
}

