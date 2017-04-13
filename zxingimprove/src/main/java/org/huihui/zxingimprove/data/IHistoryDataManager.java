package org.huihui.zxingimprove.data;

import com.google.zxing.client.result.ParsedResultType;
import java.util.List;

public abstract interface IHistoryDataManager
{
  public abstract long addItem(HistoryItem paramHistoryItem);

  public abstract boolean deleteAll();

  public abstract boolean deleteItem(long paramLong, HistorySource paramHistorySource, ParsedResultType paramParsedResultType, boolean paramBoolean);

  public abstract List<HistoryCounter> getHistoryCounters();

  public abstract List<HistoryItem> getHistoryItems(int paramInt, HistorySource[] paramArrayOfHistorySource);

  public abstract boolean isFavorite(long paramLong);

  public abstract boolean setFavorite(long paramLong, boolean paramBoolean);

  public abstract boolean setName(long paramLong, String paramString);
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.data.IHistoryDataManager
 * JD-Core Version:    0.6.0
 */