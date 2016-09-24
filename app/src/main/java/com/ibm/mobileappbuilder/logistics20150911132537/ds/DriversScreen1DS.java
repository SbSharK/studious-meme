

package com.ibm.mobileappbuilder.logistics20150911132537.ds;

import android.content.Context;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.ds.restds.TypedByteArrayUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * "DriversScreen1DS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class DriversScreen1DS extends AppNowDatasource<DriversScreen1DSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private DriversScreen1DSService service;

    public static DriversScreen1DS getInstance(SearchOptions searchOptions){
        return new DriversScreen1DS(searchOptions);
    }

    private DriversScreen1DS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = DriversScreen1DSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<DriversScreen1DSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<DriversScreen1DSItem>>() {
                @Override
                public void onSuccess(List<DriversScreen1DSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new DriversScreen1DSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getDriversScreen1DSItemById(id, new Callback<DriversScreen1DSItem>() {
                @Override
                public void success(DriversScreen1DSItem result, Response response) {
                                        listener.onSuccess(result);
                }

                @Override
                public void failure(RetrofitError error) {
                                        listener.onFailure(error);
                }
            });
        }
    }

    @Override
    public void getItems(final Listener<List<DriversScreen1DSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<DriversScreen1DSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryDriversScreen1DSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<DriversScreen1DSItem>>() {
            @Override
            public void success(List<DriversScreen1DSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{"firstname", "lastname", "email", "address", "phone", "college"};
    }

    // Pagination

    @Override
    public int getPageSize(){
        return PAGE_SIZE;
    }

    @Override
    public void getUniqueValuesFor(String searchStr, final Listener<List<String>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
                service.getServiceProxy().distinct(searchStr, conditions, new Callback<List<String>>() {
             @Override
             public void success(List<String> result, Response response) {
                                  result.removeAll(Collections.<String>singleton(null));
                 listener.onSuccess(result);
             }

             @Override
             public void failure(RetrofitError error) {
                                  listener.onFailure(error);
             }
        });
    }

    @Override
    public URL getImageUrl(String path) {
        return service.getImageUrl(path);
    }

    @Override
    public void create(DriversScreen1DSItem item, Listener<DriversScreen1DSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().createDriversScreen1DSItem(item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().createDriversScreen1DSItem(item, callbackFor(listener));
        
    }

    private Callback<DriversScreen1DSItem> callbackFor(final Listener<DriversScreen1DSItem> listener) {
      return new Callback<DriversScreen1DSItem>() {
          @Override
          public void success(DriversScreen1DSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(DriversScreen1DSItem item, Listener<DriversScreen1DSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().updateDriversScreen1DSItem(item.getIdentifiableId(),
                item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().updateDriversScreen1DSItem(item.getIdentifiableId(), item, callbackFor(listener));
        
    }

    @Override
    public void deleteItem(DriversScreen1DSItem item, final Listener<DriversScreen1DSItem> listener) {
                service.getServiceProxy().deleteDriversScreen1DSItemById(item.getIdentifiableId(), new Callback<DriversScreen1DSItem>() {
            @Override
            public void success(DriversScreen1DSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<DriversScreen1DSItem> items, final Listener<DriversScreen1DSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<DriversScreen1DSItem>>() {
            @Override
            public void success(List<DriversScreen1DSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<DriversScreen1DSItem> items){
        List<String> ids = new ArrayList<>();
        for(DriversScreen1DSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}

