package com.zw.test;

import android.animation.AnimatorListenerAdapter;
import android.animation.FloatEvaluator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Zheng Wei on 2018/4/18.
 * description:
 */

public class MyView extends View {
    private static final String TAG = "MyView";
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private int count = 3;//总 边形数
    private int bcount = 6;//bcount边形
    private float maxLinesize = 500;//最长边到中心点的位置
    private float animatedValue;
    private boolean hasLoad;

    public MyView(Context context) {
        super(context);
        Log.d(TAG, "MyView() called with: context = [" + context + "]");
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "MyView() called with: context = [" + context + "], attrs = [" + attrs + "]");
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "MyView() called with: context = [" + context + "], attrs = [" + attrs + "], defStyleAttr = [" + defStyleAttr + "]");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPaint == null) {
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setStrokeWidth(5);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.RED);
        }
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawPoint(0, 0, mPaint);
//        Path path = new Path();

//        for (int i = 0; i < count; i++) {
//            float l = ((float) (i + 1)) / count * maxLinesize;//得到当前边形角到中心点的距离
//            //path.reset();
//            for (int j = 0; j < bcount; j++) {
//                if (j == 0) {
//                    path.moveTo(l, 0);
//                } else {
//                    float x = (float) (Math.cos(Math.PI * 2 * j / bcount) * l);
//                    float y = (float) (Math.sin(Math.PI * 2 * j / bcount) * l);
//                    path.lineTo(x, y);
//                }
//            }
//            path.close();
//            canvas.drawPath(path, mPaint);
//        }
//        Path path = new Path();
//
//        path.addRect(-100, -100, 100, 100, Path.Direction.CW);  // 添加小矩形
//        path.addRect(-200, -200, 200, 200, Path.Direction.CW);  // 添加大矩形
//
//        //canvas.drawPath(path,mPaint);                    // 绘制 Path
//
//        PathMeasure measure = new PathMeasure(path, false);     // 将Path与PathMeasure关联
//
//        float len1 = measure.getLength();                       // 获得第一条路径的长度
//
//        measure.nextContour();                                  // 跳转到下一条路径
//
//        float len2 = measure.getLength();                       // 获得第二条路径的长度
//
//        Log.i("LEN","len1="+len1);                              // 输出两条路径的长度
//        Log.i("LEN","len2="+len2);

        Path path = new Path();
        path.addCircle( animatedValue*50, 0, 200, Path.Direction.CW);
//        PathMeasure measure = new PathMeasure(path, false);
//        Path srcPath = new Path();
//        boolean segment = measure.getSegment(10, 200, srcPath, true);
//        Log.d(TAG, "onDraw: " + segment + "length=" + measure.getLength());
        canvas.drawPath(path, mPaint);


//        Path path = new Path();                                     // 创建Path并添加了一个矩形
//        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
//        Path dst = new Path();                                      // 创建用于存储截取后内容的 Path
//        PathMeasure measure = new PathMeasure(path, false);         // 将 Path 与 PathMeasure 关联
//       截取一部分存入dst中，并使用 moveTo 保持截取得到的 Path 第一个点的位置不变
//        measure.getSegment(200, 600, dst, true);
//
//        canvas.drawPath(dst, mPaint);
        if(!hasLoad){
            hasLoad=true;
            ValueAnimator valueAnim =ValueAnimator.ofFloat(0,3f);
            valueAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    animatedValue = (float) valueAnimator.getAnimatedValue();
                    Log.d(TAG, "ValueAnimator = [" + animatedValue + "]");
                    invalidate();
                }
            });
            valueAnim.addListener(new AnimatorListenerAdapter() {

            });
            valueAnim.setDuration(2000);

            valueAnim.start();
        }

    }



}
