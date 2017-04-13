package org.huihui.zxingimprove;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.io.File;
import java.util.HashMap;

import mobi.thinkchange.android.superqrcode.data.DataCollection;
import mobi.thinkchange.android.superqrcode.fragment.AboutFragment;
import mobi.thinkchange.android.superqrcode.fragment.CreateFragment;
import mobi.thinkchange.android.superqrcode.fragment.HistoryFragment;
import mobi.thinkchange.android.superqrcode.fragment.IStackableFragment;
import mobi.thinkchange.android.superqrcode.fragment.ScanFragment;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;
import mobi.thinkchange.android.superqrcode.util.PreferencesUtils;

public class MainActivity extends FragmentActivity
  implements OnClickListener, TabHost.OnTabChangeListener
{
  static final int CREATE_FRAGMENT_INDEX = 1;
  static Class<?>[] FRAGMENT_CLASS_ARRAY = { ScanFragment.class, CreateFragment.class, HistoryFragment.class, AboutFragment.class };
  static final int HISTORY_FRAGMENT_INDEX = 2;
  public static final int REAL_TABHOST_ID = 2131623977;
  static final int SCAN_FRAGMENT_INDEX;
  private String clickTabType = "0";
  private DataCollection dataCollection;
  private FragmentTabHost mTabHost;
  private MiscUtils miscUtils;
  private PreferencesUtils preferencesUtils;
  private int scanCount = 0;

  private View createTabItemView(LayoutInflater paramLayoutInflater, int paramInt)
  {
    View localView1 = paramLayoutInflater.inflate(2130903095, null);
    TypedArray localTypedArray = getResources().obtainTypedArray(2131558403);
    ((ImageView)localView1.findViewById(2131624187)).setImageDrawable(localTypedArray.getDrawable(paramInt));
    localTypedArray.recycle();
    ((TextView)localView1.findViewById(2131624188)).setText(getResources().getStringArray(2131558402)[paramInt]);
    View localView2 = localView1.findViewById(2131624189);
    if (paramInt == 3)
      localView2.setVisibility(8);
    return localView1;
  }

  private Fragment getActiveFragmentInFragmentTabHost()
  {
    return getSupportFragmentManager().findFragmentById(2131623977);
  }

  private Fragment getActiveFragmentInTabHost(int paramInt)
  {
    return getSupportFragmentManager().findFragmentByTag("android:switcher:2131623977:" + paramInt);
  }

  private void initViews()
  {
    this.preferencesUtils = new PreferencesUtils(this);
    this.dataCollection = ((DataCollection)getApplicationContext());
    this.miscUtils = new MiscUtils(this);
    this.miscUtils.initSound(2131099651);
    LayoutInflater localLayoutInflater = LayoutInflater.from(this);
    this.mTabHost = ((FragmentTabHost)findViewById(16908306));
    this.mTabHost.setOnTabChangedListener(this);
    this.mTabHost.setup(this, getSupportFragmentManager(), 2131623977);
    int i = FRAGMENT_CLASS_ARRAY.length;
    String[] arrayOfString = getResources().getStringArray(2131558404);
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      TabHost.TabSpec localTabSpec = this.mTabHost.newTabSpec(arrayOfString[j]).setIndicator(createTabItemView(localLayoutInflater, j));
      this.mTabHost.addTab(localTabSpec, FRAGMENT_CLASS_ARRAY[j], null);
    }
  }

  private void issueTCCKU()
  {
    findViewById(2131623978).setVisibility(8);
    findViewById(2131623981).setVisibility(8);
    findViewById(2131623978).setOnClickListener(this);
    findViewById(2131623981).setOnClickListener(this);
    String str = getIntent().getStringExtra("from");
    HashMap localHashMap = new HashMap();
    if ("tcu3.notifycku".equals(str))
      localHashMap.put("isnotifycku", "1");
    while (true)
    {
      this.preferencesUtils.setIsShowAd(Boolean.valueOf(false));
      return;
      if (!"tcu3.notifyopen".equals(str))
        continue;
      localHashMap.put("isnotifycku", "2");
    }
  }

  private boolean onExit()
  {
    return true;
  }

  private void sendParams()
  {
  }

  @SuppressLint({"SdCardPath"})
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == -1)
    {
      Bitmap localBitmap = BitmapFactory.decodeFile(new File(Environment.getExternalStorageDirectory(), "barcode_pic.jpg").getAbsolutePath());
      Intent localIntent = new Intent();
      localIntent.setAction("action.barcode_bitmap");
      Bundle localBundle = new Bundle();
      localBundle.putParcelable("barcode_bitmap", localBitmap);
      localIntent.putExtras(localBundle);
      sendBroadcast(localIntent);
    }
    Log.e("main_requestCode", paramInt1);
    Log.e("main_resultCode", paramInt2);
  }

  public void onBackPressed()
  {
    switch (this.mTabHost.getCurrentTab())
    {
    default:
    case 0:
    case 1:
    case 2:
    }
    Fragment localFragment;
    do
    {
      if (onExit())
        super.onBackPressed();
      return;
      localFragment = getActiveFragmentInFragmentTabHost();
    }
    while ((!(localFragment instanceof IStackableFragment)) || (!((IStackableFragment)localFragment).popFragment()));
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131623978:
    case 2131623979:
    case 2131623980:
    case 2131623981:
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903042);
    initViews();
    this.mTabHost.setCurrentTab(0);
    if (this.preferencesUtils.getIsShowAd())
      issueTCCKU();
  }

  protected void onResume()
  {
    super.onResume();
    this.miscUtils.initSound(2131099651);
  }

  protected void onStop()
  {
    super.onStop();
  }

  public void onTabChanged(String paramString)
  {
    String[] arrayOfString = getResources().getStringArray(2131558404);
    if (paramString.equals(arrayOfString[0]))
    {
      this.clickTabType = "0";
      this.scanCount = (1 + this.scanCount);
    }
    while (true)
    {
      sendParams();
      this.miscUtils.playSound();
      return;
      if (paramString.equals(arrayOfString[1]))
      {
        this.clickTabType = "1";
        continue;
      }
      if (paramString.equals(arrayOfString[2]))
      {
        this.clickTabType = "2";
        continue;
      }
      this.clickTabType = "3";
    }
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.MainActivity
 * JD-Core Version:    0.6.0
 */