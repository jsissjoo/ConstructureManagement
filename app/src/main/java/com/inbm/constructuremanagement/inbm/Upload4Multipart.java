package com.inbm.constructuremanagement.inbm;

import java.io.IOException;
import java.util.ArrayList;

abstract public class Upload4Multipart extends AbsWeb {
    ArrayList<String> path;
    String json;

    public Upload4Multipart(OnJsonLoadListener listener, ArrayList<String> path,String json) {
        super(listener);
        this.path = path;
        this.json = json;
    }

    //http://inbm.com/1.png&N=31324&E=789790

    @Override
    protected String getHTML(String url) throws IOException {
        return _web.multipart(url,path,json);
    }

}


