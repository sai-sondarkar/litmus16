package itmvu.litmus17;

import android.app.Application;

import com.firebase.client.Firebase;
import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by ABCD on 1/8/2016.
 */
public class PushNotify extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
       Firebase.getDefaultConfig().setPersistenceEnabled(true);
        //System.out.println("Application");
        Parse.initialize(this, "TY2z22hCcpzd3jilZmfcWvWGquBDmgIAgd4b0mne", "UTqGUHbri6YP1xzOkMxcEEIYEEvdoiHagFgEZ0dX");
       // PushService.setDefaultPushCallback(this, abtus.class);
        //ParseInstallation.getCurrentInstallation().saveEventually();
        ParseInstallation.getCurrentInstallation().saveInBackground();

    }



}
