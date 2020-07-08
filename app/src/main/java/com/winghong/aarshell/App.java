package com.winghong.aarshell;

import android.app.Application;

import com.itech.common.MobiLog;
import com.itech.common.SdkInitializationListener;
import com.itech.component.MobiSdk;
import com.itech.component.SdkConfiguration;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/08 16:52
 *     @desc   :
 * </pre>
 */
public class App extends Application {

    public static boolean initial = false;

    @Override
    public void onCreate() {
        super.onCreate();

        SdkConfiguration configuration = new SdkConfiguration.Builder("93FA65EB53E4439EA015A722447BE460")
                .withLogLevel(MobiLog.LogLevel.DEBUG)
                .withDelayImprFirstOpen(5000)
                .build();

        MobiSdk.initializeSdk(this, configuration, new SdkInitializationListener() {
            @Override
            public void onInitializationFinished() {
                //logic();
                initial = true;
            }
        });
    }
}
