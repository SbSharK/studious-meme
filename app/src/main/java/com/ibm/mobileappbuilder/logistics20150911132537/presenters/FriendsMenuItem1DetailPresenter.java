
package com.ibm.mobileappbuilder.logistics20150911132537.presenters;

import com.ibm.mobileappbuilder.logistics20150911132537.R;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.DriversScreen1DSItem;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.mvp.view.DetailView;

public class FriendsMenuItem1DetailPresenter extends BasePresenter implements DetailCrudPresenter<DriversScreen1DSItem>,
      Datasource.Listener<DriversScreen1DSItem> {

    private final CrudDatasource<DriversScreen1DSItem> datasource;
    private final DetailView view;

    public FriendsMenuItem1DetailPresenter(CrudDatasource<DriversScreen1DSItem> datasource, DetailView view){
        this.datasource = datasource;
        this.view = view;
    }

    @Override
    public void deleteItem(DriversScreen1DSItem item) {
        datasource.deleteItem(item, this);
    }

    @Override
    public void editForm(DriversScreen1DSItem item) {
        view.navigateToEditForm();
    }

    @Override
    public void onSuccess(DriversScreen1DSItem item) {
                view.showMessage(R.string.item_deleted, true);
        view.close(true);
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic, true);
    }
}

