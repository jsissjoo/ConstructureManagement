package com.inbm.constructuremanagement;

import android.net.Uri;

public class _photo_info_ {
        Uri photoUri;
        float latitude,longitude;

        public _photo_info_(Uri photoUri, float latitude, float longitude) {
            this.photoUri = photoUri;
            this.latitude = latitude;
            this.longitude = longitude;
        }

    public Uri getPhotoUri() {
        return photoUri;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}