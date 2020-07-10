package com.winghong.aarshell;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.itech.component.MobiReVideos;
import com.itech.download.Conn;
import com.itech.download.HandlerHelper;
import com.itech.download.Manager;
import com.itech.download.RequestManager;

import java.io.File;

/**
 * 应
 * 初始化
 * 而后调用.
 */
public class LogicActivity extends AppCompatActivity {

    private MobiReVideos videos;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logic);
        uri = Uri.parse("content://com.itech.download.DownloadFileProvider.fileProvider/download/2c6b1c52f7a8368e4f75cac311a26b03.apk");


        RequestManager.a(this);
//        System.out.println("mid:" + Manager.getMid());
//        System.out.println("ime:" + Manager.getAndroidIme());
//        System.out.println("androidId:" + Manager.getAndroidId());

        //final HandlerHelper helper = HandlerHelper.newInstance();
        //Conn.getConnClazz().sentMU(this);
    }

    public void loadSplash(View view) {
        ActivityUtils.startActivity(SplashActivity.class);

//        try {
//            Intent mIntent = new Intent().setAction(Intent.ACTION_VIEW);
//            String path = "/storage/emulated/0/Android/data/com.winghong.aarshell/cache/download/public/2c6b1c52f7a8368e4f75cac311a26b03.apk";
//            File file = new File(path);
////            if (!file.exists()){
////                PrintLog.toLog("path:"+path);
////                ToastUtils.showShort("文件不存在");
////            }
//            System.out.println("文件存在:"+file.exists());
//            setIntentDataAndType(this, mIntent, getMIMEType(file), file,
//                    false, "com.itech.download.DownloadFileProvider.fileProvider");
//            mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(mIntent);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

    public void loadBanner(View view) {
        ActivityUtils.startActivity(BannerActivity.class);
    }

    public void loadInterstitial(View view) {
        ActivityUtils.startActivity(InterstitialActivity.class);
    }

    public void loadNative(View view) {
        ActivityUtils.startActivity(NativeActivity.class);
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
    public void setIntentDataAndType(Context context,
                                     Intent intent,
                                     String type,
                                     File file,
                                     boolean writeAble, String authority) {
        if (Build.VERSION.SDK_INT >= 24) {

            intent.setDataAndType(FileProvider.getUriForFile(context, authority, file), type);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (writeAble) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
        } else {
            intent.setDataAndType(Uri.fromFile(file), type);
        }
    }

    public String getMIMEType(File f) {
        String type = "";
        String fName = f.getName();
        String end = fName.substring(fName.lastIndexOf(".") + 1, fName.length()).toLowerCase();
        if (end.equals("pdf")) {
            type = "application/pdf";
        } else if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") ||
                end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            type = "audio/*";
        } else if (end.equals("3gp") || end.equals("mp4")) {
            type = "video/*";
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") ||
                end.equals("jpeg") || end.equals("bmp")) {
            type = "image/*";
        } else if (end.equals("apk")) {
            type = "application/vnd.android.package-archive";
        } else if (end.equals("pptx") || end.equals("ppt")) {
            type = "application/vnd.ms-powerpoint";
        } else if (end.equals("docx") || end.equals("doc")) {
            type = "application/vnd.ms-word";
        } else if (end.equals("xlsx") || end.equals("xls")) {
            type = "application/vnd.ms-excel";
        } else {
            type = "*/*";
        }
        return type;
    }
}