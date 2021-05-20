package com.xrzhang.demo.lock;


import java.util.concurrent.Semaphore;

/**
 * @Program: LockDemo
 * @Description: 测试信号量 (限流)
 * @Author: xrzhang
 * @Create: 2021-05-18 17:00
 */

public class TestSemaphore {

    public static void main(String[] args) {
//        Semaphore semaphore = new Semaphore(5);
        Semaphore semaphore = new Semaphore(2, true);
        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("T1 running!");
                Thread.sleep(5000);


            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }

        }).start();

        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("T2 running!");
                Thread.sleep(10000);


            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }

        }).start();

    }

}