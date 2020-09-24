package com.android.rolap.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.R;

public class PembayaranActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout progressbar;
    private ImageView ivKembali;
    private Helper helper;
    private PrefManager prefmanager;
    private Button btnHome;
    private TextView tvMetodePembayaran,tvNorek,tvTotalPembayaran,tvKeterangan;
    private String strMetodePembayaran,strNorek,strTotalPembayaran;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        helper = new Helper(this);
        prefmanager = new PrefManager(this);

        progressbar = findViewById(R.id.progressbar);
        tvMetodePembayaran = findViewById(R.id.tvMetodePembayaran);
        tvNorek = findViewById(R.id.tvNorek);
        ivKembali = findViewById(R.id.ivBack);
        btnHome = findViewById(R.id.btnHome);
        tvTotalPembayaran = findViewById(R.id.tvTotalPembayaran);
        tvKeterangan = findViewById(R.id.tvKeterangan);

        strMetodePembayaran = getIntent().getStringExtra("metode_pembayaran");
        strNorek = getIntent().getStringExtra("nomor_rekening");
        strTotalPembayaran = getIntent().getStringExtra("total_pembayaran");

        tvMetodePembayaran.setText(strMetodePembayaran);
        tvNorek.setText(strNorek);
        tvTotalPembayaran.setText("Rp "+strTotalPembayaran);
        tvKeterangan.setText("Harap lakukan pembayaran sebelum jam 23.59 dan konfirmasi ke WA ("+prefmanager.getNoWa()+")");

        btnHome.setOnClickListener(this);
        ivKembali.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.btnHome:
                Intent intent = new Intent(PembayaranActivity.this, MenuActivity.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(PembayaranActivity.this, R.anim.anim_left, R.anim.anim_right);
                startActivity(intent, options.toBundle());
                break;
        }
    }
}
