package com.karljamoralin.popularmovies;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by jamorali on 10/26/2016.
 */

public class AwesomeImageView extends ImageView {
    public AwesomeImageView(Context context) {
        super(context);
    }

    public AwesomeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AwesomeImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), (int) (getMeasuredWidth() * 1.5)); //Snap to width
    }
}