package com.inbm.constructuremanagement;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.inbm.constructuremanagement.inbm.recycler.AbsViewHolder;
import com.inbm.constructuremanagement.rvs_adapters.Adapter4Images;

public class ImageDetailActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton ibtn_back, ibtn_delete;
    ImageView iv_detail;
    TextView tv_lati_long;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        //layout
        ibtn_back = findViewById(R.id.ibtn_back);
        ibtn_delete = findViewById(R.id.ibtn_delete);
        iv_detail = findViewById(R.id.iv_image_detail);
        tv_lati_long = findViewById(R.id.tv_lati_long);

        //btn listener
        ibtn_back.setOnClickListener(this);
        ibtn_delete.setOnClickListener(this);

        //getintents + set

        Glide.with(this).load(getIntent().getStringExtra("image")).into(iv_detail);
        String latnlon = "N : " + getIntent().getDoubleExtra("lat",0.0) + "\nE : " + getIntent().getDoubleExtra("long",0.0);
//        Toast.makeText(this, latnlon, Toast.LENGTH_SHORT).show();
        tv_lati_long.setText(latnlon);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.ibtn_back:
                finish();
                overridePendingTransition(0,0);
                break;
            case R.id.ibtn_delete:
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(ImageDetailActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(ImageDetailActivity.this);
                }
                builder.setMessage("사진을 삭제하시겠습니까? ")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                                Adapter4Images.photos.remove(getIntent().getIntExtra("position",0));
                                finish();
                                overridePendingTransition(0,0);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                break;
        }
    }
}
