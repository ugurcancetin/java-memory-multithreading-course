package org.parameterpassing;

public class PassByValue {

  public static void main(String[] args) {
    int originalValue = 10;
    System.out.println("Before method call: originalValue = " + originalValue);

    modifyPrimitive(originalValue);

    System.out.println("After method call: originalValue = " + originalValue);
  }

  public static void modifyPrimitive(int value) {
    value = 20;
    System.out.println("Inside modifyPrimitive: value = " + value);
  }
}
