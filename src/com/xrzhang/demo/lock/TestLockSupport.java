package com.xrzhang.demo.lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Program: LockDemo
 * @Description: 测试LockSupport  说明：比wait notify更灵活
 * @Author: xrzhang
 * @Create: 2021-05-19 14:09
 */

public class TestLockSupport {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

        try {
            for (int i = 0; i < 8; i++) {
                TimeUnit.SECONDS.sleep(1);
            }
            LockSupport.unpark(t);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

}