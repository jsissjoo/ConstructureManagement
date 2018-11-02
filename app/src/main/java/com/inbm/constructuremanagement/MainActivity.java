package com.inbm.constructuremanagement;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.inbm.constructuremanagement.inbm.AbsWeb;
import com.inbm.constructuremanagement.rvs_adapters.UploadImagesAndJson;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText et_id, et_pwd;
    Button btn_login,btn_upload_test,btn_upload_many_test, btn_select_image;
    Uri uri;
    ArrayList<String> images;

    public final static int GALLERY_REQUEST  = 4101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        images = new ArrayList<>();
        et_id = findViewById(R.id.et_id);
        et_pwd = findViewById(R.id.et_pwd);
        btn_login = findViewById(R.id.btn_login);
        btn_upload_test = findViewById(R.id.btn_upload_test);
        btn_upload_many_test = findViewById(R.id.btn_upload_many_test);

        btn_select_image = findViewById(R.id.btn_select_image);

        btn_select_image.setOnClickListener(this);
        btn_upload_many_test.setOnClickListener(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = et_id.getText().toString();
                String pwd = et_pwd.getText().toString();
                if(id.equals("admin")){
                    startActivity(new Intent(MainActivity.this,AdminActivity.class));
                }
                else{
                    startActivity(new Intent(MainActivity.this,ReportActivity.class));

                }
            }
        });

        btn_upload_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendGallaryPhotoIntent();
            }
        });
    }

    public void sendGallaryPhotoIntent(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            if (requestCode==GALLERY_REQUEST){
                uri = data.getParcelableExtra("file");
                final Uri uri_data = data.getData();
                final String path = getPathFromURI(uri_data);

                if (path != null) {
                    File f = new File(path);
                    uri = Uri.fromFile(f);
                }
            }
            String imagePath = uri.getPath();
            images.add(imagePath);

            //set image's api

        }
        else{

            return;

        }
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_select_image:
                sendGallaryPhotoIntent();
                break;
            case R.id.btn_upload_test:
                break;
            case R.id.btn_upload_many_test:
                new UploadImagesAndJson(new AbsWeb.OnJsonLoadListener() {
                    @Override
                    public <T> void onJsonLoad(T t) {

                    }
                },images,"{'name':'joo'}");
                break;
        }
    }
}
