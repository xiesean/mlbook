package org.sean.csj;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;

import io.legado.app.BuildConfig;

public class CAD {
    private static String APP_ID = BuildConfig.CAD_ID;
    public static String UID_BANNER = BuildConfig.CAD_BANNER;
    public static String UID_COVER = BuildConfig.CAD_COVER;
    public static String CAD_VIDEO_COVER = BuildConfig.CAD_VIDEO_COVER;
    public static String UID_SPLASH = BuildConfig.CAD_SPLASH;
    public static String UID_REWARD = BuildConfig.CAD_REWARD;

    public static void init(Context context) {
    }

    public static void showReward(Activity activity) {
    }

    public static void showReward(Activity activity, AdCallback callback) {
    }

    public static boolean hasRewardAd(Activity activity) {
        return false;
    }

    public static void showSplashAd(Activity activity, ViewGroup container, AdCallback listener) {
    }
}
