package com.ss.test14;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.Data;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.*;

/**
 * 上界通配符测试
 * @author ChengXiao
 * @date 2021/7/27 13:10
 **/
public class ExtendsTest {

    public static void main(String[] args) {
        OrderVo orderVo = new OrderVo();
        orderVo.setName("123");

        OrderVo orderVo2 = new OrderVo();
        orderVo2.setName("456");

        Order order = new Order();
        order.setName("999");

        System.out.println(orderVo);

        OrderServiceImpl orderService = new OrderServiceImpl();
        // 检查两个对象差异
        orderService.test(orderVo, orderVo2);

        HashSet<Boolean> hashSet = Sets.newHashSet();
        List<Boolean> list = Lists.newArrayList();
        list.add(false);
        list.add(false);
//        hashSet.addAll(list);
//        if (hashSet.size() > 1) {
//            System.out.println("重复");
//        }
        boolean result = list.stream().allMatch(v -> !v);
        System.out.println("=="+result);
        boolean contains = list.contains(false);
        System.out.println(contains);

        Map<String, Integer> map = Maps.newHashMap();
        map.put("123", 1);
        map.put("123", 2);
        System.out.println(map);
    }
}

@Data
class OrderVo extends Order{
    // OrderVO
    private String name;
}
@Data
class Order{
    // Order
    private String name;
}

interface OrderService{

    <E extends Order> E test(E arg1, E arg2);
}
class OrderServiceImpl implements OrderService{

    @Override
    public <E extends Order> E test(E arg1, E arg2) {
        Map<String, List<Object>> stringListMap = compareFields(arg1, arg2, null);
        System.out.println(stringListMap);
        //.....
        return arg1;
    }
    /**
     * 比较两个实体属性值，返回一个map以有差异的属性名为key，value为一个Map分别存oldObject,newObject此属性名的值
     * @param obj1 进行属性比较的对象1
     * @param obj2 进行属性比较的对象2
     * @param ignoreArr 忽略比较的字段
     * @return 属性差异比较结果map
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, List<Object>> compareFields(Object obj1, Object obj2, String[] ignoreArr) {
        try {
            Map<String, List<Object>> map = Maps.newHashMap();
            List<String> ignoreList = null;
            if (ignoreArr != null && ignoreArr.length > 0) {
                // array转化为list
                ignoreList = Arrays.asList(ignoreArr);
            }
            if (obj1.getClass() == obj2.getClass()) {// 只有两个对象都是同一类型的才有可比性
                Class clazz = obj1.getClass();
                // 获取object的属性描述
                PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz,
                        Object.class).getPropertyDescriptors();
                for (PropertyDescriptor pd : pds) {// 这里就是所有的属性了
                    String name = pd.getName();// 属性名
                    if(ignoreList != null && ignoreList.contains(name)){// 如果当前属性选择忽略比较，跳到下一次循环
                        continue;
                    }
                    Method readMethod = pd.getReadMethod();// get方法
                    // 在obj1上调用get方法等同于获得obj1的属性值
                    Object o1 = readMethod.invoke(obj1);
                    // 在obj2上调用get方法等同于获得obj2的属性值
                    Object o2 = readMethod.invoke(obj2);
                    if(o1 instanceof Timestamp){
                        o1 = new Date(((Timestamp) o1).getTime());
                    }
                    if(o2 instanceof Timestamp){
                        o2 = new Date(((Timestamp) o2).getTime());
                    }
                    if(o1 == null && o2 == null){
                        continue;
                    }else if(o1 == null && o2 != null){
                        List<Object> list = Lists.newArrayList();
                        list.add(o1);
                        list.add(o2);
                        map.put(name, list);
                        continue;
                    }
                    if (!o1.equals(o2)) {// 比较这两个值是否相等,不等就可以放入map了
                        List<Object> list = Lists.newArrayList();
                        list.add(o1);
                        list.add(o2);
                        map.put(name, list);
                    }
                }
            }
            return map;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
