package org.huihui.zxingimprove.fragment.scanfragmentstack;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.TextParsedResult;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;
import mobi.thinkchange.android.superqrcode.util.ToastUtil;

public class ScanFragmentChild02ScanResultText extends AbstractScanFragmentChild02Scan
{
  private String shareText;
  private TextParsedResult textParsedResult;
  private String textStr;
  private TextView textView;

  private void initData()
  {
    if (this.textParsedResult.getText() != null)
      this.textStr = this.textParsedResult.getText();
    this.textView.setText(this.textStr);
    this.shareText = this.textStr;
  }

  private void initView(View paramView)
  {
    this.textView = ((TextView)paramView.findViewById(2131624168));
  }

  protected void clickBottomButton()
  {
    MiscUtils.copy(this.mContext, this.shareText);
    this.toastUtil.getBgToastInstance(getResources().getString(2131493040));
  }

  protected int getBottomButtonStringResource()
  {
    return 2131493052;
  }

  protected int getLayoutResource()
  {
    return 2130903087;
  }

  protected void getParsedResult(ParsedResult paramParsedResult)
  {
    this.textParsedResult = ((TextParsedResult)paramParsedResult);
  }

  protected int getResultImageResource()
  {
    return 2130837683;
  }

  protected ParsedResultType getScanType()
  {
    return ParsedResultType.TEXT;
  }

  protected String getShareText()
  {
    return this.shareText;
  }

  protected int getTitleStringResource()
  {
    return 2131493018;
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
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.scanfragmentstack.ScanFragmentChild02ScanResultText
 * JD-Core Version:    0.6.0
 */