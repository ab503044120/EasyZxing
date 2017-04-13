package org.huihui.zxingimprove.fragment.createfragmentstack;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.result.ParsedResultType;
import mobi.thinkchange.android.superqrcode.data.DataCollection;
import mobi.thinkchange.android.superqrcode.data.HistoryDataManagerFactory;
import mobi.thinkchange.android.superqrcode.data.HistoryItem;
import mobi.thinkchange.android.superqrcode.data.HistorySource;
import mobi.thinkchange.android.superqrcode.data.IHistoryDataManager;
import mobi.thinkchange.android.superqrcode.fragment.IStackableFragment;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;
import mobi.thinkchange.android.superqrcode.util.encode.BarCodeEncoder;
import mobi.thinkchange.android.superqrcode.util.encode.QRCodeEncoder;

public class CreateFragmentChild03CreateResult extends Fragment
  implements OnClickListener
{
  private static final String CREATE_NAME_PATTERN = "_yyyyMMdd_HHMMSS";
  private DataCollection dataCollection;
  private String defaultName = null;
  private Bitmap mBitmap;
  private long mCurrResultId = -1L;
  private ImageView mQrcodeView;
  private ImageView titleLeftImg;
  private ImageView titleRightImg;

  public static String getDefaultName(ParsedResultType paramParsedResultType)
  {
    String str = paramParsedResultType.name();
    return str.substring(0, Math.min(str.length(), 5)) + MiscUtils.getDateInString(System.currentTimeMillis(), "_yyyyMMdd_HHMMSS");
  }

  private void initGenQRCode(View paramView)
  {
    int i = getResources().getDimensionPixelSize(2131361808);
    try
    {
      String str1 = getArguments().getString("type_name");
      String str2;
      ParsedResultType localParsedResultType;
      BarcodeFormat localBarcodeFormat;
      String str3;
      if ((str1.equals(getResources().getString(2131493021))) || (str1.equals(getResources().getString(2131493062))))
      {
        str2 = getArguments().getString("ENCODE_DATA");
        if (!TextUtils.isEmpty(str2))
        {
          Bitmap localBitmap1 = BarCodeEncoder.creatBarcode(getActivity(), str2, getResources().getDimensionPixelSize(2131361810), getResources().getDimensionPixelSize(2131361811));
          this.mQrcodeView.setImageBitmap(localBitmap1);
          MiscUtils.saveFile(localBitmap1, "barcode.png");
          this.mBitmap = localBitmap1;
        }
        localParsedResultType = ParsedResultType.PRODUCT;
        localBarcodeFormat = BarCodeEncoder.barcodeFormat;
        str3 = str2;
      }
      String str4;
      for (Object localObject = str2; ; localObject = str4)
      {
        this.defaultName = getDefaultName(localParsedResultType);
        if (this.mCurrResultId == -1L)
        {
          HistoryItem localHistoryItem = new HistoryItem();
          localHistoryItem.setType(localParsedResultType);
          localHistoryItem.setName(this.defaultName);
          localHistoryItem.setBarcodeFormat(localBarcodeFormat);
          localHistoryItem.setRawText(str3);
          localHistoryItem.setDisplayString((String)localObject);
          localHistoryItem.setSource(HistorySource.CREATE);
          this.mCurrResultId = HistoryDataManagerFactory.getInstance(getActivity()).addItem(localHistoryItem);
        }
        if (this.defaultName == null)
          this.defaultName = getDefaultName(ParsedResultType.TEXT);
        EditText localEditText = (EditText)paramView.findViewById(2131623998);
        localEditText.setHint(this.defaultName);
        ImageView localImageView = this.mQrcodeView;
        1 local1 = new OnTouchListener(paramView, localEditText)
        {
          public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
          {
            CreateFragmentChild03CreateResult.this.removeEditTextFocus(this.val$view, this.val$nameEditText);
            return false;
          }
        };
        localImageView.setOnTouchListener(local1);
        2 local2 = new OnTouchListener(paramView, localEditText)
        {
          public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
          {
            CreateFragmentChild03CreateResult.this.removeEditTextFocus(this.val$view, this.val$nameEditText);
            return false;
          }
        };
        paramView.setOnTouchListener(local2);
        return;
        QRCodeEncoder localQRCodeEncoder = new QRCodeEncoder(getActivity(), getArguments(), i, false);
        Bitmap localBitmap2 = localQRCodeEncoder.encodeAsBitmap();
        this.mQrcodeView.setImageBitmap(localBitmap2);
        MiscUtils.saveFile(localBitmap2, "barcode.png");
        this.mBitmap = localBitmap2;
        localParsedResultType = localQRCodeEncoder.getType();
        localBarcodeFormat = localQRCodeEncoder.getFormat();
        str3 = localQRCodeEncoder.getContents();
        str4 = localQRCodeEncoder.getDisplayContents();
      }
    }
    catch (WriterException localWriterException)
    {
      while (true)
        localWriterException.printStackTrace();
    }
  }

  private void initTitleBar(View paramView)
  {
    TextView localTextView = (TextView)paramView.findViewById(2131624016);
    localTextView.setText(2131493032);
    localTextView.append(" ");
    localTextView.append(getArguments().getString("type_name"));
    this.titleLeftImg = ((ImageView)paramView.findViewById(2131624190));
    this.titleLeftImg.setImageResource(2130837685);
    this.titleLeftImg.setOnClickListener(this);
    this.titleRightImg = ((ImageView)paramView.findViewById(2131624191));
    this.titleRightImg.setVisibility(8);
  }

  private void removeEditTextFocus(View paramView, EditText paramEditText)
  {
    paramView.setFocusable(true);
    paramView.setFocusableInTouchMode(true);
    paramView.requestFocus();
    InputMethodManager localInputMethodManager = (InputMethodManager)getActivity().getSystemService("input_method");
    if (localInputMethodManager.isActive())
      localInputMethodManager.hideSoftInputFromWindow(paramEditText.getWindowToken(), 0);
    HistoryDataManagerFactory.getInstance(getActivity()).setName(this.mCurrResultId, paramEditText.getText().toString());
  }

  private void sendParams()
  {
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131624190:
      ((IStackableFragment)getParentFragment()).popFragment();
      return;
    case 2131623999:
      MiscUtils.shareImage(getActivity(), getResources().getString(2131492864), MiscUtils.CREATE_QRCOD_PATH + "barcode.png");
      return;
    case 2131624000:
    }
    MiscUtils.saveImageToGallery(getActivity(), this.mBitmap, this.defaultName);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.dataCollection = ((DataCollection)getActivity().getApplicationContext());
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130903046, paramViewGroup, false);
    this.mQrcodeView = ((ImageView)localView.findViewById(2131623994));
    localView.findViewById(2131623999).setOnClickListener(this);
    localView.findViewById(2131624000).setOnClickListener(this);
    initTitleBar(localView);
    initGenQRCode(localView);
    sendParams();
    return localView;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.createfragmentstack.CreateFragmentChild03CreateResult
 * JD-Core Version:    0.6.0
 */