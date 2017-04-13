package org.huihui.zxingimprove.fragment.createfragmentstack;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mobi.thinkchange.android.superqrcode.util.ToastUtil;

public class CreateFragmentChild02CreateLocation extends AbstractCreateFragmentChild02Create
{
  static int FLAG_PARSE_FLOAT_ERROR = 65537;

  static float safeParseFloat(String paramString)
  {
    try
    {
      float f = Float.parseFloat(paramString);
      return f;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    return FLAG_PARSE_FLOAT_ERROR;
  }

  static boolean valueValid(String paramString, int paramInt1, int paramInt2)
  {
    float f = safeParseFloat(paramString);
    if (f == FLAG_PARSE_FLOAT_ERROR)
      return false;
    if (f > paramInt1)
      return false;
    return f >= paramInt2;
  }

  protected void fillInputValue(Bundle paramBundle, ListEditorAdapter paramListEditorAdapter)
  {
    Bundle localBundle = new Bundle();
    paramBundle.putBundle("ENCODE_DATA", localBundle);
    String str1 = (String)paramListEditorAdapter.getItemInputValue(0);
    if (str1 != null);
    try
    {
      float f4 = Float.parseFloat(str1);
      f3 = f4;
      localBundle.putFloat("LAT", f3);
      str2 = (String)paramListEditorAdapter.getItemInputValue(1);
      if (str2 == null);
    }
    catch (NumberFormatException localNumberFormatException2)
    {
      try
      {
        String str2;
        float f2 = Float.parseFloat(str2);
        f1 = f2;
        localBundle.putFloat("LONG", f1);
        return;
        localNumberFormatException2 = localNumberFormatException2;
        float f3 = 0.0F;
      }
      catch (NumberFormatException localNumberFormatException1)
      {
        while (true)
          float f1 = 0.0F;
      }
    }
  }

  protected List<Map<String, Object>> getTypeData()
  {
    ArrayList localArrayList = new ArrayList();
    int[] arrayOfInt1 = { 2131493169, 2131493170 };
    int[] arrayOfInt2 = { 2130837625, 2130837626 };
    boolean[] arrayOfBoolean = { 1, 1 };
    Object[] arrayOfObject = new Object[arrayOfInt1.length];
    if (this.mLastEditData != null)
    {
      Bundle localBundle = this.mLastEditData.getBundle("ENCODE_DATA");
      if (localBundle != null)
      {
        Float localFloat1 = Float.valueOf(localBundle.getFloat("LAT"));
        Float localFloat2 = Float.valueOf(localBundle.getFloat("LONG"));
        arrayOfObject[0] = localFloat1;
        arrayOfObject[1] = localFloat2;
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
    return 2130837675;
  }

  protected String getTypeName()
  {
    return getString(2131493020);
  }

  protected boolean isCheckOutSuccess(ListEditorAdapter paramListEditorAdapter)
  {
    String str1 = (String)paramListEditorAdapter.getItemInputValue(0);
    String str2 = (String)paramListEditorAdapter.getItemInputValue(1);
    String str3;
    if (TextUtils.isEmpty(str1))
      str3 = getString(2131493098);
    while (str3 != null)
    {
      this.toastUtil.getBgToastInstance(str3);
      return false;
      if (!valueValid(str1, 90, -90))
      {
        str3 = getString(2131493100);
        continue;
      }
      if (TextUtils.isEmpty(str2))
      {
        str3 = getString(2131493099);
        continue;
      }
      boolean bool = valueValid(str2, 180, -180);
      str3 = null;
      if (bool)
        continue;
      str3 = getString(2131493101);
    }
    return true;
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.createfragmentstack.CreateFragmentChild02CreateLocation
 * JD-Core Version:    0.6.0
 */