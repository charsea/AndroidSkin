package com.zw.test;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    /**
     * 换肤控件集合
     */
    private List<View> iCcbSkins = Collections.synchronizedList(new LinkedList<View>());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constant.color= android.graphics.Color.GREEN;
                changeSkin(getWindow().getDecorView());
            }
        });
        ColorChangeListener colorChangeListener=new ColorChangeListener();
        findViewById(R.id.btn_red).setOnClickListener(colorChangeListener);
        findViewById(R.id.btn_blue).setOnClickListener(colorChangeListener);
        findViewById(R.id.btn_green).setOnClickListener(colorChangeListener);
    }

    private class ColorChangeListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            int drawingCacheBackgroundColor = ((ColorDrawable)view.getBackground()).getColor();
            Constant.color=drawingCacheBackgroundColor;
            changeSkin(getWindow().getDecorView());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void changeSkin(View rootView){
        iCcbSkins.clear();
        findSkinView(rootView);
        for (View skinView : iCcbSkins) {
            if (skinView instanceof ISkinChange)
                ((ISkinChange) skinView).onSkinChange();

        }
    }

    /**
     * 获取当前界面需要换肤的控件集合
     */
    private void findSkinView(View v) {

        if ((v instanceof ISkinChange && ((ISkinChange) v).isGeneralSkin()) ) {
            iCcbSkins.add(v);
        }
        if (v instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) v;
            for (int i = 0, count = group.getChildCount(); i < count; i++) {
                View child = group.getChildAt(i);
                findSkinView(child);
            }
        }
    }
}
