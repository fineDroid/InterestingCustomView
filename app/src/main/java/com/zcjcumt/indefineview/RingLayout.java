package com.zcjcumt.indefineview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.findroid.interestingview.view.RingView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RingLayout extends LinearLayout {
    private RingView mRingView;

    public RingLayout(Context context) {
        this(context, null);
    }

    public RingLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RingLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        mRingView = new RingView(context);
        final RingView.Builder builder = new RingView.Builder(mRingView);
        builder.setStrokeWidth(20).setPaintColor(Color.parseColor("#FF4040")).build();
        Observable.interval(1, 2, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                builder.addCustom(aLong).build();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        addView(mRingView, params);
    }

    private static float dpToPx(float dp) {
        return dp * ((float) Resources.getSystem().getDisplayMetrics().densityDpi / 160.0F);
    }

}
