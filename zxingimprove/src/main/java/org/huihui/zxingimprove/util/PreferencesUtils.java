package org.huihui.zxingimprove.util;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.provider.Settings;

public class PreferencesUtils
{
  private static final String FEEDBACK_CONTACT = "feedback_contact";
  private static final String INIT_HISTORY = "init_history";
  private static final String IS_FIRST_USE = "is_first_use";
  private Activity activity;
  private Editor editor;
  private SharedPreferences preferences;

  public PreferencesUtils(Activity paramActivity)
  {
    this.activity = paramActivity;
    this.preferences = PreferenceManager.getDefaultSharedPreferences(paramActivity);
    this.editor = this.preferences.edit();
  }

  public String getFeedBackContact()
  {
    return this.preferences.getString("feedback_contact", "");
  }

  public boolean getIsFirstUse()
  {
    return this.preferences.getBoolean("is_first_use", true);
  }

  public boolean getIsInitHistory()
  {
    return this.preferences.getBoolean("init_history", false);
  }

  public boolean getIsOpenSound()
  {
    return this.preferences.getBoolean("app_sound", false);
  }

  public boolean getIsOpenVibrate()
  {
    return this.preferences.getBoolean("app_vibrate", false);
  }

  public boolean getIsShowAd()
  {
    return this.preferences.getBoolean("is_show_ad", true);
  }

  public boolean getIsShowHelp()
  {
    return this.preferences.getBoolean("show_help", false);
  }

  public void saveFeedBackContact(String paramString)
  {
    this.editor.putString("feedback_contact", paramString);
    this.editor.commit();
  }

  public void screenTimeout()
  {
    String str = this.preferences.getString("screenTimeout", "4");
    int i;
    if (str.equals("0"))
      i = 10000;
    while (true)
    {
      Settings.System.putInt(this.activity.getContentResolver(), "screen_off_timeout", i);
      return;
      if (str.equals("1"))
      {
        i = 30000;
        continue;
      }
      if (str.equals("2"))
      {
        i = 60000;
        continue;
      }
      if (str.equals("3"))
      {
        i = 180000;
        continue;
      }
      if (str.equals("4"))
      {
        i = 300000;
        continue;
      }
      if (str.equals("5"))
      {
        i = 600000;
        continue;
      }
      boolean bool = str.equals("6");
      i = 0;
      if (!bool)
        continue;
      i = 1800000;
    }
  }

  public void setIsFirstUse(boolean paramBoolean)
  {
    this.editor.putBoolean("is_first_use", paramBoolean);
    this.editor.commit();
  }

  public void setIsInitHistory(boolean paramBoolean)
  {
    this.editor.putBoolean("init_history", paramBoolean);
    this.editor.commit();
  }

  public void setIsShowAd(Boolean paramBoolean)
  {
    this.editor.putBoolean("is_show_ad", paramBoolean.booleanValue());
    this.editor.commit();
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.util.PreferencesUtils
 * JD-Core Version:    0.6.0
 */