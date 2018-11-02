package com.inbm.constructuremanagement;

import com.google.firebase.iid.FirebaseInstanceId;
import com.inbm.constructuremanagement.inbm.App;
import com.inbm.constructuremanagement.inbm._log;

public class _firebase {
    public static void setToken() {
            String token = FirebaseInstanceId.getInstance().getToken();
            App.firebase_token = token;
    }
    public static String getToken(){
        return App.getFirebase_token();
    }

}
