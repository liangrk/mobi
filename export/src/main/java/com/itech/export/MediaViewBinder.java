// Copyright 2018-2020 Twitter, Inc.
// Licensed under the MoPub SDK License Agreement
// http://www.mopub.com/legal/sdk-license-agreement/

package com.itech.export;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MediaViewBinder {
    public final static class Builder {
        private final int layoutId;
        private int mediaLayoutId;
        private int titleId;
        private int textId;
        private int iconImageId;
        private int callToActionId;
        private int privacyInformationIconImageId;
        private int sponsoredTextId;

        private Map<String, Integer> extras = Collections.emptyMap();

        public Builder(final int layoutId) {
            this.layoutId = layoutId;
            this.extras = new HashMap<String, Integer>();
        }

        public final Builder mediaLayoutId(final int mediaLayoutId) {
            this.mediaLayoutId = mediaLayoutId;
            return this;
        }

        public final Builder titleId(final int titlteId) {
            this.titleId = titlteId;
            return this;
        }

        public final Builder textId(final int textId) {
            this.textId = textId;
            return this;
        }

        public final Builder iconImageId(final int iconImageId) {
            this.iconImageId = iconImageId;
            return this;
        }

        public final Builder callToActionId(final int callToActionId) {
            this.callToActionId = callToActionId;
            return this;
        }

        public final Builder privacyInformationIconImageId(final int privacyInformationIconImageId) {
            this.privacyInformationIconImageId = privacyInformationIconImageId;
            return this;
        }

        public final Builder sponsoredTextId(final int sponsoredTextId) {
            this.sponsoredTextId = sponsoredTextId;
            return this;
        }

        public final Builder addExtras(final Map<String, Integer> resourceIds) {
            this.extras = new HashMap<String, Integer>(resourceIds);
            return this;
        }

        public final Builder addExtra(final String key, final int resourceId) {
            this.extras.put(key, resourceId);
            return this;
        }

        public final MediaViewBinder build() {
            return new MediaViewBinder(this);
        }
    }

    public final int layoutId;
    public final int mediaLayoutId;
    public final int titleId;
    public final int textId;
    public final int callToActionId;
    public final int iconImageId;
    public final int privacyInformationIconImageId;
    public final int sponsoredTextId;
    public final Map<String, Integer> extras;

    private MediaViewBinder(final Builder builder) {
        this.layoutId = builder.layoutId;
        this.mediaLayoutId = builder.mediaLayoutId;
        this.titleId = builder.titleId;
        this.textId = builder.textId;
        this.callToActionId = builder.callToActionId;
        this.iconImageId = builder.iconImageId;
        this.privacyInformationIconImageId = builder.privacyInformationIconImageId;
        this.sponsoredTextId = builder.sponsoredTextId;
        this.extras = builder.extras;
    }
}
