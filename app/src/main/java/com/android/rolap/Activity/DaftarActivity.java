package com.android.rolap.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.Validate;
import com.android.rolap.R;
import com.android.rolap.Rest.RequestAPI;
import com.android.rolap.Rest.Response.ResponseDaftar;
import com.android.rolap.Rest.RestApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarActivity extends AppCompatActivity implements View.OnClickListener {

    private Helper helper;
    private EditText etNama,etEmail,etTelepon,etPassword,etKonfirmasiPassword;
    private CheckBox cbMan,cbWoman;
    private Button btnDaftar;
    private String strNama,strEmail,strJenisKelamin,strTelepon,strPassword,strKonfirmasiPassword;
    private RelativeLayout progressbar;
    private ImageView ivKembali;
    private TextView tvLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        helper = new Helper(this);

        progressbar = findViewById(R.id.progressbar);
        etNama =  findViewById(R.id.etNama);
        etEmail =  findViewById(R.id.etEmail);
        cbMan =  findViewById(R.id.CBman);
        cbWoman =  findViewById(R.id.CBwoman);
        etTelepon =  findViewById(R.id.etTelepon);
        etPassword =  findViewById(R.id.etPassword);
        etKonfirmasiPassword =  findViewById(R.id.etKonfirmasiPassword);
        btnDaftar = findViewById(R.id.btnDaftar);
        ivKembali = findViewById(R.id.ivBack);
        tvLogin = findViewById(R.id.link_login);

        cbMan.setOnClickListener(this);
        cbWoman.setOnClickListener(this);
        btnDaftar.setOnClickListener(this);
        ivKembali.setOnClickListener(this);
        tvLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.CBman:
                if(cbMan.isChecked()){
                    cbWoman.setChecked(false);
                }
                break;
            case R.id.CBwoman:
                if(cbWoman.isChecked()){
                    cbMan.setChecked(false);
                }
                break;
            case R.id.btnDaftar:
                if (isValidate()) {
                    if (helper.isOnline()) {
                        getRegister();
                    } else {
                        helper.showToast(getString(R.string.msgNoCennection));
                    }
                }
                break;
            case R.id.link_login:
                Intent intLogin = new Intent(this, LoginActivity.class);
                ActivityOptions options =
                        null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in, R.anim.slide_out);
                    startActivity(intLogin, options.toBundle());
                }
                break;
        }
    }

    public void getRegister() {
        progressbar.setVisibility(View.VISIBLE);

        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponseDaftar> call = rolapAPI.postDaftar(strNama,strTelepon,strEmail,strJenisKelamin,strPassword,strKonfirmasiPassword);
        call.enqueue(new Callback<ResponseDaftar>() {
            @Override
            public void onResponse(Call<ResponseDaftar> call, Response<ResponseDaftar> response) {
                if (response.isSuccessful()) {
                    progressbar.setVisibility(View.GONE);
                    if (response.body().status == true) {
                        helper.showToast(response.body().message);
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
            public void onFailure(Call<ResponseDaftar> call, Throwable t) {
                progressbar.setVisibility(View.GONE);
                helper.showToast(getString(R.string.msgWrong));
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    public boolean isValidate() {
        strNama = etNama.getText().toString().trim();
        strEmail = etEmail.getText().toString().trim();
        strPassword = etPassword.getText().toString().trim();
        strTelepon = etTelepon.getText().toString().trim();
        strKonfirmasiPassword = etKonfirmasiPassword.getText().toString().trim();
        if (cbMan.isChecked()) {
            strJenisKelamin = "L";
        }else if(cbMan.isChecked()){
            strJenisKelamin = "P";
        }

        if (Validate.isNull(strNama)) {
            etNama.setError("Nama tidak boleh kosong");
            etNama.requestFocus();
            return false;
        } else if (Validate.isNull(strEmail)) {
            etEmail.setError("Email tidak boleh kosong");
            etEmail.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {
            etEmail.setError("Masukkan email yang valid");
            etEmail.requestFocus();
            return false;
        }else if (Validate.isNull(strJenisKelamin)) {
            cbMan.setError("Jenis Kelamin tidak boleh kosong");
            cbMan.requestFocus();
            return false;
        } else if (Validate.isNull(strPassword)) {
            etPassword.setError("Password tidak boleh kosong");
            etPassword.requestFocus();
            return false;
        } else if (Validate.isNull(strTelepon)) {
            etTelepon.setError("Telepon tidak boleh kosong");
            etTelepon.requestFocus();
            return false;
        } else if (Validate.isNull(strKonfirmasiPassword)) {
            etKonfirmasiPassword.setError("Konfirmasi Password anda");
            etKonfirmasiPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}
