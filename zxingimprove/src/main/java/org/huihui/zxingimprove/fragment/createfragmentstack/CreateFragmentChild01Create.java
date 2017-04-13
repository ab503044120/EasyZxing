package org.huihui.zxingimprove.fragment.createfragmentstack;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import mobi.thinkchange.android.superqrcode.FeedbackActivity;
import mobi.thinkchange.android.superqrcode.SettingsActivity;
import mobi.thinkchange.android.superqrcode.data.DataCollection;
import mobi.thinkchange.android.superqrcode.data.HistorySource;
import mobi.thinkchange.android.superqrcode.fragment.IStackableFragment;
import mobi.thinkchange.android.superqrcode.fragment.historyfragmentstack.HistoryFragmentChild02;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;

public class CreateFragmentChild01Create extends Fragment
  implements OnClickListener, OnItemClickListener
{
  private DataCollection dataCollection;
  private ImageView feedbackButton;
  private GridView gridView;
  private int[] icons = { 2130837616, 2130837689, 2130837633, 2130837690, 2130837607, 2130837683, 2130837676, 2130837675, 2130837678, 2130837669, 2130837599, 2130837640 };
  private Activity mContext;
  private MiscUtils miscUtils;
  private Resources resources;
  private ImageView settingsButton;
  private int[] text = { 2131493012, 2131493013, 2131493014, 2131493015, 2131493017, 2131493018, 2131493019, 2131493020, 2131493021, 2131493053, 2131493016, 2131493023 };
  private String[] texts = new String[12];
  private TextView titleText;
  public FragmentTransaction transaction;
  private View view;

  private void init()
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; ; i++)
    {
      if (i >= this.texts.length)
      {
        SimpleAdapter localSimpleAdapter = new SimpleAdapter(this.mContext, localArrayList, 2130903091, new String[] { "icon", "text" }, new int[] { 2131624181, 2131624182 });
        this.gridView.setAdapter(localSimpleAdapter);
        return;
      }
      HashMap localHashMap = new HashMap();
      localHashMap.put("icon", Integer.valueOf(this.icons[i]));
      localHashMap.put("text", this.texts[i]);
      localArrayList.add(localHashMap);
    }
  }

  private void sendParams()
  {
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131624191)
    {
      this.dataCollection.setClickFeedbackButton("1");
      MiscUtils.startActivity(this.mContext, FeedbackActivity.class, "crearte");
    }
    do
      return;
    while (paramView.getId() != 2131624190);
    MiscUtils.startActivity(this.mContext, SettingsActivity.class, null);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mContext = getActivity();
    this.dataCollection = ((DataCollection)this.mContext.getApplicationContext());
    this.resources = getActivity().getResources();
    for (int i = 0; ; i++)
    {
      if (i >= this.texts.length)
        return;
      this.texts[i] = this.resources.getString(this.text[i]);
    }
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.view = paramLayoutInflater.inflate(2130903047, paramViewGroup, false);
    this.titleText = ((TextView)this.view.findViewById(2131624016));
    this.titleText.setText(getResources().getString(2131493006));
    this.settingsButton = ((ImageView)this.view.findViewById(2131624190));
    this.settingsButton.setOnClickListener(this);
    this.feedbackButton = ((ImageView)this.view.findViewById(2131624191));
    this.feedbackButton.setOnClickListener(this);
    this.gridView = ((GridView)this.view.findViewById(2131624001));
    this.gridView.setOnItemClickListener(this);
    this.gridView.setSelector(new ColorDrawable(0));
    init();
    return this.view;
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    String str1 = "";
    String str2 = null;
    switch (paramInt)
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    case 9:
    case 10:
    }
    while (true)
    {
      if (str2 != null)
      {
        Bundle localBundle1 = new Bundle();
        localBundle1.putString("ENCODE_TYPE", str2);
        Fragment localFragment = CreateFragmentChild02Factory.createTargetFragment(str2);
        localFragment.setArguments(localBundle1);
        ((IStackableFragment)getParentFragment()).addFragmentToStack(localFragment);
      }
      if (paramInt == 0)
      {
        str1 = "0";
        HistoryFragmentChild02 localHistoryFragmentChild02 = new HistoryFragmentChild02();
        Bundle localBundle2 = new Bundle();
        localBundle2.putString("title", getString(2131493012));
        localBundle2.putInt("type", 4098);
        int[] arrayOfInt = new int[1];
        arrayOfInt[0] = HistorySource.CREATE.ordinal();
        localBundle2.putIntArray("source", arrayOfInt);
        localHistoryFragmentChild02.setArguments(localBundle2);
        ((IStackableFragment)getParentFragment()).addFragmentToStack(localHistoryFragmentChild02);
      }
      if (paramInt == 11)
      {
        str1 = "11";
        MiscUtils.openBrowser(this.mContext, getResources().getString(2131492997));
      }
      this.dataCollection.setClickCreateType(str1);
      this.miscUtils.playSound();
      sendParams();
      return;
      str2 = "URL_KEY";
      str1 = "1";
      continue;
      str2 = "EMAIL_TYPE";
      str1 = "2";
      continue;
      str2 = "WIFI_TYPE";
      str1 = "3";
      continue;
      str2 = "TEXT_TYPE";
      str1 = "4";
      continue;
      str2 = "CONTACT_TYPE";
      str1 = "5";
      continue;
      str2 = "SMS_TYPE";
      str1 = "6";
      continue;
      str2 = "PRODUCT_TYPE";
      str1 = "7";
      continue;
      str2 = "PHONE_TYPE";
      str1 = "8";
      continue;
      str2 = "LOCATION_TYPE";
      str1 = "9";
      continue;
      str2 = "CALENDAR_TYPE";
      str1 = "10";
    }
  }

  public void onResume()
  {
    super.onResume();
    this.dataCollection.setClickCreateType("");
    this.miscUtils = new MiscUtils(this.mContext);
    this.miscUtils.initSound(2131099650);
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.createfragmentstack.CreateFragmentChild01Create
 * JD-Core Version:    0.6.0
 */