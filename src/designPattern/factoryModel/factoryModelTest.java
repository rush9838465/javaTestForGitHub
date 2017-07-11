package designPattern.factoryModel;

import designPattern.factoryModel.gateway.Gateway;
import designPattern.factoryModel.gateway.GatewayModule;

/**
 * Created by 98384 on 2017/7/11.
 */
public class factoryModelTest {
    public static void main(String[] args) {
        GatewayModule gt = new GatewayModule();

        Gateway wm = gt.getGatewayModule("WIFI");
        wm.open();
        wm.write("write data to wifi");
        System.out.println("read: " + wm.read());
        wm.close();

        Gateway zm = gt.getGatewayModule("ZIGBEE");
        zm.open();
        zm.write("write data to zigbee");
        System.out.println("read: " + zm.read());
        zm.close();

    }
}
