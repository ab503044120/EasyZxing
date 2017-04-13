package org.huihui.zxingimprove.fragment;

import android.support.v4.app.Fragment;
import mobi.thinkchange.android.superqrcode.fragment.createfragmentstack.CreateFragmentChild01Create;

public class CreateFragment extends AbstractStackableFragment
{
  protected Fragment getFirstFragment()
  {
    return new CreateFragmentChild01Create();
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.CreateFragment
 * JD-Core Version:    0.6.0
 */