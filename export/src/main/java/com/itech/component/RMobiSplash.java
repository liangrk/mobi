package com.itech.component;

import android.content.Context;
import android.support.annotation.NonNull;

import com.itech.constants.RConstants;
import com.itech.core.PluginManager;
import com.itech.export.MobiSplashListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/06/28 18:01
 *     @desc   :
 * </pre>
 */
public class RMobiSplash {

    private ClassLoader classLoader;
    private Object splash;
    private Class<?> splashClass;

    public RMobiSplash(@NonNull Context context) {
        try {
            classLoader = PluginManager.getInstance().getClassLoader();
            splashClass = classLoader.loadClass(RConstants.CLA_SPLASH);
            splash = splashClass.getConstructor(Context.class)
                    .newInstance(context);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

//    private ViewBinder getViewBinder(){
//        return new ViewBinder.Builder(R.layout.splash_ad)
//        .titleId(R.id.splash_native_title)
//        .textId(R.id.splash_native_text)
//        .mainImageId(R.id.splash_native_main_image)
//        .iconImageId(R.id.splash_native_icon_image)
//        .callToActionId(R.id.splash_native_cta)
////                .privacyInformationIconImageId(R.id.native_privacy_information_icon_image)
////                .sponsoredTextId(R.id.native_sponsored_text_view)
////                .addExtra(SplashViewBinder.APPINFO, R.id.appinfo)
//        .build();
//    }

    public void loadSplash(@NonNull String unitId,
                           @NonNull MobiSplashListener listener,
                           @NonNull int timeout) {
        try {
            splashClass = classLoader.loadClass(RConstants.CLA_SPLASH);
//            splashClass.getMethod("setViewBinder",ViewBinder.class)
//                    .invoke(splash,getViewBinder());
            Method loadSplashMet = splashClass.getMethod("loadSplash",
                    String.class, MobiSplashListener.class, int.class);
            loadSplashMet.invoke(splash,unitId,listener,timeout);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
