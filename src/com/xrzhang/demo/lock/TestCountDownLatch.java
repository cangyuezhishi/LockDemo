package com.xrzhang.demo.lock;


import java.util.concurrent.CountDownLatch;

/**
 * @Program: demo
 * @Description: 测试CountDownLatch
 * @Author: xrzhang
 * @Create: 2021-05-18 15:30
 */

public class TestCountDownLatch {


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {

            new Thread(() -> {

                try {
                    Thread.sleep((long) (Math.random() * 10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                countDownLatch.countDown();

            }).start();
        }

        countDownLatch.await();

        System.out.println("主线程！");
    }

}