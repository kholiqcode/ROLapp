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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
    public static String KID = "KID";

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
//                            Intent intent = new Intent(getActivity(), MenuActivity.class);
//                            intent.putExtra("kid", kategoriList.get(position).id);
                            replaceFragment(new TutorFragment(),kategoriList.get(position).id);
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

    public void replaceFragment(@NonNull Fragment fragment,@Nullable String kid) {
        Bundle mBundle = new Bundle();
        mBundle.putString(KID, kid);
        fragment.setArguments(mBundle);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, fragment, fragment.getClass().getSimpleName());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}
