package org.huihui.zxingimprove.fragment.scanfragmentstack;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import com.google.zxing.client.result.GeoParsedResult;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;

public class ScanFragmentChild02ScanResultGeo extends AbstractScanFragmentChild02Scan
{
  private GeoParsedResult geoParsedResult;
  private String latitude = "";
  private TextView latitudeText;
  private String longitude = "";
  private TextView longitudeTextView;
  private String shareText;

  private void initData()
  {
    if (Double.valueOf(this.geoParsedResult.getLongitude()) != null)
      this.longitude = String.valueOf(this.geoParsedResult.getLongitude());
    if (Double.valueOf(this.geoParsedResult.getLatitude()) != null)
      this.latitude = String.valueOf(this.geoParsedResult.getLatitude());
    this.longitudeTextView.setText(this.longitude);
    this.latitudeText.setText(this.latitude);
    this.shareText = (getResources().getString(2131493048) + this.longitude + "\n" + getResources().getString(2131493049) + this.latitude);
  }

  private void initView(View paramView)
  {
    this.longitudeTextView = ((TextView)paramView.findViewById(2131624157));
    this.latitudeText = ((TextView)paramView.findViewById(2131624159));
  }

  protected void clickBottomButton()
  {
    MiscUtils.openMap(this.mContext, getGeoURI(), this.longitude, this.latitude);
  }

  protected int getBottomButtonStringResource()
  {
    return 2131493050;
  }

  public String getGeoURI()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("geo:");
    localStringBuilder.append(this.latitude);
    localStringBuilder.append(',');
    localStringBuilder.append(this.longitude);
    return localStringBuilder.toString();
  }

  protected int getLayoutResource()
  {
    return 2130903082;
  }

  protected void getParsedResult(ParsedResult paramParsedResult)
  {
    this.geoParsedResult = ((GeoParsedResult)paramParsedResult);
  }

  protected int getResultImageResource()
  {
    return 2130837675;
  }

  protected ParsedResultType getScanType()
  {
    return ParsedResultType.GEO;
  }

  protected String getShareText()
  {
    return this.shareText;
  }

  protected int getTitleStringResource()
  {
    return 2131493020;
  }

  protected void initChildData()
  {
    initData();
  }

  protected void initChildView(View paramView)
  {
    initView(paramView);
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.scanfragmentstack.ScanFragmentChild02ScanResultGeo
 * JD-Core Version:    0.6.0
 */