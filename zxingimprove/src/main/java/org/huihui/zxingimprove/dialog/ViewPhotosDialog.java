package org.huihui.zxingimprove.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewPhotosDialog extends Dialog
{
  private Button cancelBtn;
  private Context context;
  private ImageView imageView;
  private Button okBtn;
  private TextView textView;

  public ViewPhotosDialog(Context paramContext)
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
    this.imageView = ((ImageView)findViewById(2131624078));
    this.imageView.setImageResource(2130837696);
    this.textView = ((TextView)findViewById(2131624028));
    this.textView.setText(this.context.getResources().getString(2131493077));
    this.okBtn = ((Button)findViewById(2131624031));
    this.okBtn.setText(this.context.getResources().getString(2131493078));
    this.cancelBtn = ((Button)findViewById(2131624030));
    this.cancelBtn.setText(this.context.getResources().getString(2131493113));
    this.cancelBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        ViewPhotosDialog.this.dismiss();
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903114);
    setupViews();
    init();
  }

  public void setOkButtonListener(View.OnClickListener paramOnClickListener)
  {
    this.okBtn.setOnClickListener(paramOnClickListener);
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.dialog.ViewPhotosDialog
 * JD-Core Version:    0.6.0
 */