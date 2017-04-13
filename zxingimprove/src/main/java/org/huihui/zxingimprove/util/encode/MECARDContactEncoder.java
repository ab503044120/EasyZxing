package org.huihui.zxingimprove.util.encode;

import android.telephony.PhoneNumberUtils;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class MECARDContactEncoder extends ContactEncoder
{
  private static final char TERMINATOR = ';';

  public String[] encode(List<String> paramList1, String paramString1, List<String> paramList2, List<String> paramList3, List<String> paramList4, List<String> paramList5, List<String> paramList6, String paramString2)
  {
    StringBuilder localStringBuilder1 = new StringBuilder(100);
    localStringBuilder1.append("MECARD:");
    StringBuilder localStringBuilder2 = new StringBuilder(100);
    MECARDFieldFormatter localMECARDFieldFormatter = new MECARDFieldFormatter(null);
    appendUpToUnique(localStringBuilder1, localStringBuilder2, "N", paramList1, 1, new MECARDNameDisplayFormatter(null), localMECARDFieldFormatter, ';');
    append(localStringBuilder1, localStringBuilder2, "ORG", paramString1, localMECARDFieldFormatter, ';');
    appendUpToUnique(localStringBuilder1, localStringBuilder2, "ADR", paramList2, 1, null, localMECARDFieldFormatter, ';');
    appendUpToUnique(localStringBuilder1, localStringBuilder2, "TEL", paramList3, 2147483647, new MECARDTelDisplayFormatter(null), localMECARDFieldFormatter, ';');
    appendUpToUnique(localStringBuilder1, localStringBuilder2, "EMAIL", paramList5, 2147483647, null, localMECARDFieldFormatter, ';');
    appendUpToUnique(localStringBuilder1, localStringBuilder2, "URL", paramList6, 2147483647, null, localMECARDFieldFormatter, ';');
    append(localStringBuilder1, localStringBuilder2, "NOTE", paramString2, localMECARDFieldFormatter, ';');
    localStringBuilder1.append(';');
    String[] arrayOfString = new String[2];
    arrayOfString[0] = localStringBuilder1.toString();
    arrayOfString[1] = localStringBuilder2.toString();
    return arrayOfString;
  }

  private static class MECARDFieldFormatter
    implements Formatter
  {
    private static final Pattern NEWLINE;
    private static final Pattern RESERVED_MECARD_CHARS = Pattern.compile("([\\\\:;])");

    static
    {
      NEWLINE = Pattern.compile("\\n");
    }

    public CharSequence format(CharSequence paramCharSequence, int paramInt)
    {
      return ':' + NEWLINE.matcher(RESERVED_MECARD_CHARS.matcher(paramCharSequence).replaceAll("\\\\$1")).replaceAll("");
    }
  }

  private static class MECARDNameDisplayFormatter
    implements Formatter
  {
    private static final Pattern COMMA = Pattern.compile(",");

    public CharSequence format(CharSequence paramCharSequence, int paramInt)
    {
      return COMMA.matcher(paramCharSequence).replaceAll("");
    }
  }

  private static class MECARDTelDisplayFormatter
    implements Formatter
  {
    private static final Pattern NOT_DIGITS = Pattern.compile("[^0-9]+");

    public CharSequence format(CharSequence paramCharSequence, int paramInt)
    {
      return NOT_DIGITS.matcher(PhoneNumberUtils.formatNumber(paramCharSequence.toString())).replaceAll("");
    }
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.util.encode.MECARDContactEncoder
 * JD-Core Version:    0.6.0
 */