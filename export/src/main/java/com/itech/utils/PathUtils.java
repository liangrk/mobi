package com.itech.utils;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import java.io.File;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/06/28 11:17
 *     @desc   :
 * </pre>
 */
public class PathUtils {

    private static boolean isSDCardEnable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public static String getAppDataPath(@NonNull Context context) {
        if (!isSDCardEnable()) {
            return "";
        }
        File cacheDir = context.getApplicationContext().getExternalCacheDir();
        if (cacheDir == null) return "";
        return getAbsolutePath(cacheDir);
    }

    private static String getAbsolutePath(final File file) {
        if (file == null) return "";
        return file.getAbsolutePath();
    }
}
