## java8新特性简介
- 速度更快
- 代码更少（增加了新的语法Lambda表达式）√ 
- 强大的Stream API √ 
- 便于并行
- 最大化减少空指针异常 Optional

### 速度更快
- 其一  
HashMap 的底层数据结构的改变  
将链表采用链表+红黑树的结构来替换  
原因：防止一个链表过长而导致查询效率低下 

  延伸问题：为什么选择红黑树而不是其他树  
-- 红黑树牺牲了一些查找性能 但其本身并不是完全平衡的二叉树。因此插入删除操作效率略高于AVL树

- 其二  
ConcurrentHashMap 的锁结构改变  
将分段锁切换成CAS算法

- 其三  
JVM内存结构变化  
将永久代去除，增加元空间  
原因：  
1、永久代内存经常不够用或者发生内存泄露  
2、字符串存在永久代中，容易出现性能问题和内存溢出。  
3、永久代会给GC带来不必要的复杂度，而且回收效率偏低。  
4、类及方法的信息等比较难确定其大小，因此对于永久代的大小指定比较困难，太小容易出现永久代溢出，太大则容易导致老年代溢出。  

  元空间使用的是物理内存，物理内存多大 元空间就能用多大，故几乎不会出现内存溢出


## Lambda表达式 
Lambda是一个匿名函数，一段可以传递的代码。可以写出更简洁、更灵活的代码。作为一种更紧凑的代码风格，是Java的语言表达能力得到了提升  
- 语法格式一：无参数，无返回值  
() -> System.out.println("Hello World!!");  
- 语法格式二：有一个参数，无返回值  
(x) -> System.out.println(x);  == x -> System.out.println(x);  
- 语法格式三：有多个参数，有返回值，多条语句(若只有一条语句，则不用写大括号和return**)  
(x,y) -> {}  
#### Java内置四大核心函数式接口  
- Consumer<T> 消费型接口； 参数类型T； 返回值类型void； 用途：对类型为T的对象应用操作，包含方法：void accept(T t);  
- Supplier<T> 供给型接口； 参数类型无； 返回值类型T； 用途：返回类型为T的对象，包含方法：T get();
- Function<T,R> 函数型接口； 参数类型T； 返回值类型R； 用途：对类型为T的对象应用操作，并返回结果。结果包含R类型的对象。包含方法：R apply(T t);
- Predicate<T> 断定型接口； 参数类型T； 返回类型boolean； 用途：确定类型为T的对象是否满足某约束，并返回boolean值。包含方法boolean test(T t);  

#### 方法引用  
方法引用：若Lambda体中的内容有方法已经实现了（只进行调用一个其他的方法的操作），可以使用"方法引用"  
可以理解为方法引用是Lambda表达式的另外一种表现形式  

主要有三种语法格式：
- 对象::实例方法名  
- 类::静态方法名  
- 类::实例方法名  

使用条件：  
  **接口抽象方法的参数和返回值类型 == 调用方法的参数和返回值类型**  
  **若Lambda 参数列表的第一个参数是 实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method**  

#### 构造器引用  
格式：
- ClassName::new 创建对象  

**调用哪个构造器取决于 接口方法传进的参数类型及数量**

#### 数组引用
格式：
- Type[]::new; 创建数组


## Stream 
Stream是java8中处理集合的关键抽象概念，可以指定希望对集合进行的操作，可以执行非常复杂的查找、过滤和映射数据等操作。  
使用Stream API对集合数据进行操作，就类似于使用SQL执行的数据库查询。也可以使用Stream API来并行执行操作。  
Stream API提供了一种高效且易于使用的处理数据的方式  

Stream是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列。“集合讲的是数据，流讲的是计算”

**Stream流操作**  
对一个数据源通过像管道方式一样读取出来，在管道中可以进行一系列的复杂操作，再将管道中的数据输出到一个新的数据源，原数据源的数据不会发生变化  

步骤：
- 创建Stream ：一个数据源（如：集合、数组），通过Collection或parallelStream 获取一个流 
- 中间操作 ：一个中间操作链，对数据源的数据进行处理 
- 终止操作（终端操作） ：一个终止操作，执行中间操作链，并产生结果  

**Stream的中间操作**  
多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何处理！而在终止操作时一次性全部处理

##### 创建流的方式 
1、Collection.stream / .parallelStream  
2、Arrays.stream  
3、Stream.of  
4、Stream.iterate / .generate  

##### 中间操作 
1、筛选与切片  
- filter -- 接收Lambda，从流中排除某些元素  
- limit -- 截断流，使其元素不超过给定数量，并将流短路 
- skip -- 跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回空流。 
- distinct -- 筛选，通过流所生成元素的hashCode() 和 equals() 去除重复元素 （自定义类的对象需要重写hashCode与equals方法） 

2、映射 
- map -- 接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并且将其映射成一个新的元素。 
- flatMap -- 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流 

3、排序 
- sorted() -- 自然排序(Comparable) 
- sorted(Comparator com) -- 定制排序 

4、 查找与匹配 
- allMatch -- 检查是否匹配所有元素 返回Boolean值 
- anyMatch -- 检查是否至少匹配一个元素 返回Boolean值 
- noneMatch -- 检查是否没有匹配元素 返回Boolean值 
- findFirst -- 返回第一个元素 返回Optional 
- findAny -- 返回当前流的任意一个元素 
- count -- 返回流中元素的总个数
- max -- 返回流中最大值
- min -- 返回流中最小值 

5、归约 
- reduce(T identity, BinaryOperator) / reduce(BinaryOperator) -- 可以将流中元素反复结合起来，得到一个值。 

6、收集 
- collect(Collectors.xxx) -- 将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法  
  
Collectors. 
- toMap 
- toList
- toSet
- toConcurrentMap
- toCollection(HashMap::new) 
- counting -- 总数 
- averagingXxx -- 平均值 
- groupingBy -- 分组，返回Map
- groupingBy(groupingBy) -- 多级分组 
- partitioningBy -- 分区 
- summarizingXxx -- 将对象或属性转换为一个对象（包含平均值、最大值等） 
- joining -- 连接 

### 并行流与顺序流 
**并行流** 
把一个内容分成多个数据块（底层Fork/Join框架--拆分任务处理再合并 类似归并排序），并用不同的线程分别处理每个数据块的流  
Stream API可以声明性地通过parallel() 与 sequential() 在并行流与顺序流之间进行切换  

Fork/Join框架与传统线程池的区别  
采用“工作窃取”模式，当当前线程的子任务处理完了，会从别的线程“窃取”子任务来处理，以提高线程利用率，提高效率  

## Optional类
Optional<T> 类是一个容器类，代表一个值存在或不存在。原来用null表示一个值不存在，现在Optional可以更好的表达这个概念。并且可以避免空指针异常。

常用方法 
- Optional.of(T t) ：创建一个 Optional 实例 
- Optional.empty() ：创建一个空的Optional 实例 
- .ofNullable(T t) ：若 t 不为null，创建 Optional 实例，否者创建空实例 
- .isPresent() ：判断是否包含值 
- .ofElse(T t) ：如果调用对象包含值，返回该值，否则返回 t 
- orElseGet(Supplier s) ：如果调用对象包含值，返回该值，否则返回 s 获取的值 
- map(Function f) ：如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty() 
- flatMap(Function mapper) ：与map类似，要求返回值必须是Optional 

## 接口中的默认方法与静态方法 
**接口中的默认方法**  
接口默认方法的“类优先”原则  
若一个接口中定义了一个默认方法，而另一个父类或接口中又定义了一个同名方法时  
- 选择父类中的方法。如果一个父类提供了具体的实现，那么接口中具有相同名字和参数的默认方法会被忽略 
- 接口冲突。如果一个父接口提供一个默认方法，而另一个接口也提供了一个具有相同名称和参数列表的方法（不管方法是否是默认方法），
那么必须覆盖方法（选择覆盖哪个接口的方法，相当于指定使用哪个接口的方法）来解决冲突

## 新时间日期API 
java.time 包及子包  
相对之前的时间日期api，现在的是不会有线程安全问题的，且使用起来没有那么麻烦  

## 重复注解与类型注解 
Java8对注解处理提供了两点改进：可重复注解及可用于类型的注解  




