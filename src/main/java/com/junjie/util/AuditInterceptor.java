package com.junjie.util;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;

/**
 * @author jbu
 */
public class AuditInterceptor extends EmptyInterceptor {

  @Override
  public void afterTransactionCompletion(Transaction tx) {
    super.afterTransactionCompletion(tx);    //To change body of overridden methods use File | Settings | File Templates.
  }


}
