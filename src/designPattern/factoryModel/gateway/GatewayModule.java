package designPattern.factoryModel.gateway;

import designPattern.abstrcatFactoryModel.abstructFactory;
import designPattern.factoryModel.freeSwitch.FreeSwitch;

/**
 * Created by 98384 on 2017/7/11.
 */
public class GatewayModule extends abstructFactory {

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

    public FreeSwitch getFreeSwitch(String module){
        return null;
    }

}
