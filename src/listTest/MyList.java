package listTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 98384 on 2017/7/8.
 */
public class MyList {
    public static void main(String[] args) {
        List<String> testList = new ArrayList<String>();

        testList.add("one");
        testList.add("two");
        testList.add("three");


        //forEach
        for(String str : testList){
            System.out.println(str);
        }
        //for
        for(int i = 0; i < testList.size(); i++){
            System.out.println(testList.get(i));
        }
        //Iterator
        Iterator<String> ite = testList.iterator();
        while(ite.hasNext())//判断下一个元素之后有值
        {
            System.out.println(ite.next());
        }
        //lamada
        testList.forEach((str)->System.out.println(str));

    }
}
