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
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
import com.android.rolap.Rest.Response.ResponseDaftar;
import com.android.rolap.Rest.Response.ResponseProfil;
import com.android.rolap.Rest.RestApi;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahProfilActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout progressbar;
    private ImageView ivKembali;
    private RequestBody requestBody;
    private Helper helper;
    private EditText etNama,etEmail,etTelepon,etAlamat;
    private CheckBox cbMan,cbWoman;
    private PrefManager prefmanager;
    private Button btnSimpan,btnUbahPassword;
    private CircleImageView civUsers;
    private String strNama,strEmail,strJenisKelamin,strTelepon,strAlamat,strFileName,strImage;
    private Uri mImageUri;
    private Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_profil);

        helper = new Helper(this);
        prefmanager = new PrefManager(this);

        progressbar = findViewById(R.id.progressbar);
        etNama =  findViewById(R.id.etNama);
        etEmail =  findViewById(R.id.etEmail);
        cbMan =  findViewById(R.id.CBman);
        cbWoman =  findViewById(R.id.CBwoman);
        etTelepon =  findViewById(R.id.etTelepon);
        etAlamat =  findViewById(R.id.etAlamat);
        btnSimpan = findViewById(R.id.btnSimpan);
        civUsers = findViewById(R.id.civUsers);
        ivKembali = findViewById(R.id.ivBack);
        btnUbahPassword = findViewById(R.id.btnUbahPassword);

        etNama.setText(String.valueOf(prefmanager.getNama()));
        etEmail.setText(String.valueOf(prefmanager.getEmail()));
        etTelepon.setText(String.valueOf(prefmanager.getTelepon()));
        etAlamat.setText(String.valueOf(prefmanager.getAlamat()));
        if(String.valueOf(prefmanager.getGender()).equalsIgnoreCase("L")){
            cbMan.setChecked(true);
        }else if(String.valueOf(prefmanager.getGender()).equalsIgnoreCase("P")){
            cbWoman.setChecked(true);
        }
        if(prefmanager.getFoto() == null){
            Glide.with(this).load(R.drawable.image_profil).into(civUsers);
        }else{
            Glide.with(this).load(Constant.WEBSERVICE_IMAGE+"users/"+prefmanager.getFoto()).into(civUsers);
        }

        btnSimpan.setOnClickListener(this);
        btnUbahPassword.setOnClickListener(this);
        ivKembali.setOnClickListener(this);
        cbWoman.setOnClickListener(this);
        cbMan.setOnClickListener(this);
        civUsers.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.CBman:
                if(cbMan.isChecked()){
                    cbWoman.setChecked(false);
                }
                break;
            case R.id.CBwoman:
                if(cbWoman.isChecked()){
                    cbMan.setChecked(false);
                }
                break;
            case R.id.btnSimpan:
                if (isValidate()) {
                    if (helper.isOnline()) {
                        postUbahProfil();
                    } else {
                        helper.showToast(getString(R.string.msgNoCennection));
                    }
                }
                break;
            case R.id.btnUbahPassword:
                Intent intLogin = new Intent(this, UbahPasswordActivity.class);
                ActivityOptions options =
                        null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_in, R.anim.slide_out);
                    startActivity(intLogin, options.toBundle());
                }
                break;
            case R.id.civUsers:
                chooseFile();
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
                                if (ContextCompat.checkSelfPermission(UbahProfilActivity.this,
                                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                    requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                                            Constant.RequestPermissions.READ_EXTERNAL_STORAGE);
                                } else {
                                    openGallery();
                                }
                                break;

                            case 1:
                                // GET IMAGE FROM CAMERA
                                if (ContextCompat.checkSelfPermission(UbahProfilActivity.this,
                                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                                        ContextCompat.checkSelfPermission(UbahProfilActivity.this,
                                                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                                        ContextCompat.checkSelfPermission(UbahProfilActivity.this,
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
                                .into(civUsers);
                        File file = new File(getRealPathFromURI(mImageUri));
                        try {
                            this.bitmap =  MediaStore.Images.Media.getBitmap(getContentResolver(),mImageUri);
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                            byte[] imageBytes = baos.toByteArray();
                            this.strImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        strFileName = file.getName();
                        this.requestBody = RequestBody.create(MediaType.parse("multipart/form-file"), file);
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
                                    .into(civUsers);
                            File file = new File(getRealPathFromURI(mImageUri));
                            try {
                                this.bitmap =  MediaStore.Images.Media.getBitmap(getContentResolver(),mImageUri);
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                                byte[] imageBytes = baos.toByteArray();
                                this.strImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            strFileName = file.getName();
                            requestBody = RequestBody.create(MediaType.parse(getContentResolver().getType(mImageUri)), file);
                        }
                    } else {
                        helper.showToast(R.string.msgNoCennection);
                    }
                    break;
            }
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

    public void postUbahProfil() {
        progressbar.setVisibility(View.VISIBLE);


        RequestBody requestApikey = RequestBody.create(MediaType.parse("text/plain"), prefmanager.getToken());
        RequestBody requestNama = RequestBody.create(MediaType.parse("text/plain"), etNama.getText().toString().trim());
        RequestBody requestAlamat = RequestBody.create(MediaType.parse("text/plain"), etAlamat.getText().toString().trim());
        RequestBody requestTelepon = RequestBody.create(MediaType.parse("text/plain"), etTelepon.getText().toString().trim());
        RequestBody requestJenisKelamin = RequestBody.create(MediaType.parse("text/plain"), "L");
        if (cbMan.isChecked()) {
            requestJenisKelamin = RequestBody.create(MediaType.parse("text/plain"), "L");
        }else if(cbWoman.isChecked()){
            requestJenisKelamin = RequestBody.create(MediaType.parse("text/plain"), "P");
        }
        RequestBody requestFoto = RequestBody.create(MediaType.parse("text/plain"), strImage);

        MultipartBody.Part part = null;
        if (this.requestBody != null) {
            part = MultipartBody.Part.createFormData("foto", strFileName, this.requestBody);
        }

        RequestAPI rolapAPI = RestApi.createAPI();
        Call<ResponseProfil> call = rolapAPI.putUsers(requestApikey,requestNama,requestAlamat,requestJenisKelamin,requestTelepon,requestFoto);
        call.enqueue(new Callback<ResponseProfil>() {
            @Override
            public void onResponse(Call<ResponseProfil> call, Response<ResponseProfil> response) {
                if (response.isSuccessful()) {
                    progressbar.setVisibility(View.GONE);
                    if (response.body().status == true) {
                        helper.showToast(response.body().message);
                        Intent intLogin = new Intent(UbahProfilActivity.this, MenuActivity.class);
                        ActivityOptions options = ActivityOptions.makeCustomAnimation(UbahProfilActivity.this, R.anim.slide_in, R.anim.slide_out);
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
            public void onFailure(Call<ResponseProfil> call, Throwable t) {
                progressbar.setVisibility(View.GONE);
                helper.showToast(getString(R.string.msgWrong));
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    public boolean isValidate() {
        strNama = etNama.getText().toString().trim();
        strEmail = etEmail.getText().toString().trim();
        strAlamat = etAlamat.getText().toString().trim();
        strTelepon = etTelepon.getText().toString().trim();
        if (cbMan.isChecked()) {
            strJenisKelamin = "L";
        }else if(cbWoman.isChecked()){
            strJenisKelamin = "P";
        }

        if (Validate.isNull(strNama)) {
            etNama.setError("Nama tidak boleh kosong");
            etNama.requestFocus();
            return false;
        } else if (Validate.isNull(strEmail)) {
            etEmail.setError("Email tidak boleh kosong");
            etEmail.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {
            etEmail.setError("Masukkan email yang valid");
            etEmail.requestFocus();
            return false;
        }else if (Validate.isNull(strJenisKelamin)) {
            cbMan.setError("Jenis Kelamin tidak boleh kosong");
            cbMan.requestFocus();
            return false;
        }  else if (Validate.isNull(strTelepon)) {
            etTelepon.setError("Telepon tidak boleh kosong");
            etTelepon.requestFocus();
            return false;
        } else {
            return true;
        }
    }

}
