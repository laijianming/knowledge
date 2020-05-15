package com.jianming.designpattern.principle.interfacesegregation;

/**
 * 接口隔离原则
 *  单个接口功能细化，将不能在含义内通用的功能隔离
 *  例如：能eat行为的animal不一定会fly，所以要将fly行为隔离出去
 * @author jianming
 * @create 2020-04-14-7:26
 */
public interface IEatAnimalAction {
    void eat();
}
