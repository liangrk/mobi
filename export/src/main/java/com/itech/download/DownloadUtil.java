package com.itech.download;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.itech.core.PluginManager;
import com.itech.other.DownloadImpl;
import com.itech.other.DownloadListenerAdapter;
import com.itech.other.DownloadTask;
import com.itech.other.Extra;
import com.itech.other.Runtime;
import com.itech.utils.SPHelper;

import java.io.File;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/07 10:08
 *     @desc   :
 * </pre>
 */
public class DownloadUtil {

    public static String LAST_DOWNLOAD_MD5_KEY = "lastMd5";

    private static final String AUTHOR = "com.itech.download.DownloadFileProvider.fileProvider";

    /**
     * static function utils class.
     */
    private DownloadUtil() {
    }

    public static void downloadStart(@NonNull Context context, @NonNull String url, @NonNull final String fileMd5) {
        final String lastDownloadMd5 = SPHelper.getString(LAST_DOWNLOAD_MD5_KEY);
        if (!TextUtils.isEmpty(lastDownloadMd5)) {
            if (lastDownloadMd5.equals(fileMd5)) {
                return;
            }
        }
        // 分发 hotfix path.
        String urlMd5 = Runtime.getInstance().md5(url);
        File dir = new File(context.getExternalCacheDir() + PluginManager.DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (DownloadImpl.getInstance().exist(url)) {
            DownloadImpl.getInstance().resume(url);
            System.out.println("下载中...");
        } else {
            System.out.println("下载开始");
            DownloadImpl.getInstance()
                    .with(context)
                    .target(new File(dir, urlMd5 + ".dex"), AUTHOR)
                    .setUniquePath(false)
                    .setForceDownload(true)
                    .setRetry(4)
                    .setBlockMaxTime(60000L)
                    .setConnectTimeOut(10000L)
                    .setDownloadTimeOut(Long.MAX_VALUE)
                    .setOpenBreakPointDownload(true)
                    .setParallelDownload(true)
                    .setExtData(fileMd5)
                    .url(url)
                    .enqueue(new DownloadListenerAdapter() {
                        @Override
                        public void onStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength, Extra extra) {
                            super.onStart(url, userAgent, contentDisposition, mimetype, contentLength, extra);
                            System.out.println("download task start...");
                        }

                        @Override
                        public void onProgress(String url, long downloaded, long length, long usedTime) {
                            super.onProgress(url, downloaded, length, usedTime);
                            System.out.println("download task progress..." + downloaded);
                        }

                        @Override
                        public boolean onResult(Throwable throwable, Uri path, String url, Extra extra) {
                            System.out.println("download complete");
                            DownloadTask downloadTask = (DownloadTask) extra;
                            String downloadFileMd5 = downloadTask.getFileMD5();
                            System.out.println("md5对比结果:" + fileMd5.equals(downloadFileMd5));
                            if (fileMd5.equals(downloadFileMd5)) {
                                // 应用.
                                SPHelper.putString(LAST_DOWNLOAD_MD5_KEY, fileMd5);
                                SPHelper.putString(PluginManager.loadPathKey, downloadTask.getFile().getAbsolutePath());
                            }
                            return super.onResult(throwable, path, url, extra);
                        }
                    });
        }
    }
}
