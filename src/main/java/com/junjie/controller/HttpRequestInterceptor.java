package com.junjie.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.junjie.model.Employee;
import com.junjie.util.ApplicationSecurityManager;

import java.util.List;

/**
 * Intercepts HTTP requests to ensure user is signed in; it also closes
 * the Hibernate session for the current thread.
 *
 * @author anil
 */
public class HttpRequestInterceptor extends HandlerInterceptorAdapter {

  @Autowired
  private ApplicationSecurityManager applicationSecurityManager;
  List<String> allowedUrl;

  /**
   * Uses ApplicationSecurityManager to ensure user is logged in; if not,
   * then user is forwarded to the sign-in page.
   *
   * @see ApplicationSecurityManager
   */
  public boolean preHandle(
    HttpServletRequest request,
    HttpServletResponse response,
    Object handler) throws Exception {

    if (request.getRequestURI().equalsIgnoreCase("/signin.htm")) return true;
    if (allowedUrl.contains(request.getRequestURI())) return true;

    Employee employee = (Employee) applicationSecurityManager
      .getEmployee(request);
    if (employee == null) {
      response.sendRedirect("signin.htm");
      return false;
    }

    return true;
  }

  public List<String> getAllowedUrl() {
    return allowedUrl;
  }

  public void setAllowedUrl(List<String> allowedUrl) {
    this.allowedUrl = allowedUrl;
    System.out.println("Allowed URLS="+allowedUrl);
  }
}
