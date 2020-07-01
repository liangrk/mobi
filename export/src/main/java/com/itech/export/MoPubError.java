// Copyright 2018-2020 Twitter, Inc.
// Licensed under the MoPub SDK License Agreement
// http://www.mopub.com/legal/sdk-license-agreement/

package com.itech.export;

/**
 * Temporary solution, this interface will be removed in the next major release
 */
@Deprecated
public interface MoPubError {
    int ER_SUCCESS = 0;
    int ER_ADAPTER_NOT_FOUND = 1;
    int ER_TIMEOUT = 2;
    int ER_INVALID_DATA = 3;
    int ER_NO_FILL = 4;
    int ER_UNSPECIFIED = 10000;

    int ER_WARMUP=5;
    int ER_SERVER_ERROR=6;

    // Client ad load errors
    int ER_INTERNAL_ERROR=7;
    int ER_RENDER_PROCESS_GONE_WITH_CRASH=8;
    int ER_RENDER_PROCESS_GONE_UNSPECIFIED=9;
    int ER_CANCELLED=10;
    int ER_MISSING_AD_UNIT_ID=11;
    int ER_NO_CONNECTION=12;

//    int ER_ADAPTER_NOT_FOUND=6;
    int ER_ADAPTER_CONFIGURATION_ERROR=13;
    int ER_ADAPTER_INITIALIZATION_SUCCESS=14;

    int ER_EXPIRED=15;

    int ER_NETWORK_TIMEOUT=16;
    int ER_NETWORK_NO_FILL=17;
    int ER_NETWORK_INVALID_STATE=18;
    int ER_MRAID_LOAD_ERROR=19;
    int ER_VIDEO_CACHE_ERROR=20;
    int ER_VIDEO_DOWNLOAD_ERROR=21;

    int ER_GDPR_DOES_NOT_APPLY=22;

    int ER_REWARDED_CURRENCIES_PARSING_ERROR=23;
    int ER_REWARD_NOT_SELECTED=24;

    int ER_VIDEO_NOT_AVAILABLE=25;
    int ER_VIDEO_PLAYBACK_ERROR=26;
    int ER_DO_NOT_TRACK = 27;


    int ER_EMPTY_AD_RESPONSE = 28;
    int ER_INVALID_RESPONSE = 29;
    int ER_IMAGE_DOWNLOAD_FAILURE = 30;
    int ER_INVALID_REQUEST_URL = 31;

    int ER_UNEXPECTED_RESPONSE_CODE=32;
    int ER_SERVER_ERROR_RESPONSE_CODE=33;
    int ER_CONNECTION_ERROR=34;
    int ER_NETWORK_INVALID_REQUEST=35;
    int ER_NATIVE_RENDERER_CONFIGURATION_ERROR=36;
    int ER_NATIVE_ADAPTER_CONFIGURATION_ERROR=37;
    int ER_NATIVE_ADAPTER_NOT_FOUND=38;
    /**
     * {@link MobiErrorCode} and NativeErrorCode must implement this function to map
     * enum value to server error code value
     * @return ER_* constant value
     */
    int getIntCode();
}
