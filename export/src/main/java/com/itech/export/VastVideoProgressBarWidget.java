// Copyright 2018-2020 Twitter, Inc.
// Licensed under the MoPub SDK License Agreement
// http://www.mopub.com/legal/sdk-license-agreement/

package com.itech.export;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

@SuppressLint("AppCompatCustomView")
public class VastVideoProgressBarWidget extends ImageView {
    @NonNull private ProgressBarDrawable mProgressBarDrawable;
    private final int mProgressBarHeight;

    public VastVideoProgressBarWidget(@NonNull final Context context) {
        super(context);

        setId(View.generateViewId());

        mProgressBarDrawable = new ProgressBarDrawable(context);
        setImageDrawable(mProgressBarDrawable);

        mProgressBarHeight =
                Dips.dipsToIntPixels(DrawableConstants.ProgressBar.HEIGHT_DIPS, context);
    }

    public void setAnchorId(final int anchorId) {
        final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                mProgressBarHeight);
        layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, anchorId);
        setLayoutParams(layoutParams);

    }

    public void calibrateAndMakeVisible(final int duration, final int skipOffset) {
        mProgressBarDrawable.setDurationAndSkipOffset(duration, skipOffset);
        setVisibility(View.VISIBLE);
    }

    public void updateProgress(final int progress) {
        mProgressBarDrawable.setProgress(progress);
    }

    public void reset() {
        mProgressBarDrawable.reset();
        mProgressBarDrawable.setProgress(0);
    }

    // for testing
    @Deprecated
    ProgressBarDrawable getImageViewDrawable() {
        return mProgressBarDrawable;
    }

    // for testing
    @Deprecated
    void setImageViewDrawable(@NonNull ProgressBarDrawable drawable) {
        mProgressBarDrawable = drawable;
    }
}
