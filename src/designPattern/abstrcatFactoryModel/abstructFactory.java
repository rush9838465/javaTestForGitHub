package designPattern.abstrcatFactoryModel;

import designPattern.factoryModel.freeSwitch.FreeSwitch;
import designPattern.factoryModel.gateway.Gateway;

/**
 * Created by 98384 on 2017/7/11.
 */
public abstract class abstructFactory {
    public abstract Gateway getGatewayModule(String module);
    public abstract FreeSwitch getFreeSwitch(String module);
}
