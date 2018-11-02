package com.inbm.constructuremanagement.inbm;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


public class _permission {


    public static String[] getPermissions(int permissionRequestCode) {

        // TODO - add permission

        switch (permissionRequestCode) {


            case CAMERA_REQUEST_CODE:
                return CAMERA_PERMISSIONS;

            case STORAGE_REQUEST_CODE:
                return STORAGE_PERMISSIONS;

            case CONTACTS_REQUEST_CODE:
                return CONTACTS_PERMISSIONS;

            case SMS_REQUEST_CODE:
                return SMS_PERMISSIONS;

            case LOCATION_REQUEST_CODE:
                return LOCATION_PERMISSIONS;

            case RECORD_AUDIO_REQUEST_CODE:
                return RECORD_AUDIO_PERMISSIONS;

            case READ_PHONE_STATE_REQUEST_CODE: {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    return PHONE_STATE_PERMISSION4O;
                }
                return PHONE_STATE_PERMISSION;
            }
            default:
                return null;
        }

    }

    public static boolean hasPermission(Context context, int permissionRequestCode) {
        String[] permissions = getPermissions(permissionRequestCode);

        boolean hasPermission = true;

        for (String permission : permissions) {
            int permissionCheck = ContextCompat.checkSelfPermission(context, permission);

            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                hasPermission = false;
                break;
            }
        }

        return hasPermission;
    }

    public static void requestPermission(Context context, int permissionRequestCode) {
        if (!hasPermission(context, permissionRequestCode)) {
            ActivityCompat.requestPermissions((Activity) context, getPermissions(permissionRequestCode), permissionRequestCode);
        }
    }

    public static void checkPermission(Context context, int permissionRequestCode, OnPermission onPermission) {
        requestPermission(context, permissionRequestCode);

        if (hasPermission(context, permissionRequestCode)) {
            onPermission.onPermission();
        }
    }

    public interface OnPermission {
        void onPermission();
    }


    // TODO - add permission
    public static final int CAMERA_REQUEST_CODE = 9000;
    public static final String[] CAMERA_PERMISSIONS = {Manifest.permission.CAMERA};

    public static final int STORAGE_REQUEST_CODE = 9001;
    public static final String[] STORAGE_PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static final int CONTACTS_REQUEST_CODE = 9002;
    public static final String[] CONTACTS_PERMISSIONS = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.GET_ACCOUNTS};

    public static final int SMS_REQUEST_CODE = 9003;
    public static final String[] SMS_PERMISSIONS = {Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS};

    public static final int LOCATION_REQUEST_CODE = 9004;
    public static final String[] LOCATION_PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    public static final int RECORD_AUDIO_REQUEST_CODE = 9005;
    public static final String[] RECORD_AUDIO_PERMISSIONS = {Manifest.permission.RECORD_AUDIO};

    public static final int READ_PHONE_STATE_REQUEST_CODE = 9006;
    public static final String[] PHONE_STATE_PERMISSION = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS};
    public static final String[] PHONE_STATE_PERMISSION4O = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.RECEIVE_SMS};

}
