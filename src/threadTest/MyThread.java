package threadTest;

/**
 * Created by 98384 on 2017/7/11.
 */
public class MyThread implements Runnable{
    public void run(){
        System.out.println("This thread is run");
        System.out.println("This thread is stop");
    }

    public static void main(String[] args) {
        MyThread mt = new MyThread();

        mt.run();
    }
}
