package com.example.fantgo.retrofit;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface FantInterface{

    // LOGIN AND REGISTRATION

    @GET("auth/login")
    public Call<ResponseBody> login(@Query("uid") String username,
                                    @Query("pwd") String password);

    @FormUrlEncoded
    @POST("auth/create")
    public Call<ResponseBody> registerUser(@Field("uid") String username,
                                           @Field("pwd") String password,
                                           @Field("email") String email);

    // ITEM SERVICES

    @GET("item/allitems")
    public Call<ResponseBody> getAllItems();

    @PUT("item/purchase")
    public Call<ResponseBody> purchaseItem(@Query("itemid") Long itemid);

    @FormUrlEncoded
    @POST("item/add")
    public Call<ResponseBody> addItem(@Field("item") String itemname,
                                      @Field("description") String description,
                                      @Field("price") int price);
}
