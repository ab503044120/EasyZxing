package org.huihui.zxingimprove.fragment.createfragmentstack;

import android.content.res.Resources;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mobi.thinkchange.android.superqrcode.util.ToastUtil;

public class CreateFragmentChild02CreateTel extends AbstractCreateFragmentChild02Create
{
  protected void fillInputValue(Bundle paramBundle, ListEditorAdapter paramListEditorAdapter)
  {
    String str = (String)paramListEditorAdapter.getItemInputValue(0);
    if (str != null)
      paramBundle.putString("ENCODE_DATA", str);
  }

  protected List<Map<String, Object>> getTypeData()
  {
    ArrayList localArrayList = new ArrayList();
    int[] arrayOfInt1 = { 2131493172 };
    int[] arrayOfInt2 = { 2130837671 };
    Object[] arrayOfObject = new Object[arrayOfInt1.length];
    if (this.mLastEditData != null)
    {
      String str = this.mLastEditData.getString("ENCODE_DATA");
      if (str != null)
        arrayOfObject[0] = str;
    }
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfInt1.length)
        return localArrayList;
      HashMap localHashMap = new HashMap();
      localHashMap.put("imageItem", Integer.valueOf(arrayOfInt2[i]));
      localHashMap.put("hintItem", getString(arrayOfInt1[i]));
      localHashMap.put("edit_text_input_value", arrayOfObject[i]);
      localHashMap.put("item_type", "edit_text");
      localHashMap.put("number", Boolean.valueOf(true));
      localArrayList.add(localHashMap);
    }
  }

  protected int getTypeIconId()
  {
    return 2130837669;
  }

  protected String getTypeName()
  {
    return getString(2131493053);
  }

  protected boolean isCheckOutSuccess(ListEditorAdapter paramListEditorAdapter)
  {
    String str1 = (String)paramListEditorAdapter.getItemInputValue(0);
    String str2;
    if ((str1 == null) || (str1.equals("")))
      str2 = getResources().getString(2131493080);
    while (str2 != null)
    {
      this.toastUtil.getBgToastInstance(str2);
      return false;
      int i = str1.length();
      str2 = null;
      if (i <= 20)
        continue;
      str2 = getResources().getString(2131493082);
    }
    return true;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.createfragmentstack.CreateFragmentChild02CreateTel
 * JD-Core Version:    0.6.0
 */