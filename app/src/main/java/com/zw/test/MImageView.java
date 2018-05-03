package com.zw.test;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Zheng Wei on 2018/5/3.
 * description:
 */

public class MImageView extends ImageView implements ISkinChange {
    public MImageView(Context context) {
        super(context);
    }

    public MImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onSkinChange() {
        if(isInEditMode()){
            return;
        }
        setBackgroundColor(Constant.color);
    }

    @Override
    public boolean isGeneralSkin() {
        return true;
    }
}
