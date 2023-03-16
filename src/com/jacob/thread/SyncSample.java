package com.jacob.thread;

import java.util.Random;

/**
 * {@code synchronized} 代码块/方法使用示例
 */
public class SyncSample {
    public static void main(String[] args) {
//        Couplet c = new Couplet();
        for (int i = 0; i < 100; ++i) {
            new Thread(() -> {
                int r = new Random().nextInt(2);
                if (r % 2 == 0) {
                    Couplet.first();
                } else {
                    Couplet.second();
                }
            }).start();
        }
    }
}

/**
 * 对联类
 */
class Couplet {

    // 锁对象
//    static final Object lock = new Object();
//    static final Object lock2 = new Object();

    /**
     * 安全的
     */
    public synchronized static void first() {
        // 同步代码块，在同一时间只允许有一个线程执行访问这个方法
//        synchronized (lock) {
            System.out.print("琴");
            System.out.print("瑟");
            System.out.print("琵");
            System.out.print("琶");
            System.out.println();
//        }
    }

    /**
     * 安全的
     */
    public static void second() {
        // 如果两个同步代码指向了同一把锁lock，那么在同一个时间内只允许有一个代码块执行，其他等待
        synchronized (Couplet.class) {
            System.out.print("魑");
            System.out.print("魅");
            System.out.print("魍");
            System.out.print("魉");
            System.out.println();
        }
    }
}
