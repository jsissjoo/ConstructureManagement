package com.inbm.constructuremanagement;

import com.inbm.constructuremanagement.inbm.Upload;

import org.json.JSONException;

import java.io.IOException;

public class UploadImage extends Upload {
    public UploadImage(OnJsonLoadListener listener, String imagePath) {
        super(listener, imagePath);
    }

    @Override
    public <T> T parse(String html) throws JSONException, IOException {
        return (T) html;
    }

    @Override
    protected String getURL() {
        return "http://192.168.0.12:3000/profile";
    }
}
