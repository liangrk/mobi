package com.winghong.aarshell;

import android.support.v7.app.AppCompatActivity;

import com.itech.common.MobiLog;
import com.itech.common.Mobip;
import com.itech.common.SdkConfigurationp;
import com.itech.common.SdkInitializationListener;

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

        SdkConfigurationp configurationp = new SdkConfigurationp.Builder(setUnitId())
                .withLogLevel(MobiLog.LogLevel.DEBUG)
                .withDelayImprFirstOpen(5000)
                .build();

        Mobip.initializeSdk(this, configurationp, new SdkInitializationListener() {
            @Override
            public void onInitializationFinished() {
                logic();
            }
        });
    }

    protected abstract String setUnitId();

    protected abstract void logic();

    protected void toLog(String msg) {
        PrintLog.toLog(msg);
    }
}
