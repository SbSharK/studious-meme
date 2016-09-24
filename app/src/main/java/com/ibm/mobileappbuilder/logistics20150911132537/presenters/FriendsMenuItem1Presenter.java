
package com.ibm.mobileappbuilder.logistics20150911132537.presenters;

import com.ibm.mobileappbuilder.logistics20150911132537.R;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.DriversScreen1DSItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.ListCrudPresenter;
import ibmmobileappbuilder.mvp.view.CrudListView;

public class FriendsMenuItem1Presenter extends BasePresenter implements ListCrudPresenter<DriversScreen1DSItem>,
      Datasource.Listener<DriversScreen1DSItem>{

    private final CrudDatasource<DriversScreen1DSItem> crudDatasource;
    private final CrudListView<DriversScreen1DSItem> view;

    public FriendsMenuItem1Presenter(CrudDatasource<DriversScreen1DSItem> crudDatasource,
                                         CrudListView<DriversScreen1DSItem> view) {
       this.crudDatasource = crudDatasource;
       this.view = view;
    }

    @Override
    public void deleteItem(DriversScreen1DSItem item) {
        crudDatasource.deleteItem(item, this);
    }

    @Override
    public void deleteItems(List<DriversScreen1DSItem> items) {
        crudDatasource.deleteItems(items, this);
    }

    @Override
    public void addForm() {
        view.showAdd();
    }

    @Override
    public void editForm(DriversScreen1DSItem item, int position) {
        view.showEdit(item, position);
    }

    @Override
    public void detail(DriversScreen1DSItem item, int position) {
        view.showDetail(item, position);
    }

    @Override
    public void onSuccess(DriversScreen1DSItem item) {
                view.showMessage(R.string.items_deleted);
        view.refresh();
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic);
    }

}

