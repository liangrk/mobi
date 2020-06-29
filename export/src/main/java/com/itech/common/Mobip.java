package com.itech.common;

import android.content.Context;
import android.support.annotation.NonNull;

import com.itech.constants.Constants;
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
            Class builderClazz = classLoader.loadClass(Constants.CLA_SDK_CONFIGURATION_BUILDER);
            Object builderObj = builderClazz.getConstructor(String.class)
                    .newInstance(configuration.getUnitId());
            // build
            Method withDelayImprFirstOpen = builderClazz.getMethod("withDelayImprFirstOpen", long.class);
            withDelayImprFirstOpen.invoke(builderObj, configuration.getDelayImprFirstOpen());
            Method buildMethon = builderClazz.getMethod("build");
            Object config = buildMethon.invoke(builderObj, null);

            // init ref.
            Class<?> mobi = classLoader.loadClass(Constants.CLA_MOBI);
            Method initializeSdk = mobi.getMethod("initializeSdk", Context.class, Object.class, SdkInitializationListener.class);
            initializeSdk.invoke(null, context, config, sdkInitializationListener);
        } catch (Exception e) {
            System.out.println("处理插件异常情况");
            // 运行时加载不成功....???
            e.printStackTrace();
        }
    }
}
