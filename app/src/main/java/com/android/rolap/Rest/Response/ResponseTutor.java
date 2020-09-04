package com.android.rolap.Rest.Response;

import java.util.List;

public class ResponseTutor {
    public boolean status;
    public  String message;
    public List<Data> data;

    public class Data {
        public String id;
        public String id_users;
        public String id_kategori;
        public String nama;
        public String jenis_kelamin;
        public String telepon;
        public String alamat;
        public String harga;
        public String total_trx;
        public String total_rate;
        public String rate_avg;
        public String foto;
    }
}
