package com.inbm.constructuremanagement.inbm;

import java.io.IOException;
import java.util.ArrayList;

abstract public class UploadMany extends AbsWeb {
    ArrayList<String> path;

    public UploadMany(OnJsonLoadListener listener, ArrayList<String> path) {
        super(listener);
        this.path = path;
    }

    @Override
    protected String getHTML(String url) throws IOException {
        return _web.uploadMany(url, path);
    }
}
