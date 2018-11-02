package com.inbm.constructuremanagement.inbm;

import android.os.Handler;
import android.os.Message;

import org.json.JSONException;

import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created by inbm on 2017. 3. 24..
 */

public abstract class AbsWeb {
    protected String html = "";

    OnJsonLoadListener listener;
    protected WeakReferenceHandler handler;
    protected OnException exception;


    public interface OnException{
        void because(String message);
    }

    public abstract <T> T parse(String html) throws JSONException, IOException;


    public interface OnJsonLoadListener {
        <T> void onJsonLoad(T t);
    }

    public AbsWeb setExceptionListener(OnException listener){
        this.exception = listener;
        return this;
    }

    //서버 다녀올 때 오류나면 얘 사용!
    public void because(OnException exception){
        //사용법 : Get/Post방식 뒤에 .because(c -> _log.e(c));
        //서버에 다녀올 때 오류가 난다면 오류를 띄워준다.
        this.exception=exception;
    }

    static class WeakReferenceHandler extends Handler
    {
        WeakReference<Object> reference;

        WeakReferenceHandler(Object o)
        {
            reference = new WeakReference<Object>(o);
        }
    }

    public AbsWeb(final OnJsonLoadListener listener )  {

        handler = new WeakReferenceHandler(this){
            public void handleMessage(Message msg){

                try{
                    listener.onJsonLoad( parse(html));

                    super.handleMessage(msg);
                } catch(Exception e){
                    if(exception != null)
                        exception.because(e.getMessage());
                }
            }
        };


        new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    html = getHTML(getURL() );
                    handler.sendEmptyMessage(0);

                } catch (Exception e) {
                    if(exception != null)
                    exception.because(e.getMessage());
                }
            }
        }).start();
    }


    protected abstract String getHTML(String url) throws IOException;
    protected abstract String getURL();


}
