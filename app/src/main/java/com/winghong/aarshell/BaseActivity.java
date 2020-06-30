package com.winghong.aarshell;

import android.support.v7.app.AppCompatActivity;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/06/30 10:35
 *     @desc   :
 * </pre>
 */
public  abstract class BaseActivity extends AppCompatActivity {

    protected void toLog(String msg){
        PrintLog.toLog(msg);
    }
}
