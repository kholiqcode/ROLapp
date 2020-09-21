package com.android.rolap.Helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.android.rolap.R;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Helper {
    private static final String TAG = Helper.class.getSimpleName();
    private Context context;
    private Activity activity;
    private PrefManager prefmanager;

    public Helper(Context context) {
        this.context = context;
        this.activity = (Activity) context;
        prefmanager = new PrefManager(context);
    }

    public Helper(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public boolean isOnline() {
        return isOnline(context);
    }

    public void showAlert(@NonNull String message) {
        Message.showAlert(activity, message);
    }

    // TOAST
    public void showToast(@NonNull String message) {
        Message.showToast(context, message);
    }

    public void showToast(int resId) {
        Message.showToast(context, resId);
    }

    // Put the image file path into this method
    public String getFileToByte(Uri mImageUri){
        Bitmap bmp = null;
        ByteArrayOutputStream bos = null;
        byte[] bt = null;
        String encodeString = null;
        try{
            bmp =  MediaStore.Images.Media.getBitmap(this.context.getContentResolver(),mImageUri);
            bos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 25, bos);
            bt = bos.toByteArray();
            encodeString = Base64.encodeToString(bt, Base64.DEFAULT);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return encodeString;
    }

    public String bitmapToBase64(Bitmap bmp){
        ByteArrayOutputStream bos = null;
        byte[] bt = null;
        String encodeString = null;
        try{
            bos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 25, bos);
            bt = bos.toByteArray();
            encodeString = Base64.encodeToString(bt, Base64.DEFAULT);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return encodeString;
    }

    public void AnimationRight(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    public void AnimationLeft(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_left);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    public void logout() {
        prefmanager.setToken("");
    }

//    public void startCartActivity() {
//        Intent intent = new Intent(context, BasketActivity.class);
//        ActivityOptions options = ActivityOptions.makeCustomAnimation(context, R.anim.slide_in, R.anim.slide_out);
//        context.startActivity(intent, options.toBundle());
//    }


    public static boolean isOnline(@NonNull Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            return nInfo != null && nInfo.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getCurrentDateTimeMix() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
            return sdf.format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return "0000-00-00 00:00:00";
        }
    }

    public static void hideKeyboard(@NonNull Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String base64Encode(String input){
        byte[] bytes = input.getBytes();
        try {
            return new String(Base64.encode(bytes, Base64.NO_WRAP), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Got unsupported encoding: " + e);
            return "encode-error";
        }
    }

    public static String convertStatus(String status){
        String res = "";
        if(status.equalsIgnoreCase("0")){
            res = "Belum Dibayar";
        }else if(status.equalsIgnoreCase("1")){
            res = "Terbayar";
        }else if(status.equalsIgnoreCase("2")){
            res = "Diproses";
        }else if(status.equalsIgnoreCase("3")){
            res = "Selesai";
        }else{
            res = "-";
        }
        return res;
    }
}
