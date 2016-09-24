
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

public interface MapDSServiceRest{

	@GET("/app/57e62c2ff222cb0300d486f6/r/mapDS")
	void queryMapDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<MapDSItem>> cb);

	@GET("/app/57e62c2ff222cb0300d486f6/r/mapDS/{id}")
	void getMapDSItemById(@Path("id") String id, Callback<MapDSItem> cb);

	@DELETE("/app/57e62c2ff222cb0300d486f6/r/mapDS/{id}")
  void deleteMapDSItemById(@Path("id") String id, Callback<MapDSItem> cb);

  @POST("/app/57e62c2ff222cb0300d486f6/r/mapDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<MapDSItem>> cb);

  @POST("/app/57e62c2ff222cb0300d486f6/r/mapDS")
  void createMapDSItem(@Body MapDSItem item, Callback<MapDSItem> cb);

  @PUT("/app/57e62c2ff222cb0300d486f6/r/mapDS/{id}")
  void updateMapDSItem(@Path("id") String id, @Body MapDSItem item, Callback<MapDSItem> cb);

  @GET("/app/57e62c2ff222cb0300d486f6/r/mapDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
}

