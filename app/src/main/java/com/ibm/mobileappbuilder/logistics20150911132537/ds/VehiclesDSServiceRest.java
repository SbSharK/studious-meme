
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

public interface VehiclesDSServiceRest{

	@GET("/app/57e62c2ff222cb0300d486f6/r/vehiclesDS")
	void queryVehiclesDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<VehiclesDSItem>> cb);

	@GET("/app/57e62c2ff222cb0300d486f6/r/vehiclesDS/{id}")
	void getVehiclesDSItemById(@Path("id") String id, Callback<VehiclesDSItem> cb);

	@DELETE("/app/57e62c2ff222cb0300d486f6/r/vehiclesDS/{id}")
  void deleteVehiclesDSItemById(@Path("id") String id, Callback<VehiclesDSItem> cb);

  @POST("/app/57e62c2ff222cb0300d486f6/r/vehiclesDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<VehiclesDSItem>> cb);

  @POST("/app/57e62c2ff222cb0300d486f6/r/vehiclesDS")
  void createVehiclesDSItem(@Body VehiclesDSItem item, Callback<VehiclesDSItem> cb);

  @PUT("/app/57e62c2ff222cb0300d486f6/r/vehiclesDS/{id}")
  void updateVehiclesDSItem(@Path("id") String id, @Body VehiclesDSItem item, Callback<VehiclesDSItem> cb);

  @GET("/app/57e62c2ff222cb0300d486f6/r/vehiclesDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
    
    @Multipart
    @POST("/app/57e62c2ff222cb0300d486f6/r/vehiclesDS")
    void createVehiclesDSItem(
        @Part("data") VehiclesDSItem item,
        @Part("carImage") TypedByteArray carImage,
        Callback<VehiclesDSItem> cb);
    
    @Multipart
    @PUT("/app/57e62c2ff222cb0300d486f6/r/vehiclesDS/{id}")
    void updateVehiclesDSItem(
        @Path("id") String id,
        @Part("data") VehiclesDSItem item,
        @Part("carImage") TypedByteArray carImage,
        Callback<VehiclesDSItem> cb);
}

