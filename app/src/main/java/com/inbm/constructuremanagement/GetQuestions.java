package com.inbm.constructuremanagement;

import com.inbm.constructuremanagement.inbm.Get;

import org.json.JSONException;

import java.io.IOException;

public class GetQuestions extends Get {
    public GetQuestions(OnJsonLoadListener listener, String param) {
        super(listener, param);
    }

    @Override
    public <T> T parse(String html) throws JSONException, IOException {
        return (T) html;
    }

    @Override
    protected String getURL() {
        return "http://inbm002.iptime.org:3000/quests/allquest";
    }
}
