package com.inbm.constructuremanagement.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.inbm.constructuremanagement.inbm._log;
import com.inbm.constructuremanagement.inbm._uri;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class _crop {

    public static final int CROP_REQUEST_CODE = 2002;

    static Uri cropUri;
    private static String mCurrentPhotoPath;

    // 카메라 전용 크랍
    public static void cropImage(Context context, Uri uri) {
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");

            File cropFile = createImageFile(context);
//            cropUri = Uri.fromFile(cropFile);
            cropUri = _uri.getUriForFile(context, cropFile);

            // 50x50픽셀미만은 편집할 수 없다는 문구 처리 + 갤러리, 포토 둘다 호환하는 방법
            cropIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            cropIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            cropIntent.setDataAndType(uri, "image/*");
            //cropIntent.putExtra("outputX", 200); // crop한 이미지의 x축 크기, 결과물의 크기
            //cropIntent.putExtra("outputY", 200); // crop한 이미지의 y축 크기
            cropIntent.putExtra("aspectX", 1); // crop 박스의 x축 비율, 1&amp;1이면 정사각형
            cropIntent.putExtra("aspectY", 1); // crop 박스의 y축 비율
            cropIntent.putExtra("scale", true);
            cropIntent.putExtra("output", cropUri); // 크랍된 이미지를 해당 경로에 저장
            ((AppCompatActivity) context).startActivityForResult(cropIntent, CROP_REQUEST_CODE);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cropImage2(Context context, Uri uri) {
        try {
            context.grantUriPermission("com.android.camera", uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, "image/*");

            File cropFile = createImageFile(context);
//            cropUri = Uri.fromFile(cropFile);
            cropUri = _uri.getUriForFile(context, cropFile);

            List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, 0);
            context.grantUriPermission(list.get(0).activityInfo.packageName, uri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            int size = list.size();
            if (size == 0) {
                Toast.makeText(context, "취소 되었습니다.", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(context, "용량이 큰 사진의 경우 시간이 오래 걸릴 수 있습니다.", Toast.LENGTH_SHORT).show();
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX", 4);
                intent.putExtra("aspectY", 3);
                intent.putExtra("scale", true);
                File croppedFileName = null;
                try {
                    croppedFileName = createImageFile(context);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                intent.putExtra("return-data", false);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
                intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString()); //Bitmap 형태로 받기 위해 해당 작업 진행

                Intent i = new Intent(intent);
                ResolveInfo res = list.get(0);
                i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                i.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                context.grantUriPermission(res.activityInfo.packageName, cropUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);

                i.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                ((AppCompatActivity) context).startActivityForResult(i, CROP_REQUEST_CODE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 카메라 전용 크랍
    public static void cropImage3(Context context, Uri photoURI, Uri albumURI){
        Log.i("cropImage", "Call");
        Log.i("cropImage", "photoURI : " + photoURI + " / albumURI : " + albumURI);

        Intent cropIntent = new Intent("com.android.camera.action.CROP");

        // 50x50픽셀미만은 편집할 수 없다는 문구 처리 + 갤러리, 포토 둘다 호환하는 방법
        cropIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        cropIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        cropIntent.setDataAndType(photoURI, "image/*");
        //cropIntent.putExtra("outputX", 200); // crop한 이미지의 x축 크기, 결과물의 크기
        //cropIntent.putExtra("outputY", 200); // crop한 이미지의 y축 크기
        cropIntent.putExtra("aspectX", 1); // crop 박스의 x축 비율, 1&amp;1이면 정사각형
        cropIntent.putExtra("aspectY", 1); // crop 박스의 y축 비율
        cropIntent.putExtra("scale", true);
        cropIntent.putExtra("output", albumURI); // 크랍된 이미지를 해당 경로에 저장
        ((AppCompatActivity) context).startActivityForResult(cropIntent, CROP_REQUEST_CODE);
    }

    private static Uri galleryAddPic(Context context){
        Log.i("galleryAddPic", "Call");
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        // 해당 경로에 있는 파일을 객체화(새로 파일을 만든다는 것으로 이해하면 안 됨)
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = _uri.getUriForFile(context, f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
        Toast.makeText(context, "사진이 앨범에 저장되었습니다.", Toast.LENGTH_SHORT).show();

        return contentUri;
    }

    public static Uri getCropUri(Context context) {
        cropUri = galleryAddPic(context);

        return cropUri;
    }

    public static File createImageFile(Context context) throws IOException {

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_crop.jpg";
        File imageFile = null;

        File storageDir = new File(context.getExternalFilesDir(null), "Camera");
        _log.e(storageDir.getAbsolutePath());

        if (!storageDir.exists()) {
            Log.i("mCurrentPhotoPath1", storageDir.toString());
            storageDir.mkdirs();
        }

        imageFile = new File(storageDir, imageFileName);
        mCurrentPhotoPath = imageFile.getAbsolutePath();

        return imageFile;
    }

    public static Uri getContentUriFromFile(Context context, File file) throws FileNotFoundException {
        return Uri.parse(
                MediaStore.Images.Media.insertImage(
                        context.getContentResolver(),
                        file.getAbsolutePath(),
                        null,
                        null
                )
        );
    }

}
