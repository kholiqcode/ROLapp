package com.android.rolap.Activity;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.Helper.Validate;
import com.android.rolap.R;
import com.android.rolap.Rest.RequestAPI;
import com.android.rolap.Rest.Response.ResponseLogin;
import com.android.rolap.Rest.RestApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail,etPassword;
    private String strEmail,strPassword;
    private Button btnLogin;
    private RelativeLayout progressbar;
    private TextView tvDaftar;
    private ImageView ivKembali;
    private Helper helper;
    private PrefManager prefmanager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        helper = new Helper(this);
        prefmanager = new PrefManager(this);

        progressbar = findViewById(R.id.progressbar);
        etEmail =  findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvDaftar = findViewById(R.id.tvDaftar);
        ivKembali = findViewById(R.id.ivBack);

        tvDaftar.setOnClickListener(this);
        ivKembali.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnLogin:
                if (isValidate()) {
                    if (helper.isOnline()) {
                        getLogin();
                    } else {
                        helper.showToast(getString(R.string.msgNoCennection));
                    }
                }
                break;
            case R.id.tvDaftar:
                Intent intRegister = new Intent(this, DaftarActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityOptions options1 = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in, R.anim.slide_out);
                    startActivity(intRegister, options1.toBundle());
                }
                break;
            case R.id.ivBack:
                onBackPressed();
                break;
        }
    }

    public boolean isValidate() {
        strEmail = etEmail.getText().toString().trim();
        strPassword = etPassword.getText().toString().trim();
        if (Validate.isNull(strEmail)) {
            etEmail.setError("Email tidak boleh kosong.");
            etEmail.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {
            etEmail.setError("Tolong masukkan email yang valid.");
            etEmail.requestFocus();
            return false;
        } else if (Validate.isNull(strPassword)) {
            etPassword.setError("Password tidak boleh kosong.");
            etPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Apakah anda yakin ingin keluar?")
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.exit(1);
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void getLogin() {
        progressbar.setVisibility(View.VISIBLE);
        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponseLogin> call = rolapAPI.postLogin(strEmail, strPassword);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                if (response.isSuccessful()) {
                    progressbar.setVisibility(View.GONE);
                    if (response.body().status == true) {
                        helper.showToast(response.body().message);
                        prefmanager.setToken(response.body().token);
                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            ActivityOptions options1 = ActivityOptions.makeCustomAnimation(LoginActivity.this, R.anim.slide_in, R.anim.slide_out);
                            startActivity(intent, options1.toBundle());
                        }
                        finish();
                    } else {
                        helper.showToast(response.body().message);
                    }
                } else {
                    progressbar.setVisibility(View.GONE);
                    helper.showToast(getString(R.string.msgWrong));
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                progressbar.setVisibility(View.GONE);
                helper.showToast(getString(R.string.msgWrong));
                t.printStackTrace();
            }
        });
    }
}
