package org.huihui.zxingimprove.fragment.historyfragmentstack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.google.zxing.client.result.ParsedResultType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mobi.thinkchange.android.superqrcode.FeedbackActivity;
import mobi.thinkchange.android.superqrcode.SettingsActivity;
import mobi.thinkchange.android.superqrcode.data.DataCollection;
import mobi.thinkchange.android.superqrcode.data.HistoryCounter;
import mobi.thinkchange.android.superqrcode.data.HistoryDataManagerFactory;
import mobi.thinkchange.android.superqrcode.data.HistorySource;
import mobi.thinkchange.android.superqrcode.data.IHistoryDataManager;
import mobi.thinkchange.android.superqrcode.fragment.IStackableFragment;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;

public class HistoryFragmentChild01 extends Fragment
  implements OnClickListener
{
  private static final int ICON_ID_ALL = 2130837659;
  private static final int[] ICON_ID_ARRAY = { 2130837656, 2130837657, 2130837663, 2130837667, 2130837666, 2130837661, 2130837665, 2130837664, 2130837655, 2130837668, 2130837660 };
  private static final int ICON_ID_FAVORITE = 2130837658;
  private static final int TITLE_ID_ALL = 2131493076;
  private static final int[] TITLE_ID_ARRAY = { 2131493017, 2131493014, 2131493021, 2131493013, 2131493018, 2131493020, 2131493053, 2131493019, 2131493016, 2131493015, 2131493062 };
  private static final int TITLE_ID_FAVORITE = 2131493075;
  private DataCollection dataCollection;
  private ImageView feedbackButton;
  private List<HistoryCounter> mHistoryCounters;
  private ImageView settingsButton;

  private int getResourceIdFromType(int paramInt1, int paramInt2)
  {
    int i = 4098;
    switch (paramInt1)
    {
    default:
      int j = ParsedResultType.values().length;
      if ((paramInt1 >= 0) && (paramInt1 < j))
      {
        if (paramInt2 != 2)
          break;
        i = ICON_ID_ARRAY[paramInt1];
      }
      else
      {
        return i;
      }
    case 4097:
      if (paramInt2 == 2)
        return 2130837658;
      return 2131493075;
    case 4098:
      if (paramInt2 == 2)
        return 2130837659;
      return 2131493076;
    }
    return TITLE_ID_ARRAY[paramInt1];
  }

  private void initListView(View paramView)
  {
    if (this.mHistoryCounters != null)
      this.mHistoryCounters.clear();
    this.mHistoryCounters = HistoryDataManagerFactory.getInstance(getActivity()).getHistoryCounters();
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; ; i++)
    {
      if (i >= this.mHistoryCounters.size())
      {
        SimpleAdapter localSimpleAdapter = new SimpleAdapter(getActivity(), localArrayList, 2130903098, new String[] { "itemIcon", "itemTitle", "itemNumber" }, new int[] { 2131624194, 2131624195, 2131624196 });
        ListView localListView = (ListView)paramView.findViewById(2131624186);
        localListView.setOnItemClickListener(new OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
          {
            TextView localTextView = (TextView)paramView.findViewById(2131624195);
            HistoryCounter localHistoryCounter = (HistoryCounter)HistoryFragmentChild01.this.mHistoryCounters.get(paramInt);
            HistoryFragmentChild01.this.dataCollection.setClickHistoryType(String.valueOf(localHistoryCounter.getType()));
            HistoryFragmentChild01.this.sendParams();
            int[] arrayOfInt;
            if (localHistoryCounter.getType() == 4097)
            {
              arrayOfInt = new int[3];
              arrayOfInt[0] = HistorySource.SCAN_FROM_CAMERA.ordinal();
              arrayOfInt[1] = HistorySource.SCAN_FROM_IMAGE.ordinal();
              arrayOfInt[2] = HistorySource.CREATE.ordinal();
            }
            while (true)
            {
              HistoryFragmentChild02 localHistoryFragmentChild02 = new HistoryFragmentChild02();
              Bundle localBundle = new Bundle();
              localBundle.putString("title", localTextView.getText().toString());
              localBundle.putInt("type", localHistoryCounter.getType());
              localBundle.putIntArray("source", arrayOfInt);
              localHistoryFragmentChild02.setArguments(localBundle);
              ((IStackableFragment)HistoryFragmentChild01.this.getParentFragment()).addFragmentToStack(localHistoryFragmentChild02);
              return;
              arrayOfInt = new int[2];
              arrayOfInt[0] = HistorySource.SCAN_FROM_CAMERA.ordinal();
              arrayOfInt[1] = HistorySource.SCAN_FROM_IMAGE.ordinal();
            }
          }
        });
        localListView.setAdapter(localSimpleAdapter);
        return;
      }
      HistoryCounter localHistoryCounter = (HistoryCounter)this.mHistoryCounters.get(i);
      HashMap localHashMap = new HashMap();
      localHashMap.put("itemIcon", Integer.valueOf(getResourceIdFromType(localHistoryCounter.getType(), 2)));
      localHashMap.put("itemTitle", getString(getResourceIdFromType(localHistoryCounter.getType(), 1)));
      localHashMap.put("itemNumber", String.valueOf(localHistoryCounter.getValue()));
      localArrayList.add(localHashMap);
    }
  }

  private void initTitleBar(View paramView)
  {
    ((TextView)paramView.findViewById(2131624016)).setText(2131493007);
  }

  private void sendParams()
  {
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131624191)
    {
      this.dataCollection.setClickFeedbackButton("1");
      MiscUtils.startActivity(getActivity(), FeedbackActivity.class, "history");
    }
    do
      return;
    while (paramView.getId() != 2131624190);
    MiscUtils.startActivity(getActivity(), SettingsActivity.class, null);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130903048, paramViewGroup, false);
    this.dataCollection = ((DataCollection)getActivity().getApplicationContext());
    this.settingsButton = ((ImageView)localView.findViewById(2131624190));
    this.settingsButton.setOnClickListener(this);
    this.feedbackButton = ((ImageView)localView.findViewById(2131624191));
    this.feedbackButton.setOnClickListener(this);
    initTitleBar(localView);
    initListView(localView);
    return localView;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.historyfragmentstack.HistoryFragmentChild01
 * JD-Core Version:    0.6.0
 */