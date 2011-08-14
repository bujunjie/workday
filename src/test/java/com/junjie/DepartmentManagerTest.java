package com.junjie;

import com.junjie.model.Department;
import com.junjie.model.DepartmentManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jbu
 */
public class DepartmentManagerTest extends BaseTest {
  @Autowired
  private DepartmentManager departmentManager;

  @Test
  public void testAddDepartments() throws Exception {
    List<Department> departments = new ArrayList<Department>();
    for (int i = 0; i < 10; i++) {
      Department d1 = new Department("D" + i, "Department " + i);
      departments.add(d1);
    }
    departmentManager.saveAll(departments);
//    service.save(d2);
//    service.saveAll(null);
    List<Department> result = departmentManager.getDepartments();
    for (int i = 0; i < 10; i++) {
      System.out.println(result.get(i));
    }
  }
}
