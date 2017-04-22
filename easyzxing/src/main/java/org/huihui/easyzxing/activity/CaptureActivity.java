package org.huihui.easyzxing.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import org.huihui.easyzxing.R;
import org.huihui.easyzxing.ViewfinderView;
import org.huihui.easyzxing.camera.BeepManager;
import org.huihui.easyzxing.decoding.CaptureActivityHandler;
import org.huihui.easyzxing.decoding.InactivityTimer;
import org.huihui.easyzxing.decoding.RGBLuminanceSource;

import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Vector;


/**
 * Initial the camera
 *
 * @author Ryan.Tang
 */
public class CaptureActivity extends AppCompatActivity implements OnClickListener {
    private static final int REQUEST_ALBUM = 0;
    private CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private boolean vibrate;
    private ImageView ivBack;
    private ImageView ivFlashlight;
    private ImageView ivAlbum;
    private boolean onlyOneD;
    private BeepManager mBeepManager;
    private ZxingManager mZxingManager;


    public static void start(Activity activity, boolean onlyOneD, int requestCode) {
        Intent intent = new Intent(activity, CaptureActivity.class);
        intent.putExtra(Extras.ONLY_ONE_D, onlyOneD);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void start(Fragment fragment, boolean onlyOneD, int requestCode) {
        Intent intent = new Intent(fragment.getActivity(), CaptureActivity.class);
        intent.putExtra(Extras.ONLY_ONE_D, onlyOneD);
        fragment.startActivityForResult(intent, requestCode);
    }
    public static void start(android.support.v4.app.Fragment fragment, boolean onlyOneD, int requestCode) {
        Intent intent = new Intent(fragment.getActivity(), CaptureActivity.class);
        intent.putExtra(Extras.ONLY_ONE_D, onlyOneD);
        fragment.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);

        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivAlbum = (ImageView) findViewById(R.id.iv_album);
        ivFlashlight = (ImageView) findViewById(R.id.iv_flashlight);

        onlyOneD = getIntent().getBooleanExtra(Extras.ONLY_ONE_D, false);
        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
        inactivityTimer = new InactivityTimer(this);
        ivBack.setOnClickListener(this);
        ivFlashlight.setOnClickListener(this);
        ivAlbum.setOnClickListener(this);
        ivAlbum.setVisibility(View.GONE);
        SurfaceView view = (SurfaceView) findViewById(R.id.preview_view);
        mZxingManager = new ZxingManager(this, viewfinderView, view.getHolder(), false, true);
        mZxingManager.init();
        mZxingManager.setZxingManagerListener(new ZxingManager.ZxingManagerListener() {
            @Override
            public void onSuccess(Result result, Bitmap barcode) {
                handleDecode(result, barcode);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mZxingManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mZxingManager.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mZxingManager.release();
    }

    /**
     * Handler scan result
     */
    public void handleDecode(Result result, Bitmap barcode) {
        mZxingManager.restartScan(1000);
//        if (TextUtils.isEmpty(result.getText())) {
//            Toast.makeText(this, "扫描失败", Toast.LENGTH_SHORT).show();
//            finish();
//        } else {
//            handleScanResult(result.getText());
//        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            finish();
        } else if (v.getId() == R.id.iv_flashlight) {
            ivFlashlight.setSelected(mZxingManager.toggleFlashlight());
        } else if (v.getId() == R.id.iv_album) {
            openAlbum();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_ALBUM) {
            Uri uri = data.getData();
            parseBitmap(uri);
        }
    }

    private void openAlbum() {
        Intent innerIntent = new Intent();
        innerIntent.setAction(Intent.ACTION_PICK);
        innerIntent.setType("image/*");
        startActivityForResult(innerIntent, REQUEST_ALBUM);
    }

    private void parseBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true; // 仅获取大小
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);
            int inSampleSize = options.outHeight / 200;// 压缩尺寸,节约时间
            if (inSampleSize <= 0) {
                inSampleSize = 1;
            }
            options.inSampleSize = inSampleSize;
            options.inJustDecodeBounds = false; // 获取bitmap
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Hashtable<DecodeHintType, String> hints = new Hashtable<>();
        hints.put(DecodeHintType.CHARACTER_SET, characterSet);
        RGBLuminanceSource source = new RGBLuminanceSource(bitmap);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
        MultiFormatReader reader = new MultiFormatReader();
        Result result = null;
        try {
            result = reader.decode(binaryBitmap, hints);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        if (result == null || TextUtils.isEmpty(result.getText())) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.tips)
                    .setMessage(R.string.analyze_fail)
                    .setPositiveButton(R.string.sure, null)
                    .show();
        } else {
            handleScanResult(result.getText());
        }
    }

    private void handleScanResult(final String result) {
        Intent intent = new Intent();
        intent.putExtra(Extras.SCAN_RESULT, result);
        setResult(RESULT_OK, intent);
        finish();
    }
}