package com.winghong.aarshell;

import android.support.v7.app.AppCompatActivity;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/06/30 10:35
 *     @desc   :
 * </pre>
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        logic();
//        SdkConfiguration configuration = new SdkConfiguration.Builder(setUnitId())
//                .withLogLevel(MobiLog.LogLevel.DEBUG)
//                .withDelayImprFirstOpen(5000)
//                .build();
//
//        MobiSdk.initializeSdk(this, configuration, new SdkInitializationListener() {
//            @Override
//            public void onInitializationFinished() {
//                logic();
//            }
//        });
    }

    protected abstract String setUnitId();

    protected abstract void logic();

    protected void toLog(String msg) {
        PrintLog.toLog(msg);
    }
}
