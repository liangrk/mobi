package com.winghong.aarshell;

import android.os.Bundle;
import android.view.View;

import com.itech.component.MobiInters;
import com.itech.export.IntersListener;
import com.itech.export.MobiErrorCode;

public class InterstitialActivity extends BaseActivity {

    private MobiInters inters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);
    }

    @Override
    protected String setUnitId() {
        return "A3F1EC9EE2AF462C8AA2A74AD883CCE3";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (inters != null) {
            inters.destroy();
        }
    }

    @Override
    protected void logic() {
        System.out.println("mobi init success in:"+this.getClass().getSimpleName());
        inters = new MobiInters(this, AdUnitId.INTERSTITIAL_ID);
        inters.setIntersListener(new IntersListener() {
            @Override
            public void onIntersLoaded() {
                toLog("interstitial onIntersLoaded function exec");
                inters.show();
            }

            @Override
            public void onIntersFailed(MobiErrorCode errorCode) {
                toLog("interstitial onIntersFailed function exec"+errorCode.getIntCode()+",msg:"+errorCode.toString());
            }

            @Override
            public void onIntersShow() {
                toLog("interstitial onIntersShow function exec");
            }

            @Override
            public void onIntersClick() {
                toLog("interstitial onIntersClick function exec");
            }

            @Override
            public void onInterClose() {
                toLog("interstitial onInterClose function exec");
            }
        });
        inters.load();
    }

    public void toShow(View view) {

    }
}