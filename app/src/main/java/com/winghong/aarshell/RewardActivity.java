package com.winghong.aarshell;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.itech.component.MobiReVideos;
import com.itech.export.MobiErrorCode;
import com.itech.export.MobiReVideoListener;
import com.itech.export.MobiReward;

import java.util.Set;

public class RewardActivity extends BaseActivity {

    private MobiReVideos videos;
    private TextView infoView;

    private String stubText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        infoView = findViewById(R.id.tv_info);
        stubText =infoView.getText().toString();
        setInfo("0");
    }

    private void setInfo(String replace){
        if (infoView == null){
            return;
        }
        infoView.setText(String.format(stubText,replace));
    }

    public void showVideo(View view) {
        if (videos == null){
            ToastUtils.showShort("mobi 还未初始化完成");
            return;
        }
        System.out.println("hasVideo????"+videos.isLoaded());
        if (videos.isLoaded()){
            videos.showVideo();
        }
        //setInfo(videos.getAvailableRewards().size()+"");
    }

    public void reloadVideo(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected String setUnitId() {
        return AdUnitId.REWARDED_VIDEO;
    }

    @Override
    protected void logic() {
        videos = new MobiReVideos(AdUnitId.REWARDED_VIDEO);

        videos.setReVideoListener(new MobiReVideoListener() {
            @Override
            public void onReVideoLoadSuccess(@NonNull String adUnitId) {
                toLog("onReVideoLoadSuccess");
                ToastUtils.showShort("广告已加载");
                setInfo(videos.getAvailableRewards().size()+"");
            }

            @Override
            public void onReVideoLoadFailure(@NonNull String adUnitId, @NonNull MobiErrorCode errorCode) {
                toLog("onReVideoLoadFailure");
                ToastUtils.showShort("广告加载失败"+errorCode.toString()+","+errorCode.getIntCode());
                setInfo(videos.getAvailableRewards().size()+"");
            }

            @Override
            public void onReVideoStarted(@NonNull String adUnitId) {
                toLog("onReVideoStarted");
            }

            @Override
            public void onReVideoPlaybackError(@NonNull String adUnitId, @NonNull MobiErrorCode errorCode) {
                toLog("onReVideoPlaybackError");
            }

            @Override
            public void onReVideoClicked(@NonNull String adUnitId) {
                toLog("onReVideoClicked");
            }

            @Override
            public void onReVideoDownStart(@NonNull String adUnitId) {
                toLog("onReVideoDownStart");
            }

            @Override
            public void onReVideoClosed(@NonNull String adUnitId) {
                toLog("onReVideoClosed");
            }

            @Override
            public void onReVideoCompleted(@NonNull Set<String> adUnitIds, @NonNull MobiReward reward) {
                toLog("onReVideoCompleted");
                videos.loadVideo(RewardActivity.this);
                toLog("看完了 再整一个");
                setInfo(videos.getAvailableRewards().size()+"");
            }
        });
        videos.loadVideo(RewardActivity.this);
    }
}