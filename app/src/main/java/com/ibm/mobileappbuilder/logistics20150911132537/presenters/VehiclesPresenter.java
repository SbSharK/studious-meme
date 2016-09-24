
package com.ibm.mobileappbuilder.logistics20150911132537.presenters;

import com.ibm.mobileappbuilder.logistics20150911132537.R;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.VehiclesDSItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.ListCrudPresenter;
import ibmmobileappbuilder.mvp.view.CrudListView;

public class VehiclesPresenter extends BasePresenter implements ListCrudPresenter<VehiclesDSItem>,
      Datasource.Listener<VehiclesDSItem>{

    private final CrudDatasource<VehiclesDSItem> crudDatasource;
    private final CrudListView<VehiclesDSItem> view;

    public VehiclesPresenter(CrudDatasource<VehiclesDSItem> crudDatasource,
                                         CrudListView<VehiclesDSItem> view) {
       this.crudDatasource = crudDatasource;
       this.view = view;
    }

    @Override
    public void deleteItem(VehiclesDSItem item) {
        crudDatasource.deleteItem(item, this);
    }

    @Override
    public void deleteItems(List<VehiclesDSItem> items) {
        crudDatasource.deleteItems(items, this);
    }

    @Override
    public void addForm() {
        view.showAdd();
    }

    @Override
    public void editForm(VehiclesDSItem item, int position) {
        view.showEdit(item, position);
    }

    @Override
    public void detail(VehiclesDSItem item, int position) {
        view.showDetail(item, position);
    }

    @Override
    public void onSuccess(VehiclesDSItem item) {
                view.showMessage(R.string.items_deleted);
        view.refresh();
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic);
    }

}

