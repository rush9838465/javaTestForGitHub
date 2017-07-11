package threadTest;

import java.io.IOException;
import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

/**
 * Created by 98384 on 2017/7/11.
 * @author zhouhe
 * @version 1.0.0.1
 */
public class MyThread implements Runnable{
    public String name;

    public MyThread(String name){
        this.name = name;
    }
    public void run(){
        System.out.println("Thread "+ name + " start");
        for(int i = 0; i < 3; i++) {
            try{
                Thread.sleep(500);
            }catch (Exception e){
                System.out.println(e);
            }
            System.out.println("Thread "+ name + ": " + i);
        }
        System.out.println("Thread "+ name + " exiting");
    }

    public void threadStart(boolean flag){
        Thread t = new Thread(this, name);
        t.setDaemon(flag);//设置是否为守护进程  定义：守护线程--也称“服务线程”，在没有用户线程可服务时会自动离开。
        t.start();
    }

    public static void main(String[] args) {
        MyThread mt = new MyThread("zhou");
        MyThread mt1 = new MyThread("he");

        mt.threadStart(false);
        mt1.threadStart(true);//如果都是守护进程将不执行任何线程而直接结束进程
    }
}
