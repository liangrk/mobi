// Copyright 2018-2020 Twitter, Inc.
// Licensed under the MoPub SDK License Agreement
// http://www.mopub.com/legal/sdk-license-agreement/

package com.itech.export;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.EnumSet;

public class RequestParameters {

    public enum NatiAsset {
        TITLE("title"),
        TEXT("text"),
        ICON_IMAGE("iconimage"),
        MAIN_IMAGE("mainimage"),
        VIDEO("video"),
        CALL_TO_ACTION_TEXT("ctatext"),
        STAR_RATING("starrating"),
        SPONSORED("sponsored");

        private final String mAssetName;

        private NatiAsset(@NonNull String assetName) {
            mAssetName = assetName;
        }

        @NonNull
        @Override
        public String toString() {
            return mAssetName;
        }
    }

    @Nullable
    private final String mKeywords;
    @Nullable
    private final String mUserDataKeywords;
    @Nullable
    private final Location mLocation;
    @Nullable
    private final EnumSet<NatiAsset> mDesiredAssets;

    public final static class Builder {
        private String keywords;
        private String userDatakeywords;
        private Location location;
        private EnumSet<NatiAsset> desiredAssets;

        @NonNull
        public final Builder keywords(String keywords) {
            this.keywords = keywords;
            return this;
        }

        @NonNull
        public final Builder userDataKeywords(String userDataKeywords) {
            this.userDatakeywords = userDataKeywords;
            return this;
        }

        @NonNull
        public final Builder location(Location location) {
            this.location = location;
            return this;
        }

        // Specify set of assets used by this ad request. If not set, this defaults to all assets
        @NonNull
        public final Builder desiredAssets(final EnumSet<NatiAsset> desiredAssets) {
            this.desiredAssets = EnumSet.copyOf(desiredAssets);
            return this;
        }

        @NonNull
        public final RequestParameters build() {
            return new RequestParameters(this);
        }
    }

    private RequestParameters(@NonNull Builder builder) {
        mKeywords = builder.keywords;
        mDesiredAssets = builder.desiredAssets;
        mUserDataKeywords = builder.userDatakeywords;
        mLocation = builder.location;
    }

    @Nullable
    public final String getKeywords() {
        return mKeywords;
    }

    @Nullable
    public final String getUserDataKeywords() {
        return mUserDataKeywords;
    }

    @Nullable
    public final Location getLocation() {
        return mLocation;
    }

    public final String getDesiredAssets() {
        String result = "";

        if (mDesiredAssets != null) {
            result = TextUtils.join(",", mDesiredAssets.toArray());
        }
        return result;
    }
}
