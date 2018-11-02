package com.inbm.constructuremanagement;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.inbm.constructuremanagement.inbm.AbsWeb;
import com.inbm.constructuremanagement.inbm._log;
import com.inbm.constructuremanagement.media._camera;
import com.inbm.constructuremanagement.rvs_adapters.Adapter4Images;
import com.inbm.constructuremanagement.rvs_adapters.Adapter4Quest;
import com.inbm.constructuremanagement.rvs_adapters.RV4Images;
import com.inbm.constructuremanagement.rvs_adapters.RV4Report;
import com.inbm.constructuremanagement.rvs_adapters._questions_;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ReportActivity extends AppCompatActivity {

    RV4Images rv4Images;
    RV4Report rv4Report;

    Adapter4Images adapter4Images;
    Adapter4Quest adapter4Quest;

    Button btn_image, btn_for_camera;

    ImageButton btn_reset;
    static ImageButton btn_submit;

    ArrayList<_questions_> questions;
    ArrayList<_photo_info_> reportImages;
    Uri uri;
    Context context;
    final static int REQUEST_PERMISSION_CODE = 40000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_report);

        context = ReportActivity.this;
        reportImages = new ArrayList<_photo_info_>();

        btn_reset = findViewById(R.id.ibtn_reset);
        btn_submit =findViewById(R.id.ibtn_submit);
        btn_image = findViewById(R.id.btn_for_camera);
        rv4Images = findViewById(R.id.rv_images);
        rv4Report = findViewById(R.id.rv_report);

        permissions();
        getAppRequest();

        adapter4Images = new Adapter4Images(this, reportImages);
        rv4Images.setAdapter(adapter4Images);

        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissions();
                _camera.captureCamera(ReportActivity.this);

            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter4Images.photos.clear();
                adapter4Images.notifyDataSetChanged();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

//    ~.jpg&N~~&E~~

    @Override
    protected void onResume() {
        super.onResume();
        adapter4Images.notifyDataSetChanged();
    }

    void getAppRequest() {
        new GetQuestions(new AbsWeb.OnJsonLoadListener() {
            @Override
            public <T> void onJsonLoad(T t) {
                _log.e(t.toString());
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<_questions_>>() {}.getType();
                questions = gson.fromJson(String.valueOf(t), type);
                setRv4ReportAdapter();
            }
        }, "");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            uri = _camera.getCaptureUri();

            adapter4Images.photos = reportImages;
            adapter4Images.notifyDataSetChanged();
            String filename = _camera.getCapturePath();

            try {
                ExifInterface exif = new ExifInterface(filename);
                float[] latlong = new float[] {0f, 0f};
                exif.getLatLong(latlong);
                reportImages.add(new _photo_info_(uri,latlong[0],latlong[1]));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            return;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //동의 했을 경우
                } else {
                    //거부했을 경우
                    Toast.makeText(this, "기능 사용을 위한 모든 권한 동의가 필요합니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    void permissions(){
        String[] Permissions = new String[5];
        Permissions[0] = ACCESS_FINE_LOCATION;
        Permissions[1] = ACCESS_COARSE_LOCATION;
        Permissions[2] = CAMERA;
        Permissions[3] = READ_EXTERNAL_STORAGE;
        Permissions[4] = WRITE_EXTERNAL_STORAGE;
        ActivityCompat.requestPermissions(ReportActivity.this, Permissions, REQUEST_PERMISSION_CODE);
    }

    void setRv4ReportAdapter(){
        adapter4Quest = new Adapter4Quest(this, questions);
        rv4Report.setAdapter(adapter4Quest);
    }

    void postReport(){
        new PostReport(new AbsWeb.OnJsonLoadListener() {
            @Override
            public <T> void onJsonLoad(T t) {

            }
        },"");
    }

    static void post(){

    }

}
