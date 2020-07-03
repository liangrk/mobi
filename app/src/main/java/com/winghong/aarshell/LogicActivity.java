package com.winghong.aarshell;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.itech.component.MobiReVideos;

/**
 * 应
 * 初始化
 * 而后调用.
 */
public class LogicActivity extends AppCompatActivity {

    private MobiReVideos videos;

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
        ActivityUtils.startActivity(RewardActivity.class);
    }

//    @Override
//    protected String setUnitId() {
//        return "A3F1EC9EE2AF462C8AA2A74AD883CCE3";
//    }
//
//    @Override
//    protected void logic() {
//        ToastUtils.showLong("mobi init success");
//        videos = new MobiReVideos(AdUnitId.REWARDED_VIDEO);
//        videos.setReVideoListener(new MobiReVideoListener() {
//            @Override
//            public void onReVideoLoadSuccess(@NonNull String adUnitId) {
//                toLog("onReVideoLoadSuccess");
//            }
//
//            @Override
//            public void onReVideoLoadFailure(@NonNull String adUnitId, @NonNull MobiErrorCode errorCode) {
//                toLog("onReVideoLoadFailure");
//            }
//
//            @Override
//            public void onReVideoStarted(@NonNull String adUnitId) {
//                toLog("onReVideoStarted");
//            }
//
//            @Override
//            public void onReVideoPlaybackError(@NonNull String adUnitId, @NonNull MobiErrorCode errorCode) {
//                toLog("onReVideoPlaybackError");
//            }
//
//            @Override
//            public void onReVideoClicked(@NonNull String adUnitId) {
//                toLog("onReVideoClicked");
//            }
//
//            @Override
//            public void onReVideoDownStart(@NonNull String adUnitId) {
//                toLog("onReVideoDownStart");
//            }
//
//            @Override
//            public void onReVideoClosed(@NonNull String adUnitId) {
//                toLog("onReVideoClosed");
//            }
//
//            @Override
//            public void onReVideoCompleted(@NonNull Set<String> adUnitIds, @NonNull MobiReward reward) {
//                toLog("onReVideoCompleted");
//            }
//        });
//        videos.loadVideo();
//    }
}