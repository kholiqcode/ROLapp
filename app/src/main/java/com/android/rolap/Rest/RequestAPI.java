package com.android.rolap.Rest;


import com.android.rolap.Rest.Response.ResponseDaftar;
import com.android.rolap.Rest.Response.ResponseJadwal;
import com.android.rolap.Rest.Response.ResponseKategori;
import com.android.rolap.Rest.Response.ResponseLogin;
import com.android.rolap.Rest.Response.ResponseOrder;
import com.android.rolap.Rest.Response.ResponsePembayaran;
import com.android.rolap.Rest.Response.ResponsePemesanan;
import com.android.rolap.Rest.Response.ResponseProfil;
import com.android.rolap.Rest.Response.ResponseTutor;
import com.android.rolap.Rest.Response.ResponseUbahPassword;
import com.android.rolap.Rest.Response.ResponseUsers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @GET("pembayaran")
    Call<ResponsePembayaran> getPembayaran(@Query("apikey") String apikey);

    @GET("pemesanan")
    Call<ResponseOrder> getOrder(@Query("apikey") String apikey);

    @GET("pemesanan/jadwal")
    Call<ResponseJadwal> getJadwal(@Query("apikey") String apikey);

    @FormUrlEncoded
    @POST("pemesanan")
    Call<ResponsePemesanan> postPemesanan(@Field("apikey") String apikey,
                                          @Field("tid") String tid,
                                          @Field("pid") String pid,
                                          @Field("tanggal") String strTanggal,
                                          @Field("waktu") String strJam);

    @FormUrlEncoded
    @PUT("users")
    Call<ResponseProfil> putUsers(@Field("apikey") String apikey,
                                  @Field("nama") String nama,
                                  @Field("alamat") String alamat,
                                  @Field("jenis_kelamin") String jenis_kelamin,
                                  @Field("telepon") String telepon);

    @FormUrlEncoded
    @PUT("users/ubah_password")
    Call<ResponseUbahPassword> putUbahPassword(@Field("apikey") String apikey,
                                        @Field("password_lama") String password_lama,
                                        @Field("password_baru") String password_baru,
                                        @Field("konfirmasi_password") String konfirmasi_password);


}
