package org.huihui.zxingimprove.fragment.scanfragmentstack;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.CaptureActivityHandler;
import com.google.zxing.client.android.InactivityTimer;
import com.google.zxing.client.android.ViewfinderView;
import com.google.zxing.client.android.camera.CameraManager;
import com.google.zxing.client.result.AddressBookParsedResult;
import com.google.zxing.client.result.CalendarParsedResult;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.ResultParser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import mobi.thinkchange.android.superqrcode.FeedbackActivity;
import mobi.thinkchange.android.superqrcode.SettingsActivity;
import mobi.thinkchange.android.superqrcode.data.DataCollection;
import mobi.thinkchange.android.superqrcode.data.HistoryDataManagerFactory;
import mobi.thinkchange.android.superqrcode.data.HistoryItem;
import mobi.thinkchange.android.superqrcode.data.HistorySource;
import mobi.thinkchange.android.superqrcode.dialog.NoSensorDialog;
import mobi.thinkchange.android.superqrcode.fragment.IStackableFragment;
import mobi.thinkchange.android.superqrcode.fragment.createfragmentstack.CreateFragmentChild03CreateResult;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;
import mobi.thinkchange.android.superqrcode.util.PreferencesUtils;
import mobi.thinkchange.android.superqrcode.util.StandardExceptionParser;
import mobi.thinkchange.android.superqrcode.util.ToastUtil;
import mobi.thinkchange.android.superqrcode.util.decodeimaage.DecodeImaageHandel;

public final class ScanFragmentChild01Scan extends Fragment
  implements Callback, OnClickListener
{
  public static final String ACTION_CHANGE_TAB = "android.view.tabbar.changeTab";
  public static final String BARCODE_ACTION = "action.barcode_bitmap";
  private static final Set<ResultMetadataType> DISPLAYABLE_METADATA_TYPES = new HashSet(5);
  private static final long VIBRATE_DURATION = 200L;
  public static AddressBookParsedResult addressBookParsedResult;
  public static CalendarParsedResult calendarResult;
  public static String displayResult;
  public static Result lastResult;
  public static ParsedResult result_P;
  private File barCodeFile;
  private String characterSet;
  private boolean copyToClipboard;
  private int crop = 180;
  private DataCollection dataCollection;
  private Vector<BarcodeFormat> decodeFormats;
  DecodeImaageBroadcast decodeImaageBroadcast;
  private String decodeType;
  private Display display;
  private long end;
  private ImageView feedbackButton;
  private ImageView flashButton;
  private CaptureActivityHandler handler;
  private boolean hasFlashlight;
  private boolean hasSurface;
  private InactivityTimer inactivityTimer;
  private boolean isOpenCamera = false;
  private Activity mContext;
  private MediaPlayer mediaPlayer;
  private MiscUtils miscUtils;
  private ImageView photoButton;
  private PreferencesUtils preferencesUtils;
  private String returnUrlTemplate;
  private long scanTime;
  private ImageView settingsButton;
  private Source source;
  private long start;
  private TextView titleText;
  private ToastUtil toastUtil;
  private boolean vibrate = true;
  private ViewfinderView viewfinderView;

  static
  {
    DISPLAYABLE_METADATA_TYPES.add(ResultMetadataType.ISSUE_NUMBER);
    DISPLAYABLE_METADATA_TYPES.add(ResultMetadataType.SUGGESTED_PRICE);
    DISPLAYABLE_METADATA_TYPES.add(ResultMetadataType.ERROR_CORRECTION_LEVEL);
    DISPLAYABLE_METADATA_TYPES.add(ResultMetadataType.POSSIBLE_COUNTRY);
  }

  private static void drawLine(Canvas paramCanvas, Paint paramPaint, ResultPoint paramResultPoint1, ResultPoint paramResultPoint2)
  {
    paramCanvas.drawLine(paramResultPoint1.getX(), paramResultPoint1.getY(), paramResultPoint2.getX(), paramResultPoint2.getY(), paramPaint);
  }

  private void drawResultPoints(Bitmap paramBitmap, Result paramResult)
  {
    int i = 0;
    ResultPoint[] arrayOfResultPoint = paramResult.getResultPoints();
    Canvas localCanvas;
    Paint localPaint;
    if ((arrayOfResultPoint != null) && (arrayOfResultPoint.length > 0))
    {
      localCanvas = new Canvas(paramBitmap);
      localPaint = new Paint();
      localPaint.setColor(getResources().getColor(2131165226));
      localPaint.setStrokeWidth(3.0F);
      localPaint.setStyle(Style.STROKE);
      localCanvas.drawRect(new Rect(2, 2, -2 + paramBitmap.getWidth(), -2 + paramBitmap.getHeight()), localPaint);
      localPaint.setColor(getResources().getColor(2131165228));
      if (arrayOfResultPoint.length != 2)
        break label145;
      localPaint.setStrokeWidth(4.0F);
      drawLine(localCanvas, localPaint, arrayOfResultPoint[0], arrayOfResultPoint[1]);
    }
    while (true)
    {
      return;
      label145: if (((arrayOfResultPoint.length == 4) && (paramResult.getBarcodeFormat().equals(BarcodeFormat.UPC_A))) || (paramResult.getBarcodeFormat().equals(BarcodeFormat.EAN_13)))
      {
        drawLine(localCanvas, localPaint, arrayOfResultPoint[0], arrayOfResultPoint[1]);
        drawLine(localCanvas, localPaint, arrayOfResultPoint[2], arrayOfResultPoint[3]);
        return;
      }
      localPaint.setStrokeWidth(10.0F);
      int j = arrayOfResultPoint.length;
      while (i < j)
      {
        ResultPoint localResultPoint = arrayOfResultPoint[i];
        localCanvas.drawPoint(localResultPoint.getX(), localResultPoint.getY(), localPaint);
        i++;
      }
    }
  }

  private void getScanType(ParsedResultType paramParsedResultType)
  {
    String str;
    switch ($SWITCH_TABLE$com$google$zxing$client$result$ParsedResultType()[paramParsedResultType.ordinal()])
    {
    default:
      str = "5";
    case 4:
    case 2:
    case 10:
    case 1:
    case 5:
    case 8:
    case 6:
    case 3:
    case 7:
    case 11:
    case 9:
    }
    while (true)
    {
      this.dataCollection.setScanResultsType(str);
      return;
      str = "1";
      continue;
      str = "2";
      continue;
      str = "3";
      continue;
      str = "4";
      continue;
      str = "5";
      continue;
      str = "6";
      continue;
      str = "7";
      continue;
      str = "8";
      continue;
      str = "9";
      continue;
      str = "10";
      continue;
      str = "11";
    }
  }

  private void handleDecodeExternally(Result paramResult, Bitmap paramBitmap)
  {
    this.viewfinderView.drawResultBitmap(paramBitmap);
  }

  private void handleDecodeInternally(Result paramResult, Bitmap paramBitmap)
  {
    this.viewfinderView.setVisibility(8);
    String str = paramResult.getText();
    if (this.copyToClipboard)
      ((ClipboardManager)this.mContext.getSystemService("clipboard")).setText(str);
  }

  private boolean hasFlashlight()
  {
    boolean bool = false;
    PackageManager localPackageManager = this.mContext.getPackageManager();
    FeatureInfo[] arrayOfFeatureInfo = localPackageManager.getSystemAvailableFeatures();
    int i = arrayOfFeatureInfo.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return bool;
      if (!"android.hardware.camera.flash".equals(arrayOfFeatureInfo[j].name))
        continue;
      bool = localPackageManager.hasSystemFeature("android.hardware.camera.flash");
    }
  }

  private void initCamera(SurfaceHolder paramSurfaceHolder)
  {
    try
    {
      CameraManager.get().openDriver(paramSurfaceHolder);
      this.dataCollection.setCameraIsOpen("1");
      this.dataCollection.setCameraOpenExceptionInfo("");
    }
    catch (Exception localException1)
    {
      try
      {
        if (this.handler == null)
          this.handler = new CaptureActivityHandler(this, this.decodeFormats, this.characterSet);
        return;
        localException1 = localException1;
        ArrayList localArrayList = new ArrayList();
        StandardExceptionParser localStandardExceptionParser = new StandardExceptionParser(this.mContext, localArrayList);
        String str1 = Thread.currentThread().getName();
        String str2 = localStandardExceptionParser.getDescription(str1, localException1);
        try
        {
          if (localException1.getMessage().equals("Fail to connect to camera service"))
            MiscUtils.showSnackBar(getActivity(), getResources().getString(2131493010), getResources().getString(2131492871));
          while (true)
          {
            Log.e("ioe", str2);
            this.dataCollection.setCameraIsOpen("0");
            this.dataCollection.setCameraOpenExceptionInfo(str2);
            return;
            new NoSensorDialog(this.mContext).show();
          }
        }
        catch (Exception localException2)
        {
          while (true)
          {
            str2 = localStandardExceptionParser.getDescription(str1, localException2);
            new NoSensorDialog(this.mContext).show();
          }
        }
      }
      catch (Exception localException3)
      {
        MiscUtils.showSnackBar(getActivity(), getResources().getString(2131493010), getResources().getString(2131492871));
      }
    }
  }

  private void onDecoded(Result paramResult, ParsedResult paramParsedResult, HistorySource paramHistorySource)
  {
    displayResult = paramParsedResult.getDisplayResult();
    String str = paramParsedResult.getType().toString();
    Log.e("result.getType()", str);
    paramResult.getBarcodeFormat().toString();
    getScanType(paramParsedResult.getType());
    HistoryItem localHistoryItem = new HistoryItem();
    localHistoryItem.setType(paramParsedResult.getType());
    localHistoryItem.setName(CreateFragmentChild03CreateResult.getDefaultName(paramParsedResult.getType()));
    localHistoryItem.setBarcodeFormat(paramResult.getBarcodeFormat());
    localHistoryItem.setRawText(paramResult.getText());
    localHistoryItem.setDisplayString(paramParsedResult.getDisplayResult());
    localHistoryItem.setSource(paramHistorySource);
    long l = HistoryDataManagerFactory.getInstance(getActivity()).addItem(localHistoryItem);
    IStackableFragment localIStackableFragment = (IStackableFragment)getParentFragment();
    Bundle localBundle = new Bundle();
    Fragment localFragment = ScanFragmentChild02Factory.sacanTargetFragment(paramParsedResult.getType());
    localBundle.putSerializable(str, paramParsedResult);
    localBundle.putLong("history_item_id", l);
    localFragment.setArguments(localBundle);
    localIStackableFragment.addFragmentToStack(localFragment);
  }

  private void playVibrate()
  {
    Vibrator localVibrator = (Vibrator)this.mContext.getSystemService("vibrator");
    if (this.vibrate)
      localVibrator.vibrate(200L);
  }

  private void resetStatusView()
  {
    this.viewfinderView.setVisibility(0);
    lastResult = null;
  }

  private void sendParams()
  {
    this.end = System.currentTimeMillis();
    this.scanTime = ((this.end - this.start) / 1000L);
    this.dataCollection.setScanTime(this.scanTime);
  }

  public void drawViewfinder()
  {
    this.viewfinderView.drawViewfinder();
  }

  public Handler getHandler()
  {
    return this.handler;
  }

  public ViewfinderView getViewfinderView()
  {
    return this.viewfinderView;
  }

  public void handleDecode(Result paramResult, Bitmap paramBitmap, HistorySource paramHistorySource)
  {
    switch ($SWITCH_TABLE$mobi$thinkchange$android$superqrcode$data$HistorySource()[paramHistorySource.ordinal()])
    {
    default:
      this.decodeType = "0";
    case 1:
    case 2:
    }
    while (true)
    {
      this.inactivityTimer.onActivity();
      lastResult = paramResult;
      result_P = ResultParser.parseResult(paramResult);
      result_P.getType();
      onDecoded(paramResult, result_P, paramHistorySource);
      this.miscUtils.playSound();
      playVibrate();
      if (paramBitmap != null)
        break;
      handleDecodeInternally(paramResult, null);
      return;
      this.decodeType = "1";
      continue;
      this.decodeType = "2";
    }
    drawResultPoints(paramBitmap, paramResult);
    switch ($SWITCH_TABLE$mobi$thinkchange$android$superqrcode$fragment$scanfragmentstack$ScanFragmentChild01Scan$Source()[this.source.ordinal()])
    {
    default:
      return;
    case 1:
    case 2:
      handleDecodeExternally(paramResult, paramBitmap);
      return;
    case 3:
      if (this.returnUrlTemplate == null)
      {
        handleDecodeInternally(paramResult, paramBitmap);
        return;
      }
      handleDecodeExternally(paramResult, paramBitmap);
      return;
    case 4:
    }
    handleDecodeInternally(paramResult, paramBitmap);
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131624005)
      if (this.hasFlashlight)
        if (!this.isOpenCamera)
        {
          this.flashButton.setImageResource(2130837585);
          this.isOpenCamera = true;
          CameraManager.openLight();
        }
    do
    {
      return;
      this.flashButton.setImageResource(2130837584);
      this.isOpenCamera = false;
      CameraManager.closeLight();
      return;
      this.toastUtil.getBgToastInstance(getResources().getString(2131493009));
      return;
      if (paramView.getId() == 2131624004)
      {
        Intent localIntent = new Intent("android.intent.action.PICK");
        localIntent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
        localIntent.putExtra("crop", "true");
        localIntent.putExtra("output", Uri.fromFile(this.barCodeFile));
        localIntent.putExtra("aspectX", 1);
        localIntent.putExtra("aspectY", 1);
        localIntent.putExtra("outputX", this.crop);
        localIntent.putExtra("outputY", this.crop);
        getActivity().startActivityForResult(localIntent, 1234);
        return;
      }
      if (paramView.getId() != 2131624191)
        continue;
      this.dataCollection.setClickFeedbackButton("1");
      MiscUtils.startActivity(this.mContext, FeedbackActivity.class, "scan");
      return;
    }
    while (paramView.getId() != 2131624190);
    MiscUtils.startActivity(this.mContext, SettingsActivity.class, null);
  }

  @SuppressLint({"SdCardPath"})
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mContext = getActivity();
    this.preferencesUtils = new PreferencesUtils(this.mContext);
    this.dataCollection = ((DataCollection)this.mContext.getApplicationContext());
    this.hasFlashlight = hasFlashlight();
    this.toastUtil = new ToastUtil(this.mContext);
    this.barCodeFile = new File(Environment.getExternalStorageDirectory(), "barcode_pic.jpg");
    this.decodeImaageBroadcast = new DecodeImaageBroadcast();
    IntentFilter localIntentFilter = new IntentFilter("action.barcode_bitmap");
    this.mContext.registerReceiver(this.decodeImaageBroadcast, localIntentFilter);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    Log.e("onCreateView", "1111");
    View localView = paramLayoutInflater.inflate(2130903050, paramViewGroup, false);
    this.mContext.getWindow().addFlags(128);
    this.display = this.mContext.getWindowManager().getDefaultDisplay();
    CameraManager.init(this.mContext.getApplication(), this.display.getWidth(), this.display.getHeight());
    this.titleText = ((TextView)localView.findViewById(2131624016));
    this.titleText.setText(getResources().getString(2131493005));
    this.settingsButton = ((ImageView)localView.findViewById(2131624190));
    this.settingsButton.setOnClickListener(this);
    this.feedbackButton = ((ImageView)localView.findViewById(2131624191));
    this.feedbackButton.setOnClickListener(this);
    this.viewfinderView = ((ViewfinderView)localView.findViewById(2131624003));
    this.flashButton = ((ImageView)localView.findViewById(2131624005));
    this.flashButton.setOnClickListener(this);
    this.photoButton = ((ImageView)localView.findViewById(2131624004));
    this.photoButton.setOnClickListener(this);
    this.handler = null;
    lastResult = null;
    this.hasSurface = false;
    this.inactivityTimer = new InactivityTimer(this.mContext);
    this.start = System.currentTimeMillis();
    return localView;
  }

  public void onDestroy()
  {
    this.mContext.unregisterReceiver(this.decodeImaageBroadcast);
    super.onDestroy();
  }

  public void onDestroyView()
  {
    this.inactivityTimer.shutdown();
    super.onDestroyView();
  }

  public void onPause()
  {
    super.onPause();
    sendParams();
    Log.e("onPause", "1111");
    if (this.handler != null)
    {
      this.handler.quitSynchronously();
      this.handler = null;
    }
    if (this.isOpenCamera)
    {
      this.flashButton.setImageResource(2130837584);
      this.isOpenCamera = false;
      CameraManager.closeLight();
    }
    CameraManager.get().closeDriver();
  }

  public void onResume()
  {
    super.onResume();
    this.miscUtils = new MiscUtils(this.mContext);
    this.vibrate = this.preferencesUtils.getIsOpenVibrate();
    this.dataCollection.setScanResultsType("");
    this.decodeType = "";
    this.dataCollection.setImageDecode("");
    resetStatusView();
    SurfaceHolder localSurfaceHolder = ((SurfaceView)this.mContext.findViewById(2131624002)).getHolder();
    if (this.hasSurface)
      initCamera(localSurfaceHolder);
    while (true)
    {
      this.source = Source.NONE;
      this.decodeFormats = null;
      this.characterSet = null;
      this.miscUtils.initSound(2131099648);
      return;
      localSurfaceHolder.addCallback(this);
      localSurfaceHolder.setType(3);
    }
  }

  public void onStart()
  {
    super.onStart();
  }

  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    if (!this.hasSurface)
    {
      this.hasSurface = true;
      initCamera(paramSurfaceHolder);
    }
  }

  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
    this.hasSurface = false;
  }

  class DecodeImaageBroadcast extends BroadcastReceiver
  {
    DecodeImaageBroadcast()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      Result localResult = new DecodeImaageHandel(paramContext, (Bitmap)paramIntent.getExtras().getParcelable("barcode_bitmap")).getResult();
      if (localResult != null)
      {
        ScanFragmentChild01Scan.this.handleDecode(localResult, null, HistorySource.SCAN_FROM_IMAGE);
        ScanFragmentChild01Scan.this.dataCollection.setImageDecode("1");
        ScanFragmentChild01Scan.this.dataCollection.setImageDecodeExceptionInfo("");
        return;
      }
      ScanFragmentChild01Scan.this.dataCollection.setImageDecode("2");
      ScanFragmentChild01Scan.this.toastUtil.getBgToastInstance(ScanFragmentChild01Scan.this.mContext.getResources().getString(2131493073));
    }
  }

  private static enum Source
  {
    static
    {
      NONE = new Source("NONE", 3);
      Source[] arrayOfSource = new Source[4];
      arrayOfSource[0] = NATIVE_APP_INTENT;
      arrayOfSource[1] = PRODUCT_SEARCH_LINK;
      arrayOfSource[2] = ZXING_LINK;
      arrayOfSource[3] = NONE;
      ENUM$VALUES = arrayOfSource;
    }
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.scanfragmentstack.ScanFragmentChild01Scan
 * JD-Core Version:    0.6.0
 */