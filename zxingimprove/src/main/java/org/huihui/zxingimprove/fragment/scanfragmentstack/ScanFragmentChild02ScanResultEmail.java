package org.huihui.zxingimprove.fragment.scanfragmentstack;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import com.google.zxing.client.result.EmailAddressParsedResult;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;

public class ScanFragmentChild02ScanResultEmail extends AbstractScanFragmentChild02Scan
{
  private String addressee = "";
  private TextView addresseeTextView;
  private String body = "";
  private TextView bodyText;
  private EmailAddressParsedResult emailAddressParsedResult;
  private String shareText;
  private String subject = "";
  private TextView subjectText;

  private void initData()
  {
    if (this.emailAddressParsedResult.getTos() != null)
      this.addressee = this.emailAddressParsedResult.getTos()[0];
    if (this.emailAddressParsedResult.getSubject() != null)
      this.subject = this.emailAddressParsedResult.getSubject();
    if (this.emailAddressParsedResult.getBody() != null)
      this.body = this.emailAddressParsedResult.getBody();
    this.addresseeTextView.setText(this.addressee);
    this.subjectText.setText(this.subject);
    this.bodyText.setText(this.body);
    this.shareText = (getResources().getString(2131493042) + this.addressee + "\n" + getResources().getString(2131493043) + this.subject + "\n" + getResources().getString(2131493044) + this.body);
  }

  private void initView(View paramView)
  {
    this.addresseeTextView = ((TextView)paramView.findViewById(2131624147));
    this.subjectText = ((TextView)paramView.findViewById(2131624148));
    this.bodyText = ((TextView)paramView.findViewById(2131624150));
  }

  protected void clickBottomButton()
  {
    MiscUtils.sendEmail(this.mContext, this.addressee, this.subject, this.body);
  }

  protected int getBottomButtonStringResource()
  {
    return 2131493041;
  }

  protected int getLayoutResource()
  {
    return 2130903080;
  }

  protected void getParsedResult(ParsedResult paramParsedResult)
  {
    this.emailAddressParsedResult = ((EmailAddressParsedResult)paramParsedResult);
  }

  protected int getResultImageResource()
  {
    return 2130837633;
  }

  protected ParsedResultType getScanType()
  {
    return ParsedResultType.EMAIL_ADDRESS;
  }

  protected String getShareText()
  {
    return this.shareText;
  }

  protected int getTitleStringResource()
  {
    return 2131493014;
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
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.scanfragmentstack.ScanFragmentChild02ScanResultEmail
 * JD-Core Version:    0.6.0
 */