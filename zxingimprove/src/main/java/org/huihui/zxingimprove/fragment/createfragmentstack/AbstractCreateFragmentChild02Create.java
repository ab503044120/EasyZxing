package org.huihui.zxingimprove.fragment.createfragmentstack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import mobi.thinkchange.android.superqrcode.data.DataCollection;
import mobi.thinkchange.android.superqrcode.fragment.IStackableFragment;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;
import mobi.thinkchange.android.superqrcode.util.ToastUtil;

public abstract class AbstractCreateFragmentChild02Create extends Fragment
  implements OnClickListener
{
  static final String KEY_OF_LAST_INPUT_VALUES = "last_input_values";
  protected DataCollection dataCollection;
  protected Bundle mLastEditData;
  protected WeakReference<ListEditorAdapter> mWeakListAdatper;
  protected MiscUtils miscUtils;
  private ImageView titleLeftImg;
  private ImageView titleRightImg;
  protected ToastUtil toastUtil;

  private void initListView(View paramView)
  {
    List localList = getTypeData();
    ListEditorAdapter localListEditorAdapter = new ListEditorAdapter(getActivity(), localList);
    ((ListView)paramView.findViewById(2131623996)).setAdapter(localListEditorAdapter);
    this.mWeakListAdatper = new WeakReference(localListEditorAdapter);
  }

  private final void initTitleBar(View paramView)
  {
    TextView localTextView = (TextView)paramView.findViewById(2131624016);
    localTextView.setText(2131493032);
    localTextView.append(" ");
    localTextView.append(getTypeName());
    this.titleLeftImg = ((ImageView)paramView.findViewById(2131624190));
    this.titleLeftImg.setImageResource(2130837685);
    this.titleLeftImg.setOnClickListener(this);
    this.titleRightImg = ((ImageView)paramView.findViewById(2131624191));
    this.titleRightImg.setVisibility(8);
  }

  private void initType(View paramView)
  {
    ((ImageView)paramView.findViewById(2131623994)).setImageResource(getTypeIconId());
    ((TextView)paramView.findViewById(2131623995)).setText(getTypeName());
  }

  protected abstract void fillInputValue(Bundle paramBundle, ListEditorAdapter paramListEditorAdapter);

  protected final Bundle getInputValue()
  {
    Bundle localBundle = new Bundle(getArguments());
    ListEditorAdapter localListEditorAdapter = (ListEditorAdapter)this.mWeakListAdatper.get();
    if (localListEditorAdapter != null)
    {
      fillInputValue(localBundle, localListEditorAdapter);
      if (!isCheckOutSuccess(localListEditorAdapter))
        localBundle = null;
    }
    return localBundle;
  }

  protected final String getType()
  {
    return getArguments().getString("ENCODE_TYPE");
  }

  protected abstract List<Map<String, Object>> getTypeData();

  protected abstract int getTypeIconId();

  protected abstract String getTypeName();

  protected abstract boolean isCheckOutSuccess(ListEditorAdapter paramListEditorAdapter);

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131624190:
    case 2131623997:
    }
    Bundle localBundle;
    do
    {
      return;
      ((IStackableFragment)getParentFragment()).popFragment();
      return;
      localBundle = getInputValue();
    }
    while (localBundle == null);
    this.miscUtils.playSound();
    localBundle.putString("type_name", getTypeName());
    this.mLastEditData = new Bundle(localBundle);
    CreateFragmentChild03CreateResult localCreateFragmentChild03CreateResult = new CreateFragmentChild03CreateResult();
    localCreateFragmentChild03CreateResult.setArguments(localBundle);
    ((IStackableFragment)getParentFragment()).addFragmentToStack(localCreateFragmentChild03CreateResult);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.dataCollection = ((DataCollection)getActivity().getApplicationContext());
    this.miscUtils = new MiscUtils(getActivity());
    this.toastUtil = new ToastUtil(getActivity());
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130903045, paramViewGroup, false);
    initTitleBar(localView);
    initType(localView);
    initListView(localView);
    localView.findViewById(2131623997).setOnClickListener(this);
    Log.d(AbstractCreateFragmentChild02Create.class.getSimpleName(), "last.edit.data(onCreateView): " + paramBundle);
    this.miscUtils.initSound(2131099649);
    return localView;
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.createfragmentstack.AbstractCreateFragmentChild02Create
 * JD-Core Version:    0.6.0
 */