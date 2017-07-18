package synchronizedTest;

/**
 * Created by 98384 on 2017/7/18.
 */
public class Mysynchronized implements Runnable{
    private static int st = 0;

    private String name;

    public Mysynchronized(String name){
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
        Mysynchronized m1 = new Mysynchronized("zhou");
        Mysynchronized m2 = new Mysynchronized("he");

        Thread t1 = new Thread(m1, "zhou");
        Thread t2 = new Thread(m2, "he");

        t1.start();
        t2.start();
    }
}
