package com.xrzhang.demo.lock;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Program: LockDemo
 * @Description: 测试CyclicBarrier
 * @Author: xrzhang
 * @Create: 2021-05-18 15:48
 */

public class TestCyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(20, () -> {
            System.out.println("满人，发车!");
        });
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }).start();
        }

    }
}