package com.winghong.aarshell;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.PermissionUtils;

/**
 * 应
 * 初始化
 * 而后调用.
 */
public class LogicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logic);
        PermissionUtils.permission(PermissionConstants.STORAGE)
                .callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void onGranted() {

                    }

                    @Override
                    public void onDenied() {
                        finish();
                    }
                }).request();
    }

    public void loadSplash(View view) {
        ActivityUtils.startActivity(SplashActivity.class);
    }

    public void loadBanner(View view) {
        ActivityUtils.startActivity(BannerActivity.class);
    }

    public void loadInterstitial(View view) {
        ActivityUtils.startActivity(InterstitialActivity.class);
    }

    public void loadNative(View view) {

    }

    public void loadNativeVideo(View view) {

    }

    public void loadRewardVideo(View view) {

    }
}