package com.junjie.controller;

import com.junjie.util.ApplicationSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jbu
 */
@Controller
@RequestMapping("signout.htm")
@SessionAttributes("")
public class SignOutController  {
  @Autowired
  private ApplicationSecurityManager applicationSecurityManager;

  @RequestMapping(method = RequestMethod.GET)
  public String setupForm(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
    applicationSecurityManager.removeEmployee(httpServletRequest);
    return "redirect:signin.htm";
  }

}
