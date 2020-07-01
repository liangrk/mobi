package com.itech.component;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/07/01 10:42
 *     @desc   :
 * </pre>
 */
public enum MobiSize implements MobiSizeInt{
    MATCH_VIEW(MATCH_VIEW_INT),
    HEIGHT_50(HEIGHT_50_INT),
    HEIGHT_90(HEIGHT_90_INT),
    HEIGHT_250(HEIGHT_250_INT),
    HEIGHT_280(HEIGHT_280_INT);

    private final int size;

    MobiSize(final int sizeInt) {
        this.size = sizeInt;
    }

    public int getSize() {
        return size;
    }
}
