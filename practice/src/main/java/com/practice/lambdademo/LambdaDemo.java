package com.practice.lambdademo;

import java.util.Arrays;

/**
 * 单方法接口被称为FunctionalInterface。
 * 接收FunctionalInterface作为参数的时候，可以把实例化的匿名类改写为Lambda表达式，能大大简化代码。
 * Lambda表达式的参数和返回值均可由编译器自动推断。
 */
public class LambdaDemo {
    public static void main(String[] args) {
        String[] array = new String[] { "Apple", "Orange", "Banana", "Lemon" };
        Arrays.sort(array, (s1, s2) -> {
            return s1.compareTo(s2);
        });
        System.out.println(String.join(", ", array));
    }
}