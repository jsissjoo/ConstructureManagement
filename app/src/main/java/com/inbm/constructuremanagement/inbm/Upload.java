package com.inbm.constructuremanagement.inbm;

import java.io.IOException;

abstract public class Upload extends AbsWeb {
    String path = "";

    public Upload(OnJsonLoadListener listener, String path) {
        super(listener);
        this.path = path;
    }

    @Override
    protected String getHTML(String url) throws IOException {
        return _web.uploadOne(url, path);
    }
}
