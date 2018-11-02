package com.inbm.constructuremanagement.inbm;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class _dialog {
    public static void notitle_show(Context context, String msg, DialogInterface.OnClickListener positive){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg)        // 메세지 설정
                .setCancelable(false)   // 뒤로 버튼 클릭시 취소 가능 설정
                .setPositiveButton("확인", positive)
                .setNegativeButton("취소", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int whichButton){
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
    }

    public static void notitle_show_with_oneBtn(Context context, String msg, DialogInterface.OnClickListener positive){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg)        // 메세지 설정
                .setCancelable(false)   // 뒤로 버튼 클릭시 취소 가능 설정
                .setPositiveButton("확인", positive);
        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
    }

    public static void changePhoneNumber_show(Context context, String msg, DialogInterface.OnClickListener positive){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg)        // 메세지 설정
                .setCancelable(false)   // 뒤로 버튼 클릭시 취소 가능 설정
                .setPositiveButton("변경하기", positive)
                .setNegativeButton("취소", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int whichButton){
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
    }

    public static void show(Context context, String title, String msg, DialogInterface.OnClickListener positive){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);     // 여기서 this는 Activity의 this
        // 여기서 부터는 알림창의 속성 설정
        builder.setTitle(title)
                .setMessage(msg)        // 메세지 설정
                .setCancelable(false)   // 뒤로 버튼 클릭시 취소 가능 설정
                .setPositiveButton("확인", positive)
                .setNegativeButton("취소", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int whichButton){
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
    }

}
