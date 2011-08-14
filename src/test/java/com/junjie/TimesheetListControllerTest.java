package com.junjie;

import com.junjie.workday.controller.TimesheetListController;
import com.junjie.workday.model.Employee;
import com.junjie.workday.model.Timesheet;
import com.junjie.workday.model.TimesheetManager;
import com.junjie.workday.util.ApplicationSecurityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test class for TimeListController
 */
public class TimesheetListControllerTest extends BaseTest {

  @Autowired
  private TimesheetManager timesheetManager;
  @Autowired
  private TimesheetListController timesheetListController;
  @Autowired
  private ApplicationSecurityManager applicationSecurityManager;

  private MockHttpServletRequest mockHttpServletRequest = null;
  private final int EMPLOYEE_ID = 1111;
  private static int timesheetId1 = 0;
  private static int timesheetId2 = 00;

  @Test
  public void testShowForm() throws Exception {
    mockHttpServletRequest = new MockHttpServletRequest("GET",
      "/timesheetlist.htm");

    Employee employee = new Employee();
    employee.setEmployeeId(EMPLOYEE_ID);
    applicationSecurityManager.setEmployee(mockHttpServletRequest,
      employee);

    List timesheets = timesheetListController.populateTimesheets(mockHttpServletRequest);
    ModelAndView modelAndView = new ModelAndView(timesheetListController.setupForm(
      mockHttpServletRequest, null));

    assertNotNull(modelAndView);
    assertNotNull(modelAndView.getModel());
    assertNotNull(timesheets);

    Timesheet timesheet;
    for (int i = 0; i < timesheets.size(); i++) {
      timesheet = (Timesheet) timesheets.get(i);
      assertEquals(EMPLOYEE_ID, timesheet.getEmployeeId());
      System.out.println(timesheet.getTimesheetId() + " passed!");
    }
  }

  @Before
  public void setUp() throws Exception {
    Timesheet timesheet = null;

    timesheet = new Timesheet();
    timesheet.setEmployeeId(EMPLOYEE_ID);
    timesheet.setPeriodEndingDate(new GregorianCalendar(2006,
      Calendar.MARCH, 04).getTime());
    timesheet.setStatusCode("P");
    timesheet.setDepartmentCode("IT");
    timesheet.setMinutesMon(480);
    timesheet.setMinutesTue(480);
    timesheet.setMinutesWed(480);
    timesheet.setMinutesThu(480);
    timesheet.setMinutesFri(480);
    timesheet.setMinutesSat(0);
    timesheet.setMinutesSun(0);
    timesheetManager.saveTimesheet(timesheet);
    timesheetId1 = timesheet.getTimesheetId();

    timesheet = new Timesheet();
    timesheet.setEmployeeId(EMPLOYEE_ID);
    timesheet.setPeriodEndingDate(new GregorianCalendar(2006,
      Calendar.MARCH, 11).getTime());
    timesheet.setStatusCode("A");
    timesheet.setDepartmentCode("IT");
    timesheet.setMinutesMon(480);
    timesheet.setMinutesTue(480);
    timesheet.setMinutesWed(480);
    timesheet.setMinutesThu(480);
    timesheet.setMinutesFri(480);
    timesheet.setMinutesSat(0);
    timesheet.setMinutesSun(0);
    timesheetManager.saveTimesheet(timesheet);
    timesheetId2 = timesheet.getTimesheetId();
  }

  @After
  public void tearDown() throws Exception {
    timesheetManager.deleteTimesheet(timesheetId1);
    timesheetManager.deleteTimesheet(timesheetId2);
  }
}
