package com.inbm.constructuremanagement;

import com.inbm.constructuremanagement.inbm.Post;
import com.inbm.constructuremanagement.inbm._log;

import org.json.JSONException;

import java.io.IOException;

public class PostAdminToken extends Post {
    public PostAdminToken(OnJsonLoadListener listener, String json) {
        super(listener, json);
    }

    @Override
    public <T> T parse(String html) throws JSONException, IOException {
        return (T) html;
    }

    @Override
    protected String getURL() {
        return "http://59.10.5.127:5609/addtoken";
    }
}
