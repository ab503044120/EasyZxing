package org.huihui.zxingimprove.fragment.historyfragmentstack;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cn.pedant.SweetAlert.SweetAlertDialog.OnSweetClickListener;
import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.ResultParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mobi.thinkchange.android.superqrcode.data.HistoryDataManagerFactory;
import mobi.thinkchange.android.superqrcode.data.HistoryItem;
import mobi.thinkchange.android.superqrcode.data.HistorySource;
import mobi.thinkchange.android.superqrcode.data.IHistoryDataManager;
import mobi.thinkchange.android.superqrcode.fragment.IStackableFragment;
import mobi.thinkchange.android.superqrcode.fragment.scanfragmentstack.ScanFragmentChild02Factory;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;

public class HistoryFragmentChild02 extends Fragment
  implements OnItemClickListener, OnClickListener, OnItemLongClickListener
{
  private static final String HISTORY_DATE_PATTERN = "yyyy/MM/dd";
  private static final int[] ICON_BG_COLOR_ARRAY;
  private static final int[] ICON_ID_ARRAY = { 2130837643, 2130837644, 2130837648, 2130837652, 2130837651, 2130837646, 2130837650, 2130837649, 2130837642, 2130837653, 2130837645 };
  private SimpleAdapter adapter;
  private List<Map<String, Object>> data;
  private boolean isFromFavoriteListview = false;
  private List<HistoryItem> mHistoryItems;
  private ImageView titleLeftImg;
  private ImageView titleRightImg;
  private int type;

  static
  {
    ICON_BG_COLOR_ARRAY = new int[] { 2131165257, 2131165258, 2131165259, 2131165260, 2131165261, 2131165262, 2131165263, 2131165264, 2131165265, 2131165266, 2131165267 };
  }

  private void deleteItem(int paramInt, boolean paramBoolean)
  {
    IHistoryDataManager localIHistoryDataManager = HistoryDataManagerFactory.getInstance(getActivity());
    HistoryItem localHistoryItem = (HistoryItem)this.mHistoryItems.get(paramInt);
    boolean bool;
    if (paramBoolean)
    {
      long l = localHistoryItem.getId();
      if (localHistoryItem.isFavorite())
      {
        bool = false;
        localIHistoryDataManager.setFavorite(l, bool);
      }
    }
    while (true)
    {
      this.mHistoryItems.remove(paramInt);
      this.data.remove(paramInt);
      this.adapter.notifyDataSetChanged();
      return;
      bool = true;
      break;
      localIHistoryDataManager.deleteItem(localHistoryItem.getId(), localHistoryItem.getSource(), localHistoryItem.getType(), localHistoryItem.isFavorite());
    }
  }

  private void handleDecode(int paramInt)
  {
    HistoryItem localHistoryItem = (HistoryItem)this.mHistoryItems.get(paramInt);
    onDecoded(ResultParser.parseResult(new Result(localHistoryItem.getRawText(), null, null, localHistoryItem.getBarcodeFormat())), localHistoryItem);
  }

  private void initListView(View paramView)
  {
    this.type = getArguments().getInt("type", 4098);
    if (this.type == 4097)
      this.isFromFavoriteListview = true;
    int[] arrayOfInt = getArguments().getIntArray("source");
    int i;
    HistorySource[] arrayOfHistorySource1;
    HistorySource[] arrayOfHistorySource2;
    int j;
    label61: Resources localResources;
    if (arrayOfInt == null)
    {
      i = 0;
      arrayOfHistorySource1 = new HistorySource[i];
      arrayOfHistorySource2 = HistorySource.values();
      j = 0;
      if (j < arrayOfHistorySource1.length)
        break label267;
      IHistoryDataManager localIHistoryDataManager = HistoryDataManagerFactory.getInstance(getActivity());
      if (this.mHistoryItems != null)
        this.mHistoryItems.clear();
      this.mHistoryItems = localIHistoryDataManager.getHistoryItems(this.type, arrayOfHistorySource1);
      localResources = getResources();
      this.data = new ArrayList();
    }
    for (int k = 0; ; k++)
    {
      if (k >= this.mHistoryItems.size())
      {
        this.adapter = new SimpleAdapter(getActivity(), this.data, 2130903099, new String[] { "itemIcon", "itemTitle", "itemBody", "itemDate" }, new int[] { 2131624197, 2131624198, 2131624199, 2131624200 });
        this.adapter.setViewBinder(new ViewBinder()
        {
          public boolean setViewValue(View paramView, Object paramObject, String paramString)
          {
            if (((paramView instanceof ImageView)) && ((paramObject instanceof HistoryDetailItemIconInfo)))
            {
              ImageView localImageView = (ImageView)paramView;
              HistoryDetailItemIconInfo localHistoryDetailItemIconInfo = (HistoryDetailItemIconInfo)paramObject;
              localImageView.setImageResource(localHistoryDetailItemIconInfo.iconId);
              localImageView.setBackgroundColor(localHistoryDetailItemIconInfo.iconBgColor);
              return true;
            }
            return false;
          }
        });
        ListView localListView = (ListView)paramView.findViewById(2131624186);
        localListView.setOnItemClickListener(this);
        localListView.setOnItemLongClickListener(this);
        localListView.setAdapter(this.adapter);
        return;
        i = arrayOfInt.length;
        break;
        label267: arrayOfHistorySource1[j] = arrayOfHistorySource2[arrayOfInt[j]];
        j++;
        break label61;
      }
      HistoryItem localHistoryItem = (HistoryItem)this.mHistoryItems.get(k);
      int m = localHistoryItem.getType().ordinal();
      HashMap localHashMap = new HashMap();
      localHashMap.put("itemIcon", new HistoryDetailItemIconInfo(ICON_ID_ARRAY[m], localResources.getColor(ICON_BG_COLOR_ARRAY[m])));
      localHashMap.put("itemTitle", localHistoryItem.getName());
      localHashMap.put("itemBody", localHistoryItem.getDisplayString());
      localHashMap.put("itemDate", MiscUtils.getDateInString(localHistoryItem.getTime(), "yyyy/MM/dd"));
      this.data.add(localHashMap);
    }
  }

  private void initTitleBar(View paramView)
  {
    String str = getArguments().getString("title");
    ((TextView)paramView.findViewById(2131624016)).setText(str);
    this.titleLeftImg = ((ImageView)paramView.findViewById(2131624190));
    this.titleLeftImg.setImageResource(2130837685);
    this.titleLeftImg.setOnClickListener(this);
    this.titleRightImg = ((ImageView)paramView.findViewById(2131624191));
    this.titleRightImg.setVisibility(8);
  }

  private void onDecoded(ParsedResult paramParsedResult, HistoryItem paramHistoryItem)
  {
    String str = paramParsedResult.getType().toString();
    IStackableFragment localIStackableFragment = (IStackableFragment)getParentFragment();
    Bundle localBundle = new Bundle();
    Fragment localFragment = ScanFragmentChild02Factory.sacanTargetFragment(paramParsedResult.getType());
    localBundle.putSerializable(str, paramParsedResult);
    localBundle.putLong("history_item_id", paramHistoryItem.getId());
    localFragment.setArguments(localBundle);
    localIStackableFragment.addFragmentToStack(localFragment);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131624190:
    }
    ((IStackableFragment)getParentFragment()).popFragment();
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130903049, paramViewGroup, false);
    initTitleBar(localView);
    initListView(localView);
    return localView;
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    handleDecode(paramInt);
  }

  public boolean onItemLongClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    SweetAlertDialog localSweetAlertDialog = new SweetAlertDialog(getActivity());
    localSweetAlertDialog.showCancelButton(true).show();
    localSweetAlertDialog.setTitleText("提示");
    if (this.isFromFavoriteListview)
    {
      localSweetAlertDialog.setContentText(getResources().getString(2131493135));
      localSweetAlertDialog.setConfirmText(getResources().getString(2131492870));
    }
    while (true)
    {
      localSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener(paramInt, localSweetAlertDialog)
      {
        public void onClick(SweetAlertDialog paramSweetAlertDialog)
        {
          HistoryFragmentChild02.this.deleteItem(this.val$position, HistoryFragmentChild02.this.isFromFavoriteListview);
          this.val$deleteItemDialog.dismiss();
        }
      });
      return true;
      localSweetAlertDialog.setContentText(getResources().getString(2131493133));
      localSweetAlertDialog.setConfirmText(getResources().getString(2131493134));
    }
  }

  static class HistoryDetailItemIconInfo
  {
    int iconBgColor;
    int iconId;

    HistoryDetailItemIconInfo(int paramInt1, int paramInt2)
    {
      this.iconId = paramInt1;
      this.iconBgColor = paramInt2;
    }
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.historyfragmentstack.HistoryFragmentChild02
 * JD-Core Version:    0.6.0
 */