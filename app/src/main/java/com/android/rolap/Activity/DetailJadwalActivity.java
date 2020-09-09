package com.android.rolap.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.R;

public class DetailJadwalActivity extends AppCompatActivity implements View.OnClickListener {

    private Helper helper;
    private PrefManager prefmanager;
    private RelativeLayout progressbar;
    private ImageView ivKembali;
    private TextView tvNamaUser,tvAlamatUser,tvJenisKelamin,tvTeleponUser,tvTanggal,tvJam,tvStatus,tvTotal;
    private String strNamaUser,strAlamatUser,strJenisKelaminUser,strTeleponUser,strTanggal,strJam,strStatus,strTotal,strFotoUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jadwal);

        helper = new Helper(this);
        prefmanager = new PrefManager(this);

        progressbar = findViewById(R.id.progressbar);
        ivKembali = findViewById(R.id.ivBack);
        tvNamaUser = findViewById(R.id.tvNamaUser);
        tvAlamatUser = findViewById(R.id.tvAlamatUser);
        tvJenisKelamin = findViewById(R.id.tvJenisKelamin);
        tvTeleponUser = findViewById(R.id.tvTeleponUser);
        tvTanggal = findViewById(R.id.tvTanggal);
        tvJam = findViewById(R.id.tvJam);
        tvStatus = findViewById(R.id.tvStatus);
        tvTotal = findViewById(R.id.tvTotal);

        strNamaUser = getIntent().getStringExtra("nama_user");
        strAlamatUser = getIntent().getStringExtra("alamat");
        strJenisKelaminUser = getIntent().getStringExtra("jenis_kelamin");
        strTeleponUser = getIntent().getStringExtra("telepon");
        strTanggal = getIntent().getStringExtra("tanggal");
        strJam = getIntent().getStringExtra("jam");
        strTotal = getIntent().getStringExtra("harga");
        strFotoUser = getIntent().getStringExtra("foto");
        strStatus = getIntent().getStringExtra("status");

        tvNamaUser.setText(strNamaUser);
        tvAlamatUser.setText(strAlamatUser);
        tvJenisKelamin.setText(strJenisKelaminUser);
        tvTeleponUser.setText(strTeleponUser);
        tvTanggal.setText(strTanggal);
        tvJam.setText(strJam);
        tvStatus.setText(helper.convertStatus(strStatus));
        tvTotal.setText(strTotal);

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
}
