package com.xrzhang.demo.lock;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Program: LockDemo
 * @Description:
 * @Author: xrzhang
 * @Create: 2021-05-19 14:34
 */

public class TestWaitNotify {

    private static List<Integer> list = new ArrayList<>();

    public static void addList(Integer i) {
        list.add(i);
    }

    public static Integer size() {
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {

        Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("T2启动");
                if (size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("T2结束");
                lock.notify();
            }
        }).start();


        TimeUnit.SECONDS.sleep(5);
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("T1启动");
                for (int i = 0; i < 10; i++) {
                    System.out.println("add:" + i);
                    addList(i);
                    if (size() == 5) {
                        lock.notify(); //notify不释放锁

                        // 调用lock.wait，释放锁
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("T1结束");
            }
        }).start();


    }


}