package com.winghong.aarshell;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.itech.common.SdkInitializationListener;
import com.itech.component.MobiReVideos;
import com.itech.component.MobiSdk;
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
        infoView = findViewById(R.id.tv_host_info);
        if (infoView == null){
            System.out.println("android studio 疯了!!!!!!!!");
            infoView = new TextView(this);
        }
        stubText =infoView.getText().toString();
        setInfo("0");

        MobiSdk.initializeReVideo(this, new SdkInitializationListener() {
            @Override
            public void onInitializationFinished() {
                loadVideo();
            }
        });
    }

    private void loadVideo() {
        videos = new MobiReVideos();

        videos.setReVideoListener(new MobiReVideoListener() {
            @Override
            public void onReVideoLoadSuccess(@NonNull String adUnitId) {
                toLog("onReVideoLoadSuccess");
                ToastUtils.showShort("广告已加载");
                setInfo(videos.getAvailableRewards(AdUnitId.REWARDED_VIDEO).size()+"\n86000980986C4C36BE0B2E5BF931CF28可用的:"+
                        videos.getAvailableRewards(AdUnitId.REWARDED_VIDEO_TWO).size());
            }

            @Override
            public void onReVideoLoadFailure(@NonNull String adUnitId, @NonNull MobiErrorCode errorCode) {
                toLog("onReVideoLoadFailure");
                ToastUtils.showShort("广告加载失败"+errorCode.toString()+","+errorCode.getIntCode());
                setInfo(videos.getAvailableRewards(AdUnitId.REWARDED_VIDEO).size()+"\n86000980986C4C36BE0B2E5BF931CF28可用的:"+
                        videos.getAvailableRewards(AdUnitId.REWARDED_VIDEO_TWO).size());
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
                videos.loadVideo(RewardActivity.this,AdUnitId.REWARDED_VIDEO_TWO);
            }

            @Override
            public void onReVideoCompleted(@NonNull Set<String> adUnitIds, @NonNull MobiReward reward) {
                toLog("onReVideoCompleted");
//                videos.loadVideo(RewardActivity.this,AdUnitId.REWARDED_VIDEO);
//                toLog("看完了 再整一个");
//                setInfo(videos.getAvailableRewards(AdUnitId.REWARDED_VIDEO).size()+"" +
//                        "\n 86000980986C4C36BE0B2E5BF931CF28可用的:"+videos.getAvailableRewards(AdUnitId.REWARDED_VIDEO_TWO).size());
            }
        });
        videos.loadVideo(RewardActivity.this,AdUnitId.REWARDED_VIDEO);
        videos.loadVideo(RewardActivity.this,AdUnitId.REWARDED_VIDEO_TWO);
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
        System.out.println("hasVideo????"+videos.isLoaded(AdUnitId.REWARDED_VIDEO));
        if (videos.isLoaded(AdUnitId.REWARDED_VIDEO)){
            videos.showVideo(AdUnitId.REWARDED_VIDEO);
        }
        //setInfo(videos.getAvailableRewards().size()+"");
    }

    public void reloadVideo(View view) {
        if (videos == null){
            return;
        }
        if (videos.isLoaded(AdUnitId.REWARDED_VIDEO_TWO)){
            videos.showVideo(AdUnitId.REWARDED_VIDEO_TWO);
        }
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

    }
}