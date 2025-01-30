package org.threads.executor;

// MyTask.java
public class MyTask implements Runnable {

  private String taskName;

  public MyTask(String name) {
    this.taskName = name;
  }

  @Override
  public void run() {
    System.out.println(taskName + " started.");
    try {
      Thread.sleep(1000); // 1 saniye bekle
    } catch (InterruptedException e) {
      System.out.println(taskName + " interrupted.");
    }
    System.out.println(taskName + " finished.");
  }
}
