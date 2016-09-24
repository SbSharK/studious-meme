

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
 * "VehiclesDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class VehiclesDS extends AppNowDatasource<VehiclesDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private VehiclesDSService service;

    public static VehiclesDS getInstance(SearchOptions searchOptions){
        return new VehiclesDS(searchOptions);
    }

    private VehiclesDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = VehiclesDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<VehiclesDSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<VehiclesDSItem>>() {
                @Override
                public void onSuccess(List<VehiclesDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new VehiclesDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getVehiclesDSItemById(id, new Callback<VehiclesDSItem>() {
                @Override
                public void success(VehiclesDSItem result, Response response) {
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
    public void getItems(final Listener<List<VehiclesDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<VehiclesDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryVehiclesDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<VehiclesDSItem>>() {
            @Override
            public void success(List<VehiclesDSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{"nameofCar", "model"};
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
    public void create(VehiclesDSItem item, Listener<VehiclesDSItem> listener) {
                    
        if(item.carImageUri != null){
            service.getServiceProxy().createVehiclesDSItem(item,
                TypedByteArrayUtils.fromUri(item.carImageUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().createVehiclesDSItem(item, callbackFor(listener));
        
    }

    private Callback<VehiclesDSItem> callbackFor(final Listener<VehiclesDSItem> listener) {
      return new Callback<VehiclesDSItem>() {
          @Override
          public void success(VehiclesDSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(VehiclesDSItem item, Listener<VehiclesDSItem> listener) {
                    
        if(item.carImageUri != null){
            service.getServiceProxy().updateVehiclesDSItem(item.getIdentifiableId(),
                item,
                TypedByteArrayUtils.fromUri(item.carImageUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().updateVehiclesDSItem(item.getIdentifiableId(), item, callbackFor(listener));
        
    }

    @Override
    public void deleteItem(VehiclesDSItem item, final Listener<VehiclesDSItem> listener) {
                service.getServiceProxy().deleteVehiclesDSItemById(item.getIdentifiableId(), new Callback<VehiclesDSItem>() {
            @Override
            public void success(VehiclesDSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<VehiclesDSItem> items, final Listener<VehiclesDSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<VehiclesDSItem>>() {
            @Override
            public void success(List<VehiclesDSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<VehiclesDSItem> items){
        List<String> ids = new ArrayList<>();
        for(VehiclesDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}

