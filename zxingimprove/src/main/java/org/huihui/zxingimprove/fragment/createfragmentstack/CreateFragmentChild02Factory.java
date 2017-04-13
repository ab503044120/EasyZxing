package org.huihui.zxingimprove.fragment.createfragmentstack;

import android.support.v4.app.Fragment;

public class CreateFragmentChild02Factory
{
  static Fragment createTargetFragment(String paramString)
  {
    CreateFragmentChild02CreateWifi localCreateFragmentChild02CreateWifi;
    if ("WIFI_TYPE".equals(paramString))
      localCreateFragmentChild02CreateWifi = new CreateFragmentChild02CreateWifi();
    boolean bool;
    do
    {
      return localCreateFragmentChild02CreateWifi;
      if ("TEXT_TYPE".equals(paramString))
        return new CreateFragmentChild02CreateText();
      if ("LOCATION_TYPE".equals(paramString))
        return new CreateFragmentChild02CreateLocation();
      if ("SMS_TYPE".equals(paramString))
        return new CreateFragmentChild02CreateSms();
      if ("EMAIL_TYPE".equals(paramString))
        return new CreateFragmentChild02CreateEmail();
      if ("URL_KEY".equals(paramString))
        return new CreateFragmentChild02CreateUrl();
      if ("PHONE_TYPE".equals(paramString))
        return new CreateFragmentChild02CreateTel();
      if ("CONTACT_TYPE".equals(paramString))
        return new CreateFragmentChild02CreateAddressbook();
      if ("CALENDAR_TYPE".equals(paramString))
        return new CreateFragmentChild02CreateCalendar();
      bool = "PRODUCT_TYPE".equals(paramString);
      localCreateFragmentChild02CreateWifi = null;
    }
    while (!bool);
    return new CreateFragmentChild02CreateProduct();
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.createfragmentstack.CreateFragmentChild02Factory
 * JD-Core Version:    0.6.0
 */