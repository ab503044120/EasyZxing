package org.huihui.zxingimprove.fragment.scanfragmentstack;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import mobi.thinkchange.android.superqrcode.data.DataCollection;
import mobi.thinkchange.android.superqrcode.data.HistoryDataManagerFactory;
import mobi.thinkchange.android.superqrcode.data.IHistoryDataManager;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;
import mobi.thinkchange.android.superqrcode.util.ToastUtil;

public abstract class AbstractScanFragmentChild02Scan extends Fragment
  implements OnClickListener
{
  private ImageView copyImg;
  protected DataCollection dataCollection;
  private IHistoryDataManager dataManager;
  private ImageView favoriteImg;
  private long historyItemId = -1L;
  private Boolean isFavorite = null;
  protected Activity mContext;
  protected MiscUtils miscUtils;
  private ParsedResult result;
  private Button resultBottomBt;
  private ImageView result_icon;
  private ImageView sharImg;
  private ImageView titleLeftImg;
  private ImageView titleRightImg;
  private TextView titleText;
  protected ToastUtil toastUtil;

  private void getBundleData(Bundle paramBundle)
  {
    ParsedResultType localParsedResultType = getScanType();
    if (paramBundle != null)
      this.result = ((ParsedResult)getArguments().getSerializable(localParsedResultType.toString()));
    getParsedResult(ScanResultChild02Factory.sacanTargetResult(this.result, localParsedResultType));
  }

  private void init()
  {
    this.miscUtils.initSound(2131099649);
    this.dataManager = HistoryDataManagerFactory.getInstance(getActivity());
    if (this.historyItemId == -1L)
      this.historyItemId = getArguments().getLong("history_item_id");
    if (this.isFavorite == null)
      this.isFavorite = Boolean.valueOf(this.dataManager.isFavorite(this.historyItemId));
    if (this.isFavorite.booleanValue());
    for (int i = 2130837637; ; i = 2130837687)
    {
      this.favoriteImg.setImageResource(i);
      this.titleText.setText(getResources().getString(getTitleStringResource()));
      initChildData();
      return;
    }
  }

  private void initView(View paramView)
  {
    RelativeLayout localRelativeLayout = (RelativeLayout)paramView.findViewById(2131624204);
    localRelativeLayout.addView(setLayoutContent(localRelativeLayout));
    this.titleLeftImg = ((ImageView)paramView.findViewById(2131624190));
    this.titleLeftImg.setImageResource(2130837685);
    this.titleLeftImg.setOnClickListener(this);
    this.titleRightImg = ((ImageView)paramView.findViewById(2131624191));
    this.titleRightImg.setVisibility(8);
    this.titleText = ((TextView)paramView.findViewById(2131624016));
    this.resultBottomBt = ((Button)paramView.findViewById(2131624135));
    this.resultBottomBt.setText(getResources().getString(getBottomButtonStringResource()));
    this.resultBottomBt.setOnClickListener(this);
    this.sharImg = ((ImageView)paramView.findViewById(2131624153));
    this.sharImg.setOnClickListener(this);
    this.favoriteImg = ((ImageView)paramView.findViewById(2131624154));
    this.favoriteImg.setOnClickListener(this);
    this.copyImg = ((ImageView)paramView.findViewById(2131624155));
    this.copyImg.setOnClickListener(this);
    this.result_icon = ((ImageView)paramView.findViewById(2131624151));
    this.result_icon.setImageResource(getResultImageResource());
    initChildView(paramView);
  }

  private View setLayoutContent(ViewGroup paramViewGroup)
  {
    LayoutInflater localLayoutInflater = (LayoutInflater)this.mContext.getSystemService("layout_inflater");
    View localView = null;
    if (localLayoutInflater != null)
      localView = localLayoutInflater.inflate(getLayoutResource(), paramViewGroup, false);
    return localView;
  }

  private void updateFavorite()
  {
    IHistoryDataManager localIHistoryDataManager = this.dataManager;
    long l = this.historyItemId;
    boolean bool1;
    boolean bool3;
    label52: int i;
    if (this.isFavorite.booleanValue())
    {
      bool1 = false;
      if (localIHistoryDataManager.setFavorite(l, bool1))
      {
        boolean bool2 = this.isFavorite.booleanValue();
        bool3 = false;
        if (!bool2)
          break label136;
        this.isFavorite = Boolean.valueOf(bool3);
        if (!this.isFavorite.booleanValue())
          break label142;
        i = 2130837637;
        label75: this.favoriteImg.setImageResource(i);
        if (!this.isFavorite.booleanValue())
          break label149;
      }
    }
    label136: label142: label149: for (int j = 2131493145; ; j = 2131493146)
    {
      this.miscUtils.showTipPopup(this.mContext, getResources().getString(j), this.favoriteImg, MiscUtils.getPopRatio(this.mContext));
      return;
      bool1 = true;
      break;
      bool3 = true;
      break label52;
      i = 2130837687;
      break label75;
    }
  }

  protected abstract void clickBottomButton();

  protected abstract int getBottomButtonStringResource();

  protected abstract int getLayoutResource();

  protected abstract void getParsedResult(ParsedResult paramParsedResult);

  protected abstract int getResultImageResource();

  protected abstract ParsedResultType getScanType();

  protected abstract String getShareText();

  protected abstract int getTitleStringResource();

  protected abstract void initChildData();

  protected abstract void initChildView(View paramView);

  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131624190)
      this.miscUtils.titleBack(getActivity());
    do
    {
      return;
      if (paramView.getId() == 2131624153)
      {
        MiscUtils.shareText(this.mContext, getString(2131493039), getShareText());
        return;
      }
      if (paramView.getId() == 2131624155)
      {
        MiscUtils.copy(this.mContext, getShareText());
        this.miscUtils.showTipPopup(this.mContext, getResources().getString(2131493040), this.copyImg, MiscUtils.getPopRatio(this.mContext));
        return;
      }
      if (paramView.getId() != 2131624135)
        continue;
      clickBottomButton();
      this.miscUtils.playSound();
      return;
    }
    while (paramView.getId() != 2131624154);
    updateFavorite();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mContext = getActivity();
    this.dataCollection = ((DataCollection)this.mContext.getApplicationContext());
    this.toastUtil = new ToastUtil(this.mContext);
    this.miscUtils = new MiscUtils(this.mContext);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    getBundleData(getArguments());
    View localView = paramLayoutInflater.inflate(2130903103, paramViewGroup, false);
    initView(localView);
    init();
    return localView;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.scanfragmentstack.AbstractScanFragmentChild02Scan
 * JD-Core Version:    0.6.0
 */