package com.ss.j8stream;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * JAVA8 Stream按多属性分组
 * @author ChengXiao
 * @date 2021/6/28 11:53
 **/
public class replaceAllTest {

    public static void main(String[] args) {
//        String str = "$32.97";
//        String s = str.replaceAll("[\\pS‘'“”]", "");
//        System.out.println(s);
//
//        String amount = "1.11";
//        String s1 = amount.replaceAll("[\\pS‘'“”]", "");
//        System.out.println(s1);

//        BigDecimal total=new BigDecimal("0.00");
//        for (int i = 0; i < 5; i++) {
//            BigDecimal number= new BigDecimal("1.11");
//            total= total.add(number) ;
//        }
//        System.out.println(total);

        List<A> aList = Lists.newArrayList();
        A a = new A();
        a.setDate("2021-05-01");
        a.setSellerCode("LG6");
        a.setTotal(new BigDecimal(100));
        aList.add(a);

        A a4 = new A();
        a4.setDate("2021-05-01");
        a4.setSellerCode("LG6");
        a4.setTotal(new BigDecimal(300));
        aList.add(a4);

        A a2 = new A();
        a2.setDate("2021-05-02");
        a2.setSellerCode("LG6");
        a2.setTotal(new BigDecimal(200));
        aList.add(a2);

        A a3 = new A();
        a3.setDate("2021-05-01");
        a3.setSellerCode("US");
        a3.setTotal(new BigDecimal(150));
        aList.add(a3);

        Map<String, Map<String, List<A>>> aMap = Maps.newHashMap();
        aList.stream().collect(Collectors.groupingBy(A::getSellerCode, Collectors.groupingBy(A::getDate)))
            .forEach(aMap::put);

        aMap.forEach((x, y) -> {// 遍历店铺
            AtomicReference<BigDecimal> totalAmount = new AtomicReference<BigDecimal>(new BigDecimal(0));
            y.forEach((k,v) -> {// 遍历日期
                // 查表获取均摊率
                v.forEach(data -> {
                    totalAmount.updateAndGet(v1 -> v1.add(data.getTotal()));
                });
            });
            // 查汇率表获取汇率 以店铺前两位areaCode+format(yyyy-MM)
            String exchangeRate = "1.1";
            // 计算店铺均摊费用总值
            BigDecimal shareTheTotalCost = new BigDecimal(exchangeRate).multiply(totalAmount.get());

            // 查销售表
            y.forEach((k, v) -> {
                System.out.println(x + " " + k);
                // 获取average均摊率
                System.out.println(v);
                // 批量插入
            });

        });

    }
}

@Data
class A{
    private String sellerCode;
    private String date;
    private BigDecimal total;
}
