package com.inbm.constructuremanagement;

import com.inbm.constructuremanagement.inbm.UploadMany;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class UploadImages extends UploadMany {
    public UploadImages(OnJsonLoadListener listener, ArrayList<String> path) {
        super(listener, path);
    }

    @Override
    public <T> T parse(String html) throws JSONException, IOException {
        return (T) html;
    }

    @Override
    protected String getURL() {
        return "http://192.168.0.12:3000/profiles";
    }
}
