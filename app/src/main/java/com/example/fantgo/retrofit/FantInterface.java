package com.example.fantgo.retrofit;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FantInterface{

    @GET("auth/login")
    public Call<ResponseBody> login(@Query("uid") String username,
                                    @Query("pwd") String password);

    @FormUrlEncoded
    @POST("auth/create")
    public Call<ResponseBody> registerUser(@Field("uid") String username,
                                           @Field("pwd") String password,
                                           @Field("email") String email);

    @GET("item/allitems")
    public Call<ResponseBody> getAllItems();
}
