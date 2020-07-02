package com.itech.export;

import android.view.View;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/06/29 17:48
 *     @desc   :
 * </pre>
 */
public interface MobiSplashListener {
    void onError(int splashErrorCode,String msg);

    void onSplashLoad(View view);

    void onTimeout();

    void onImpression(View view);

    void onClick(View view);

    void onSkip();

    void onTimeOver();
}
