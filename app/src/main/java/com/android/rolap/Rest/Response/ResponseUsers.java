package com.android.rolap.Rest.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseUsers {
    public boolean status;
    public String message;
    public List<Data> data;

    public class Data{

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
