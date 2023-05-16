package com.jacob.thread;

/**
 * 死锁示例
 */
@SuppressWarnings("all")
public class DeadLock {

    private static final String fileA = "A文件";
    private static final String fileB = "B文件";

    public static void main(String[] args) {
        // 线程1
        new Thread() {
            public void run() {
                while (true) {
                    // 打开文件A，线程独占
                    synchronized (fileA) {
                        System.out.println(this.getName() + "：文件A写入");
                        synchronized (fileB) {
                            System.out.println(this.getName() + "：文件B写入");
                        }
                        System.out.println(this.getName() + "：所有文件保存");
                    }
                }
            }
        }.start();

        // 线程2
        new Thread() {
            public void run() {
                while (true) {
                    // 打开文件A，线程独占
                    synchronized (fileB) {
                        System.out.println(this.getName() + "：文件B写入");
                        synchronized (fileA) {
                            System.out.println(this.getName() + "：文件A写入");
                        }
                        System.out.println(this.getName() + "：所有文件保存");
                    }
                }
            }
        }.start();
    }
}
