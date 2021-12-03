package com.ss.j8stream;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 测试java8 处理集合根据某属性分组并汇总其中的一个属性
 * @author ChengXiao
 * @date 2021/9/25 17:58
 **/
public class Test {

    public static void main(String[] args) {
        List<Customer> list = new ArrayList<Customer>() {{
            add(new Customer("A", 1, "123456"));
            add(new Customer("A", 2, "123456"));
            add(new Customer("C", 3, "123456"));
            add(new Customer("D", 4, "123456"));
            add(new Customer("D", 5, "123456"));
        }};

        List<Customer> merge = merge(list);
        System.out.println(merge);



        List<String> arrayList = Arrays.asList("aa","bb","cc","aa");
        Stream<String> stream = arrayList.stream();

        // filter 接收Lambda,从流中排除某些元素
        stream.filter(e -> !e.equals("aa")).forEach(System.out :: println); //bb cc
        System.out.println("==========");
        // 截断流，使其元素不超过给定数量
        arrayList.stream().limit(2).forEach(System.out :: println); //aa bb
        System.out.println("==========");
        // 跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。
        arrayList.stream().skip(2).forEach(System.out :: println); //cc aa
        System.out.println("==========");
        // 筛选，通过流所生成元素的hashCode()和equals()去除重复元素
        arrayList.stream().distinct().forEach(System.out :: println); //aa bb cc
        // 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
//        stream.map(x -> x.toUpperCase()).forEach(System.out :: println); //AA BB CC AA
        System.out.println("==========");
        List<Integer> integerList = Arrays.asList(2, -9, 0, 22, 6, -1);
        // 产生一个新流，其中按自然顺序排序
        integerList.stream().sorted().forEach(System.out::println);
        System.out.println("==========");
        // 产生一个新流，其中按比较器顺序排序
        integerList.stream().sorted((e1,e2) -> -Integer.compare(e1,e2)).forEach(System.out :: println);
        System.out.println("==========");
        // 检查是否匹配所有元素
        boolean allMatch = integerList.stream().allMatch(e -> e > 0);
        System.out.println(allMatch);
        System.out.println("==========");
        // 检查是否至少匹配一个元素
        boolean anyMatch = integerList.stream().anyMatch(e -> e > 0);
        System.out.println(anyMatch);
        System.out.println("==========");
        // 检查是否没有匹配所有元素
        boolean noneMatch = integerList.stream().noneMatch(e -> e > 100);
        System.out.println(noneMatch);
        System.out.println("==========");
        Integer first = integerList.stream().sorted().findFirst().get();
        System.out.println(first);
        Integer any = integerList.parallelStream().findAny().get();
        System.out.println(any);
        System.out.println("==========");
        // 返回流中元素总数
        long count = integerList.stream().filter(e -> e > 0).count();
        System.out.println(count);
        System.out.println("==========");
        // 返回流中最大值和最小值
        Integer max = integerList.stream().max(Integer :: compare).get();
        System.out.println(max);
        Integer min = integerList.stream().min((e1, e2) -> Integer.compare(e1, e2)).get();
        System.out.println(min);
        System.out.println("==========");
        // 可以将流中元素反复结合起来，得到一个值。返回T
        Integer reduce1 = integerList.stream().reduce(0, Integer::sum);
        Integer reduce11 = integerList.stream().reduce(5, (e1,e2) -> e1 + e2);
        System.out.println(reduce1);
        System.out.println(reduce11);
        Integer reduce2 = integerList.stream().reduce(Integer::sum).get();
        System.out.println(reduce2);
    }


    /**
     * @Description 使用Java8的流进行处理，将name相同的对象进行合并，将value属性求和
     * @Title merge
     * @Param [list]
     * @Return java.util.List<Pool>
     * @Author Louis
     */
    public static List<Customer> merge(List<Customer> list) {
        return new ArrayList<>(list.stream()
                .collect(Collectors.toMap(Customer::getName, a -> a, (o1, o2) -> {
                    o1.setAge(o1.getAge() + o2.getAge());
                    return o1;
                })).values());
    }
}

@Data
class Customer {
    String name;
    Integer age;
    String phone;

    public Customer(String name, Integer age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }
}