package org.huihui.zxingimprove.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;

public class Log
{
  static final String LOG_TAG = "TCV3";
  private static boolean mDebug = false;

  static int d(String paramString)
  {
    return android.util.Log.d("TCV3", formatMessage(paramString));
  }

  public static int dDebug(String paramString)
  {
    if (mDebug)
      return d(paramString);
    return 0;
  }

  public static boolean debugEnabled()
  {
    return mDebug;
  }

  static int e(String paramString)
  {
    return android.util.Log.e("TCV3", formatMessage(paramString));
  }

  public static int eDebug(String paramString)
  {
    if (mDebug)
      return e(paramString);
    return 0;
  }

  private static String formatMessage(String paramString)
  {
    return Thread.currentThread().toString() + ": " + paramString;
  }

  static int i(String paramString)
  {
    return android.util.Log.i("TCV3", formatMessage(paramString));
  }

  public static int iDebug(String paramString)
  {
    if (mDebug)
      return i(paramString);
    return 0;
  }

  public static void logDebugRequestInformation(boolean paramBoolean, HttpEntityEnclosingRequest paramHttpEntityEnclosingRequest)
  {
    StringBuffer localStringBuffer;
    Header[] arrayOfHeader;
    int i;
    int j;
    if (paramBoolean)
    {
      localStringBuffer = new StringBuffer();
      arrayOfHeader = paramHttpEntityEnclosingRequest.getAllHeaders();
      i = arrayOfHeader.length;
      j = 0;
    }
    while (true)
    {
      if (j >= i)
      {
        localStringBuffer.append(paramHttpEntityEnclosingRequest.getRequestLine().toString()).append("\n");
        if (paramHttpEntityEnclosingRequest.getEntity() == null);
      }
      try
      {
        InputStream localInputStream = paramHttpEntityEnclosingRequest.getEntity().getContent();
        if (localInputStream != null)
        {
          int k = localInputStream.available();
          if (k > 0)
          {
            byte[] arrayOfByte = new byte[k];
            localInputStream.read(arrayOfByte);
            localStringBuffer.append("POST:\n");
            localStringBuffer.append(new String(arrayOfByte)).append("\n");
          }
        }
        iDebug(localStringBuffer.toString());
        return;
        localStringBuffer.append(arrayOfHeader[j].toString()).append("\n");
        j++;
      }
      catch (IOException localIOException)
      {
        while (true)
          wDebug("Error Writing hit to log...");
      }
    }
  }

  public static String printStackTrace(Throwable paramThrowable)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    PrintStream localPrintStream = new PrintStream(localByteArrayOutputStream);
    paramThrowable.printStackTrace(localPrintStream);
    localPrintStream.flush();
    return new String(localByteArrayOutputStream.toByteArray());
  }

  static void setDebug(boolean paramBoolean)
  {
    mDebug = paramBoolean;
  }

  static int v(String paramString)
  {
    return android.util.Log.v("TCV3", formatMessage(paramString));
  }

  public static int vDebug(String paramString)
  {
    if (mDebug)
      return v(paramString);
    return 0;
  }

  public static int w(String paramString)
  {
    return android.util.Log.w("TCV3", formatMessage(paramString));
  }

  public static int wDebug(String paramString)
  {
    if (mDebug)
      return w(paramString);
    return 0;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.data.Log
 * JD-Core Version:    0.6.0
 */