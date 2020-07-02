// Copyright 2018-2020 Twitter, Inc.
// Licensed under the MoPub SDK License Agreement
// http://www.mopub.com/legal/sdk-license-agreement/

package com.itech.core;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Reflection {
    public static class MethodBuilder {
        @Nullable private final Object mInstance;
        @NonNull private final String mMethodName;
        @Nullable private Class<?> mClass;

        @NonNull private List<Class<?>> mParameterClasses;
        @NonNull private List<Object> mParameters;
        private boolean mIsAccessible;
        private boolean mIsStatic;

        public MethodBuilder(@Nullable final Object instance, @NonNull final String methodName) {

            mInstance = instance;
            mMethodName = methodName;

            mParameterClasses = new ArrayList<Class<?>>();
            mParameters = new ArrayList<Object>();

            mClass = (instance != null) ? instance.getClass() : null;
        }

        @NonNull
        public <T> MethodBuilder addParam(@NonNull final Class<T> clazz,
                @Nullable final T parameter) {

            mParameterClasses.add(clazz);
            mParameters.add(parameter);

            return this;
        }

        @NonNull
        public MethodBuilder addParam(@NonNull final String className,
                @Nullable final Object parameter) throws ClassNotFoundException {

            final Class<?> clazz = Class.forName(className);

            mParameterClasses.add(clazz);
            mParameters.add(parameter);

            return this;
        }

        @NonNull
        public MethodBuilder setAccessible() {
            mIsAccessible = true;

            return this;
        }

        @NonNull
        public MethodBuilder setStatic(@NonNull final Class<?> clazz) {

            mIsStatic = true;
            mClass = clazz;

            return this;
        }

        @NonNull
        public MethodBuilder setStatic(@NonNull final String className)
                throws ClassNotFoundException {

            mIsStatic = true;
            mClass = Class.forName(className);

            return this;
        }

        @Nullable
        public Object execute() throws Exception {
            final Class<?>[] classArray = new Class<?>[mParameterClasses.size()];
            final Class<?>[] parameterTypes = mParameterClasses.toArray(classArray);

            final Method method = getDeclaredMethodWithTraversal(mClass, mMethodName, parameterTypes);

            if (mIsAccessible) {
                method.setAccessible(true);
            }

            final Object[] parameters = mParameters.toArray();

            if (mIsStatic) {
                return method.invoke(null, parameters);
            } else {
                return method.invoke(mInstance, parameters);
            }
        }
    }

    @Nullable
    public static Method getDeclaredMethodWithTraversal(@Nullable final Class<?> clazz,
            @NonNull final String methodName, @NonNull final Class<?>... parameterTypes)
            throws NoSuchMethodException {

        Class<?> currentClass = clazz;

        while (currentClass != null) {
            try {
                return currentClass.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {
                currentClass = currentClass.getSuperclass();
            }
        }

        throw new NoSuchMethodException();
    }

    public static boolean classFound(@NonNull final String className) {

        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @NonNull
    public static <T> T instantiateClassWithEmptyConstructor(@NonNull final String className,
            @NonNull final Class<? extends T> superclass)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException, NullPointerException {

        final Class<? extends T> clazz = Class.forName(className).asSubclass(superclass);
        // noinspection unchecked
        final Constructor<? extends T> constructor = clazz.getDeclaredConstructor((Class[]) null);
        constructor.setAccessible(true);

        return constructor.newInstance();
    }

    @NonNull
    public static <T> T instantiateClassWithConstructor(@NonNull final String className,
            @NonNull final Class<? extends T> superClass, @NonNull final Class[] classes,
            @NonNull final Object[] parameters)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {

        final Class<? extends T> clazz = Class.forName(className).asSubclass(superClass);
        // noinspection unchecked
        final Constructor<? extends T> constructor = clazz.getDeclaredConstructor(classes);
        constructor.setAccessible(true);

        return constructor.newInstance(parameters);
    }

    // access class private field
    public static Field getPrivateField(@NonNull final Class classType, @NonNull final String fieldName) throws NoSuchFieldException {
        Field declaredField = classType.getDeclaredField(fieldName);
        declaredField.setAccessible(true);
        return declaredField;
    }

}
