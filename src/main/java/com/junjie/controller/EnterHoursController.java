package com.junjie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.junjie.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import com.junjie.model.DepartmentManager;
import com.junjie.model.Employee;
import com.junjie.model.Timesheet;
import com.junjie.model.TimesheetManager;
import com.junjie.util.ApplicationSecurityManager;
import com.junjie.util.DateUtil;

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
