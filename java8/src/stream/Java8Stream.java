package stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Stream流操作
 *  .filter((集合单个元素) -> true/false) 筛选符合某个条件的元素
 *  .map(对象::对象属性)  可以只取对象的该属性出来
 *  .limit(int)  限制选取条数
 *  .forEach(System.out::println)  遍历数据输出
 *  .collect(Collectors.toCollections)  将流转换成某种集合
 *
 * 步骤：
 *   1、 创建流
 *   2、 中间操作
 *   3、 终止操作
 * 
 * @author jianming
 * @create 2020-05-14-11:12
 */
public class Java8Stream {

    /**
     * 集合数据
     */
    List<Integer> list = new ArrayList<>();
    {list.add(1);list.add(3);list.add(2);list.add(5);list.add(6);list.add(4);}


    /**
     * 使用流操作筛选集合中的数据
     */
    @Test
    public void test1() {

        // 使用Stream对集合进行操作
        // 1、创建流
        list.stream()
                // 2、中间操作
                .filter((x) -> x > 3)
                .limit(2)
                // 3、终止操作
                .forEach(System.out::println);

    }

    /**
     * 创建Stream的方式
     *  1、Collection.stream
     *  2、Arrays.stream
     *  3、Stream.of
     *  4、Stream.iterate \ Stream.generate
     */
    @Test
    public void test2() {
        // 1、可以通过Collection系列集合提供的stream() 或 parallelStream获取流
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        // 2、通过Arrays 中的静态方法 stream() 获取数组流
        Integer[] ins = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(ins);

        // 3、通过 Stream 类中的静态方法 of() 获取流
        Stream<String> stream2 = Stream.of("aa", "bb", "cc");

        // 4、创建无限流（无穷尽）
        // 迭代获取流  参数一：起始值  参数二：迭代规则
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
        stream3.limit(3).forEach(System.out::println);

        // 生成获取流
        Stream.generate(() -> Math.random()).limit(3).forEach(System.out::println);
    }

    /**
     * 流的中间操作 (注：在未执行终止操作前，中间操作是不会执行任何操作的)
     *  筛选与切片
     *  filter -- 接收Lambda，从流中排除某些元素
     *  limit -- 截断流，使其元素不超过给定数量，并将流短路
     *  skip -- 跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回空流。
     *  distinct -- 筛选，通过流所生成元素的hashCode() 和 equals() 去除重复元素 （自定义类的对象需要重写hashCode与equals方法）
     *
     *  映射
     *  map -- 接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并且将其映射成一个新的元素。
     *  flatMap -- 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     *
     * 内部迭代：迭代操作由Stream API 完成
     */
    @Test
    public void test3() {
        // 获取流
        Stream<Integer> stream = list.stream();
        // 筛选与切片 ------------------------------------------
        // filter 筛选
        stream = stream.filter((x) -> {
            System.out.println("Stream API filter 的中间操作");
            return x > 4;
        });
//        stream.limit(3);
//        stream.distinct();
//        stream.skip(2);

        // 终止操作：一次性执行全部终止操作，即"惰性求值"
        stream.forEach(System.out::println);

        System.out.println("------------------------------------------");

        // 映射 ------------------------------------------
        // map映射
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");
        Stream<String> mapStream = list.stream();

        mapStream.map((x) -> x.toUpperCase()).forEach(System.out::println);

        System.out.println("------------------------------------------");

        // flatMap映射
        Stream<Character> characterStream = list.stream()
                // 将流的集合直接映射成一个流来操作  相当于List的addAll方法
                .flatMap(Java8Stream::filterCharacter);
        characterStream.forEach(System.out::println);

    }
    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for(Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    /**
     * 中间操作
     *  排序
     *  sorted() -- 自然排序(Comparable)
     *  sorted(Comparator com) -- 定制排序
     *
     */
    @Test
    public void test4() {
        List<String> list = Arrays.asList("cac","bcb","ada","ebe","ded");
        // 自然排序
        list.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("------------------------------------------");

        // 定制排序 按字符串的第二个字符排序
        list.stream()
                .sorted((x1, x2) -> x1.charAt(1) > x2.charAt(1)?1:-1)
                .forEach(System.out::println);

    }

    /**
     * 中间操作
     *  查找与匹配
     *  allMatch -- 检查是否匹配所有元素 返回Boolean值
     *  anyMatch -- 检查是否至少匹配一个元素 返回Boolean值
     *  noneMatch -- 检查是否没有匹配元素 返回Boolean值
     *  findFirst -- 返回第一个元素 返回Optional
     *  findAny -- 返回当前流的任意一个元素
     *  count -- 返回流中元素的总个数
     *  max -- 返回流中最大值
     *  min -- 返回流中最小值
     */
    @Test
    public void test5() {
        // 判断流中是否全部匹配该条件
        boolean b = list.stream()
                .allMatch((x) -> x > 4);

        // 获取并行流，多个线程同时指向filter、findAny操作，谁先找到findAny就输出谁
        Optional<Integer> any = list.parallelStream()
                .filter((x) -> x > 4)
                .findAny();
        System.out.println(any);

        // 查找流中的第一个值
        Optional<Integer> first = list.stream()
                .findFirst();
        System.out.println(first.orElse(-1));

        // 查找流中最大的值
        Optional<Integer> max = list.stream()
                .max((x1, x2) -> x1 > x2 ? 1 : -1);
        System.out.println(max.get());


    }

    /**
     * 中间操作
     *  归约
     *  reduce(T identity, BinaryOperator) / reduce(BinaryOperator) -- 可以将流中元素反复结合起来，得到一个值。
     */
    @Test
    public void test6() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Integer reduce = list.stream()
                // 参数一：起始值  参数二：归约操作  以起始值作为x，流中取出数据为y，相加后的值再作为x
                .reduce(0, (x, y) -> x + y);
        System.out.println("reduce = " + reduce);
    }

    /**
     * 中间操作
     *  收集
     *  collect(Collectors.xxx) -- 将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     *
     *  Collectors.
     *    toMap
     *    toList
     *    toSet
     *    toConcurrentMap
     *    toCollection(HashMap::new)
     *    counting -- 总数
     *    averagingXxx -- 平均值
     *    maxBy -- 最大值
     *    groupingBy -- 分组，返回Map
     *    groupingBy(groupingBy) -- 多级分组
     *    partitioningBy -- 分区
     *    summarizingXxx -- 将对象或属性转换为一个对象（包含平均值、最大值等）
     *    joining -- 连接
     */
    @Test
    public void test7() {
        Map<Double, Double> collect = list.stream()
                .collect(Collectors.toMap((x) -> x + 0.3, (y) -> y + 0.5));
        Set<Map.Entry<Double, Double>> entries = collect.entrySet();
        for(Map.Entry entry : entries) {
            System.out.println("key = " + entry.getKey() + " value = " + entry.getValue());
        }
//        collect.forEach((x, y) -> System.out.println(x + "  " + y));
    }


    /**
     * 并行流
     *  Stream API可以声明性地通过parallel() 与 sequential() 在并行流与顺序流之间进行切换
     */
    @Test
    public void test8() {
        // 使用Fork/Join框架 并行计算0-100000000000的和
        long reduce = LongStream.rangeClosed(0, 100000000000L)
                // 将顺序流切换为并行流
                .parallel()
                .reduce(0, Long::sum);
    }

}
