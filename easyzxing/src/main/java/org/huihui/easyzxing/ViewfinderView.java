/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.huihui.easyzxing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.google.zxing.ResultPoint;

import org.huihui.easyzxing.camera.CameraManager;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.PixelFormat.OPAQUE;


/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder
 * rectangle and partial transparency outside it, as well as the laser scanner
 * animation and result points.
 */
public final class ViewfinderView extends View implements ViewFinderViewInterface {
    private static final long ANIMATION_DELAY = 10L;
    private static int BORDER_WIDTH;
    private static int BORDER_HEIGHT;
    private static int LASER_HEIGHT;
    private static int LASER_PADDING;
    private static int LASER_OFFSET_INCREASE;
    protected static final int MAX_RESULT_POINTS = 20;
    private Paint mFinderMaskPaint;
    private Paint mBorderPaint;
    private Paint mLaserPaint;
    private Drawable mLaserDrawable;
    private final int mMaskColor;
    private final int mFrameColor;
    private final int mLaserColor;
    private int mLaserOffset = 0;
    private List<ResultPoint> possibleResultPoints;
    private List<ResultPoint> lastPossibleResultPoints;
    private final int resultPointColor = 0xc0ffff00;
    private final Paint dotPaint;
    private Bitmap resultBitmap;

    // This constructor is used when the class is built from an XML resource.
    public ViewfinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Initialize these once for performance rather than calling them every time in onDraw().
        BORDER_WIDTH = dp2px(16);
        BORDER_HEIGHT = dp2px(2);
        LASER_HEIGHT = dp2px(2);
        LASER_PADDING = dp2px(0);
        LASER_OFFSET_INCREASE = dp2px(1);

        mFinderMaskPaint = new Paint();
        mBorderPaint = new Paint();
        mLaserPaint = new Paint();
        mLaserDrawable = getResources().getDrawable(R.drawable.icon_saotao);
        mMaskColor = getResources().getColor(R.color.viewfinder_mask);
        mFrameColor = 0xffcd2525;//getResources().getColor(R.color.viewfinder_frame);
        mLaserColor = 0xffcd2525;//getResources().getColor(R.color.viewfinder_laser);

        mFinderMaskPaint.setColor(mMaskColor);
        mBorderPaint.setColor(mFrameColor);
        mBorderPaint.setStrokeWidth(BORDER_HEIGHT);
        mLaserPaint.setColor(mLaserColor);
        mLaserPaint.setStrokeWidth(dp2px(1.1f));

        possibleResultPoints = new ArrayList<>(5);
        dotPaint = new Paint();
    }

    @Override
    public void onDraw(Canvas canvas) {
        Rect frame = CameraManager.get().getFramingRect();
        if (frame == null) {
            return;
        }

        drawViewFinderMask(canvas, frame);
        drawViewFinderBorder(canvas, frame);
        drawLaser(canvas, frame);
        if (resultBitmap != null) {
            // Draw the opaque result bitmap over the scanning rectangle
            dotPaint.setAlpha(OPAQUE);
            canvas.drawBitmap(resultBitmap, frame.left, frame.top, dotPaint);
        } else {
            List<ResultPoint> currentPossible = possibleResultPoints;
            List<ResultPoint> currentLast = lastPossibleResultPoints;
            if (currentPossible.isEmpty()) {
                lastPossibleResultPoints = null;
            } else {
                possibleResultPoints = new ArrayList<ResultPoint>(5);
                lastPossibleResultPoints = currentPossible;
                dotPaint.setAlpha(OPAQUE);
                dotPaint.setColor(resultPointColor);
                for (ResultPoint point : currentPossible) {
                    canvas.drawCircle(frame.left + point.getX(), frame.top + point.getY(), 6.0f, dotPaint);
                }
            }
            if (currentLast != null) {
                dotPaint.setAlpha(OPAQUE / 2);
                dotPaint.setColor(resultPointColor);
                for (ResultPoint point : currentLast) {
                    canvas.drawCircle(frame.left + point.getX(), frame.top + point.getY(), 3.0f, dotPaint);
                }
            }

            postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top, frame.right, frame.bottom);
        }
    }


    public void drawViewfinder() {
        resultBitmap = null;
        invalidate();
    }

    @Override
    public void showPreView(Bitmap bitmap) {
        resultBitmap = bitmap;
    }

    public void addPossibleResultPoint(ResultPoint point) {
        List<ResultPoint> points = possibleResultPoints;
        points.add(point);
        int size = points.size();
        if (size > MAX_RESULT_POINTS) {
            // trim it
            points.subList(0, size - MAX_RESULT_POINTS / 2).clear();
        }
    }

    private void drawViewFinderMask(Canvas canvas, Rect frame) {
        canvas.drawRect(0, 0, canvas.getWidth(), frame.top, mFinderMaskPaint);
        canvas.drawRect(0, frame.top, frame.left, frame.bottom, mFinderMaskPaint);
        canvas.drawRect(frame.right, frame.top, canvas.getWidth(), frame.bottom, mFinderMaskPaint);
        canvas.drawRect(0, frame.bottom, canvas.getWidth(), canvas.getHeight(), mFinderMaskPaint);
    }

    /**
     * 画四轴边框
     *
     * @param canvas
     * @param frame
     */
    private void drawViewFinderBorder(Canvas canvas, Rect frame) {
        int halfStroke = BORDER_HEIGHT / 2;
        canvas.drawLine((float) (frame.left + halfStroke), (float) (frame.top), (float) (frame.left + halfStroke), (float) (frame.top + BORDER_WIDTH), mBorderPaint);
        canvas.drawLine((float) (frame.left), (float) (frame.top + halfStroke), (float) (frame.left + BORDER_WIDTH), (float) (frame.top + halfStroke), mBorderPaint);
        canvas.drawLine((float) (frame.left + halfStroke), (float) (frame.bottom), (float) (frame.left + halfStroke), (float) (frame.bottom - BORDER_WIDTH), mBorderPaint);
        canvas.drawLine((float) (frame.left), (float) (frame.bottom - halfStroke), (float) (frame.left + BORDER_WIDTH), (float) (frame.bottom - halfStroke), mBorderPaint);
        canvas.drawLine((float) (frame.right - halfStroke), (float) (frame.top), (float) (frame.right - halfStroke), (float) (frame.top + BORDER_WIDTH), mBorderPaint);
        canvas.drawLine((float) (frame.right), (float) (frame.top + halfStroke), (float) (frame.right - BORDER_WIDTH), (float) (frame.top + halfStroke), mBorderPaint);
        canvas.drawLine((float) (frame.right - halfStroke), (float) (frame.bottom), (float) (frame.right - halfStroke), (float) (frame.bottom - BORDER_WIDTH), mBorderPaint);
        canvas.drawLine((float) (frame.right), (float) (frame.bottom - halfStroke), (float) (frame.right - BORDER_WIDTH), (float) (frame.bottom - halfStroke), mBorderPaint);
    }

    private void drawLaser(Canvas canvas, Rect frame) {
        if (mLaserOffset + LASER_HEIGHT >= frame.height()) {
            mLaserOffset = 0;
        }
        canvas.drawLine(frame.left + LASER_PADDING, frame.top + mLaserOffset, frame.right - LASER_PADDING, frame.top + mLaserOffset + LASER_HEIGHT, mLaserPaint);
//        mLaserDrawable.setBounds(frame.left + LASER_PADDING, frame.top + mLaserOffset, frame.right - LASER_PADDING, frame.top + mLaserOffset + LASER_HEIGHT);
//        mLaserDrawable.draw(canvas);
        mLaserOffset += LASER_OFFSET_INCREASE;
    }

    private int dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
