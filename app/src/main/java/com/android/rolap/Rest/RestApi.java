package com.android.rolap.Rest;

import com.android.rolap.BuildConfig;
import com.android.rolap.Helper.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApi {
    static final String WEBSERVICE_PATH = Constant.WEBSERVICE_PATH;
    private static final String TAG = RestApi.class.getSimpleName();

    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static RequestAPI createAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestApi.WEBSERVICE_PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getRetrofitClient())
                .build();

        return retrofit.create(RequestAPI.class);
    }

    private static OkHttpClient getRetrofitClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.readTimeout(60, TimeUnit.SECONDS);
        client.connectTimeout(60, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG)
            client.addInterceptor(logging);
        return client.build();
    }
}
