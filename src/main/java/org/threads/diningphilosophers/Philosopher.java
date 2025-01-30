package org.threads.diningphilosophers;

public class Philosopher implements Runnable {

  private final Fork leftFork;
  private final Fork rightFork;

  public Philosopher(Fork leftFork, Fork rightFork) {
    this.leftFork = leftFork;
    this.rightFork = rightFork;
  }

  private void doAction(String action) throws InterruptedException {
    System.out.println(Thread.currentThread().getName() + " " + action);
    Thread.sleep((int) (Math.random() * 100));
  }

  @Override
  public void run() {
    try {
      while (true) {
        doAction(": Thinking");
        leftFork.pickUp();
        doAction(": Picked up left fork");
        rightFork.pickUp();
        doAction(": Picked up right fork - eating");
        doAction(": Put down right fork");
        rightFork.putDown();
        doAction(": Put down left fork");
        leftFork.putDown();
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}