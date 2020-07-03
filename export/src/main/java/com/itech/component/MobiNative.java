package com.itech.component;

import android.content.Context;
import android.support.annotation.NonNull;

import com.itech.constants.RConstants;
import com.itech.core.PluginManager;
import com.itech.core.Reflection;

import java.lang.reflect.InvocationTargetException;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/03 17:40
 *     @desc   : native.
 * </pre>
 */
public class MobiNative {

    private Context context;
    private String unitId;
    private Class<?> natiClass;

    public void makeRequest(){

    }

    private void initNati(@NonNull Context context,@NonNull String unitId){
        try {
            natiClass = PluginManager.getInstance()
                    .getClassLoader()
                    .loadClass(RConstants.CLA_MOBI_NATI);
            natiClass.getConstructor(Context.class,String.class)
                    .newInstance(context,unitId);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void getNatiRequest(){

    }

    public void onDestroy(){

    }

}
