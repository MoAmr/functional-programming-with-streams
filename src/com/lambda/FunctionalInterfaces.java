package com.lambda;

import java.time.LocalTime;
import java.util.Objects;
import java.util.Random;

public class FunctionalInterfaces {


    public static void main(String[] args) {
        System.out.println(composeHashCodes3("Hello", "world"));
        System.out.println(composeHashCodes3("Hello", null));

        final Random rand = new Random();
        BooleanSupplier randomBs = rand::nextBoolean;
        System.out.println(randomBs);
    }

    public static int composeHashCodes(Object a, Object b) {
        return a.hashCode() ^ b.hashCode();
    }

    // A "Slow" method
    public static String getApplicationStatus() {
        System.out.println("getApplicationStatus");
        return "It's" + LocalTime.now();
    }

    public static int composeHashCodes2(Object a, Object b) {
        Objects.requireNonNull(a, "a may not be null " + getApplicationStatus());
        Objects.requireNonNull(b, "b may not be null " + getApplicationStatus());

        return a.hashCode() ^ b.hashCode();
    }

    public static int composeHashCodes3(Object a, Object b) {
        Objects.requireNonNull(a, () -> "a may not be null " + getApplicationStatus());
        Objects.requireNonNull(b, () -> "b may not be null " + getApplicationStatus());

        return a.hashCode() ^ b.hashCode();
    }
}

     interface BooleanSupplier {

        boolean getAsBoolean();
    }

