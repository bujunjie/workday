package com.junjie.workday.model;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jbu
 */
@Repository("employeeManager")
public class EmployeeManagerImpl implements EmployeeManager {
  SessionFactory sessionFactory;
  HibernateTemplate hibernateTemplate;

  @Autowired
  public EmployeeManagerImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
    this.hibernateTemplate = new HibernateTemplate(sessionFactory);
  }

  @Override
  public List getHourlyEmployees() {
    List<Employee> result = null;
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      result = session.createQuery("from Employee WHERE employeeCode='H' ORDER BY name").list();
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
  public List getEmployees() {
    List<Employee> result = null;
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      result = session.createQuery("from Employee ORDER BY name").list();
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
  public Employee getEmployeeById(int employeeId) {
    Employee result = null;
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      result = (Employee) session.createQuery("from Employee WHERE employeeId=?").setInteger(0, employeeId).uniqueResult();
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
  public void addAll(List<Employee> list) {
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      int i = 0;
      for (Employee e : list) {
        session.saveOrUpdate(e);
        i++;
        if (i % 100 == 0) {
          session.flush();
          session.clear();
          System.out.print("#");
        }
      }
      tx.commit();
    } catch (HibernateException e) {
      tx.rollback();
      throw e;
    } finally {
      session.close();
    }
  }

  @Override
  public int add(Employee e) {
    hibernateTemplate.saveOrUpdate(e);
    return e.employeeId;
  }

  @Override
  public void delete(int employeeId) {
    hibernateTemplate.delete(hibernateTemplate.load(Employee.class, employeeId));
  }

}
