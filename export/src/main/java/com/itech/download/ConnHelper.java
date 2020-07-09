package com.itech.download;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;

import com.itech.utils.AppUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/09 10:48
 *     @desc   : helper. eg: encrypt / mobile internet state
 * </pre>
 */
public class ConnHelper {
    public static byte[] encryptAES2Base64(byte[] data,
                                           String key) {
        byte[] input = encryptByte(data, key);
        if (input == null || input.length == 0) return new byte[0];
        return Base64.encode(input, Base64.NO_WRAP);
    }

    public static byte[] decryptBase642AES(byte[] data,
                                           String key) {
        if (data == null || data.length == 0) return new byte[0];
        byte[] decode = Base64.decode(data, Base64.NO_WRAP);
        return decryptByte(decode, key);
    }

    public static byte[] encryptByte(byte[] plaintext, String key) {
        byte[] ciphertext = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            ciphertext = cipher.doFinal(plaintext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ciphertext;
    }

    public static byte[] decryptByte(byte[] ciphertext, String key) {
        byte[] plaintext = null;
        try {
            SecretKeySpec skey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            plaintext = cipher.doFinal(ciphertext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plaintext;
    }

    public static boolean isNetConn() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                AppUtils.getService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            return info != null && info.isConnected();
        }
        return false;
    }
}
