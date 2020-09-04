package com.android.rolap.Activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

import com.android.rolap.Helper.Constant;
import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.R;
import com.bumptech.glide.Glide;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailTutorActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etTanggal,etJam;
    private TextView tvNamaTutor,tvHargaTutor,tvAlamatTutor,tvJenisKelamin,tvTelepon;
    private String strTanggal,strJam,strTid,strNama,strHarga,strAlamat,strJenisKelamin,strTotalRate,strRateAvg,strFoto;
    private RatingBar rbTutor;
    private Button btnPesan;
    private CircleImageView civTutor;
    private RelativeLayout progressbar;
    private ImageView ivKembali;
    private Helper helper;
    private PrefManager prefmanager;
    private int year,month,day;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail_tutor);

        helper = new Helper(this);
        prefmanager = new PrefManager(this);

        progressbar = findViewById(R.id.progressbar);
        tvNamaTutor = findViewById(R.id.tvNamaTutor);
        tvAlamatTutor = findViewById(R.id.tvAlamatTutor);
        tvJenisKelamin = findViewById(R.id.tvJenisKelamin);
        tvHargaTutor = findViewById(R.id.tvHargaTutor);
        etTanggal =  findViewById(R.id.etTanggal);
        rbTutor = findViewById(R.id.rbTutor);
        civTutor = findViewById(R.id.civTutor);
        etJam = findViewById(R.id.etJam);
        btnPesan = findViewById(R.id.btnPesan);
        ivKembali = findViewById(R.id.ivBack);

        strTid = getIntent().getStringExtra("kid");
        strNama = getIntent().getStringExtra("nama");
        strJenisKelamin = getIntent().getStringExtra("jenis_kelamin");
        strAlamat = getIntent().getStringExtra("alamat");
        strHarga = getIntent().getStringExtra("harga");
        strAlamat = getIntent().getStringExtra("alamat");
        strRateAvg = getIntent().getStringExtra("rate_avg");
        strTotalRate = getIntent().getStringExtra("total_rate");
        strFoto = getIntent().getStringExtra("foto");

        tvNamaTutor.setText(strNama);

        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(tvNamaTutor, 18, 25, 1,
                TypedValue.COMPLEX_UNIT_DIP);
        if(tvNamaTutor.getLineCount() == 2){
            tvNamaTutor.setTextSize(18);
        }
        tvAlamatTutor.setText(strAlamat);
        tvJenisKelamin.setText(strJenisKelamin);
        rbTutor.setRating(Float.parseFloat(strRateAvg));
        tvHargaTutor.setText("Rp "+strHarga);
        if(strFoto == ""){
            Glide.with(getApplicationContext()).load(R.drawable.image_profil).into(civTutor);
        }else{
            Glide.with(getApplicationContext()).load(Constant.WEBSERVICE_IMAGE+strFoto).into(civTutor);
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
//                paymentMode();
                break;
            case R.id.etTanggal:
                DatePickerDialog();
                break;
        }
    }

    public void DatePickerDialog(){
        final Calendar calender = Calendar.getInstance();
        year = calender.get(Calendar.YEAR);
        month = calender.get(Calendar.MONTH);
        day = calender.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(DetailTutorActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                strTanggal = year+"-"+month+"-"+dayOfMonth;
                etTanggal.setText(strTanggal);
            }
        },year,month,day);
        datePickerDialog.show();
    }
}
