package org.huihui.zxingimprove.data;

public class HistoryCounter
{
  public static final int COUNTER_TYPE_ALL = 4098;
  public static final int COUNTER_TYPE_FAVORITE = 4097;
  public static final int ORDER_ALL = 1;
  public static final int ORDER_FAVORITE = 0;
  public static final int ORDER_OTHER_MINIMUM = 2;
  int id;
  int order;
  boolean preserved;
  int type;
  int value;

  public int getId()
  {
    return this.id;
  }

  public int getOrder()
  {
    return this.order;
  }

  public int getType()
  {
    return this.type;
  }

  public int getValue()
  {
    return this.value;
  }

  public boolean isPreserved()
  {
    return this.preserved;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setOrder(int paramInt)
  {
    this.order = paramInt;
  }

  public void setPreserved(boolean paramBoolean)
  {
    this.preserved = paramBoolean;
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }

  public void setValue(int paramInt)
  {
    this.value = paramInt;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.data.HistoryCounter
 * JD-Core Version:    0.6.0
 */