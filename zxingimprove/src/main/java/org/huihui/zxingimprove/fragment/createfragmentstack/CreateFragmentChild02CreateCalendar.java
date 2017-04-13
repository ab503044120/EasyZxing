package org.huihui.zxingimprove.fragment.createfragmentstack;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateFragmentChild02CreateCalendar extends AbstractCreateFragmentChild02Create
{
  protected void fillInputValue(Bundle paramBundle, ListEditorAdapter paramListEditorAdapter)
  {
    Bundle localBundle = new Bundle();
    paramBundle.putBundle("ENCODE_DATA", localBundle);
    String str1 = (String)paramListEditorAdapter.getItemInputValue(0);
    if (str1 != null)
      localBundle.putString("SUMMARY", str1);
    String str2 = (String)paramListEditorAdapter.getItemInputValue(1);
    if (str2 != null)
      localBundle.putString("DTSTART", str2);
    String str3 = (String)paramListEditorAdapter.getItemInputValue(2);
    if (str3 != null)
      localBundle.putString("DTEND", str3);
    String str4 = (String)paramListEditorAdapter.getItemInputValue(3);
    if (str4 != null)
      localBundle.putString("LOCATION", str4);
    String str5 = (String)paramListEditorAdapter.getItemInputValue(4);
    if (str5 != null)
      localBundle.putString("DESCRIPTION", str5);
  }

  protected List<Map<String, Object>> getTypeData()
  {
    ArrayList localArrayList = new ArrayList();
    int[] arrayOfInt1 = { 2131493154, 2131493155, 2131493156, 2131493157, 2131493158 };
    int[] arrayOfInt2 = { 2130837600, 2130837604, 2130837601, 2130837603, 2130837602 };
    Object[] arrayOfObject = new Object[arrayOfInt1.length];
    if (this.mLastEditData != null)
    {
      Bundle localBundle = this.mLastEditData.getBundle("ENCODE_DATA");
      if (localBundle != null)
      {
        String str1 = localBundle.getString("SUMMARY");
        String str2 = localBundle.getString("DTSTART");
        String str3 = localBundle.getString("DTEND");
        String str4 = localBundle.getString("LOCATION");
        String str5 = localBundle.getString("DESCRIPTION");
        arrayOfObject[0] = str1;
        arrayOfObject[1] = str2;
        arrayOfObject[2] = str3;
        arrayOfObject[3] = str4;
        arrayOfObject[4] = str5;
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
      localHashMap.put("number", Boolean.valueOf(false));
      localArrayList.add(localHashMap);
    }
  }

  protected int getTypeIconId()
  {
    return 2130837599;
  }

  protected String getTypeName()
  {
    return getString(2131493016);
  }

  protected boolean isCheckOutSuccess(ListEditorAdapter paramListEditorAdapter)
  {
    return true;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.createfragmentstack.CreateFragmentChild02CreateCalendar
 * JD-Core Version:    0.6.0
 */