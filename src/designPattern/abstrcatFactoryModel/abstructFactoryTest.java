package designPattern.abstrcatFactoryModel;

import designPattern.factoryModel.freeSwitch.FreeSwitch;
import designPattern.factoryModel.gateway.Gateway;

/**
 * Created by 98384 on 2017/7/11.
 */
public class abstructFactoryTest {
    public static void main(String[] args) {
        SmartProduct sp = new SmartProduct();
        abstructFactory af = sp.getSmartProduct("GW");
        Gateway gt = af.getGatewayModule("WIFI");
        gt.open();
        gt.close();

        abstructFactory af1 = sp.getSmartProduct("FS");
        FreeSwitch fs = af1.getFreeSwitch("ONE");
        fs.open();
        fs.close();
    }
}
