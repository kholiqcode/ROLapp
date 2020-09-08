package com.android.rolap.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.Helper.Validate;
import com.android.rolap.R;
import com.android.rolap.Rest.RequestAPI;
import com.android.rolap.Rest.Response.ResponseProfil;
import com.android.rolap.Rest.Response.ResponseUbahPassword;
import com.android.rolap.Rest.RestApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private Helper helper;
    private PrefManager prefmanager;
    private ImageView ivKembali;
    private EditText etPasswordLama,etPasswordBaru,etKonfirmasiPassword;
    private Button btnSimpan;
    private RelativeLayout progressbar;
    private String strPasswordLama,strPasswordBaru,strKonfirmasiPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_password);

        helper = new Helper(this);
        prefmanager = new PrefManager(this);

        ivKembali = findViewById(R.id.ivBack);
        etPasswordBaru = findViewById(R.id.etPasswordBaru);
        etPasswordLama = findViewById(R.id.etPasswordLama);
        etKonfirmasiPassword = findViewById(R.id.etKonfirmasiPassword);
        btnSimpan = findViewById(R.id.btnSimpan);
        progressbar = findViewById(R.id.progressbar);

        ivKembali.setOnClickListener(this);
        btnSimpan.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.btnSimpan:
                if (isValidate()) {
                    if (helper.isOnline()) {
                        postUbahPassword();
                    } else {
                        helper.showToast(getString(R.string.msgNoCennection));
                    }
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    public boolean isValidate() {
        strPasswordLama = etPasswordLama.getText().toString();
        strPasswordBaru = etPasswordBaru.getText().toString();
        strKonfirmasiPassword = etKonfirmasiPassword.getText().toString();

       if (Validate.isNull(strPasswordLama)) {
            etPasswordLama.setError("Harap masukkan password lama anda");
            etPasswordLama.requestFocus();
            return false;
        }  else if (Validate.isNull(strPasswordBaru)) {
            etPasswordLama.setError("Harap masukkan password baru anda");
            etPasswordLama.requestFocus();
            return false;
        } else if (Validate.isNull(strKonfirmasiPassword)) {
           etKonfirmasiPassword.setError("Konfirmasi Password baru anda");
           etKonfirmasiPassword.requestFocus();
           return false;
       } else {
            return true;
        }
    }

    public void postUbahPassword() {
        progressbar.setVisibility(View.VISIBLE);

        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponseUbahPassword> call = rolapAPI.putUbahPassword(prefmanager.getToken(),this.strPasswordLama,this.strPasswordBaru,this.strKonfirmasiPassword);
        call.enqueue(new Callback<ResponseUbahPassword>() {
            @Override
            public void onResponse(Call<ResponseUbahPassword> call, Response<ResponseUbahPassword> response) {
                if (response.isSuccessful()) {
                    progressbar.setVisibility(View.GONE);
                    if (response.body().status == true) {
                        helper.showToast(response.body().message);
                        Intent intLogin = new Intent(UbahPasswordActivity.this, MenuActivity.class);
                        ActivityOptions options = ActivityOptions.makeCustomAnimation(UbahPasswordActivity.this, R.anim.slide_in, R.anim.slide_out);
                        startActivity(intLogin, options.toBundle());

                    } else {
                        helper.showToast(response.body().message);
                    }
                } else {
                    progressbar.setVisibility(View.GONE);
                    helper.showToast(getString(R.string.msgWrong));
                }
            }

            @Override
            public void onFailure(Call<ResponseUbahPassword> call, Throwable t) {
                progressbar.setVisibility(View.GONE);
                helper.showToast(getString(R.string.msgWrong));
                t.printStackTrace();
            }
        });
    }
}
