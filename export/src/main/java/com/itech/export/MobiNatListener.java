package com.itech.export;

import android.view.View;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/03 18:06
 *     @desc   :
 * </pre>
 */
public interface MobiNatListener {
    void onNatiLoad(View nativeView);

    void onNatiFail(NatiErrorCode errorCode);

    /**
     * @param view maybe null
     */
    void onImpression(View view);

    /**
     * TODO: maybe not call.
     * @param view
     */
    void onClick(View view);
}
