package org.sean.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.sean.BaseApplication;


public class AppUtil {
    private static final int WHAT_TOAST = 1;

    private static Application application = BaseApplication.getApplication();

    static Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case WHAT_TOAST:
                    Toast.makeText(application, (String) msg.obj, Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    public static void showToast(String msg) {
        if (application != null) {
            Message message = new Message();
            message.what = WHAT_TOAST;
            message.obj = msg;
            handler.sendMessage(message);
        }
    }

    private static void runUiThread(int what) {
        Message msg = new Message();
        msg.what = what;
        handler.sendMessage(msg);
    }

    /**
     * 跳转到应用商店评分
     *
     * @param context
     */
    public static void goAppShop(Context context) {
        try {
            Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            // Do nothing
        }
    }

    public static void shareApp(Context context, String title, String text) {
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, text);
            intent.setType("text/plain");
            context.startActivity(Intent.createChooser(intent, title));
        } catch (Exception e) {
            // Do nothing
        }
    }
}
