package com.inbm.constructuremanagement.rvs_adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.inbm.constructuremanagement.inbm.recycler.AbsVerticalRV;

public class RV4Report extends AbsVerticalRV {
    public RV4Report(Context context) {
        super(context);
    }

    public RV4Report(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RV4Report(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected int getOffset() {
        return 0;
    }
}
