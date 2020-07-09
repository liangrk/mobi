package com.itech.download;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.itech.BuildConfig;
import com.itech.constants.RConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/09 10:00
 *     @desc   :
 * </pre>
 */
public class Conn {

    private String targetHost = BuildConfig.DEBUG ? "https://reqtest.ztdance.cn" : "https://req.nuplayer.cn";
    private boolean post = true;
    private String method = post ? "POST" : "GET";
    private String charset = "UTF-8";
    /**
     * 重定向.
     */
    private boolean redirects = true;
    private byte[] buff = new byte[1024];

    private Map<String, Object> jsonMap = new HashMap<>();

    private int pv;
    private int nv;
    private String ime;
    private String androidId;
    private String mid;

    private static Conn connClazz;
    private HandlerHelper helper = HandlerHelper.newInstance();

    public static Conn getConnClazz() {
        if (connClazz == null) {
            connClazz = new Conn(Manager.PV, Manager.NV, Manager.getAndroidId(), Manager.getAndroidIme(), Manager.getMid());
        }
        return connClazz;
    }

    private Conn(int pv, int nv, String ime, String androidId, String mid) {
        this.pv = pv;
        this.nv = nv;
        this.ime = ime;
        this.androidId = androidId;
        this.mid = mid;
    }

    /**
     * if url is not null
     * so call downloadUtils.download function.
     */
    // public void sentMU(int pv, int nv, String ime, String androidId, String mid) {
    public void sentMU(@NonNull final Context context) {
        setConfiguration();
        helper.startTask(new Runnable() {
            @Override
            public void run() {
                try {
                    String jsonStr = sendRequest(jsonMap, "/m/u");
                    System.out.println("检测接口1:" + jsonStr);
                    if (TextUtils.isEmpty(jsonStr)) {
                        return;
                    }
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONObject data = jsonObject.getJSONObject("data");
                    final String md5 = data.getString("md5");
                    final String targetUrl = data.getString("url");
                    System.out.println("hotfix target url:" + targetUrl);
                    if (!TextUtils.isEmpty(targetUrl) && !TextUtils.isEmpty(md5)) {
                        helper.runOnMainThread(new Runnable() {
                            @Override
                            public void run() {
                                DownloadUtil.downloadStart(context, targetUrl, md5);
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // public void sendMuPa(int pv, int nv, String ime, String androidId, String mid) {
    public void sendMuPa() {
        setConfiguration();
        helper.startTask(new Runnable() {
            @Override
            public void run() {
                try {
                    String jsonStr = sendRequest(jsonMap,"/m/upa");
                    System.out.println("检测接口2:"+jsonStr);
                    if (TextUtils.isEmpty(jsonStr)){
                        return;
                    }
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONObject data = jsonObject.getJSONObject("data");
                    int res = data.getInt("res");
                    if (res==1){

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    // public void sendMup(int pv, int nv, String ime, String androidId, String mid, List<String> pkg) {
    public void sendMup(List<String> pkg) {
        if (pkg == null) return;
        setConfiguration();
        JSONArray jsonArray = new JSONArray();
        for (String s : pkg) {
            if (!TextUtils.isEmpty(s)) {
                // 保证不为空
                jsonArray.put(s);
            }
        }
        jsonMap.put("pkg", jsonArray);
        String request = sendRequest(jsonMap, "/m/up");
        System.out.println("全量:" + request);
    }

    public void sendMpa(String pkg, String action) {
        if (TextUtils.isEmpty(pkg) || TextUtils.isEmpty(action)) return;
        setConfiguration();
        jsonMap.put("pkg", pkg);
        jsonMap.put("action", action);
        String request = sendRequest(jsonMap, "/m/pa");
    }

    /**
     * 传入aes加密的字节数组进行解密
     *
     * @param encryptByte aes加密的字节数组
     * @return 解密后的字符串
     * @throws UnsupportedEncodingException
     */
    private String deEncryptJson(byte[] encryptByte) throws UnsupportedEncodingException {
        return new String(ConnHelper.decryptBase642AES(encryptByte, RConstants.LOCK), charset);
    }

    /**
     * 传入一个map 获取加密后的json字符串的字节数组
     *
     * @param jsonMap json存放的map key==json_key value == json_value
     * @return aes加密后的json字符串的字节数组
     * @throws UnsupportedEncodingException
     */
    private byte[] encryptJson(Map<String, Object> jsonMap) throws UnsupportedEncodingException {
        JSONObject jsonObject = new JSONObject();
        Iterator<String> iterator = jsonMap.keySet().iterator();
        try {
            while (iterator.hasNext()) {
                String key = iterator.next();
                jsonObject.put(key, jsonMap.get(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ConnHelper.encryptAES2Base64(jsonObject.toString().getBytes(charset), RConstants.LOCK);
    }

    /**
     * 通过HttpUrlConnection进行网络请求
     *
     * @param map 加密后的字节数组
     * @return response result. maybe null
     */
    private synchronized String sendRequest(Map<String, Object> map, String childPath) {
        if (!ConnHelper.isNetConn()) return null;
        HttpURLConnection conn = null;
        URL url = null;
        InputStream is = null;
        OutputStream os = null;
        ByteArrayOutputStream baos = null;
        try {
            url = new URL(targetHost + childPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setInstanceFollowRedirects(redirects);
            conn.setConnectTimeout(10000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            os = conn.getOutputStream();
            byte[] bytes = encryptJson(map);
            os.write(bytes);
            is = conn.getInputStream();
            baos = new ByteArrayOutputStream();
            int rc = 0;
            //byte[] buff = new byte[1024*4];
            while ((rc = is.read(buff, 0, buff.length)) > 0) {
                baos.write(buff, 0, rc);
            }
            byte[] responseByte = baos.toByteArray();
            return deEncryptJson(responseByte);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (is != null) {
                    is.close();
                }
                if (os != null) {
                    os.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public void setNv(int nv) {
        this.nv = nv;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    private void setConfiguration() {
        jsonMap.clear();
        jsonMap.put("pv", pv);
        jsonMap.put("nv", nv);
        jsonMap.put("ime", ime);
        jsonMap.put("androidId", androidId);
        jsonMap.put("mid", mid);
    }
}
