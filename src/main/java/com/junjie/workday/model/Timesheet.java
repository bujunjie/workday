package com.junjie.workday.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * @author jbu
 */
@Entity
public class Timesheet {
  int timesheetId;
  int employeeId;
  String statusCode;
  String departmentCode;
  Department department;
  Date periodEndingDate;
  int minutesMon;
  int minutesTue;
  int minutesWed;
  int minutesThu;
  int minutesFri;
  int minutesSat;
  int minutesSun;

  public Timesheet() {
  }

  public Timesheet(int timesheetId, int employeeId, String statusCode, String departmentCode, Date periodEndingDate,
                   int minutesMon, int minutesTue, int minutesWed, int minutesThu, int minutesFri, int minutesSat, int minutesSun) {
    this.timesheetId = timesheetId;
    this.employeeId = employeeId;
    this.statusCode = statusCode;
    this.departmentCode = departmentCode;
    this.periodEndingDate = periodEndingDate;
    this.minutesMon = minutesMon;
    this.minutesTue = minutesTue;
    this.minutesWed = minutesWed;
    this.minutesThu = minutesThu;
    this.minutesFri = minutesFri;
    this.minutesSat = minutesSat;
    this.minutesSun = minutesSun;
  }

  @Id
  public int getTimesheetId() {
    return timesheetId;
  }

  public void setTimesheetId(int timesheetId) {
    this.timesheetId = timesheetId;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  public String getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }

  public String getDepartmentCode() {
    return departmentCode;
  }

  public void setDepartmentCode(String departmentCode) {
    this.departmentCode = departmentCode;
  }

  public Date getPeriodEndingDate() {
    return periodEndingDate;
  }

  public void setPeriodEndingDate(Date periodEndingDate) {
    this.periodEndingDate = periodEndingDate;
  }

  public int getMinutesMon() {
    return minutesMon;
  }

  public void setMinutesMon(int minutesMon) {
    this.minutesMon = minutesMon;
  }

  public int getMinutesTue() {
    return minutesTue;
  }

  public void setMinutesTue(int minutesTue) {
    this.minutesTue = minutesTue;
  }

  public int getMinutesWed() {
    return minutesWed;
  }

  public void setMinutesWed(int minutesWed) {
    this.minutesWed = minutesWed;
  }

  public int getMinutesThu() {
    return minutesThu;
  }

  public void setMinutesThu(int minutesThu) {
    this.minutesThu = minutesThu;
  }

  public int getMinutesFri() {
    return minutesFri;
  }

  public void setMinutesFri(int minutesFri) {
    this.minutesFri = minutesFri;
  }

  public int getMinutesSat() {
    return minutesSat;
  }

  public void setMinutesSat(int minutesSat) {
    this.minutesSat = minutesSat;
  }

  public int getMinutesSun() {
    return minutesSun;
  }

  public void setMinutesSun(int minutesSun) {
    this.minutesSun = minutesSun;
  }

  @ManyToOne(cascade = CascadeType.MERGE)
  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public int getTotalMinutes() {
    return (minutesMon + minutesTue + minutesWed + minutesThu
      + minutesFri + minutesSat + minutesSun);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Timesheet timesheet = (Timesheet) o;

    if (employeeId != timesheet.employeeId) {
      return false;
    }
    if (minutesFri != timesheet.minutesFri) {
      return false;
    }
    if (minutesMon != timesheet.minutesMon) {
      return false;
    }
    if (minutesSat != timesheet.minutesSat) {
      return false;
    }
    if (minutesSun != timesheet.minutesSun) {
      return false;
    }
    if (minutesThu != timesheet.minutesThu) {
      return false;
    }
    if (minutesTue != timesheet.minutesTue) {
      return false;
    }
    if (minutesWed != timesheet.minutesWed) {
      return false;
    }
    if (timesheetId != timesheet.timesheetId) {
      return false;
    }
    if (department != null ? !department.equals(timesheet.department) : timesheet.department != null) {
      return false;
    }
    if (departmentCode != null ? !departmentCode.equals(timesheet.departmentCode) : timesheet.departmentCode != null) {
      return false;
    }
    if (periodEndingDate != null ? !isSameDay(periodEndingDate, periodEndingDate) : timesheet.periodEndingDate != null) {
      return false;
    }
    if (statusCode != null ? !statusCode.equals(timesheet.statusCode) : timesheet.statusCode != null) {
      return false;
    }

    return true;
  }

  private boolean isSameDay(Date d1, Date d2) {
    return (d1.getYear() == d2.getYear() && d1.getMonth() == d2.getMonth() && d1.getDay() == d2.getDay());
  }

  @Override
  public int hashCode() {
    int result = timesheetId;
    result = 31 * result + employeeId;
    result = 31 * result + (statusCode != null ? statusCode.hashCode() : 0);
    result = 31 * result + (departmentCode != null ? departmentCode.hashCode() : 0);
    result = 31 * result + (department != null ? department.hashCode() : 0);
    result = 31 * result + (periodEndingDate != null ? periodEndingDate.hashCode() : 0);
    result = 31 * result + minutesMon;
    result = 31 * result + minutesTue;
    result = 31 * result + minutesWed;
    result = 31 * result + minutesThu;
    result = 31 * result + minutesFri;
    result = 31 * result + minutesSat;
    result = 31 * result + minutesSun;
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("Timesheet");
    sb.append("{timesheetId=").append(timesheetId);
    sb.append(", employeeId=").append(employeeId);
    sb.append(", statusCode='").append(statusCode).append('\'');
    sb.append(", departmentCode=").append(departmentCode);
    sb.append(", periodEndingDate=").append(periodEndingDate);
    sb.append(", minuteMon=").append(minutesMon);
    sb.append(", minuteTue=").append(minutesTue);
    sb.append(", minuteWed=").append(minutesWed);
    sb.append(", minuteThu=").append(minutesThu);
    sb.append(", minuteFri=").append(minutesFri);
    sb.append(", minuteSat=").append(minutesSat);
    sb.append(", minuteSun=").append(minutesSun);
    sb.append('}');
    return sb.toString();
  }

}
