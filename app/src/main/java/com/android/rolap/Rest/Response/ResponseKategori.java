package com.android.rolap.Rest.Response;

import java.util.List;

public class ResponseKategori {

    public boolean status;
    public  String message;
    public List<Data> data;

    public class Data {
        public String id;
        public String nama;
        public int total_tutor;
        public String foto;
    }
}
