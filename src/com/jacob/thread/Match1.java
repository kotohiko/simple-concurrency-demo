package com.jacob.thread;

import java.util.Random;

/**
 * 使用集成 {@code Thread} 的方式实现多线程
 */
public class Match1 {
    public static void main(String[] args) {
        Runner liuXiang = new Runner();
        liuXiang.setName("刘翔");
        Runner jacob = new Runner();
        jacob.setName("Jacob");
        Runner luFei = new Runner();
        luFei.setName("路飞");
        liuXiang.start();
        jacob.start();
        luFei.start();
    }
}

class Runner extends Thread {
    @Override
    public void run() {
        int speed = new Random().nextInt(100);
        for (int i = 1; i <= 100; ++i) {
            try {
                Thread.sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + "已前进" + (i * speed) + "米（" + speed + "米/秒)");
        }
    }
}
