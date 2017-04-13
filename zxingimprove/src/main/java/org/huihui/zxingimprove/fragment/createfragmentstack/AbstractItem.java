package org.huihui.zxingimprove.fragment.createfragmentstack;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public abstract class AbstractItem
{
  public EditText edittext;
  public ImageView icon;
  public int position;

  public AbstractItem(int paramInt)
  {
    this.position = paramInt;
  }

  abstract void fillViewWithData(View paramView);

  abstract String getType();

  abstract View inflateSelf(LayoutInflater paramLayoutInflater);

  abstract void savePreviousData();

  public void updatePostion(int paramInt)
  {
    this.position = paramInt;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.createfragmentstack.AbstractItem
 * JD-Core Version:    0.6.0
 */