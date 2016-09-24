
package com.ibm.mobileappbuilder.logistics20150911132537.ds;
import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.POST;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Path;
import retrofit.http.PUT;

public interface ClientsDSServiceRest{

	@GET("/app/57e62c2ff222cb0300d486f6/r/clientsDS")
	void queryClientsDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<ClientsDSItem>> cb);

	@GET("/app/57e62c2ff222cb0300d486f6/r/clientsDS/{id}")
	void getClientsDSItemById(@Path("id") String id, Callback<ClientsDSItem> cb);

	@DELETE("/app/57e62c2ff222cb0300d486f6/r/clientsDS/{id}")
  void deleteClientsDSItemById(@Path("id") String id, Callback<ClientsDSItem> cb);

  @POST("/app/57e62c2ff222cb0300d486f6/r/clientsDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<ClientsDSItem>> cb);

  @POST("/app/57e62c2ff222cb0300d486f6/r/clientsDS")
  void createClientsDSItem(@Body ClientsDSItem item, Callback<ClientsDSItem> cb);

  @PUT("/app/57e62c2ff222cb0300d486f6/r/clientsDS/{id}")
  void updateClientsDSItem(@Path("id") String id, @Body ClientsDSItem item, Callback<ClientsDSItem> cb);

  @GET("/app/57e62c2ff222cb0300d486f6/r/clientsDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
}

