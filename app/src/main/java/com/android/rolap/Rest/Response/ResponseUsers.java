package com.android.rolap.Rest.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseUsers {
    public boolean status;
    public String message;
    public List<DataUser> data;

    public class DataUser{

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getJenis_kelamin() {
            return jenis_kelamin;
        }

        public void setJenis_kelamin(String jenis_kelamin) {
            this.jenis_kelamin = jenis_kelamin;
        }

        public String getTelepon() {
            return telepon;
        }

        public void setTelepon(String telepon) {
            this.telepon = telepon;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getFoto() {
            return foto;
        }

        public void setFoto(String foto) {
            this.foto = foto;
        }

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("nama")
        @Expose
        public String nama;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("jenis_kelamin")
        @Expose
        public String jenis_kelamin;
        @SerializedName("telepon")
        @Expose
        public String telepon;
        @SerializedName("alamat")
        @Expose
        public String alamat;
        @SerializedName("foto")
        @Expose
        public String foto;

    }
}
