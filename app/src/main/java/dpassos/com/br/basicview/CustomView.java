package dpassos.com.br.basicview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by djalma on 25/05/2015.
 */
public class CustomView extends View implements View.OnTouchListener{

    private int x, y;
    private SensorManager mSensorManager;

    public CustomView(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);

        canvas.drawCircle(x,y,50, paint);
    }

    public void update(){
        invalidate();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = (int)event.getX();
        y = (int)event.getY();
        invalidate();
        return true;
    }
}