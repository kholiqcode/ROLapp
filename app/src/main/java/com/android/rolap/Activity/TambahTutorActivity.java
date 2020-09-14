package com.android.rolap.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.rolap.Helper.Constant;
import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.Helper.Validate;
import com.android.rolap.R;
import com.android.rolap.Rest.RequestAPI;
import com.android.rolap.Rest.Response.Building;
import com.android.rolap.Rest.Response.ResponseKategori;
import com.android.rolap.Rest.Response.ResponsePembayaran;
import com.android.rolap.Rest.Response.ResponseProfil;
import com.android.rolap.Rest.Response.ResponseTambahTutor;
import com.android.rolap.Rest.RestApi;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahTutorActivity extends AppCompatActivity implements View.OnClickListener {

    private Helper helper;
    private PrefManager prefmanager;
    private ImageView ivKembali;
    private EditText etNama,etAlamat,etHarga;
    private Spinner spinKategori;
    private CircleImageView civTutor;
    private String strNama,strAlamat,strFoto,strHarga,strKid;
    private RelativeLayout progressbar;
    private List<ResponseKategori.Data> kategoriList;
    private ArrayList<Building> buildingList;
    private Bitmap bitmap;
    private Uri mImageUri;
    private Button btnTambah;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tutor);

        helper = new Helper(this);
        prefmanager = new PrefManager(this);

        ivKembali = findViewById(R.id.ivBack);
        progressbar = findViewById(R.id.progressbar);
        etNama = findViewById(R.id.etNama);
        etAlamat = findViewById(R.id.etAlamat);
        etHarga = findViewById(R.id.etHarga);
        civTutor = findViewById(R.id.civTutor);
        spinKategori = findViewById(R.id.SpinKategori);
        btnTambah =  findViewById(R.id.btnTambah);

        if (helper.isOnline()) {
            getKategori();
        } else {
            helper.showToast(getString(R.string.msgNoCennection));
        }

        ivKembali.setOnClickListener(this);
        civTutor.setOnClickListener(this);
        btnTambah.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.civTutor:
                chooseFile();
                break;
            case R.id.btnTambah:
                if (isValidate()) {
                    if (helper.isOnline()) {
                        postTambahTutor();
                    } else {
                        helper.showToast(getString(R.string.msgNoCennection));
                    }
                }
                break;
        }
    }

    private void chooseFile() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.pilih_gambar);
        builder.setItems(R.array.profile_pic_upload_option,
                new DialogInterface.OnClickListener() {

                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                // GET IMAGE FROM GALLERY
                                if (ContextCompat.checkSelfPermission(TambahTutorActivity.this,
                                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                    requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                                            Constant.RequestPermissions.READ_EXTERNAL_STORAGE);
                                } else {
                                    openGallery();
                                }
                                break;

                            case 1:
                                // GET IMAGE FROM CAMERA
                                if (ContextCompat.checkSelfPermission(TambahTutorActivity.this,
                                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                                        ContextCompat.checkSelfPermission(TambahTutorActivity.this,
                                                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                                        ContextCompat.checkSelfPermission(TambahTutorActivity.this,
                                                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                    requestPermissions(new String[]{
                                                    Manifest.permission.CAMERA,
                                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                                    Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                            Constant.RequestPermissions.CAMERA);
                                } else {
                                    openCamera();
                                }
                                break;

                            default:
                                break;
                        }
                    }
                });

        builder.show();

    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, this.getString(R.string.profile_pic_open_gallary_title)), Constant.ActivityForResult.GALLERY);
    }

    private void openCamera() {
        String fileName = getString(R.string.app_name) + helper.getCurrentDateTimeMix() + ".jpg";
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, fileName);
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image captured by Camera on" + getString(R.string.app_name));
        mImageUri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
        startActivityForResult(intent, Constant.ActivityForResult.CAMERA);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Constant.RequestPermissions.READ_EXTERNAL_STORAGE
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else if (requestCode == Constant.RequestPermissions.CAMERA
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case Constant.ActivityForResult.CAMERA:
                    if (helper.isOnline()) {
                        Glide.with(this)
                                .load(mImageUri)
                                .into(civTutor);
                        try {
                            this.bitmap =  MediaStore.Images.Media.getBitmap(getContentResolver(),mImageUri);
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte[] imageBytes = baos.toByteArray();
                            strFoto = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        helper.showToast(R.string.msgNoCennection);
                    }
                    break;
                case Constant.ActivityForResult.GALLERY:
                    if (helper.isOnline()) {
                        if (data != null) {
                            mImageUri = data.getData();
                            assert mImageUri != null;
                            Glide.with(this)
                                    .load(mImageUri)
                                    .into(civTutor);
                            try {
                                this.bitmap =  MediaStore.Images.Media.getBitmap(getContentResolver(),mImageUri);
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                                byte[] imageBytes = baos.toByteArray();
                                strFoto = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        helper.showToast(R.string.msgNoCennection);
                    }
                    break;
            }
        }

    }

    public void postTambahTutor() {
        progressbar.setVisibility(View.VISIBLE);

        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponseTambahTutor> call = rolapAPI.postTambahTutor(prefmanager.getToken(),strKid,strNama,strAlamat,strHarga,strFoto);
        call.enqueue(new Callback<ResponseTambahTutor>() {
            @Override
            public void onResponse(Call<ResponseTambahTutor> call, Response<ResponseTambahTutor> response) {
                if (response.isSuccessful()) {
                    progressbar.setVisibility(View.GONE);
                    if (response.body().status == true) {
                        helper.showToast(response.body().message);
                        Intent intLogin = new Intent(TambahTutorActivity.this, MenuActivity.class);
                        ActivityOptions options = ActivityOptions.makeCustomAnimation(TambahTutorActivity.this, R.anim.slide_in, R.anim.slide_out);
                        startActivity(intLogin, options.toBundle());

                    } else {
                        helper.showToast(response.body().message);
                    }
                } else {
                    progressbar.setVisibility(View.GONE);
                    helper.showToast(getString(R.string.msgWrong));
                }
            }

            @Override
            public void onFailure(Call<ResponseTambahTutor> call, Throwable t) {
                progressbar.setVisibility(View.GONE);
                helper.showToast(getString(R.string.msgWrong));
                t.printStackTrace();
            }
        });
    }

    public void getKategori() {
        progressbar.setVisibility(View.VISIBLE);
        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponseKategori> call = rolapAPI.getKategori(prefmanager.getToken());
        call.enqueue(new Callback<ResponseKategori>() {
            @Override
            public void onResponse(Call<ResponseKategori> call, Response<ResponseKategori> response) {
                if (response.isSuccessful()) {
                    if(response.body().status == true){
                        kategoriList = response.body().data;
                        addSpinner();

                        spinKategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long ids) {
                                Building building = buildingList.get(position);

                                String id = building.getId();
                                String name = building.getName();

                                strKid = building.getId();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                } else {
                    helper.showToast(R.string.msgWrong);
                }
                progressbar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseKategori> call, Throwable t) {
                helper.showToast(getString(R.string.msgWrong));
                t.printStackTrace();
            }
        });
    }

    public void addSpinner() {
        //
        if (kategoriList.size() != 0) {
            List<String> listSpinner = new ArrayList<String>();
            buildingList = new ArrayList <> ();
            for (int i = 0; i < kategoriList.size(); i++) {
                listSpinner.add(kategoriList.get(i).nama);
                Building building = new Building(kategoriList.get(i).id, kategoriList.get(i).nama);
                buildingList.add(building);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item,listSpinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinKategori.setAdapter(adapter);

        } else {
            helper.showToast(R.string.msgWrong);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    public boolean isValidate() {
        strNama = etNama.getText().toString().trim();
        strAlamat = etAlamat.getText().toString().trim();
        strHarga = etHarga.getText().toString().trim();

        if (Validate.isNull(strNama)) {
            etNama.setError("Nama tidak boleh kosong");
            etNama.requestFocus();
            return false;
        } else if (Validate.isNull(strAlamat)) {
            etAlamat.setError("Alamat tidak boleh kosong");
            etAlamat.requestFocus();
            return false;
        } else if (Validate.isNull(strHarga)) {
            etHarga.setError("Harga tidak boleh kosong");
            etHarga.requestFocus();
            return false;
        }  else if (Validate.isNull(strKid)) {
            spinKategori.setPrompt("Kategori tidak boleh kosong");
            spinKategori.requestFocus();
            return false;
        }   else if (Validate.isNull(strFoto)) {
            helper.showAlert("Harap masukkan foto");
            civTutor.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}
