package com.inbm.constructuremanagement;

public interface OnPermission {
    void onPermissionGranted(int requestCode);
    void onPermissionDenied(int requestCode);
}