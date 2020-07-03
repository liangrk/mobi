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
public interface MobiNatiNetworkListener {
    void onNatiLoad();
    void onNatiFail(NatiErrorCode errorCode);

    void onImpression(View view);
    void onClick(View view);
}
