package com.winghong.aarshell;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.itech.common.Mobip;
import com.itech.common.SdkConfigurationp;
import com.itech.common.SdkInitializationListener;
import com.itech.export.MobiSplashListener;
import com.itech.export.SplashErrorCode;
import com.itech.splash.RMobiSplash;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SdkConfigurationp configurationp = new SdkConfigurationp.Builder("93FA65EB53E4439EA015A722447BE460")
                .withDelayImprFirstOpen(2000)
                .build();
        Mobip.initializeSdk(this, configurationp, new SdkInitializationListener() {

            @Override
            public void onInitializationFinished() {
                logic();
            }
        });
    }

    private void logic() {
        RMobiSplash splash = new RMobiSplash(this);
        splash.loadSplash("A3F1EC9EE2AF462C8AA2A74AD883CCE3", new MobiSplashListener() {
            @Override
            public void onError(SplashErrorCode splashErrorCode) {
                System.out.println("onErr:" + splashErrorCode.getMessage());
            }

            @Override
            public void onSplashLoad(View view) {
                System.out.println("onSplashLoad" + view);
            }

            @Override
            public void onTimeout() {
                System.out.println("timeout");
            }

            @Override
            public void onImpression(View view) {
                System.out.println("onImpression" + view);
            }

            @Override
            public void onClick(View view) {
                System.out.println("onClick" + view);
            }

            @Override
            public void onSkip() {
                System.out.println("onSkip");
            }

            @Override
            public void onTimeOver() {
                System.out.println("onTimeOver");
            }
        }, 1000);
    }
}