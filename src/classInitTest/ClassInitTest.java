package classInitTest;



public class ClassInitTest {

    public  static void main(String[] args)
    {
        try {
//测试Class.forName()
            Class testTypeForName = Class.forName("classInitTest.TestClassType");
            System.out.println("testForName---" + testTypeForName);
//测试类名.class
            Class testTypeClass = TestClassType.class;
            System.out.println("testTypeClass---" + testTypeClass);
//测试Object.getClass()
            TestClassType testGetClass = new TestClassType();
            System.out.println("testGetClass---" + testGetClass.getClass());
        } catch (ClassNotFoundException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

