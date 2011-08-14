package com.junjie.util;

/**
 * @author jbu
 */
public class SpringTestMessage {
  String message;

  public void setMessage(String message) {
    this.message = message;
  }

  public void printMessage() {
    System.out.println("Welcome Junjie, the message for you is "+message);
  }
}
