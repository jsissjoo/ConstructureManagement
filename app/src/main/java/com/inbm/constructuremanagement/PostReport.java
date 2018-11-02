package com.inbm.constructuremanagement;

import com.inbm.constructuremanagement.inbm.Post;

import org.json.JSONException;

import java.io.IOException;

public class PostReport extends Post {
    public PostReport(OnJsonLoadListener listener, String json) {
        super(listener, json);
    }

    @Override
    public <T> T parse(String html) throws JSONException, IOException {
        html.toString();
        return null;
    }

    @Override
    protected String getURL() {
        return "http://192.168.0.28:3000/quests/allquest";
    }
}
