package com.itech.component;

import android.content.Context;
import android.support.annotation.NonNull;

import com.itech.common.MobiLog;
import com.itech.common.SdkInitializationListener;
import com.itech.constants.RConstants;
import com.itech.core.PluginManager;

import java.lang.reflect.Method;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/06/28 11:04
 *     @desc   : sdk入口.
 * </pre>
 */
public class MobiSdk {

    private static boolean isPluginLoad = false;

    private static SdkConfiguration sdkConfiguration;

    /**
     * init with application/activity
     * application context is unable register reward video event.
     * and you need use with{@link MobiSdk#initializeReVideo(Context, SdkInitializationListener)}
     *
     * @param context                   application/activity context
     * @param configuration             sdk configuration
     * @param sdkInitializationListener initial success callback listener
     */
    public static void initializeSdk(@NonNull Context context,
                                     @NonNull final SdkConfiguration configuration,
                                     @NonNull final SdkInitializationListener sdkInitializationListener) {
        //SdkInitializationListener listener = (SdkInitializationListener) sdkInitializationListener;
        sdkConfiguration = configuration;
        try {
            PluginManager manager = PluginManager.getInstance();
            if (manager.getLoadedPlugin() == null) {
                if (!isPluginLoad) {
                    manager.loadPath(context);
                    isPluginLoad = true;
                } else {
                    // uninstall. reload.
                }
            }

            // 1.copy已有的configuration 到反射对象中.
            Class builderClazz = PluginManager.getClass(RConstants.CLA_SDK_CONFIGURATION_BUILDER);
            Object builderObj = builderClazz.getConstructor(PluginManager.getParamsType(String.class))
                    .newInstance(PluginManager.getObjectArr(configuration.getUnitId()));

            Method withDelayImprFirstOpen = builderClazz.getMethod("withDelayImprFirstOpen",
                    PluginManager.getParamsType(long.class));
            withDelayImprFirstOpen.invoke(builderObj, PluginManager.getObjectArr(configuration.getDelayImprFirstOpen()));

            MobiLog.LogLevel level = configuration.getLevel();
            Method logLevel = builderClazz.getMethod("withLogLevel", PluginManager.getParamsType(int.class));
            logLevel.invoke(builderObj, PluginManager.getObjectArr(level == MobiLog.LogLevel.DEBUG ? 0 : 1));

            Method buildMethon = builderClazz.getMethod("build");
            Object config = buildMethon.invoke(builderObj);

            // init ref.
            Class<?> mobi = PluginManager.getClass(RConstants.CLA_MOBI);
            Method initializeSdk = mobi.getMethod("initializeSdk",
                    PluginManager.getParamsType(Context.class, Object.class, SdkInitializationListener.class));
            initializeSdk.invoke(null, PluginManager.getObjectArr(context, config, sdkInitializationListener));
        } catch (Exception e) {
            System.out.println("处理插件异常情况");
            // 运行时加载不成功....???
            e.printStackTrace();
        }
    }

    /**
     * reward video 需要单独初始化.
     *
     * @param context                   activity context
     * @param sdkInitializationListener initial success callback
     */
    public static void initializeReVideo(@NonNull Context context,
                                         @NonNull final SdkInitializationListener sdkInitializationListener) {
        initializeSdk(context, sdkConfiguration, sdkInitializationListener);
    }
}
