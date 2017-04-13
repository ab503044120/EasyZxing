package org.huihui.zxingimprove.fragment;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import mobi.thinkchange.android.superqrcode.FeedbackActivity;
import mobi.thinkchange.android.superqrcode.SettingsActivity;
import mobi.thinkchange.android.superqrcode.data.DataCollection;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;

public class AboutFragment extends Fragment
  implements OnClickListener
{
  private DataCollection dataCollection;
  private ImageView feedbackButton;
  private TextView feedbackText;
  private ImageView settingsButton;
  private TextView shareText;
  private TextView titleText;
  private TextView versionText;
  private TextView wbsiteText;

  private Runnable getVersionCode(TextView paramTextView)
  {
    return new Runnable(paramTextView)
    {
      public void run()
      {
        String str = null;
        int i = 1;
        try
        {
          PackageInfo localPackageInfo = AboutFragment.this.getActivity().getPackageManager().getPackageInfo(AboutFragment.this.getActivity().getPackageName(), 0);
          str = null;
          if (localPackageInfo != null)
          {
            str = localPackageInfo.versionName;
            i = localPackageInfo.versionCode;
          }
          this.val$view.append(str);
          this.val$view.append(".");
          this.val$view.append(String.valueOf(i));
          return;
        }
        catch (NameNotFoundException localNameNotFoundException)
        {
          while (true)
            localNameNotFoundException.printStackTrace();
        }
      }
    };
  }

  private void init()
  {
    this.titleText.setText(getResources().getString(2131493008));
  }

  private void initView(View paramView)
  {
    this.dataCollection = ((DataCollection)getActivity().getApplicationContext());
    this.titleText = ((TextView)paramView.findViewById(2131624016));
    this.wbsiteText = ((TextView)paramView.findViewById(2131623990));
    this.wbsiteText.setOnClickListener(this);
    this.shareText = ((TextView)paramView.findViewById(2131623991));
    this.shareText.setOnClickListener(this);
    this.feedbackText = ((TextView)paramView.findViewById(2131623992));
    this.feedbackText.setOnClickListener(this);
    this.versionText = ((TextView)paramView.findViewById(2131623989));
    this.feedbackButton = ((ImageView)paramView.findViewById(2131624191));
    this.feedbackButton.setOnClickListener(this);
    this.settingsButton = ((ImageView)paramView.findViewById(2131624190));
    this.settingsButton.setOnClickListener(this);
    getActivity().runOnUiThread(getVersionCode(this.versionText));
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131623990)
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getString(2131492997))));
    do
    {
      return;
      if (paramView.getId() == 2131623991)
      {
        Intent localIntent = new Intent("android.intent.action.SEND");
        localIntent.setType("text/*");
        localIntent.putExtra("android.intent.extra.SUBJECT", getString(2131492999));
        localIntent.putExtra("android.intent.extra.TEXT", getString(2131493000));
        startActivity(localIntent);
        return;
      }
      if (paramView.getId() == 2131623992)
      {
        MiscUtils.startActivity(getActivity(), FeedbackActivity.class, "about_feedback");
        return;
      }
      if (paramView.getId() != 2131624191)
        continue;
      this.dataCollection.setClickFeedbackButton("1");
      MiscUtils.startActivity(getActivity(), FeedbackActivity.class, "about");
      return;
    }
    while (paramView.getId() != 2131624190);
    MiscUtils.startActivity(getActivity(), SettingsActivity.class, null);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130903044, paramViewGroup, false);
    initView(localView);
    init();
    return localView;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.AboutFragment
 * JD-Core Version:    0.6.0
 */