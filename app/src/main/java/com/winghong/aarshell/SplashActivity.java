package com.winghong.aarshell;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ColorUtils;
import com.itech.core.PluginManager;
import com.itech.export.MobiSplashListener;
import com.itech.component.RMobiSplash;

public class SplashActivity extends BaseActivity {

    private ConstraintLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = findViewById(R.id.container);
        // testCodeCreateView(this);

//        SdkConfigurationp configurationp = new SdkConfigurationp.Builder("93FA65EB53E4439EA015A722447BE460")
//                .withLogLevel(MobiLog.LogLevel.DEBUG)
//                .withDelayImprFirstOpen(2000)
//                .build();
//
//        Mobip.initializeSdk(this, configurationp, new SdkInitializationListener() {
//            @Override
//            public void onInitializationFinished() {
//                logic();
//            }
//        });
    }

    private void testCodeCreateView(Context context) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout splashLayout = new RelativeLayout(context);
        splashLayout.setLayoutParams(layoutParams);
        splashLayout.setBackgroundColor(ColorUtils.getColor(R.color.colorPrimaryDark));

        // TODO: 后续检查该view是否存在控制显示
        ImageView splashView = new ImageView(context);
        splashView.setBackgroundColor(ColorUtils.getColor(R.color.colorAccent));
        splashLayout.setVisibility(View.VISIBLE);
        splashLayout.addView(splashView,RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(layoutParams);
        params.width = 50;
        params.height = 50;
        params.setMargins(25,25,25,25);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        ProgressBar progressBar = new ProgressBar(context);
        progressBar.setBackgroundColor(ColorUtils.getColor(R.color.colorPrimary));
        splashLayout.addView(progressBar,params);

        /*layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = 100;
        RelativeLayout bottomLayout = new RelativeLayout(context);
        bottomLayout.setLayoutParams(layoutParams);
        bottomLayout.setGravity(RelativeLayout.ALIGN_PARENT_BOTTOM);
        //bottomLayout.setPadding(25,25,25,25);
        bottomLayout.setLayoutParams(layoutParams);
        bottomLayout.setBackgroundColor(ColorUtils.getColor(R.color.colorPrimary));
        splashLayout.addView(bottomLayout,layoutParams);
        */

        ConstraintLayout layout = findViewById(R.id.container);
        layout.removeAllViews();
        layout.addView(splashLayout);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PluginManager.getInstance().getClassLoader().clearAssertionStatus();
    }

    @Override
    protected String setUnitId() {
        return "A3F1EC9EE2AF462C8AA2A74AD883CCE3";
    }

    @Override
    protected void logic() {
        toLog("mobi init success in:"+this.getClass().getSimpleName());
        RMobiSplash splash = new RMobiSplash(this);
        splash.loadSplash("A3F1EC9EE2AF462C8AA2A74AD883CCE3", new MobiSplashListener() {
            @Override
            public void onError(int splashErrorCode,String msg) {
                PrintLog.toLog("onErr:" + msg+",code:"+splashErrorCode);
            }

            @Override
            public void onSplashLoad(View view) {
                toLog("onSplashLoad" + view);
                if (view!=null){
                    rootLayout.removeAllViews();
                    rootLayout.addView(view);
                }
            }

            @Override
            public void onTimeout() {
                toLog("timeout");
            }

            @Override
            public void onImpression(View view) {
                toLog("onImpression" + view);
            }

            @Override
            public void onClick(View view) {
                toLog("onClick" + view);
            }

            @Override
            public void onSkip() {
                toLog("onSkip");
                if (!SplashActivity.this.isDestroyed()){
                    SplashActivity.this.finish();
                }
            }

            @Override
            public void onTimeOver() {
                toLog("onTimeOver");
            }
        }, 3000);
    }
}