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

import com.android.rolap.Adapter.SpaSayaAdapter;
import com.android.rolap.Adapter.TutorSayaAdapter;
import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.R;
import com.android.rolap.Rest.RequestAPI;
import com.android.rolap.Rest.Response.ResponseBasic;
import com.android.rolap.Rest.Response.ResponseSpaSaya;
import com.android.rolap.Rest.Response.ResponseTutorSaya;
import com.android.rolap.Rest.RestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpaSayaActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rvSpaSaya;
    private Helper helper;
    private PrefManager prefmanager;
    private RelativeLayout progressbar;
    private TextView tvNoData;
    private List<ResponseSpaSaya.Data> spasayaList;
    private SpaSayaAdapter spaSayaAdapter;
    private ImageView ivKembali;
    private LinearLayout llBottom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spa_saya);

        helper = new Helper(this);
        prefmanager = new PrefManager(this);

        tvNoData = findViewById(R.id.tvNoData);
        progressbar = findViewById(R.id.progressbar);
        ivKembali = findViewById(R.id.ivBack);
        rvSpaSaya = findViewById(R.id.rvSpaSaya);
        llBottom = findViewById(R.id.llBottom);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvSpaSaya.setLayoutManager(layoutManager);

        if (helper.isOnline()) {
            getSpaSaya(0);
        } else {
            helper.showToast(getString(R.string.msgNoCennection));
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
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    public void getSpaSaya(final int from) {
        progressbar.setVisibility(View.VISIBLE);
        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponseSpaSaya> call = rolapAPI.getSpaSaya(prefmanager.getToken());
        call.enqueue(new Callback<ResponseSpaSaya>() {
            @Override
            public void onResponse(Call<ResponseSpaSaya> call, Response<ResponseSpaSaya> response) {
                if (response.isSuccessful()) {
                    spasayaList = response.body().data;
                    spaSayaAdapter = new SpaSayaAdapter(SpaSayaActivity.this,spasayaList);
                    rvSpaSaya.setAdapter(spaSayaAdapter);

                    if (from == 0) {
                        helper.AnimationLeft(rvSpaSaya);
                    }

                    if (spasayaList.size() == 0) {
                        tvNoData.setVisibility(View.VISIBLE);
                        rvSpaSaya.setVisibility(View.GONE);
                        llBottom.setVisibility(View.GONE);
                    } else {
                        tvNoData.setVisibility(View.GONE);
                        rvSpaSaya.setVisibility(View.VISIBLE);
                        llBottom.setVisibility(View.VISIBLE);
                    }
                    progressbar.setVisibility(View.GONE);

                    spaSayaAdapter.setOnDetailClick(new SpaSayaAdapter.OnDetailsClick() {
                        @Override
                        public void onDetailClick(int position) {
                            Intent intent = new Intent(SpaSayaActivity.this, DetailJadwalActivity.class);
                            intent.putExtra("pid", spasayaList.get(position).id);
                            intent.putExtra("nama_tutor", spasayaList.get(position).nama);
                            ActivityOptions options = ActivityOptions.makeCustomAnimation(SpaSayaActivity.this, R.anim.slide_in, R.anim.slide_out);
                            startActivity(intent, options.toBundle());

                        }
                    });

                    spaSayaAdapter.setOnDeleteCLick(new SpaSayaAdapter.OnDeleteClick() {
                        @Override
                        public void onDeleteClick(int position) {
                            deleteSpa(spasayaList.get(position).id);
                        }
                    });

                } else {
                    helper.showToast(getString(R.string.msgWrong));
                }
            }

            @Override
            public void onFailure(Call<ResponseSpaSaya> call, Throwable t) {
                helper.showToast(getString(R.string.msgWrong));
                t.printStackTrace();
            }
        });
        progressbar.setVisibility(View.GONE);
    }

    public void deleteSpa(String sid) {
        progressbar.setVisibility(View.VISIBLE);
        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponseBasic> call = rolapAPI.deleteTutor(prefmanager.getToken(),sid);
        call.enqueue(new Callback<ResponseBasic>() {
            @Override
            public void onResponse(Call<ResponseBasic> call, Response<ResponseBasic> response) {
                if (response.isSuccessful()) {
                    helper.showToast(response.body().message);
                    getSpaSaya(1);
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
}
