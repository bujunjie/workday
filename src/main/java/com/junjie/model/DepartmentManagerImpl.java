package com.junjie.model;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * @author jbu
 */
public class DepartmentManagerImpl extends HibernateDaoSupport implements DepartmentManager {
  private final static Logger logger = Logger.getLogger(DepartmentManagerImpl.class);

  @Override
  public void save(Department department) {
    getHibernateTemplate().save(department);
  }

  @Override
  public void saveAll(List<Department> list) {
    Session session = getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    int i = 0;
    try {
    for (Department d : list) {
      session.save(d);
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
  public void update(Department department) {
    getHibernateTemplate().update(department);
  }

  @Override
  public void delete(Department department) {
    getHibernateTemplate().delete(department);
  }

  @Override
  public void deleteAll() {
    getHibernateTemplate().deleteAll(getDepartments());
  }

  @Override
  public Department getDepartmentById(String departmentCode) {
    List list = getHibernateTemplate().find("from Department where departmentCode=?", departmentCode);
    return (Department) list.get(0);
  }

  @Override
  public List<Department> getDepartments() {
    Session session = getSessionFactory().openSession();
    try {
      return session.createQuery("from Department ORDER BY name").list();
    } catch (HibernateException e) {
      throw e;
    } finally {
      session.close();
    }
  }
}
