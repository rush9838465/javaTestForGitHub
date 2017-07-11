package lambdaTest;

/**
 * Created by 98384 on 2017/7/10.
 */
public class Mylambda {
    int[] arr = new int[10];

    public void lambdaTest(MylambdaInterface mi){
        mi.test(this);
    }
    public static void main(String[] args) {
        Mylambda ml = new Mylambda();
        ml.lambdaTest((x)->{
            System.out.println(x);

        });
    }
}