package com.zhuyong.postmanloading;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.Random;

/**
 * Created by zhuyong on 2017/7/20.
 */

public class PostManLoadingView extends View {

    private Paint mPaintCircle;
    private Paint mPaintPoint;
    private int mRandius = 40;
    private int mInterval = 50;//空心圆之间的间隔
    //第一个小圆圆心坐标
    private float X1;
    private float Y1;
    //第二个小圆圆心坐标
    private float X2;
    private float Y2;
    //第三个小圆圆心坐标
    private float X3;
    private float Y3;

    public PostManLoadingView(Context context) {
        this(context, null);
    }

    public PostManLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PostManLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaintCircle = new Paint();
        mPaintCircle.setAntiAlias(true);
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setStrokeWidth(3);
        mPaintCircle.setColor(Color.RED);

        mPaintPoint = new Paint();
        mPaintPoint.setAntiAlias(true);
        mPaintPoint.setStyle(Paint.Style.FILL);
        mPaintPoint.setColor(Color.RED);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mWidth = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(mWidth, mWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        //绘制实心圆
        canvas.drawCircle(centerX, centerY, mRandius, mPaintPoint);
        //绘制空心圆
        canvas.drawCircle(centerX, centerY, mRandius + mInterval, mPaintCircle);
        canvas.drawCircle(centerX, centerY, mRandius + mInterval + mInterval, mPaintCircle);
        canvas.drawCircle(centerX, centerY, mRandius + mInterval + mInterval + mInterval, mPaintCircle);
        //绘制滚动的小圆球
        canvas.drawCircle(centerX + X1, centerY + Y1, 20, mPaintPoint);
        canvas.drawCircle(centerX + X2, centerY + Y2, 20, mPaintPoint);
        canvas.drawCircle(centerX + X3, centerY + Y3, 20, mPaintPoint);

    }


    public void start() {

        ValueAnimator animator1 = getValueAnimator(mRandius + mInterval);
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float deg = (float) valueAnimator.getAnimatedValue();
                float rad = (float) (Math.PI * deg / 180);
                Y1 = (float) (Math.sin(rad) * (mRandius + mInterval));
                X1 = (float) (Math.cos(rad) * (mRandius + mInterval));
            }
        });
        animator1.start();
        ValueAnimator animator2 = getValueAnimator(mRandius + mInterval + mInterval);
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float deg = (float) valueAnimator.getAnimatedValue();
                float rad = (float) (Math.PI * deg / 180);
                Y2 = (float) (Math.sin(rad) * (mRandius + mInterval + mInterval));
                X2 = (float) (Math.cos(rad) * (mRandius + mInterval + mInterval));
            }
        });
        animator2.start();
        ValueAnimator animator3 = getValueAnimator(mRandius + mInterval + mInterval + mInterval);
        animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float deg = (float) valueAnimator.getAnimatedValue();
                float rad = (float) (Math.PI * deg / 180);

                Y3 = (float) (Math.sin(rad) * (mRandius + mInterval + mInterval + mInterval));
                X3 = (float) (Math.cos(rad) * (mRandius + mInterval + mInterval + mInterval));
                invalidate();
            }
        });
        animator3.start();
    }

    public ValueAnimator getValueAnimator(final int mRandius) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 360);
        animator.setDuration(mRandius * 30);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setInterpolator(new LinearInterpolator());
        return animator;
    }

}
