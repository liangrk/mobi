package com.itech.core;

import android.content.Context;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.didi.virtualapk.internal.LoadedPlugin;
import com.itech.R;
import com.itech.common.utils.PathUtils;
import com.itech.constants.RConstants;

import java.io.File;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/06/28 11:08
 *     @desc   :
 * </pre>
 */
public class PluginManager {

    private ClassLoader classLoader;

    private Context hostContext;

    private PluginManager() {
    }

    private String defaultPath = "mobi" + File.separator + "plugin.apk";
    private String hotfixPath = "hotfix" + File.separator + "plugin.apk";

    private static PluginManager manager;

    public static PluginManager getInstance() {
        if (manager == null) {
            synchronized (PluginManager.class) {
                if (manager == null) {
                    manager = new PluginManager();
                }
            }
        }
        return manager;
    }

    public void loadPath(@NonNull Context context) throws Exception {
        com.didi.virtualapk.PluginManager.getInstance(context.getApplicationContext()).init();
        String pathPrefix = PathUtils.getAppDataPath(context);
        File hotfixApk = new File(pathPrefix + hotfixPath);

        if (!hotfixApk.exists()) {
            //File defApk = new File(pathPrefix + defaultPath);
            if (!FileUtils.isFileExists(pathPrefix + defaultPath)){
                boolean result = copyApk(context, pathPrefix + defaultPath);
                if (!result) {
                    System.out.println("temp_log: apk copy failed?: file:");
                }
            }else {
                System.out.println("文件存在 无需copy");
            }

            File apk = new File(pathPrefix + defaultPath);
            loadPluginApk(context, apk);
            return;
        }
        // 校验hotfix apk完整性.
        loadPluginApk(context, hotfixApk);
    }

    // TODO: custom resource util has err.
    private boolean copyApk(Context context, String path) {
        boolean copyRaw = ResourceUtils.copyFileFromRaw(R.raw.plugin, path);
        System.out.println("copy result:" + copyRaw);
        return copyRaw;
    }

    private void loadPluginApk(Context context, File apk) throws Exception {
//        classLoader = Core.createClassLoader(context, apk, context.getClassLoader());
        com.didi.virtualapk.PluginManager instance = com.didi.virtualapk.PluginManager.getInstance(context);
        if (instance.getLoadedPlugin(RConstants.PKG_SDK) == null){
            instance.loadPlugin(apk);
        }
        LoadedPlugin loadedPlugin = instance.getLoadedPlugin(RConstants.PKG_SDK);
        classLoader = loadedPlugin.getClassLoader();

        hostContext = com.didi.virtualapk.PluginManager.getInstance(context).getHostContext();
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public Context getHostContext(){ return hostContext; }
}
