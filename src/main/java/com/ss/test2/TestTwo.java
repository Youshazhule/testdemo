package com.ss.test2;

/**
 * @author ChengXiao
 * @date 2021/4/14 15:04
 **/
public class TestTwo {

    public static void main(String[] args) {
        new Pig(4);
    }
}

class Animal{

    void eat() {
        System.out.println("Animal eat()");
    }

    Animal() {
        System.out.println("Animal 构造器 before=== ");
        eat();
        System.out.println("Animal 构造器 after===");
    }
}

class Pig extends Animal{

    private int a = 2;

    Pig(int i) {
        a = i;
        System.out.println("Pig 构造器 a = " + a);
    }

    @Override
    void eat() {
        System.out.println("Pig eat() a = " + a);
    }
}
