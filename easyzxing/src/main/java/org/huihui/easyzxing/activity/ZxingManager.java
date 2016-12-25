package org.huihui.easyzxing.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.SurfaceHolder;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import org.huihui.easyzxing.R;
import org.huihui.easyzxing.ViewFinderViewInterface;
import org.huihui.easyzxing.camera.BeepManager;
import org.huihui.easyzxing.camera.CameraManager;
import org.huihui.easyzxing.decoding.CaptureActivityHandler;
import org.huihui.easyzxing.decoding.InactivityTimer;

import java.io.IOException;
import java.util.Vector;

/**
 * User: Administrator
 * Date: 2016-12-23 {HOUR}:25
 */
public class ZxingManager implements CaptrueInterface, SurfaceHolder.Callback {
    private SurfaceHolder mSurfaceHolder;
    private Activity mContext;

    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    ViewFinderViewInterface mViewFinderViewInterface;

    private CaptureActivityHandler handler;
    private BeepManager mBeepManager;

    private ZxingManagerListener mZxingManagerListener = null;
    //初始化
    private boolean isInit;
    //surfaceview创建完成
    private boolean isSurfaceCreated;
    //camera准备
    private boolean isPrepare;
    //是否只支持条码  false:支持条码和二维码 true:只支持条码
    private boolean isOneD;
    private boolean autoShutDown;
    private InactivityTimer mInactivityTimer;

    /**
     * @param context                 activity 必要
     * @param viewFinderViewInterface 刷新的viev
     * @param surfaceHolder           surfaceholder
     * @param isOneD                  false:支持条码和二维码 true:只支持条码
     * @param autoShutDown            支持长时间自动关闭
     */
    public ZxingManager(Activity context, ViewFinderViewInterface viewFinderViewInterface, SurfaceHolder surfaceHolder, boolean isOneD, boolean autoShutDown) {
        mContext = context;
        this.mViewFinderViewInterface = viewFinderViewInterface;
        this.mSurfaceHolder = surfaceHolder;
        this.isOneD = isOneD;
        decodeFormats = null;
        characterSet = "UTF-8";
        this.autoShutDown = autoShutDown;
    }

    public ZxingManager(Activity context, Vector<BarcodeFormat> decodeFormats, String characterSet, ViewFinderViewInterface viewFinderViewInterface
            , SurfaceHolder surfaceHolder) {
        mContext = context;
        this.mViewFinderViewInterface = viewFinderViewInterface;
        this.decodeFormats = decodeFormats;
        this.characterSet = characterSet;
        this.mSurfaceHolder = surfaceHolder;
    }

    public void init() {
        if (!isInit) {
            isInit = true;
            if (mBeepManager == null) {
                mBeepManager = new BeepManager(mContext);
                mBeepManager.init();
            }
            if (autoShutDown) {
                mInactivityTimer = new InactivityTimer(mContext);
            }
            CameraManager.init(mContext.getApplicationContext(), isOneD);
            if (mSurfaceHolder == null) {
                //如果没有sufaceview
                prepare();
            } else {
                mSurfaceHolder.addCallback(this);
            }
        }

    }

    private void prepare() {
        try {
            CameraManager.get().openDriver(mSurfaceHolder);
        } catch (IOException | RuntimeException ioe) {
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats, characterSet);
        }
    }

    public void onPause() {
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    public void onResume() {
        if (isSurfaceCreated) {
            prepare();
        }
    }

    public void release() {
        mBeepManager.release();
        if (mInactivityTimer != null) {
            mInactivityTimer.shutdown();
        }
        onPause();
    }

    public boolean toggleFlashlight() {
        return CameraManager.get().flashlight();
    }

    @Override
    public void handleDecode(Result result, Bitmap barcode) {
        mBeepManager.playBeepSoundAndVibrate();
        if (mInactivityTimer != null) {
            mInactivityTimer.onActivity();
        }
        if (mViewFinderViewInterface != null) {
            mViewFinderViewInterface.showPreView(barcode);
        }
        if (mZxingManagerListener != null) {
            mZxingManagerListener.onSuccess(result, barcode);
        }
    }

    /**
     * 扫描完一张以后重新开始再扫
     */
    public void restartScan(long delay) {
        handler.sendEmptyMessageDelayed(R.id.restart_preview, delay);
    }

    @Override
    public Handler getHandler() {
        return handler;
    }

    @Override
    public ViewFinderViewInterface getViewfinderView() {
        return mViewFinderViewInterface;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!isSurfaceCreated) {
            prepare();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isSurfaceCreated = false;
    }

    public void setZxingManagerListener(ZxingManagerListener zxingManagerListener) {
        mZxingManagerListener = zxingManagerListener;
    }

    public interface ZxingManagerListener {
        void onSuccess(Result result, Bitmap barcode);
    }

}