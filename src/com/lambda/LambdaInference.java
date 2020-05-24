package com.lambda;

import java.util.function.Consumer;

public class LambdaInference {

    public static void main(String[] args) {

        // standard syntax
        Consumer<String> c1 = msg -> System.out.println(msg.length());

        // Compile-time error: not enough info
//        Object x1 = msg -> System.out.println(msg.lenght());

        // Compile-time error: not enough info
//        Object x2 = (String msg) -> System.out.println(msg.length());

        // Ok: cast added
        Object x3 = (Consumer<String>) ((String msg) -> System.out.println(msg.length()));
    }
}
