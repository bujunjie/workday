package com.junjie.model;

/**
 * @author jbu
 */
public class Employee {
  int employeeId;
  String name;
  String employeeCode;
  String password;
  String email;
  int managerEmployeeId;

  public Employee() {
  }

  public Employee(int employeeId, String name, String employeeCode, String password, String email, int managerEmployeeId) {
    this.employeeId = employeeId;
    this.name = name;
    this.employeeCode = employeeCode;
    this.password = password;
    this.email = email;
    this.managerEmployeeId = managerEmployeeId;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmployeeCode() {
    return employeeCode;
  }

  public void setEmployeeCode(String employeeCode) {
    this.employeeCode = employeeCode;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getManagerEmployeeId() {
    return managerEmployeeId;
  }

  public void setManagerEmployeeId(int managerEmployeeId) {
    this.managerEmployeeId = managerEmployeeId;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Employee");
    sb.append("{employeeId=").append(employeeId);
    sb.append(", name='").append(name).append('\'');
    sb.append(", employeeCode='").append(employeeCode).append('\'');
    sb.append(", password='").append(password).append('\'');
    sb.append(", email='").append(email).append('\'');
    sb.append(", managerEmployeeId=").append(managerEmployeeId);
    sb.append('}');
    return sb.toString();
  }
}
