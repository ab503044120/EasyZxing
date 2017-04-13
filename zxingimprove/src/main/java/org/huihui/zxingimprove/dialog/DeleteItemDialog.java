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
import android.widget.TextView;

public class DeleteItemDialog extends Dialog
{
  private Button cancelBtn;
  private Context context;
  private Button deleteBtn;
  private TextView textView;

  public DeleteItemDialog(Context paramContext)
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
    this.textView.setText(this.context.getResources().getString(2131493133));
    this.deleteBtn = ((Button)findViewById(2131624031));
    this.deleteBtn.setText(this.context.getResources().getString(2131493134));
    this.cancelBtn = ((Button)findViewById(2131624030));
    this.cancelBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        DeleteItemDialog.this.dismiss();
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903056);
    setupViews();
    init();
  }

  public void setContenText(String paramString)
  {
    this.textView.setText(paramString);
  }

  public void setDeleteButtonListener(View.OnClickListener paramOnClickListener)
  {
    this.deleteBtn.setOnClickListener(paramOnClickListener);
  }

  public void setOkButtonText(String paramString)
  {
    this.deleteBtn.setText(paramString);
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.dialog.DeleteItemDialog
 * JD-Core Version:    0.6.0
 */