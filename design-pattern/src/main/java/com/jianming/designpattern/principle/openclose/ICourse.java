package com.jianming.designpattern.principle.openclose;

/**
 * 接口定义抽象方法，扩展时不应修改接口
 * @author jianming
 * @create 2020-04-14-5:27
 */
public interface ICourse {
    Integer getId();
    String getName();
    Double getPrice();
}
