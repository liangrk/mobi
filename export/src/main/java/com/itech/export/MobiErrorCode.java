// Copyright 2018-2020 Twitter, Inc.
// Licensed under the MoPub SDK License Agreement
// http://www.mopub.com/legal/sdk-license-agreement/

package com.itech.export;

public enum MobiErrorCode implements MoPubError {
    AD_SUCCESS("ad successfully loaded.",ER_SUCCESS),
    DO_NOT_TRACK("Do not track is enabled.",ER_DO_NOT_TRACK),
    UNSPECIFIED("Unspecified error.",ER_UNSPECIFIED),

    // Ad load server errors
    NO_FILL("No ads found.",ER_NO_FILL),
    WARMUP("Ad unit is warming up. Try again in a few minutes.",ER_WARMUP),
    SERVER_ERROR("Unable to connect to MoPub adserver.",ER_SERVER_ERROR),

    // Client ad load errors
    INTERNAL_ERROR("Unable to serve ad due to invalid internal state.",ER_INTERNAL_ERROR),
    RENDER_PROCESS_GONE_WITH_CRASH("Render process for this WebView has crashed.",ER_RENDER_PROCESS_GONE_WITH_CRASH),
    RENDER_PROCESS_GONE_UNSPECIFIED("Render process is gone for this WebView. Unspecified cause.",ER_RENDER_PROCESS_GONE_UNSPECIFIED),
    CANCELLED("Ad request was cancelled.",ER_CANCELLED),
    MISSING_AD_UNIT_ID("Unable to serve ad due to missing or empty ad unit ID.",ER_MISSING_AD_UNIT_ID),
    NO_CONNECTION("No internet connection detected.",ER_NO_CONNECTION),

    ADAPTER_NOT_FOUND("Unable to find Native Network or Custom Event adapter.",ER_ADAPTER_NOT_FOUND),
    ADAPTER_CONFIGURATION_ERROR("Native Network or Custom Event adapter was configured incorrectly.",ER_ADAPTER_CONFIGURATION_ERROR),
    ADAPTER_INITIALIZATION_SUCCESS("AdapterConfiguration initialization success.",ER_ADAPTER_INITIALIZATION_SUCCESS),

    EXPIRED("Ad expired since it was not shown within 4 hours.",ER_EXPIRED),

    NETWORK_TIMEOUT("Third-party network failed to respond in a timely manner.",ER_NETWORK_TIMEOUT),
    NETWORK_NO_FILL("Third-party network failed to provide an ad.",ER_NETWORK_NO_FILL),
    NETWORK_INVALID_STATE("Third-party network failed due to invalid internal state.",ER_NETWORK_INVALID_STATE),
    MRAID_LOAD_ERROR("Error loading MRAID ad.",ER_MRAID_LOAD_ERROR),
    VIDEO_CACHE_ERROR("Error creating a cache to store downloaded videos.",ER_VIDEO_CACHE_ERROR),
    VIDEO_DOWNLOAD_ERROR("Error downloading video.",ER_VIDEO_DOWNLOAD_ERROR),

    GDPR_DOES_NOT_APPLY("GDPR does not apply. Ignoring consent-related actions.",ER_GDPR_DOES_NOT_APPLY),

    REWARDED_CURRENCIES_PARSING_ERROR("Error parsing rewarded currencies JSON header.",ER_REWARDED_CURRENCIES_PARSING_ERROR),
    REWARD_NOT_SELECTED("Reward not selected for rewarded ad.",ER_REWARD_NOT_SELECTED),

    VIDEO_NOT_AVAILABLE("No video loaded for ad unit.",ER_VIDEO_NOT_AVAILABLE),
    VIDEO_PLAYBACK_ERROR("Error playing a video.",ER_VIDEO_PLAYBACK_ERROR);

    private final String message;
    private final int code;
    MobiErrorCode(String message, int code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String toString() {
        return this.message;
    }

    @Override
    public int getIntCode() {
        return code;
    }
}
