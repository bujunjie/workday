package com.junjie.workday.model;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author jbu
 */
@Repository("timesheetManager")
public class TimesheetManagerImpl implements TimesheetManager {
  @Autowired
  SessionFactory sessionFactory;

  @Override
  public List getTimesheets() {
    List<Timesheet> result = null;
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      result = session.createQuery("from Timesheet order by employeeId").list();
      tx.commit();
      return result;
    } catch (HibernateException e) {
      tx.rollback();
      throw e;
    } finally {
      session.close();
    }
  }


  @Override
  public List getTimesheets(int employeeId) {
    List<Timesheet> result = null;
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      result = session.createQuery("from Timesheet WHERE employeeId=?").setInteger(0, employeeId).list();
      tx.commit();
      return result;
    } catch (HibernateException e) {
      tx.rollback();
      throw e;
    } finally {
      session.close();
    }
  }

  @Override
  public Timesheet getTimesheet(int employeeId, Date periodEndingDate) {
    Timesheet result = null;
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      result = (Timesheet) session.createQuery("from Timesheet WHERE employeeId=? and periodEndingDate=?")
        .setInteger(0, employeeId)
        .setDate(1, periodEndingDate)
        .uniqueResult();
      tx.commit();
      return result;
    } catch (HibernateException e) {
      tx.rollback();
      throw e;
    } finally {
      session.close();
    }
  }

  @Override
  public void saveTimesheet(Timesheet timesheet) {
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      session.saveOrUpdate(timesheet);
      tx.commit();
    } catch (HibernateException e) {
      tx.rollback();
      throw e;
    } finally {
      session.close();
    }
  }

  @Override
  public void deleteTimesheet(int timesheetId) {
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      session.delete(session.load(Timesheet.class, timesheetId));
      session.flush();
      tx.commit();
    } catch (HibernateException e) {
      tx.rollback();
      throw e;
    } finally {
      session.close();
    }
  }

    @Override
    public Timesheet getTimesheet(int timesheetId, boolean doLock) {
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
      Timesheet timesheet  = null;
    try {
      if (doLock) {
        timesheet = (Timesheet) session.get(Timesheet.class, timesheetId, LockMode.UPGRADE);
      } else {
        timesheet = (Timesheet) session.get(Timesheet.class, timesheetId);
      }
      tx.commit();
      return timesheet;
    } catch (HibernateException e) {
      tx.rollback();
      throw e;
    } finally {
      session.close();
    }
  }

}
