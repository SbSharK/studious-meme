
package com.ibm.mobileappbuilder.logistics20150911132537.presenters;

import com.ibm.mobileappbuilder.logistics20150911132537.R;
import com.ibm.mobileappbuilder.logistics20150911132537.ds.DriversScreen1DSItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BaseFormPresenter;
import ibmmobileappbuilder.mvp.view.FormView;

public class FriendsMenuItem1FormPresenter extends BaseFormPresenter<DriversScreen1DSItem> {

    private final CrudDatasource<DriversScreen1DSItem> datasource;

    public FriendsMenuItem1FormPresenter(CrudDatasource<DriversScreen1DSItem> datasource, FormView<DriversScreen1DSItem> view){
        super(view);
        this.datasource = datasource;
    }

    @Override
    public void deleteItem(DriversScreen1DSItem item) {
        datasource.deleteItem(item, new OnItemDeletedListener());
    }

    @Override
    public void save(DriversScreen1DSItem item) {
        // validate
        if (validate(item)){
            datasource.updateItem(item, new OnItemUpdatedListener());
        } else {
            view.showMessage(R.string.correct_errors, false);
        }
    }

    @Override
    public void create(DriversScreen1DSItem item) {
        // validate
        if (validate(item)){
            datasource.create(item, new OnItemCreatedListener());
        } else {
            view.showMessage(R.string.correct_errors, false);
        }
    }

    private class OnItemDeletedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(DriversScreen1DSItem  item) {
                        view.showMessage(R.string.item_deleted, true);
            view.close(true);
        }
    }

    private class OnItemUpdatedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(DriversScreen1DSItem item) {
                        view.setItem(item);
            view.showMessage(R.string.item_updated, true);
            view.close(true);
        }
    }

    private class OnItemCreatedListener extends ShowingErrorOnFailureListener {
        @Override
        public void onSuccess(DriversScreen1DSItem item) {
                        view.setItem(item);
            view.showMessage(R.string.item_created, true);
            view.close(true);
        }
    }

    private abstract class ShowingErrorOnFailureListener implements Datasource.Listener<DriversScreen1DSItem > {
        @Override
        public void onFailure(Exception e) {
            view.showMessage(R.string.error_data_generic, true);
        }
    }

}

