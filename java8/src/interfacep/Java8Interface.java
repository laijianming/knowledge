package interfacep;

/**
 * 默认方法
 *  java8支持接口有已实现的方法，用default修饰
 * @author jianming
 * @create 2020-05-15-11:19
 */
public interface Java8Interface {

    /**
     * 默认方法
     * @param str
     * @return
     */
    default String hello(String str) {
        return "Hello " + str;
    }

    /**
     * 静态方法
     */
    static void hello() {
        System.out.println("静态方法");
    }


}
