package com.inbm.constructuremanagement.inbm;

import org.json.JSONException;

public class LoginWeegle extends PostWithHeaders {
    public LoginWeegle(OnJsonLoadListener listener, String json) {
        super(listener, json);
    }

    @Override
    protected _web._header_[] getHeaders() {

        return new _web._header_[0];
    }

    @Override
    public <T> T parse(String html) throws JSONException {
        return null;
    }

    @Override
    protected String getURL() {
        return null;
    }
}
