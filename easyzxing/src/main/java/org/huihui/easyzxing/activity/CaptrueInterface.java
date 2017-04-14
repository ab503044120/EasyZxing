package org.huihui.easyzxing.activity;

import android.os.Handler;

import org.huihui.easyzxing.ViewFinderViewInterface;
import org.huihui.easyzxing.decoding.BarcodeResult;


/**
 * User: huihui
 * Date: 2016-12-19 {HOUR}:25
 */
public interface CaptrueInterface {
//    public static boolean onlyOneD = true;

    public abstract void handleDecode(BarcodeResult barcode);

    public abstract Handler getHandler();

    public abstract ViewFinderViewInterface getViewfinderView();
}