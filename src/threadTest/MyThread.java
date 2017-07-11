package threadTest;

import java.io.IOException;

import static java.lang.Thread.sleep;

/**
 * Created by 98384 on 2017/7/11.
 */
public class MyThread implements Runnable{
    int testi;
    MyThread(int i){
        testi = i;
    }
    public void run(){
        System.out.println("This thread is run" + testi);
        try{
            Thread.currentThread().sleep(1000);
        }catch (Exception e){

        }

        System.out.println("This thread is stop"+ testi);
    }

    public static void main(String[] args) {
        MyThread mt = new MyThread(1);
        MyThread mt1 = new MyThread(2);
        Thread t = new Thread(mt,"T1");
        t.start();
        Thread t1 = new Thread(mt1,"T2");
        t1.start();
    }
}
