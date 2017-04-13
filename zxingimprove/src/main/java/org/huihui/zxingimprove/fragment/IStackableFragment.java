package org.huihui.zxingimprove.fragment;

import android.support.v4.app.Fragment;

public abstract interface IStackableFragment
{
  public abstract Fragment addFragmentToStack(Fragment paramFragment);

  public abstract boolean popFragment();
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.IStackableFragment
 * JD-Core Version:    0.6.0
 */