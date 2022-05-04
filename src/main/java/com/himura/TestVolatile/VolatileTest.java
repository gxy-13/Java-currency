package com.himura.TestVolatile;

public class VolatileTest {
    private static int count = 0;
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    count++;
                }
            }).start();
        }
        System.out.println(count);
    }
}
