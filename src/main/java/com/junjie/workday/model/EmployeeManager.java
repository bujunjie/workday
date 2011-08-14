package com.junjie.workday.model;

import java.util.List;

/**
 * @author jbu
 */
public interface EmployeeManager {
  List getHourlyEmployees();

  List getEmployees();

  Employee getEmployeeById(int employeeId);

  void addAll(List<Employee> list);

  int add(Employee e);

  void delete(int employeeId);
}
