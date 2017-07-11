package listTest;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

/**
 * Created by 98384 on 2017/7/8.
 */
public class MyMap {
    public static void main(String[] args) {
        Map<String, Integer> myMap = new HashMap<String, Integer>();

        myMap.put("zhou", 91);
        myMap.put("he", 92);
        myMap.put("zhou he", 96);

        ActionListener listener = event -> System.out.println("button clicked");// 2
        BinaryOperator<Long> addImplicit = (x, y) -> x + y;// 5

        System.out.println("通过Map.entrySet遍历key和value");
        for (Map.Entry<String, Integer> entry : myMap.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            entry.setValue(1);//修改Value
        }

        for(String key : myMap.keySet()){
            System.out.println("key="+ key + "      value=" + myMap.get(key));
        }



    }
}
