package com.winghong.aarshell;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.itech.component.MobiSize;
import com.itech.component.MobiView;
import com.itech.export.BanListener;
import com.itech.export.MobiErrorCode;

public class BannerActivity extends BaseActivity {

    private MobiView xmlBanner;
    private MobiView codeBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        //codeBanner.setUnitId(AdUnitId.BANNER_ID);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (xmlBanner != null) {
            xmlBanner.destroy();
        }
        if (codeBanner != null){
            codeBanner.destroy();
        }
    }

    @Override
    protected String setUnitId() {
        return "93FA65EB53E4439EA015A722447BE460";
    }

    @Override
    protected void logic() {
        System.out.println("mobi init success in:" + this.getClass().getSimpleName());
//        codeBanner = new MobiView(this);
        final FrameLayout adContainer = findViewById(R.id.fl_container);
        xmlBanner = new MobiView(this);
//        xmlBanner = findViewById(R.id.mobiview);
        //codeBanner = new MobiView(this);

        xmlBanner.setUnitId(AdUnitId.BANNER_ID);
        xmlBanner.setASize(MobiSize.HEIGHT_90);
        xmlBanner.setBannerListener(new MobiView.Listener() {
            @Override
            public void onBanLoaded(View view) {
                toLog("onBanLoaded:"+view);
                if (view!=null){
                    adContainer.removeAllViews();
                    adContainer.addView(view);
//                    xmlBanner.addView(view);
                }
            }

            @Override
            public void onBanFailed(View view, MobiErrorCode mobiErr) {
                toLog("wtf????"+view);
                toLog("onBanLoadFailed:"+mobiErr.getIntCode()+","+mobiErr.toString());
            }

            @Override
            public void onBanClicked(View view) {
                toLog("onBanClicked"+view);
            }

            @Override
            public void onBanExpanded(View view) {
                toLog("onBanExpanded"+view);
            }

            @Override
            public void onBanCollapsed(View view) {
                toLog("onBanCollapsed"+view);
            }
        });
        xmlBanner.load();
    }
}