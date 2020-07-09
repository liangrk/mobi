package com.itech.logic;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

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
        if (intent==null) return;
        String action = intent.getAction();
        String packageName = Objects.requireNonNull(intent.getDataString()).split(":")[1];
        System.out.println("action:"+action);
        System.out.println("packageName:"+packageName);
        Conn.getConnClazz().sendMpa(packageName,action);
    }
}
