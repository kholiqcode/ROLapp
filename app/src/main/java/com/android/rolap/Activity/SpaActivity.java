package com.android.rolap.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.R;

public class SpaActivity extends AppCompatActivity implements View.OnClickListener {

    private Helper helper;
    private PrefManager prefmanager;
    private RelativeLayout rlSpaSaya,rlTambahSpa;
    private ImageView ivKembali;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spa);

        helper = new Helper(this);
        prefmanager = new PrefManager(this);

        ivKembali = findViewById(R.id.ivBack);
        rlSpaSaya = findViewById(R.id.rlSpaSaya);
        rlTambahSpa = findViewById(R.id.rlTambahSpa);

        ivKembali.setOnClickListener(this);
        rlSpaSaya.setOnClickListener(this);
        rlTambahSpa.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.rlSpaSaya:
                Intent intent = new Intent(this, SpaSayaActivity.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.anim_left, R.anim.anim_right);
                startActivity(intent, options.toBundle());
                break;
            case R.id.rlTambahSpa:
                Intent intent1 = new Intent(this, TambahSpaActivity.class);
                ActivityOptions options1 = ActivityOptions.makeCustomAnimation(this, R.anim.anim_left, R.anim.anim_right);
                startActivity(intent1, options1.toBundle());
                break;
        }
    }
}
