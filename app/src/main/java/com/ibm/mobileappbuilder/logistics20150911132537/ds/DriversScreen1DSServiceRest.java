
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
import retrofit.mime.TypedByteArray;
import retrofit.http.Part;
import retrofit.http.Multipart;

public interface DriversScreen1DSServiceRest{

	@GET("/app/57e62c2ff222cb0300d486f6/r/driversScreen1DS")
	void queryDriversScreen1DSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<DriversScreen1DSItem>> cb);

	@GET("/app/57e62c2ff222cb0300d486f6/r/driversScreen1DS/{id}")
	void getDriversScreen1DSItemById(@Path("id") String id, Callback<DriversScreen1DSItem> cb);

	@DELETE("/app/57e62c2ff222cb0300d486f6/r/driversScreen1DS/{id}")
  void deleteDriversScreen1DSItemById(@Path("id") String id, Callback<DriversScreen1DSItem> cb);

  @POST("/app/57e62c2ff222cb0300d486f6/r/driversScreen1DS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<DriversScreen1DSItem>> cb);

  @POST("/app/57e62c2ff222cb0300d486f6/r/driversScreen1DS")
  void createDriversScreen1DSItem(@Body DriversScreen1DSItem item, Callback<DriversScreen1DSItem> cb);

  @PUT("/app/57e62c2ff222cb0300d486f6/r/driversScreen1DS/{id}")
  void updateDriversScreen1DSItem(@Path("id") String id, @Body DriversScreen1DSItem item, Callback<DriversScreen1DSItem> cb);

  @GET("/app/57e62c2ff222cb0300d486f6/r/driversScreen1DS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
    
    @Multipart
    @POST("/app/57e62c2ff222cb0300d486f6/r/driversScreen1DS")
    void createDriversScreen1DSItem(
        @Part("data") DriversScreen1DSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<DriversScreen1DSItem> cb);
    
    @Multipart
    @PUT("/app/57e62c2ff222cb0300d486f6/r/driversScreen1DS/{id}")
    void updateDriversScreen1DSItem(
        @Path("id") String id,
        @Part("data") DriversScreen1DSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<DriversScreen1DSItem> cb);
}

