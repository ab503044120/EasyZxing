package org.huihui.zxingimprove.fragment.createfragmentstack;

import android.content.res.Resources;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import mobi.thinkchange.android.superqrcode.util.ToastUtil;

public class CreateFragmentChild02CreateEmail extends AbstractCreateFragmentChild02Create
{
  protected void fillInputValue(Bundle paramBundle, ListEditorAdapter paramListEditorAdapter)
  {
    Bundle localBundle = new Bundle();
    paramBundle.putBundle("ENCODE_DATA", localBundle);
    String str1 = (String)paramListEditorAdapter.getItemInputValue(0);
    if (str1 != null)
      localBundle.putString("TO", str1);
    String str2 = (String)paramListEditorAdapter.getItemInputValue(1);
    if (str2 != null)
      localBundle.putString("SUB", str2);
    String str3 = (String)paramListEditorAdapter.getItemInputValue(2);
    if (str3 != null)
      localBundle.putString("BODY", str3);
  }

  protected List<Map<String, Object>> getTypeData()
  {
    ArrayList localArrayList = new ArrayList();
    int[] arrayOfInt1 = { 2131493148, 2131493149, 2131493150 };
    int[] arrayOfInt2 = { 2130837622, 2130837624, 2130837623 };
    boolean[] arrayOfBoolean = new boolean[3];
    Object[] arrayOfObject = new Object[arrayOfInt1.length];
    if (this.mLastEditData != null)
    {
      Bundle localBundle = this.mLastEditData.getBundle("ENCODE_DATA");
      if (localBundle != null)
      {
        String str1 = localBundle.getString("TO");
        String str2 = localBundle.getString("SUB");
        String str3 = localBundle.getString("BODY");
        arrayOfObject[0] = str1;
        arrayOfObject[1] = str2;
        arrayOfObject[2] = str3;
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
    return 2130837633;
  }

  protected String getTypeName()
  {
    return getString(2131493014);
  }

  protected boolean isCheckOutSuccess(ListEditorAdapter paramListEditorAdapter)
  {
    String str1 = (String)paramListEditorAdapter.getItemInputValue(0);
    String str2 = (String)paramListEditorAdapter.getItemInputValue(1);
    String str3 = (String)paramListEditorAdapter.getItemInputValue(2);
    String str4;
    if ((str1 == null) || (str1.equals("")))
      str4 = getResources().getString(2131493090);
    while (str4 != null)
    {
      this.toastUtil.getBgToastInstance(str4);
      return false;
      if (!Pattern.matches("^[a-zA-Z0-9_\\-]{1,}@[a-zA-Z0-9_\\-]{1,}\\.[a-zA-Z0-9_\\-.]{1,}$", str1))
      {
        str4 = getResources().getString(2131493091);
        continue;
      }
      if (str2.length() > 20)
      {
        str4 = getResources().getString(2131493082);
        continue;
      }
      int i = str3.length();
      str4 = null;
      if (i <= 200)
        continue;
      str4 = getResources().getString(2131493085);
    }
    return true;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.createfragmentstack.CreateFragmentChild02CreateEmail
 * JD-Core Version:    0.6.0
 */