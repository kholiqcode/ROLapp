package com.android.rolap.Activity;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import com.android.rolap.Helper.Constant;
import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.R;
import com.android.rolap.Rest.RequestAPI;
import com.android.rolap.Rest.Response.ResponsePembayaran;
import com.android.rolap.Rest.Response.ResponsePemesanan;
import com.android.rolap.Rest.RestApi;
import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSpaActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etTanggal,etJam;
    private TextView tvNamaSpa,tvHargaSpa,tvAlamatSpa,tvJenisKelamin,tvTelepon;
    private String strTanggal,strJam,strTid,strNama,strHarga,strAlamat,strJenisKelamin,strTotalRate,strRateAvg,strFoto,strMid,strPembayaran,strNorek;
    private RatingBar rbSpa;
    private Button btnPesan;
    private CircleImageView civSpa;
    private RelativeLayout progressbar;
    private RadioGroup rgPembayaran;
    private ImageView ivKembali;
    private Helper helper;
    private PrefManager prefmanager;
    private int year,month,day,hour,minute;
    private List<ResponsePembayaran.Data> pembayaranList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_spa);

        helper = new Helper(this);
        prefmanager = new PrefManager(this);

        progressbar = findViewById(R.id.progressbar);
        tvNamaSpa = findViewById(R.id.tvNamaSpa);
        tvAlamatSpa = findViewById(R.id.tvAlamatSpa);
        tvJenisKelamin = findViewById(R.id.tvJenisKelamin);
        tvHargaSpa = findViewById(R.id.tvHargaSpa);
        etTanggal =  findViewById(R.id.etTanggal);
        rbSpa = findViewById(R.id.rbSpa);
        civSpa = findViewById(R.id.civSpa);
        etJam = findViewById(R.id.etJam);
        btnPesan = findViewById(R.id.btnPesan);
        ivKembali = findViewById(R.id.ivBack);

        strTid = getIntent().getStringExtra("sid");
        strNama = getIntent().getStringExtra("nama");
        strJenisKelamin = getIntent().getStringExtra("jenis_kelamin");
        strAlamat = getIntent().getStringExtra("alamat");
        strHarga = getIntent().getStringExtra("harga");
        strAlamat = getIntent().getStringExtra("alamat");
        strRateAvg = getIntent().getStringExtra("rate_avg");
        strTotalRate = getIntent().getStringExtra("total_rate");
        strFoto = getIntent().getStringExtra("foto");

        tvNamaSpa.setText(strNama);

        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(tvNamaSpa, 18, 25, 1,
                TypedValue.COMPLEX_UNIT_DIP);
        tvAlamatSpa.setText(strAlamat);
        tvJenisKelamin.setText(strJenisKelamin);
        rbSpa.setRating(Float.parseFloat(strRateAvg));
        tvHargaSpa.setText("Rp "+strHarga);
        if(strFoto == ""){
            Glide.with(getApplicationContext()).load(R.drawable.image_profil).into(civSpa);
        }else{
            Glide.with(getApplicationContext()).load(Constant.WEBSERVICE_IMAGE+"spa/"+strFoto).into(civSpa);
        }

        btnPesan.setOnClickListener(this);
        ivKembali.setOnClickListener(this);
        etTanggal.setOnClickListener(this);
        etJam.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.btnPesan:
                paymentMode();
                break;
            case R.id.etTanggal:
                DatePickerDialog();
                break;
            case R.id.etJam:
                TimePickerDialog();
                break;
        }
    }

    public void DatePickerDialog(){
        final Calendar calender = Calendar.getInstance();
        year = calender.get(Calendar.YEAR);
        month = calender.get(Calendar.MONTH);
        day = calender.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(DetailSpaActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                strTanggal = year+"-"+month+"-"+dayOfMonth;
                etTanggal.setText(strTanggal);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    public void TimePickerDialog(){
        final Calendar calender = Calendar.getInstance();
        hour = calender.get(Calendar.HOUR_OF_DAY);
        minute = calender.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(DetailSpaActivity.this,android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                strJam = hourOfDay+":"+minute;
                etJam.setText(strJam);
            }
        },hour,minute,true);
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
    }

    public void paymentMode() {
        final Dialog dialog = new Dialog(this);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_pembayaran);
        rgPembayaran = dialog.findViewById(R.id.radiogroup);
        progressbar.setVisibility(View.VISIBLE);
        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponsePembayaran> call = rolapAPI.getPembayaran(prefmanager.getToken());
        call.enqueue(new Callback<ResponsePembayaran>() {
            @Override
            public void onResponse(Call<ResponsePembayaran> call, Response<ResponsePembayaran> response) {
                if (response.isSuccessful()) {
                    if(response.body().status == true){
                        pembayaranList = response.body().data;
                        addRadioButtons();

                        rgPembayaran.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                strMid = String.valueOf(checkedId);
                                Pesan();
                            }
                        });
                    }
                } else {
                    helper.showToast(R.string.msgWrong);
                }
                progressbar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponsePembayaran> call, Throwable t) {
                helper.showToast(getString(R.string.msgWrong));
                t.printStackTrace();
            }
        });

        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        dialog.show();
    }

    public void Pesan(){
        progressbar.setVisibility(View.VISIBLE);
        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponsePemesanan> call = rolapAPI.postPemesanan(prefmanager.getToken(),this.strTid ,this.strMid, this.strTanggal,this.strJam);
        call.enqueue(new Callback<ResponsePemesanan>() {
            @Override
            public void onResponse(Call<ResponsePemesanan> call, Response<ResponsePemesanan> response) {
                if (response.isSuccessful()) {
                    if(response.body().status == true){
                        helper.showToast(response.body().message);
                        for (int i = 0; i < pembayaranList.size(); i++) {
                            if(Integer.parseInt(pembayaranList.get(i).id) == Integer.parseInt(strMid)){
                                strPembayaran = pembayaranList.get(i).metode_pembayaran;
                                strNorek = pembayaranList.get(i).nomor_rekening;
                            }
                        }
                        Intent intent = new Intent(DetailSpaActivity.this, PembayaranActivity.class);
                        intent.putExtra("metode_pembayaran", strPembayaran);
                        intent.putExtra("nomor_rekening", strNorek);
                        intent.putExtra("total_pembayaran", strHarga);
                        ActivityOptions options = ActivityOptions.makeCustomAnimation(DetailSpaActivity.this, R.anim.anim_left, R.anim.anim_right);
                        startActivity(intent, options.toBundle());
                    }
                } else {
                    helper.showToast(response.body().message);
                }
                progressbar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponsePemesanan> call, Throwable t) {
                helper.showToast(getString(R.string.msgWrong));
                t.printStackTrace();
            }
        });
    }

    public void addRadioButtons() {
//        mRgAllButtons.setOrientation(LinearLayout.HORIZONTAL);
        //
        if (pembayaranList.size() != 0) {
            for (int i = 0; i < pembayaranList.size(); i++) {
                RadioButton rdbtn = new RadioButton(this);
                rdbtn.setId(Integer.parseInt(pembayaranList.get(i).id));
                rdbtn.setText(pembayaranList.get(i).metode_pembayaran);
                rdbtn.setButtonDrawable(R.drawable.selector_radio_button);
                rdbtn.setOnClickListener(this);
                rgPembayaran.addView(rdbtn);
            }
        }
    }
}
