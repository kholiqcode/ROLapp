package com.android.rolap.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class PrefManager {

    private SharedPreferences pref;
    private Gson gson;
    private Type type;

    public PrefManager(Context context) {
        if (context == null) {
            context = getAppContext();
        }

        gson = new Gson();

        pref = PreferenceManager.getDefaultSharedPreferences(context);
        refreshToken();
        refreshNama();
        refreshEmail();
        refreshGender();
        refreshTelepon();
        refreshFoto();

    }

    public static Context getAppContext() {
        return App.mContext;
    }

    private final String TOKEN = "TOKEN";
    private final String NAMA_USER = "NAMA_USER";
    private final String EMAIL_USER = "EMAIL_USER";
    private final String GENDER_USER = "GENDER_USER";
    private final String TELEPON_USER = "TELEPON_USER";
    private final String ALAMAT_USER = "ALAMAT_USER";
    private final String FOTO_USER = "FOTO_USER";

    @Nullable
    private String token;
    @Nullable
    private String nama;
    @Nullable
    private String email;
    @Nullable
    private String gender;
    @Nullable
    private String telepon;
    @Nullable
    private String alamat;
    @Nullable
    private String foto;

    //GETTER & SETTER
    private void refreshToken() {
        token = pref.getString(TOKEN, "");
    }

    @Nullable
    public String getToken() {
        return token;
    }

    public void setToken(@NonNull String token) {
        this.token = token;
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    private void refreshNama() {
        nama = pref.getString(NAMA_USER, "");
    }

    @Nullable
    public String getNama() {
        return nama;
    }

    public void setNama(@Nullable String nama) {
        this.nama = nama;
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(NAMA_USER, nama);
        editor.apply();
    }

    private void refreshEmail() {
        email = pref.getString(EMAIL_USER, "");
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    private void refreshGender() {
        gender = pref.getString(GENDER_USER, "");
    }

    @Nullable
    public String getGender() {
        return gender;
    }

    public void setGender(@Nullable String gender) {
        this.gender = gender;
    }

    private void refreshTelepon() {
        telepon = pref.getString(TELEPON_USER, "");
    }

    @Nullable
    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(@Nullable String telepon) {
        this.telepon = telepon;
    }

    private void refreshAlamat() {
        alamat = pref.getString(ALAMAT_USER, "");
    }

    @Nullable
    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(@Nullable String alamat) {
        this.alamat = alamat;
    }

    private void refreshFoto() {
        foto = pref.getString(FOTO_USER, "");
    }

    @Nullable
    public String getFoto() {
        return foto;
    }

    public void setFoto(@Nullable String foto) {
        this.foto = foto;
    }

}
