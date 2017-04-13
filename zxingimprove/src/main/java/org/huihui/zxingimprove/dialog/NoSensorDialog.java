package org.huihui.zxingimprove.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class NoSensorDialog extends Dialog
  implements OnClickListener
{
  private Context context;

  public NoSensorDialog(Context paramContext)
  {
    super(paramContext, 2131427342);
    this.context = paramContext;
  }

  private void initLayout()
  {
    Display localDisplay = ((Activity)this.context).getWindowManager().getDefaultDisplay();
    LayoutParams localLayoutParams = getWindow().getAttributes();
    localLayoutParams.width = (int)(0.95D * localDisplay.getWidth());
    localLayoutParams.gravity = 17;
    getWindow().setAttributes(localLayoutParams);
  }

  private void setListeners()
  {
    findViewById(2131624031).setOnClickListener(this);
    findViewById(2131624030).setOnClickListener(this);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131624031:
    }
    while (true)
    {
      dismiss();
      return;
      Intent localIntent = new Intent();
      localIntent.setData(Uri.parse(this.context.getResources().getString(2131493011)));
      localIntent.setAction("android.intent.action.VIEW");
      this.context.startActivity(localIntent);
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903100);
    setListeners();
    initLayout();
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.dialog.NoSensorDialog
 * JD-Core Version:    0.6.0
 */