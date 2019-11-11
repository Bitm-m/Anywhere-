package com.absinthe.anywhere_.utils;

import android.widget.Toast;

import androidx.annotation.StringRes;

import com.absinthe.anywhere_.AnywhereApplication;

public class ToastUtil {
    /**
     * make a toast via a string
     *
     * @param text a string text
     */
    public static void makeText(String text) {
        Toast.makeText(AnywhereApplication.sContext, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * make a toast via a resource id
     *
     * @param resId a string resource id
     */
    public static void makeText(@StringRes int resId) {
        Toast.makeText(AnywhereApplication.sContext, AnywhereApplication.sContext.getText(resId), Toast.LENGTH_SHORT).show();
    }
}
