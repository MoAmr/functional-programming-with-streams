package com.lambda;

import java.util.*;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streams {

    public static void main(String[] args) {

        /** Print the names of the employees with salary at least 2500$
         alphabetically sorted */
        Employee[] emps = new Employee[11];
        emps[0] = new Employee("Alec", 1500);
        emps[1] = new Employee("Bob", 1900);
        emps[2] = new Employee("Claire", 1850);
        emps[3] = new Employee("Danielle", 1750);
        emps[4] = new Employee("Ethan", 1900);
        emps[5] = new Employee("John", 2300);
        emps[6] = new Employee("Kim", 2500);
        emps[7] = new Employee("Sara", 1750);
        emps[8] = new Employee("Collin", 4000);
        emps[9] = new Employee("Mike", 3000);
        emps[10] = new Employee("Derick", 2000);

        Arrays.stream(emps)
                .filter(e -> e.getSalary() >= 2500)
                .map(Employee::getName)
                .sorted()
                .forEach(System.out::println);

        // Static sequence of Objects
        Stream<Integer> fib = Stream.of(1, 1, 2, 3);
        fib.forEach(System.out::println);
        Stream<String> italianNumbers = Stream.of("uno", "due", "tre");
        italianNumbers.forEach(System.out::println);
        Stream<Employee> empStream = Stream.of(emps);
        // Turn an array into a stream
        Stream<Employee> empStream1 = Arrays.stream(emps);

        /** Select 10 random positive distinct integres */
        final Random rand = new Random();
        Stream<Integer> randoms = Stream.generate(rand::nextInt);

        randoms.filter(n -> n > 0)
                .distinct()
                .limit(10)
                .forEach(System.out::println);

        /** You have a sequence of employees, sorted by increasing salary,
         * you want the employees with salary at most 2000
         */

        Stream<Employee> sortedEmps = Stream.of(emps);

        sortedEmps.takeWhile(e -> e.getSalary() <= 2000)
                .forEach(System.out::println);

        // Stops as soon as salary is above 2000: Much more efficient

        // Print Names of the 10 employees with highest salary
        empStream.sorted(
                Comparator.comparingInt(Employee::getSalary)
                        .reversed()
        ).limit(10)
                .map(Employee::getName)
                .forEach(System.out::println);

        // Check whether all employees have a valid name
        List<Employee> employeeList = new ArrayList<>();

        boolean allValidNames = employeeList
                .stream()
                .allMatch(e -> e.getName() != null && e.getName().length() > 0);

        // Collect employees having salaries lower than 2000 $
        Stream<Employee> empStream2 = Stream.of(emps);

//        Object[] lowSalaryEmps = empStream2
//                .filter(e -> e.getSalary() < 2000)
//                .toArray();

        Employee[] lowEmpsArr = empStream2
                .filter(e -> e.getSalary() < 2000)
                .toArray(Employee[]::new);

        // Find the longest string in a collection
        String[] element = {"hello, mohammed, ali, sara, Eid Mubarak"};
        List<String> stringList = Arrays.asList(element);

        Optional<String> longest = stringList
                .stream()
                .max(Comparator.comparingInt(String::length));
        System.out.println(longest);

        StringBuilder summary = new StringBuilder();
        for (String s : stringList) {
            summary.append(s);
        }
        System.out.println(summary.toString());

        // To apply the previous concatenation to string builder using streams
        StringBuilder builder = stringList.stream().collect(

                () -> new StringBuilder(),

                (StringBuilder builder1, String s) -> builder1.append(s),
                (StringBuilder builder2, StringBuilder builder3) -> builder2.append(builder3));

        /** Stream of employees into a TreeSet of employees and sort this
         * TreeSet by the employee's salaries */
        Stream<Employee> employeeStream = Stream.of(emps);

        TreeSet<Employee> tree = employeeStream
                .collect(Collectors.toCollection(
                        () -> new TreeSet<Employee>(
                                Comparator.comparingInt(Employee::getSalary)
                        )
                ));

        /** Building a map from employee's names to their salaries,
         * starting from stream of employees. In other words,
         * we want to build a map from string to integer. We invoke collect
         * with the standard toMap collector. With key mapper getName and value
         * mapper getSalary */
        Stream<Employee> employeeStream1 = Stream.of(emps);

        Map<String, Integer> salaries = employeeStream1
                .collect(
                        Collectors.toMap(
                                Employee::getName, Employee::getSalary)
                );

        // Compute the average salary of a stream of employees
        Stream<Employee> employeeStream2 = Stream.of(emps);

        OptionalDouble avgSalary = employeeStream2
                .mapToInt(Employee::getSalary).average();

        // Compute total salary of employees
        List<Employee> employees = Arrays.asList(emps);
        int totalSalary = employees.stream()
                .parallel()
                .mapToInt(Employee::getSalary)
                .sum();

        SalaryAdder adder = new SalaryAdder();
        List<Employee> parallelEmployeesList = Arrays.asList(emps);

        parallelEmployeesList.stream()
                .parallel()
                .forEach(adder::accept);

        long totalSal = adder.total.longValue();

    }

    static class SalaryAdder {

        LongAdder total;

        public void accept(Employee e) {
            total.add(e.getSalary());
        }
    }
}
