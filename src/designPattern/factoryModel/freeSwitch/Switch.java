package designPattern.factoryModel.freeSwitch;

import designPattern.abstrcatFactoryModel.abstructFactory;
import designPattern.factoryModel.gateway.Gateway;

/**
 * Created by 98384 on 2017/7/11.
 */
public class Switch extends abstructFactory {

    public FreeSwitch getFreeSwitch(String module){
        if(module == null){
            return null;
        }
        if(module.equalsIgnoreCase("ONE")){
            return new switchOne();
        } else if(module.equalsIgnoreCase("TWO")){
            return new switchTwo();
        }else {
            return null;
        }
    }

    public Gateway getGatewayModule(String module){
        return null;
    }


}
