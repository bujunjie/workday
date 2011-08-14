package com.junjie.workday.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.junjie.workday.model.Department;
import com.junjie.workday.model.DepartmentManager;
import com.junjie.workday.model.Employee;
import com.junjie.workday.model.Timesheet;
import com.junjie.workday.util.ApplicationSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import com.junjie.workday.model.TimesheetManager;
import com.junjie.workday.util.DateUtil;

/**
 * Controller class for the "Enter Hours" screen
 *
 * @author anil
 */
@Controller
@RequestMapping("/enterhours.htm")
public class EnterHoursController  {
  private TimesheetManager timesheetManager;
  private DepartmentManager departmentManager;
  private ApplicationSecurityManager applicationSecurityManager;
  @Autowired
  MessageSource messageSource;


  public static final String TID = "tid";

  @Autowired
  public EnterHoursController(TimesheetManager timesheetManager, DepartmentManager departmentManager,
                              ApplicationSecurityManager applicationSecurityManager) {
    this.timesheetManager = timesheetManager;
    this.departmentManager = departmentManager;
    this.applicationSecurityManager = applicationSecurityManager;
  }

  @ModelAttribute("timesheet")
  public Timesheet populateTimesheet(HttpServletRequest request) {
        if (request.getParameter(TID) != null
                && request.getParameter(TID).trim().length() > 0)
            return timesheetManager.getTimesheet(Integer.parseInt(request
                    .getParameter(TID)), false);

        Timesheet timesheet = new Timesheet();
        Employee employee = (Employee) applicationSecurityManager
                .getEmployee(request);
        timesheet.setEmployeeId(employee.getEmployeeId());
        timesheet.setStatusCode("P");
        timesheet.setPeriodEndingDate(DateUtil.getCurrentPeriodEndingDate());
        return timesheet;
  }

  @RequestMapping(method=RequestMethod.GET)
  public String setupForm(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
    return "enterhours";
  }


  @ModelAttribute("departments")
  public List<Department> populateDepartments() {
    return departmentManager.getDepartments();
  }

  @InitBinder
  public void initDataBinder(WebDataBinder binder) {
//    binder.setRequiredFields(new String[] {"number", "name"});
    binder.registerCustomEditor(int.class, new MinutesPropertyEditor());
  }

  @RequestMapping(method = RequestMethod.POST)
  public String processSubmit(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("timesheet") Timesheet timesheet,
                              BindingResult result, SessionStatus status) {
    new EnterHoursValidator().validate(timesheet, result);
    if (result.hasErrors()) {
      return "enterhours";
    }
    timesheetManager.saveTimesheet(timesheet);
    request.getSession().setAttribute(
      "message", "Timesheet saved successfully");
//      getMessageSourceAccessor().getMessage(
//        "message.enterhours.savesuccess"));
    return "redirect:timesheetlist.htm";
  }


}
