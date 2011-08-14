package com.junjie;

import com.junjie.workday.model.Employee;
import com.junjie.workday.model.EmployeeManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jbu
 */
public class EmployeeManagerTest extends BaseTest {
  @Autowired
  private final EmployeeManager employeeManager = null;

  @Test
  public void testAddEmployees() {
    List<Employee> list = new ArrayList<Employee>();
    for (int i = 0; i < 10; i++) {
      Employee e = new Employee(0, "EMP" + i, "H", "1234567890", "emp" + i + "@gmail.com", 0);
      list.add(e);
    }
    employeeManager.addAll(list);
    System.out.println(employeeManager.getHourlyEmployees());
  }

}
