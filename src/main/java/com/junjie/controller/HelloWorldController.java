package com.junjie.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jbu
 */
@Controller
@RequestMapping("/hello.htm")
public class HelloWorldController  {
  protected final Log logger = LogFactory.getLog(getClass());

  @RequestMapping(method= RequestMethod.GET)
  public String setupForm() {
    logger.info("Return View");
    return "helloworld";
  }
}