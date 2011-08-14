package com.junjie.util;

import org.apache.log4j.Logger;

/**
 * @author jbu
 */
public class WorkdayJmxBean {
  private static int signInCount;
  private static int timesheetsFetched;
  private static final Logger logger = Logger.getLogger(WorkdayJmxBean.class);

  public int getSignInCount() {
    return signInCount;
  }

  public void setSignInCount(int signInCount) {
    WorkdayJmxBean.signInCount = signInCount;
    logger.info("Current signinCount="+signInCount);
  }

  public int getTimesheetsFetched() {
    return timesheetsFetched;
  }

  public void setTimesheetsFetched(int timesheetsFetched) {
    WorkdayJmxBean.timesheetsFetched = timesheetsFetched;
  }
}
