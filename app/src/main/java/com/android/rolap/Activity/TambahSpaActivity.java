package com.android.rolap.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

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
import com.android.rolap.Rest.Response.ResponseTambahSpa;
import com.android.rolap.Rest.Response.ResponseTambahTutor;
import com.android.rolap.Rest.RestApi;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahSpaActivity extends AppCompatActivity implements View.OnClickListener {

    private Helper helper;
    private PrefManager prefmanager;
    private ImageView ivKembali;
    private EditText etNama,etAlamat,etHarga;
    private CircleImageView civSpa;
    private String strNama,strAlamat,strFoto,strHarga;
    private RelativeLayout progressbar;
    private Bitmap bitmap;
    private Uri mImageUri;
    private Button btnTambah;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_spa);

        helper = new Helper(this);
        prefmanager = new PrefManager(this);

        ivKembali = findViewById(R.id.ivBack);
        progressbar = findViewById(R.id.progressbar);
        etNama = findViewById(R.id.etNama);
        etAlamat = findViewById(R.id.etAlamat);
        etHarga = findViewById(R.id.etHarga);
        civSpa = findViewById(R.id.civSpa);
        btnTambah =  findViewById(R.id.btnTambah);

        ivKembali.setOnClickListener(this);
        civSpa.setOnClickListener(this);
        btnTambah.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.civSpa:
                chooseFile();
                break;
            case R.id.btnTambah:
                if (isValidate()) {
                    if (helper.isOnline()) {
                        postTambahSpa();
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
                                if (ContextCompat.checkSelfPermission(TambahSpaActivity.this,
                                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                            Constant.RequestPermissions.READ_EXTERNAL_STORAGE);
                                } else {
                                    openGallery();
                                }
                                break;

                            case 1:
                                // GET IMAGE FROM CAMERA
                                if (ContextCompat.checkSelfPermission(TambahSpaActivity.this,
                                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                                        ContextCompat.checkSelfPermission(TambahSpaActivity.this,
                                                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                                        ContextCompat.checkSelfPermission(TambahSpaActivity.this,
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
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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

    private String getRealPathFromURI(Uri contentURI) {
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
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
                                .into(civSpa);
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
                                    .into(civSpa);
                        }
                    } else {
                        helper.showToast(R.string.msgNoCennection);
                    }
                    break;
            }
            File file = new File(getRealPathFromURI(mImageUri));
            try {
                Bitmap compressedImageBitmap = new Compressor(this).compressToBitmap(file);
                strFoto = helper.bitmapToBase64(compressedImageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void postTambahSpa() {
        progressbar.setVisibility(View.VISIBLE);

        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponseTambahSpa> call = rolapAPI.postTambahSpa(prefmanager.getToken(),strNama,strAlamat,strHarga,strFoto);
        call.enqueue(new Callback<ResponseTambahSpa>() {
            @Override
            public void onResponse(Call<ResponseTambahSpa> call, Response<ResponseTambahSpa> response) {
                if (response.isSuccessful()) {
                    progressbar.setVisibility(View.GONE);
                    if (response.body().status == true) {
                        helper.showToast(response.body().message);
                        Intent intLogin = new Intent(TambahSpaActivity.this, MenuActivity.class);
                        ActivityOptions options = ActivityOptions.makeCustomAnimation(TambahSpaActivity.this, R.anim.slide_in, R.anim.slide_out);
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
            public void onFailure(Call<ResponseTambahSpa> call, Throwable t) {
                progressbar.setVisibility(View.GONE);
                helper.showToast(getString(R.string.msgWrong));
                t.printStackTrace();
            }
        });
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
        } else if (Validate.isNull(strFoto)) {
            helper.showAlert("Harap masukkan foto");
            civSpa.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}
