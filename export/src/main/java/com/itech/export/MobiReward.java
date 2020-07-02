// Copyright 2018-2020 Twitter, Inc.
// Licensed under the MoPub SDK License Agreement
// http://www.mopub.com/legal/sdk-license-agreement/

package com.itech.export;

import android.support.annotation.NonNull;

/**
 * Represents a reward to the user for completing a rewarded task like watching a video.
 */
public final class MobiReward {
    /**
     * This should be used if there is no reward label specified.
     */
    public static final String NO_REWARD_LABEL = "";

    /**
     * Legacy amount for certain custom events that don't take a reward amount. Try not to use this
     * for any future custom events.
     */
    public static final int NO_REWARD_AMOUNT = -123;

    /**
     * The default amount if nothing or an invalid amount is specified. This also is applicable for
     * custom events that do not require a reward amount.
     */
    public static final int DEFAULT_REWARD_AMOUNT = 0;

    private final boolean mSuccess;
    private final @NonNull String mLabel;
    private final int mAmount;

    private MobiReward(boolean success, @NonNull String label, int amount) {
        mSuccess = success;
        mLabel = label;
        mAmount = amount;
    }

    @NonNull
    public static MobiReward failure() {
        return new MobiReward(false, NO_REWARD_LABEL, DEFAULT_REWARD_AMOUNT);
    }

    @NonNull
    public static MobiReward success(@NonNull final String rewardLabel, final int amount) {
        return new MobiReward(true, rewardLabel, amount);
    }

    public final boolean isSuccessful() {
        return mSuccess;
    }

    @NonNull
    public final String getLabel() {
        return mLabel;
    }

    public final int getAmount() {
        return mAmount;
    }
}
