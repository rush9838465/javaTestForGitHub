package designPattern.abstrcatFactoryModel;

import designPattern.factoryModel.freeSwitch.FreeSwitch;
import designPattern.factoryModel.freeSwitch.Switch;
import designPattern.factoryModel.gateway.Gateway;
import designPattern.factoryModel.gateway.GatewayModule;

/**
 * Created by 98384 on 2017/7/11.
 */
public class SmartProduct{

    public abstructFactory getSmartProduct(String name){
        if(name == null){
            return null;
        }
        if(name.equalsIgnoreCase("GW")){
            return new GatewayModule();
        } else if(name.equalsIgnoreCase("FS")){
            return new Switch();
        }else {
            return null;
        }
    }

}
