package org.huihui.zxingimprove.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class AbstractStackableFragment extends Fragment
  implements IStackableFragment
{
  public final AbstractStackableFragment addFragmentToStack(Fragment paramFragment)
  {
    FragmentTransaction localFragmentTransaction = getChildFragmentManager().beginTransaction();
    localFragmentTransaction.replace(getContainerViewId(), paramFragment);
    localFragmentTransaction.setTransition(4097);
    localFragmentTransaction.addToBackStack(null);
    localFragmentTransaction.commit();
    return this;
  }

  protected int getContainerViewId()
  {
    return 2131623984;
  }

  protected abstract Fragment getFirstFragment();

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (paramBundle == null)
      getChildFragmentManager().beginTransaction().add(getContainerViewId(), getFirstFragment()).commit();
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    Log.e(getTag(), "onCreateView");
    return paramLayoutInflater.inflate(2130903043, paramViewGroup, false);
  }

  public void onDestroy()
  {
    super.onDestroy();
    Log.e(getTag(), "onDestroy");
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    Log.e(getTag(), "onDestroyView");
  }

  public void onPause()
  {
    super.onPause();
    Log.e(getTag(), "onPause");
  }

  public void onResume()
  {
    super.onResume();
    Log.e(getTag(), "onResume");
  }

  public final boolean popFragment()
  {
    FragmentManager localFragmentManager = getChildFragmentManager();
    if (localFragmentManager.getBackStackEntryCount() > 0)
    {
      localFragmentManager.popBackStack();
      return true;
    }
    return false;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.AbstractStackableFragment
 * JD-Core Version:    0.6.0
 */