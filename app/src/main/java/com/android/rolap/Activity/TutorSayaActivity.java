package com.android.rolap.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.rolap.Adapter.JadwalAdapter;
import com.android.rolap.Adapter.TutorSayaAdapter;
import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.R;
import com.android.rolap.Rest.RequestAPI;
import com.android.rolap.Rest.Response.ResponseBasic;
import com.android.rolap.Rest.Response.ResponseJadwal;
import com.android.rolap.Rest.Response.ResponseTutorSaya;
import com.android.rolap.Rest.RestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TutorSayaActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rvTutorSaya;
    private Helper helper;
    private PrefManager prefmanager;
    private RelativeLayout progressbar;
    private TextView tvNoData;
    private List<ResponseTutorSaya.Data> tutorsayaList;
    private TutorSayaAdapter tutorSayaAdapter;
    private ImageView ivKembali;
    private LinearLayout llBottom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_saya);

        helper = new Helper(this);
        prefmanager = new PrefManager(this);

        tvNoData = findViewById(R.id.tvNoData);
        progressbar = findViewById(R.id.progressbar);
        ivKembali = findViewById(R.id.ivBack);
        rvTutorSaya = findViewById(R.id.rvTutorSaya);
        llBottom = findViewById(R.id.llBottom);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvTutorSaya.setLayoutManager(layoutManager);

        if (helper.isOnline()) {
            getTutorSaya(0);
        } else {
            helper.showToast(getString(R.string.msgNoCennection));
        }

        ivKembali.setOnClickListener(this);
    }

    public void getTutorSaya(final int from) {
        progressbar.setVisibility(View.VISIBLE);
        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponseTutorSaya> call = rolapAPI.getTutorSaya(prefmanager.getToken());
        call.enqueue(new Callback<ResponseTutorSaya>() {
            @Override
            public void onResponse(Call<ResponseTutorSaya> call, Response<ResponseTutorSaya> response) {
                if (response.isSuccessful()) {
                    tutorsayaList = response.body().data;
                    tutorSayaAdapter = new TutorSayaAdapter(TutorSayaActivity.this,tutorsayaList);
                    rvTutorSaya.setAdapter(tutorSayaAdapter);

                    if (from == 0) {
                        helper.AnimationLeft(rvTutorSaya);
                    }

                    if (tutorsayaList.size() == 0) {
                        tvNoData.setVisibility(View.VISIBLE);
                        rvTutorSaya.setVisibility(View.GONE);
                        llBottom.setVisibility(View.GONE);
                    } else {
                        tvNoData.setVisibility(View.GONE);
                        rvTutorSaya.setVisibility(View.VISIBLE);
                        llBottom.setVisibility(View.VISIBLE);
                    }
                    progressbar.setVisibility(View.GONE);

                    tutorSayaAdapter.setOnDetailClick(new TutorSayaAdapter.OnDetailsClick() {
                        @Override
                        public void onDetailClick(int position) {
                            Intent intent = new Intent(TutorSayaActivity.this, DetailJadwalActivity.class);
                            intent.putExtra("pid", tutorsayaList.get(position).id);
                            intent.putExtra("nama_tutor", tutorsayaList.get(position).nama);
                            ActivityOptions options = ActivityOptions.makeCustomAnimation(TutorSayaActivity.this, R.anim.slide_in, R.anim.slide_out);
                            startActivity(intent, options.toBundle());

                        }
                    });

                    tutorSayaAdapter.setOnDeleteCLick(new TutorSayaAdapter.OnDeleteClick() {
                        @Override
                        public void onDeleteClick(int position) {
                            deleteTutor(tutorsayaList.get(position).id);
                        }
                    });

                } else {
                    helper.showToast(getString(R.string.msgWrong));
                }
            }

            @Override
            public void onFailure(Call<ResponseTutorSaya> call, Throwable t) {
                helper.showToast(getString(R.string.msgWrong));
                t.printStackTrace();
            }
        });

    }

    public void deleteTutor(String kid) {
        progressbar.setVisibility(View.VISIBLE);
        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponseBasic> call = rolapAPI.deleteTutor(prefmanager.getToken(),kid);
        call.enqueue(new Callback<ResponseBasic>() {
            @Override
            public void onResponse(Call<ResponseBasic> call, Response<ResponseBasic> response) {
                if (response.isSuccessful()) {
                    helper.showToast(response.body().message);
                    getTutorSaya(1);
                } else {
                    helper.showToast(getString(R.string.msgWrong));
                }
            }

            @Override
            public void onFailure(Call<ResponseBasic> call, Throwable t) {
                helper.showToast(getString(R.string.msgWrong));
                t.printStackTrace();
            }
        });
        progressbar.setVisibility(View.GONE);
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
