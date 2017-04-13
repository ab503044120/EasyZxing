package org.huihui.zxingimprove.data;

public enum HistorySource
{
  static
  {
    CREATE = new HistorySource("CREATE", 2);
    HistorySource[] arrayOfHistorySource = new HistorySource[3];
    arrayOfHistorySource[0] = SCAN_FROM_CAMERA;
    arrayOfHistorySource[1] = SCAN_FROM_IMAGE;
    arrayOfHistorySource[2] = CREATE;
    ENUM$VALUES = arrayOfHistorySource;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.data.HistorySource
 * JD-Core Version:    0.6.0
 */