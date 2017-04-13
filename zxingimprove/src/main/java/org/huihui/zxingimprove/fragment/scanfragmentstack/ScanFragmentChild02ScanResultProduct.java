package org.huihui.zxingimprove.fragment.scanfragmentstack;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.ProductParsedResult;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;

public class ScanFragmentChild02ScanResultProduct extends AbstractScanFragmentChild02Scan
{
  private String productId = "";
  private TextView productNumberTextView;
  private ProductParsedResult productParsedResult;
  private String shareText;

  private void initData()
  {
    if (this.productParsedResult.getProductID() != null)
      this.productId = this.productParsedResult.getProductID();
    this.productNumberTextView.setText(this.productId);
    this.shareText = (getResources().getString(2131493056) + this.productId);
  }

  private void initView(View paramView)
  {
    this.productNumberTextView = ((TextView)paramView.findViewById(2131624163));
  }

  protected void clickBottomButton()
  {
    MiscUtils.searchProduct(this.mContext, this.productId);
  }

  protected int getBottomButtonStringResource()
  {
    return 2131493055;
  }

  protected int getLayoutResource()
  {
    return 2130903084;
  }

  protected void getParsedResult(ParsedResult paramParsedResult)
  {
    this.productParsedResult = ((ProductParsedResult)paramParsedResult);
  }

  protected int getResultImageResource()
  {
    return 2130837678;
  }

  protected ParsedResultType getScanType()
  {
    return ParsedResultType.PRODUCT;
  }

  protected String getShareText()
  {
    return this.shareText;
  }

  protected int getTitleStringResource()
  {
    return 2131493021;
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
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.scanfragmentstack.ScanFragmentChild02ScanResultProduct
 * JD-Core Version:    0.6.0
 */