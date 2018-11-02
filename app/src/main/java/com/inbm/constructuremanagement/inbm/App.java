package com.inbm.constructuremanagement.inbm;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by inbm on 2017. 2. 27..
 */
public class App extends Application {

    private static Context context;
    public static String firebase_token;

    @Override
    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();

        //firebase
        firebase_token = FirebaseInstanceId.getInstance().getToken();
        setFirebase_token(firebase_token);

    }

    public static Context getStaticContext() {
        return App.context;
    }

    public static void setFirebase_token(String firebase_token) {
        App.firebase_token = firebase_token;
    }

    public static String getFirebase_token() {
        return firebase_token;
    }

    public static void _toast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}