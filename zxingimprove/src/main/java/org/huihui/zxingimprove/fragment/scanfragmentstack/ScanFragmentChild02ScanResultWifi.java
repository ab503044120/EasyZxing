package org.huihui.zxingimprove.fragment.scanfragmentstack;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.WifiParsedResult;
import mobi.thinkchange.android.superqrcode.dialog.WifiIsNotDialog;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;
import mobi.thinkchange.android.superqrcode.util.wifi.WifiAdmin;
import mobi.thinkchange.android.superqrcode.util.wifi.WifiConfigManager;

public class ScanFragmentChild02ScanResultWifi extends AbstractScanFragmentChild02Scan
{
  private static final String TAG = ScanFragmentChild02ScanResultWifi.class.getSimpleName();
  private SweetAlertDialog connectDilog;
  private SweetAlertDialog failedDialog;
  private boolean isCancelConnect = false;
  private boolean isShowPassword = false;
  private boolean mEnable = true;
  private String networkEncryption = "";
  private TextView networkEncryptionTextView;
  private String password = "";
  private TextView passwordContent;
  private ImageView passwordEye;
  private String shareText;
  private String ssid = "";
  private TextView ssidTextView;
  private SweetAlertDialog successfulDialog;
  private int timeOut = 20;
  private WifiAdmin wifiAdmin;
  private WifiConfigManager wifiConfigManager;
  private Handler wifiHandler;
  private SweetAlertDialog wifiOpenDialog;
  private WifiParsedResult wifiParsedResult;
  private WifiStateThread wifiThread;

  private void connectWifi()
  {
    this.wifiAdmin.disconnectWifi();
    startWifiStateThread();
    WifiManager localWifiManager = (WifiManager)getActivity().getSystemService("wifi");
    if (localWifiManager == null)
    {
      Log.e(TAG, "手机没有WIFI");
      return;
    }
    this.wifiConfigManager = new WifiConfigManager(localWifiManager);
    WifiConfigManager localWifiConfigManager = this.wifiConfigManager;
    WifiParsedResult[] arrayOfWifiParsedResult = new WifiParsedResult[1];
    arrayOfWifiParsedResult[0] = this.wifiParsedResult;
    localWifiConfigManager.execute(arrayOfWifiParsedResult);
  }

  private void init()
  {
    this.wifiThread = new WifiStateThread();
    if (this.wifiParsedResult.getSsid() != null)
      this.ssid = this.wifiParsedResult.getSsid();
    if (this.wifiParsedResult.getPassword() != null)
      this.password = this.wifiParsedResult.getPassword();
    if (this.wifiParsedResult.getNetworkEncryption() != null)
      this.networkEncryption = this.wifiParsedResult.getNetworkEncryption();
    this.ssidTextView.setText(getResources().getString(2131493026) + this.ssid);
    this.networkEncryptionTextView.setText(getResources().getString(2131493027) + this.networkEncryption);
    this.passwordContent.setText(this.password);
    this.passwordContent.setTransformationMethod(PasswordTransformationMethod.getInstance());
    this.shareText = (getResources().getString(2131493026) + this.ssid + "\n" + getResources().getString(2131493027) + this.networkEncryption + "\n" + getResources().getString(2131493028) + this.password);
  }

  private void initView(View paramView)
  {
    this.ssidTextView = ((TextView)paramView.findViewById(2131624172));
    this.networkEncryptionTextView = ((TextView)paramView.findViewById(2131624174));
    this.passwordContent = ((TextView)paramView.findViewById(2131624178));
    this.passwordEye = ((ImageView)paramView.findViewById(2131624177));
    this.passwordEye.setOnClickListener(new OnClickListener()
    {
      public void onClick(View paramView)
      {
        if (!ScanFragmentChild02ScanResultWifi.this.isShowPassword)
        {
          ScanFragmentChild02ScanResultWifi.this.passwordContent.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
          ScanFragmentChild02ScanResultWifi.this.isShowPassword = true;
          ScanFragmentChild02ScanResultWifi.this.passwordEye.setImageResource(2130837692);
          return;
        }
        ScanFragmentChild02ScanResultWifi.this.passwordContent.setTransformationMethod(PasswordTransformationMethod.getInstance());
        ScanFragmentChild02ScanResultWifi.this.isShowPassword = false;
        ScanFragmentChild02ScanResultWifi.this.passwordEye.setImageResource(2130837691);
      }
    });
  }

  @SuppressLint({"HandlerLeak"})
  private void startWifiStateThread()
  {
    this.connectDilog = new SweetAlertDialog(this.mContext);
    this.connectDilog.setTitleText("提示");
    this.connectDilog.setConfirmText(getResources().getString(2131492869));
    this.connectDilog.show();
    this.connectDilog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener()
    {
      public void onClick(SweetAlertDialog paramSweetAlertDialog)
      {
        ScanFragmentChild02ScanResultWifi.this.isCancelConnect = true;
        ScanFragmentChild02ScanResultWifi.this.wifiConfigManager.cancel(true);
        ScanFragmentChild02ScanResultWifi.this.timeOut = 20;
        ScanFragmentChild02ScanResultWifi.this.stopWifiStateThread();
        ScanFragmentChild02ScanResultWifi.this.connectDilog.dismiss();
      }
    });
    this.wifiHandler = new Handler()
    {
      public void handleMessage(Message paramMessage)
      {
        ScanFragmentChild02ScanResultWifi localScanFragmentChild02ScanResultWifi = ScanFragmentChild02ScanResultWifi.this;
        localScanFragmentChild02ScanResultWifi.timeOut = (-1 + localScanFragmentChild02ScanResultWifi.timeOut);
        Log.e(ScanFragmentChild02ScanResultWifi.TAG, (Boolean)paramMessage.obj);
        if (((Boolean)paramMessage.obj).booleanValue())
        {
          Log.e(ScanFragmentChild02ScanResultWifi.TAG, "WIFI连接成功");
          ScanFragmentChild02ScanResultWifi.this.stopWifiStateThread();
          if (!ScanFragmentChild02ScanResultWifi.this.isCancelConnect)
          {
            ScanFragmentChild02ScanResultWifi.this.connectDilog.dismiss();
            ScanFragmentChild02ScanResultWifi.this.successfulDialog = new SweetAlertDialog(ScanFragmentChild02ScanResultWifi.this.mContext, 2);
            ScanFragmentChild02ScanResultWifi.this.successfulDialog.setTitleText("提示");
            ScanFragmentChild02ScanResultWifi.this.successfulDialog.setContentText(ScanFragmentChild02ScanResultWifi.this.getResources().getString(2131493036));
            ScanFragmentChild02ScanResultWifi.this.successfulDialog.setConfirmText(ScanFragmentChild02ScanResultWifi.this.getResources().getString(2131493037));
            ScanFragmentChild02ScanResultWifi.this.successfulDialog.showCancelButton(true).show();
            ScanFragmentChild02ScanResultWifi.this.successfulDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener()
            {
              public void onClick(SweetAlertDialog paramSweetAlertDialog)
              {
                MiscUtils.openBrowser(ScanFragmentChild02ScanResultWifi.this.mContext, ScanFragmentChild02ScanResultWifi.this.getResources().getString(2131492997));
                ScanFragmentChild02ScanResultWifi.this.successfulDialog.dismiss();
              }
            });
          }
        }
        while (true)
        {
          if (ScanFragmentChild02ScanResultWifi.this.timeOut <= 0)
          {
            ScanFragmentChild02ScanResultWifi.this.stopWifiStateThread();
            ScanFragmentChild02ScanResultWifi.this.connectDilog.dismiss();
            ScanFragmentChild02ScanResultWifi.this.failedDialog = new SweetAlertDialog(ScanFragmentChild02ScanResultWifi.this.mContext, 1);
            ScanFragmentChild02ScanResultWifi.this.failedDialog.setTitleText("提示");
            ScanFragmentChild02ScanResultWifi.this.failedDialog.setContentText(ScanFragmentChild02ScanResultWifi.this.getResources().getString(2131493038));
            ScanFragmentChild02ScanResultWifi.this.failedDialog.show();
            Log.e(ScanFragmentChild02ScanResultWifi.TAG, "WIFI连接失败");
          }
          return;
          ScanFragmentChild02ScanResultWifi.this.connectDilog.setContentText(ScanFragmentChild02ScanResultWifi.this.getResources().getString(2131493035) + "  " + ScanFragmentChild02ScanResultWifi.this.timeOut + "s");
          Log.e(ScanFragmentChild02ScanResultWifi.TAG, "WIFI断开连接");
        }
      }
    };
    new Thread(this.wifiThread).start();
  }

  private void stopWifiStateThread()
  {
    if (this.mEnable)
      this.mEnable = false;
  }

  protected void clickBottomButton()
  {
    this.isCancelConnect = false;
    this.mEnable = true;
    if (!this.wifiAdmin.hasWifi(this.mContext))
    {
      new WifiIsNotDialog(this.mContext).show();
      return;
    }
    if (WifiAdmin.wifiIsOpen(this.mContext))
    {
      connectWifi();
      return;
    }
    this.wifiOpenDialog = new SweetAlertDialog(this.mContext, 3);
    this.wifiOpenDialog.showCancelButton(true).show();
    this.wifiOpenDialog.setTitleText("提示");
    this.wifiOpenDialog.setContentText(getResources().getString(2131493034));
    this.wifiOpenDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener()
    {
      public void onClick(SweetAlertDialog paramSweetAlertDialog)
      {
        ScanFragmentChild02ScanResultWifi.this.connectWifi();
        ScanFragmentChild02ScanResultWifi.this.wifiOpenDialog.dismiss();
      }
    });
  }

  protected int getBottomButtonStringResource()
  {
    return 2131493024;
  }

  protected int getLayoutResource()
  {
    return 2130903089;
  }

  protected void getParsedResult(ParsedResult paramParsedResult)
  {
    this.wifiParsedResult = ((WifiParsedResult)paramParsedResult);
  }

  protected int getResultImageResource()
  {
    return 2130837690;
  }

  protected ParsedResultType getScanType()
  {
    return ParsedResultType.WIFI;
  }

  protected String getShareText()
  {
    return this.shareText;
  }

  protected int getTitleStringResource()
  {
    return 2131493015;
  }

  protected void initChildData()
  {
    init();
  }

  protected void initChildView(View paramView)
  {
    initView(paramView);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.wifiAdmin = new WifiAdmin(this.mContext);
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    stopWifiStateThread();
    if (this.connectDilog != null)
      this.connectDilog.dismiss();
  }

  public void onPause()
  {
    super.onPause();
    Log.e("ResultWifiFragment_onPause", "111111111");
  }

  class WifiStateThread
    implements Runnable
  {
    WifiStateThread()
    {
    }

    public void run()
    {
      while (true)
      {
        if (!ScanFragmentChild02ScanResultWifi.this.mEnable)
          return;
        try
        {
          boolean bool = ScanFragmentChild02ScanResultWifi.this.wifiAdmin.wifiIsConnected(ScanFragmentChild02ScanResultWifi.this.mContext);
          ScanFragmentChild02ScanResultWifi.this.wifiHandler.sendMessage(ScanFragmentChild02ScanResultWifi.this.wifiHandler.obtainMessage(100, Boolean.valueOf(bool)));
          Thread.sleep(1000L);
          if (ScanFragmentChild02ScanResultWifi.this.mEnable)
            continue;
          Thread.sleep(2000L);
          ScanFragmentChild02ScanResultWifi.this.timeOut = 20;
          Log.e(ScanFragmentChild02ScanResultWifi.TAG, "Exit");
          return;
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException.printStackTrace();
        }
      }
    }
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.scanfragmentstack.ScanFragmentChild02ScanResultWifi
 * JD-Core Version:    0.6.0
 */