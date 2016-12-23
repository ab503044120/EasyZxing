package org.huihui.easyzxing.activity;

import android.graphics.Bitmap;
import android.os.Handler;

import com.google.zxing.Result;

import org.huihui.easyzxing.ViewFinderViewInterface;


/**
 * User: huihui
 * Date: 2016-12-19 {HOUR}:25
 */
public interface CaptrueInterface {
//    public static boolean onlyOneD = true;

    public abstract void handleDecode(Result result, Bitmap barcode);

    public abstract Handler getHandler();

    public abstract ViewFinderViewInterface getViewfinderView();
}