package listTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 98384 on 2017/7/8.
 */
public class MyList {

    public static void sMethod(){
        System.out.println("sMethod");
    }

    public static void main(String[] args) {
        MyList ml = new MyList();

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

//        List<String> list = Arrays.asList("1one", "two", "three", "4four");
//        for(String str : list) {
//            if (Character.isDigit(str.charAt(0))) {
//                System.out.println(str);
//            }
//        }

        List<String> list = Arrays.asList("1one", "two", "three", "4four");
        list.stream()// 1.得到容器的Steam
            .filter(str -> Character.isDigit(str.charAt(0)))// 2.选出以数字开头的字符串
            .forEach(str -> System.out.println(str));// 3.输出字符串


    }
}
