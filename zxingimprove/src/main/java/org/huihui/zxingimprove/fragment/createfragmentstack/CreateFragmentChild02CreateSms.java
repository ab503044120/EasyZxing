package org.huihui.zxingimprove.fragment.createfragmentstack;

import android.content.res.Resources;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mobi.thinkchange.android.superqrcode.util.ToastUtil;

public class CreateFragmentChild02CreateSms extends AbstractCreateFragmentChild02Create
{
  protected void fillInputValue(Bundle paramBundle, ListEditorAdapter paramListEditorAdapter)
  {
    Bundle localBundle = new Bundle();
    paramBundle.putBundle("ENCODE_DATA", localBundle);
    String str1 = (String)paramListEditorAdapter.getItemInputValue(0);
    if (str1 != null)
      localBundle.putString("number", str1);
    String str2 = (String)paramListEditorAdapter.getItemInputValue(1);
    if (str2 != null)
      localBundle.putString("message", str2);
  }

  protected List<Map<String, Object>> getTypeData()
  {
    ArrayList localArrayList = new ArrayList();
    int[] arrayOfInt1 = { 2131493167, 2131493168 };
    int[] arrayOfInt2 = { 2130837630, 2130837629 };
    boolean[] arrayOfBoolean = new boolean[2];
    arrayOfBoolean[0] = true;
    Object[] arrayOfObject = new Object[arrayOfInt1.length];
    if (this.mLastEditData != null)
    {
      Bundle localBundle = this.mLastEditData.getBundle("ENCODE_DATA");
      if (localBundle != null)
      {
        String str1 = localBundle.getString("number");
        String str2 = localBundle.getString("message");
        arrayOfObject[0] = str1;
        arrayOfObject[1] = str2;
      }
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
      localHashMap.put("number", Boolean.valueOf(arrayOfBoolean[i]));
      localArrayList.add(localHashMap);
    }
  }

  protected int getTypeIconId()
  {
    return 2130837676;
  }

  protected String getTypeName()
  {
    return getString(2131493019);
  }

  protected boolean isCheckOutSuccess(ListEditorAdapter paramListEditorAdapter)
  {
    String str1 = (String)paramListEditorAdapter.getItemInputValue(0);
    String str2 = (String)paramListEditorAdapter.getItemInputValue(1);
    String str3;
    if ((str1 == null) || (str1.equals("")))
      str3 = getResources().getString(2131493081);
    while (str3 != null)
    {
      this.toastUtil.getBgToastInstance(str3);
      return false;
      if (str1.length() > 20)
      {
        str3 = getResources().getString(2131493082);
        continue;
      }
      int i = str2.length();
      str3 = null;
      if (i <= 70)
        continue;
      str3 = getResources().getString(2131493089);
    }
    return true;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.createfragmentstack.CreateFragmentChild02CreateSms
 * JD-Core Version:    0.6.0
 */