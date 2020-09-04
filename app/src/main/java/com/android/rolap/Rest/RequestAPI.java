package com.android.rolap.Rest;


import com.android.rolap.Rest.Response.ResponseDaftar;
import com.android.rolap.Rest.Response.ResponseKategori;
import com.android.rolap.Rest.Response.ResponseLogin;
import com.android.rolap.Rest.Response.ResponseTutor;
import com.android.rolap.Rest.Response.ResponseUsers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestAPI {

    @FormUrlEncoded
    @POST("login")
    Call<ResponseLogin> postLogin(@Field("email") String email,
                                 @Field("password") String password);

    @FormUrlEncoded
    @POST("daftar")
    Call<ResponseDaftar> postDaftar(@Field("nama") String nama,
                                   @Field("telepon") String telepon,
                                   @Field("email") String email,
                                    @Field("jenis_kelamin") String jenis_kelamin,
                                   @Field("password") String password,
                                   @Field("konfirmasi_password") String konfirmasi_password);

    @GET("users")
    Call<ResponseUsers> getUser(@Query("apikey") String apikey);

    @GET("kategori")
    Call<ResponseKategori> getKategori(@Query("apikey") String apikey);

    @GET("tutor")
    Call<ResponseTutor> getTutor(@Query("apikey") String apikey,
                                 @Query("kid") String kid);


}
