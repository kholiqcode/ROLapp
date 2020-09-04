package com.android.rolap.Helper;

import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;

public class Message {

    /* -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- SHOW TOAST -=-=-=-=-=-=-=-=-=-=-=-=-=- */
    public static void showToast(@NonNull Context context, @NonNull String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showToast(@NonNull Context context, @StringRes int resId) {
        Toast toast = Toast.makeText(context, resId, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /* -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=- SHOW ALERT -=-=-=-=-=-=-=-=-=-=-=-=-=- */

    public static void showAlert(@NonNull Context context, @NonNull String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(@NonNull DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = alertDialog.create();
        dialog.show();

    }
}
