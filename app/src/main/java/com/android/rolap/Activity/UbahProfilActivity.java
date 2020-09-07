package com.android.rolap.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.android.rolap.Rest.Response.ResponseDaftar;
import com.android.rolap.Rest.Response.ResponseProfil;
import com.android.rolap.Rest.RestApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahProfilActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout progressbar;
    private ImageView ivKembali;
    private Helper helper;
    private EditText etNama,etEmail,etTelepon,etAlamat;
    private CheckBox cbMan,cbWoman;
    private PrefManager prefmanager;
    private Button btnSimpan,btnUbahPassword;
    private String strNama,strEmail,strJenisKelamin,strTelepon,strAlamat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_profil);

        helper = new Helper(this);
        prefmanager = new PrefManager(this);

        progressbar = findViewById(R.id.progressbar);
        etNama =  findViewById(R.id.etNama);
        etEmail =  findViewById(R.id.etEmail);
        cbMan =  findViewById(R.id.CBman);
        cbWoman =  findViewById(R.id.CBwoman);
        etTelepon =  findViewById(R.id.etTelepon);
        etAlamat =  findViewById(R.id.etAlamat);
        btnSimpan = findViewById(R.id.btnSimpan);
        ivKembali = findViewById(R.id.ivBack);
        btnUbahPassword = findViewById(R.id.btnUbahPassword);

        etNama.setText(String.valueOf(prefmanager.getNama()));
        etEmail.setText(String.valueOf(prefmanager.getEmail()));
        etTelepon.setText(String.valueOf(prefmanager.getTelepon()));
        etAlamat.setText(String.valueOf(prefmanager.getAlamat()));
        if(String.valueOf(prefmanager.getGender()).equalsIgnoreCase("L")){
            cbMan.setChecked(true);
        }else if(String.valueOf(prefmanager.getGender()).equalsIgnoreCase("P")){
            cbWoman.setChecked(true);
        }

        btnSimpan.setOnClickListener(this);
        btnUbahPassword.setOnClickListener(this);
        ivKembali.setOnClickListener(this);
        cbWoman.setOnClickListener(this);
        cbMan.setOnClickListener(this);
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
            case R.id.btnSimpan:
                if (isValidate()) {
                    if (helper.isOnline()) {
                        postUbahProfil();
                    } else {
                        helper.showToast(getString(R.string.msgNoCennection));
                    }
                }
                break;
            case R.id.btnUbahPassword:
                Intent intLogin = new Intent(this, MenuActivity.class);
                ActivityOptions options =
                        null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in, R.anim.slide_out);
                    startActivity(intLogin, options.toBundle());
                }
                break;
        }
    }

    public void postUbahProfil() {
        progressbar.setVisibility(View.VISIBLE);

        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponseProfil> call = rolapAPI.putUsers(prefmanager.getToken(),this.strNama,this.strAlamat,this.strJenisKelamin,this.strTelepon);
        call.enqueue(new Callback<ResponseProfil>() {
            @Override
            public void onResponse(Call<ResponseProfil> call, Response<ResponseProfil> response) {
                if (response.isSuccessful()) {
                    progressbar.setVisibility(View.GONE);
                    if (response.body().status == true) {
                        helper.showToast(response.body().message);
                        Intent intLogin = new Intent(UbahProfilActivity.this, MenuActivity.class);
                        ActivityOptions options = ActivityOptions.makeCustomAnimation(UbahProfilActivity.this, R.anim.slide_in, R.anim.slide_out);
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
            public void onFailure(Call<ResponseProfil> call, Throwable t) {
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
        strAlamat = etAlamat.getText().toString().trim();
        strTelepon = etTelepon.getText().toString().trim();
        if (cbMan.isChecked()) {
            strJenisKelamin = "L";
        }else if(cbWoman.isChecked()){
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
        }  else if (Validate.isNull(strTelepon)) {
            etTelepon.setError("Telepon tidak boleh kosong");
            etTelepon.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}
