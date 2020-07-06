package com.itech.component;

import android.content.Context;
import android.support.annotation.NonNull;

import com.itech.constants.RConstants;
import com.itech.core.PluginManager;
import com.itech.export.MediaViewBinder;
import com.itech.export.MobiNatListener;
import com.itech.export.RequestParameters;
import com.itech.export.ViewBinder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EnumSet;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/03 17:40
 *     @desc   : native.
 * </pre>
 */
public class MobiNative {

    private Class<?> natiClass;
    private Object natInstance;

    public MobiNative(@NonNull Context context, @NonNull String unitId, @NonNull MobiNatListener listener) {
        initNati(context, unitId, listener);
    }

    private void setStaticRenderer(ViewBinder binder) {
        try {
            Class<?> aClass = PluginManager.getInstance()
                    .getClassLoader()
                    .loadClass(RConstants.CLA_MOBI_STATIC_RENDERER);
            Object staticRenderer = aClass.getConstructor(ViewBinder.class)
                    .newInstance(binder);
            natiClass.getMethod("registerAdRenderer", Object.class)
                    .invoke(natInstance, staticRenderer);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void setVideoRenderer(MediaViewBinder binder) {
        try {
            Class<?> aClass = PluginManager.getInstance()
                    .getClassLoader()
                    .loadClass(RConstants.CLA_MOBI_VIDEO_RENDERER);
            Object videoRenderer = aClass.getConstructor(MediaViewBinder.class).newInstance(binder);
            natiClass.getMethod("registerAdRenderer", Object.class)
                    .invoke(natInstance, videoRenderer);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册对应的 renderer
     *
     * @param staticBinder 静态原生 为空则不注册
     * @param videoBinder  视频原生 为空则不注册
     */
    public void registerRenderer(ViewBinder staticBinder, MediaViewBinder videoBinder) {
        if (staticBinder != null) {
            setStaticRenderer(staticBinder);
        }

        if (videoBinder != null) {
            setVideoRenderer(videoBinder);
        }
    }

    // 最后发起请求
    public void makeRequest(RequestParameters parameters) {
        try {
            natiClass.getMethod("makeRequest", RequestParameters.class)
                    .invoke(natInstance, parameters);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void makeRequest() {
        try {
            natiClass.getMethod("makeRequest")
                    .invoke(natInstance);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public RequestParameters getDefaultParams() {
        EnumSet<RequestParameters.NatiAsset> desiredAssets = EnumSet.of(
                RequestParameters.NatiAsset.TITLE,
                RequestParameters.NatiAsset.TEXT,
                RequestParameters.NatiAsset.ICON_IMAGE,
                RequestParameters.NatiAsset.MAIN_IMAGE,
                RequestParameters.NatiAsset.VIDEO,//需要注册支持视频的 AdRenderer
                RequestParameters.NatiAsset.CALL_TO_ACTION_TEXT,
                RequestParameters.NatiAsset.SPONSORED);
        return new RequestParameters.Builder()
                .desiredAssets(desiredAssets)
                .build();
    }

    private void initNati(@NonNull Context context, @NonNull String unitId, @NonNull MobiNatListener listener) {
        try {
            natiClass = PluginManager.getInstance()
                    .getClassLoader()
                    .loadClass(RConstants.CLA_MOBI_NATI);
            natInstance = natiClass.getConstructor(Context.class, String.class)
                    .newInstance(context, unitId);
            natiClass.getMethod("setNatListener",MobiNatListener.class)
                    .invoke(natInstance,listener);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            Method destroyMethod = natiClass.getMethod("destroy");
            destroyMethod.invoke(natInstance);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
