package com.android.rolap.Fragment;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.android.rolap.Activity.JadwalSayaActivity;
import com.android.rolap.Activity.LoginActivity;
import com.android.rolap.Activity.SpaActivity;
import com.android.rolap.Activity.TutorActivity;
import com.android.rolap.Activity.UbahProfilActivity;
import com.android.rolap.Helper.Constant;
import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.R;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilFragment extends Fragment implements View.OnClickListener{

    private View view;
    private PrefManager prefmanager;
    private Helper helper;
    private RelativeLayout progressbar;
    private TextView tvNamaUser;
    private RelativeLayout rlUbahProfil,rlSpa,rlTutor,rlJadwal,rlLogout;
    private CircleImageView civUsers;

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
        civUsers = view.findViewById(R.id.civProfil);

        tvNamaUser.setText(prefmanager.getNama());
        if(prefmanager.getFoto() == ""){
            Glide.with(this).load(R.drawable.image_profil).into(civUsers);
        }else{
            Glide.with(this).load(Constant.IMAGE_USERS+prefmanager.getFoto()).into(civUsers);
        }

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
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Apakah anda yakin ingin logout?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                prefmanager.setToken("");
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                ActivityOptions options =
                                        ActivityOptions.makeCustomAnimation(getActivity(), R.anim.slide_in, R.anim.slide_out);
                                startActivity(intent, options.toBundle());
                                helper.showToast("Anda berhasil logout");
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                break;

            case R.id.rlJadwal:
                Intent intent5 = new Intent(getActivity(), JadwalSayaActivity.class);
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
