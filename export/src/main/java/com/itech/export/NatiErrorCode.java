// Copyright 2018-2020 Twitter, Inc.
// Licensed under the MoPub SDK License Agreement
// http://www.mopub.com/legal/sdk-license-agreement/

package com.itech.export;

public enum NatiErrorCode implements MoPubError {
    AD_SUCCESS("ad successfully loaded.",ER_SUCCESS),
    EMPTY_AD_RESPONSE("Server returned empty response.",ER_EMPTY_AD_RESPONSE),
    INVALID_RESPONSE("Unable to parse response from server.",ER_INVALID_RESPONSE),
    IMAGE_DOWNLOAD_FAILURE("Unable to download images associated with ad.",ER_IMAGE_DOWNLOAD_FAILURE),
    INVALID_REQUEST_URL("Invalid request url.",ER_INVALID_REQUEST_URL),
    UNEXPECTED_RESPONSE_CODE("Received unexpected response code from server.",ER_UNEXPECTED_RESPONSE_CODE),
    SERVER_ERROR_RESPONSE_CODE("Server returned erroneous response code.",ER_SERVER_ERROR_RESPONSE_CODE),
    CONNECTION_ERROR("Network is unavailable.",ER_CONNECTION_ERROR),
    UNSPECIFIED("Unspecified error occurred.",ER_UNSPECIFIED),

    NETWORK_INVALID_REQUEST("Third-party network received invalid request.",ER_NETWORK_INVALID_REQUEST),
    NETWORK_TIMEOUT("Third-party network failed to respond in a timely manner.",ER_NETWORK_TIMEOUT),
    NETWORK_NO_FILL("Third-party network failed to provide an ad.",ER_NETWORK_NO_FILL),
    NETWORK_INVALID_STATE("Third-party network failed due to invalid internal state.",ER_NETWORK_INVALID_STATE),

    NATIVE_RENDERER_CONFIGURATION_ERROR("A required renderer was not registered for the CustomEventNative.",ER_NATIVE_RENDERER_CONFIGURATION_ERROR),
    NATIVE_ADAPTER_CONFIGURATION_ERROR("CustomEventNative was configured incorrectly.",ER_NATIVE_ADAPTER_CONFIGURATION_ERROR),
    NATIVE_ADAPTER_NOT_FOUND("Unable to find CustomEventNative.",ER_NATIVE_ADAPTER_NOT_FOUND);

    private final String message;
    private final int code;

    NatiErrorCode(String message, int code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public final String toString() {
        return message;
    }

    @Override
    public int getIntCode() {
        return this.code;
//        switch (this) {
//            case NETWORK_TIMEOUT:
//                return ER_TIMEOUT;
//            case NATIVE_ADAPTER_NOT_FOUND:
//                return ER_ADAPTER_NOT_FOUND;
//            case AD_SUCCESS:
//                return ER_SUCCESS;
//        }
//        return ER_UNSPECIFIED;
    }

}
