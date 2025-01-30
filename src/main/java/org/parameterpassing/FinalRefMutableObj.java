package org.parameterpassing;

/*
Key Takeaway:
    Final References prevent reassignment but do not make the underlying objects immutable. If the object is mutable, its internal state can still be modified.
*/

public class FinalRefMutableObj {

  public static void main(String[] args) {
    final Mutable obj = new Mutable(10);
    System.out.println("Initial value: " + obj.value); // Outputs 10

    // Modifying the object's state is allowed
    obj.setValue(20);
    System.out.println("After modification: " + obj.value); // Outputs 20

    // Reassigning the final reference is NOT allowed
//     obj = new Mutable(30); // Uncommenting this line will cause a compile-time error
  }

}

class Mutable {

  int value;

  Mutable(int value) {
    this.value = value;
  }

  void setValue(int value) {
    this.value = value;
  }
}