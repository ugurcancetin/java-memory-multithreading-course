package org.parameterpassing;
/*
Key Takeaway:
Final or Effectively Final: Variables used within anonymous classes
or lambdas must not be modified after their initial assignment, even if they aren't explicitly declared as final.
 */
public class FinalParameterAnonymousClass {

  public static void main(String[] args) {
    String message = "Hello";

    Runnable runnable = () -> {
      // Accessing the outer variable requires it to be final or effectively final
      System.out.println(message);
    };

    runnable.run();

    // Modifying 'message' after its use in the anonymous class
    //  message = "Hi"; // Uncommenting this line will cause a compile-time error
  }
}
