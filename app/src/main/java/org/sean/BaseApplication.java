package org.sean;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import org.sean.csj.CAD;
import org.sean.google.admob.GAD;


/**
 * Created by Administrator on 2015/10/21.
 */
public class BaseApplication extends Application {

    private static Context context;
    private static Application application;
    private static Boolean supportGoogleService = null;

    public static Context getContext() {
        return context;
    }

    public static Application getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        application = this;
        if (isSupportGoogleService()) {
            GAD.init(this);
//            PayUtil.init();
        } else {
            CAD.init(this);
        }

    }

    public static boolean isSupportGoogleService() {
        supportGoogleService = true;
        if (supportGoogleService == null) {
            GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
            int resultCode = googleApiAvailability.isGooglePlayServicesAvailable(context);
            supportGoogleService = (resultCode == ConnectionResult.SUCCESS);
        }
        return supportGoogleService;
    }
}
