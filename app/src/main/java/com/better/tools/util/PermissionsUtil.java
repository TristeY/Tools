package com.better.tools.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by whyme
 * Date 2019/5/30
 */
public class PermissionsUtil {
    final String TAG = "Permission";

    private List<String> permissionsDinied = new ArrayList();

    /**
     * 多个权限申请
     * @param activity 当前activity
     * @param permissionsRequired 所需请求的权限，不定参数
     */
    void permissionRequest(Activity activity,String...permissionsRequired){
        for(String permission:permissionsRequired){
            if (permissionCheck(activity, permission) == PackageManager.PERMISSION_DENIED) {
                permissionsDinied.add(permission);
            } else {
                Log.i(TAG, "permissionRequest: " + permission + "has allready exist.");
            }
        }
        for (String permission : permissionsDinied){
            permissionRequest(activity,permission);
        }

    }

    /**
     * 单个权限请求
     * @param activity 当前activity
     * @param permission 所需请求的权限
     */
    void permissionRequest(Activity activity,String permission){
        ActivityCompat.requestPermissions(activity,new String[]{permission},getRequestCode());
    }

    //TODO 增加针对requestCode的筛选
    private int getRequestCode() {
        return 1001;
    }

    boolean isMarshmallow(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * 检查权限
     * 具有权限：PackageManager.PERMISSION_GRANTED
     * 没有权限：PERMISSION_DENIED
     */
    int permissionCheck(Activity activity,String permission){

        return ContextCompat.checkSelfPermission(activity, permission);
    }

    /**
     *  TODO 提取通用方法
     *  是否需要向用户解释权限申请理由
     *  True:未请求过、用户拒绝
     *  False：拒绝请求同时选择了Don't ask again、设备规范禁止
     */
    boolean shouldShowRequestRationale(Activity activity,String permission){
        return ActivityCompat.shouldShowRequestPermissionRationale(activity,permission);
    }


}
