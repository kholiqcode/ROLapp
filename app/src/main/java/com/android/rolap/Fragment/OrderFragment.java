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

import com.android.rolap.Activity.DetailPemesananActivity;
import com.android.rolap.Activity.DetailTutorActivity;
import com.android.rolap.Activity.MenuActivity;
import com.android.rolap.Activity.PembayaranActivity;
import com.android.rolap.Adapter.KategoriAdapter;
import com.android.rolap.Adapter.OrderAdapter;
import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.R;
import com.android.rolap.Rest.RequestAPI;
import com.android.rolap.Rest.Response.ResponseKategori;
import com.android.rolap.Rest.Response.ResponseOrder;
import com.android.rolap.Rest.RestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderFragment extends Fragment {

    private View view;
    private Helper helper;
    private PrefManager prefmanager;
    private RelativeLayout progressbar;
    private RecyclerView rvOrder;
    private TextView tvNoData;
    private List<ResponseOrder.Data> orderList;
    private OrderAdapter orderAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order, container, false);

        helper = new Helper(getActivity());
        prefmanager = new PrefManager(getActivity());

        tvNoData = view.findViewById(R.id.tvNoData);
        progressbar = view.findViewById(R.id.progressbar);
        rvOrder = view.findViewById(R.id.rvOrder);
        rvOrder.setNestedScrollingEnabled(false);
        rvOrder.setHasFixedSize(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvOrder.setLayoutManager(layoutManager);
        if (helper.isOnline()) {
            getOrderList();
        } else {
            helper.showToast(getString(R.string.msgNoCennection));
        }

        return view;
    }

    public void getOrderList() {
        progressbar.setVisibility(View.VISIBLE);
        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponseOrder> call = rolapAPI.getOrder(prefmanager.getToken());
        call.enqueue(new Callback<ResponseOrder>() {
            @Override
            public void onResponse(Call<ResponseOrder> call, Response<ResponseOrder> response) {
                if (response.isSuccessful()) {
                    orderList = response.body().data;
                    orderAdapter = new OrderAdapter(getActivity(), orderList);
                    rvOrder.setAdapter(orderAdapter);

                    helper.AnimationLeft(rvOrder);

                    if (orderList.size() == 0) {
                        tvNoData.setVisibility(View.VISIBLE);
                        rvOrder.setVisibility(View.GONE);
                    } else {
                        tvNoData.setVisibility(View.GONE);
                        rvOrder.setVisibility(View.VISIBLE);
                    }
                    progressbar.setVisibility(View.GONE);

                    orderAdapter.setOnDetailClick(new OrderAdapter.OnDetailsClick() {
                        @Override
                        public void onDetailClick(int position) {
                            Intent intent = new Intent(getActivity(), DetailPemesananActivity.class);
                            intent.putExtra("pid", orderList.get(position).id);
                            intent.putExtra("nama", orderList.get(position).nama);
                            intent.putExtra("alamat", orderList.get(position).alamat);
                            intent.putExtra("jenis_kelamin", orderList.get(position).jenis_kelamin);
                            intent.putExtra("telepon", orderList.get(position).telepon);
                            intent.putExtra("tanggal", orderList.get(position).tanggal);
                            intent.putExtra("metode_pembayaran", orderList.get(position).metode_pembayaran);
                            intent.putExtra("nomor_rekening", orderList.get(position).nomor_rekening);
                            intent.putExtra("jam", orderList.get(position).waktu);
                            intent.putExtra("total", orderList.get(position).total);
                            intent.putExtra("status", orderList.get(position).status);
                            intent.putExtra("foto", orderList.get(position).foto);
                            ActivityOptions options = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.anim_left, R.anim.anim_right);
                            startActivity(intent, options.toBundle());
                        }
                    });

                } else {
//                    helper.showToast(getString(R.string.msgWrong));
                    progressbar.setVisibility(View.GONE);
                    tvNoData.setVisibility(View.VISIBLE);
                    rvOrder.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseOrder> call, Throwable t) {
                helper.showToast(getString(R.string.msgWrong));
                t.printStackTrace();
            }
        });
    }

}
