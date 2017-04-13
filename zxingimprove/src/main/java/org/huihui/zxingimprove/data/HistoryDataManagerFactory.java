package org.huihui.zxingimprove.data;

import android.content.Context;

public class HistoryDataManagerFactory
{
  private static final Object LOCK;
  private static IHistoryDataManager mHistoryDataManager = null;

  static
  {
    LOCK = new Object();
  }

  public static IHistoryDataManager getInstance(Context paramContext)
  {
    synchronized (LOCK)
    {
      if (mHistoryDataManager == null)
        mHistoryDataManager = new SQLiteHistoryDataManager(paramContext);
      return mHistoryDataManager;
    }
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.data.HistoryDataManagerFactory
 * JD-Core Version:    0.6.0
 */