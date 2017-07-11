package threadTest;

/**
 * Created by 98384 on 2017/7/11.
 */
public class MyThreadLambda{


    public static void main(String[] args) {
        new Thread(()->System.out.println("Thread run"),"zhouhe").start();
    }
}
