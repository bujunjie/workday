package com.junjie.model;

import java.util.Date;
import java.util.List;

/**
 * @author jbu
 */
public interface TimesheetManager {
  List getTimesheets();

  List getTimesheets(int employeeId);

  Timesheet getTimesheet(int employeeId, Date periodEndingDate);

  void saveTimesheet(Timesheet timesheet);

  void deleteTimesheet(int timesheetId);

  Timesheet getTimesheet(int timesheetId, boolean doLock);
}
