package com.lambda;

import java.util.Comparator;
import java.util.function.Function;

public class ComposingFunctions {

    public static void main(String[] args) {

        Function<Employee, String> getName = Employee::getName;

        Function<String, Character> getFirstLetter = name -> name.charAt(0);

        Function<Employee, Character> initial = getName.andThen(getFirstLetter);

        Comparator<Employee> byName = Comparator.comparing(Employee::getName);

        Comparator<Employee> bySalary = Comparator.comparingInt(Employee::getSalary);

        Comparator<Employee> byNameAndSalary = byName.thenComparing(bySalary);
    }
}
