package com.inbm.constructuremanagement.inbm;

import java.io.IOException;

abstract public class GetWithHeaders extends AbsWeb{


    abstract protected  _web._header_[] getHeaders();
    protected String param = "";

    public GetWithHeaders(OnJsonLoadListener listener, String param) {
        super(listener);
        this.param= param;
    }

    @Override
    protected String getHTML(String url) throws IOException {
//        ArrayList<_header_> headers = new ArrayList<>();
        _log.e("============================================"+_web.getWithHeaders(url+param,getHeaders()));
        return _web.getWithHeaders(url+param,getHeaders());
    }

}
