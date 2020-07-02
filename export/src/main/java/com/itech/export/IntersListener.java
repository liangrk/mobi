package com.itech.export;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/02 10:26
 *     @desc   :
 * </pre>
 */
public interface IntersListener {
    void onIntersLoaded();
    void onIntersFailed(MobiErrorCode errorCode);
    void onIntersShow();
    void onIntersClick();
    void onInterClose();
}
