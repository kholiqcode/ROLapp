package com.android.rolap.Rest.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseAdmin {
    public boolean status;
    public String message;
    public List<Data> data;

    public class Data{

        @SerializedName("no_wa")
        @Expose
        public String no_wa;

    }
}
