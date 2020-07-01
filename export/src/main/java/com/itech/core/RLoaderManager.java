package com.itech.core;

import com.itech.constants.RConstants;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/06/30 19:41
 *     @desc   :
 * </pre>
 */
//public class RLoaderManager {
//
//    private static RLoaderManager manager;
//    private final ClassLoader classLoader;
//
//
//    public void loadMobiView() throws ClassNotFoundException {
//        Class<?> mobiView = loadClass(RConstants.CLA_MOBIVIEW);
//        mobi
//    }
//
//    private Class<?> loadClass(String clazz) throws ClassNotFoundException {
//        if (classLoader == null){
//            return null;
//        }
//        return classLoader.loadClass(clazz);
//    }
//
//    private RLoaderManager(){
//        classLoader = PluginManager.getInstance()
//                .getClassLoader();
//    }
//
//    public static RLoaderManager getInstance(){
//        if (manager == null){
//            synchronized (RLoaderManager.class){
//                if (manager == null){
//                    manager = new RLoaderManager();
//                }
//            }
//        }
//        return manager;
//    }
//}
