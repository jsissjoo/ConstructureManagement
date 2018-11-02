package com.inbm.constructuremanagement.rvs_adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.inbm.constructuremanagement.ImageDetailActivity;
import com.inbm.constructuremanagement.R;
import com.inbm.constructuremanagement.ReportActivity;
import com.inbm.constructuremanagement._photo_info_;
import com.inbm.constructuremanagement.inbm.recycler.AbsAdapter4Header;
import com.inbm.constructuremanagement.inbm.recycler.AbsViewHolder;

import java.net.URI;
import java.util.ArrayList;

public class Adapter4Images extends AbsAdapter4Header {

    public static ArrayList<_photo_info_> photos;

    public Adapter4Images(Context context, OnClicked clicklistener, ArrayList<_photo_info_> photos ) {
        super(context, clicklistener);
        this.photos = photos;
    }

    public Adapter4Images(Context context,ArrayList<_photo_info_> photos) {
        super(context);
        this.photos = photos;
    }

    @Override
    protected int getLayout() {
        return R.layout.row_4_images;
    }

    @Override
    protected int getHeadLayout() {
        return 0;
    }

    @Override
    protected AbsViewHolder getViewHolder(View v) {
        return new VH4Images(v);
    }

    @Override
    protected void display(AbsViewHolder holder, int position) {
        VH4Images vh = (VH4Images) holder;
        Glide.with(context).load(photos.get(position).getPhotoUri()).apply(RequestOptions.centerCropTransform()).into(vh.iv_image);
    }

    @Override
    protected int getCount() {
        if(photos==null)return 0;
        else return photos.size();
    }

    private class VH4Images extends AbsViewHolder {
        ImageView iv_image;
        public VH4Images(View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);
            iv_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent imageDetailIntent = new Intent(context, ImageDetailActivity.class);
                    imageDetailIntent.putExtra("position",position);
                    imageDetailIntent.putExtra("image",Adapter4Images.this.photos.get(position).getPhotoUri().getPath());
                    imageDetailIntent.putExtra("lat",Adapter4Images.this.photos.get(position).getLatitude());
                    imageDetailIntent.putExtra("long",Adapter4Images.this.photos.get(position).getLongitude());
                    context.startActivity(imageDetailIntent);
                }
            });
        }

        @Override
        protected void ui4FocusIn(View v, AbsViewHolder holder) {

        }

        @Override
        protected void ui4FocusOut(View v, AbsViewHolder holder) {

        }
    }
}
