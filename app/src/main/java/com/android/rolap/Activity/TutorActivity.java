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

public class TutorActivity extends AppCompatActivity implements View.OnClickListener {

    private Helper helper;
    private PrefManager prefmanager;
    private RelativeLayout rlTutorSaya,rlTambahTutor;
    private ImageView ivKembali;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);

        helper = new Helper(this);
        prefmanager = new PrefManager(this);

        ivKembali = findViewById(R.id.ivBack);
        rlTutorSaya = findViewById(R.id.rlTutorSaya);
        rlTambahTutor = findViewById(R.id.rlTambahTutor);

        ivKembali.setOnClickListener(this);
        rlTutorSaya.setOnClickListener(this);
        rlTambahTutor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.rlTutorSaya:
                Intent intent = new Intent(this, TutorSayaActivity.class);
                ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.anim_left, R.anim.anim_right);
                startActivity(intent, options.toBundle());
                break;
            case R.id.rlTambahTutor:
//                Intent intent = new Intent(this, PembayaranActivity.class);
//                ActivityOptions options = ActivityOptions.makeCustomAnimation(this, R.anim.anim_left, R.anim.anim_right);
//                startActivity(intent, options.toBundle());
                break;
        }
    }
}
