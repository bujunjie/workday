package com.junjie;

import com.junjie.util.SpringTestMessage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jbu
 */
public class SpringTest extends BaseTest {

  @Autowired
  SpringTestMessage springTestMessage;

  @Test
  public void testMessage() {
    System.out.println("OK");
  }

}
