package org.huihui.zxingimprove.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.result.ParsedResultType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class SQLiteHistoryDataManager
  implements IHistoryDataManager
{
  private static final String CREATE_TABLE_COUNTER;
  private static final String CREATE_TABLE_ITEM = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER NOT NULL, '%s' INTEGER NOT NULL, '%s' INTEGER NOT NULL);", new Object[] { "barcode_history_items", "id", "type", "name", "barcode_format", "raw_text", "display_string", "is_favorite", "source", "time" });
  private static final String DB_BARCODE_HISTORY_FILENAME = "barcode_history.db";
  private static final String INIT_TABLE_COUNTER;
  private static final int MAX_ITEM_NUMBER = 500;
  private static final String TABLE_COMMON_COLUMN_ID = "id";
  private static final String TABLE_COUNTER_COLUMN_IS_PRESERVED = "is_preserved";
  private static final String TABLE_COUNTER_COLUMN_ORDER = "barcode_order";
  private static final String TABLE_COUNTER_COLUMN_TYPE = "type";
  private static final String TABLE_COUNTER_COLUMN_VALUE = "value";
  private static final String TABLE_COUNTER_NAME = "barcode_history_counters";
  private static final String TABLE_ITEM_COLUMN_BARCODE_FORMAT = "barcode_format";
  private static final String TABLE_ITEM_COLUMN_DISPLAY_STRING = "display_string";
  private static final String TABLE_ITEM_COLUMN_IS_FAVORITE = "is_favorite";
  private static final String TABLE_ITEM_COLUMN_NAME = "name";
  private static final String TABLE_ITEM_COLUMN_RAW_TEXT = "raw_text";
  private static final String TABLE_ITEM_COLUMN_SOURCE = "source";
  private static final String TABLE_ITEM_COLUMN_TIME = "time";
  private static final String TABLE_ITEM_COLUMN_TYPE = "type";
  private static final String TABLE_ITEM_NAME = "barcode_history_items";
  private Context mContext;
  private BarcodeHistorySQLiteOpenHelper mDbHelper;

  static
  {
    CREATE_TABLE_COUNTER = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' INTEGER NOT NULL, '%s' INTEGER NOT NULL, '%s' INTEGER NOT NULL);", new Object[] { "barcode_history_counters", "id", "type", "value", "barcode_order", "is_preserved" });
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(String.format("INSERT INTO %s(%s, %s, %s, %s)", new Object[] { "barcode_history_counters", "type", "value", "barcode_order", "is_preserved" }));
    localStringBuffer.append(" SELECT ").append(4098).append(",").append(0).append(",").append(0).append(",").append(1);
    localStringBuffer.append(" UNION ALL SELECT ").append(4097).append(",").append(0).append(",").append(1).append(",").append(1);
    ParsedResultType[] arrayOfParsedResultType = ParsedResultType.values();
    int i = 0;
    if (i >= arrayOfParsedResultType.length)
    {
      localStringBuffer.append(";");
      INIT_TABLE_COUNTER = localStringBuffer.toString();
      android.util.Log.w(SQLiteHistoryDataManager.class.getSimpleName(), INIT_TABLE_COUNTER);
      return;
    }
    if (arrayOfParsedResultType[i] == ParsedResultType.VIN);
    while (true)
    {
      i++;
      break;
      localStringBuffer.append(" UNION ALL SELECT ").append(arrayOfParsedResultType[i].ordinal()).append(",").append(0).append(",").append(0).append(",").append(0);
    }
  }

  public SQLiteHistoryDataManager(Context paramContext)
  {
    this(paramContext, "barcode_history.db");
  }

  SQLiteHistoryDataManager(Context paramContext, String paramString)
  {
    this.mContext = paramContext.getApplicationContext();
    this.mDbHelper = new BarcodeHistorySQLiteOpenHelper(this.mContext, paramString);
  }

  private void close()
  {
    try
    {
      this.mDbHelper.getWritableDatabase().close();
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      Log.wDebug("Error opening database for close");
    }
  }

  private int deleteItem(long paramLong)
  {
    ArrayList localArrayList = new ArrayList();
    HistoryItem localHistoryItem = new HistoryItem();
    localHistoryItem.setId(paramLong);
    localArrayList.add(localHistoryItem);
    return deleteItems(localArrayList);
  }

  private int deleteItems(Collection<HistoryItem> paramCollection)
  {
    if (paramCollection == null)
      throw new NullPointerException("logs cannot be null");
    if (paramCollection.isEmpty())
      return 0;
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase("Error opening database for deleteLog");
    if (localSQLiteDatabase == null)
      return 0;
    String[] arrayOfString = new String[paramCollection.size()];
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = TextUtils.join(",", Collections.nCopies(arrayOfString.length, "?"));
    String str = String.format("id in (%s)", arrayOfObject);
    int i = 0;
    Iterator localIterator = paramCollection.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localSQLiteDatabase.delete("barcode_history_items", str, arrayOfString);
      HistoryItem localHistoryItem = (HistoryItem)localIterator.next();
      int j = i + 1;
      arrayOfString[i] = Long.toString(localHistoryItem.getId());
      i = j;
    }
  }

  private void dropTable(SQLiteDatabase paramSQLiteDatabase, String paramString)
  {
    paramSQLiteDatabase.execSQL("DROP TABLE " + paramString);
  }

  private boolean execSingleSql(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return false;
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase("Error opening database for putHit");
    if (localSQLiteDatabase == null)
      return false;
    try
    {
      localSQLiteDatabase.execSQL(paramString);
      i = 1;
      return i;
    }
    catch (SQLiteException localSQLiteException)
    {
      while (true)
      {
        Log.wDebug("Error storing hit");
        int i = 0;
      }
    }
  }

  static boolean getBooleanFromInt(int paramInt)
  {
    return paramInt != 0;
  }

  private List<HistoryItem> getHistoryItems(int paramInt1, int paramInt2, boolean paramBoolean, HistorySource[] paramArrayOfHistorySource)
  {
    ArrayList localArrayList = new ArrayList();
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase("Error opening database for peekHits");
    if (localSQLiteDatabase == null);
    while (true)
    {
      return localArrayList;
      String str1;
      label78: StringBuffer localStringBuffer;
      int i;
      label90: String str2;
      label198: Cursor localCursor;
      ParsedResultType[] arrayOfParsedResultType;
      BarcodeFormat[] arrayOfBarcodeFormat;
      HistorySource[] arrayOfHistorySource;
      switch (paramInt1)
      {
      default:
        Object[] arrayOfObject3 = new Object[2];
        arrayOfObject3[0] = "type";
        arrayOfObject3[1] = String.valueOf(paramInt1);
        str1 = String.format("%s = %s", arrayOfObject3);
        localStringBuffer = new StringBuffer();
        i = 0;
        if (i < paramArrayOfHistorySource.length)
          break;
        if (localStringBuffer.length() > 0)
        {
          localStringBuffer.deleteCharAt(-1 + localStringBuffer.length());
          localStringBuffer.insert(0, String.format("%s in (", new Object[] { "source" }));
          localStringBuffer.append(")");
        }
        if (localStringBuffer.length() > 0)
          str1 = str1 + " AND " + localStringBuffer.toString();
        if (paramBoolean)
        {
          str2 = "%s ASC";
          localCursor = null;
          arrayOfParsedResultType = ParsedResultType.values();
          arrayOfBarcodeFormat = BarcodeFormat.values();
          arrayOfHistorySource = HistorySource.values();
        }
      case 4098:
      case 4097:
      }
      try
      {
        String[] arrayOfString = { "id", "type", "name", "barcode_format", "raw_text", "display_string", "is_favorite", "source", "time" };
        Object[] arrayOfObject2 = { "id" };
        localCursor = localSQLiteDatabase.query("barcode_history_items", arrayOfString, str1, null, null, null, String.format(str2, arrayOfObject2), Integer.toString(paramInt2));
        if (localCursor.moveToFirst())
        {
          boolean bool;
          do
          {
            HistoryItem localHistoryItem = new HistoryItem();
            localHistoryItem.setId(localCursor.getInt(0));
            localHistoryItem.setType(arrayOfParsedResultType[localCursor.getInt(1)]);
            localHistoryItem.setName(localCursor.getString(2));
            localHistoryItem.setBarcodeFormat(arrayOfBarcodeFormat[localCursor.getInt(3)]);
            localHistoryItem.setRawText(localCursor.getString(4));
            localHistoryItem.setDisplayString(localCursor.getString(5));
            localHistoryItem.setFavorite(getBooleanFromInt(localCursor.getInt(6)));
            localHistoryItem.setSource(arrayOfHistorySource[localCursor.getInt(7)]);
            localHistoryItem.setTime(localCursor.getLong(8));
            localArrayList.add(localHistoryItem);
            bool = localCursor.moveToNext();
          }
          while (bool);
        }
        return localArrayList;
        str1 = "1";
        break label78;
        Object[] arrayOfObject1 = new Object[2];
        arrayOfObject1[0] = "is_favorite";
        arrayOfObject1[1] = String.valueOf(1);
        str1 = String.format("%s = %s", arrayOfObject1);
        break label78;
        localStringBuffer.append(paramArrayOfHistorySource[i].ordinal()).append(',');
        i++;
        break label90;
        str2 = "%s DESC";
        break label198;
      }
      catch (SQLiteException localSQLiteException)
      {
        Log.wDebug("error in peekHits fetching hitIds: " + localSQLiteException.getMessage());
        return localArrayList;
      }
      finally
      {
        if (localCursor != null)
          localCursor.close();
      }
    }
    throw localObject;
  }

  private int getNumStoredItems()
  {
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase("Error opening database for requestNumLogsPending");
    if (localSQLiteDatabase == null)
      return 0;
    Cursor localCursor = null;
    try
    {
      localCursor = localSQLiteDatabase.rawQuery("SELECT COUNT(*) from barcode_history_items", null);
      boolean bool = localCursor.moveToFirst();
      i = 0;
      if (bool)
      {
        long l = localCursor.getLong(0);
        i = (int)l;
      }
      return i;
    }
    catch (SQLiteException localSQLiteException)
    {
      while (true)
      {
        Log.wDebug("Error getting numStoredLogs");
        int i = 0;
        if (localCursor == null)
          continue;
        localCursor.close();
        i = 0;
      }
    }
    finally
    {
      if (localCursor != null)
        localCursor.close();
    }
    throw localObject;
  }

  private SQLiteDatabase getWritableDatabase(String paramString)
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = this.mDbHelper.getWritableDatabase();
      return localSQLiteDatabase;
    }
    catch (SQLiteException localSQLiteException)
    {
      Log.wDebug(paramString);
    }
    return null;
  }

  private List<HistoryItem> peekItems(int paramInt, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase("Error opening database for peekHits");
    if (localSQLiteDatabase == null);
    while (true)
    {
      return localArrayList;
      String str;
      label33: Cursor localCursor;
      ParsedResultType[] arrayOfParsedResultType;
      BarcodeFormat[] arrayOfBarcodeFormat;
      HistorySource[] arrayOfHistorySource;
      if (paramBoolean)
      {
        str = "%s ASC";
        localCursor = null;
        arrayOfParsedResultType = ParsedResultType.values();
        arrayOfBarcodeFormat = BarcodeFormat.values();
        arrayOfHistorySource = HistorySource.values();
      }
      try
      {
        String[] arrayOfString = { "id", "type", "name", "barcode_format", "raw_text", "display_string", "is_favorite", "source", "time" };
        Object[] arrayOfObject = { "id" };
        localCursor = localSQLiteDatabase.query("barcode_history_items", arrayOfString, null, null, null, null, String.format(str, arrayOfObject), Integer.toString(paramInt));
        if (localCursor.moveToFirst())
        {
          boolean bool;
          do
          {
            HistoryItem localHistoryItem = new HistoryItem();
            localHistoryItem.setId(localCursor.getInt(0));
            localHistoryItem.setType(arrayOfParsedResultType[localCursor.getInt(1)]);
            localHistoryItem.setName(localCursor.getString(2));
            localHistoryItem.setBarcodeFormat(arrayOfBarcodeFormat[localCursor.getInt(3)]);
            localHistoryItem.setRawText(localCursor.getString(4));
            localHistoryItem.setDisplayString(localCursor.getString(5));
            localHistoryItem.setFavorite(getBooleanFromInt(localCursor.getInt(6)));
            localHistoryItem.setSource(arrayOfHistorySource[localCursor.getInt(7)]);
            localHistoryItem.setTime(localCursor.getLong(8));
            localArrayList.add(localHistoryItem);
            bool = localCursor.moveToNext();
          }
          while (bool);
        }
        return localArrayList;
        str = "%s DESC";
        break label33;
      }
      catch (SQLiteException localSQLiteException)
      {
        Log.wDebug("error in peekHits fetching hitIds: " + localSQLiteException.getMessage());
        return localArrayList;
      }
      finally
      {
        if (localCursor != null)
          localCursor.close();
      }
    }
    throw localObject;
  }

  private void removeOldItemIfFull()
  {
    int i = 1 + (-500 + getNumStoredItems());
    if (i > 0)
    {
      List localList = peekItems(i, true);
      Log.wDebug("Store full, deleting " + localList.size() + " hits to make room");
      deleteItems(localList);
    }
  }

  private void updateCounter(int paramInt1, int paramInt2)
  {
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase("Error opening database for putHit");
    if (localSQLiteDatabase == null)
      return;
    try
    {
      localSQLiteDatabase.beginTransaction();
      localSQLiteDatabase.execSQL("UPDATE barcode_history_counters SET value=value+" + paramInt2 + " where " + "type" + "=" + paramInt1);
      if (paramInt1 != 4097)
        localSQLiteDatabase.execSQL("UPDATE barcode_history_counters SET value=value+" + paramInt2 + " where " + "type" + "=" + 4098);
      localSQLiteDatabase.setTransactionSuccessful();
      return;
    }
    catch (SQLiteException localSQLiteException)
    {
      Log.wDebug("Error storing hit");
      return;
    }
    finally
    {
      localSQLiteDatabase.endTransaction();
    }
    throw localObject;
  }

  private boolean updateFavorite(long paramLong, boolean paramBoolean)
  {
    if (paramBoolean);
    for (int i = 1; ; i = 0)
    {
      Object[] arrayOfObject = new Object[5];
      arrayOfObject[0] = "barcode_history_items";
      arrayOfObject[1] = "is_favorite";
      arrayOfObject[2] = Integer.valueOf(i);
      arrayOfObject[3] = "id";
      arrayOfObject[4] = Long.valueOf(paramLong);
      return execSingleSql(String.format("UPDATE %s SET %s=%s where %s=%s", arrayOfObject));
    }
  }

  private long writeItemToDatabase(HistoryItem paramHistoryItem)
  {
    long l1 = -1L;
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase("Error opening database for putHit");
    if (localSQLiteDatabase == null)
      return l1;
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("type", Integer.valueOf(paramHistoryItem.getType().ordinal()));
    localContentValues.put("name", paramHistoryItem.getName());
    localContentValues.put("barcode_format", Integer.valueOf(paramHistoryItem.getBarcodeFormat().ordinal()));
    localContentValues.put("raw_text", paramHistoryItem.getRawText());
    localContentValues.put("display_string", paramHistoryItem.getDisplayString());
    int i;
    if (paramHistoryItem.isFavorite())
      i = 1;
    while (true)
    {
      localContentValues.put("is_favorite", Integer.valueOf(i));
      localContentValues.put("source", Integer.valueOf(paramHistoryItem.getSource().ordinal()));
      localContentValues.put("time", Long.valueOf(System.currentTimeMillis()));
      try
      {
        long l2 = localSQLiteDatabase.insert("barcode_history_items", null, localContentValues);
        l1 = l2;
        return l1;
        i = 0;
      }
      catch (SQLiteException localSQLiteException)
      {
        while (true)
          Log.wDebug("Error storing hit");
      }
    }
  }

  public long addItem(HistoryItem paramHistoryItem)
  {
    if (paramHistoryItem.getType() == null);
    do
      return -1L;
    while ((paramHistoryItem.getBarcodeFormat() == null) || (paramHistoryItem.getRawText() == null) || (paramHistoryItem.getDisplayString() == null) || (paramHistoryItem.getSource() == null));
    removeOldItemIfFull();
    long l = writeItemToDatabase(paramHistoryItem);
    if (paramHistoryItem.getSource() != HistorySource.CREATE)
      updateCounter(paramHistoryItem.getType().ordinal(), 1);
    close();
    return l;
  }

  public boolean deleteAll()
  {
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase("Error opening database for deleteAll");
    if (localSQLiteDatabase == null)
      return false;
    localSQLiteDatabase.beginTransaction();
    try
    {
      dropTable(localSQLiteDatabase, "barcode_history_items");
      dropTable(localSQLiteDatabase, "barcode_history_counters");
      localSQLiteDatabase.setTransactionSuccessful();
      return true;
    }
    finally
    {
      localSQLiteDatabase.endTransaction();
      close();
    }
    throw localObject;
  }

  public boolean deleteItem(long paramLong, HistorySource paramHistorySource, ParsedResultType paramParsedResultType, boolean paramBoolean)
  {
    if (paramHistorySource == null);
    int i;
    do
    {
      do
        return false;
      while (paramParsedResultType == null);
      i = deleteItem(paramLong);
      if ((i == 1) && (paramHistorySource != HistorySource.CREATE))
        updateCounter(paramParsedResultType.ordinal(), -1);
      if ((i == 1) && (paramBoolean))
        updateCounter(4097, -1);
      close();
    }
    while (i != 1);
    return true;
  }

  public List<HistoryCounter> getHistoryCounters()
  {
    ArrayList localArrayList = new ArrayList();
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase("Error opening database for peekCounters");
    if (localSQLiteDatabase == null)
      return localArrayList;
    Cursor localCursor = null;
    try
    {
      localCursor = localSQLiteDatabase.query("barcode_history_counters", new String[] { "id", "type", "value", "barcode_order", "is_preserved" }, String.format("%s = 1 or %s > 0", new Object[] { "is_preserved", "value" }), null, null, null, String.format("%s DESC, %s DESC, %s DESC", new Object[] { "is_preserved", "barcode_order", "value" }), null);
      if (localCursor.moveToFirst())
      {
        boolean bool;
        do
        {
          HistoryCounter localHistoryCounter = new HistoryCounter();
          localHistoryCounter.setId(localCursor.getInt(0));
          localHistoryCounter.setType(localCursor.getInt(1));
          localHistoryCounter.setValue(localCursor.getInt(2));
          localHistoryCounter.setOrder(localCursor.getInt(3));
          localHistoryCounter.setPreserved(getBooleanFromInt(localCursor.getInt(4)));
          localArrayList.add(localHistoryCounter);
          bool = localCursor.moveToNext();
        }
        while (bool);
      }
      return localArrayList;
    }
    catch (SQLiteException localSQLiteException)
    {
      Log.wDebug("error in peekHits fetching hitIds: " + localSQLiteException.getMessage());
      return localArrayList;
    }
    finally
    {
      if (localCursor != null)
        localCursor.close();
      localSQLiteDatabase.close();
    }
    throw localObject;
  }

  public List<HistoryItem> getHistoryItems(int paramInt, HistorySource[] paramArrayOfHistorySource)
  {
    return getHistoryItems(paramInt, 100, false, paramArrayOfHistorySource);
  }

  public boolean isFavorite(long paramLong)
  {
    SQLiteDatabase localSQLiteDatabase = getWritableDatabase("Error opening database for peekCounters");
    if (localSQLiteDatabase == null)
      return false;
    Cursor localCursor = null;
    try
    {
      String[] arrayOfString = { "is_favorite" };
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = "id";
      arrayOfObject[1] = Long.valueOf(paramLong);
      localCursor = localSQLiteDatabase.query("barcode_history_items", arrayOfString, String.format("%s = %s", arrayOfObject), null, null, null, null, null);
      boolean bool = localCursor.moveToFirst();
      i = 0;
      if (bool)
      {
        int j = localCursor.getInt(0);
        if (j != 1)
          break label126;
      }
      label126: for (i = 1; ; i = 0)
        return i;
    }
    catch (SQLiteException localSQLiteException)
    {
      while (true)
      {
        Log.wDebug("error in peekHits fetching hitIds: " + localSQLiteException.getMessage());
        if (localCursor != null)
          localCursor.close();
        localSQLiteDatabase.close();
        int i = 0;
      }
    }
    finally
    {
      if (localCursor != null)
        localCursor.close();
      localSQLiteDatabase.close();
    }
    throw localObject;
  }

  public boolean setFavorite(long paramLong, boolean paramBoolean)
  {
    boolean bool = updateFavorite(paramLong, paramBoolean);
    if (paramBoolean);
    for (int i = 1; ; i = -1)
    {
      updateCounter(4097, i);
      close();
      return bool;
    }
  }

  public boolean setName(long paramLong, String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return false;
    Object[] arrayOfObject = new Object[5];
    arrayOfObject[0] = "barcode_history_items";
    arrayOfObject[1] = "name";
    arrayOfObject[2] = paramString;
    arrayOfObject[3] = "id";
    arrayOfObject[4] = Long.valueOf(paramLong);
    boolean bool = execSingleSql(String.format("UPDATE %s SET %s='%s' where %s=%s", arrayOfObject));
    close();
    return bool;
  }

  private class BarcodeHistorySQLiteOpenHelper extends SQLiteOpenHelper
  {
    private boolean badDatabase;

    BarcodeHistorySQLiteOpenHelper(Context paramString, String arg3)
    {
      super(str, null, 1);
    }

    private boolean tablePresent(String paramString, SQLiteDatabase paramSQLiteDatabase)
    {
      Cursor localCursor = null;
      try
      {
        localCursor = paramSQLiteDatabase.query("SQLITE_MASTER", new String[] { "name" }, "name=?", new String[] { paramString }, null, null, null);
        boolean bool2 = localCursor.moveToFirst();
        bool1 = bool2;
        return bool1;
      }
      catch (SQLiteException localSQLiteException)
      {
        Log.wDebug("error querying for table " + paramString);
        boolean bool1 = false;
        return false;
      }
      finally
      {
        if (localCursor != null)
          localCursor.close();
      }
      throw localObject;
    }

    public SQLiteDatabase getWritableDatabase()
    {
      if (this.badDatabase)
        throw new SQLiteException("Database creation failed");
      return super.getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase)
    {
    }

    public void onOpen(SQLiteDatabase paramSQLiteDatabase)
    {
      try
      {
        if (!tablePresent("barcode_history_items", paramSQLiteDatabase))
          paramSQLiteDatabase.execSQL(SQLiteHistoryDataManager.CREATE_TABLE_ITEM);
        if (!tablePresent("barcode_history_counters", paramSQLiteDatabase))
        {
          paramSQLiteDatabase.execSQL(SQLiteHistoryDataManager.CREATE_TABLE_COUNTER);
          paramSQLiteDatabase.execSQL(SQLiteHistoryDataManager.INIT_TABLE_COUNTER);
        }
        return;
      }
      catch (SQLiteException localSQLiteException)
      {
        Log.wDebug("Error on database open");
        this.badDatabase = true;
      }
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
    {
    }
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.data.SQLiteHistoryDataManager
 * JD-Core Version:    0.6.0
 */