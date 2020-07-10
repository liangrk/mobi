package com.itech.logic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.blankj.utilcode.util.ToastUtils;
import com.itech.download.Conn;

import java.util.Objects;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/09 15:41
 *     @desc   :
 * </pre>
 */
public class IReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ToastUtils.showShort("hererrererererer");
        System.out.println("receiver exec...intent:" + intent);
        if (intent == null) return;
        String action = intent.getAction();
        String packageName = Objects.requireNonNull(intent.getDataString()).split(":")[1];
        System.out.println("action:" + action);
        System.out.println("packageName:" + packageName);
        Conn.getConnClazz().sendMpa(packageName, action);
    }

    public static IReceiver registerIReceiver(Context context) {
        System.out.println("register function exec... context is null?:"+context);
        if (context == null) return null;
        IReceiver receiver = new IReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addDataScheme("package");
        context.getApplicationContext().registerReceiver(receiver, filter);
        System.out.println("register package receiver.");
        return receiver;
    }

    public static void unRegisterIReceiver(Context context, IReceiver receiver) {
        if (context == null || receiver == null) return;
        try {
            context.unregisterReceiver(receiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
