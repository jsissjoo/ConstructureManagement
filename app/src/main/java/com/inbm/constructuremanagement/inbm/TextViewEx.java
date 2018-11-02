package com.inbm.constructuremanagement.inbm;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by inbm on 2017. 3. 17..
 */

public class TextViewEx extends TextView {
    public TextViewEx(Context context) {
        super(context);
    }

    public TextViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TextViewEx(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public float getTextWidth(){
        Rect bounds = new Rect();

        getPaint().getTextBounds(getText().toString(), 0, getText().length(), bounds);

        return bounds.width();
    }
}
