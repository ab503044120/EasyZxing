package org.huihui.zxingimprove.fragment.scanfragmentstack;

import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.TelParsedResult;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;

public class ScanFragmentChild02ScanResultTel extends AbstractScanFragmentChild02Scan
  implements OnClickListener
{
  private String phoneNumber = "";
  private TextView phoneNumberTextView;
  private String shareText;
  private TelParsedResult telParsedResult;
  private String telUri = "";

  private void initData()
  {
    if (this.telParsedResult.getNumber() != null)
      this.phoneNumber = this.telParsedResult.getNumber();
    if (this.telParsedResult.getTelURI() != null)
      this.telUri = this.telParsedResult.getTelURI();
    this.phoneNumberTextView.setText(this.phoneNumber);
    this.shareText = (getResources().getString(2131493045) + this.phoneNumber);
  }

  private void initView(View paramView)
  {
    this.phoneNumberTextView = ((TextView)paramView.findViewById(2131624164));
  }

  protected void clickBottomButton()
  {
    MiscUtils.Call(this.mContext, this.telUri);
  }

  protected int getBottomButtonStringResource()
  {
    return 2131493054;
  }

  protected int getLayoutResource()
  {
    return 2130903086;
  }

  protected void getParsedResult(ParsedResult paramParsedResult)
  {
    this.telParsedResult = ((TelParsedResult)paramParsedResult);
  }

  protected int getResultImageResource()
  {
    return 2130837669;
  }

  protected ParsedResultType getScanType()
  {
    return ParsedResultType.TEL;
  }

  protected String getShareText()
  {
    return this.shareText;
  }

  protected int getTitleStringResource()
  {
    return 2131493053;
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
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.scanfragmentstack.ScanFragmentChild02ScanResultTel
 * JD-Core Version:    0.6.0
 */