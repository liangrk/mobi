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
                              long delayImprFirstOpen) {
        this.unitId = unitId;
        this.delayImprFirstOpen = delayImprFirstOpen;
    }

    private String unitId;
    private long delayImprFirstOpen;

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

    public static class Builder {
        private String unitId;
        private long delayImprFirstOpen = 0;

        public Builder(@NonNull String unitId) {
            this.unitId = unitId;
        }
        public Builder withDelayImprFirstOpen(long timeMillis) {
            this.delayImprFirstOpen = timeMillis;
            return this;
        }

        public SdkConfigurationp build() {
            return new SdkConfigurationp(unitId, delayImprFirstOpen);
        }
    }
}
