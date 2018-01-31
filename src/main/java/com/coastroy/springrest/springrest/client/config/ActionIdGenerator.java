package com.coastroy.springrest.springrest.client.config;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class ActionIdGenerator {
  private static final long INIT_ACTION_ID = 1;
  private AtomicLong actionId = new AtomicLong(INIT_ACTION_ID);

  public ActionIdGenerator() {
  }

  public String generate() {
    // reset after reach max value
    actionId.compareAndSet(Integer.MAX_VALUE, INIT_ACTION_ID);
    return String.valueOf(actionId.getAndIncrement()) + "-" + System.currentTimeMillis();
  }
}
