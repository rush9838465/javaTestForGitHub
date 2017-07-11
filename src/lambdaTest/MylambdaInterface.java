package lambdaTest;


import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by 98384 on 2017/7/10.
 */
public interface  MylambdaInterface <T> {
    void test(T y);//通过泛型可以让lambda的参数 更具通用性
    default void test1(){

    }
}
