package com.inbm.constructuremanagement.image_info;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MetaDataExtract {

    public ArrayList<MetaData> MetaDataExtract(final Context context) {

        ArrayList<MetaData> result = new ArrayList<>();

        //Images...
        Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] imageProjection = {MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME,
                MediaStore.Images.ImageColumns.LATITUDE, MediaStore.Images.ImageColumns.LONGITUDE,
                MediaStore.Images.ImageColumns.DATE_TAKEN};
        Cursor Image_cursor = context.getContentResolver().query(imageUri, imageProjection, null, null, MediaStore.Images.ImageColumns.DATE_TAKEN + " desc");

        //데이터
        int columnImageData = Image_cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        int columnImageLat = Image_cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.LATITUDE);
        int columnImageLong = Image_cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.LONGITUDE);
        int columnImageDate = Image_cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATE_TAKEN);
        int columnDisplayname = Image_cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);

        int imageLastIndex;
        while (Image_cursor.moveToNext()) {
            String imagePath = Image_cursor.getString(columnImageData);
            double imagesLat = Image_cursor.getDouble(columnImageLat);
            double imageLong = Image_cursor.getDouble(columnImageLong);
            String imageDate = Image_cursor.getString(columnImageDate);
            String nameOfFile = Image_cursor.getString(columnDisplayname);
            //Date 바꾸기
            long addIDate = Long.parseLong(imageDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(addIDate);

            Date iDate = calendar.getTime();

            imageLastIndex = imagePath.lastIndexOf(nameOfFile);
            imageLastIndex = imageLastIndex >= 0 ? imageLastIndex : nameOfFile.length() - 1;

            if (!TextUtils.isEmpty(imagePath) && Double.compare(imagesLat, 0.0) != 0) {
                //생성자 사용.
                MetaData metaData = new MetaData(imagePath, imagesLat, imageLong, iDate);
                result.add(metaData);
            }
        }

        //Videos....
        Uri videoUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] videoProjection = {MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME,
                MediaStore.Video.VideoColumns.LATITUDE, MediaStore.Video.VideoColumns.LONGITUDE,
                MediaStore.Video.VideoColumns.DATE_TAKEN};
        Cursor videoCursor = context.getContentResolver().query(videoUri, videoProjection, null, null, MediaStore.Video.VideoColumns.DATE_TAKEN + " desc");

        //데이터
        int columnVideoData = videoCursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);//파일
        int columnVideoLat = videoCursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.LATITUDE);//위도
        int columnVideoLng = videoCursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.LONGITUDE);//경도
        int columnVideoDate = videoCursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.DATE_TAKEN);//촬영날짜
        int columnDisplayVideoName = videoCursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);// 파일이름


        int videoLastIndex;
        //넣어버리기~
        while (videoCursor.moveToNext()) {
            String videoPath = videoCursor.getString(columnVideoData);
            double videosLat = videoCursor.getDouble(columnVideoLat);
            double videoLong = videoCursor.getDouble(columnVideoLng);
            String videoDate = videoCursor.getString(columnVideoDate);
            String videoNameOfFile = videoCursor.getString(columnVideoData);

            //Date 바꾸기
            long addvDate = Long.parseLong(videoDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(addvDate);
            Date vDate = calendar.getTime();

            videoLastIndex = videoPath.lastIndexOf(videoNameOfFile);
            videoLastIndex = videoLastIndex >= 0 ? videoLastIndex : videoNameOfFile.length() - 1;

            if (!TextUtils.isEmpty(videoPath) && Double.compare(videoLong, 0.0) != 0) {
                //생성자 사용.
                MetaData metaData = new MetaData(videoPath, videosLat, videoLong, vDate);
                result.add(metaData);
            }

        }


        return result;
    }
}
