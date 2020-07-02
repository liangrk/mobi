package com.itech.export;

import android.support.annotation.NonNull;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/06/28 18:11
 *     @desc   :
 * </pre>
 */
@Deprecated
public class SplashErrorCode implements MoPubError {

    private final String message;
    private final int code;

    public SplashErrorCode(String message, int code) {
        this.message = message;
        this.code = code;
    }

    @NonNull
    @Override
    public final String toString() {
        return message;
    }

    @Override
    public int getIntCode() {
        return 0;
    }
}
