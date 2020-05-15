package lambda;

import org.junit.Test;

import java.io.PrintStream;
import java.util.*;
import java.util.function.*;

/**
 * Lambda表达式
 * 给只有一个方法的接口（函数式接口）使用，
 *   创建对象的时候，不需要写new，不需要写参数类型（JVM编译器通过上下文（当前句）推断出数据类型），直接写方法体的内容
 *  语法：  参数列表（不用写类型） -> {方法实现}
 *
 * 函数式接口
 *  只有一个方法的接口，可以使用 @FunctionalInterface 注解修饰，可规定当前接口必须只有一个方法，否则报错
 *
 * Java8内置四大核心函数式接口
 *  Consumer<T>   消费型接口； 方法：void accept(T t);
 *  Supplier<T>   供给型接口； 方法：T get();
 *  Function<T,R> 函数型接口； 方法：R apply(T t);
 *  Predicate<T>  断定型接口； 方法boolean test(T t);
 *
 * @author jianming
 * @create 2020-05-13-21:31
 */
public class Java8Lambda {

    /**
     * 样例： Comparator
     *   Comparator<Integer> com = (x, y) -> Integer.compare(x,y);
     *
     */
    public void compare() {
        // (x, y) -> Integer.compare(x,y); == Integer::compare;
        Comparator<Integer> com = (x, y) -> Integer.compare(x,y);
        TreeSet<Integer> treeSet = new TreeSet<>(com);
    }


    /**
     * 语法格式一：无参数，无返回值
     *  () -> System.out.println("Hello World!!");
     */
    @Test
    public void test1() {
        // 局部内部类使用同级别变量时，该变量必须为final修饰。jdk1.8默认添加
        int i = 10;
        new Thread(() ->
            System.out.println("Hello World!! " + i)
        ).start();
    }

    /**
     * 语法格式二：有一个参数，无返回值
     *  (x) -> System.out.println(x);  == x -> System.out.println(x);
     */
    @Test
    public void test2() {
        // Consumer<String> con = x -> System.out.println(x);
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("Hello World!!");
    }

    /**
     * 语法格式三：有多个参数，有返回值，多条语句
     *  (x,y) -> {}
     *
     *  若只有一条语句，则不用写大括号和return
     */
    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("有多个参数，有返回值，多条语句");
            return Integer.compare(x,y);
        };
        int compare = com.compare(7, 4);
        System.out.println("compare = " + compare);
        // com = (x,y) -> Integer.compare(x,y);

    }

    /**
     * 应用：对一个集合进行排序
     */
    @Test
    public void test4() {
        List<Integer> list = new ArrayList<>();
        Collections.sort(list, (e1, e2) -> {
            System.out.println("排序");
            return Integer.compare(e1, e2);
        });
    }

    /**
     * ------------------------------------------------------------------
     */


    /**
     * Java8内置四大核心函数式接口
     */

    /**
     * 函数式接口一： 消费型接口
     *   Consumer<T> void accept(T t);
     *
     *  用于类似策略模式的情景，给一个固定的方法传入一个自定义Lambda实现类进去，执行当前实现类方法
     */
    @Test
    public void test5() {
        consume(20d,(x) -> {
            // 消费过程
            double discount = 0.8;
            System.out.println("打折活动"+ discount + "折,成功消费了" + x*discount + "元");
        });
    }
    public void consume(double money,Consumer<Double> con) {
        System.out.println("准备付款");
        con.accept(money);
        System.out.println("付款成功");
    }

    /**
     * 函数式接口二： 供给型接口
     *   Supplier<T> T get();
     *
     *  用于进行一系类操作后返回一个对象的情景（用于产生一个对象）
     */
    @Test
    public void test6() {
        // 生成10个随机数
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 1000));
        numList.stream().forEach(System.out::println);
    }
    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> result = new ArrayList<>();
        Integer integer;
        for(int i = 0; i < num; i++) {
            integer = sup.get();
            result.add(integer);
        }
        return result;
    }

    /**
     * 函数式接口三： 函数型接口
     *   Function<T,R> R apply(T t);
     *
     *  用于对一个对象做一些处理并返回该对象或该对象的属性或其他情景
     */
    @Test
    public void test7() {
        // 对一个字符串进行处理，并返回该字符串处理后的长度
        Integer integer = strHandler(" \t\t Hello World !!!    ", (x) -> x.trim().length());
        System.out.println("处理过的字符串长度为 " + integer);
    }
    public Integer strHandler(String str, Function<String, Integer> fun) {
        return fun.apply(str);
    }


    /**
     * 函数式接口四： 断言型接口
     *   Predicate<T> boolean test(T t);
     */
    @Test
    public void test8() {
        // 生成随机数
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 1000));
        // 取大于等于500 的数的集合
        List<Integer> list = filterNum(numList, (x) -> x >= 500);
        list.stream().forEach(System.out::println);
    }
    public List<Integer> filterNum(List<Integer> list, Predicate<Integer> pre) {
        List<Integer> result = new ArrayList<>();
        for(Integer i : list) {
            if(pre.test(i)) {
                result.add(i);
            }
        }
        return result;
    }


    /**
     * ------------------------------------------------------------------
     */


    /**
     * Lambda的方法引用
     */


    /**
     * 方法引用的三种表现格式
     *  对象::实例方法名
     *   类::静态方法名
     *   类::实例方法名
     *
     * 使用条件： 接口抽象方法的参数和返回值类型 == 调用方法的参数和返回值类型
     */
    @Test
    public void test9() {
        Consumer<Integer> con;
        con = (x) -> System.out.println(x);
        // 方法引用1： 对象::实例方法名
        PrintStream ps = System.out;
        con = ps::println;

        // 方法引用2： 类::静态方法名
        Comparator<Integer> com = Integer::compare;

        // 方法引用3： 类::实例方法名
        // 条件：当第一个参数是实例方法的调用者，第二个参数是实例方法的参数时，才可以使用这种方式
        BiPredicate<String,String> bp = String::equals;

    }

    /**
     * Lambda的构造器引用
     */

    /**
     * 构造器引用格式
     *  ClassName::new
     *
     * 构造器引用即调用构造方法，返回对象
     * 调用哪个构造器取决于传入的参数类型及数量
     */
    @Test
    public void test10() {
        Supplier<List> sup = () -> new ArrayList();
        // 调用无参构造器，供给型接口为 T get(); 无参有返回值
        sup = ArrayList::new;
        System.out.println(sup.get().size());

        Function<Integer,List> fun = (x) -> new ArrayList(x);
        // 调用有参构造器，函数型接口为 R apply(T t); 有参无返回值
        fun = ArrayList::new;
        System.out.println(fun.apply(29).size());
    }

    /**
     * Lambda的数组引用
     */

    /**
     * 构造器引用格式
     *  Type[]::new;
     */
    @Test
    public void test11() {
        Function<Integer,String[]> fun = (x) -> new String[x];
        fun = String[]::new;
        String[] apply = fun.apply(10);
    }
}
