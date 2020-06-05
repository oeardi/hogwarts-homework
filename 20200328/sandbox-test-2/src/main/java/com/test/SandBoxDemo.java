package com.test;

/**
 * @author 冷枫红舞
 */
public class SandBoxDemo {

    final void output() {
        System.out.println("hello Hogwarts 45 - sandbox demo");
    }

    final void loop() {
        int loop = 5000;
        for (int i = 0; i < loop; i++) {
            try {
                output();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String... args) {
        new SandBoxDemo().loop();
    }

}
