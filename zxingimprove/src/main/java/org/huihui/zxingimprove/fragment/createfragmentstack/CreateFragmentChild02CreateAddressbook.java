package org.huihui.zxingimprove.fragment.createfragmentstack;

import android.content.res.Resources;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import mobi.thinkchange.android.superqrcode.util.ToastUtil;

public class CreateFragmentChild02CreateAddressbook extends AbstractCreateFragmentChild02Create
{
  protected void fillInputValue(Bundle paramBundle, ListEditorAdapter paramListEditorAdapter)
  {
    Bundle localBundle = new Bundle();
    paramBundle.putBundle("ENCODE_DATA", localBundle);
    String str1 = (String)paramListEditorAdapter.getItemInputValue(0);
    if (str1 != null)
      localBundle.putString("name", str1);
    String str2 = (String)paramListEditorAdapter.getItemInputValue(1);
    if (str2 != null)
      localBundle.putString("company", str2);
    String str3 = (String)paramListEditorAdapter.getItemInputValue(2);
    if (str3 != null)
      localBundle.putString("phone", str3);
    String str4 = (String)paramListEditorAdapter.getItemInputValue(3);
    if (str4 != null)
      localBundle.putString("email", str4);
    String str5 = (String)paramListEditorAdapter.getItemInputValue(4);
    if (str5 != null)
      localBundle.putString("postal", str5);
    String str6 = (String)paramListEditorAdapter.getItemInputValue(5);
    if (str6 != null)
      localBundle.putString("URL_KEY", str6);
    String str7 = (String)paramListEditorAdapter.getItemInputValue(6);
    if (str7 != null)
      localBundle.putString("NOTE_KEY", str7);
  }

  protected List<Map<String, Object>> getTypeData()
  {
    ArrayList localArrayList = new ArrayList();
    int[] arrayOfInt1 = { 2131493159, 2131493160, 2131493161, 2131493162, 2131493163, 2131493164, 2131493165 };
    int[] arrayOfInt2 = { 2130837612, 2130837609, 2130837613, 2130837610, 2130837608, 2130837614, 2130837611 };
    boolean[] arrayOfBoolean = new boolean[7];
    arrayOfBoolean[2] = true;
    Object[] arrayOfObject = new Object[arrayOfInt1.length];
    if (this.mLastEditData != null)
    {
      Bundle localBundle = this.mLastEditData.getBundle("ENCODE_DATA");
      if (localBundle != null)
      {
        String str1 = localBundle.getString("name");
        String str2 = localBundle.getString("company");
        String str3 = localBundle.getString("phone");
        String str4 = localBundle.getString("email");
        String str5 = localBundle.getString("postal");
        String str6 = localBundle.getString("URL_KEY");
        String str7 = localBundle.getString("NOTE_KEY");
        arrayOfObject[0] = str1;
        arrayOfObject[1] = str2;
        arrayOfObject[2] = str3;
        arrayOfObject[3] = str4;
        arrayOfObject[4] = str5;
        arrayOfObject[5] = str6;
        arrayOfObject[6] = str7;
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
    return 2130837607;
  }

  protected String getTypeName()
  {
    return getString(2131493017);
  }

  protected boolean isCheckOutSuccess(ListEditorAdapter paramListEditorAdapter)
  {
    String str1 = (String)paramListEditorAdapter.getItemInputValue(0);
    String str2 = (String)paramListEditorAdapter.getItemInputValue(1);
    String str3 = (String)paramListEditorAdapter.getItemInputValue(2);
    String str4 = (String)paramListEditorAdapter.getItemInputValue(3);
    String str5 = (String)paramListEditorAdapter.getItemInputValue(4);
    String str6 = (String)paramListEditorAdapter.getItemInputValue(5);
    String str7 = (String)paramListEditorAdapter.getItemInputValue(6);
    String str8;
    if ((str1 == null) || (str1.equals("")))
      str8 = getResources().getString(2131493102);
    while (str8 != null)
    {
      this.toastUtil.getBgToastInstance(str8);
      return false;
      if (str1.length() > 20)
      {
        str8 = getResources().getString(2131493103);
        continue;
      }
      if (str2.length() > 30)
      {
        str8 = getResources().getString(2131493104);
        continue;
      }
      if (str3.length() > 20)
      {
        str8 = getResources().getString(2131493105);
        continue;
      }
      if ((str4.length() > 0) && (!Pattern.matches("^[a-zA-Z0-9_\\-]{1,}@[a-zA-Z0-9_\\-]{1,}\\.[a-zA-Z0-9_\\-.]{1,}$", str4)))
      {
        str8 = getResources().getString(2131493106);
        continue;
      }
      if (str5.length() > 30)
      {
        str8 = getResources().getString(2131493107);
        continue;
      }
      if ((str6 != null) && (str6.length() > 50))
      {
        str8 = getResources().getString(2131493108);
        continue;
      }
      str8 = null;
      if (str7 == null)
        continue;
      int i = str7.length();
      str8 = null;
      if (i <= 100)
        continue;
      str8 = getResources().getString(2131493109);
    }
    return true;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.createfragmentstack.CreateFragmentChild02CreateAddressbook
 * JD-Core Version:    0.6.0
 */