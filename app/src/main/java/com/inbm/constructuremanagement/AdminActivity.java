package com.inbm.constructuremanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.inbm.constructuremanagement.inbm.AbsWeb;
import com.inbm.constructuremanagement.inbm.App;
import com.inbm.constructuremanagement.inbm._log;

public class AdminActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        _firebase.setToken();
        String token = App.getFirebase_token();

        new PostAdminToken(new AbsWeb.OnJsonLoadListener() {
            @Override
            public <T> void onJsonLoad(T t) {
                _log.e(t.toString());
            }
        },"{\"token\" : \""+token+"\"}").because(c->_log.e(c));
    }
}
