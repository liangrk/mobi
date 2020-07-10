/*
 * Copyright (C)  Justson(https://github.com/Justson/Downloader)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.itech.download;

//import androidx.core.content.FileProvider;

import android.app.Application;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;

import com.itech.logic.IReceiver;
import com.itech.utils.AppUtils;

/**
 * @author cenxiaozhong
 * @since 2.0.0
 */
public class DownloadFileProvider extends FileProvider {

    @Override
    public void attachInfo(@NonNull Context context, @NonNull ProviderInfo info) {
        super.attachInfo(context, info);
        try {
            Application application = (Application) context;
            AppUtils.init(application);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreate() {
        System.out.println("provider exec");
        Application application = (Application) getContext().getApplicationContext();
        AppUtils.init(application);
        IReceiver.registerIReceiver(application.getApplicationContext());
        return super.onCreate();
    }
}
