package optional;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author jianming
 * @create 2020-05-15-10:11
 */
public class Java8Optional {

    /**
     * Optional 容器类常用方法
     * - Optional.of(T t) ：创建一个 Optional 实例
     * - Optional.empty() ：创建一个空的Optional 实例
     * - .ofNullable(T t) ：若 t 不为null，创建 Optional 实例，否者创建空实例
     * - .isPresent() ：判断是否包含值
     * - .ofElse(T t) ：如果调用对象包含值，返回该值，否则返回 t
     * - orElseGet(Supplier s) ：如果调用对象包含值，返回该值，否则返回 s 获取的值
     * - map(Function f) ：如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
     * - flatMap(Function mapper) ：与map类似，要求返回值必须是Optional
     */
    @Test
    public void test1() {
        List<String> list = Arrays.asList("aaa", "bbb");
        // .of 方法不能传入 null，否则报空指针异常
        Optional<List<String>> op1 = Optional.of(list);
        System.out.println("op1  " + op1.get());

        // 创建一个空Optional
        Optional<Object> op2 = Optional.empty();
        // 如果包含值 则进入
        if(op2.isPresent()) {
            // java.util.NoSuchElementException: No value present
            System.out.println("op2  " + op2.get());
        }

        // 传入null 创建一个Optional
        Optional<Object> op3 = Optional.ofNullable(null);
        // java.util.NoSuchElementException: No value present
//        System.out.println(op3.get());

        // if or
        Object o = op3.orElse(10086);
        System.out.println("orElse  " + o);

        // 返回特殊处理的值
        Object o1 = op2.orElseGet(() -> {
            double random = Math.random();
            return "Hello " + random;
        });
        System.out.println("orElseGet  " + o1);

        // 把容器中的对象应用到map中
        Optional<String> s = op1.map((x) -> x.get(0));
        System.out.println(s.get());
        // flatMap
        Optional<String> s1 = op1.flatMap((x) -> Optional.of(x.get(1)));
        System.out.println(s1.get());
    }


}
