package date;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * Java8 提供了一系列线程安全的时间操作API，在java.time 包或子包下
 *  LocalDate、LocalTime、LocalDateTime
 *    的实例是不可变的对象，分别表示使用ISO-8601日历系统的日期、时间、日期和时间。
 * @author jianming
 * @create 2020-05-15-13:39
 */
public class Java8DateApi {

    /**
     * 样例：对时间的转换
     */
    @Test
    public void test() {
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate parse = LocalDate.parse("20200515", dft);
        System.out.println(parse);
    }

    /**
     * LocalDate、LocalTime、LocalDateTime
     */
    @Test
    public void test1() {
        // 获取当前系统时间 LocalDateTime
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("ldt = " + ldt);

        LocalDateTime ldt1 = LocalDateTime.of(2020, 05, 15, 05, 20, 00);
        System.out.println("ldt1 = " + ldt1);

        LocalDateTime localDateTime = ldt.plusYears(2);
        System.out.println("localDateTime = " + localDateTime);
    }

    /**
     * Instant 时间戳（以Unix元年：1970年1月1日 00:00:00 到某个时间之间的毫秒值）
     */
    @Test
    public void test2() {
        // 默认获取的是 UTC 时区
        Instant now = Instant.now();
        System.out.println("now = " + now);
        // 做时间偏移
        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println("offsetDateTime = " + offsetDateTime);
        // 获取毫秒
        System.out.println("获取毫秒 " + now.toEpochMilli());
        // 相较于 Unix元年做操作
        Instant instant = Instant.ofEpochSecond(60);
        System.out.println("instant = " + instant);
    }

    /**
     * 对两个时间进行计算
     *  Duration ： 计算两个“时间”之间的间隔
     *  Period ： 计算两个“日期”之间的间隔
     */
    @Test
    public void test3() {
        // 计算两个“时间”之间的间隔
        Instant ins1 = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant ins2 = Instant.now();
        Duration duration = Duration.between(ins1, ins2);
        System.out.println("Instant = " + duration.toMillis());

        //----------------------------------------------

        LocalTime localTime1 = LocalTime.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime localTime2 = LocalTime.now();
        duration = Duration.between(localTime1, localTime2);
        System.out.println("LocalTime = " + duration.toMillis());

        // 计算两个“日期”之间的间隔
        LocalDate localDate1 = LocalDate.of(2019, 1, 1);
        LocalDate localDate2 = LocalDate.now();
        Period period = Period.between(localDate1, localDate2);
        System.out.println("period = " + period);
    }

    /**
     * 时间矫正器
     * TemporalAdjuster 可以将日期调整到“下个周日”等操作
     * TemporalAdjusters 该类通过静态方法提供了大量的常用TemporalAdjuster的实现
     */
    @Test
    public void test4() {
        LocalDateTime localDateTime = LocalDateTime.now();
        // 下一个周五
        LocalDateTime ldt2 = localDateTime.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        System.out.println("ldt2 = " + ldt2);
    }

    /**
     * 时间格式的转换
     */
    @Test
    public void test5() {
        DateTimeFormatter dft = DateTimeFormatter.ISO_DATE;
        LocalDateTime localDateTime = LocalDateTime.now();

        String date = localDateTime.format(dft);
        System.out.println("date = " + date);
    }

    /**
     * ZonedDate、ZonedTime、ZonedDateTime
     *  对时区的操作
     * jianming
     */
    @Test
    public void test6() {
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime zonedDateTime = ldt.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println("zonedDateTime = " + zonedDateTime);
    }

}
