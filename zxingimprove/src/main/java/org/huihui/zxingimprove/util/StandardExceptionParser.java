package org.huihui.zxingimprove.util;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class StandardExceptionParser
{
  private final TreeSet<String> includedPackages = new TreeSet();

  public StandardExceptionParser(Context paramContext, Collection<String> paramCollection)
  {
    setIncludedPackages(paramContext, paramCollection);
  }

  protected StackTraceElement getBestStackTraceElement(Throwable paramThrowable)
  {
    StackTraceElement[] arrayOfStackTraceElement = paramThrowable.getStackTrace();
    if ((arrayOfStackTraceElement == null) || (arrayOfStackTraceElement.length == 0))
      return null;
    int i = arrayOfStackTraceElement.length;
    int j = 0;
    if (j >= i)
      return arrayOfStackTraceElement[0];
    StackTraceElement localStackTraceElement = arrayOfStackTraceElement[j];
    String str = localStackTraceElement.getClassName();
    Iterator localIterator = this.includedPackages.iterator();
    do
    {
      if (localIterator.hasNext())
        continue;
      j++;
      break;
    }
    while (!str.startsWith((String)localIterator.next()));
    return localStackTraceElement;
  }

  protected Throwable getCause(Throwable paramThrowable)
  {
    for (Throwable localThrowable = paramThrowable; ; localThrowable = localThrowable.getCause())
      if (localThrowable.getCause() == null)
        return localThrowable;
  }

  public String getDescription(String paramString, Throwable paramThrowable)
  {
    return getDescription(getCause(paramThrowable), getBestStackTraceElement(getCause(paramThrowable)), paramString);
  }

  protected String getDescription(Throwable paramThrowable, StackTraceElement paramStackTraceElement, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramThrowable.getClass().getSimpleName());
    String str1 = paramThrowable.getMessage();
    if (str1 != null)
    {
      if (str1.length() > 50)
        str1.substring(0, 47).concat("...");
      localStringBuilder.append(":").append(paramThrowable.getMessage());
    }
    if (paramStackTraceElement != null)
    {
      String[] arrayOfString = paramStackTraceElement.getClassName().split("\\.");
      String str2 = "unknown";
      if ((arrayOfString != null) && (arrayOfString.length > 0))
        str2 = arrayOfString[(-1 + arrayOfString.length)];
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = str2;
      arrayOfObject[1] = paramStackTraceElement.getMethodName();
      arrayOfObject[2] = Integer.valueOf(paramStackTraceElement.getLineNumber());
      localStringBuilder.append(String.format(" (@%s.%s:%d)", arrayOfObject));
    }
    if (paramString != null)
      localStringBuilder.append(String.format(" {%s}", new Object[] { paramString }));
    return localStringBuilder.toString();
  }

  public void setIncludedPackages(Context paramContext, Collection<String> paramCollection)
  {
    this.includedPackages.clear();
    HashSet localHashSet = new HashSet();
    if (paramCollection != null)
      localHashSet.addAll(paramCollection);
    if (paramContext != null);
    String str1;
    int i;
    Iterator localIterator2;
    try
    {
      String str3 = paramContext.getApplicationContext().getPackageName();
      this.includedPackages.add(str3);
      ActivityInfo[] arrayOfActivityInfo = paramContext.getApplicationContext().getPackageManager().getPackageInfo(str3, 15).activities;
      int j;
      if (arrayOfActivityInfo != null)
        j = arrayOfActivityInfo.length;
      for (int k = 0; ; k++)
      {
        if (k >= j)
        {
          localIterator1 = localHashSet.iterator();
          if (localIterator1.hasNext())
            break;
          return;
        }
        localHashSet.add(arrayOfActivityInfo[k].packageName);
      }
    }
    catch (NameNotFoundException localNameNotFoundException)
    {
      Iterator localIterator1;
      while (true)
        Log.i("tesw", "No package found");
      str1 = (String)localIterator1.next();
      i = 1;
      localIterator2 = this.includedPackages.iterator();
    }
    while (true)
    {
      if (!localIterator2.hasNext());
      while (true)
      {
        if (i == 0)
          break label237;
        this.includedPackages.add(str1);
        break;
        String str2 = (String)localIterator2.next();
        if (str1.startsWith(str2))
          break label239;
        if (!str2.startsWith(str1))
          continue;
        this.includedPackages.remove(str2);
      }
      label237: break;
      label239: i = 0;
    }
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.util.StandardExceptionParser
 * JD-Core Version:    0.6.0
 */