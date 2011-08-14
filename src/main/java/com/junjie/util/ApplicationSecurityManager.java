package com.junjie.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Manages setting, getting and removal of Employee object
 * in a HTTP session.
 * @author anil
 */
public class ApplicationSecurityManager
{
    public static final String USER = "user";

    public Object getEmployee(HttpServletRequest request)
    {
        return request.getSession(true).getAttribute(USER);
    }

    public void setEmployee(HttpServletRequest request, Object employee)
    {
        request.getSession(true).setAttribute(USER, employee);
    }

    public void removeEmployee(HttpServletRequest request)
    {
        request.getSession(true).removeAttribute(USER);
    }
}
