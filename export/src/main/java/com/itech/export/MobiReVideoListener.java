// Copyright 2018-2020 Twitter, Inc.
// Licensed under the MoPub SDK License Agreement
// http://www.mopub.com/legal/sdk-license-agreement/

package com.itech.export;

import android.support.annotation.NonNull;

import java.util.Set;

/**
 * Listener for rewarded video events. Implementers of this interface will receive events for all
 * rewarded video ad units in the app.:
 */
public interface MobiReVideoListener {

    /**
     * Called when the adUnitId has loaded. At this point you should be able to call
     */
    public void onReVideoLoadSuccess(@NonNull String adUnitId);

    /**
     * Called when a video fails to load for the given ad unit id. The provided error code will
     * give more insight into the reason for the failure to load.
     */
    public void onReVideoLoadFailure(@NonNull String adUnitId, @NonNull MobiErrorCode errorCode);

    /**
     * Called when a rewarded video starts playing.
     */
    public void onReVideoStarted(@NonNull String adUnitId);

    /**
     * Called when there is an error during video playback.
     */
    public void onReVideoPlaybackError(@NonNull String adUnitId, @NonNull MobiErrorCode errorCode);

    /**
     * Called when a rewarded video is clicked.
     */
    public void onReVideoClicked(@NonNull String adUnitId);

    /**
     * Called when a rewarded video is downstart.
     */
    public void onReVideoDownStart(@NonNull String adUnitId);

    /**
     * Called when a rewarded video is closed. At this point your application should resume.
     */
    public void onReVideoClosed(@NonNull String adUnitId);

    /**
     * Called when a rewarded video is completed and the user should be rewarded.
     */
    public void onReVideoCompleted(@NonNull Set<String> adUnitIds, @NonNull MobiReward reward);
}
