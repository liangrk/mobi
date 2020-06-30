package com.itech.common;

import android.support.annotation.NonNull;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/06/28 12:02
 *     @desc   :
 * </pre>
 */
public class SdkConfigurationp {

    private SdkConfigurationp(@NonNull String unitId,
                              long delayImprFirstOpen,
                              MobiLog.LogLevel logLevel) {
        this.unitId = unitId;
        this.delayImprFirstOpen = delayImprFirstOpen;
        this.level = logLevel;
    }

    private String unitId;
    private long delayImprFirstOpen;
    private MobiLog.LogLevel level;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public long getDelayImprFirstOpen() {
        return delayImprFirstOpen;
    }

    public void setDelayImprFirstOpen(long delayImprFirstOpen) {
        this.delayImprFirstOpen = delayImprFirstOpen;
    }

    public MobiLog.LogLevel getLevel() {
        return level;
    }

    public void setLevel(MobiLog.LogLevel level) {
        this.level = level;
    }

    public static class Builder {
        private String unitId;
        private long delayImprFirstOpen = 0;
        private MobiLog.LogLevel level;

        public Builder(@NonNull String unitId) {
            this.unitId = unitId;
        }

        public Builder withDelayImprFirstOpen(long timeMillis) {
            this.delayImprFirstOpen = timeMillis;
            return this;
        }

        public Builder withLogLevel(MobiLog.LogLevel level) {
            this.level = level;
            return this;
        }

        public SdkConfigurationp build() {
            return new SdkConfigurationp(unitId, delayImprFirstOpen, level);
        }
    }
}
