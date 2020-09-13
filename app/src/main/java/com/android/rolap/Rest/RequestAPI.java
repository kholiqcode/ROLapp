package com.android.rolap.Rest;


import com.android.rolap.Rest.Response.ResponseBasic;
import com.android.rolap.Rest.Response.ResponseDaftar;
import com.android.rolap.Rest.Response.ResponseJadwal;
import com.android.rolap.Rest.Response.ResponseKategori;
import com.android.rolap.Rest.Response.ResponseLogin;
import com.android.rolap.Rest.Response.ResponseOrder;
import com.android.rolap.Rest.Response.ResponsePembayaran;
import com.android.rolap.Rest.Response.ResponsePemesanan;
import com.android.rolap.Rest.Response.ResponseProfil;
import com.android.rolap.Rest.Response.ResponseSpa;
import com.android.rolap.Rest.Response.ResponseSpaSaya;
import com.android.rolap.Rest.Response.ResponseTutor;
import com.android.rolap.Rest.Response.ResponseTutorSaya;
import com.android.rolap.Rest.Response.ResponseUbahPassword;
import com.android.rolap.Rest.Response.ResponseUsers;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.android.rolap.Helper.Constant.WEBSERVICE_API_PATH;
import static com.android.rolap.Helper.Constant.WEBSERVICE_PATH;

public interface RequestAPI {

    @FormUrlEncoded
    @POST(WEBSERVICE_PATH+WEBSERVICE_API_PATH+"login")
    Call<ResponseLogin> postLogin(@Field("email") String email,
                                 @Field("password") String password);

    @FormUrlEncoded
    @POST(WEBSERVICE_PATH+WEBSERVICE_API_PATH+"daftar")
    Call<ResponseDaftar> postDaftar(@Field("nama") String nama,
                                   @Field("telepon") String telepon,
                                   @Field("email") String email,
                                    @Field("jenis_kelamin") String jenis_kelamin,
                                   @Field("password") String password,
                                   @Field("konfirmasi_password") String konfirmasi_password);

    @GET(WEBSERVICE_PATH+WEBSERVICE_API_PATH+"users")
    Call<ResponseUsers> getUser(@Query("apikey") String apikey);

    @GET(WEBSERVICE_PATH+WEBSERVICE_API_PATH+"kategori")
    Call<ResponseKategori> getKategori(@Query("apikey") String apikey);

    @GET(WEBSERVICE_PATH+WEBSERVICE_API_PATH+"tutor")
    Call<ResponseTutor> getTutor(@Query("apikey") String apikey,
                                 @Query("kid") String kid);

    @GET(WEBSERVICE_PATH+WEBSERVICE_API_PATH+"spa")
    Call<ResponseSpa> getSpa(@Query("apikey") String apikey);

    @GET(WEBSERVICE_PATH+WEBSERVICE_API_PATH+"tutor/tutor_saya")
    Call<ResponseTutorSaya> getTutorSaya(@Query("apikey") String apikey);

    @GET(WEBSERVICE_PATH+WEBSERVICE_API_PATH+"spa/spa_saya")
    Call<ResponseSpaSaya> getSpaSaya(@Query("apikey") String apikey);

    @GET(WEBSERVICE_PATH+WEBSERVICE_API_PATH+"pembayaran")
    Call<ResponsePembayaran> getPembayaran(@Query("apikey") String apikey);

    @GET(WEBSERVICE_PATH+WEBSERVICE_API_PATH+"pemesanan")
    Call<ResponseOrder> getOrder(@Query("apikey") String apikey);

    @GET(WEBSERVICE_PATH+WEBSERVICE_API_PATH+"pemesanan/jadwal")
    Call<ResponseJadwal> getJadwal(@Query("apikey") String apikey);

    @FormUrlEncoded
    @POST(WEBSERVICE_PATH+WEBSERVICE_API_PATH+"pemesanan")
    Call<ResponsePemesanan> postPemesanan(@Field("apikey") String apikey,
                                          @Field("tid") String tid,
                                          @Field("pid") String pid,
                                          @Field("tanggal") String strTanggal,
                                          @Field("waktu") String strJam);

    @Multipart
    @POST(WEBSERVICE_PATH+WEBSERVICE_API_PATH+"users/edit")
    Call<ResponseProfil> putUsers(@Part("apikey") RequestBody apikey,
                                  @Part("nama") RequestBody nama,
                                  @Part("alamat") RequestBody alamat,
                                  @Part("jenis_kelamin") RequestBody jenis_kelamin,
                                  @Part("telepon") RequestBody telepon,
                                  @Part("foto") RequestBody foto);

    @FormUrlEncoded
    @POST(WEBSERVICE_PATH+WEBSERVICE_API_PATH+"tutor/delete")
    Call<ResponseBasic> deleteTutor(@Field("apikey") String apikey,
                                    @Field("tid") String tid);

    @FormUrlEncoded
    @POST(WEBSERVICE_PATH+WEBSERVICE_API_PATH+"spa/delete")
    Call<ResponseBasic> deleteSpa(@Field("apikey") String apikey,
                                    @Field("sid") String sid);

    @FormUrlEncoded
    @PUT(WEBSERVICE_PATH+WEBSERVICE_API_PATH+"users/ubah_password")
    Call<ResponseUbahPassword> putUbahPassword(@Field("apikey") String apikey,
                                        @Field("password_lama") String password_lama,
                                        @Field("password_baru") String password_baru,
                                        @Field("konfirmasi_password") String konfirmasi_password);


}
