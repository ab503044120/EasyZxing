package org.huihui.zxingimprove;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;

public class SettingsActivity extends PreferenceActivity
  implements OnClickListener
{
  private ImageView titleLeftImg;
  private ImageView titleRightImg;
  private TextView titleText;

  private void init()
  {
    AnimationSet localAnimationSet = new AnimationSet(true);
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
    localAlphaAnimation.setDuration(50L);
    localAnimationSet.addAnimation(localAlphaAnimation);
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, -1.0F, 1, 0.0F);
    localTranslateAnimation.setDuration(100L);
    localAnimationSet.addAnimation(localTranslateAnimation);
    LayoutAnimationController localLayoutAnimationController = new LayoutAnimationController(localAnimationSet, 0.5F);
    getListView().setLayoutAnimation(localLayoutAnimationController);
  }

  private void setupViews()
  {
    this.titleText = ((TextView)findViewById(2131624016));
    this.titleText.setText(getResources().getString(2131493111));
    this.titleRightImg = ((ImageView)findViewById(2131624191));
    this.titleRightImg.setVisibility(8);
    this.titleLeftImg = ((ImageView)findViewById(2131624190));
    this.titleLeftImg.setImageResource(2130837685);
    this.titleLeftImg.setOnClickListener(this);
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131624190)
      finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    addPreferencesFromResource(2131034112);
    setContentView(2130903104);
    setupViews();
    init();
  }

  public boolean onPreferenceTreeClick(PreferenceScreen paramPreferenceScreen, Preference paramPreference)
  {
    if (paramPreference.getKey().equals("help"))
      MiscUtils.startActivity(this, HelpActivity.class, "settings_help");
    while (true)
    {
      return super.onPreferenceTreeClick(paramPreferenceScreen, paramPreference);
      if (paramPreference.getKey().equals("share"))
      {
        Intent localIntent = new Intent("android.intent.action.SEND");
        localIntent.setType("text/*");
        localIntent.putExtra("android.intent.extra.SUBJECT", getString(2131492999));
        localIntent.putExtra("android.intent.extra.TEXT", getString(2131493000));
        startActivity(Intent.createChooser(localIntent, getTitle()));
        continue;
      }
      if (!paramPreference.getKey().equals("feedback"))
        continue;
      MiscUtils.startActivity(this, FeedbackActivity.class, "settings_feedback");
    }
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.SettingsActivity
 * JD-Core Version:    0.6.0
 */