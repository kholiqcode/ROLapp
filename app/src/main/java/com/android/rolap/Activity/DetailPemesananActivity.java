package com.android.rolap.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.rolap.Helper.Constant;
import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.R;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailPemesananActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvNamaTutor,tvTanggal,tvJam,tvTotal,tvAlamatTutor,tvJenisKelamin,tvTelepon,tvStatus;
    private Helper helper;
    private PrefManager prefmanager;
    private Button btnHome;
    private CircleImageView civTutor;
    private RelativeLayout progressbar;
    private ImageView ivKembali;
    private Integer intTotal;
    private String strNamaTutor,strAlamatTutor,strJenisKelaminTutor,strTelepon,strTanggal,strJam,strTotal,strStatus,strFoto;

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

        strNamaTutor = getIntent().getStringExtra("nama");
        strAlamatTutor = getIntent().getStringExtra("alamat");
        strJenisKelaminTutor = getIntent().getStringExtra("jenis_kelamin");
        strTelepon = getIntent().getStringExtra("telepon");
        strTanggal = getIntent().getStringExtra("tanggal");
        strJam = getIntent().getStringExtra("jam");
        strTotal = getIntent().getStringExtra("harga");
        strFoto = getIntent().getStringExtra("foto");
        strStatus = getIntent().getStringExtra("status");
        intTotal = getIntent().getIntExtra("total",0);

        Log.d("harga",""+strTotal);

        tvNamaTutor.setText(strNamaTutor);
        tvAlamatTutor.setText(strAlamatTutor);
        tvJenisKelamin.setText(strJenisKelaminTutor);
        tvTelepon.setText(strTelepon);
        tvTanggal.setText(strTanggal);
        tvJam.setText(strJam);
        tvStatus.setText(helper.convertStatus(strStatus));
        tvTotal.setText("Rp "+Integer.toString(intTotal));
        if(strFoto == ""){
            Glide.with(getApplicationContext()).load(R.drawable.image_profil).into(civTutor);
        }else{
            Glide.with(getApplicationContext()).load(Constant.WEBSERVICE_IMAGE+"tutor/"+strFoto).into(civTutor);
        }

        ivKembali.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.ivBack:
                onBackPressed();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
