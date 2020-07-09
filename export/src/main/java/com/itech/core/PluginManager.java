package com.itech.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.didi.virtualapk.internal.LoadedPlugin;
import com.itech.R;
import com.itech.constants.RConstants;
import com.itech.utils.AppUtils;
import com.itech.utils.FileUtils;
import com.itech.utils.PathUtils;
import com.itech.utils.ResourceUtils;
import com.itech.utils.SPHelper;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/06/28 11:08
 *     @desc   :
 * </pre>
 */
public class PluginManager {

    private PluginManager() {
    }

    public static String DIR = "/hotfix";
    private String defaultPathDir = "/dex";
    private String defaultFileName = "dex.dex";
    // in download util.
    // private String hotfixPath = DIR + File.separator + "fix" + File.separator + "dex01.dex";

    @SuppressLint("StaticFieldLeak")
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

        File dir = new File(pathPrefix+defaultPathDir);
        if (!dir.exists()){
            dir.mkdir();
        }
        File defaultApk = new File(dir,defaultFileName);

        String path = SPHelper.getString(loadPathKey);
        // 优先hotfix
        if (!TextUtils.isEmpty(path) && FileUtils.exist(context,path)){
            try {
                ToastUtils.showShort("here! you right");
                loadPluginApk(context,new File(path));
                System.out.println("hotfix replace load success! new hotfix path:"+path);
                // 删除非本次加载的同级目录下的hotfix
                return;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (!FileUtils.exist(context,defaultApk)){
            boolean b = copyApk(context, defaultApk.getPath());
            System.out.println("temp_log: apk copy:"+b);
        }
        // load default
        loadPluginApk(context,defaultApk);
//        if (FileUtils.hasChildFile(hotfixPath)) {
//            System.out.println("temp_log:plugin load hotLast.");
//            try {
//
//                return;
//            }catch (Exception e){
//                // 发生异常时. 卸载所有插件 重新reload.
//
//            }
//        }
//
//        if (!hotfixApk.exists()) {
//            //File defApk = new File(pathPrefix + defaultPath);
//            if (!FileUtils.exist(context, defaultApk)) {
//                boolean result = copyApk(context, pathPrefix + defaultPath);
//                if (!result) {
//                    System.out.println("temp_log: apk copy failed?: file:");
//                }
//            } else {
//                System.out.println("文件存在 无需copy");
//            }
//
//            File apk = new File(pathPrefix + defaultPath);
//            loadPluginApk(context, apk);
//            return;
//        }
//        // 校验hotfix apk完整性.
//
    }

    // TODO: custom resource util has err.
    private boolean copyApk(Context context, String path) {
        boolean copyRaw = ResourceUtils.copyRaw(context, R.raw.plugin, path);
        System.out.println("copy result:" + copyRaw);
        return copyRaw;
    }

    private void loadPluginApk(Context context, File apk) throws Exception {
        com.didi.virtualapk.PluginManager instance = com.didi.virtualapk.PluginManager.getInstance(context);
        if (instance.getLoadedPlugin(RConstants.PKG_SDK) == null) {
            instance.loadPlugin(apk);
        }
        loadedPlugin = instance.getLoadedPlugin(RConstants.PKG_SDK);
        classLoader = loadedPlugin.getClassLoader();
        hostContext = com.didi.virtualapk.PluginManager.getInstance(context).getHostContext();
    }

    public LoadedPlugin getLoadedPlugin() {
        return loadedPlugin;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public Context getHostContext() {
        return hostContext;
    }

    public void getAppInfo(File apk) {
        PackageManager pm = AppUtils.getApplication().getPackageManager();
        if (pm == null) return;
        PackageInfo pi = pm.getPackageArchiveInfo(apk.getAbsolutePath(), 0);
        if (pi == null) return;
        //pi
    }

    public static String loadPathKey = "plLoader";

    private static Map<String, Class> cacheMap = new HashMap<>();
    private static Map<Class, Map<String, Method>> methodCacheMap = new HashMap<>();

    private static Object[] singleArr = new Object[1];
    private static Object[] twoArr = new Object[2];
    private static Object[] threeArr = new Object[3];

    private static Class[] singleType = new Class[1];
    private static Class[] twoType = new Class[2];
    private static Class[] threeType = new Class[3];

    private LoadedPlugin loadedPlugin;

    public static Class getClass(String className) {
        if (manager == null) {
            System.out.println("plugin manager not init.");
            return null;
        }
        if (cacheMap.containsKey(className)) {
            return cacheMap.get(className);
        }
        try {
            Class<?> clazz = manager.getClassLoader().loadClass(className);
            cacheMap.put(className, clazz);
            return clazz;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("class not found.");
            return null;
        }
    }

    public static Object[] getObjectArr(Object o) {
        singleArr[0] = o;
        return singleArr;
    }

    public static Object[] getObjectArr(Object o1, Object o2) {
        twoArr[0] = o1;
        twoArr[1] = o2;
        return twoArr;
    }

    public static Object[] getObjectArr(Object o1, Object o2, Object o3) {
        threeArr[0] = o1;
        threeArr[1] = o2;
        threeArr[2] = o3;
        return threeArr;
    }

    public static Class[] getParamsType(Class params) {
        singleType[0] = params;
        return singleType;
    }

    public static Class[] getParamsType(Class c1, Class c2) {
        twoType[0] = c1;
        twoType[1] = c2;
        return twoType;
    }

    public static Class[] getParamsType(Class c1, Class c2, Class c3) {
        threeType[0] = c1;
        threeType[1] = c2;
        threeType[2] = c3;
        return threeType;
    }

    private ClassLoader classLoader;
    private Context hostContext;
}
