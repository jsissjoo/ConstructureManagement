package com.inbm.constructuremanagement;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.inbm.constructuremanagement.inbm.App;
import com.inbm.constructuremanagement.inbm._log;


public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";
    String token;

    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        App.firebase_token = token;
        registToken2Server(token);
    }

    //TODO send token to server
    private void registToken2Server(String token) {
        _log.e("===============================token : "+token);
    }
}