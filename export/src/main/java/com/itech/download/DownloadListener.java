package com.itech.download;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/07 18:09
 *     @desc   :
 * </pre>
 */
public interface DownloadListener {

    /**
     * 开始下载.
     */
    void startDownload();

    /**
     * 下载完成.
     */
    void endDownload();
}
