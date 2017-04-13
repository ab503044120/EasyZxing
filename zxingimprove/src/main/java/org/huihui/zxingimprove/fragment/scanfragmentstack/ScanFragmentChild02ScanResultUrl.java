package org.huihui.zxingimprove.fragment.scanfragmentstack;

import android.view.View;
import android.widget.TextView;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.URIParsedResult;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;

public class ScanFragmentChild02ScanResultUrl extends AbstractScanFragmentChild02Scan
{
  private String shareText;
  private URIParsedResult uriParsedResult;
  private String url;
  private TextView urlTextView;

  private void initData()
  {
    if (this.uriParsedResult.getURI() != null)
      this.url = this.uriParsedResult.getURI();
    this.urlTextView.setText(this.url);
    this.shareText = this.url;
  }

  private void initView(View paramView)
  {
    this.urlTextView = ((TextView)paramView.findViewById(2131624170));
  }

  protected void clickBottomButton()
  {
    MiscUtils.openBrowser(this.mContext, this.url);
  }

  protected int getBottomButtonStringResource()
  {
    return 2131493037;
  }

  protected int getLayoutResource()
  {
    return 2130903088;
  }

  protected void getParsedResult(ParsedResult paramParsedResult)
  {
    this.uriParsedResult = ((URIParsedResult)paramParsedResult);
  }

  protected int getResultImageResource()
  {
    return 2130837689;
  }

  protected ParsedResultType getScanType()
  {
    return ParsedResultType.URI;
  }

  protected String getShareText()
  {
    return this.shareText;
  }

  protected int getTitleStringResource()
  {
    return 2131493013;
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
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.scanfragmentstack.ScanFragmentChild02ScanResultUrl
 * JD-Core Version:    0.6.0
 */