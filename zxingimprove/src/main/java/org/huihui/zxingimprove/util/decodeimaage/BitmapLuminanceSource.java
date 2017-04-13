package org.huihui.zxingimprove.util.decodeimaage;

import android.graphics.Bitmap;
import com.google.zxing.LuminanceSource;

public class BitmapLuminanceSource extends LuminanceSource
{
  private byte[] bitmapPixels;

  protected BitmapLuminanceSource(Bitmap paramBitmap)
  {
    super(paramBitmap.getWidth(), paramBitmap.getHeight());
    int[] arrayOfInt = new int[paramBitmap.getWidth() * paramBitmap.getHeight()];
    this.bitmapPixels = new byte[paramBitmap.getWidth() * paramBitmap.getHeight()];
    paramBitmap.getPixels(arrayOfInt, 0, getWidth(), 0, 0, getWidth(), getHeight());
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfInt.length)
        return;
      this.bitmapPixels[i] = (byte)arrayOfInt[i];
    }
  }

  public byte[] getMatrix()
  {
    return this.bitmapPixels;
  }

  public byte[] getRow(int paramInt, byte[] paramArrayOfByte)
  {
    System.arraycopy(this.bitmapPixels, paramInt * getWidth(), paramArrayOfByte, 0, getWidth());
    return paramArrayOfByte;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.util.decodeimaage.BitmapLuminanceSource
 * JD-Core Version:    0.6.0
 */