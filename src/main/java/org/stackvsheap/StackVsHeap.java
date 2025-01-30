package org.stackvsheap;

public class StackVsHeap {

  public static void main(String[] args) {
    System.out.println("Main method started");
    StackVsHeap demo = new StackVsHeap();
    demo.methodA();
    System.out.println("Main method ended");
  }

  public void methodA() {
    System.out.println("Method A started");
    int localVariable = 10; // Stored in stack
    MyObject obj = new MyObject(); // Reference stored in stack, object stored in heap
    obj.setValue(20);
    methodB(obj);
    System.out.println("Method A ended");
  }

  public void methodB(MyObject obj) {
    System.out.println("Method B started");
    System.out.println("Object value: " + obj.getValue());
    System.out.println("Method B ended");
  }
}

class MyObject {

  private int value; // Stored in heap

  public void setValue(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}