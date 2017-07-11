package threadTest;

import java.io.IOException;

import static java.lang.Thread.sleep;

/**
 * Created by 98384 on 2017/7/11.
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
                Thread.currentThread().sleep(500);
            }catch (Exception e){
                System.out.println(e);
            }
            System.out.println("Thread "+ name + ": " + i);
        }
        System.out.println("Thread "+ name + " exiting");
    }

    public void threadStart(){
        Thread t = new Thread(this, name);
        t.start();
    }

    public static void main(String[] args) {
        MyThread mt = new MyThread("zhou");
        MyThread mt1 = new MyThread("he");

        mt.threadStart();
        mt1.threadStart();
    }
}
