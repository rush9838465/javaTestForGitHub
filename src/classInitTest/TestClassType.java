package classInitTest;

public class TestClassType{
    //构造函数
    public TestClassType(){
        System.out.println("----构造函数---");
    }
    //静态的参数初始化
    static{
        System.out.println("---静态的参数初始化---");
    }
    //非静态的参数初始化
    {
        System.out.println("----非静态的参数初始化---");
    }

}
