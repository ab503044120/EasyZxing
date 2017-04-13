package org.huihui.zxingimprove.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

public class FeedbackSuccessfulDialog extends Dialog
{
  private Context context;
  private ImageView imageView;
  private TextView textView;

  public FeedbackSuccessfulDialog(Context paramContext)
  {
    super(paramContext, 2131427342);
    this.context = paramContext;
  }

  private void init()
  {
    Display localDisplay = ((Activity)this.context).getWindowManager().getDefaultDisplay();
    LayoutParams localLayoutParams = getWindow().getAttributes();
    localLayoutParams.width = (int)(0.85D * localDisplay.getWidth());
    localLayoutParams.gravity = 17;
    getWindow().setAttributes(localLayoutParams);
  }

  private void setupViews()
  {
    setCanceledOnTouchOutside(false);
    this.textView = ((TextView)findViewById(2131624028));
    this.imageView = ((ImageView)findViewById(2131624078));
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903069);
    setupViews();
    init();
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.dialog.FeedbackSuccessfulDialog
 * JD-Core Version:    0.6.0
 */