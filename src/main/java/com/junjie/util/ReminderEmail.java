package com.junjie.util;

import com.junjie.model.Employee;
import com.junjie.model.EmployeeManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;
import java.util.List;

/**
 * @author jbu
 */
public class ReminderEmail {
  private static final Logger logger = Logger.getLogger(ReminderEmail.class);

  @Autowired
  private EmployeeManager employeeManager;

  @Autowired
  private MailSender mailSender;

  @Autowired
  private SimpleMailMessage message;

//  @Scheduled(cron="*/10 * * * * ?")
  public void sendMail() {
    List<Employee> list = employeeManager.getHourlyEmployees();
    if (list == null || list.size() < 1) {
      return;
    }
    String emailAddresses[] = new String[list.size()];
    int i=0;
    for (Employee employee: list)
    {
      emailAddresses[i++] = employee.getEmail();
    }

//    System.out.println("sendMail reminder: "+ Arrays.toString(emailAddresses));
    emailAddresses = new String[]{"bujunjie@gmail.com"};
    logger.info("Sending reminder email to bujunjie@gmail.com");

    message.setTo(emailAddresses);
    SimpleMailMessage threadSafeMailMessage =
      new SimpleMailMessage(message);
    mailSender.send(threadSafeMailMessage);
  }
}