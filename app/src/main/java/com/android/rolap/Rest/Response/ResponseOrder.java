package com.android.rolap.Rest.Response;

import java.util.List;

public class ResponseOrder {
    public boolean status;
    public  String message;
    public List<Data> data;

    public class Data {
        public String id;
        public String id_tutor;
        public String id_users;
        public String id_pembayaran;
        public String nama;
        public String telepon;
        public String jenis_kelamin;
        public String alamat;
        public int total_trx;
        public float rate_avg;
        public int total_rate;
        public String metode_pembayaran;
        public String nomor_rekening;
        public String tanggal;
        public String waktu;
        public String status;
        public int total;
        public String foto;
    }
}
