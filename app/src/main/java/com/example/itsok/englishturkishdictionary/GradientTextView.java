package com.example.itsok.englishturkishdictionary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.widget.TextView;

public class GradientTextView extends TextView {

    private int colorStartGradient, colorEndGradient, colorShadow;

    public GradientTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.GradientTextView);
        colorShadow = a.getColor(R.styleable.GradientTextView_colorShadowGradient, -2);
        colorStartGradient = a.getColor(R.styleable.GradientTextView_colorStartGradient, -2);
        colorEndGradient = a.getColor(R.styleable.GradientTextView_colorEndGradient, -2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (colorShadow != -2)
        {
            getPaint().setShadowLayer(3, 3, 5, colorShadow);
            getPaint().setShader(null);
            super.onDraw(canvas);
        }
        if (colorStartGradient != -2 && colorEndGradient != -2)
        {
            getPaint().clearShadowLayer();
            getPaint().setShader(
                    new LinearGradient(0, 0, 0, getHeight(), colorStartGradient,
                            colorEndGradient, TileMode.MIRROR));
        }
        super.onDraw(canvas);
    }

}
