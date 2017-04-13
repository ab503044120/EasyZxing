package org.huihui.zxingimprove;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import android.widget.ViewSwitcher.ViewFactory;
import com.viewpagerindicator.CirclePageIndicator;
import mobi.thinkchange.android.superqrcode.data.DataCollection;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;
import mobi.thinkchange.android.superqrcode.util.PreferencesUtils;

public class HelpActivity extends FragmentActivity
  implements OnClickListener, ViewFactory
{
  static final int PAGER_SIZE = 4;
  private int count = 0;
  private DataCollection dataCollection;
  String from = null;
  private ViewSwitcher mSwitcher;
  private PreferencesUtils preferencesUtils;
  private Button startButton;

  private void initFlow()
  {
    Intent localIntent = getIntent();
    if (localIntent != null)
      this.from = localIntent.getStringExtra("from");
    if (this.from != null)
      this.startButton.setText(getResources().getString(2131493113));
    boolean bool = this.preferencesUtils.getIsFirstUse();
    if ((this.from == null) && (!bool))
      jump2MainActivity();
  }

  private void initViews()
  {
    this.dataCollection = ((DataCollection)getApplicationContext());
    this.preferencesUtils = new PreferencesUtils(this);
    this.preferencesUtils.setIsShowAd(Boolean.valueOf(true));
    HelpPagerAdapter localHelpPagerAdapter = new HelpPagerAdapter(getSupportFragmentManager());
    ViewPager localViewPager = (ViewPager)findViewById(2131623972);
    localViewPager.setAdapter(localHelpPagerAdapter);
    CirclePageIndicator localCirclePageIndicator = (CirclePageIndicator)findViewById(2131623975);
    localCirclePageIndicator.setFillColor(-12935383);
    localCirclePageIndicator.setPageColor(-6250336);
    localCirclePageIndicator.setStrokeWidth(0.0F);
    localCirclePageIndicator.setViewPager(localViewPager);
    localCirclePageIndicator.setOnPageChangeListener(new TextChangeOnPageChangeListener());
    findViewById(2131623974).setOnClickListener(this);
    this.startButton = ((Button)findViewById(2131623974));
    this.mSwitcher = ((ViewSwitcher)findViewById(2131623973));
    this.mSwitcher.setFactory(this);
    Animation localAnimation1 = AnimationUtils.loadAnimation(this, 17432576);
    Animation localAnimation2 = AnimationUtils.loadAnimation(this, 17432577);
    this.mSwitcher.setInAnimation(localAnimation1);
    this.mSwitcher.setOutAnimation(localAnimation2);
    updateSwitcher(0);
  }

  private void jump2MainActivity()
  {
    this.preferencesUtils.setIsFirstUse(false);
    MiscUtils.myStartActivity(this, MainActivity.class, getIntent());
    finish();
  }

  private void sendParams()
  {
  }

  private void updateSwitcher(int paramInt)
  {
    View localView = this.mSwitcher.getNextView();
    TextView localTextView1 = (TextView)localView.findViewById(2131624192);
    TextView localTextView2 = (TextView)localView.findViewById(2131624193);
    localTextView1.setText(2131493136 + paramInt);
    localTextView2.setText(2131493140 + paramInt);
    this.mSwitcher.showNext();
  }

  public View makeView()
  {
    return getLayoutInflater().inflate(2130903097, null);
  }

  public void onBackPressed()
  {
    if (this.from == null)
    {
      jump2MainActivity();
      return;
    }
    finish();
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131623974)
    {
      if (this.from != null)
        finish();
    }
    else
      return;
    jump2MainActivity();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903040);
    initViews();
    initFlow();
  }

  protected void onPause()
  {
    super.onPause();
    sendParams();
  }

  public class HelpPagerAdapter extends FragmentStatePagerAdapter
  {
    public HelpPagerAdapter(FragmentManager arg2)
    {
      super();
    }

    public int getCount()
    {
      return 4;
    }

    public Fragment getItem(int paramInt)
    {
      HelpPagerFragment localHelpPagerFragment = new HelpPagerFragment();
      Bundle localBundle = new Bundle();
      localBundle.putInt("image", 2130837591 + paramInt);
      localHelpPagerFragment.setArguments(localBundle);
      return localHelpPagerFragment;
    }

    public CharSequence getPageTitle(int paramInt)
    {
      return "OBJECT " + (paramInt + 1);
    }
  }

  public static class HelpPagerFragment extends Fragment
  {
    public static final String ARG_IMAGE = "image";

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
      View localView = paramLayoutInflater.inflate(2130903041, paramViewGroup, false);
      int i = getArguments().getInt("image");
      ((ImageView)localView.findViewById(2131623976)).setImageResource(i);
      return localView;
    }
  }

  class TextChangeOnPageChangeListener
    implements OnPageChangeListener
  {
    TextChangeOnPageChangeListener()
    {
    }

    public void onPageScrollStateChanged(int paramInt)
    {
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
    }

    public void onPageSelected(int paramInt)
    {
      HelpActivity localHelpActivity = HelpActivity.this;
      localHelpActivity.count = (1 + localHelpActivity.count);
      HelpActivity.this.updateSwitcher(paramInt);
    }
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.HelpActivity
 * JD-Core Version:    0.6.0
 */