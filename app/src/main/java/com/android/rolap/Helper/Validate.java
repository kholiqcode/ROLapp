package com.android.rolap.Helper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Validate {
    public static boolean isNull(@Nullable String str) {
        return str == null || str.equalsIgnoreCase("null") || str.trim().length() == 0;
    }

    @NonNull
    public static boolean isNotNull(@Nullable String str) {
        return !(str == null || str.equalsIgnoreCase("null") || str.trim().length() == 0);
    }
}
