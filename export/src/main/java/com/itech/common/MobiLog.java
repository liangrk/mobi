package com.itech.common;

import android.support.annotation.NonNull;

/**
 * <pre>
 *     @author : wing-hong
 *     @email  : wing-hong@foxmail.com
 *     @date   : 2020/06/30 10:07
 *     @desc   :
 * </pre>
 */
public class MobiLog {
    /**
     * LogLevelInt values set for parity with iOS
     */
    public interface LogLevelInt {
        int DEBUG_INT = 20;
        int INFO_INT = 30;
        int NONE_INT = 70;
    }

    public enum LogLevel implements LogLevelInt {
        DEBUG(DEBUG_INT, "DEBUG"),
        INFO(INFO_INT, "INFO"),
        NONE(NONE_INT, "NONE");

        private int mLevel;
        private String mLevelString;

        LogLevel(int level, String levelString) {
            this.mLevel = level;
            this.mLevelString = levelString;
        }

        @NonNull
        public String toString() {
            return mLevelString;
        }

        @NonNull
        public int intValue() {
            return mLevel;
        }

        /**
         * This valueOf overload is used to get the associated LogLevel enum from an int.
         *
         * @param level The int value for which the LogLevel is needed.
         * @return The LogLevel associated with the level. Will return NONE by default.
         */
        @NonNull
        public static LogLevel valueOf(final int level) {
            switch (level) {
                case DEBUG_INT:
                    return DEBUG;
                case INFO_INT:
                    return INFO;
                case NONE_INT:
                default:
                    return NONE;
            }
        }
    }
}
