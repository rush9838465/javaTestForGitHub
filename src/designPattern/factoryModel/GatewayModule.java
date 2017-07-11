package designPattern.factoryModel;

/**
 * Created by 98384 on 2017/7/11.
 */
public class GatewayModule {

    public Gateway getGatewayModule(String module){
        if(module == null){
            return null;
        }
        if(module.equalsIgnoreCase("WIFI")){
            return new WifiModule();
        } else if(module.equalsIgnoreCase("ZIGBEE")){
            return new ZigbeeModule();
        }else {
            return null;
        }

    }

}
