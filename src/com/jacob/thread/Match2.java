package com.jacob.thread;

import java.util.Random;

/**
 * 通过实现 {@code Runnable} 来创建线程
 */
public class Match2 {
    public static void main(String[] args) {
        Runner2 liuXiang = new Runner2();
        Thread thread1 = new Thread(liuXiang);
        thread1.setName("刘翔");
        Thread jacob = new Thread(new Runner2());
        jacob.setName("Jacob");
        Thread luFei = new Thread(new Runner2());
        luFei.setName("路飞");
        thread1.start();
        jacob.start();
        luFei.start();
    }
}

class Runner2 implements Runnable {
    @Override
    public void run() {
        int speed = new Random().nextInt(100);
        for (int i = 1; i <= 100; ++i) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Thread.currentThread()用于获取当前执行的线程对象
            // 在Runnable中是无法使用this获取到当前线程对象的
            System.out.println(Thread.currentThread().getName() + "已前进" + (i * speed) + "米（" + speed + "米/秒)");
        }
    }
}
