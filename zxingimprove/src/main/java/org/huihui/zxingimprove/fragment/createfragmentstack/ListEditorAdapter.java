package org.huihui.zxingimprove.fragment.createfragmentstack;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListEditorAdapter extends BaseAdapter
{
  static final String EDITTEXT_TYPE_NUMBER = "number";
  static final String ITEM_TYPE_EDITTEXT = "edit_text";
  static final String ITEM_TYPE_SPINNER = "spinner";
  static final String KEY_OF_EDIT_TEXT_INPUT_VALUE = "edit_text_input_value";
  static final String KEY_OF_INPUT_CONTROL_ENABLED = "input_control_enabled";
  static final String KEY_OF_ITEM_TYPE = "item_type";
  private List<Map<String, Object>> mData;
  private LayoutInflater mInflater;
  private Map<Integer, Object> mInputValuesSecondly = new HashMap();
  private int mLastFocusItemIndex = -1;

  public ListEditorAdapter(Context paramContext, List<Map<String, Object>> paramList)
  {
    this.mData = paramList;
    this.mInflater = LayoutInflater.from(paramContext);
    this.mInputValuesSecondly.clear();
  }

  static int safeIntParse(Object paramObject)
  {
    int i = -1;
    if (paramObject == null)
      return i;
    try
    {
      int j = Integer.parseInt(paramObject.toString());
      i = j;
      label18: return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      break label18;
    }
  }

  public int getCount()
  {
    return this.mData.size();
  }

  public Object getItem(int paramInt)
  {
    return this.mData.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return 0L;
  }

  public Object getItemInputValue(int paramInt)
  {
    Map localMap = (Map)this.mData.get(paramInt);
    if (localMap != null)
    {
      Object localObject = localMap.get("edit_text_input_value");
      if (localObject == null)
        localObject = this.mInputValuesSecondly.get(Integer.valueOf(paramInt));
      return localObject;
    }
    return null;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    String str = (String)((Map)this.mData.get(paramInt)).get("item_type");
    Object localObject;
    if (paramView == null)
      if (str.equals("edit_text"))
      {
        localObject = new EditTextItem(paramInt);
        paramView = ((AbstractItem)localObject).inflateSelf(this.mInflater);
      }
    do
    {
      ((AbstractItem)localObject).fillViewWithData(paramView);
      return paramView;
      boolean bool = str.equals("spinner");
      localObject = null;
      if (!bool)
        break;
      localObject = new SpinnerItem(paramInt);
      break;
      localObject = (AbstractItem)paramView.getTag();
      ((AbstractItem)localObject).savePreviousData();
      ((AbstractItem)localObject).updatePostion(paramInt);
    }
    while (((AbstractItem)localObject).getType().equals(str));
    if (str.equals("edit_text"))
      localObject = new EditTextItem(paramInt);
    while (true)
    {
      paramView = ((AbstractItem)localObject).inflateSelf(this.mInflater);
      break;
      if (!str.equals("spinner"))
        continue;
      localObject = new SpinnerItem(paramInt);
    }
  }

  public final class EditTextItem extends AbstractItem
  {
    public EditTextItem(int arg2)
    {
      super();
    }

    void fillViewWithData(View paramView)
    {
      Map localMap = (Map)ListEditorAdapter.this.mData.get(this.position);
      Integer localInteger = (Integer)localMap.get("imageItem");
      if (localInteger != null)
        this.icon.setImageResource(localInteger.intValue());
      String str = (String)localMap.get("hintItem");
      if (str != null)
        this.edittext.setHint(str);
      if (((Boolean)localMap.get("number")).booleanValue())
        this.edittext.setInputType(3);
      while (true)
      {
        Object localObject1 = localMap.get("edit_text_input_value");
        if (localObject1 != null)
        {
          this.edittext.setText(localObject1.toString());
          Boolean localBoolean = (Boolean)localMap.get("input_control_enabled");
          if ((localBoolean != null) && (!localBoolean.booleanValue()))
            break;
          this.edittext.setEnabled(true);
          return;
          this.edittext.setInputType(1);
          continue;
        }
        else
        {
          EditText localEditText = this.edittext;
          if (ListEditorAdapter.this.mInputValuesSecondly.get(Integer.valueOf(this.position)) == null);
          for (Object localObject2 = null; ; localObject2 = ListEditorAdapter.this.mInputValuesSecondly.get(Integer.valueOf(this.position)).toString())
          {
            localEditText.setText((CharSequence)localObject2);
            break;
          }
        }
      }
      this.edittext.setEnabled(false);
    }

    String getType()
    {
      return "edit_text";
    }

    View inflateSelf(LayoutInflater paramLayoutInflater)
    {
      View localView = paramLayoutInflater.inflate(2130903092, null);
      this.icon = ((ImageView)localView.findViewById(2131624183));
      this.edittext = ((EditText)localView.findViewById(2131624184));
      this.edittext.setTag(Integer.valueOf(this.position));
      this.edittext.addTextChangedListener(new MyTextWatcher(ListEditorAdapter.this, this));
      localView.setTag(this);
      return localView;
    }

    void savePreviousData()
    {
      int i = ((Integer)this.edittext.getTag()).intValue();
      String str = this.edittext.getText().toString();
      ListEditorAdapter.this.mInputValuesSecondly.put(Integer.valueOf(i), str);
    }

    public void updatePostion(int paramInt)
    {
      super.updatePostion(paramInt);
      this.edittext.setTag(Integer.valueOf(paramInt));
    }
  }

  class MyTextWatcher
    implements TextWatcher
  {
    private EditTextItem mHolder;

    public MyTextWatcher(EditTextItem arg2)
    {
      Object localObject;
      this.mHolder = localObject;
    }

    public void afterTextChanged(Editable paramEditable)
    {
      if (paramEditable != null)
      {
        String str = paramEditable.toString();
        int i = ((Integer)this.mHolder.edittext.getTag()).intValue();
        ((Map)ListEditorAdapter.this.mData.get(i)).put("edit_text_input_value", str);
      }
      this.mHolder.edittext.setFocusable(true);
      this.mHolder.edittext.setFocusableInTouchMode(true);
      this.mHolder.edittext.requestFocus();
      Editable localEditable = this.mHolder.edittext.getText();
      this.mHolder.edittext.setSelection(localEditable.length());
    }

    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
    }

    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
    {
    }
  }

  class SpinnerItem extends AbstractItem
  {
    private Spinner mSpinner;

    public SpinnerItem(int arg2)
    {
      super();
    }

    void fillViewWithData(View paramView)
    {
      Map localMap = (Map)ListEditorAdapter.this.mData.get(this.position);
      Integer localInteger = (Integer)localMap.get("imageItem");
      if (localInteger != null)
        this.icon.setImageResource(localInteger.intValue());
      Object localObject = localMap.get("edit_text_input_value");
      if (localObject != null)
      {
        int j = ListEditorAdapter.safeIntParse(localObject);
        if (j == -1)
          j = 0;
        this.mSpinner.setSelection(ListEditorAdapter.safeIntParse(Integer.valueOf(j)));
        return;
      }
      int i = ListEditorAdapter.safeIntParse(ListEditorAdapter.this.mInputValuesSecondly.get(Integer.valueOf(this.position)));
      if (i == -1)
        i = 0;
      this.mSpinner.setSelection(i);
    }

    String getType()
    {
      return "spinner";
    }

    View inflateSelf(LayoutInflater paramLayoutInflater)
    {
      View localView = paramLayoutInflater.inflate(2130903093, null);
      this.icon = ((ImageView)localView.findViewById(2131624183));
      this.mSpinner = ((Spinner)localView.findViewById(2131624185));
      ArrayAdapter localArrayAdapter = ArrayAdapter.createFromResource(localView.getContext(), 2131558400, 2130903106);
      localArrayAdapter.setDropDownViewResource(2130903105);
      this.mSpinner.setAdapter(localArrayAdapter);
      this.mSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
      {
        public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
        {
          if (ListEditorAdapter.safeIntParse(((Map)ListEditorAdapter.this.mData.get(SpinnerItem.this.position)).get("edit_text_input_value")) == paramInt)
            return;
          ((Map)ListEditorAdapter.this.mData.get(SpinnerItem.this.position)).put("edit_text_input_value", Integer.valueOf(paramInt));
          if (paramInt != 2);
          for (boolean bool = true; ; bool = false)
          {
            ((Map)ListEditorAdapter.this.mData.get(2)).put("input_control_enabled", Boolean.valueOf(bool));
            ListEditorAdapter.this.notifyDataSetChanged();
            return;
          }
        }

        public void onNothingSelected(AdapterView<?> paramAdapterView)
        {
        }
      });
      localView.setTag(this);
      return localView;
    }

    void savePreviousData()
    {
      Integer localInteger = Integer.valueOf(this.mSpinner.getSelectedItemPosition());
      ListEditorAdapter.this.mInputValuesSecondly.put(Integer.valueOf(this.position), localInteger);
    }
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.createfragmentstack.ListEditorAdapter
 * JD-Core Version:    0.6.0
 */