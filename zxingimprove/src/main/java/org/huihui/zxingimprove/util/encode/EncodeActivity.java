package org.huihui.zxingimprove.util.encode;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;
import com.google.zxing.client.android.FinishListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EncodeActivity extends Activity
{
  private static final int MAX_BARCODE_FILENAME_LENGTH = 24;
  private static final Pattern NOT_ALPHANUMERIC;
  private static final String TAG = EncodeActivity.class.getSimpleName();
  private static final String USE_VCARD_KEY = "USE_VCARD";
  private QRCodeEncoder qrCodeEncoder;

  static
  {
    NOT_ALPHANUMERIC = Pattern.compile("[^A-Za-z0-9]");
  }

  private static CharSequence makeBarcodeFileName(CharSequence paramCharSequence)
  {
    String str = NOT_ALPHANUMERIC.matcher(paramCharSequence).replaceAll("_");
    if (str.length() > 24)
      str = str.substring(0, 24);
    return str;
  }

  // ERROR //
  private void share()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 72	mobi/thinkchange/android/superqrcode/util/encode/EncodeActivity:qrCodeEncoder	Lmobi/thinkchange/android/superqrcode/util/encode/QRCodeEncoder;
    //   4: astore_1
    //   5: aload_1
    //   6: ifnonnull +13 -> 19
    //   9: getstatic 26	mobi/thinkchange/android/superqrcode/util/encode/EncodeActivity:TAG	Ljava/lang/String;
    //   12: ldc 74
    //   14: invokestatic 80	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   17: pop
    //   18: return
    //   19: aload_1
    //   20: invokevirtual 85	mobi/thinkchange/android/superqrcode/util/encode/QRCodeEncoder:getContents	()Ljava/lang/String;
    //   23: astore_2
    //   24: aload_2
    //   25: ifnonnull +13 -> 38
    //   28: getstatic 26	mobi/thinkchange/android/superqrcode/util/encode/EncodeActivity:TAG	Ljava/lang/String;
    //   31: ldc 74
    //   33: invokestatic 80	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   36: pop
    //   37: return
    //   38: aload_1
    //   39: invokevirtual 89	mobi/thinkchange/android/superqrcode/util/encode/QRCodeEncoder:encodeAsBitmap	()Landroid/graphics/Bitmap;
    //   42: astore 5
    //   44: aload 5
    //   46: ifnull -28 -> 18
    //   49: new 91	java/io/File
    //   52: dup
    //   53: new 91	java/io/File
    //   56: dup
    //   57: invokestatic 97	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   60: ldc 99
    //   62: invokespecial 102	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   65: ldc 104
    //   67: invokespecial 102	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   70: astore 6
    //   72: aload 6
    //   74: invokevirtual 108	java/io/File:exists	()Z
    //   77: ifne +55 -> 132
    //   80: aload 6
    //   82: invokevirtual 111	java/io/File:mkdirs	()Z
    //   85: ifne +47 -> 132
    //   88: getstatic 26	mobi/thinkchange/android/superqrcode/util/encode/EncodeActivity:TAG	Ljava/lang/String;
    //   91: new 113	java/lang/StringBuilder
    //   94: dup
    //   95: ldc 115
    //   97: invokespecial 118	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   100: aload 6
    //   102: invokevirtual 122	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   105: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   108: invokestatic 80	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   111: pop
    //   112: aload_0
    //   113: ldc 126
    //   115: invokespecial 130	mobi/thinkchange/android/superqrcode/util/encode/EncodeActivity:showErrorMessage	(I)V
    //   118: return
    //   119: astore_3
    //   120: getstatic 26	mobi/thinkchange/android/superqrcode/util/encode/EncodeActivity:TAG	Ljava/lang/String;
    //   123: aload_3
    //   124: invokevirtual 131	com/google/zxing/WriterException:toString	()Ljava/lang/String;
    //   127: invokestatic 80	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   130: pop
    //   131: return
    //   132: new 91	java/io/File
    //   135: dup
    //   136: aload 6
    //   138: new 113	java/lang/StringBuilder
    //   141: dup
    //   142: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   145: aload_2
    //   146: invokestatic 134	mobi/thinkchange/android/superqrcode/util/encode/EncodeActivity:makeBarcodeFileName	(Ljava/lang/CharSequence;)Ljava/lang/CharSequence;
    //   149: invokevirtual 122	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   152: ldc 136
    //   154: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   160: invokespecial 102	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   163: astore 7
    //   165: aload 7
    //   167: invokevirtual 142	java/io/File:delete	()Z
    //   170: ifne +27 -> 197
    //   173: getstatic 26	mobi/thinkchange/android/superqrcode/util/encode/EncodeActivity:TAG	Ljava/lang/String;
    //   176: new 113	java/lang/StringBuilder
    //   179: dup
    //   180: ldc 144
    //   182: invokespecial 118	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   185: aload 7
    //   187: invokevirtual 122	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   190: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   193: invokestatic 80	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   196: pop
    //   197: aconst_null
    //   198: astore 8
    //   200: new 146	java/io/FileOutputStream
    //   203: dup
    //   204: aload 7
    //   206: invokespecial 149	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   209: astore 9
    //   211: aload 5
    //   213: getstatic 155	android/graphics/Bitmap$CompressFormat:PNG	Landroid/graphics/Bitmap$CompressFormat;
    //   216: iconst_0
    //   217: aload 9
    //   219: invokevirtual 161	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   222: pop
    //   223: aload 9
    //   225: ifnull +8 -> 233
    //   228: aload 9
    //   230: invokevirtual 164	java/io/FileOutputStream:close	()V
    //   233: new 166	android/content/Intent
    //   236: dup
    //   237: ldc 168
    //   239: ldc 170
    //   241: invokestatic 176	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   244: invokespecial 179	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   247: astore 16
    //   249: aload 16
    //   251: ldc 181
    //   253: new 113	java/lang/StringBuilder
    //   256: dup
    //   257: aload_0
    //   258: ldc 182
    //   260: invokevirtual 186	mobi/thinkchange/android/superqrcode/util/encode/EncodeActivity:getString	(I)Ljava/lang/String;
    //   263: invokestatic 190	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   266: invokespecial 118	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   269: ldc 192
    //   271: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   274: aload_1
    //   275: invokevirtual 195	mobi/thinkchange/android/superqrcode/util/encode/QRCodeEncoder:getTitle	()Ljava/lang/String;
    //   278: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   281: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   284: invokevirtual 199	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   287: pop
    //   288: aload 16
    //   290: ldc 201
    //   292: aload_2
    //   293: invokevirtual 199	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   296: pop
    //   297: aload 16
    //   299: ldc 203
    //   301: new 113	java/lang/StringBuilder
    //   304: dup
    //   305: ldc 205
    //   307: invokespecial 118	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   310: aload 7
    //   312: invokevirtual 208	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   315: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   318: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   321: invokestatic 176	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   324: invokevirtual 211	android/content/Intent:putExtra	(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;
    //   327: pop
    //   328: aload 16
    //   330: ldc 213
    //   332: invokevirtual 217	android/content/Intent:setType	(Ljava/lang/String;)Landroid/content/Intent;
    //   335: pop
    //   336: aload 16
    //   338: ldc 218
    //   340: invokevirtual 222	android/content/Intent:addFlags	(I)Landroid/content/Intent;
    //   343: pop
    //   344: aload_0
    //   345: aload 16
    //   347: aconst_null
    //   348: invokestatic 226	android/content/Intent:createChooser	(Landroid/content/Intent;Ljava/lang/CharSequence;)Landroid/content/Intent;
    //   351: invokevirtual 230	mobi/thinkchange/android/superqrcode/util/encode/EncodeActivity:startActivity	(Landroid/content/Intent;)V
    //   354: return
    //   355: astore 10
    //   357: getstatic 26	mobi/thinkchange/android/superqrcode/util/encode/EncodeActivity:TAG	Ljava/lang/String;
    //   360: new 113	java/lang/StringBuilder
    //   363: dup
    //   364: ldc 232
    //   366: invokespecial 118	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   369: aload 7
    //   371: invokevirtual 122	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   374: ldc 234
    //   376: invokevirtual 139	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   379: aload 10
    //   381: invokevirtual 122	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   384: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   387: invokestatic 80	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   390: pop
    //   391: aload_0
    //   392: ldc 126
    //   394: invokespecial 130	mobi/thinkchange/android/superqrcode/util/encode/EncodeActivity:showErrorMessage	(I)V
    //   397: aload 8
    //   399: ifnull -381 -> 18
    //   402: aload 8
    //   404: invokevirtual 164	java/io/FileOutputStream:close	()V
    //   407: return
    //   408: astore 14
    //   410: return
    //   411: astore 11
    //   413: aload 8
    //   415: ifnull +8 -> 423
    //   418: aload 8
    //   420: invokevirtual 164	java/io/FileOutputStream:close	()V
    //   423: aload 11
    //   425: athrow
    //   426: astore 12
    //   428: goto -5 -> 423
    //   431: astore 22
    //   433: goto -200 -> 233
    //   436: astore 11
    //   438: aload 9
    //   440: astore 8
    //   442: goto -29 -> 413
    //   445: astore 10
    //   447: aload 9
    //   449: astore 8
    //   451: goto -94 -> 357
    //
    // Exception table:
    //   from	to	target	type
    //   38	44	119	com/google/zxing/WriterException
    //   200	211	355	java/io/FileNotFoundException
    //   402	407	408	java/io/IOException
    //   200	211	411	finally
    //   357	397	411	finally
    //   418	423	426	java/io/IOException
    //   228	233	431	java/io/IOException
    //   211	223	436	finally
    //   211	223	445	java/io/FileNotFoundException
  }

  private void showErrorMessage(int paramInt)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage(paramInt);
    localBuilder.setPositiveButton(2131492883, new FinishListener(this));
    localBuilder.setOnCancelListener(new FinishListener(this));
    localBuilder.show();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Intent localIntent = getIntent();
    if (localIntent == null)
      finish();
    String str;
    do
    {
      return;
      str = localIntent.getAction();
    }
    while (("com.google.zxing.client.android.qr.ENCODE".equals(str)) || ("android.intent.action.SEND".equals(str)));
    finish();
  }

  protected void onResume()
  {
    super.onResume();
    ((WindowManager)getSystemService("window")).getDefaultDisplay();
    Point localPoint = new Point();
    int i = localPoint.x;
    int j = localPoint.y;
    if (i < j);
    for (int k = i; ; k = j)
    {
      (k * 7 / 8);
      if (getIntent() == null);
      return;
    }
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.util.encode.EncodeActivity
 * JD-Core Version:    0.6.0
 */