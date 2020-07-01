package com.itech.export;

import android.view.View;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/01 9:54
 *     @desc   : export component.
 * </pre>
 */
public interface BanListener {
    /**
     * banner load success
     */
    void onBanLoaded(View view);

    /**
     * banner load failed
     * @param view banner view
     * @param mobiErr contain err_code & err_msg
     */
    void onBanFailed(View view,MobiErrorCode mobiErr);

    /**
     * banner click callback
     */
    void onBanClicked(View view);

    void onBanExpanded(View view);

    void onBanCollapsed(View view);
}
