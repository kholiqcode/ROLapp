package com.android.rolap.Activity;

import android.os.Bundle;
import android.view.View;
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
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailTutorSayaActivity extends AppCompatActivity implements View.OnClickListener {

    private Helper helper;
    private PrefManager prefmanager;
    private RelativeLayout progressbar;
    private ImageView ivKembali;
    private TextView tvNama,tvAlamat,tvJenisKelamin,tvTelepon,tvTanggal,tvTotalTransaksi,tvHarga;
    private String strNama,strAlamat,strJenisKelamin,strTelepon,strTotalTrx,strFoto,strHarga,strRateAvg;
    private RatingBar rbTutor;
    private CircleImageView civTutor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tutor_saya);

        helper = new Helper(this);
        prefmanager = new PrefManager(this);

        progressbar = findViewById(R.id.progressbar);
        ivKembali = findViewById(R.id.ivBack);
        tvNama = findViewById(R.id.tvNamaTutor);
        tvAlamat = findViewById(R.id.tvAlamatTutor);
        tvJenisKelamin = findViewById(R.id.tvJenisKelamin);
        tvTelepon = findViewById(R.id.tvTelepon);
        tvTotalTransaksi = findViewById(R.id.tvTotalTransaksi);
        tvHarga = findViewById(R.id.tvHargaTutor);
        rbTutor = findViewById(R.id.rbTutor);
        civTutor = findViewById(R.id.civTutor);

        strNama = getIntent().getStringExtra("nama");
        strAlamat = getIntent().getStringExtra("alamat");
        strJenisKelamin = getIntent().getStringExtra("jenis_kelamin");
        strTelepon = getIntent().getStringExtra("telepon");
        strRateAvg = getIntent().getStringExtra("rate_avg");
        strFoto = getIntent().getStringExtra("foto");
        strTotalTrx = getIntent().getStringExtra("total_trx");
        strHarga = getIntent().getStringExtra("harga");

        tvNama.setText(strNama);
        tvAlamat.setText(strAlamat);
        tvJenisKelamin.setText(strJenisKelamin);
        tvTelepon.setText(strTelepon);
        tvHarga.setText("Rp "+strHarga);
        tvTotalTransaksi.setText(strTotalTrx);
        rbTutor.setRating(Float.parseFloat(strRateAvg));
        if(strFoto == "" || strFoto.isEmpty()){
            Glide.with(getApplicationContext()).load(R.drawable.image_profil).into(civTutor);
        }else{
            Glide.with(getApplicationContext()).load(Constant.IMAGE_KATALOG+strFoto).into(civTutor);
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
}
