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

import com.android.rolap.Activity.LoginActivity;
import com.android.rolap.Activity.SpaActivity;
import com.android.rolap.Activity.TutorActivity;
import com.android.rolap.Activity.UbahProfilActivity;
import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.R;

public class ProfilFragment extends Fragment implements View.OnClickListener{

    private View view;
    private PrefManager prefmanager;
    private Helper helper;
    private RelativeLayout progressbar;
    private TextView tvNamaUser;
    private RelativeLayout rlUbahProfil,rlSpa,rlTutor,rlJadwal,rlLogout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profil, container, false);

        prefmanager = new PrefManager(getActivity());
        helper =  new Helper(getActivity());
        progressbar = view.findViewById(R.id.progressbar);
        tvNamaUser = view.findViewById(R.id.tvNamaUser);
        rlUbahProfil = view.findViewById(R.id.rlUbahProfil);
        rlSpa = view.findViewById(R.id.rlSpa);
        rlTutor = view.findViewById(R.id.rlTutor);
        rlJadwal = view.findViewById(R.id.rlJadwal);
        rlLogout = view.findViewById(R.id.rlLogout);

        tvNamaUser.setText(prefmanager.getNama());

        rlUbahProfil.setOnClickListener(this);
        rlSpa.setOnClickListener(this);
        rlTutor.setOnClickListener(this);
        rlJadwal.setOnClickListener(this);
        rlLogout.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.rlUbahProfil:
                Intent intent = new Intent(getActivity(), UbahProfilActivity.class);
                ActivityOptions options =
                        null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    options = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.slide_in, R.anim.slide_out);
                    startActivity(intent, options.toBundle());
                }
                break;
            case R.id.rlSpa:
                Intent intent2 = new Intent(getActivity(), SpaActivity.class);
                ActivityOptions options2 =
                        null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    options2 = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.slide_in, R.anim.slide_out);
                    startActivity(intent2, options2.toBundle());
                }
                break;
            case R.id.rlTutor:
                Intent intent3 = new Intent(getActivity(), TutorActivity.class);
                ActivityOptions options3 =
                        null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    options3 = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.slide_in, R.anim.slide_out);
                    startActivity(intent3, options3.toBundle());
                }
                break;
            case R.id.rlLogout:
                prefmanager.setToken("");
                Intent intent4 = new Intent(getActivity(), LoginActivity.class);
                ActivityOptions options4 =
                        null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    options4 = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.slide_in, R.anim.slide_out);
                    startActivity(intent4, options4.toBundle());
                }
                helper.showToast("Anda berhasil logout");
                getActivity().finish();
                break;

            case R.id.rlJadwal:
                Intent intent5 = new Intent(getActivity(), SpaActivity.class);
                ActivityOptions options5 =
                        null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    options5 = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.slide_in, R.anim.slide_out);
                    startActivity(intent5, options5.toBundle());
                }
                break;
        }
    }
}
