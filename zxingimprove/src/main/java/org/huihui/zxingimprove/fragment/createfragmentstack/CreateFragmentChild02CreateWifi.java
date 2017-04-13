package org.huihui.zxingimprove.fragment.createfragmentstack;

import android.content.res.Resources;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mobi.thinkchange.android.superqrcode.util.ToastUtil;

public class CreateFragmentChild02CreateWifi extends AbstractCreateFragmentChild02Create
{
  protected void fillInputValue(Bundle paramBundle, ListEditorAdapter paramListEditorAdapter)
  {
    Bundle localBundle = new Bundle();
    paramBundle.putBundle("ENCODE_DATA", localBundle);
    String str1 = (String)paramListEditorAdapter.getItemInputValue(0);
    if (str1 != null)
      localBundle.putString("S", str1);
    Integer localInteger = (Integer)paramListEditorAdapter.getItemInputValue(1);
    if (localInteger != null)
    {
      localBundle.putString("T-Position", String.valueOf(localInteger));
      localBundle.putString("T", getResources().getStringArray(2131558401)[localInteger.intValue()]);
    }
    String str2 = (String)paramListEditorAdapter.getItemInputValue(2);
    if (str2 != null)
      localBundle.putString("P", str2);
  }

  protected List<Map<String, Object>> getTypeData()
  {
    ArrayList localArrayList = new ArrayList();
    int[] arrayOfInt1 = { 2131493151, 2131493152, 2131493153 };
    int[] arrayOfInt2 = { 2130837619, 2130837617, 2130837677 };
    Object[] arrayOfObject = new Object[arrayOfInt1.length];
    if (this.mLastEditData != null)
    {
      Bundle localBundle = this.mLastEditData.getBundle("ENCODE_DATA");
      if (localBundle != null)
      {
        String str2 = localBundle.getString("S");
        String str3 = localBundle.getString("P");
        Object localObject = localBundle.get("T-Position");
        arrayOfObject[0] = str2;
        arrayOfObject[1] = localObject;
        arrayOfObject[2] = str3;
      }
    }
    int i = 0;
    if (i >= arrayOfInt1.length)
      return localArrayList;
    HashMap localHashMap = new HashMap();
    localHashMap.put("imageItem", Integer.valueOf(arrayOfInt2[i]));
    localHashMap.put("hintItem", getString(arrayOfInt1[i]));
    localHashMap.put("edit_text_input_value", arrayOfObject[i]);
    if (i == 1);
    for (String str1 = "spinner"; ; str1 = "edit_text")
    {
      localHashMap.put("item_type", str1);
      localHashMap.put("number", Boolean.valueOf(false));
      localArrayList.add(localHashMap);
      i++;
      break;
    }
  }

  protected int getTypeIconId()
  {
    return 2130837690;
  }

  protected String getTypeName()
  {
    return getString(2131493015);
  }

  protected boolean isCheckOutSuccess(ListEditorAdapter paramListEditorAdapter)
  {
    String str1 = (String)paramListEditorAdapter.getItemInputValue(0);
    String str2 = (String)paramListEditorAdapter.getItemInputValue(2);
    String str3;
    if ((str1 == null) || (str1.equals("")))
      str3 = getResources().getString(2131493095);
    while (str3 != null)
    {
      this.toastUtil.getBgToastInstance(str3);
      return false;
      if (str1.length() > 50)
      {
        str3 = getResources().getString(2131493096);
        continue;
      }
      int i = str2.length();
      str3 = null;
      if (i <= 50)
        continue;
      str3 = getResources().getString(2131493097);
    }
    return true;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.createfragmentstack.CreateFragmentChild02CreateWifi
 * JD-Core Version:    0.6.0
 */