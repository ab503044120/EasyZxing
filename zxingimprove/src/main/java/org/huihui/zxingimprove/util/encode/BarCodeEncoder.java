package org.huihui.zxingimprove.util.encode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public abstract class BarCodeEncoder
{
  public static BarcodeFormat barcodeFormat = BarcodeFormat.EAN_13;

  public static Bitmap creatBarcode(Context paramContext, String paramString, int paramInt1, int paramInt2)
  {
    return encodeAsBitmap(paramString, barcodeFormat, paramInt1, paramInt2);
  }

  protected static Bitmap encodeAsBitmap(String paramString, BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2)
  {
    MultiFormatWriter localMultiFormatWriter = new MultiFormatWriter();
    BitMatrix localBitMatrix1;
    int[] arrayOfInt;
    int k;
    int m;
    int n;
    while (true)
    {
      int i;
      try
      {
        BitMatrix localBitMatrix2 = localMultiFormatWriter.encode(paramString, paramBarcodeFormat, paramInt1, paramInt2, null);
        localBitMatrix1 = localBitMatrix2;
        i = localBitMatrix1.getWidth();
        int j = localBitMatrix1.getHeight();
        arrayOfInt = new int[i * j];
        k = 0;
        if (k < j)
          continue;
        Bitmap localBitmap = Bitmap.createBitmap(i, j, Config.ARGB_8888);
        localBitmap.setPixels(arrayOfInt, 0, i, 0, 0, i, j);
        return localBitmap;
      }
      catch (WriterException localWriterException)
      {
        localWriterException.printStackTrace();
        localBitMatrix1 = null;
        continue;
        m = k * i;
        n = 0;
      }
      if (n < i)
        break;
      k++;
    }
    int i1 = m + n;
    if (localBitMatrix1.get(n, k));
    for (int i2 = -16777216; ; i2 = -1)
    {
      arrayOfInt[i1] = i2;
      n++;
      break;
    }
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.util.encode.BarCodeEncoder
 * JD-Core Version:    0.6.0
 */