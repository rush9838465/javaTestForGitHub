package designPattern.factoryModel.freeSwitch;

/**
 * Created by 98384 on 2017/7/11.
 */
public class switchOne implements FreeSwitch {
    public void open(){

        System.out.println(this.getClass().getName() + " is open");
    }

    public void close(){

        System.out.println(this.getClass().getName() + " is close");
    }
}
