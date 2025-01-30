package org.parameterpassing;

public class PassByRef {

  public static void main(String[] args) {
    Person person = new Person("Alice");
    System.out.println("Before method call: person.name = " + person.name);

    modifyObject(person);
    System.out.println("After modifyObject call: person.name = " + person.name);

    reassignObject(person);
    System.out.println("After reassignObject call: person.name = " + person.name);
  }

  // Method that modifies the object's state
  public static void modifyObject(Person p) {
    p.name = "Bob";
    System.out.println("Inside modifyObject: p.name = " + p.name);
  }

  // Method that attempts to reassign the object reference
  public static void reassignObject(Person p) {
    p = new Person("Charlie");
    System.out.println("Inside reassignObject: p.name = " + p.name);
  }
}

class Person {

  String name;

  Person(String name) {
    this.name = name;
  }
}