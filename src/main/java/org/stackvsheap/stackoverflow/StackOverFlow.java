package org.stackvsheap.stackoverflow;

public class StackOverFlow {

  public static void main(String[] args) {
    System.out.println("Main method started");
    StackOverFlow factorial = new StackOverFlow();
    try {
      System.out.println("Factorial result: " + factorial.calculateFactorial(1000000));
    } catch (StackOverflowError e) {
      System.err.println("StackOverflowError occurred!");
    } catch (Exception e){
      e.printStackTrace();
    }
    System.out.println("Main method ended");
  }

  public long calculateFactorial(int n) {
    System.out.println("Calculating factorial of " + n);
    if (n == 1) {
      return 1;
    } else {
      return n * calculateFactorial(n - 1);
    }
  }
}
