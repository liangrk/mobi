package com.itech.export;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/06/28 18:11
 *     @desc   :
 * </pre>
 */
public class SplashErrorCode {

    private String message;
    private int code;

    public SplashErrorCode(String message, int code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String toString() {
        return message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
