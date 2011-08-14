package com.junjie.workday.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author jbu
 */
@Entity
public class Department {
  String departmentCode;
  String name;

  public Department() {
  }

  public Department(String departmentCode, String name) {
    this.departmentCode = departmentCode;
    this.name = name;
  }

  @Id
  public String getDepartmentCode() {
    return departmentCode;
  }

  public void setDepartmentCode(String departmentCode) {
    this.departmentCode = departmentCode;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Department");
    sb.append("{departmentCode='").append(departmentCode).append('\'');
    sb.append(", name='").append(name).append('\'');
    sb.append('}');
    return sb.toString();
  }
}