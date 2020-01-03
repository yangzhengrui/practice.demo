package com.practice.treaddemo;

import org.junit.jupiter.api.Test;

/**
 * Java用Thread对象表示一个线程，通过调用start()启动一个新线程；
 * 一个线程对象只能调用一次start()方法；
 * 线程的执行代码写在run()方法中；
 * 线程调度由操作系统决定，程序本身无法决定调度顺序；
 * Thread.sleep()可以把当前线程暂停一段时间。
 * 
 * Java线程对象Thread的状态包括：New、Runnable、Blocked、Waiting、Timed Waiting和Terminated；
 * 通过对另一个线程对象调用join()方法可以等待其执行结束；
 * 可以指定等待时间，超过等待时间线程仍然没有结束就不再等待；
 * 对已经运行结束的线程调用join()方法会立刻返回。
 * 
 * 对目标线程调用interrupt()方法可以请求中断一个线程，目标线程通过检测isInterrupted()标志获取自身是否已中断。
 * 如果目标线程处于等待状态，该线程会捕获到InterruptedException；
 * 目标线程检测到isInterrupted()为true或者捕获了InterruptedException都应该立刻结束自身线程；
 * 通过标志位判断需要正确使用volatile关键字；
 * volatile关键字解决了共享变量在线程间的可见性问题。
 * volatile关键字的目的是告诉虚拟机：
 *  每次访问变量时，总是获取主内存的最新值；
 *  每次修改变量后，立刻回写到主内存。
 * 
 * 守护线程是为其他线程服务的线程；
 * 所有非守护线程都执行完毕后，虚拟机退出；
 * 守护线程不能持有需要关闭的资源（如打开文件等）。
 */
public class TreadTest {

    @Test
    public void TreadDemoTest() {
        //方法一
        final Thread thread = new MyThread();
        thread.start();
        //方法二
        final Thread thread2 = new Thread(new MyRunnable());
        thread2.start();
        //方法三  jdk8 lambda
        final Thread thread3=new Thread(()->{
            System.out.println("start new thread by labmda!");
        });
        thread3.start();
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("start new thread!");
        }
    }

    class MyRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("start new thread by Runnable!");
        }
    }
}