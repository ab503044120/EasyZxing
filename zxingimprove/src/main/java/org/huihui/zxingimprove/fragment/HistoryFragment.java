package org.huihui.zxingimprove.fragment;

import android.support.v4.app.Fragment;
import mobi.thinkchange.android.superqrcode.fragment.historyfragmentstack.HistoryFragmentChild01;

public class HistoryFragment extends AbstractStackableFragment
{
  protected Fragment getFirstFragment()
  {
    return new HistoryFragmentChild01();
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.HistoryFragment
 * JD-Core Version:    0.6.0
 */