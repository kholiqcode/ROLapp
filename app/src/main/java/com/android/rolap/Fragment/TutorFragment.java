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

import com.android.rolap.Activity.DetailTutorActivity;
import com.android.rolap.Activity.MenuActivity;
import com.android.rolap.Adapter.KategoriAdapter;
import com.android.rolap.Adapter.TutorAdapter;
import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.R;
import com.android.rolap.Rest.RequestAPI;
import com.android.rolap.Rest.Response.ResponseKategori;
import com.android.rolap.Rest.Response.ResponseTutor;
import com.android.rolap.Rest.RestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.android.rolap.Fragment.KategoriFragment.KID;

public class TutorFragment extends Fragment {
    private View view;
    private Helper helper;
    private PrefManager prefmanager;
    private RelativeLayout progressbar;
    private String kid;
    private RecyclerView rvTutor;
    private List<ResponseTutor.Data> tutorList;
    private TextView tvNoData;
    private TutorAdapter tutorAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tutor, container, false);

        helper = new Helper(getActivity());
        prefmanager = new PrefManager(getActivity());

        tvNoData = view.findViewById(R.id.tvNoData);
        progressbar = view.findViewById(R.id.progressbar);
        rvTutor = view.findViewById(R.id.rvTutor);
        rvTutor.setNestedScrollingEnabled(false);
        rvTutor.setHasFixedSize(false);

        kid = getActivity().getIntent().getStringExtra("kid");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvTutor.setLayoutManager(layoutManager);
        if (helper.isOnline()) {
            getTutorList();
        } else {
            helper.showToast(getString(R.string.msgNoCennection));
        }
        return view;
    }

    public void getTutorList() {
        progressbar.setVisibility(View.VISIBLE);
        RequestAPI rolapAPI = RestApi.createAPI();
        String kid = getArguments().getString(KID);
        Call<ResponseTutor> call = rolapAPI.getTutor(prefmanager.getToken(),kid);
        call.enqueue(new Callback<ResponseTutor>() {
            @Override
            public void onResponse(Call<ResponseTutor> call, Response<ResponseTutor> response) {
                if (response.isSuccessful()) {
                    tutorList = response.body().data;
                    tutorAdapter = new TutorAdapter(getActivity(),tutorList);
                    rvTutor.setAdapter(tutorAdapter);

                    helper.AnimationLeft(rvTutor);

                    if (tutorList.size() == 0) {
                        tvNoData.setVisibility(View.VISIBLE);
                        rvTutor.setVisibility(View.GONE);
                    } else {
                        tvNoData.setVisibility(View.GONE);
                        rvTutor.setVisibility(View.VISIBLE);
                    }
                    progressbar.setVisibility(View.GONE);

                    tutorAdapter.setOnDetailClick(new TutorAdapter.OnDetailsClick() {
                        @Override
                        public void onDetailClick(int position) {
                            Intent intent = new Intent(getActivity(), DetailTutorActivity.class);
                            intent.putExtra("tid", tutorList.get(position).id);
                            intent.putExtra("nama", tutorList.get(position).nama);
                            intent.putExtra("jenis_kelamin", tutorList.get(position).jenis_kelamin);
                            intent.putExtra("telepon", tutorList.get(position).telepon);
                            intent.putExtra("total_rate", tutorList.get(position).total_rate);
                            intent.putExtra("rate_avg", tutorList.get(position).rate_avg);
                            intent.putExtra("alamat", tutorList.get(position).alamat);
                            intent.putExtra("harga", tutorList.get(position).harga);
                            intent.putExtra("foto", tutorList.get(position).foto);
                            ActivityOptions options =
                                    null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                                options = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.slide_in, R.anim.slide_out);
                                startActivity(intent, options.toBundle());
                            }
                        }
                    });

                } else {
//                    helper.showToast(getString(R.string.msgWrong));
                    progressbar.setVisibility(View.GONE);
                    tvNoData.setVisibility(View.VISIBLE);
                    rvTutor.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseTutor> call, Throwable t) {
                helper.showToast(getString(R.string.msgWrong));
                t.printStackTrace();
            }
        });

    }
}
