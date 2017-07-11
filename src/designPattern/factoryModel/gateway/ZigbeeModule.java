package designPattern.factoryModel.gateway;

/**
 * Created by 98384 on 2017/7/11.
 */
public class ZigbeeModule implements Gateway{
    String str;

    public void open(){
        System.out.println(this.getClass().getName() + " is open");
    }

    public void close(){

        System.out.println(this.getClass().getName() + " is close");
    }

    public String read(){
        return str;
    }

    public void write(String str){
        this.str = str;
    }
}
