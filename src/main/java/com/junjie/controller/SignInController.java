package com.junjie.controller;

import com.junjie.model.Employee;
import com.junjie.model.EmployeeManager;
import com.junjie.util.ApplicationSecurityManager;
import com.junjie.util.WorkdayJmxBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Controller for the Sign In screen.
 *
 * @author anil
 */
@Controller
@RequestMapping("/signin.htm")
public class SignInController {
  @Autowired
  private EmployeeManager employeeManager;
  @Autowired
  private ApplicationSecurityManager applicationSecurityManager;
  @Autowired
  private WorkdayJmxBean workdayJmxBean;

  @ModelAttribute("employee")
  public Employee populateEmployee() {
    return new Employee();
  }

  @RequestMapping(method = RequestMethod.GET)
  public String setupForm(HttpServletRequest request) {
    if (applicationSecurityManager.getEmployee(request) != null) {
      return "redirect:timesheetlist.htm";
    } else {
      return "signin";
    }
  }

  @RequestMapping(method = RequestMethod.POST)
  public String processSubmit(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("employee") Employee employee, BindingResult result, SessionStatus status) {
    new SignInValidator().validate(employee, result);
    if (result.hasErrors()) {
      return "signin";
    }

    Employee dbEmployee = employeeManager.getEmployeeById(employee.getEmployeeId());
    if (dbEmployee == null || !dbEmployee.getPassword().equalsIgnoreCase(employee.getPassword())) {
      ObjectError objectError = new ObjectError("employee", "Invalid user name or password");
      result.addError(objectError);
      return "signin";
    }
    workdayJmxBean.setSignInCount(workdayJmxBean.getSignInCount() + 1);
    applicationSecurityManager.setEmployee(request, dbEmployee);
    return "redirect:timesheetlist.htm";
  }
}
