package com.itech.other;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

public class NotificationClickReceiver extends BroadcastReceiver {
    public static final String ACTION = "com.download.click";
    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        Runtime.getInstance().log(action,"NotificationClickReceiver");
        if (action.equals(ACTION)) {
            try {
                String url = intent.getStringExtra("TAG");
                if (!TextUtils.isEmpty(url)) {

                    if(DownloadImpl.getInstance().isPaused(url)){
                        DownloadImpl.getInstance().resume(url);
                        //Toast.makeText(context,R.string.download_resume_tips,Toast.LENGTH_SHORT).show();
                    }else{
                        DownloadImpl.getInstance().pause(url);
                        //Toast.makeText(context,R.string.download_paused_tips,Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Runtime.getInstance().logError(action, " error url empty");
                }
            } catch (Throwable ignore) {
                if (Runtime.getInstance().isDebug()) {
                    ignore.printStackTrace();
                }
            }

        }
    }
}
