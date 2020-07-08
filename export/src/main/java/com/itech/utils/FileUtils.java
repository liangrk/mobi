package com.itech.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Build;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/08 15:00
 *     @desc   :
 * </pre>
 */
public class FileUtils {

//    public static boolean copy(){
//
//    }

    public static boolean exist(Context context, File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) return true;
        if (Build.VERSION.SDK_INT >= 29) {
            AssetFileDescriptor afd = null;
            try {
                Uri uri = Uri.fromFile(file);
                afd = context.getApplicationContext().getContentResolver()
                        .openAssetFileDescriptor(uri, "r");
                if (afd == null) return false;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            } finally {
                if (afd!=null){
                    try {
                        afd.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    public static boolean delete(File file) {
        if (file == null) return false;
        if (file.isDirectory()) {
            return deleteDir(file);
        }
        return deleteFile(file);
    }

    /**
     * Delete the directory.
     *
     * @param dir The directory.
     * @return {@code true}: success<br>{@code false}: fail
     */
    private static boolean deleteDir(final File dir) {
        if (dir == null) return false;
        // dir doesn't exist then return true
        if (!dir.exists()) return true;
        // dir isn't a directory then return false
        if (!dir.isDirectory()) return false;
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isFile()) {
                    if (!file.delete()) return false;
                } else if (file.isDirectory()) {
                    if (!deleteDir(file)) return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * Delete the file.
     *
     * @param file The file.
     * @return {@code true}: success<br>{@code false}: fail
     */
    private static boolean deleteFile(final File file) {
        return file != null && (!file.exists() || file.isFile() && file.delete());
    }
}
