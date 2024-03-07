package com.aiman.examples;

import java.util.Objects;
import java.util.Scanner;

public class SimpleCalculator {
    public int max(int a, int b, int c) {
        if (a > b && a > c) {
            return a;
        } else if (b > c && b > a) {
            return b;
        } else {
            return c;
        }
    }

    public static void main(String[] args) {
        System.out.println(calculator(2,3, "+"));
    }

    public static float calculator(float a, float b, String op) {
        float result = 0;
        switch (op) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "/":
                result = a / b;
                break;
            case "*":
                result = a * b;
                break;
        }
        return result;
    }
}
