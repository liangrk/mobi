package com.winghong.aarshell;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.itech.component.MobiNative;
import com.itech.export.MediaViewBinder;
import com.itech.export.MobiNatListener;
import com.itech.export.NatiErrorCode;
import com.itech.export.ViewBinder;

public class NativeActivity extends BaseActivity {

    private FrameLayout adContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);

        adContainer = findViewById(R.id.ad_container);
    }

    @Override
    protected String setUnitId() {
        return AdUnitId.NATIVE_ID;
    }

    @Override
    protected void logic() {
        toLog("mobi init success in:"+this.getClass().getSimpleName());
        MobiNative mobiNative = new MobiNative(this, AdUnitId.NATIVE_ID, new MobiNatListener() {
            @Override
            public void onNatiLoad(View view) {
                toLog("onNatiLoad:"+view);
                if (view!=null && !NativeActivity.this.isDestroyed() && adContainer!=null){
                    adContainer.removeAllViews();
                    adContainer.addView(view);
                }
            }

            @Override
            public void onNatiFail(NatiErrorCode errorCode) {
                toLog("onNatiFail:"+errorCode.getIntCode()+","+errorCode.toString());
            }

            @Override
            public void onImpression(View view) {
                toLog("onImpression:"+view);
            }

            @Override
            public void onClick(View view) {
                toLog("onClick:"+view);
            }
        });
        mobiNative.registerRenderer(
                new ViewBinder.Builder(R.layout.native_ad_list_item)
                        .titleId(R.id.native_title)
                        .textId(R.id.native_text)
                        .mainImageId(R.id.native_main_image)
                        .iconImageId(R.id.native_icon_image)
                        .callToActionId(R.id.native_cta)
                        //.privacyInformationIconImageId(R.id.native_privacy_information_icon_image)
                        //.sponsoredTextId(R.id.native_sponsored_text_view)
                        .build(),
                new MediaViewBinder.Builder(R.layout.video_ad_list_item_bak)
                        .titleId(R.id.native_title)
                        .textId(R.id.native_text)
                        .mediaLayoutId(R.id.native_media_layout)
                        .iconImageId(R.id.native_icon_image)
                        .callToActionId(R.id.native_cta)
                        //.privacyInformationIconImageId(R.id.native_privacy_information_icon_image)
                        //.sponsoredTextId(R.id.native_sponsored_text_view)
                        .build());

        mobiNative.makeRequest();
        ToastUtils.showShort("send request!");
    }

    public void reload(View view) {

    }
}