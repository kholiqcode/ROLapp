package com.android.rolap.Rest.Response;

import java.util.List;

public class ResponseJadwal {
    public boolean status;
    public  String message;
    public List<Data> data;

    public class Data {
        public String id;
        public String id_tutor;
        public String id_users;
        public String id_pembayaran;
        public String nama_tutor;
        public String nama_user;
        public String jenis_kelamin;
        public String telepon;
        public String alamat;
        public String total_trx;
        public String total_rate;
        public String rate_avg;
        public String foto;
        public String metode_pembayaran;
        public String tanggal;
        public String waktu;
        public String status;
        public String total;

    }
}
