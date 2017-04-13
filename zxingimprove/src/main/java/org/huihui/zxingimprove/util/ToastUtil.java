package org.huihui.zxingimprove.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ToastUtil
{
  private Activity activity;
  private Toast toast;

  public ToastUtil(Activity paramActivity)
  {
    this.activity = paramActivity;
    this.toast = new Toast(paramActivity);
  }

  public static Toast getToastInstance(Context paramContext)
  {
    Toast localToast = Toast.makeText(paramContext, null, 0);
    localToast.setGravity(80, 0, 80);
    return localToast;
  }

  public Toast getBgToastInstance(String paramString)
  {
    View localView = this.activity.getLayoutInflater().inflate(2130903109, null);
    RelativeLayout localRelativeLayout = (RelativeLayout)localView.findViewById(2131624209);
    localRelativeLayout.getBackground().setAlpha(210);
    localRelativeLayout.invalidate();
    ((TextView)localView.findViewById(2131624210)).setText(paramString);
    this.toast.setDuration(1);
    this.toast.setView(localView);
    this.toast.show();
    return this.toast;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.util.ToastUtil
 * JD-Core Version:    0.6.0
 */