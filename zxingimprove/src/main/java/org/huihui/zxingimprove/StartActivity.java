package org.huihui.zxingimprove;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.result.ParsedResultType;
import mobi.thinkchange.android.superqrcode.data.DataCollection;
import mobi.thinkchange.android.superqrcode.data.HistoryDataManagerFactory;
import mobi.thinkchange.android.superqrcode.data.HistoryItem;
import mobi.thinkchange.android.superqrcode.data.HistorySource;
import mobi.thinkchange.android.superqrcode.data.IHistoryDataManager;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;
import mobi.thinkchange.android.superqrcode.util.PreferencesUtils;

public class StartActivity extends Activity
{
  private DataCollection dataCollection;
  private boolean isInitHistory;
  private PreferencesUtils preferencesUtils;

  private static void addItem(IHistoryDataManager paramIHistoryDataManager, HistoryItem paramHistoryItem, boolean paramBoolean)
  {
    long l = paramIHistoryDataManager.addItem(paramHistoryItem);
    if (paramBoolean)
      paramIHistoryDataManager.setFavorite(l, paramBoolean);
  }

  private void initFlow()
  {
    this.dataCollection = ((DataCollection)getApplicationContext());
    this.dataCollection.setId();
    this.dataCollection.setClickFeedbackButton("0");
    this.preferencesUtils = new PreferencesUtils(this);
    this.isInitHistory = this.preferencesUtils.getIsInitHistory();
    int i = getResources().getInteger(2131296261);
    if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("isStart", true))
    {
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          if (!StartActivity.this.isInitHistory)
          {
            new InitHistoryAsyncTask(StartActivity.this).execute(new Integer[0]);
            return;
          }
          StartActivity.this.jump2HelpActivity();
        }
      }
      , i);
      return;
    }
    jump2HelpActivity();
  }

  private void jump2HelpActivity()
  {
    MiscUtils.myStartActivity(this, HelpActivity.class, getIntent());
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903051);
    initFlow();
  }

  class InitHistoryAsyncTask extends AsyncTask<Integer, Integer, String>
  {
    private long period = -1L;

    InitHistoryAsyncTask()
    {
    }

    protected String doInBackground(Integer[] paramArrayOfInteger)
    {
      this.period = SystemClock.elapsedRealtime();
      HistoryItem localHistoryItem1 = new HistoryItem();
      localHistoryItem1.setType(ParsedResultType.TEXT);
      localHistoryItem1.setName(StartActivity.this.getResources().getString(2131493173));
      localHistoryItem1.setBarcodeFormat(BarcodeFormat.QR_CODE);
      localHistoryItem1.setRawText(StartActivity.this.getResources().getString(2131493174));
      localHistoryItem1.setDisplayString(localHistoryItem1.getRawText());
      localHistoryItem1.setSource(HistorySource.SCAN_FROM_CAMERA);
      HistoryItem localHistoryItem2 = new HistoryItem();
      localHistoryItem2.setType(ParsedResultType.URI);
      localHistoryItem2.setName(StartActivity.this.getResources().getString(2131493175));
      localHistoryItem2.setBarcodeFormat(BarcodeFormat.QR_CODE);
      localHistoryItem2.setRawText(StartActivity.this.getResources().getString(2131493176));
      localHistoryItem2.setDisplayString(localHistoryItem2.getRawText());
      localHistoryItem2.setSource(HistorySource.SCAN_FROM_CAMERA);
      HistoryItem localHistoryItem3 = new HistoryItem();
      localHistoryItem3.setType(ParsedResultType.ADDRESSBOOK);
      localHistoryItem3.setName(StartActivity.this.getResources().getString(2131493177));
      localHistoryItem3.setBarcodeFormat(BarcodeFormat.QR_CODE);
      localHistoryItem3.setRawText(StartActivity.this.getResources().getString(2131493178));
      localHistoryItem3.setDisplayString(StartActivity.this.getResources().getString(2131493179));
      localHistoryItem3.setSource(HistorySource.SCAN_FROM_CAMERA);
      HistoryItem localHistoryItem4 = new HistoryItem();
      localHistoryItem4.setType(ParsedResultType.EMAIL_ADDRESS);
      localHistoryItem4.setName(StartActivity.this.getResources().getString(2131493180));
      localHistoryItem4.setBarcodeFormat(BarcodeFormat.QR_CODE);
      localHistoryItem4.setRawText(StartActivity.this.getResources().getString(2131493181));
      localHistoryItem4.setDisplayString(StartActivity.this.getResources().getString(2131493182));
      localHistoryItem4.setSource(HistorySource.SCAN_FROM_CAMERA);
      HistoryItem localHistoryItem5 = new HistoryItem();
      localHistoryItem5.setType(ParsedResultType.TEXT);
      localHistoryItem5.setName(StartActivity.this.getResources().getString(2131493185));
      localHistoryItem5.setBarcodeFormat(BarcodeFormat.QR_CODE);
      localHistoryItem5.setRawText(StartActivity.this.getResources().getString(2131493186));
      localHistoryItem5.setDisplayString(localHistoryItem5.getRawText());
      localHistoryItem5.setSource(HistorySource.SCAN_FROM_CAMERA);
      IHistoryDataManager localIHistoryDataManager = HistoryDataManagerFactory.getInstance(StartActivity.this.getApplicationContext());
      StartActivity.access$0(localIHistoryDataManager, localHistoryItem1, true);
      StartActivity.access$0(localIHistoryDataManager, localHistoryItem2, true);
      StartActivity.access$0(localIHistoryDataManager, localHistoryItem4, false);
      StartActivity.access$0(localIHistoryDataManager, localHistoryItem3, false);
      StartActivity.access$0(localIHistoryDataManager, localHistoryItem5, false);
      this.period = (SystemClock.elapsedRealtime() - this.period);
      return "0";
    }

    protected void onPostExecute(String paramString)
    {
      super.onPostExecute(paramString);
      Log.e("StartActivity", "初始化时间： " + this.period + "ms");
      StartActivity.this.jump2HelpActivity();
      StartActivity.this.preferencesUtils.setIsInitHistory(true);
    }
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.StartActivity
 * JD-Core Version:    0.6.0
 */