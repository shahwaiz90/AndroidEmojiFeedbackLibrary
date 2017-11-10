package github.shahwaiz90.emojifeedback.views;

/**
 * Created by Ahmad Shahwaiz on 11/9/2017.
 * Github: https://www.github.com/shahwaiz90
 * LinkedIn: https://www.linkedin.com/in/ahmadshahwaiz/
 */


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import github.shahwaiz90.emojifeedback.model.SnowFlake;

/**
 * This class is directly called from feedback_screen.xml
 */

public class SnowView extends View {
    private static final int NUM_SNOWFLAKES = 80;
    private static final int DELAY = 20;
    Context currentContext;
    private SnowFlake[] snowflakes;

    public SnowView(Context context) {
        super(context);
        currentContext = context;
    }

    public SnowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SnowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void resize(int width, int height) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#FF8F00"));
        paint.setStyle(Paint.Style.FILL);
        snowflakes = new SnowFlake[NUM_SNOWFLAKES];
        for (int i = 0; i < NUM_SNOWFLAKES; i++) {
            snowflakes[i] = SnowFlake.create(width, height, paint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw || h != oldh) {
            resize(w, h);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (SnowFlake snowFlake : snowflakes) {
            snowFlake.draw(canvas);
        }
        getHandler().postDelayed(runnable, DELAY);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

}
