package org.huihui.zxingimprove.fragment;

import android.support.v4.app.Fragment;
import mobi.thinkchange.android.superqrcode.fragment.scanfragmentstack.ScanFragmentChild01Scan;

public class ScanFragment extends AbstractStackableFragment
{
  protected Fragment getFirstFragment()
  {
    return new ScanFragmentChild01Scan();
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.ScanFragment
 * JD-Core Version:    0.6.0
 */