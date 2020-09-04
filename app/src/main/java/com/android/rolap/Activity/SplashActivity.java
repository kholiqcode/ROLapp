package com.android.rolap.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.R;
import com.android.rolap.Rest.RequestAPI;
import com.android.rolap.Rest.Response.ResponseUsers;
import com.android.rolap.Rest.RestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    private PrefManager prefmanager;
    private Helper helper;
    private List<ResponseUsers.DataUser> dataUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        prefmanager = new PrefManager(this);
        helper = new Helper(this);

        ImageView ivSplash = findViewById(R.id.ivLogo);

        Animation anim =  new RotateAnimation(0f,360f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.ABSOLUTE);
        anim.setDuration(3000);

        ivSplash.setAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (helper.isOnline()) {
                    getInfo();
                    finish();
                } else {
                    helper.showToast(getString(R.string.msgNoCennection));
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        ActivityOptions options1 = ActivityOptions.makeCustomAnimation(SplashActivity.this, R.anim.slide_in, R.anim.slide_out);
                        startActivity(intent, options1.toBundle());
                    }
                }
//                Log.d("isFirstTime",""+prefmanager.isFirstTime());
//                launchHomeScreen();
            }
        }, 3000);
    }

    public void getInfo(){
        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponseUsers> call = rolapAPI.getUser(prefmanager.getToken());
        call.enqueue(new Callback<ResponseUsers>() {
            @Override
            public void onResponse(Call<ResponseUsers> call, Response<ResponseUsers> response) {
                if (response.isSuccessful()) {
                    dataUser = response.body().data;
                    prefmanager.setNama(dataUser.get(0).getNama());
                    prefmanager.setEmail(dataUser.get(0).getEmail());
                    prefmanager.setTelepon(dataUser.get(0).getTelepon());
                    prefmanager.setGender(dataUser.get(0).getJenis_kelamin());
                    prefmanager.setAlamat(dataUser.get(0).getAlamat());
                    prefmanager.setFoto(dataUser.get(0).getFoto());
                    Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
                    ActivityOptions options1 =
                            null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        options1 = ActivityOptions.makeCustomAnimation(SplashActivity.this, R.anim.slide_in, R.anim.slide_out);
                        startActivity(intent, options1.toBundle());
                    }
                } else {
                    helper.showToast(getString(R.string.msgWrong));
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        ActivityOptions options1 = ActivityOptions.makeCustomAnimation(SplashActivity.this, R.anim.slide_in, R.anim.slide_out);
                        startActivity(intent, options1.toBundle());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseUsers> call, Throwable t) {
                helper.showToast(getString(R.string.msgWrong));
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityOptions options1 = ActivityOptions.makeCustomAnimation(SplashActivity.this, R.anim.slide_in, R.anim.slide_out);
                    startActivity(intent, options1.toBundle());
                }
                t.printStackTrace();
            }
        });
    }
}
