package com.jacob.thread;

import java.util.Random;

/**
 * 使用集成 {@code Thread} 的方式实现多线程
 */
public class Match1 {
    public static void main(String[] args) {
        Runner cao = new Runner();
        cao.setName("曹操");

        Runner liu = new Runner();
        liu.setName("刘备");

        Runner sun = new Runner();
        sun.setName("孙权");

        cao.start();
        liu.start();
        sun.start();
    }
}

class Runner extends Thread {
    @Override
    public void run() {
        // 创建一个 100 以内的随机整数作为「速度」
        int speed = new Random().nextInt(100);
        for (int i = 1; i <= 10; ++i) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(i + this.getName() + "已前进" + (i * speed) + "米（" + speed + "米/秒)");
        }
    }
}
