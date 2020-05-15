package com.jianming.designpattern.principle.interfacesegregation;

/**
 * Dog的行为有eat和swim，故只需实现eat和swim的接口，而不实现fly接口，则避免了不相干的内容的耦合
 * @author jianming
 * @create 2020-04-14-7:30
 */
public class Dog implements IEatAnimalAction,ISwimAnimalAction {
    @Override
    public void eat() {

    }

    @Override
    public void swim() {

    }
}
