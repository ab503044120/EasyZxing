package org.huihui.zxingimprove.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

public class WifiOpenDialog extends Dialog
{
  private Button cancelBtn;
  private Context context;
  private Button okBtn;

  public WifiOpenDialog(Context paramContext)
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
    this.okBtn = ((Button)findViewById(2131624031));
    this.cancelBtn = ((Button)findViewById(2131624030));
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903114);
    setupViews();
    init();
  }

  public void setCancelButtonListener(View.OnClickListener paramOnClickListener)
  {
    this.cancelBtn.setOnClickListener(paramOnClickListener);
  }

  public void setOkButtonListener(View.OnClickListener paramOnClickListener)
  {
    this.okBtn.setOnClickListener(paramOnClickListener);
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.dialog.WifiOpenDialog
 * JD-Core Version:    0.6.0
 */