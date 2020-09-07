package com.android.rolap.Rest.Response;

import java.util.List;

public class ResponsePembayaran {

    public boolean status;
    public  String message;
    public List<Data> data;

    public class Data {
        public String id;
        public String metode_pembayaran;
        public String nomor_rekening;
        public String status;
    }
}
