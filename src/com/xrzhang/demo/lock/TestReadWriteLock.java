package com.xrzhang.demo.lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Program: LockDemo
 * @Description: 读写锁测试
 * @Author: xrzhang
 * @Create: 2021-05-18 16:39
 */

public class TestReadWriteLock {

    public static int value;

    public static ReentrantLock reentrantLock = new ReentrantLock();

    public static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static Lock readLock = readWriteLock.readLock();

    public static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock) {
        lock.lock();
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "读value");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock) {
        lock.lock();
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "写value");

            value = value + 1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {


        for (int i = 0; i < 18; i++) {
//            new Thread(() -> read(reentrantLock)).start();
            new Thread(() -> read(readLock)).start();
        }

//        for (int i = 0; i < 3; i++) {
//            new Thread(() -> write(writeLock));
//        }



    }

}