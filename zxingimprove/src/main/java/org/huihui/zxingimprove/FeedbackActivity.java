package org.huihui.zxingimprove;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import mobi.thinkchange.android.superqrcode.data.DataCollection;
import mobi.thinkchange.android.superqrcode.dialog.FeedbackSuccessfulDialog;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;
import mobi.thinkchange.android.superqrcode.util.PreferencesUtils;

public class FeedbackActivity extends Activity
  implements OnClickListener
{
  private int MAX_LENGTH = 100;
  private int REST_LENGTH = 0;
  private EditText contactEditText;
  private EditText contentEditText;
  private DataCollection dataCollection;
  private FeedbackSuccessfulDialog dialog;
  private Button feedbackButton;
  private String from;
  private PreferencesUtils preferencesUtils;
  private RelativeLayout relativeLayout;
  private TextView stringCountTextView;
  private ImageView titleLeftImg;
  private ImageView titleRightImg;
  private TextView titleText;

  private boolean checkInput()
  {
    String str1 = this.contactEditText.getText().toString();
    String str2 = this.contentEditText.getText().toString();
    String str3;
    if (str1.length() > 30)
      str3 = getResources().getString(2131493120);
    while (str3 != null)
    {
      MiscUtils.showSnackBar(this, str3, getResources().getString(2131492871));
      return false;
      if ((str2 == null) || (str2.equals("")))
      {
        str3 = getResources().getString(2131493121);
        continue;
      }
      int i = str2.length();
      str3 = null;
      if (i <= 100)
        continue;
      str3 = getResources().getString(2131493122);
    }
    return true;
  }

  private void initData()
  {
    Intent localIntent = getIntent();
    if (localIntent != null)
      this.from = localIntent.getStringExtra("from");
    this.dataCollection = ((DataCollection)getApplicationContext());
    this.contentEditText.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramEditable)
      {
        if (FeedbackActivity.this.REST_LENGTH > FeedbackActivity.this.MAX_LENGTH)
        {
          FeedbackActivity.this.stringCountTextView.setText(Html.fromHtml("<font color='red'>" + FeedbackActivity.this.REST_LENGTH + "</font>"));
          return;
        }
        FeedbackActivity.this.stringCountTextView.setText(FeedbackActivity.this.REST_LENGTH);
      }

      public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
        if (FeedbackActivity.this.REST_LENGTH > FeedbackActivity.this.MAX_LENGTH)
        {
          FeedbackActivity.this.stringCountTextView.setText(Html.fromHtml("<font color='red'>" + FeedbackActivity.this.REST_LENGTH + "</font>"));
          return;
        }
        FeedbackActivity.this.stringCountTextView.setText(FeedbackActivity.this.REST_LENGTH);
      }

      public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
        FeedbackActivity.this.REST_LENGTH = FeedbackActivity.this.contentEditText.getText().length();
      }
    });
  }

  private void initView()
  {
    this.preferencesUtils = new PreferencesUtils(this);
    this.dialog = new FeedbackSuccessfulDialog(this);
    this.titleText = ((TextView)findViewById(2131624016));
    this.titleText.setText(getResources().getString(2131493132));
    this.titleLeftImg = ((ImageView)findViewById(2131624190));
    this.titleLeftImg.setImageResource(2130837685);
    this.titleLeftImg.setOnClickListener(this);
    this.titleRightImg = ((ImageView)findViewById(2131624191));
    this.titleRightImg.setVisibility(8);
    this.feedbackButton = ((Button)findViewById(2131624135));
    this.feedbackButton.setOnClickListener(this);
    this.feedbackButton.setText(getResources().getString(2131493119));
    this.relativeLayout = ((RelativeLayout)findViewById(2131624110));
    this.relativeLayout.setOnClickListener(this);
    this.contactEditText = ((EditText)findViewById(2131624108));
    this.contentEditText = ((EditText)findViewById(2131624111));
    this.stringCountTextView = ((TextView)findViewById(2131624112));
  }

  private void sendParams()
  {
  }

  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131624190)
      finish();
    label94: InputMethodManager localInputMethodManager;
    do
    {
      do
      {
        while (true)
        {
          return;
          if (paramView.getId() != 2131624135)
            break label94;
          if (!MiscUtils.isNetworkAvailable(this))
            break;
          if (!checkInput())
            continue;
          sendParams();
          this.dialog.show();
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              if (FeedbackActivity.this.dialog.isShowing())
              {
                FeedbackActivity.this.dialog.dismiss();
                FeedbackActivity.this.finish();
              }
            }
          }
          , 2000L);
          return;
        }
        MiscUtils.showSnackBar(this, getResources().getString(2131493112), getResources().getString(2131492871));
        return;
      }
      while (paramView.getId() != 2131624110);
      this.contentEditText.setFocusable(true);
      this.contentEditText.setFocusableInTouchMode(true);
      this.contentEditText.requestFocus();
      localInputMethodManager = (InputMethodManager)getSystemService("input_method");
    }
    while (!localInputMethodManager.isActive());
    localInputMethodManager.showSoftInput(this.contentEditText, 2);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903076);
    initView();
    initData();
  }

  protected void onPause()
  {
    super.onPause();
    this.preferencesUtils.saveFeedBackContact(this.contactEditText.getText().toString());
  }

  protected void onResume()
  {
    super.onResume();
    String str = this.preferencesUtils.getFeedBackContact();
    this.contactEditText.setText(str);
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.FeedbackActivity
 * JD-Core Version:    0.6.0
 */