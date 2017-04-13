package org.huihui.zxingimprove.util.encode;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

abstract class ContactEncoder
{
  static void append(StringBuilder paramStringBuilder1, StringBuilder paramStringBuilder2, String paramString1, String paramString2, Formatter paramFormatter, char paramChar)
  {
    String str = trim(paramString2);
    if (str != null)
    {
      paramStringBuilder1.append(paramString1).append(paramFormatter.format(str, 0)).append(paramChar);
      paramStringBuilder2.append(str).append('\n');
    }
  }

  static void appendUpToUnique(StringBuilder paramStringBuilder1, StringBuilder paramStringBuilder2, String paramString, List<String> paramList, int paramInt, Formatter paramFormatter1, Formatter paramFormatter2, char paramChar)
  {
    if (paramList == null)
      return;
    int i = 0;
    HashSet localHashSet = new HashSet(2);
    int j = 0;
    label21: String str;
    if (j < paramList.size())
    {
      str = trim((String)paramList.get(j));
      if ((str != null) && (!str.isEmpty()) && (!localHashSet.contains(str)))
      {
        paramStringBuilder1.append(paramString).append(paramFormatter2.format(str, j)).append(paramChar);
        if (paramFormatter1 != null)
          break label145;
      }
    }
    label145: for (Object localObject = str; ; localObject = paramFormatter1.format(str, j))
    {
      paramStringBuilder2.append((CharSequence)localObject).append('\n');
      i++;
      if (i == paramInt)
        break;
      localHashSet.add(str);
      j++;
      break label21;
      break;
    }
  }

  static String trim(String paramString)
  {
    if (paramString == null)
      return null;
    String str = paramString.trim();
    if (str.isEmpty())
      str = null;
    return str;
  }

  abstract String[] encode(List<String> paramList1, String paramString1, List<String> paramList2, List<String> paramList3, List<String> paramList4, List<String> paramList5, List<String> paramList6, String paramString2);
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.util.encode.ContactEncoder
 * JD-Core Version:    0.6.0
 */