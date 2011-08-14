package com.junjie.model;

import java.util.List;

/**
 * @author jbu
 */
public interface DepartmentManager {
  void save(Department department);

  void saveAll(List<Department> list);

  void update(Department department);

  void delete(Department department);

  void deleteAll();

  Department getDepartmentById(String departmentCode);

  List<Department> getDepartments();
}
