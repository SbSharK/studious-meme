

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
 * "MapDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class MapDS extends AppNowDatasource<MapDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private MapDSService service;

    public static MapDS getInstance(SearchOptions searchOptions){
        return new MapDS(searchOptions);
    }

    private MapDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = MapDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<MapDSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<MapDSItem>>() {
                @Override
                public void onSuccess(List<MapDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new MapDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getMapDSItemById(id, new Callback<MapDSItem>() {
                @Override
                public void success(MapDSItem result, Response response) {
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
    public void getItems(final Listener<List<MapDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<MapDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryMapDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<MapDSItem>>() {
            @Override
            public void success(List<MapDSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{null};
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
    public void create(MapDSItem item, Listener<MapDSItem> listener) {
                          service.getServiceProxy().createMapDSItem(item, callbackFor(listener));
          }

    private Callback<MapDSItem> callbackFor(final Listener<MapDSItem> listener) {
      return new Callback<MapDSItem>() {
          @Override
          public void success(MapDSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(MapDSItem item, Listener<MapDSItem> listener) {
                          service.getServiceProxy().updateMapDSItem(item.getIdentifiableId(), item, callbackFor(listener));
          }

    @Override
    public void deleteItem(MapDSItem item, final Listener<MapDSItem> listener) {
                service.getServiceProxy().deleteMapDSItemById(item.getIdentifiableId(), new Callback<MapDSItem>() {
            @Override
            public void success(MapDSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<MapDSItem> items, final Listener<MapDSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<MapDSItem>>() {
            @Override
            public void success(List<MapDSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<MapDSItem> items){
        List<String> ids = new ArrayList<>();
        for(MapDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}

