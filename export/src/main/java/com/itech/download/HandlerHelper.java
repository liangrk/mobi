package com.itech.download;

import android.os.Handler;
import android.os.Looper;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/09 17:59
 *     @desc   :
 * </pre>
 */
public class HandlerHelper {

    private Handler handler;

    public void runOnMainThread(Runnable task){
        if (task == null) return;
        handler.postDelayed(task,0);
    }

    private HandlerHelper(){
        handler = new Handler(Looper.getMainLooper());
    }

    public void startTask(Runnable runnable){
        if (runnable == null) return;
        new Thread(runnable).start();
    }

    private static HandlerHelper helper;

    public static HandlerHelper newInstance(){
        if (helper == null){
            helper = new HandlerHelper();
        }
        return helper;
    }
}
