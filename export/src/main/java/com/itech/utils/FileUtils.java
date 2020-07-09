package com.itech.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/08 15:00
 *     @desc   :
 * </pre>
 */
public class FileUtils {

    private static final char[] HEX_DIGITS_UPPER =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String getFileMd5ToString(File file) {
        com.blankj.utilcode.util.FileUtils.getFileMD5ToString(new File(""));
        return bytes2HexString(getFileMd5(file));
    }

    public static String bytes2HexString(final byte[] bytes) {
        if (bytes == null) return "";
        char[] hexDigits = HEX_DIGITS_UPPER;
        int len = bytes.length;
        if (len < 0) return "";
        char[] ret = new char[len << 1];
        for (int i = 0, j = 0; i < len; i++) {
            ret[j++] = hexDigits[bytes[i] >> 4 & 0x0f];
            ret[j++] = hexDigits[bytes[i] & 0x0f];
        }
        return new String(ret);
    }

    public static byte[] getFileMd5(File file) {
        if (file == null) return null;
        DigestInputStream dis = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            dis = new DigestInputStream(fis, md);
            byte[] buffer = new byte[1024 * 256];
            while (true) {
                if (!(dis.read(buffer) > 0)) break;
            }
            md = dis.getMessageDigest();
            return md.digest();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dis != null) dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

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
                if (afd != null) {
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

    public static boolean exist(Context context, String filePath) {
        File file = getFileByPath(filePath);
        return exist(context, file);
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

    private static File getFileByPath(final String path) {
        return TextUtils.isEmpty(path) ? null : new File(path);
    }

    public static boolean hasChildFile(String path) {
        if (TextUtils.isEmpty(path)) return false;
        File file = new File(path);
        if (!file.exists()) return false;
        if (file.isFile()) return false;
        return file.listFiles().length != 0;
    }
}
