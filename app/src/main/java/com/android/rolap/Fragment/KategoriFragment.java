package com.android.rolap.Fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.rolap.Activity.MenuActivity;
import com.android.rolap.Adapter.KategoriAdapter;
import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.R;
import com.android.rolap.Rest.RequestAPI;
import com.android.rolap.Rest.Response.ResponseKategori;
import com.android.rolap.Rest.RestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KategoriFragment extends Fragment {
    private View view;
    private Helper helper;
    private PrefManager prefmanager;
    private RelativeLayout progressbar;
    private RecyclerView rvKategori;
    private List<ResponseKategori.Data> kategoriList;
    private TextView tvNoData;
    private KategoriAdapter kategoriAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_kategori, container, false);

        helper = new Helper(getActivity());
        prefmanager = new PrefManager(getActivity());

        tvNoData = view.findViewById(R.id.tvNoData);
        progressbar = view.findViewById(R.id.progressbar);
        rvKategori = view.findViewById(R.id.rvKategori);
        rvKategori.setNestedScrollingEnabled(false);
        rvKategori.setHasFixedSize(false);

        prefmanager = new PrefManager(getActivity());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvKategori.setLayoutManager(layoutManager);
        if (helper.isOnline()) {
            getKategoriList();
        } else {
            helper.showToast(getString(R.string.msgNoCennection));
        }
        return view;
    }

    public void getKategoriList() {
        progressbar.setVisibility(View.VISIBLE);
        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponseKategori> call = rolapAPI.getKategori(prefmanager.getToken());
        call.enqueue(new Callback<ResponseKategori>() {
            @Override
            public void onResponse(Call<ResponseKategori> call, Response<ResponseKategori> response) {
                if (response.isSuccessful()) {
                    kategoriList = response.body().data;
                    kategoriAdapter = new KategoriAdapter(getActivity(), kategoriList);
                    rvKategori.setAdapter(kategoriAdapter);

                    helper.AnimationLeft(rvKategori);

                    if (kategoriList.size() == 0) {
                        tvNoData.setVisibility(View.VISIBLE);
                        rvKategori.setVisibility(View.GONE);
                    } else {
                        tvNoData.setVisibility(View.GONE);
                        rvKategori.setVisibility(View.VISIBLE);
                    }
                    progressbar.setVisibility(View.GONE);

                    kategoriAdapter.setOnDetailClick(new KategoriAdapter.OnDetailsClick() {
                        @Override
                        public void onDetailClick(int position) {
                            Intent intent = new Intent(getActivity(), MenuActivity.class);
                            intent.putExtra("kid", kategoriList.get(position).id);
                            intent.putExtra("nama", kategoriList.get(position).nama);
                            intent.putExtra("foto", kategoriList.get(position).foto);
                            ActivityOptions options =
                                    null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                                options = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.slide_in, R.anim.slide_out);
                                startActivity(intent, options.toBundle());
                            }
                        }
                    });

                } else {
                    helper.showToast(getString(R.string.msgWrong));
                }
            }

            @Override
            public void onFailure(Call<ResponseKategori> call, Throwable t) {
                helper.showToast(getString(R.string.msgWrong));
                t.printStackTrace();
            }
        });

    }
}
