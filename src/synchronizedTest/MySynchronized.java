package synchronizedTest;

/**
 * Created by 98384 on 2017/7/18.
 */
public class MySynchronized implements Runnable{
    private static int st = 0;

    volatile private String name;

    public MySynchronized(String name){
        this.name = name;
    }

    public synchronized static void getIndex(String name)//如果没有synchronized 2个线程会穿插调用
    {
        System.out.println(name + ": " + st);
        st++;



    }

    public void run(){
        for(int i = 0; i < 100; i++){
            getIndex(name);
        }
    }

    public static void main(String[] args) {
        MySynchronized m1 = new MySynchronized("zhou");
        MySynchronized m2 = new MySynchronized("he");

        Thread t1 = new Thread(m1, "zhou");
        Thread t2 = new Thread(m2, "he");

        t1.start();
        t2.start();
    }
}
