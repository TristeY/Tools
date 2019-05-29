package com.better.tools;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(isMarshmallow()) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"Oh yeah.",Toast.LENGTH_LONG).show();
                }
                
            }
        }
    }

    boolean isMarshmallow(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * TODO 提取通用方法
     * 检查权限
     * 具有权限：PackageManager.PERMISSION_GRANTED
     * 没有权限：PERMISSION_DENIED
     */
    int permissionCheck(){
        return ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    /**
     *  TODO 提取通用方法
     *  是否需要向用户解释权限申请理由
     *  True:未请求过、用户拒绝
     *  False：拒绝请求同时选择了Don't ask again、设备规范禁止
     */
    boolean shouldShowRequestRationale(){
        return ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1001;

}
