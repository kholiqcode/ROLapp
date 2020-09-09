package com.android.rolap.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.rolap.Adapter.JadwalAdapter;
import com.android.rolap.Adapter.TutorAdapter;
import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.R;
import com.android.rolap.Rest.RequestAPI;
import com.android.rolap.Rest.Response.ResponseJadwal;
import com.android.rolap.Rest.Response.ResponseTutor;
import com.android.rolap.Rest.RestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class JadwalSayaActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView rvJadwal;
    private Helper helper;
    private PrefManager prefmanager;
    private RelativeLayout progressbar;
    private TextView tvNoData;
    private List<ResponseJadwal.Data> jadwalList;
    private JadwalAdapter jadwalAdapter;
    private ImageView ivKembali;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_saya);

        helper = new Helper(this);
        prefmanager = new PrefManager(this);

        tvNoData = findViewById(R.id.tvNoData);
        progressbar = findViewById(R.id.progressbar);
        ivKembali = findViewById(R.id.ivBack);
        rvJadwal = findViewById(R.id.rvJadwalSaya);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvJadwal.setLayoutManager(layoutManager);

        if (helper.isOnline()) {
            getJadwalList();
        } else {
            helper.showToast(getString(R.string.msgNoCennection));
        }

        ivKembali.setOnClickListener(this);
    }

    public void getJadwalList() {
        progressbar.setVisibility(View.VISIBLE);
        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponseJadwal> call = rolapAPI.getJadwal(prefmanager.getToken());
        call.enqueue(new Callback<ResponseJadwal>() {
            @Override
            public void onResponse(Call<ResponseJadwal> call, Response<ResponseJadwal> response) {
                if (response.isSuccessful()) {
                    jadwalList = response.body().data;
                    jadwalAdapter = new JadwalAdapter(JadwalSayaActivity.this,jadwalList);
                    rvJadwal.setAdapter(jadwalAdapter);

                    helper.AnimationLeft(rvJadwal);

                    if (jadwalList.size() == 0) {
                        tvNoData.setVisibility(View.VISIBLE);
                        rvJadwal.setVisibility(View.GONE);
                    } else {
                        tvNoData.setVisibility(View.GONE);
                        rvJadwal.setVisibility(View.VISIBLE);
                    }
                    progressbar.setVisibility(View.GONE);

                    jadwalAdapter.setOnDetailClick(new JadwalAdapter.OnDetailsClick() {
                        @Override
                        public void onDetailClick(int position) {
                            Intent intent = new Intent(JadwalSayaActivity.this, DetailJadwalActivity.class);
                            intent.putExtra("pid", jadwalList.get(position).id);
                            intent.putExtra("nama_tutor", jadwalList.get(position).nama_tutor);
                            intent.putExtra("nama_user", jadwalList.get(position).nama_user);
                            intent.putExtra("jenis_kelamin", jadwalList.get(position).jenis_kelamin);
                            intent.putExtra("telepon", jadwalList.get(position).telepon);
                            intent.putExtra("alamat", jadwalList.get(position).alamat);
                            intent.putExtra("foto", jadwalList.get(position).foto);
                            intent.putExtra("metode_pembayaran", jadwalList.get(position).metode_pembayaran);
                            intent.putExtra("tanggal", jadwalList.get(position).tanggal);
                            intent.putExtra("jam", jadwalList.get(position).waktu);
                            intent.putExtra("status", jadwalList.get(position).status);
                            ActivityOptions options = ActivityOptions.makeCustomAnimation(JadwalSayaActivity.this, R.anim.slide_in, R.anim.slide_out);
                            startActivity(intent, options.toBundle());

                        }
                    });

                } else {
                    helper.showToast(getString(R.string.msgWrong));
                }
            }

            @Override
            public void onFailure(Call<ResponseJadwal> call, Throwable t) {
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
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}
