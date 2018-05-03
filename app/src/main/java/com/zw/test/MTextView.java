package com.zw.test;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Zheng Wei on 2018/5/3.
 * description:
 */

public class MTextView extends TextView implements ISkinChange {
    public   boolean isSkined=true;
    public MTextView(Context context) {
        super(context);
        init(context,null);
    }

    public MTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public MTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MTextView);
        isSkined=typedArray.getBoolean(R.styleable.MTextView_isSkin,false);
    }

    @Override
    public void onSkinChange() {
        if(isInEditMode()){
            return;
        }
        if(isSkined){
            setTextColor(Constant.color);
        }
    }

    @Override
    public boolean isGeneralSkin() {
        return isSkined;
    }

    public void setSkinColor(int color){
        isSkined=true;
        Constant.color=color;
        onSkinChange();
    }


}
