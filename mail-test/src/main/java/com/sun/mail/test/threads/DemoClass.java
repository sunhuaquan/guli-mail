package com.sun.mail.test.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DemoClass {

    //通信对象:0--打印A 1---打印B 2----打印C
    private int number = 0; //声明锁
    private Lock lock = new ReentrantLock();
    //声明钥匙/A
    private Condition conditionA = lock.newCondition();
    //声明钥匙B
    private Condition conditionB = lock.newCondition();
    //声明钥匙C
    private Condition conditionC = lock.newCondition();


    /**
     * A打印5次
     */
    public void printA(int j) {
        try {
            lock.lock();
            while (number != 0) {
                conditionA.await();
            }
            System.out.println(Thread.currentThread().getName() + "输出A,第" + j + "轮开始"); //输出5次A
            for (int i = 0; i < 5; i++) {
                System.out.println("A");
            }
            //开始打印B
            number = 1;
            // 唤醒B
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * B打印10次
     */
    public void printB(int j) {
        try {
            lock.lock();
            while (number != 1) {
                conditionB.await();
            }
            System.out.println(Thread.currentThread().getName() + "输出B,第" + j + "轮开始");
            //输出10次B
            for (int i = 0; i < 10; i++) {
                System.out.println("B");
            }
            //开始打印C
            number = 2;
            //唤醒C
            conditionC.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * C打印15次
     */
    public void printC(int j) {
        try {
            lock.lock();
            while (number != 2) {
                conditionC.await();
            }
            System.out.println(Thread.currentThread().getName() + "输出C,第" + j + "轮开始");
            //输出15次C
            for (int i = 0; i < 15; i++) {
                System.out.println("C");
            }
            System.out.println("-----------------------------------------");
            //开始打印A
            number = 0;
            //唤醒A
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 交替加减 * @param args
     */
    public static void main(String[] args) {
        DemoClass demoClass = new DemoClass();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                demoClass.printA(i);
            }
        }, "A线程").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                demoClass.printB(i);
            }
        }, "B线程").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                demoClass.printC(i);
            }
        }, "C线程").start();
    }
}

