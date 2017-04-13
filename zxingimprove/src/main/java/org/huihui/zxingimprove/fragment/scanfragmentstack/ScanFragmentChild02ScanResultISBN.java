package org.huihui.zxingimprove.fragment.scanfragmentstack;

import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.google.zxing.client.result.ISBNParsedResult;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;

public class ScanFragmentChild02ScanResultISBN extends AbstractScanFragmentChild02Scan
  implements OnClickListener
{
  private String isbn = "";
  private ISBNParsedResult isbnParsedResult;
  private TextView isbnTextView;
  private String shareText;

  private void initData()
  {
    if (this.isbnParsedResult.getISBN() != null)
      this.isbn = this.isbnParsedResult.getISBN();
    this.isbnTextView.setText(this.isbn);
    this.shareText = (getResources().getString(2131493064) + this.isbn);
  }

  private void initView(View paramView)
  {
    this.isbnTextView = ((TextView)paramView.findViewById(2131624161));
  }

  protected void clickBottomButton()
  {
    MiscUtils.searchISBN(this.mContext, this.isbn);
  }

  protected int getBottomButtonStringResource()
  {
    return 2131493063;
  }

  protected int getLayoutResource()
  {
    return 2130903083;
  }

  protected void getParsedResult(ParsedResult paramParsedResult)
  {
    this.isbnParsedResult = ((ISBNParsedResult)paramParsedResult);
  }

  protected int getResultImageResource()
  {
    return 2130837673;
  }

  protected ParsedResultType getScanType()
  {
    return ParsedResultType.ISBN;
  }

  protected String getShareText()
  {
    return this.shareText;
  }

  protected int getTitleStringResource()
  {
    return 2131493062;
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
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.scanfragmentstack.ScanFragmentChild02ScanResultISBN
 * JD-Core Version:    0.6.0
 */