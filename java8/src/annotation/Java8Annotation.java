package annotation;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 重复注解与类型注解
 * @author jianming
 * @create 2020-05-15-15:03
 */
public class Java8Annotation {
    /**
     * 重复注解
     * jianming
     */
    @Test
    public void test() throws Exception {
        Class<Java8Annotation> clazz = Java8Annotation.class;
        Method show = clazz.getMethod("show");

        MyAnnotation[] mas = show.getAnnotationsByType(MyAnnotation.class);

        for(MyAnnotation ma : mas) {
            System.out.println(ma.value());
        }

    }

    @MyAnnotation("123aa")
    @MyAnnotation("456bb")
    public void show() {

    }

}
