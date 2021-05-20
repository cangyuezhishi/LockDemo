package com.xrzhang.demo.lock;


import java.util.concurrent.locks.ReentrantLock;

/**
 * @Program: demo
 * @Description: ReenTrantLock 公平锁测试
 * @Author: xrzhang
 * @Create: 2021-05-17 19:24
 */

public class ReenTrantLock1 extends Thread {
    public static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {

        for (int i = 0; i < 100; i++)
        {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁！");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {

        ReenTrantLock1 reenTrantLock1 = new ReenTrantLock1();

        Thread t1 = new Thread(reenTrantLock1);
        t1.start();

        Thread t2 = new Thread(reenTrantLock1);
        t2.start();

        ReentrantLock rtLock = new ReentrantLock();

        rtLock.lock();

        try {

        } finally {
            rtLock.unlock();
        }

    }
}