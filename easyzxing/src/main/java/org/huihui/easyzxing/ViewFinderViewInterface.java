package org.huihui.easyzxing;

import android.graphics.Bitmap;

import com.google.zxing.ResultPoint;

/**
 * User: huihui
 * Date: 2016-12-19 {HOUR}:45
 */
public interface ViewFinderViewInterface {

    void addPossibleResultPoint(ResultPoint point);

    void drawViewfinder();

    void showPreView(Bitmap bitmap);
}