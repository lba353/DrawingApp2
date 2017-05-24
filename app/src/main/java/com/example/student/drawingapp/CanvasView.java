package com.example.student.drawingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CanvasView extends View {

    private Paint myPaint;
    private Path myPath;
    private Bitmap myBitmap;
    private Canvas myCanvas;
    Context myContext;
    public int width, height;
    private float mX, mY;

    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);
        myContext = c;

        myPath = new Path();
        myPaint = new Paint();
        myPaint.setAntiAlias(true);
        myPaint.setARGB(255, 0, 0, 0); // Alpha, Red, Green, Blue, 0-255
        myPaint.setStyle(Paint.Style.STROKE); // Making a stroke, or line
        myPaint.setStrokeJoin(Paint.Join.ROUND); // Joins segments on rounds.
        myPaint.setStrokeWidth(4f); // Takes a float value for stroke width
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
        myBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        myCanvas = new Canvas(myBitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTouch(x,y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x,y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;
        }
        return true;
    }

    private void startTouch(float startX, float startY){
        myPath.moveTo(startX, startY);
        mX = startX;
        mY = startY;
    }

    private void moveTouch(float moveX, float moveY){
        float deltaX = Math.abs( moveX - mX);
        float deltaY = Math.abs( moveY - mY);
        if ( deltaX >= 5 || deltaY >=5 ){
            myPath.quadTo(mX, mY, (moveX + mX)/2, (moveY + mY) / 2);
            mX = moveX;
            mY = moveY;
        }
    }

    private void upTouch(){
        myPath.lineTo(mX, mY);
    }

    public void clearCanvas() {
        myPath.reset();
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(myPath, myPaint);
    }
}
