package com.inbm.constructuremanagement.rvs_adapters;

import com.inbm.constructuremanagement.inbm.Upload4Multipart;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class UploadImagesAndJson extends Upload4Multipart {
    public UploadImagesAndJson(OnJsonLoadListener listener, ArrayList<String> path, String json) {
        super(listener, path, json);
    }

    @Override
    public <T> T parse(String html) throws JSONException, IOException {
        return (T) html;
    }

    @Override
    protected String getURL() {
        return "http://192.168.0.3:3000/photo";
    }
}
