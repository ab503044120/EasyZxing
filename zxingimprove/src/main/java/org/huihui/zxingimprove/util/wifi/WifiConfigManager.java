package org.huihui.zxingimprove.util.wifi;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.google.zxing.client.result.WifiParsedResult;
import java.util.BitSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WifiConfigManager extends AsyncTask<WifiParsedResult, Object, Object>
{
  private static final Pattern HEX_DIGITS = Pattern.compile("[0-9A-Fa-f]+");
  private static final String TAG = "WifiConfigManager";
  private final WifiManager mWifiManager;

  public WifiConfigManager(WifiManager paramWifiManager)
  {
    this.mWifiManager = paramWifiManager;
  }

  private static WifiConfiguration changeNetworkCommon(WifiParsedResult paramWifiParsedResult)
  {
    WifiConfiguration localWifiConfiguration = new WifiConfiguration();
    localWifiConfiguration.allowedAuthAlgorithms.clear();
    localWifiConfiguration.allowedGroupCiphers.clear();
    localWifiConfiguration.allowedKeyManagement.clear();
    localWifiConfiguration.allowedPairwiseCiphers.clear();
    localWifiConfiguration.allowedProtocols.clear();
    localWifiConfiguration.SSID = quoteNonHex(paramWifiParsedResult.getSsid(), new int[0]);
    localWifiConfiguration.hiddenSSID = paramWifiParsedResult.isHidden();
    return localWifiConfiguration;
  }

  private static void changeNetworkUnEncrypted(WifiManager paramWifiManager, WifiParsedResult paramWifiParsedResult)
  {
    WifiConfiguration localWifiConfiguration = changeNetworkCommon(paramWifiParsedResult);
    localWifiConfiguration.allowedKeyManagement.set(0);
    updateNetwork(paramWifiManager, localWifiConfiguration);
  }

  private static void changeNetworkWEP(WifiManager paramWifiManager, WifiParsedResult paramWifiParsedResult)
  {
    WifiConfiguration localWifiConfiguration = changeNetworkCommon(paramWifiParsedResult);
    localWifiConfiguration.wepKeys[0] = quoteNonHex(paramWifiParsedResult.getPassword(), new int[] { 10, 26, 58 });
    localWifiConfiguration.wepTxKeyIndex = 0;
    localWifiConfiguration.allowedAuthAlgorithms.set(1);
    localWifiConfiguration.allowedKeyManagement.set(0);
    localWifiConfiguration.allowedGroupCiphers.set(2);
    localWifiConfiguration.allowedGroupCiphers.set(3);
    localWifiConfiguration.allowedGroupCiphers.set(0);
    localWifiConfiguration.allowedGroupCiphers.set(1);
    updateNetwork(paramWifiManager, localWifiConfiguration);
  }

  private static void changeNetworkWPA(WifiManager paramWifiManager, WifiParsedResult paramWifiParsedResult)
  {
    WifiConfiguration localWifiConfiguration = changeNetworkCommon(paramWifiParsedResult);
    localWifiConfiguration.preSharedKey = quoteNonHex(paramWifiParsedResult.getPassword(), new int[] { 64 });
    localWifiConfiguration.allowedAuthAlgorithms.set(0);
    localWifiConfiguration.allowedProtocols.set(0);
    localWifiConfiguration.allowedProtocols.set(1);
    localWifiConfiguration.allowedKeyManagement.set(1);
    localWifiConfiguration.allowedKeyManagement.set(2);
    localWifiConfiguration.allowedPairwiseCiphers.set(1);
    localWifiConfiguration.allowedPairwiseCiphers.set(2);
    localWifiConfiguration.allowedGroupCiphers.set(2);
    localWifiConfiguration.allowedGroupCiphers.set(3);
    updateNetwork(paramWifiManager, localWifiConfiguration);
  }

  private static String convertToQuotedString(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      paramString = null;
    do
      return paramString;
    while ((paramString.charAt(0) == '"') && (paramString.charAt(-1 + paramString.length()) == '"'));
    return '"' + paramString + '"';
  }

  private static Integer findNetworkInExistingConfig(WifiManager paramWifiManager, String paramString)
  {
    Iterator localIterator = paramWifiManager.getConfiguredNetworks().iterator();
    WifiConfiguration localWifiConfiguration;
    String str;
    do
    {
      if (!localIterator.hasNext())
        return null;
      localWifiConfiguration = (WifiConfiguration)localIterator.next();
      str = localWifiConfiguration.SSID;
    }
    while ((str == null) || (!str.equals(paramString)));
    return Integer.valueOf(localWifiConfiguration.networkId);
  }

  private static boolean isHexOfLength(CharSequence paramCharSequence, int[] paramArrayOfInt)
  {
    int i = 1;
    if ((paramCharSequence == null) || (!HEX_DIGITS.matcher(paramCharSequence).matches()))
      i = 0;
    do
      return i;
    while (paramArrayOfInt.length == 0);
    int j = paramArrayOfInt.length;
    for (int k = 0; ; k++)
    {
      if (k >= j)
        return false;
      int m = paramArrayOfInt[k];
      if (paramCharSequence.length() == m)
        break;
    }
  }

  private static String quoteNonHex(String paramString, int[] paramArrayOfInt)
  {
    if (isHexOfLength(paramString, paramArrayOfInt))
      return paramString;
    return convertToQuotedString(paramString);
  }

  private static void updateNetwork(WifiManager paramWifiManager, WifiConfiguration paramWifiConfiguration)
  {
    Integer localInteger = findNetworkInExistingConfig(paramWifiManager, paramWifiConfiguration.SSID);
    if (localInteger != null)
    {
      Log.e("WifiConfigManager", "Removing old configuration for network " + paramWifiConfiguration.SSID);
      paramWifiManager.removeNetwork(localInteger.intValue());
      paramWifiManager.saveConfiguration();
    }
    int i = paramWifiManager.addNetwork(paramWifiConfiguration);
    if (i >= 0)
    {
      if (paramWifiManager.enableNetwork(i, true))
      {
        Log.e("WifiConfigManager", "Associating to network " + paramWifiConfiguration.SSID);
        paramWifiManager.saveConfiguration();
        return;
      }
      Log.e("WifiConfigManager", "Failed to enable network " + paramWifiConfiguration.SSID);
      return;
    }
    Log.e("WifiConfigManager", "Unable to add network " + paramWifiConfiguration.SSID);
  }

  // ERROR //
  protected Object doInBackground(WifiParsedResult[] paramArrayOfWifiParsedResult)
  {
    // Byte code:
    //   0: aload_1
    //   1: iconst_0
    //   2: aaload
    //   3: astore_2
    //   4: aload_0
    //   5: getfield 31	mobi/thinkchange/android/superqrcode/util/wifi/WifiConfigManager:mWifiManager	Landroid/net/wifi/WifiManager;
    //   8: invokevirtual 239	android/net/wifi/WifiManager:isWifiEnabled	()Z
    //   11: ifne +43 -> 54
    //   14: ldc 11
    //   16: ldc 241
    //   18: invokestatic 206	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   21: pop
    //   22: aload_0
    //   23: getfield 31	mobi/thinkchange/android/superqrcode/util/wifi/WifiConfigManager:mWifiManager	Landroid/net/wifi/WifiManager;
    //   26: iconst_1
    //   27: invokevirtual 245	android/net/wifi/WifiManager:setWifiEnabled	(Z)Z
    //   30: ifeq +53 -> 83
    //   33: ldc 11
    //   35: ldc 247
    //   37: invokestatic 206	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   40: pop
    //   41: iconst_0
    //   42: istore 10
    //   44: aload_0
    //   45: getfield 31	mobi/thinkchange/android/superqrcode/util/wifi/WifiConfigManager:mWifiManager	Landroid/net/wifi/WifiManager;
    //   48: invokevirtual 239	android/net/wifi/WifiManager:isWifiEnabled	()Z
    //   51: ifeq +43 -> 94
    //   54: aload_2
    //   55: invokevirtual 250	com/google/zxing/client/result/WifiParsedResult:getNetworkEncryption	()Ljava/lang/String;
    //   58: astore_3
    //   59: aload_3
    //   60: invokestatic 256	mobi/thinkchange/android/superqrcode/util/wifi/NetworkType:forIntentValue	(Ljava/lang/String;)Lmobi/thinkchange/android/superqrcode/util/wifi/NetworkType;
    //   63: astore 6
    //   65: aload 6
    //   67: getstatic 260	mobi/thinkchange/android/superqrcode/util/wifi/NetworkType:NO_PASSWORD	Lmobi/thinkchange/android/superqrcode/util/wifi/NetworkType;
    //   70: if_acmpne +90 -> 160
    //   73: aload_0
    //   74: getfield 31	mobi/thinkchange/android/superqrcode/util/wifi/WifiConfigManager:mWifiManager	Landroid/net/wifi/WifiManager;
    //   77: aload_2
    //   78: invokestatic 262	mobi/thinkchange/android/superqrcode/util/wifi/WifiConfigManager:changeNetworkUnEncrypted	(Landroid/net/wifi/WifiManager;Lcom/google/zxing/client/result/WifiParsedResult;)V
    //   81: aconst_null
    //   82: areturn
    //   83: ldc 11
    //   85: ldc_w 264
    //   88: invokestatic 206	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   91: pop
    //   92: aconst_null
    //   93: areturn
    //   94: iload 10
    //   96: bipush 10
    //   98: if_icmplt +14 -> 112
    //   101: ldc 11
    //   103: ldc_w 266
    //   106: invokestatic 206	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   109: pop
    //   110: aconst_null
    //   111: areturn
    //   112: ldc 11
    //   114: ldc_w 268
    //   117: invokestatic 206	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   120: pop
    //   121: ldc2_w 269
    //   124: invokestatic 276	java/lang/Thread:sleep	(J)V
    //   127: iinc 10 1
    //   130: goto -86 -> 44
    //   133: astore 4
    //   135: ldc 11
    //   137: new 126	java/lang/StringBuilder
    //   140: dup
    //   141: ldc_w 278
    //   144: invokespecial 133	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   147: aload_3
    //   148: invokevirtual 137	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: invokevirtual 143	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   154: invokestatic 206	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   157: pop
    //   158: aconst_null
    //   159: areturn
    //   160: aload_2
    //   161: invokevirtual 98	com/google/zxing/client/result/WifiParsedResult:getPassword	()Ljava/lang/String;
    //   164: invokestatic 114	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   167: ifne -86 -> 81
    //   170: aload 6
    //   172: getstatic 281	mobi/thinkchange/android/superqrcode/util/wifi/NetworkType:WEP	Lmobi/thinkchange/android/superqrcode/util/wifi/NetworkType;
    //   175: if_acmpne +13 -> 188
    //   178: aload_0
    //   179: getfield 31	mobi/thinkchange/android/superqrcode/util/wifi/WifiConfigManager:mWifiManager	Landroid/net/wifi/WifiManager;
    //   182: aload_2
    //   183: invokestatic 283	mobi/thinkchange/android/superqrcode/util/wifi/WifiConfigManager:changeNetworkWEP	(Landroid/net/wifi/WifiManager;Lcom/google/zxing/client/result/WifiParsedResult;)V
    //   186: aconst_null
    //   187: areturn
    //   188: aload 6
    //   190: getstatic 286	mobi/thinkchange/android/superqrcode/util/wifi/NetworkType:WPA	Lmobi/thinkchange/android/superqrcode/util/wifi/NetworkType;
    //   193: if_acmpne -112 -> 81
    //   196: aload_0
    //   197: getfield 31	mobi/thinkchange/android/superqrcode/util/wifi/WifiConfigManager:mWifiManager	Landroid/net/wifi/WifiManager;
    //   200: aload_2
    //   201: invokestatic 288	mobi/thinkchange/android/superqrcode/util/wifi/WifiConfigManager:changeNetworkWPA	(Landroid/net/wifi/WifiManager;Lcom/google/zxing/client/result/WifiParsedResult;)V
    //   204: aconst_null
    //   205: areturn
    //   206: astore 12
    //   208: goto -81 -> 127
    //
    // Exception table:
    //   from	to	target	type
    //   59	65	133	java/lang/IllegalArgumentException
    //   121	127	206	java/lang/InterruptedException
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.util.wifi.WifiConfigManager
 * JD-Core Version:    0.6.0
 */