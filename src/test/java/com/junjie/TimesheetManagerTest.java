package com.junjie;

import com.junjie.model.Employee;
import com.junjie.model.EmployeeManager;
import com.junjie.model.Timesheet;
import com.junjie.model.TimesheetManager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author jbu
 */
public class TimesheetManagerTest extends BaseTest {
  @Autowired
  private TimesheetManager timesheetManager;

  @Autowired
  private EmployeeManager employeeManager;

  private int employeeId;

  private Date periodEndingDate = new GregorianCalendar(2006,
    Calendar.SEPTEMBER, 15).getTime();

  @Before
  public void setUpClass() {
    Employee e = new Employee(0, "Test Employee", "H", "123", "test@test.com", 0);
    employeeId = employeeManager.add(e);
  }

  @Test
  public void testGetAll() {
    addTimesheet();
    List timesheetList = timesheetManager.getTimesheets();
    assertNotNull(timesheetList);
    assertTrue(timesheetList.size() > 0);
  }

  @Test
  public void testGetByEmployeeId() {
    addTimesheet();
    List<Timesheet> timesheetList = timesheetManager.getTimesheets(employeeId);
    assertNotNull(timesheetList);

    Timesheet timesheet;
    for (int i = 0; i < timesheetList.size(); i++) {
      timesheet = timesheetList.get(i);
      assertEquals(employeeId, timesheet.getEmployeeId());
      System.out.println(">>>> Department name = " + timesheet.getDepartment().getName());
    }
  }

  @Test
  public void testGetByEmployeeAndPeriod() {
    addTimesheet();

    Timesheet timesheet = timesheetManager.getTimesheet(employeeId,
      periodEndingDate);
    assertNotNull(timesheet);

    assertEquals(employeeId, timesheet.getEmployeeId());
    assertTrue(!periodEndingDate.before(timesheet.getPeriodEndingDate()));
    assertTrue(!periodEndingDate.after(timesheet.getPeriodEndingDate()));
    System.out.println(">>>> Department name = "
      + timesheet.getDepartment().getName());
  }

  public Timesheet addTimesheet() {
    Timesheet timesheet = new Timesheet(0, employeeId, "P", "IT", periodEndingDate, 480, 480, 480, 480, 480, 0, 0);
    timesheetManager.saveTimesheet(timesheet);
    return timesheet;
  }

  @Test
  public void testAdd() {
    Timesheet timesheet = timesheetManager.getTimesheet(employeeId,
      periodEndingDate);
    boolean newRecord = false;
    if (timesheet == null) {
      timesheet = new Timesheet();
      timesheet.setEmployeeId(employeeId);
      timesheet.setPeriodEndingDate(periodEndingDate);
      newRecord = true;
    } else {
      System.out.println("Timesheet record found: "
        + timesheet.getTimesheetId());
    }
    timesheet = addTimesheet();

    Timesheet timesheetInDb = timesheetManager.getTimesheet(employeeId,
      periodEndingDate);
//    assertTrue(timesheetInDb.equals(timesheet));
  }

  @Test
  public void testDeleteByEmployeeId() {
    Date periodEndingDate = new Date();
    Timesheet timesheet = timesheetManager.getTimesheet(employeeId,
      periodEndingDate);

    // delete just in case it exists
    if (timesheet == null) {
      timesheet = new Timesheet();
      timesheet.setEmployeeId(employeeId);
      timesheet.setPeriodEndingDate(periodEndingDate);
    } else {
      timesheetManager.deleteTimesheet(timesheet.getTimesheetId());
    }
    timesheet = addTimesheet();

    assertTrue(timesheet.getTimesheetId() > 0);
    timesheetManager.deleteTimesheet(timesheet.getTimesheetId());

    timesheet = timesheetManager.getTimesheet(employeeId,
      periodEndingDate);
    assertNull(timesheet);
  }

  @Test
  public void testGetByTimesheetId() {
    List timesheetList = timesheetManager.getTimesheets();
    assertNotNull(timesheetList);
    assertTrue(timesheetList.size() > 0);

    Timesheet timesheetInList = (Timesheet) timesheetList.get(0);
    int timesheetId = timesheetInList.getTimesheetId();
    Timesheet timesheetInDb = timesheetManager.getTimesheet(timesheetId, false);
    assertNotNull(timesheetInDb);
//    assertEquals(timesheetInDb, timesheetInList);
  }

}
