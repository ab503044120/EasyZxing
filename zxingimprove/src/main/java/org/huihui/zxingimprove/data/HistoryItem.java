package org.huihui.zxingimprove.data;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.result.ParsedResultType;

public class HistoryItem
{
  BarcodeFormat barcodeFormat;
  String displayString;
  boolean favorite;
  long id;
  String name;
  String rawText;
  HistorySource source;
  long time;
  ParsedResultType type;

  public BarcodeFormat getBarcodeFormat()
  {
    return this.barcodeFormat;
  }

  public String getDisplayString()
  {
    return this.displayString;
  }

  public long getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  public String getRawText()
  {
    return this.rawText;
  }

  public HistorySource getSource()
  {
    return this.source;
  }

  public long getTime()
  {
    return this.time;
  }

  public ParsedResultType getType()
  {
    return this.type;
  }

  public boolean isFavorite()
  {
    return this.favorite;
  }

  public void setBarcodeFormat(BarcodeFormat paramBarcodeFormat)
  {
    this.barcodeFormat = paramBarcodeFormat;
  }

  public void setDisplayString(String paramString)
  {
    this.displayString = paramString;
  }

  public void setFavorite(boolean paramBoolean)
  {
    this.favorite = paramBoolean;
  }

  public void setId(long paramLong)
  {
    this.id = paramLong;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setRawText(String paramString)
  {
    this.rawText = paramString;
  }

  public void setSource(HistorySource paramHistorySource)
  {
    this.source = paramHistorySource;
  }

  public void setTime(long paramLong)
  {
    this.time = paramLong;
  }

  public void setType(ParsedResultType paramParsedResultType)
  {
    this.type = paramParsedResultType;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.data.HistoryItem
 * JD-Core Version:    0.6.0
 */