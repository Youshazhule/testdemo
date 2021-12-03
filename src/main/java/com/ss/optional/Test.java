package com.ss.optional;

import lombok.Data;

import java.util.Optional;

/**
 * @author ChengXiao
 * @date 2021/9/7 18:16
 **/
public class Test {

    public static void main(String[] args) throws Exception {
        A a = null;
//        a.setName("123");
        String name = Optional.ofNullable(a).orElse(new A()).getName();
        System.out.println(name == null ? "空" : name);

        A a1 = new A();
        a1.setName("123");
        Optional.ofNullable(a1).ifPresent(v -> {
            System.out.println(v.getName());
        });

    }
}
@Data
class A{
    private String name;
}
