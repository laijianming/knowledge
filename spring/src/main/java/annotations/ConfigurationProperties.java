package annotations;

import java.lang.annotation.*;

/**
 * 读取配置文件内容注解
 * @author jianming
 * @create 2020-05-15-18:03
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConfigurationProperties {
    String value() default "application.properties";
}
