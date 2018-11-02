package com.inbm.constructuremanagement.inbm;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

/**
 * Created by inbm on 2017. 3. 24..
 */

public class _web {

    public static String uploadOne(String url, String path) throws IOException{

        final MediaType MEDIA_TYPE = path.endsWith("png") ?
                MediaType.parse("image/png") : MediaType.parse("image/jpeg");
        File file1 = new File(path);
        String html = "";
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("photos", file1.getName(), RequestBody.create(MEDIA_TYPE, file1))
                .build();

        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = client.newCall(request).execute();

        html = response.body().string();

        return html;
    }


    public static String uploadMany(String url, ArrayList<String> path) throws IOException{
        String html = "";
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("title","photos");

        for(int i = 0; i < path.size();i++){
            File file = new File(path.get(i));
            final MediaType MEDIA_TYPE = path.get(i).endsWith("png") ?
                    MediaType.parse("image/png") : MediaType.parse("image/jpeg");
            builder.addFormDataPart("photos"+i, file.getName(), RequestBody.create(MEDIA_TYPE, file));
        }
        RequestBody requestBody = builder.build();

        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = client.newCall(request).execute();

        html = response.body().string();

        return html;
    }

    public static String multipart (String url, ArrayList<String> path, String json) throws IOException{
        String html = "";
        OkHttpClient client = new OkHttpClient();

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("payload",json);

        for(int i = 0; i < path.size();i++){
            File file = new File(path.get(i));
            final MediaType MEDIA_TYPE = path.get(i).endsWith("png") ?
                    MediaType.parse("image/png") : MediaType.parse("image/jpeg");
            builder.addFormDataPart("photos"+i, file.getName(), RequestBody.create(MEDIA_TYPE, file));
        }
        RequestBody requestBody = builder.build();

        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = client.newCall(request).execute();

        html = response.body().string();

        return html;
    }


    public static String get(String url) throws IOException {
        String html = "";
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        html = response.body().string();

        return html;
    }
    public static String post(String url, String json)  {
        String html = "";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        Response response;
        try {
            response = client.newCall(request).execute();
            html = response.body().string();
        } catch (IOException e) {
            _log.m("post error", e.getMessage());
        }
        return html;
    }

    //TODO REVIEW
    public static String delete(String url, String json)  {
        String html = "";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();


        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).delete(body).build();

        Response response;

        try {
            response = client.newCall(request).execute();
            html = response.body().string();
        } catch (IOException e) {
            _log.m("delete error", e.getMessage());
        }
        return html;
    }


    public static String postWithHeaders(String url, String json, _header_ ...headers){
        String html = "";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();


        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder builder = new Request.Builder();


        //Request request = new Request.Builder();//.url(url).post(body).build();

        for(int i=0; i<headers.length; i++){
            builder.addHeader(headers[i].key, headers[i].value);
        }
        Request request = builder.url(url).post(body).build();
        Response response;

        try {
            response = client.newCall(request).execute();
            html = response.body().string();
        } catch (IOException e) {
            _log.m("post error", e.getMessage());
        }
        return html;
    }
    public static String getWithHeaders(String url, _header_ ...headers){
        String html = "";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();


        Request.Builder builder = new Request.Builder();


        //Request request = new Request.Builder();//.url(url).post(body).build();

        for(int i=0; i<headers.length; i++){
            builder.addHeader(headers[i].key, headers[i].value);
        }
        Request request = builder.url(url).build();

        Response response;

        try {
            response = client.newCall(request).execute();
            html = response.body().string();
        } catch (IOException e) {
            _log.m("get error", e.getMessage());
        }
        return html;
    }


    public static String deleteWithHeaders(String url, String json, _header_ ...headers){
        String html = "";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();


        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder builder = new Request.Builder();


        //Request request = new Request.Builder();//.url(url).post(body).build();

        for(int i=0; i<headers.length; i++){
            builder.addHeader(headers[i].key, headers[i].value);
        }
        Request request = builder.url(url).delete(body).build();


        Response response;

        try {
            response = client.newCall(request).execute();
            html = response.body().string();
        } catch (IOException e) {
            _log.m("detele error", e.getMessage());
        }
        return html;
    }

    public static class _header_{
        public _header_(String key, String value) {
            this.key = key;
            this.value = value;
        }

        String key;
        String value;
    }




}
