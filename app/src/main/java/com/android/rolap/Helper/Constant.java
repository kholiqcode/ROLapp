package com.android.rolap.Helper;

public class Constant {

    public static final String WEBSERVICE_PATH = "http://192.168.0.108/tutor/api/";
    public static final String WEBSERVICE_IMAGE = "http://192.168.0.108/tutor/public/images/";
    public static final String WEBSERVICE_API_PATH = "api/";
    public static final int Response_OK = 200;

    public static class RequestPermissions {
        public static final int READ_EXTERNAL_STORAGE = 1111;
        public static final int CAMERA = 1112;
    }

    public static class ActivityForResult {
        public static final int CAMERA = 1010;
        public static final int GALLERY = 1011;
    }
}