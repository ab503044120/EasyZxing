package org.huihui.zxingimprove.util.wifi;

 enum NetworkType
{
  static
  {
    NO_PASSWORD = new NetworkType("NO_PASSWORD", 2);
    NetworkType[] arrayOfNetworkType = new NetworkType[3];
    arrayOfNetworkType[0] = WEP;
    arrayOfNetworkType[1] = WPA;
    arrayOfNetworkType[2] = NO_PASSWORD;
    ENUM$VALUES = arrayOfNetworkType;
  }

  static NetworkType forIntentValue(String paramString)
  {
    if (paramString == null)
      return NO_PASSWORD;
    if ("WPA".equals(paramString))
      return WPA;
    if ("WEP".equals(paramString))
      return WEP;
    if ("nopass".equals(paramString))
      return NO_PASSWORD;
    throw new IllegalArgumentException(paramString);
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.util.wifi.NetworkType
 * JD-Core Version:    0.6.0
 */