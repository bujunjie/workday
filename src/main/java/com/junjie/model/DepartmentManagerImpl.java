package com.junjie.model;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jbu
 */
@Repository("departmentManager")
public class DepartmentManagerImpl implements DepartmentManager {
  private final static Logger logger = Logger.getLogger(DepartmentManagerImpl.class);

  HibernateTemplate hibernateTemplate;
  SessionFactory sessionFactory;

  @Autowired
  public DepartmentManagerImpl(SessionFactory sessionFactory) {
    this.sessionFactory= sessionFactory;
    hibernateTemplate = new HibernateTemplate(sessionFactory);
  }

  @Override
  public void save(Department department) {
    hibernateTemplate.save(department);
  }

  @Override
  public void saveAll(List<Department> list) {
    Session session = sessionFactory.openSession();
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
    hibernateTemplate.update(department);
  }

  @Override
  public void delete(Department department) {
    hibernateTemplate.delete(department);
  }

  @Override
  public void deleteAll() {
    hibernateTemplate.deleteAll(getDepartments());
  }

  @Override
  public Department getDepartmentById(String departmentCode) {
    List list = hibernateTemplate.find("from Department where departmentCode=?", departmentCode);
    return (Department) list.get(0);
  }

  @Override
  public List<Department> getDepartments() {
    Session session = sessionFactory.openSession();
    try {
      return session.createQuery("from Department ORDER BY name").list();
    } catch (HibernateException e) {
      throw e;
    } finally {
      session.close();
    }
  }
}
