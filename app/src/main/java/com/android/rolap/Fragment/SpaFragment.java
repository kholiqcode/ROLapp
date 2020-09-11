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

import com.android.rolap.Activity.DetailSpaActivity;
import com.android.rolap.Activity.DetailTutorActivity;
import com.android.rolap.Adapter.SpaAdapter;
import com.android.rolap.Adapter.TutorAdapter;
import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.R;
import com.android.rolap.Rest.RequestAPI;
import com.android.rolap.Rest.Response.ResponseSpa;
import com.android.rolap.Rest.Response.ResponseTutor;
import com.android.rolap.Rest.RestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.rolap.Fragment.KategoriFragment.KID;

public class SpaFragment extends Fragment {

    private View view;
    private Helper helper;
    private PrefManager prefmanager;
    private RelativeLayout progressbar;
    private String kid;
    private RecyclerView rvSpa;
    private List<ResponseSpa.Data> spaList;
    private TextView tvNoData;
    private SpaAdapter spaAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_spa, container, false);

        helper = new Helper(getActivity());
        prefmanager = new PrefManager(getActivity());

        tvNoData = view.findViewById(R.id.tvNoData);
        progressbar = view.findViewById(R.id.progressbar);
        rvSpa = view.findViewById(R.id.rvSpa);
        rvSpa.setNestedScrollingEnabled(false);
        rvSpa.setHasFixedSize(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvSpa.setLayoutManager(layoutManager);
        if (helper.isOnline()) {
            getSpaList();
        } else {
            helper.showToast(getString(R.string.msgNoCennection));
        }

        return view;
    }

    public void getSpaList() {
        progressbar.setVisibility(View.VISIBLE);
        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponseSpa> call = rolapAPI.getSpa(prefmanager.getToken());
        call.enqueue(new Callback<ResponseSpa>() {
            @Override
            public void onResponse(Call<ResponseSpa> call, Response<ResponseSpa> response) {
                if (response.isSuccessful()) {
                    spaList = response.body().data;
                    spaAdapter = new SpaAdapter(getActivity(),spaList);
                    rvSpa.setAdapter(spaAdapter);

                    helper.AnimationLeft(rvSpa);

                    if (spaList.size() == 0) {
                        tvNoData.setVisibility(View.VISIBLE);
                        rvSpa.setVisibility(View.GONE);
                    } else {
                        tvNoData.setVisibility(View.GONE);
                        rvSpa.setVisibility(View.VISIBLE);
                    }
                    progressbar.setVisibility(View.GONE);

                    spaAdapter.setOnDetailClick(new SpaAdapter.OnDetailsClick() {
                        @Override
                        public void onDetailClick(int position) {
                            Intent intent = new Intent(getActivity(), DetailSpaActivity.class);
                            intent.putExtra("sid", spaList.get(position).id);
                            intent.putExtra("nama", spaList.get(position).nama);
                            intent.putExtra("jenis_kelamin", spaList.get(position).jenis_kelamin);
                            intent.putExtra("telepon", spaList.get(position).telepon);
                            intent.putExtra("total_rate", spaList.get(position).total_rate);
                            intent.putExtra("rate_avg", spaList.get(position).rate_avg);
                            intent.putExtra("alamat", spaList.get(position).alamat);
                            intent.putExtra("harga", spaList.get(position).harga);
                            intent.putExtra("foto", spaList.get(position).foto);
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
            public void onFailure(Call<ResponseSpa> call, Throwable t) {
                helper.showToast(getString(R.string.msgWrong));
                t.printStackTrace();
            }
        });

    }
}
