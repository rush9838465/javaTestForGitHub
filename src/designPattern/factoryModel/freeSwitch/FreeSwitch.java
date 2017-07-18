package designPattern.factoryModel.freeSwitch;

/**
 * Created by 98384 on 2017/7/11.
 */
public interface FreeSwitch {
    void close();
    void open();
    default void start(){
        System.out.print("333");
    }
    static void start1(){
        System.out.print("333");
    }

}
