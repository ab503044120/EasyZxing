package org.huihui.zxingimprove.fragment.scanfragmentstack;

import com.google.zxing.client.result.AddressBookParsedResult;
import com.google.zxing.client.result.CalendarParsedResult;
import com.google.zxing.client.result.EmailAddressParsedResult;
import com.google.zxing.client.result.GeoParsedResult;
import com.google.zxing.client.result.ISBNParsedResult;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.ProductParsedResult;
import com.google.zxing.client.result.SMSParsedResult;
import com.google.zxing.client.result.TelParsedResult;
import com.google.zxing.client.result.TextParsedResult;
import com.google.zxing.client.result.URIParsedResult;
import com.google.zxing.client.result.WifiParsedResult;

public class ScanResultChild02Factory
{
  static ParsedResult sacanTargetResult(ParsedResult paramParsedResult, ParsedResultType paramParsedResultType)
  {
    switch ($SWITCH_TABLE$com$google$zxing$client$result$ParsedResultType()[paramParsedResultType.ordinal()])
    {
    default:
      return (TextParsedResult)paramParsedResult;
    case 1:
      return (AddressBookParsedResult)paramParsedResult;
    case 2:
      return (EmailAddressParsedResult)paramParsedResult;
    case 10:
      return (WifiParsedResult)paramParsedResult;
    case 8:
      return (SMSParsedResult)paramParsedResult;
    case 7:
      return (TelParsedResult)paramParsedResult;
    case 9:
      return (CalendarParsedResult)paramParsedResult;
    case 5:
      return (TextParsedResult)paramParsedResult;
    case 4:
      return (URIParsedResult)paramParsedResult;
    case 6:
      return (GeoParsedResult)paramParsedResult;
    case 3:
      return (ProductParsedResult)paramParsedResult;
    case 11:
    }
    return (ISBNParsedResult)paramParsedResult;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.scanfragmentstack.ScanResultChild02Factory
 * JD-Core Version:    0.6.0
 */