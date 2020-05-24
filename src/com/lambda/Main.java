package com.lambda;

import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        Employee Mohamed = new Employee("Mohamed", 2000),
                Ali = new Employee("Ali", 3000);

        Comparator<Employee> byName = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        System.out.println("By name:");
        System.out.println(byName.compare(Mohamed, Ali));

        try {
            // Throws NullPointerException
            System.out.println(byName.compare(Mohamed, null));
        } catch(NullPointerException e) {
            System.out.println(e);
        }

        // A static method in Comparator
        Comparator<Employee> byNameThenNull = Comparator.nullsLast(byName);

        System.out.println("Then null:");
        System.out.println(byNameThenNull.compare(Mohamed, Ali));
        System.out.println(byNameThenNull.compare(Mohamed, null));

        // A default method in Comparator
        Comparator<Employee> nullThenByDecreasingName = byNameThenNull.reversed();

        System.out.println("Reversed:");
        System.out.println(nullThenByDecreasingName.compare(Mohamed, Ali));
        System.out.println(nullThenByDecreasingName.compare(Mohamed, null));
    }
}
