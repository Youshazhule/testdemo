package com.ss.beancopy;

import cn.hutool.core.bean.BeanUtil;
import lombok.Data;

/**
 * @author ChengXiao
 * @date 2021/8/30 16:47
 **/
public class Test {

    public static void main(String[] args) {
        A a = new A();

        C c = new C();
        c.setName("张三");
        BeanUtil.copyProperties(c, a);

        B b = new B();
        b.setAge("18");
        BeanUtil.copyProperties(b, a);

        System.out.println(a);
    }
}

@Data
class A{
    private String name;
    private String age;
}
@Data
class B{
    private String age;
}
@Data
class C{
    private String name;
}
