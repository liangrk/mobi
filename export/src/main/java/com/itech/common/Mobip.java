package com.itech.common;

import android.content.Context;
import android.support.annotation.NonNull;

import com.itech.constants.RConstants;
import com.itech.core.PluginManager;

import java.lang.reflect.Method;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/06/28 11:04
 *     @desc   :
 * </pre>
 */
public class Mobip {

    public static void initializeSdk(@NonNull Context context,
                                     @NonNull final SdkConfigurationp configuration,
                                     @NonNull final SdkInitializationListener sdkInitializationListener) {
        try {
            PluginManager manager = PluginManager.getInstance();
            manager.loadPath(context);
            ClassLoader classLoader = manager.getClassLoader();
            // 1.copy已有的configuration 到反射对象中.
            Class builderClazz = classLoader.loadClass(RConstants.CLA_SDK_CONFIGURATION_BUILDER);
            Object builderObj = builderClazz.getConstructor(String.class)
                    .newInstance(configuration.getUnitId());

            Method withDelayImprFirstOpen = builderClazz.getMethod("withDelayImprFirstOpen", long.class);
            withDelayImprFirstOpen.invoke(builderObj, configuration.getDelayImprFirstOpen());

            MobiLog.LogLevel level = configuration.getLevel();
            Method logLevel = builderClazz.getMethod("withLogLevel", int.class);
            logLevel.invoke(builderObj, level == MobiLog.LogLevel.DEBUG ? 0 : 1);

            Method buildMethon = builderClazz.getMethod("build");
            Object config = buildMethon.invoke(builderObj);

            // init ref.
            Class<?> mobi = classLoader.loadClass(RConstants.CLA_MOBI);
            Method initializeSdk = mobi.getMethod("initializeSdk", Context.class, Object.class, SdkInitializationListener.class);
            initializeSdk.invoke(null, context, config, sdkInitializationListener);
        } catch (Exception e) {
            System.out.println("处理插件异常情况");
            // 运行时加载不成功....???
            e.printStackTrace();
        }
    }
}
