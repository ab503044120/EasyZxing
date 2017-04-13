package org.huihui.zxingimprove.fragment.scanfragmentstack;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import com.google.zxing.client.result.CalendarParsedResult;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import java.text.DateFormat;
import java.util.Date;
import mobi.thinkchange.android.superqrcode.util.MiscUtils;

public class ScanFragmentChild02ScanResultCalendar extends AbstractScanFragmentChild02Scan
{
  private CalendarParsedResult calendarParsedResult;
  private String description;
  private TextView descriptionTextView;
  private Date endTime;
  private String endTimeStr;
  private TextView endTimeTextView;
  private String location;
  private TextView locationTextView;
  private String shareText;
  private Date startTime;
  private String startTimeStr;
  private TextView startTimeTextView;
  private String subject;
  private TextView subjectTextView;

  private static String format(boolean paramBoolean, Date paramDate)
  {
    if (paramDate == null)
      return null;
    if (paramBoolean);
    for (DateFormat localDateFormat = DateFormat.getDateInstance(2); ; localDateFormat = DateFormat.getDateTimeInstance(2, 2))
      return localDateFormat.format(paramDate);
  }

  private void initData()
  {
    if (this.calendarParsedResult.getSummary() != null)
      this.subject = this.calendarParsedResult.getSummary();
    if (this.calendarParsedResult.getStart() != null)
      this.startTime = this.calendarParsedResult.getStart();
    if (this.calendarParsedResult.getEnd() != null)
      this.endTime = this.calendarParsedResult.getEnd();
    if (this.calendarParsedResult.getLocation() != null)
      this.location = this.calendarParsedResult.getLocation();
    if (this.calendarParsedResult.getDescription() != null)
      this.description = this.calendarParsedResult.getDescription();
    this.startTimeStr = format(this.calendarParsedResult.isStartAllDay(), this.startTime);
    this.endTimeStr = format(this.calendarParsedResult.isEndAllDay(), this.endTime);
    this.subjectTextView.setText(this.subject);
    this.startTimeTextView.setText(this.startTimeStr);
    this.endTimeTextView.setText(this.endTimeStr);
    this.locationTextView.setText(this.location);
    this.descriptionTextView.setText(this.description);
    this.shareText = (getResources().getString(2131493043) + this.subject + "\n" + getResources().getString(2131493058) + this.startTimeStr + "\n" + getResources().getString(2131493059) + this.endTimeStr + "\n" + getResources().getString(2131493060) + this.location + "\n" + getResources().getString(2131493061) + this.description);
  }

  private void initView(View paramView)
  {
    this.subjectTextView = ((TextView)paramView.findViewById(2131624137));
    this.startTimeTextView = ((TextView)paramView.findViewById(2131624139));
    this.endTimeTextView = ((TextView)paramView.findViewById(2131624141));
    this.locationTextView = ((TextView)paramView.findViewById(2131624143));
    this.descriptionTextView = ((TextView)paramView.findViewById(2131624145));
  }

  protected void clickBottomButton()
  {
    MiscUtils.addCalendarEvent(this.mContext, this.subject, this.startTime, this.calendarParsedResult.isStartAllDay(), this.endTime, this.location, this.description);
  }

  protected int getBottomButtonStringResource()
  {
    return 2131493057;
  }

  protected int getLayoutResource()
  {
    return 2130903079;
  }

  protected void getParsedResult(ParsedResult paramParsedResult)
  {
    this.calendarParsedResult = ((CalendarParsedResult)paramParsedResult);
  }

  protected int getResultImageResource()
  {
    return 2130837599;
  }

  protected ParsedResultType getScanType()
  {
    return ParsedResultType.CALENDAR;
  }

  protected String getShareText()
  {
    return this.shareText;
  }

  protected int getTitleStringResource()
  {
    return 2131493016;
  }

  protected void initChildData()
  {
    initData();
  }

  protected void initChildView(View paramView)
  {
    initView(paramView);
  }
}

/* Location:           C:\Users\Administrator\Desktop\base_dex2jar.jar
 * Qualified Name:     mobi.thinkchange.android.superqrcode.fragment.scanfragmentstack.ScanFragmentChild02ScanResultCalendar
 * JD-Core Version:    0.6.0
 */