package com.lambda;

import java.util.Comparator;
import java.util.function.Consumer;

public class lambda1 {

    public static void main(String[] args) {

        Comparator<Employee> byName = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };


        // Our first lambda expression
        Comparator<Employee> byNameLambda1 =
                (Employee a, Employee b) -> {
                    return a.getName().compareTo(b.getName());
                };


        // Removing parameter types
        Comparator<Employee> byNameLambda2 =
                (a, b) -> {
                    return a.getName().compareTo(b.getName());
                };

        // Removing braces and 'return'
        Comparator<Employee> byNameLambda3 =
                (a, b) -> a.getName().compareTo(b.getName());

        // You cannot remove braces and leave 'return'
//        Comparator<Employee> byNameLambda4 =
//                (a, b) -> return a.getName().compareTo(b.getName());

        // Expression with no parameters
        Runnable r = () -> {
            System.out.println("A compact Runnable!");
        };
        Thread t1 = new Thread(r);

        // No need to even mention Runnable
        Thread t2 = new Thread(() -> {
            System.out.println("An implicit Runnable!");
        });

        // No need for braces here
        Thread t3 = new Thread( () -> System.out.println("An implicit Runnable!"));

        // Expression with parameter
        Consumer<String> lenghtPrinter =
                s -> System.out.println(s.length());
    }
}

