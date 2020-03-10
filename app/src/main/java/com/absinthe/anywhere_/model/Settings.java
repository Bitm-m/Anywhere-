package com.absinthe.anywhere_.model;

import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatDelegate;

import com.absinthe.anywhere_.AnywhereApplication;
import com.absinthe.anywhere_.BuildConfig;
import com.absinthe.anywhere_.utils.StorageUtils;
import com.absinthe.anywhere_.utils.UiUtils;
import com.absinthe.anywhere_.utils.manager.IconPackManager;
import com.absinthe.anywhere_.utils.manager.Logger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Settings {
    @SuppressLint("StaticFieldLeak")
    public static IconPackManager sIconPackManager;
    public static IconPackManager.IconPack sIconPack;
    public static String sDate;
    public static String sToken;

    public static final String DEFAULT_ICON_PACK = "default.icon.pack";

    public static void init() {
        setLogger();
        setTheme(GlobalValues.sDarkMode);
        initIconPackManager();
        setDate();
        initToken();
    }

    public static void release() {
        sIconPackManager.setContext(null);
        sIconPackManager = null;
    }

    public static void setTheme(String mode) {
        switch (mode) {
            case "":
            case Const.DARK_MODE_OFF:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case Const.DARK_MODE_ON:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case Const.DARK_MODE_SYSTEM:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case Const.DARK_MODE_AUTO:
                AppCompatDelegate.setDefaultNightMode(UiUtils.getAutoDarkMode());
                break;
            default:
        }
    }

    public static void setLogger() {
        Logger.setDebugMode(BuildConfig.DEBUG | GlobalValues.sIsDebugMode);
    }

    public static void initIconPackManager() {
        sIconPackManager = new IconPackManager();
        sIconPackManager.setContext(AnywhereApplication.sContext);

        HashMap<String, IconPackManager.IconPack> hashMap = sIconPackManager.getAvailableIconPacks(true);

        for (Map.Entry<String, IconPackManager.IconPack> entry : hashMap.entrySet()) {
            if (entry.getKey().equals(GlobalValues.sIconPack)) {
                sIconPack = entry.getValue();
            }
        }
        if (sIconPack == null) {
            GlobalValues.setsIconPack(DEFAULT_ICON_PACK);
        }
    }

    private static void setDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd", Locale.getDefault());
        sDate = dateFormat.format(date);
    }

    private static void initToken() {
        try {
            sToken = StorageUtils.getTokenFromFile(AnywhereApplication.sContext);
        } catch (IOException e) {
            sToken = "";
        }
    }
}
