package org.huihui.zxingimprove.fragment.scanfragmentstack;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.SMSParsedResult;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;

public class ScanFragmentChild02ScanResultSMS extends AbstractScanFragmentChild02Scan
{
  private String message = "";
  private TextView messageText;
  private String phoneNumber = "";
  private TextView phoneNumberTextView;
  private String shareText;
  private SMSParsedResult smsParsedResult;

  private void initData()
  {
    if (this.smsParsedResult.getNumbers() != null)
      this.phoneNumber = this.smsParsedResult.getNumbers()[0];
    if (this.smsParsedResult.getBody() != null)
      this.message = this.smsParsedResult.getBody();
    this.phoneNumberTextView.setText(this.phoneNumber);
    this.messageText.setText(this.message);
    this.shareText = (getResources().getString(2131493045) + this.phoneNumber + "\n" + getResources().getString(2131493046) + this.message);
  }

  private void initView(View paramView)
  {
    this.phoneNumberTextView = ((TextView)paramView.findViewById(2131624164));
    this.messageText = ((TextView)paramView.findViewById(2131624166));
  }

  protected void clickBottomButton()
  {
    MiscUtils.sendSMS(this.mContext, this.phoneNumber, this.message);
  }

  protected int getBottomButtonStringResource()
  {
    return 2131493047;
  }

  protected int getLayoutResource()
  {
    return 2130903085;
  }

  protected void getParsedResult(ParsedResult paramParsedResult)
  {
    this.smsParsedResult = ((SMSParsedResult)paramParsedResult);
  }

  protected int getResultImageResource()
  {
    return 2130837676;
  }

  protected ParsedResultType getScanType()
  {
    return ParsedResultType.SMS;
  }

  protected String getShareText()
  {
    return this.shareText;
  }

  protected int getTitleStringResource()
  {
    return 2131493019;
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
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.scanfragmentstack.ScanFragmentChild02ScanResultSMS
 * JD-Core Version:    0.6.0
 */