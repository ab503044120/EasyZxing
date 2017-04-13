package org.huihui.zxingimprove.util.encode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.android.Contents;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class QRCodeEncoder
{
  private static final int BLACK = -16777216;
  private static final int WHITE = -1;
  private String contents;
  private final Context context;
  private final int dimension;
  private String displayContents;
  private BarcodeFormat format;
  private String title;
  private ParsedResultType type;
  private final boolean useVCard;

  public QRCodeEncoder(Context paramContext, Bundle paramBundle, int paramInt, boolean paramBoolean)
    throws WriterException
  {
    this.context = paramContext;
    this.dimension = paramInt;
    this.useVCard = paramBoolean;
    encodeContentsFromBundle(paramBundle);
  }

  private boolean encodeContentsFromBundle(Bundle paramBundle)
  {
    String str1 = paramBundle.getString("ENCODE_FORMAT");
    this.format = null;
    if (str1 != null);
    try
    {
      this.format = BarcodeFormat.valueOf(str1);
      label24: String str2;
      if ((this.format == null) || (this.format == BarcodeFormat.QR_CODE))
      {
        str2 = paramBundle.getString("ENCODE_TYPE");
        if (!TextUtils.isEmpty(str2));
      }
      while (true)
      {
        return false;
        this.format = BarcodeFormat.QR_CODE;
        encodeQRCodeContents(paramBundle, str2);
        while (!TextUtils.isEmpty(this.contents))
        {
          return true;
          String str3 = paramBundle.getString("ENCODE_DATA");
          if (TextUtils.isEmpty(str3))
            continue;
          this.contents = str3;
          this.displayContents = str3;
          this.title = this.context.getString(2131492903);
        }
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      break label24;
    }
  }

  private void encodeQRCodeContents(Bundle paramBundle, String paramString)
  {
    if (paramString.equals("TEXT_TYPE"))
    {
      String str24 = paramBundle.getString("ENCODE_DATA");
      if (!TextUtils.isEmpty(str24))
      {
        this.contents = str24;
        this.displayContents = str24;
        this.title = this.context.getString(2131492903);
        this.type = ParsedResultType.TEXT;
      }
    }
    label787: String str1;
    label898: String str2;
    String str3;
    String str4;
    String str5;
    do
    {
      Bundle localBundle1;
      do
      {
        do
          while (true)
          {
            return;
            if (paramString.equals("URL_KEY"))
            {
              String str23 = paramBundle.getString("ENCODE_DATA");
              if (TextUtils.isEmpty(str23))
                continue;
              if ((str23.contains("http://")) || (str23.contains("https://")));
              for (this.contents = str23; ; this.contents = ("http://" + str23))
              {
                this.displayContents = this.contents;
                Log.e("displayContents", this.displayContents);
                this.title = this.context.getString(2131493021);
                this.type = ParsedResultType.URI;
                return;
              }
            }
            if (paramString.equals("PRODUCT_TYPE"))
            {
              String str22 = paramBundle.getString("ENCODE_DATA");
              if (TextUtils.isEmpty(str22))
                continue;
              this.contents = str22;
              this.displayContents = str22;
              this.title = this.context.getString(2131493013);
              this.type = ParsedResultType.PRODUCT;
              return;
            }
            if (paramString.equals("EMAIL_TYPE"))
            {
              Bundle localBundle6 = paramBundle.getBundle("ENCODE_DATA");
              if (localBundle6 == null)
                continue;
              String str19 = localBundle6.getString("TO");
              String str20 = localBundle6.getString("SUB");
              String str21 = localBundle6.getString("BODY");
              if (TextUtils.isEmpty(str19))
                continue;
              StringBuilder localStringBuilder3 = new StringBuilder(100);
              localStringBuilder3.append("MATMSG:");
              localStringBuilder3.append("TO:").append(str19).append(';');
              if (!TextUtils.isEmpty(str20))
                localStringBuilder3.append("SUB:").append(str20).append(';');
              if (!TextUtils.isEmpty(str21))
                localStringBuilder3.append("BODY:").append(str21).append(';');
              localStringBuilder3.append(';');
              this.contents = localStringBuilder3.toString();
              this.displayContents = (str19 + "\n" + str20 + "\n" + str21);
              this.title = this.context.getString(2131492898);
              this.type = ParsedResultType.EMAIL_ADDRESS;
              return;
            }
            if (paramString.equals("PHONE_TYPE"))
            {
              String str18 = ContactEncoder.trim(paramBundle.getString("ENCODE_DATA"));
              if (str18 == null)
                continue;
              this.contents = ("tel:" + str18);
              this.displayContents = PhoneNumberUtils.formatNumber(str18);
              this.title = this.context.getString(2131492901);
              this.type = ParsedResultType.TEL;
              return;
            }
            if (paramString.equals("SMS_TYPE"))
            {
              Bundle localBundle5 = paramBundle.getBundle("ENCODE_DATA");
              if (localBundle5 == null)
                continue;
              String str15 = localBundle5.getString("number");
              String str16 = localBundle5.getString("message");
              if (TextUtils.isEmpty(str15))
                continue;
              String str17 = PhoneNumberUtils.formatNumber(str15);
              StringBuilder localStringBuilder2 = new StringBuilder(100);
              localStringBuilder2.append("smsto:");
              localStringBuilder2.append(str17);
              if (!TextUtils.isEmpty(str16))
                localStringBuilder2.append(':').append(str16);
              this.contents = localStringBuilder2.toString();
              this.displayContents = (str17 + "\n" + str16);
              this.title = this.context.getString(2131492902);
              this.type = ParsedResultType.SMS;
              return;
            }
            if (paramString.equals("CONTACT_TYPE"))
            {
              Bundle localBundle4 = paramBundle.getBundle("ENCODE_DATA");
              if (localBundle4 == null)
                continue;
              String str10 = localBundle4.getString("name");
              String str11 = localBundle4.getString("company");
              String str12 = localBundle4.getString("postal");
              List localList1 = getAllBundleValues(localBundle4, Contents.PHONE_KEYS);
              List localList2 = getAllBundleValues(localBundle4, Contents.PHONE_TYPE_KEYS);
              List localList3 = getAllBundleValues(localBundle4, Contents.EMAIL_KEYS);
              String str13 = localBundle4.getString("URL_KEY");
              List localList4;
              String str14;
              if (str13 == null)
              {
                localList4 = null;
                str14 = localBundle4.getString("NOTE_KEY");
                if (!this.useVCard)
                  break label898;
              }
              for (Object localObject = new VCardContactEncoder(); ; localObject = new MECARDContactEncoder())
              {
                String[] arrayOfString = ((ContactEncoder)localObject).encode(Collections.singletonList(str10), str11, Collections.singletonList(str12), localList1, localList2, localList3, localList4, str14);
                if (TextUtils.isEmpty(arrayOfString[1]))
                  break;
                this.contents = arrayOfString[0];
                this.displayContents = arrayOfString[1];
                this.title = this.context.getString(2131492897);
                this.type = ParsedResultType.ADDRESSBOOK;
                return;
                localList4 = Collections.singletonList(str13);
                break label787;
              }
            }
            if (paramString.equals("LOCATION_TYPE"))
            {
              Bundle localBundle3 = paramBundle.getBundle("ENCODE_DATA");
              if (localBundle3 == null)
                continue;
              float f1 = localBundle3.getFloat("LAT", 3.4028235E+38F);
              float f2 = localBundle3.getFloat("LONG", 3.4028235E+38F);
              if ((f1 == 3.4028235E+38F) || (f2 == 3.4028235E+38F))
                continue;
              this.contents = ("geo:" + f1 + ',' + f2);
              this.displayContents = (f1 + "," + f2);
              this.title = this.context.getString(2131492899);
              this.type = ParsedResultType.GEO;
              return;
            }
            if (!paramString.equals("WIFI_TYPE"))
              break;
            Bundle localBundle2 = paramBundle.getBundle("ENCODE_DATA");
            if (localBundle2 == null)
              continue;
            String str7 = localBundle2.getString("S");
            String str8 = localBundle2.getString("P");
            String str9 = localBundle2.getString("T");
            if (TextUtils.isEmpty(str7))
              continue;
            StringBuilder localStringBuilder1 = new StringBuilder(100);
            localStringBuilder1.append("WIFI:");
            localStringBuilder1.append("S:").append(str7).append(';');
            if (!TextUtils.isEmpty(str9))
              localStringBuilder1.append("T:").append(str9).append(';');
            if (!TextUtils.isEmpty(str8))
              localStringBuilder1.append("P:").append(str8).append(';');
            localStringBuilder1.append(';');
            this.contents = localStringBuilder1.toString();
            this.displayContents = this.contents;
            this.title = this.context.getString(2131492900);
            this.type = ParsedResultType.WIFI;
            return;
          }
        while (!paramString.equals("CALENDAR_TYPE"));
        localBundle1 = paramBundle.getBundle("ENCODE_DATA");
      }
      while (localBundle1 == null);
      str1 = localBundle1.getString("SUMMARY");
      str2 = localBundle1.getString("DTSTART");
      str3 = localBundle1.getString("DTEND");
      str4 = localBundle1.getString("LOCATION");
      str5 = localBundle1.getString("DESCRIPTION");
    }
    while (TextUtils.isEmpty(str1));
    String str6 = "BEGIN:VEVENT\r\nSUMMARY:" + str1 + "\r\n";
    if (!TextUtils.isEmpty(str2))
      str6 = str6 + "DTSTART:" + str2 + "\r\n";
    if (!TextUtils.isEmpty(str3))
      str6 = str6 + "DTEND:" + str3 + "\r\n";
    if (!TextUtils.isEmpty(str4))
      str6 = str6 + "LOCATION:" + str4 + "\r\n";
    if (!TextUtils.isEmpty(str5))
      str6 = str6 + "DESCRIPTION:" + str5 + "\r\n";
    this.contents = (str6 + "END:VEVENT");
    this.displayContents = this.contents;
    this.title = this.context.getString(2131493016);
    this.type = ParsedResultType.CALENDAR;
  }

  private static List<String> getAllBundleValues(Bundle paramBundle, String[] paramArrayOfString)
  {
    ArrayList localArrayList = new ArrayList(paramArrayOfString.length);
    int i = paramArrayOfString.length;
    int j = 0;
    if (j >= i)
      return localArrayList;
    Object localObject1 = paramBundle.get(paramArrayOfString[j]);
    if (localObject1 == null);
    for (Object localObject2 = null; ; localObject2 = localObject1.toString())
    {
      localArrayList.add(localObject2);
      j++;
      break;
    }
  }

  private static String guessAppropriateEncoding(CharSequence paramCharSequence)
  {
    for (int i = 0; ; i++)
    {
      if (i >= paramCharSequence.length())
        return null;
      if (paramCharSequence.charAt(i) > 'ÿ')
        return "UTF-8";
    }
  }

  public Bitmap encodeAsBitmap()
    throws WriterException
  {
    String str1 = this.contents;
    if (str1 == null)
      return null;
    String str2 = guessAppropriateEncoding(str1);
    EnumMap localEnumMap = null;
    if (str2 != null)
    {
      localEnumMap = new EnumMap(EncodeHintType.class);
      localEnumMap.put(EncodeHintType.CHARACTER_SET, str2);
    }
    BitMatrix localBitMatrix;
    int[] arrayOfInt;
    int k;
    int m;
    int n;
    while (true)
    {
      int i;
      try
      {
        localBitMatrix = new MultiFormatWriter().encode(str1, this.format, this.dimension, this.dimension, localEnumMap);
        i = localBitMatrix.getWidth();
        int j = localBitMatrix.getHeight();
        arrayOfInt = new int[i * j];
        k = 0;
        if (k >= j)
        {
          Bitmap localBitmap = Bitmap.createBitmap(i, j, Config.ARGB_8888);
          localBitmap.setPixels(arrayOfInt, 0, i, 0, 0, i, j);
          return localBitmap;
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        return null;
      }
      m = k * i;
      n = 0;
      if (n < i)
        break;
      k++;
    }
    int i1 = m + n;
    if (localBitMatrix.get(n, k));
    for (int i2 = -16777216; ; i2 = -1)
    {
      arrayOfInt[i1] = i2;
      n++;
      break;
    }
  }

  public String getContents()
  {
    return this.contents;
  }

  public String getDisplayContents()
  {
    return this.displayContents;
  }

  public BarcodeFormat getFormat()
  {
    return this.format;
  }

  public String getTitle()
  {
    return this.title;
  }

  public ParsedResultType getType()
  {
    return this.type;
  }

  boolean isUseVCard()
  {
    return this.useVCard;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.util.encode.QRCodeEncoder
 * JD-Core Version:    0.6.0
 */