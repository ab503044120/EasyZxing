package org.huihui.zxingimprove.fragment.scanfragmentstack;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import com.google.zxing.client.result.AddressBookParsedResult;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;

public class ScanFragmentChild02ScanResultAddressBook extends AbstractScanFragmentChild02Scan
{
  private AddressBookParsedResult addressBookParsedResult;
  private String addressStr = "";
  private TextView addressTextView;
  private String emailStr = "";
  private TextView emailTextView;
  private String nameStr = "";
  private TextView nameTextView;
  private String noteStr = "";
  private TextView noteTextView;
  private String orgStr = "";
  private TextView orgTextView;
  private String phoneNumber = "";
  private TextView phoneNumberTextView;
  private String shareText;
  private String urlStr = "";
  private TextView urlTextView;

  private void initData()
  {
    if (this.addressBookParsedResult.getNames() != null)
      this.nameStr = this.addressBookParsedResult.getNames()[0];
    if (this.addressBookParsedResult.getOrg() != null)
      this.orgStr = this.addressBookParsedResult.getOrg();
    if (this.addressBookParsedResult.getPhoneNumbers() != null)
      this.phoneNumber = this.addressBookParsedResult.getPhoneNumbers()[0];
    if (this.addressBookParsedResult.getEmails() != null)
      this.emailStr = this.addressBookParsedResult.getEmails()[0];
    if (this.addressBookParsedResult.getAddresses() != null)
      this.addressStr = this.addressBookParsedResult.getAddresses()[0];
    if (this.addressBookParsedResult.getURLs() != null)
      this.urlStr = this.addressBookParsedResult.getURLs()[0];
    if (this.addressBookParsedResult.getNote() != null)
      this.noteStr = this.addressBookParsedResult.getNote();
    this.nameTextView.setText(this.nameStr);
    this.orgTextView.setText(this.orgStr);
    this.phoneNumberTextView.setText(this.phoneNumber);
    this.emailTextView.setText(this.emailStr);
    this.addressTextView.setText(this.addressStr);
    this.urlTextView.setText(this.urlStr);
    this.noteTextView.setText(this.noteStr);
    this.shareText = (getResources().getString(2131493066) + this.nameStr + "\n" + getResources().getString(2131493067) + this.orgStr + "\n" + getResources().getString(2131493068) + this.phoneNumber + "\n" + getResources().getString(2131493069) + this.emailStr + "\n" + getResources().getString(2131493070) + this.addressStr + "\n" + getResources().getString(2131493071) + this.urlStr + "\n" + getResources().getString(2131493072) + this.noteStr);
  }

  private void initView(View paramView)
  {
    this.nameTextView = ((TextView)paramView.findViewById(2131624116));
    this.orgTextView = ((TextView)paramView.findViewById(2131624119));
    this.phoneNumberTextView = ((TextView)paramView.findViewById(2131624122));
    this.emailTextView = ((TextView)paramView.findViewById(2131624125));
    this.addressTextView = ((TextView)paramView.findViewById(2131624128));
    this.urlTextView = ((TextView)paramView.findViewById(2131624131));
    this.noteTextView = ((TextView)paramView.findViewById(2131624134));
  }

  protected void clickBottomButton()
  {
    MiscUtils.addContact(this.mContext, this.addressBookParsedResult.getNames(), this.addressBookParsedResult.getPhoneNumbers(), this.addressBookParsedResult.getEmails(), this.noteStr, this.addressStr, this.orgStr, this.addressBookParsedResult.getURLs());
  }

  protected int getBottomButtonStringResource()
  {
    return 2131493065;
  }

  protected int getLayoutResource()
  {
    return 2130903077;
  }

  protected void getParsedResult(ParsedResult paramParsedResult)
  {
    this.addressBookParsedResult = ((AddressBookParsedResult)paramParsedResult);
  }

  protected int getResultImageResource()
  {
    return 2130837607;
  }

  protected ParsedResultType getScanType()
  {
    return ParsedResultType.ADDRESSBOOK;
  }

  protected String getShareText()
  {
    return this.shareText;
  }

  protected int getTitleStringResource()
  {
    return 2131493017;
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
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.scanfragmentstack.ScanFragmentChild02ScanResultAddressBook
 * JD-Core Version:    0.6.0
 */