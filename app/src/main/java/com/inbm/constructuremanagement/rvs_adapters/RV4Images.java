package com.inbm.constructuremanagement.rvs_adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.inbm.constructuremanagement.inbm._header_;
import com.inbm.constructuremanagement.inbm.recycler.AbsGridRV;

import java.util.ArrayList;

public class RV4Images extends AbsGridRV {
    public RV4Images(Context context) {
        super(context);
    }

    public RV4Images(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RV4Images(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected int getSpanCount() {
        return 4;
    }

    @Override
    protected ArrayList<_header_> getHeaders() {
        return new ArrayList<_header_>();
    }
}
