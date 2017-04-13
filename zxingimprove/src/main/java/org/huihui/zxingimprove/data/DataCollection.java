package org.huihui.zxingimprove.data;

import android.app.Application;
import java.security.SecureRandom;

public class DataCollection extends Application
{
  private String Id;
  private String cameraIsOpen = "1";
  private String cameraOpenExceptionInfo;
  private String clickCreateType;
  private String clickFeedbackButton;
  private String clickHistoryType;
  private String clickTabType;
  private String imageDecode;
  private String imageDecodeExceptionInfo;
  private int scanCount;
  private String scanResultsType;
  private long scanTime;
  private String vision;

  public String getCameraIsOpen()
  {
    return this.cameraIsOpen;
  }

  public String getCameraOpenExceptionInfo()
  {
    return this.cameraOpenExceptionInfo;
  }

  public String getClickCreateType()
  {
    return this.clickCreateType;
  }

  public String getClickFeedbackButton()
  {
    return this.clickFeedbackButton;
  }

  public String getClickHistoryType()
  {
    return this.clickHistoryType;
  }

  public String getClickTabType()
  {
    return this.clickTabType;
  }

  public String getId()
  {
    return this.Id;
  }

  public String getImageDecode()
  {
    return this.imageDecode;
  }

  public String getImageDecodeExceptionInfo()
  {
    return this.imageDecodeExceptionInfo;
  }

  public int getScanCount()
  {
    return this.scanCount;
  }

  public String getScanResultsType()
  {
    return this.scanResultsType;
  }

  public long getScanTime()
  {
    return this.scanTime;
  }

  public String getVision()
  {
    this.vision = "4001";
    return this.vision;
  }

  public void setCameraIsOpen(String paramString)
  {
    this.cameraIsOpen = paramString;
  }

  public void setCameraOpenExceptionInfo(String paramString)
  {
    this.cameraOpenExceptionInfo = paramString;
  }

  public void setClickCreateType(String paramString)
  {
    this.clickCreateType = paramString;
  }

  public void setClickFeedbackButton(String paramString)
  {
    this.clickFeedbackButton = paramString;
  }

  public void setClickHistoryType(String paramString)
  {
    this.clickHistoryType = paramString;
  }

  public void setClickTabType(String paramString)
  {
    this.clickTabType = paramString;
  }

  public void setId()
  {
    this.Id = Long.toHexString(1L + (0xFFFFFFFF & new SecureRandom().nextLong()) % 9223372036854775807L).substring(0, 8);
  }

  public void setImageDecode(String paramString)
  {
    this.imageDecode = paramString;
  }

  public void setImageDecodeExceptionInfo(String paramString)
  {
    this.imageDecodeExceptionInfo = paramString;
  }

  public void setScanCount(int paramInt)
  {
    this.scanCount = paramInt;
  }

  public void setScanResultsType(String paramString)
  {
    this.scanResultsType = paramString;
  }

  public void setScanTime(long paramLong)
  {
    this.scanTime = paramLong;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.data.DataCollection
 * JD-Core Version:    0.6.0
 */