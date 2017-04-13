package org.huihui.zxingimprove.util.encode;

import android.telephony.PhoneNumberUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class VCardTelDisplayFormatter
  implements Formatter
{
  private final List<Map<String, Set<String>>> metadataForIndex;

  VCardTelDisplayFormatter()
  {
    this(null);
  }

  VCardTelDisplayFormatter(List<Map<String, Set<String>>> paramList)
  {
    this.metadataForIndex = paramList;
  }

  private static CharSequence formatMetadata(CharSequence paramCharSequence, Map<String, Set<String>> paramMap)
  {
    if ((paramMap == null) || (paramMap.isEmpty()))
      return paramCharSequence;
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator1 = paramMap.entrySet().iterator();
    while (true)
    {
      if (!localIterator1.hasNext())
      {
        if (localStringBuilder.length() > 0)
          localStringBuilder.append(' ');
        localStringBuilder.append(paramCharSequence);
        return localStringBuilder;
      }
      Set localSet = (Set)((Entry)localIterator1.next()).getValue();
      if ((localSet == null) || (localSet.isEmpty()))
        continue;
      Iterator localIterator2 = localSet.iterator();
      localStringBuilder.append((String)localIterator2.next());
      while (localIterator2.hasNext())
        localStringBuilder.append(',').append((String)localIterator2.next());
    }
  }

  public CharSequence format(CharSequence paramCharSequence, int paramInt)
  {
    String str = PhoneNumberUtils.formatNumber(paramCharSequence.toString());
    if ((this.metadataForIndex == null) || (this.metadataForIndex.size() <= paramInt));
    for (Map localMap = null; ; localMap = (Map)this.metadataForIndex.get(paramInt))
      return formatMetadata(str, localMap);
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.util.encode.VCardTelDisplayFormatter
 * JD-Core Version:    0.6.0
 */