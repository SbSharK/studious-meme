

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
 * "ClientsDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class ClientsDS extends AppNowDatasource<ClientsDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private ClientsDSService service;

    public static ClientsDS getInstance(SearchOptions searchOptions){
        return new ClientsDS(searchOptions);
    }

    private ClientsDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = ClientsDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<ClientsDSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<ClientsDSItem>>() {
                @Override
                public void onSuccess(List<ClientsDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new ClientsDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getClientsDSItemById(id, new Callback<ClientsDSItem>() {
                @Override
                public void success(ClientsDSItem result, Response response) {
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
    public void getItems(final Listener<List<ClientsDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<ClientsDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryClientsDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<ClientsDSItem>>() {
            @Override
            public void success(List<ClientsDSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{"name", "phone", "email", "address", "city", "country"};
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
    public void create(ClientsDSItem item, Listener<ClientsDSItem> listener) {
                          service.getServiceProxy().createClientsDSItem(item, callbackFor(listener));
          }

    private Callback<ClientsDSItem> callbackFor(final Listener<ClientsDSItem> listener) {
      return new Callback<ClientsDSItem>() {
          @Override
          public void success(ClientsDSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(ClientsDSItem item, Listener<ClientsDSItem> listener) {
                          service.getServiceProxy().updateClientsDSItem(item.getIdentifiableId(), item, callbackFor(listener));
          }

    @Override
    public void deleteItem(ClientsDSItem item, final Listener<ClientsDSItem> listener) {
                service.getServiceProxy().deleteClientsDSItemById(item.getIdentifiableId(), new Callback<ClientsDSItem>() {
            @Override
            public void success(ClientsDSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<ClientsDSItem> items, final Listener<ClientsDSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<ClientsDSItem>>() {
            @Override
            public void success(List<ClientsDSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<ClientsDSItem> items){
        List<String> ids = new ArrayList<>();
        for(ClientsDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}

