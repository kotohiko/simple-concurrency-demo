package com.jacob.thread;

import java.util.Random;

/**
 * 通过实现 {@code Runnable} 来创建线程
 */
public class Match2 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runner2());
        thread1.setName("曹操");

        Thread liu = new Thread(new Runner2());
        liu.setName("刘备");

        Thread sun = new Thread(new Runner2());
        sun.setName("孙权");

        thread1.start();
        liu.start();
        sun.start();
    }
}

class Runner2 implements Runnable {
    @Override
    public void run() {
        int speed = new Random().nextInt(100);
        for (int i = 1; i <= 10; ++i) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Thread.currentThread() 用于获取当前执行的线程对象
            // 在 Runnable 中是无法使用 this 获取到当前线程对象的
            System.out.println(i + Thread.currentThread().getName()
                    + "已前进" + (i * speed) + "米（" + speed + "米/秒)");
        }
    }
}
