package org.huihui.zxingimprove.util.decodeimaage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.android.DecodeFormatManager;
import com.google.zxing.common.HybridBinarizer;
import java.util.Hashtable;
import java.util.Vector;
import mobi.thinkchange.android.superqrcode.data.DataCollection;

public class DecodeImaageHandel
{
  private Activity activity;
  private Bitmap bitmap;
  private DataCollection dataCollection;
  private MultiFormatReader multiFormatReader;

  public DecodeImaageHandel(Context paramContext, Bitmap paramBitmap)
  {
    this.activity = ((Activity)paramContext);
    this.bitmap = paramBitmap;
    init();
  }

  private void init()
  {
    this.dataCollection = ((DataCollection)this.activity.getApplicationContext());
    this.multiFormatReader = new MultiFormatReader();
    Hashtable localHashtable = new Hashtable(2);
    Vector localVector = new Vector();
    if ((localVector == null) || (localVector.isEmpty()))
    {
      localVector = new Vector();
      localVector.addAll(DecodeFormatManager.ONE_D_FORMATS);
      localVector.addAll(DecodeFormatManager.QR_CODE_FORMATS);
      localVector.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
    }
    localHashtable.put(DecodeHintType.POSSIBLE_FORMATS, localVector);
    this.multiFormatReader.setHints(localHashtable);
  }

  public Result getResult()
  {
    try
    {
      Result localResult = this.multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(new BitmapLuminanceSource(this.bitmap))));
      return localResult;
    }
    catch (NotFoundException localNotFoundException)
    {
      this.dataCollection.setImageDecodeExceptionInfo(localNotFoundException.toString());
    }
    return null;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.util.decodeimaage.DecodeImaageHandel
 * JD-Core Version:    0.6.0
 */