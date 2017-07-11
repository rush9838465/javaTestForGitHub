package designPattern.factoryModel;

/**
 * Created by 98384 on 2017/7/11.
 */
public interface Gateway {
    void open();
    void close();
    String read();
    void write(String str);
}
