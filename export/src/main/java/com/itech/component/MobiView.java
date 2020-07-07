package com.itech.component;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.itech.constants.RConstants;
import com.itech.core.PluginManager;
import com.itech.export.BanListener;
import com.itech.export.MobiErrorCode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/06/30 19:39
 *     @desc   : banner view.
 * </pre>
 */
public class MobiView extends FrameLayout {

    private Class<?> mobiViewClass;
    private Object invokeObject;
    private Context context;
    private Listener listener;

    private boolean xmlInit = false;
    private boolean engineInit = false;

    private com.itech.export.BanListener exportListener = new com.itech.export.BanListener() {
        @Override
        public void onBanLoaded(View view) {
            if (getActivity() != null && xmlInit) {
                MobiView.this.removeAllViews();
                MobiView.this.addView(view);
            }
            if (listener != null) {
                listener.onBanLoaded(view);
            }
        }

        @Override
        public void onBanFailed(View view, MobiErrorCode mobiErr) {
            if (listener != null) {
                listener.onBanFailed(view, mobiErr);
            }
        }

        @Override
        public void onBanClicked(View view) {
            if (listener != null) {
                listener.onBanClicked(view);
            }
        }

        @Override
        public void onBanExpanded(View view) {
            if (listener != null) {
                listener.onBanExpanded(view);
            }
        }

        @Override
        public void onBanCollapsed(View view) {
            if (listener != null) {
                listener.onBanCollapsed(view);
            }
        }
    };

    public MobiView(@NonNull Context context) {
        super(context);
        this.context = context;
        xmlInit = false;
        loadWithR();
    }

    public MobiView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        xmlInit = true;
    }

    public String getUnitId() {
        if (!engineInit) {
            loadWithR();
        }
        if (mobiViewClass == null) {
            return null;
        }
        try {
            Method getUnidMethod = mobiViewClass.getMethod("getAdUnitId");
            Object unitId = getUnidMethod.invoke(invokeObject);
            return (String) unitId;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setUnitId(String unitId) {
        if (!engineInit) {
            loadWithR();
        }
        if (mobiViewClass == null) {
            return;
        }
        try {
            Method setUnitIdMethod = mobiViewClass.getMethod("setAdUnitId", String.class);
            setUnitIdMethod.invoke(invokeObject, unitId);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void setASize(MobiSize mobiSize) {
        if (!engineInit) {
            loadWithR();
        }
        if (mobiViewClass == null) {
            return;
        }
        try {
            Method setASizeMethod = mobiViewClass.getMethod("setASize", int.class);
            setASizeMethod.invoke(invokeObject, mobiSize.getSize());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public int getMobiViewWidth() {
        if (!engineInit) {
            loadWithR();
        }
        if (mobiViewClass == null) {
            return 0;
        }
        try {
            Method widthMethod = mobiViewClass.getMethod("getAdWidth");
            return (int) widthMethod.invoke(invokeObject);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getMobiViewHeight() {
        if (!engineInit) {
            loadWithR();
        }
        if (mobiViewClass == null) {
            return 0;
        }
        try {
            Method widthMethod = mobiViewClass.getMethod("getAdHeight");
            return (int) widthMethod.invoke(invokeObject);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Activity getActivity() {
//        if (mobiViewClass == null){
//            return (Activity) context;
//        }
//        try {
//            Method getActivityMethod = mobiViewClass.getMethod("getActivity");
//            return (Activity) getActivityMethod.invoke(invokeObject);
//        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
        return (Activity) context;
    }

    public void setBannerListener(MobiView.Listener listener) {
        if (!engineInit) {
            loadWithR();
        }
        this.listener = listener;
        if (mobiViewClass == null) {
            return;
        }
        try {
            Method setBanListenerMethod = mobiViewClass.getMethod("setBanListener", BanListener.class);
            setBanListenerMethod.invoke(invokeObject, exportListener);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Listener getMobiViewListener() {
        return listener;
    }

    public void destroy() {
        if (!engineInit) {
            loadWithR();
        }
        if (mobiViewClass == null) {
            return;
        }
        try {
            Method destroyMethod = mobiViewClass.getMethod("destroy");
            destroyMethod.invoke(invokeObject);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        if (!engineInit) {
            loadWithR();
        }
        if (mobiViewClass == null) {
            System.out.println("处理mobiView class 无法加载问题");
            return;
        }
        try {
            Method loadAMethod = mobiViewClass.getMethod("loadA");
            loadAMethod.invoke(invokeObject);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void loadWithR() {
        ClassLoader classLoader = PluginManager.getInstance().getClassLoader();
        try {
            mobiViewClass = classLoader.loadClass(RConstants.CLA_MOBIVIEW);
            invokeObject = mobiViewClass.getConstructor(Context.class)
                    .newInstance(context);
            mobiViewClass.getMethod("setHostContext",Context.class)
                    .invoke(invokeObject,PluginManager.getInstance().getHostContext());
            engineInit = true;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public interface Listener {
        /**
         * banner load success
         */
        void onBanLoaded(View view);

        /**
         * banner load failed
         *
         * @param view    banner view
         * @param mobiErr contain err_code & err_msg
         */
        void onBanFailed(View view, MobiErrorCode mobiErr);

        void onBanClicked(View view);

        void onBanExpanded(View view);

        void onBanCollapsed(View view);
    }
}
