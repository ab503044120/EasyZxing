package org.huihui.zxingimprove.util.wifi;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.util.Log;
import java.util.List;

public class WifiAdmin
{
  private static final String TAG = "WifiAdmin";
  private List<ScanResult> listResult;
  private ScanResult mScanResult;
  private StringBuffer mStringBuffer = new StringBuffer();
  private List<WifiConfiguration> mWifiConfiguration;
  private WifiInfo mWifiInfo;
  WifiLock mWifiLock;
  private WifiManager mWifiManager;

  public WifiAdmin(Context paramContext)
  {
    this.mWifiManager = ((WifiManager)paramContext.getSystemService("wifi"));
    this.mWifiInfo = this.mWifiManager.getConnectionInfo();
  }

  public static boolean wifiIsOpen(Context paramContext)
  {
    return ((WifiManager)paramContext.getSystemService("wifi")).isWifiEnabled();
  }

  public void checkNetWorkState()
  {
    if (this.mWifiInfo != null)
    {
      Log.e("WifiAdmin", "网络正常工作");
      return;
    }
    Log.e("WifiAdmin", "网络已断开");
  }

  public void closeWifi(Activity paramActivity)
  {
    WifiManager localWifiManager = (WifiManager)paramActivity.getSystemService("wifi");
    if (localWifiManager.isWifiEnabled())
      localWifiManager.setWifiEnabled(false);
  }

  public void disconnectWifi()
  {
    int i = getNetworkId();
    this.mWifiManager.disableNetwork(i);
    this.mWifiManager.disconnect();
    this.mWifiInfo = null;
  }

  public int getNetworkId()
  {
    if (this.mWifiInfo == null)
      return 0;
    return this.mWifiInfo.getNetworkId();
  }

  public boolean hasWifi(Activity paramActivity)
  {
    return ((ConnectivityManager)paramActivity.getSystemService("connectivity")).getNetworkInfo(1) != null;
  }

  public boolean wifiIsConnected(Activity paramActivity)
  {
    return ((ConnectivityManager)paramActivity.getSystemService("connectivity")).getNetworkInfo(1).isConnected();
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.util.wifi.WifiAdmin
 * JD-Core Version:    0.6.0
 */