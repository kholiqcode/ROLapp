package com.android.rolap.Activity;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.rolap.Helper.Constant;
import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.R;
import com.android.rolap.Rest.RequestAPI;
import com.android.rolap.Rest.Response.ResponseBasic;
import com.android.rolap.Rest.Response.ResponseProfil;
import com.android.rolap.Rest.RestApi;
import com.bumptech.glide.Glide;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPemesananActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvNamaTutor,tvTanggal,tvJam,tvTotal,tvAlamatTutor,tvJenisKelamin,tvTelepon,tvStatus;
    private Helper helper;
    private PrefManager prefmanager;
    private Button btnBayar;
    private CircleImageView civTutor;
    private RelativeLayout progressbar;
    private ImageView ivKembali;
    private Integer intTotal;
    private String strPid,strNamaTutor,strAlamatTutor,strJenisKelaminTutor,strTelepon,strTanggal,strJam,strTotal,strStatus,strFoto,strPembayaran,strRekening;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);

        helper = new Helper(this);
        prefmanager = new PrefManager(this);

        progressbar = findViewById(R.id.progressbar);
        ivKembali = findViewById(R.id.ivBack);
        tvNamaTutor = findViewById(R.id.tvNamaTutor);
        tvAlamatTutor = findViewById(R.id.tvAlamatTutor);
        civTutor = findViewById(R.id.civTutor);
        tvJenisKelamin = findViewById(R.id.tvJenisKelamin);
        tvTelepon = findViewById(R.id.tvTelepon);
        tvTanggal = findViewById(R.id.tvTanggal);
        tvJam = findViewById(R.id.tvJam);
        tvTotal = findViewById(R.id.tvTotal);
        tvStatus = findViewById(R.id.tvStatus);
        btnBayar = findViewById(R.id.btnBayar);

        strPid = getIntent().getStringExtra("pid");
        strNamaTutor = getIntent().getStringExtra("nama");
        strAlamatTutor = getIntent().getStringExtra("alamat");
        strJenisKelaminTutor = getIntent().getStringExtra("jenis_kelamin");
        strTelepon = getIntent().getStringExtra("telepon");
        strTanggal = getIntent().getStringExtra("tanggal");
        strJam = getIntent().getStringExtra("jam");
        strTotal = getIntent().getStringExtra("harga");
        strPembayaran = getIntent().getStringExtra("metode_pembayaran");
        strRekening = getIntent().getStringExtra("nomor_rekening");
        strFoto = getIntent().getStringExtra("foto");
        strStatus = getIntent().getStringExtra("status");
        intTotal = getIntent().getIntExtra("total",0);

        tvNamaTutor.setText(strNamaTutor);
        tvAlamatTutor.setText(strAlamatTutor);
        tvJenisKelamin.setText(strJenisKelaminTutor);
        tvTelepon.setText(strTelepon);
        tvTanggal.setText(strTanggal);
        tvJam.setText(strJam);
        tvStatus.setText(helper.convertStatus(strStatus));
        if(strStatus.equalsIgnoreCase("0")){
            btnBayar.setVisibility(View.VISIBLE);
        }
        tvTotal.setText("Rp "+Integer.toString(intTotal));
        if(strFoto == ""){
            Glide.with(getApplicationContext()).load(R.drawable.image_profil).into(civTutor);
        }else{
            Glide.with(getApplicationContext()).load(Constant.IMAGE_KATALOG+strFoto).into(civTutor);
        }

        checkRating();

        ivKembali.setOnClickListener(this);
        btnBayar.setOnClickListener(this);
    }

    public void ratingDialog() {
        final Dialog dialog = new Dialog(this, R.style.CustomDialog);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_rating);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);

        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnComment = dialog.findViewById(R.id.btnComment);
        final RatingBar rating = dialog.findViewById(R.id.rating);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strRating = String.valueOf(rating.getRating());
                if (helper.isOnline()) {
                    addRating(strRating);
                    dialog.dismiss();
                } else {
                    helper.showToast(getString(R.string.msgNoCennection));

                }
            }
        });
        dialog.show();
    }

    public void addRating(String strRate) {
        progressbar.setVisibility(View.VISIBLE);
        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponseBasic> call = rolapAPI.postRating(prefmanager.getToken(),strPid,strRate);
        call.enqueue(new Callback<ResponseBasic>() {
            @Override
            public void onResponse(Call<ResponseBasic> call, Response<ResponseBasic> response) {
                if (response.isSuccessful()) {
                    helper.showAlert(response.body().message);
                } else {
                    helper.showToast(getString(R.string.msgWrong));
                }
                progressbar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseBasic> call, Throwable t) {
                progressbar.setVisibility(View.GONE);
                helper.showToast(getString(R.string.msgWrong));
                t.printStackTrace();
            }
        });
    }

    public void checkRating() {
        progressbar.setVisibility(View.VISIBLE);
        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponseBasic> call = rolapAPI.checkRating(prefmanager.getToken(),strPid);
        call.enqueue(new Callback<ResponseBasic>() {
            @Override
            public void onResponse(Call<ResponseBasic> call, Response<ResponseBasic> response) {
                if (response.isSuccessful()) {
                    if (response.body().status == false) {
                        ratingDialog();
                        helper.showToast(response.body().message);
                    }
                }
                progressbar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseBasic> call, Throwable t) {
                progressbar.setVisibility(View.GONE);
                helper.showToast(getString(R.string.msgWrong));
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.btnBayar:
                Intent intent = new Intent(this, PembayaranActivity.class);
                intent.putExtra("metode_pembayaran", strPembayaran);
                intent.putExtra("nomor_rekening", strRekening);
                intent.putExtra("total_pembayaran", intTotal.toString());
                ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.anim_left, R.anim.anim_right);
                startActivity(intent, options.toBundle());
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
