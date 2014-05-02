package com.example.demoprogressbutton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.Button;

public class ProgressButton extends Button {

    public static final int TYPE_FILL = 0;
    public static final int TYPE_STROKE = 1;

    private Paint mPaintFillType;
    private Paint mPaintStrokeType;
    private Rect mCanvasRect;
    private Rect mRect;
    private RectF mRectF;
    
    private int mProgress;
    private int mProgressType = TYPE_FILL;
    private int mProgressColor = Color.parseColor("#adff2f");
    private int mProgressAlpha = -1;
    private int mStrokeWidth = 2;
    private boolean mIsFillPadding = false;

    public ProgressButton(Context context) {
        super(context);
        initialize();
    }

    public ProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public ProgressButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }
    
    private void initialize() {
        mPaintFillType = new Paint();
        mPaintFillType.setAntiAlias(true);
        mPaintFillType.setStrokeWidth(1.0f);
        
        mPaintStrokeType = new Paint();
        mPaintStrokeType.setAntiAlias(true);
        
        mCanvasRect = new Rect();
        mRect = new Rect();
        mRectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mProgressType == TYPE_FILL) {
            mPaintFillType.setColor(mProgressColor);
            if(mProgressAlpha < 0) {
                mPaintFillType.setAlpha(128);
            }
            else {
                mPaintFillType.setAlpha(mProgressAlpha);
            }
            
            canvas.getClipBounds(mRect);
            mRect.left += paddingLeft();
            mRect.top += paddingTop();
            mRect.right = (mRect.left - paddingLeft()) + (mProgress * getWidth() / 100) - paddingRight();
            mRect.bottom -= paddingBottom();
            mRectF.set(mRect);
            canvas.drawRoundRect(mRectF, 8.0f, 8.0f, mPaintFillType);
        } else if(mProgressType == TYPE_STROKE) {
            mPaintStrokeType.setColor(mProgressColor);
            if(mProgressAlpha < 0) {
                mPaintStrokeType.setAlpha(255);
            }
            else {
                mPaintStrokeType.setAlpha(mProgressAlpha);
            }
            
            canvas.getClipBounds(mCanvasRect);
            if(mProgress >= 0 && mProgress < 25) {
                mRect.left = mCanvasRect.left + paddingLeft();
                mRect.top = mCanvasRect.top + paddingTop();
                mRect.right = mCanvasRect.left + mProgress * (getWidth() - paddingLeft() - paddingRight()) / 25 - paddingRight();
                mRect.bottom = mCanvasRect.top + paddingTop() + mStrokeWidth;
                canvas.drawRect(mRect, mPaintStrokeType);
            } else if(mProgress < 50) {
                mRect.left = mCanvasRect.left + paddingLeft();
                mRect.top = mCanvasRect.top + paddingTop();
                mRect.right = mCanvasRect.right - paddingRight();
                mRect.bottom = mCanvasRect.top + paddingTop() + mStrokeWidth;
                canvas.drawRect(mRect, mPaintStrokeType);

                mRect.left = mCanvasRect.right - paddingRight() - mStrokeWidth;
                mRect.top = mCanvasRect.top + paddingTop();
                mRect.right = mCanvasRect.right - paddingRight();
                mRect.bottom = mCanvasRect.top + paddingTop() + (mProgress - 25) * (getHeight() - paddingTop() - paddingBottom()) / 25;
                canvas.drawRect(mRect, mPaintStrokeType);
            } else if(mProgress < 75) {
                mRect.left = mCanvasRect.left + paddingLeft();
                mRect.top = mCanvasRect.top + paddingTop();
                mRect.right = mCanvasRect.right - paddingRight();
                mRect.bottom = mCanvasRect.top + paddingTop() + mStrokeWidth;
                canvas.drawRect(mRect, mPaintStrokeType);

                mRect.left = mCanvasRect.right - paddingRight() - mStrokeWidth;
                mRect.top = mCanvasRect.top + paddingTop();
                mRect.right = mCanvasRect.right - paddingRight();
                mRect.bottom = mCanvasRect.bottom - paddingBottom();
                canvas.drawRect(mRect, mPaintStrokeType);

                mRect.left = mCanvasRect.right - paddingRight() - (mProgress - 50) * (getWidth() - paddingLeft() - paddingRight()) / 25;
                mRect.top = mCanvasRect.bottom - paddingBottom() - mStrokeWidth;
                mRect.right = mCanvasRect.right - paddingRight();
                mRect.bottom = mCanvasRect.bottom - paddingBottom();
                canvas.drawRect(mRect, mPaintStrokeType);
            } else if(mProgress <= 100) {
                mRect.left = mCanvasRect.left + paddingLeft();
                mRect.top = mCanvasRect.top + paddingTop();
                mRect.right = mCanvasRect.right - paddingRight();
                mRect.bottom = mCanvasRect.top + paddingTop() + mStrokeWidth;
                canvas.drawRect(mRect, mPaintStrokeType);

                mRect.left = mCanvasRect.right - paddingRight() - mStrokeWidth;
                mRect.top = mCanvasRect.top + paddingTop();
                mRect.right = mCanvasRect.right - paddingRight();
                mRect.bottom = mCanvasRect.bottom - paddingBottom();
                canvas.drawRect(mRect, mPaintStrokeType);

                mRect.left = mCanvasRect.left + paddingLeft();
                mRect.top = mCanvasRect.bottom - paddingBottom() - mStrokeWidth;
                mRect.right = mCanvasRect.right - paddingRight();
                mRect.bottom = mCanvasRect.bottom - paddingBottom();
                canvas.drawRect(mRect, mPaintStrokeType);

                mRect.left = mCanvasRect.left + paddingLeft();
                mRect.top = mCanvasRect.bottom - paddingBottom() - (mProgress - 75) * (getHeight() - paddingTop() - paddingBottom()) / 25;
                mRect.right = mCanvasRect.left + paddingLeft() + mStrokeWidth;
                mRect.bottom = mCanvasRect.bottom - paddingBottom();
                canvas.drawRect(mRect, mPaintStrokeType);
            }
        }

        super.onDraw(canvas);
    }
    
    private int paddingLeft() {
        if(!mIsFillPadding) {
            return getPaddingLeft();
        }
        return 0;
    }
    
    private int paddingRight() {
        if(!mIsFillPadding) {
            return getPaddingRight();
        }
        return 0;
    }
    
    private int paddingTop() {
        if(!mIsFillPadding) {
            return getPaddingTop();
        }
        return 0;
    }
    
    private int paddingBottom() {
        if(!mIsFillPadding) {
            return getPaddingBottom();
        }
        return 0;
    }

    public void setProgress(int progress) {
        if(progress >= 0 && progress <= 100) {
            mProgress = progress;
            invalidate();
        } else if(progress < 0) {
            mProgress = 0;
            invalidate();
        } else if(progress > 100) {
            mProgress = 100;
            invalidate();
        }
    }

    public int getProgress() {
        return mProgress;
    }

    /**
     * Set progress type <br> Default type is TYPE_FILL
     * @param type ProgressButton.TYPE_FILLE or ProgressButton.TYPE_STROKE
     */
    public void setProgressType(int type) {
        if(type == TYPE_FILL || type == TYPE_STROKE)
            mProgressType = type;
        else
            mProgressType = TYPE_FILL;
    }

    public void setProgressColor(int color) {
        mProgressColor = color;
    }
    
    /**
     * Set the alpha of progress <br> Default value is 128 for TYPE_FILL and 255 for TYPE_STROKE
     * @param alpha between 0 and 255 (include 0 and 255) otherwise nothing will happen
     */
    public void setProgressAlpha(int alpha) {
        if(alpha <= 0 || alpha > 255) return;
        mProgressAlpha = alpha;
    }
    
    /**
     * Set the stroke width for stroke type (e.g. the setType(ProgressButton.TYPE_STROKE) has been called)
     * <br> Default width is 2
     * @param width if width <= 0, nothing will happen.
     */
    public void setStrokeWidth(int width) {
        if(width <= 0) return;
        mStrokeWidth = width;
    }
    
    /**
     * Set if the progress bar/stroke occupy the button's padding <br> Default is false
     * @param isFillPadding
     */
    public void setIsFillPadding(boolean isFillPadding) {
        mIsFillPadding = isFillPadding;
    }
}
