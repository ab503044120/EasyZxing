package org.huihui.zxingimprove.fragment.scanfragmentstack;

import android.support.v4.app.Fragment;
import com.google.zxing.client.result.ParsedResultType;

public class ScanFragmentChild02Factory
{
  public static Fragment sacanTargetFragment(ParsedResultType paramParsedResultType)
  {
    switch ($SWITCH_TABLE$com$google$zxing$client$result$ParsedResultType()[paramParsedResultType.ordinal()])
    {
    default:
      return new ScanFragmentChild02ScanResultText();
    case 1:
      return new ScanFragmentChild02ScanResultAddressBook();
    case 2:
      return new ScanFragmentChild02ScanResultEmail();
    case 10:
      return new ScanFragmentChild02ScanResultWifi();
    case 8:
      return new ScanFragmentChild02ScanResultSMS();
    case 7:
      return new ScanFragmentChild02ScanResultTel();
    case 9:
      return new ScanFragmentChild02ScanResultCalendar();
    case 5:
      return new ScanFragmentChild02ScanResultText();
    case 4:
      return new ScanFragmentChild02ScanResultUrl();
    case 6:
      return new ScanFragmentChild02ScanResultGeo();
    case 3:
      return new ScanFragmentChild02ScanResultProduct();
    case 11:
    }
    return new ScanFragmentChild02ScanResultISBN();
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.scanfragmentstack.ScanFragmentChild02Factory
 * JD-Core Version:    0.6.0
 */