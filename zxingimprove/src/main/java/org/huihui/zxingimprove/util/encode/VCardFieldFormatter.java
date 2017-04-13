package org.huihui.zxingimprove.util.encode;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class VCardFieldFormatter
  implements Formatter
{
  private static final Pattern NEWLINE;
  private static final Pattern RESERVED_VCARD_CHARS = Pattern.compile("([\\\\,;])");
  private final List<Map<String, Set<String>>> metadataForIndex;

  static
  {
    NEWLINE = Pattern.compile("\\n");
  }

  VCardFieldFormatter()
  {
    this(null);
  }

  VCardFieldFormatter(List<Map<String, Set<String>>> paramList)
  {
    this.metadataForIndex = paramList;
  }

  private static CharSequence formatMetadata(CharSequence paramCharSequence, Map<String, Set<String>> paramMap)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator1;
    if (paramMap != null)
      localIterator1 = paramMap.entrySet().iterator();
    Entry localEntry;
    Set localSet;
    do
    {
      if (!localIterator1.hasNext())
      {
        localStringBuilder.append(':').append(paramCharSequence);
        return localStringBuilder;
      }
      localEntry = (Entry)localIterator1.next();
      localSet = (Set)localEntry.getValue();
    }
    while ((localSet == null) || (localSet.isEmpty()));
    localStringBuilder.append(';').append((String)localEntry.getKey()).append('=');
    if (localSet.size() > 1)
      localStringBuilder.append('"');
    Iterator localIterator2 = localSet.iterator();
    localStringBuilder.append((String)localIterator2.next());
    while (true)
    {
      if (!localIterator2.hasNext())
      {
        if (localSet.size() <= 1)
          break;
        localStringBuilder.append('"');
        break;
      }
      localStringBuilder.append(',').append((String)localIterator2.next());
    }
  }

  public CharSequence format(CharSequence paramCharSequence, int paramInt)
  {
    String str1 = RESERVED_VCARD_CHARS.matcher(paramCharSequence).replaceAll("\\\\$1");
    String str2 = NEWLINE.matcher(str1).replaceAll("");
    if ((this.metadataForIndex == null) || (this.metadataForIndex.size() <= paramInt));
    for (Map localMap = null; ; localMap = (Map)this.metadataForIndex.get(paramInt))
      return formatMetadata(str2, localMap);
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.util.encode.VCardFieldFormatter
 * JD-Core Version:    0.6.0
 */