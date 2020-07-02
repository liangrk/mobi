package com.itech.component;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.itech.constants.RConstants;
import com.itech.core.PluginManager;
import com.itech.export.IntersListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/02 9:48
 *     @desc   :
 * </pre>
 */
public class MobiInters {

    private Activity activity;
    private Class<?> interClass = null;
    private Object invokeObj;

    public MobiInters(@NonNull Activity activity, @NonNull String unitId) {
        this.activity = activity;

        try {
            interClass = PluginManager.getInstance()
                    .getClassLoader()
                    .loadClass(RConstants.CLA_MOBIINTER);
            invokeObj = interClass.getConstructor(Activity.class, String.class)
                    .newInstance(activity, unitId);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void setIntersListener(@NonNull IntersListener listener) {
        if (isBreak()) return;
        try {
            Method setListenerMethod = interClass.getMethod("setIntersListener", IntersListener.class);
            setListenerMethod.invoke(invokeObj, listener);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * TODO: need support.
     * @return
     */
    @Deprecated
    public boolean isLoaded(){
        return false;
    }

    public void load() {
        if (isBreak()) return;
        try {
            Method loadMethod = interClass.getMethod("load");
            loadMethod.invoke(invokeObj);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void show() {
        if (isBreak()) return;
        try {
            Method showMethod = interClass.getMethod("show");
            showMethod.invoke(invokeObj);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        if (isBreak()) return;
        try {
            Method destroyMethod = interClass.getMethod("destroy");
            destroyMethod.invoke(invokeObj);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Activity getActivity() {
        return this.activity;
    }

    private boolean isBreak() {
        if (invokeObj == null || interClass == null) {
            System.out.println("invoke object is null:" + this.getClass().getSimpleName());
            return true;
        }
        return false;
    }
}
