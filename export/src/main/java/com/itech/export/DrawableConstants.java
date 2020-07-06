// Copyright 2018-2020 Twitter, Inc.
// Licensed under the MoPub SDK License Agreement
// http://www.mopub.com/legal/sdk-license-agreement/

package com.itech.export;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

public class DrawableConstants {

    public static final int TRANSPARENT_GRAY = 0x88000000;

    public static class ProgressBar {
        public static final int HEIGHT_DIPS = 4;
        public static final int NUGGET_WIDTH_DIPS = 4;

        public static final int BACKGROUND_COLOR = Color.WHITE;
        public static final int BACKGROUND_ALPHA = 128;
        public static final Paint.Style BACKGROUND_STYLE = Paint.Style.FILL;

        public static final int PROGRESS_COLOR = Color.parseColor("#FFCC4D");
        public static final int PROGRESS_ALPHA = 255;
        public static final Paint.Style PROGRESS_STYLE = Paint.Style.FILL;
    }

    public static class RadialCountdown {
        public static final int SIDE_SOUND_LENGTH_DIPS = 35;
        public static final int SIDE_LENGTH_DIPS = 30;
        public static final int TOP_MARGIN_DIPS = 12;
        public static final int RIGHT_MARGIN_DIPS = 12;
        public static final int PADDING_DIPS = 5;

        public static final int CIRCLE_STROKE_WIDTH_DIPS = 3;
        public static final float TEXT_SIZE_SP = 14f;
        public static final float START_ANGLE = -90f;

        public static final int BACKGROUND_COLOR = Color.WHITE;
        public static final int BACKGROUND_ALPHA = 128;
        public static final Paint.Style BACKGROUND_STYLE = Paint.Style.STROKE;

        public static final int PROGRESS_COLOR = Color.WHITE;
        public static final int PROGRESS_ALPHA = 255;
        public static final Paint.Style PROGRESS_STYLE = Paint.Style.STROKE;

        public static final int TEXT_COLOR = Color.WHITE;
        public static final Paint.Align TEXT_ALIGN = Paint.Align.CENTER;
    }

    public static class SideWidget{
        public static final int CORNER_RADIUS_DIPS = 17;
        public static final int OUTLINE_STROKE_WIDTH_DIPS = 1;

        public static final int BACKGROUND_COLOR = Color.BLACK;
        public static final int BACKGROUND_ALPHA = 51;
        public static final Paint.Style BACKGROUND_STYLE = Paint.Style.FILL;

        public static final int OUTLINE_COLOR = Color.WHITE;
        public static final int OUTLINE_ALPHA = 51;
        public static final Paint.Style OUTLINE_STYLE = Paint.Style.STROKE;

        public static final int SIDE_LENGTH_DIPS = 30;
        public static final int TOP_MARGIN_DIPS = 12;
        public static final int RIGHT_MARGIN_DIPS = 12;
        public static final int PADDING_DIPS = 5;
    }

    public static class CtaRichButton{
        public static final int WIDTH_DIPS = 150;
        public static final int HEIGHT_DIPS = 80;
        public static final int MARGIN_DIPS = 25;
        public static final int CORNER_RADIUS_DIPS = 6;
        public static final int OUTLINE_STROKE_WIDTH_DIPS = 2;
        public static final int ICON_WIDTH_DIPS = 50;
        public static final int ICON_HEIGHT_DIPS = 50;
        public static final int ICON_MARGIN_LEFT = 10;

        public static final int TITLE_HEIGHT_DIPS = 40;
        public static final int TITLE_MARGIN_LEFT_DIPS = 10;
        public static final int TITLE_MARGIN_RIGHT_DIPS = 5;
        public static final int TITLE_MARGIN_BOTTOM_DIPS = 1;
        public static final int TITLE_COLOR = Color.BLACK;
        public static final float TITLE_SIZE_SP = 14f;

        public static final int CTA_HEIGHT_DIPS = 40;
        public static final float CTA_SIZE_SP = 14f;
        public static final int CTA_COLOR = Color.parseColor("#7bc293");
        public static final int CTA_MARGIN_RIGHT_DIPS = 10;
        public static final int CTA_PADDING_DIPS = 10;
        public static final int CTA_BACKGROUND_COLOR = Color.WHITE;
        public static final Paint.Style CTA_BACKGROUND_STYLE = Paint.Style.STROKE;

        public static final int CTA_OUTLINE_COLOR = Color.parseColor("#7bc293");
        public static final Paint.Style CTA_OUTLINE_STYLE = Paint.Style.STROKE;
        public static final int CTA_CORNER_RADIUS_DIPS = 5;

        public static final int DESC_COLOR = Color.parseColor("#999999");
        public static final float DESC_SIZE_SP = 14f;

        public static final float TEXT_SIZE_SP = 15f;

        public static final int BACKGROUND_COLOR = Color.WHITE;
        public static final int BACKGROUND_ALPHA = 51;
        public static final Paint.Style BACKGROUND_STYLE = Paint.Style.FILL;

        public static final int OUTLINE_COLOR = Color.WHITE;
        public static final int OUTLINE_ALPHA = 51;
        public static final Paint.Style OUTLINE_STYLE = Paint.Style.STROKE;

        public static final String DEFAULT_CTA_TEXT = "Learn More";
        public static final Typeface TEXT_TYPEFACE = Typeface.create("Helvetica", Typeface.NORMAL);
        public static final int TEXT_COLOR = Color.BLACK;
        public static final Paint.Align TEXT_ALIGN = Paint.Align.CENTER;
    }

    public static class CtaButton {
        public static final int WIDTH_DIPS = 150;
        public static final int HEIGHT_DIPS = 38;
        public static final int MARGIN_DIPS = 16;
        public static final int PADDING_DIPS = 16;
        public static final int CORNER_RADIUS_DIPS = 6;
        public static final int OUTLINE_STROKE_WIDTH_DIPS = 2;
        public static final float TEXT_SIZE_SP = 15f;

        public static final int BACKGROUND_COLOR = Color.BLACK;
        public static final int BACKGROUND_ALPHA = 51;
        public static final Paint.Style BACKGROUND_STYLE = Paint.Style.FILL;

        public static final int OUTLINE_COLOR = Color.WHITE;
        public static final int OUTLINE_ALPHA = 51;
        public static final Paint.Style OUTLINE_STYLE = Paint.Style.STROKE;

        public static final String DEFAULT_CTA_TEXT = "Learn More";
        public static final Typeface TEXT_TYPEFACE = Typeface.create("Helvetica", Typeface.NORMAL);
        public static final int TEXT_COLOR = Color.WHITE;
        public static final Paint.Align TEXT_ALIGN = Paint.Align.CENTER;
    }
    
    public static class CloseButton {
        public static final int WIDGET_HEIGHT_DIPS = 30;
        public static final int EDGE_PADDING = 6;
        public static final int TOP_MARGIN_DIPS = 12;
        public static final int RIGHT_MARGIN_DIPS = 12;
        public static final int IMAGE_PADDING_DIPS = 15;
        public static final int TEXT_RIGHT_MARGIN_DIPS = 0;
        public static final float TEXT_SIZE_SP = 20f;

        public static final float OUTLINE_STROKE_WIDTH = 1f;

        public static final int STROKE_COLOR = Color.WHITE;
        public static final float STROKE_WIDTH = 2f;
        public static final Paint.Cap STROKE_CAP = Paint.Cap.ROUND;

        public static final String DEFAULT_CLOSE_BUTTON_TEXT = "";
        public static final Typeface TEXT_TYPEFACE = Typeface.create("Helvetica", Typeface.NORMAL);
        public static final int TEXT_COLOR = Color.WHITE;

        public static final int ALERT_DIALOG_CLOSE_COLOR = Color.parseColor("#999999");
        public static final int ALERT_DIALOG_CONTINUE_COLOR = Color.BLACK;

    }
    
    public static class GradientStrip {
        public static final int GRADIENT_STRIP_HEIGHT_DIPS = 72;
        public static final int START_COLOR = Color.argb(102, 0, 0, 0);
        public static final int END_COLOR = Color.argb(0, 0, 0, 0);
    }

    public static class BlurredLastVideoFrame {
        public static final int ALPHA = 100;
    }

    public static class PrivacyInfoIcon {
        public static final int LEFT_MARGIN_DIPS = 12;
        public static final int TOP_MARGIN_DIPS = 12;
    }
}
