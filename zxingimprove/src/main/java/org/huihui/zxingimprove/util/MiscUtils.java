package org.huihui.zxingimprove.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.gc.materialdesign.widgets.SnackBar;
import com.google.zxing.client.android.Contents;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import mobi.thinkchange.android.superqrcode.StartActivity;
import mobi.thinkchange.android.superqrcode.fragment.IStackableFragment;

public class MiscUtils
{
  public static final String BARCODE_FILENAME = "barcode.png";
  private static final float BEEP_VOLUME = 0.1F;
  public static final String CREATE_QRCOD_PATH;
  public static final String FROM = "from";
  public static boolean sdCardExist = Environment.getExternalStorageState().equals("mounted");
  private static ToastUtil toastUtil;
  private Activity activity;
  private final OnCompletionListener beepListener = new OnCompletionListener()
  {
    public void onCompletion(MediaPlayer paramMediaPlayer)
    {
      paramMediaPlayer.seekTo(0);
    }
  };
  private boolean isOpneSound = false;
  private MediaPlayer mediaPlayer;
  private PopupWindow popupWindow;
  private PreferencesUtils preferencesUtils;

  static
  {
    CREATE_QRCOD_PATH = Environment.getExternalStorageDirectory() + "/create_barcode/";
  }

  public MiscUtils(Activity paramActivity)
  {
    this.activity = paramActivity;
    this.preferencesUtils = new PreferencesUtils(paramActivity);
  }

  public static void Call(Context paramContext, String paramString)
  {
    if (paramContext == null);
    do
      return;
    while (TextUtils.isEmpty(paramString));
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(paramString)));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
    }
  }

  public static void addCalendarEvent(Context paramContext, String paramString1, Date paramDate1, boolean paramBoolean, Date paramDate2, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent("android.intent.action.INSERT");
    localIntent.setType("vnd.android.cursor.item/event");
    long l1 = paramDate1.getTime();
    localIntent.putExtra("beginTime", l1);
    if (paramBoolean)
      localIntent.putExtra("allDay", true);
    long l2;
    if (paramDate2 == null)
      if (paramBoolean)
        l2 = l1 + 86400000L;
    while (true)
    {
      localIntent.putExtra("endTime", l2);
      localIntent.putExtra("title", paramString1);
      localIntent.putExtra("eventLocation", paramString2);
      localIntent.putExtra("description", paramString3);
      try
      {
        paramContext.startActivity(localIntent);
        return;
        l2 = l1;
        continue;
        l2 = paramDate2.getTime();
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        Log.e("addCalendarEvent", "没有可用的日历应用程序响应 android.intent.action.INSERT");
        localIntent.setAction("android.intent.action.EDIT");
        paramContext.startActivity(localIntent);
      }
    }
  }

  @SuppressLint({"NewApi"})
  public static void addContact(Context paramContext, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, String paramString1, String paramString2, String paramString3, String[] paramArrayOfString4)
  {
    Intent localIntent = new Intent("android.intent.action.INSERT_OR_EDIT", ContactsContract.Contacts.CONTENT_URI);
    localIntent.setType("vnd.android.cursor.item/contact");
    String str1;
    if (paramArrayOfString1 != null)
      str1 = paramArrayOfString1[0];
    while (true)
    {
      putExtra(localIntent, "name", str1);
      int i;
      label48: int k;
      label62: int m;
      label77: int i1;
      label91: ArrayList localArrayList;
      int i3;
      if (paramArrayOfString2 != null)
      {
        i = paramArrayOfString2.length;
        int j = Math.min(i, Contents.PHONE_KEYS.length);
        k = 0;
        if (k < j)
          break label230;
        if (paramArrayOfString3 == null)
          break label251;
        m = paramArrayOfString3.length;
        int n = Math.min(m, Contents.EMAIL_KEYS.length);
        i1 = 0;
        if (i1 < n)
          break label257;
        localArrayList = new ArrayList();
        if (paramArrayOfString4 != null)
        {
          int i2 = paramArrayOfString4.length;
          i3 = 0;
          if (i3 < i2)
            break label278;
        }
        label127: if (!localArrayList.isEmpty())
          localIntent.putParcelableArrayListExtra("data", localArrayList);
        StringBuilder localStringBuilder = new StringBuilder();
        if (paramString1 != null)
          localStringBuilder.append('\n').append(paramString1);
        if (localStringBuilder.length() > 0)
          putExtra(localIntent, "notes", localStringBuilder.substring(1));
        putExtra(localIntent, "postal", paramString2);
        putExtra(localIntent, "company", paramString3);
      }
      try
      {
        paramContext.startActivity(localIntent);
        return;
        str1 = null;
        continue;
        i = 0;
        break label48;
        label230: putExtra(localIntent, Contents.PHONE_KEYS[k], paramArrayOfString2[k]);
        k++;
        break label62;
        label251: m = 0;
        break label77;
        label257: putExtra(localIntent, Contents.EMAIL_KEYS[i1], paramArrayOfString3[i1]);
        i1++;
        break label91;
        label278: String str2 = paramArrayOfString4[i3];
        if ((str2 != null) && (!str2.isEmpty()))
        {
          ContentValues localContentValues = new ContentValues(2);
          localContentValues.put("mimetype", "vnd.android.cursor.item/website");
          localContentValues.put("data1", str2);
          localArrayList.add(localContentValues);
          break label127;
        }
        i3++;
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
      }
    }
  }

  public static void addShortcut(Context paramContext)
  {
    Intent localIntent1 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    localIntent1.putExtra("duplicate", false);
    localIntent1.putExtra("android.intent.extra.shortcut.NAME", paramContext.getString(2131492864));
    localIntent1.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", ShortcutIconResource.fromContext(paramContext, 2130837674));
    Intent localIntent2 = new Intent(paramContext.getApplicationContext(), StartActivity.class);
    localIntent2.setAction("android.intent.action.MAIN");
    localIntent2.addCategory("android.intent.category.LAUNCHER");
    localIntent1.putExtra("android.intent.extra.shortcut.INTENT", localIntent2);
    paramContext.sendBroadcast(localIntent1);
  }

  @SuppressLint({"NewApi"})
  public static void copy(Context paramContext, String paramString)
  {
    if (Build.VERSION.SDK_INT > 11)
    {
      ((android.content.ClipboardManager)paramContext.getSystemService("clipboard")).setText(paramString);
      return;
    }
    ((android.text.ClipboardManager)paramContext.getSystemService("clipboard")).setText(paramString);
  }

  @SuppressLint({"SimpleDateFormat"})
  public static String getDateInString(long paramLong, String paramString)
  {
    return new SimpleDateFormat(paramString).format(new Date(paramLong));
  }

  public static int getPopRatio(Context paramContext)
  {
    return 360 * getScreenWidth(paramContext) / 720;
  }

  public static Bitmap getRoundedCornerBitmap(Bitmap paramBitmap, int paramInt)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    Rect localRect = new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
    RectF localRectF = new RectF(localRect);
    float f = paramInt;
    localPaint.setAntiAlias(true);
    localCanvas.drawARGB(0, 0, 0, 0);
    localPaint.setColor(-12434878);
    localCanvas.drawRoundRect(localRectF, f, f, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(paramBitmap, localRect, localRect, localPaint);
    return localBitmap;
  }

  public static int getScreenWidth(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((Activity)paramContext).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }

  public static boolean isNetworkAvailable(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getApplicationContext().getSystemService("connectivity");
    if (localConnectivityManager == null);
    while (true)
    {
      return false;
      NetworkInfo[] arrayOfNetworkInfo = localConnectivityManager.getAllNetworkInfo();
      if (arrayOfNetworkInfo == null)
        continue;
      for (int i = 0; i < arrayOfNetworkInfo.length; i++)
        if (arrayOfNetworkInfo[i].getState() == State.CONNECTED)
          return true;
    }
  }

  public static void myStartActivity(Context paramContext, Class<?> paramClass, Intent paramIntent)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, paramClass);
    if (!(paramContext instanceof Activity))
      localIntent.addFlags(268435456);
    if (paramIntent != null)
      localIntent.putExtras(paramIntent);
    paramContext.startActivity(localIntent);
  }

  public static void openBrowser(Context paramContext, String paramString)
  {
    if (paramContext == null);
    do
      return;
    while (TextUtils.isEmpty(paramString));
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      if (!(paramContext instanceof Activity))
        localIntent.addFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
    }
  }

  public static void openMap(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (paramContext == null)
      return;
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString1)));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      String str = "http://api.map.baidu.com/marker?location=" + paramString3 + "," + paramString2 + "&title=" + paramContext.getResources().getString(2131493051) + "&content=" + paramContext.getResources().getString(2131493049) + paramString3 + "," + paramContext.getResources().getString(2131493048) + paramString2 + "&output=html";
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
      Log.e("openMap", str);
    }
  }

  @SuppressLint({"NewApi"})
  private static void putExtra(Intent paramIntent, String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (!paramString2.isEmpty()))
      paramIntent.putExtra(paramString1, paramString2);
  }

  public static void saveFile(Bitmap paramBitmap, String paramString)
  {
    if (!sdCardExist)
      return;
    File localFile1 = new File(CREATE_QRCOD_PATH);
    if (!localFile1.exists())
      localFile1.mkdir();
    File localFile2 = new File(CREATE_QRCOD_PATH + paramString);
    try
    {
      BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(localFile2));
      paramBitmap.compress(CompressFormat.JPEG, 80, localBufferedOutputStream);
      localBufferedOutputStream.flush();
      localBufferedOutputStream.close();
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }

  // ERROR //
  public static void saveImageToGallery(Context paramContext, Bitmap paramBitmap, String paramString)
  {
    // Byte code:
    //   0: getstatic 48	mobi/thinkchange/android/superqrcode/util/MiscUtils:sdCardExist	Z
    //   3: ifeq +270 -> 273
    //   6: new 517	java/io/File
    //   9: dup
    //   10: getstatic 71	mobi/thinkchange/android/superqrcode/util/MiscUtils:CREATE_QRCOD_PATH	Ljava/lang/String;
    //   13: invokespecial 518	java/io/File:<init>	(Ljava/lang/String;)V
    //   16: astore_3
    //   17: aload_3
    //   18: invokevirtual 521	java/io/File:exists	()Z
    //   21: ifne +8 -> 29
    //   24: aload_3
    //   25: invokevirtual 524	java/io/File:mkdir	()Z
    //   28: pop
    //   29: new 50	java/lang/StringBuilder
    //   32: dup
    //   33: aload_2
    //   34: invokestatic 528	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   37: invokespecial 490	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   40: ldc_w 562
    //   43: invokevirtual 66	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   49: astore 4
    //   51: new 517	java/io/File
    //   54: dup
    //   55: aload_3
    //   56: aload 4
    //   58: invokespecial 565	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   61: astore 5
    //   63: new 532	java/io/FileOutputStream
    //   66: dup
    //   67: aload 5
    //   69: invokespecial 535	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   72: astore 6
    //   74: aload_1
    //   75: getstatic 544	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   78: bipush 100
    //   80: aload 6
    //   82: invokevirtual 548	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   85: pop
    //   86: aload 6
    //   88: invokevirtual 566	java/io/FileOutputStream:flush	()V
    //   91: aload 6
    //   93: invokevirtual 567	java/io/FileOutputStream:close	()V
    //   96: aload_0
    //   97: invokevirtual 571	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   100: aload 5
    //   102: invokevirtual 574	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   105: aload 4
    //   107: aconst_null
    //   108: invokestatic 580	android/provider/MediaStore$Images$Media:insertImage	(Landroid/content/ContentResolver;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   111: pop
    //   112: aload_0
    //   113: new 102	android/content/Intent
    //   116: dup
    //   117: ldc_w 582
    //   120: new 50	java/lang/StringBuilder
    //   123: dup
    //   124: ldc_w 584
    //   127: invokespecial 490	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   130: aload_3
    //   131: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   134: invokevirtual 69	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   137: invokestatic 110	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   140: invokespecial 113	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;)V
    //   143: invokevirtual 308	android/content/Context:sendBroadcast	(Landroid/content/Intent;)V
    //   146: new 586	cn/pedant/SweetAlert/SweetAlertDialog
    //   149: dup
    //   150: aload_0
    //   151: iconst_2
    //   152: invokespecial 589	cn/pedant/SweetAlert/SweetAlertDialog:<init>	(Landroid/content/Context;I)V
    //   155: astore 9
    //   157: aload 9
    //   159: iconst_1
    //   160: invokevirtual 593	cn/pedant/SweetAlert/SweetAlertDialog:showCancelButton	(Z)Lcn/pedant/SweetAlert/SweetAlertDialog;
    //   163: invokevirtual 596	cn/pedant/SweetAlert/SweetAlertDialog:show	()V
    //   166: aload 9
    //   168: ldc_w 598
    //   171: invokevirtual 602	cn/pedant/SweetAlert/SweetAlertDialog:setTitleText	(Ljava/lang/String;)Lcn/pedant/SweetAlert/SweetAlertDialog;
    //   174: pop
    //   175: aload 9
    //   177: aload_0
    //   178: invokevirtual 498	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   181: ldc_w 603
    //   184: invokevirtual 502	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   187: invokevirtual 606	cn/pedant/SweetAlert/SweetAlertDialog:setContentText	(Ljava/lang/String;)Lcn/pedant/SweetAlert/SweetAlertDialog;
    //   190: pop
    //   191: aload 9
    //   193: aload_0
    //   194: invokevirtual 498	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   197: ldc_w 607
    //   200: invokevirtual 502	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   203: invokevirtual 610	cn/pedant/SweetAlert/SweetAlertDialog:setConfirmText	(Ljava/lang/String;)Lcn/pedant/SweetAlert/SweetAlertDialog;
    //   206: pop
    //   207: aload 9
    //   209: aload_0
    //   210: invokevirtual 498	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   213: ldc_w 611
    //   216: invokevirtual 502	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   219: invokevirtual 614	cn/pedant/SweetAlert/SweetAlertDialog:setCancelText	(Ljava/lang/String;)Lcn/pedant/SweetAlert/SweetAlertDialog;
    //   222: pop
    //   223: aload 9
    //   225: new 616	mobi/thinkchange/android/superqrcode/util/MiscUtils$3
    //   228: dup
    //   229: aload_3
    //   230: aload 4
    //   232: aload_0
    //   233: aload 9
    //   235: invokespecial 619	mobi/thinkchange/android/superqrcode/util/MiscUtils$3:<init>	(Ljava/io/File;Ljava/lang/String;Landroid/content/Context;Lcn/pedant/SweetAlert/SweetAlertDialog;)V
    //   238: invokevirtual 623	cn/pedant/SweetAlert/SweetAlertDialog:setConfirmClickListener	(Lcn/pedant/SweetAlert/SweetAlertDialog$OnSweetClickListener;)Lcn/pedant/SweetAlert/SweetAlertDialog;
    //   241: pop
    //   242: return
    //   243: astore 16
    //   245: aload 16
    //   247: invokevirtual 557	java/io/FileNotFoundException:printStackTrace	()V
    //   250: goto -154 -> 96
    //   253: astore 7
    //   255: aload 7
    //   257: invokevirtual 558	java/io/IOException:printStackTrace	()V
    //   260: goto -164 -> 96
    //   263: astore 8
    //   265: aload 8
    //   267: invokevirtual 557	java/io/FileNotFoundException:printStackTrace	()V
    //   270: goto -158 -> 112
    //   273: new 625	mobi/thinkchange/android/superqrcode/util/ToastUtil
    //   276: dup
    //   277: aload_0
    //   278: checkcast 425	android/app/Activity
    //   281: invokespecial 626	mobi/thinkchange/android/superqrcode/util/ToastUtil:<init>	(Landroid/app/Activity;)V
    //   284: putstatic 628	mobi/thinkchange/android/superqrcode/util/MiscUtils:toastUtil	Lmobi/thinkchange/android/superqrcode/util/ToastUtil;
    //   287: getstatic 628	mobi/thinkchange/android/superqrcode/util/MiscUtils:toastUtil	Lmobi/thinkchange/android/superqrcode/util/ToastUtil;
    //   290: aload_0
    //   291: invokevirtual 498	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   294: ldc_w 629
    //   297: invokevirtual 502	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   300: invokevirtual 633	mobi/thinkchange/android/superqrcode/util/ToastUtil:getBgToastInstance	(Ljava/lang/String;)Landroid/widget/Toast;
    //   303: pop
    //   304: return
    //
    // Exception table:
    //   from	to	target	type
    //   63	96	243	java/io/FileNotFoundException
    //   63	96	253	java/io/IOException
    //   96	112	263	java/io/FileNotFoundException
  }

  public static void searchISBN(Context paramContext, String paramString)
  {
    if (paramContext == null)
      return;
    String str = "http://www.baidu.com/s?wd=" + paramString;
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(str));
      if (!(paramContext instanceof Activity))
        localIntent.addFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
    }
  }

  public static void searchProduct(Context paramContext, String paramString)
  {
    if (paramContext == null);
    do
      return;
    while (TextUtils.isEmpty(paramString));
    String str = "http://www.baidu.com/s?wd=" + paramString;
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(str));
      if (!(paramContext instanceof Activity))
        localIntent.addFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
    }
  }

  public static void sendEmail(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (paramContext == null);
    do
      return;
    while (TextUtils.isEmpty(paramString1));
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.putExtra("android.intent.extra.EMAIL", new String[] { paramString1 });
    putExtra(localIntent, "android.intent.extra.SUBJECT", paramString2);
    putExtra(localIntent, "android.intent.extra.TEXT", paramString3);
    localIntent.setType("message/rfc822");
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
    }
  }

  public static void sendSMS(Context paramContext, String paramString1, String paramString2)
  {
    if (paramContext == null);
    do
      return;
    while (TextUtils.isEmpty(paramString1));
    Intent localIntent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + paramString1));
    putExtra(localIntent, "sms_body", paramString2);
    localIntent.putExtra("exit_on_sent", true);
    localIntent.putExtra("compose_mode", true);
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
    }
  }

  public static void shareImage(Context paramContext, String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.putExtra("android.intent.extra.SUBJECT", paramString1);
    localIntent.setType("image/*");
    localIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(paramString2)));
    paramContext.startActivity(localIntent);
  }

  public static void shareText(Context paramContext, String paramString1, String paramString2)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/*");
    localIntent.putExtra("android.intent.extra.SUBJECT", paramString1);
    localIntent.putExtra("android.intent.extra.TEXT", paramString2);
    paramContext.startActivity(localIntent);
  }

  public static void showSnackBar(Activity paramActivity, String paramString1, String paramString2)
  {
    SnackBar localSnackBar = new SnackBar(paramActivity, paramString1, paramString2, new OnClickListener()
    {
      public void onClick(View paramView)
      {
      }
    });
    localSnackBar.setColorButton(paramActivity.getResources().getColor(2131165240));
    localSnackBar.show();
  }

  public static void startActivity(Activity paramActivity, Class<?> paramClass, String paramString)
  {
    Intent localIntent = new Intent(paramActivity, paramClass);
    localIntent.addFlags(131072);
    if (paramString != null)
      localIntent.putExtra("from", paramString);
    paramActivity.startActivity(localIntent);
  }

  public Fragment getActiveFragmentInFragmentTabHost(FragmentActivity paramFragmentActivity)
  {
    return paramFragmentActivity.getSupportFragmentManager().findFragmentById(2131623977);
  }

  public void hideTiptPopup()
  {
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        if (MiscUtils.this.popupWindow.isShowing())
          MiscUtils.this.popupWindow.dismiss();
      }
    }
    , 4000L);
  }

  public void initSound(int paramInt)
  {
    this.isOpneSound = this.preferencesUtils.getIsOpenSound();
    AssetFileDescriptor localAssetFileDescriptor;
    if (this.isOpneSound)
    {
      this.mediaPlayer = new MediaPlayer();
      this.mediaPlayer.setAudioStreamType(3);
      this.mediaPlayer.setOnCompletionListener(this.beepListener);
      localAssetFileDescriptor = this.activity.getResources().openRawResourceFd(paramInt);
    }
    try
    {
      this.mediaPlayer.setDataSource(localAssetFileDescriptor.getFileDescriptor(), localAssetFileDescriptor.getStartOffset(), localAssetFileDescriptor.getLength());
      localAssetFileDescriptor.close();
      this.mediaPlayer.setVolume(0.1F, 0.1F);
      this.mediaPlayer.prepare();
      return;
    }
    catch (IOException localIOException)
    {
      this.mediaPlayer = null;
    }
  }

  public void playSound()
  {
    this.isOpneSound = this.preferencesUtils.getIsOpenSound();
    if ((this.isOpneSound) && (this.mediaPlayer != null))
      this.mediaPlayer.start();
  }

  public void showTipPopup(Context paramContext, String paramString, View paramView, int paramInt)
  {
    View localView = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(2130903110, null);
    ((TextView)localView.findViewById(2131624211)).setText(paramString);
    this.popupWindow = new PopupWindow(paramContext);
    this.popupWindow.setBackgroundDrawable(new ColorDrawable(0));
    this.popupWindow.setWidth(paramInt);
    this.popupWindow.setHeight(-2);
    this.popupWindow.setOutsideTouchable(true);
    this.popupWindow.setFocusable(true);
    this.popupWindow.setContentView(localView);
    this.popupWindow.setAnimationStyle(2131427340);
    this.popupWindow.showAsDropDown(paramView, paramView.getWidth() - this.popupWindow.getWidth(), 0);
    hideTiptPopup();
  }

  public void titleBack(FragmentActivity paramFragmentActivity)
  {
    Fragment localFragment = getActiveFragmentInFragmentTabHost(paramFragmentActivity);
    if ((localFragment instanceof IStackableFragment))
      ((IStackableFragment)localFragment).popFragment();
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.util.MiscUtils
 * JD-Core Version:    0.6.0
 */