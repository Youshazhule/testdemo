package com.ss.j8map;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author ChengXiao
 * @date 2021/9/24 16:27
 **/
public class MapTest {

    public static void main(String[] args) {
        // map 新特性
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (int i = 0; i < 6; i++) {
            map.put(i, "val_" + i);
        }
        map.put(10, null);

        // 1:遍历
//        map.forEach((key, value) -> System.out.println(key + ":" + value));

        // 2:V getOrDefault(key,defaultValue):获取key值,如果key不存在则用defaultValue 避免空指针问题
//        System.out.println("3-->" + map.getOrDefault(3, "val_66"));//3-->val_3
//        System.out.println("10-->" + map.getOrDefault(10, "val_66"));//10-->null
//        System.out.println("11-->" + map.getOrDefault(11, "val_66"));//11-->val_66

        // 3:V putIfAbsent(K key, V value):根据key获取value,如果获取不到(null)则增加key-value
        // 如果获取到value, 而且oldValue不等于null则不进行value覆盖，返回oldValue
//        System.out.println(map.putIfAbsent(3, "val_66"));//val_3
//        System.out.println(map.putIfAbsent(10, "val_66"));//null
//        System.out.println(map.putIfAbsent(11, "val_66"));//null
//        System.out.println(map.get(3) + "--" + map.get(10) + "--" + map.get(11));//val_3--val_66--val_66


        // 5:boolean replace(K key, V oldValue, V newValue):根据key匹配node,如果value相同则替换newValue
//        map.put(11, null);
//        map.replace(3, "val_3", "new_val_3");
//        map.replace(10, "val_66", "new_val_666666");
//        map.replace(11, null, "new_val_11");
//        System.out.println(map.toString());//{0=val_0, 1=val_1, 2=val_2, 3=val_3, 4=val_4, 5=val_5, 10=val_666666, 11=val_11}


//        String s = "";
//        System.out.println(StringUtils.isBlank(s) ? BigDecimal.ZERO : new BigDecimal(s));

        /** 6:
         * void replaceAll(BiFunction function)：调用此方法时重写BiFunction的Object apply(Object o, Object o2)方法，
         * 其中o为key，o2为value，根据重写方法逻辑进行重新赋值。
         */
        map.replaceAll((key, value) -> {
            if (key == 2) {
                return value + "222";
            }
            return value;
        });
        System.out.println(map.toString());//{0=val_0, 1=val_1, 2=val_2222, 3=val_3, 4=val_4, 5=val_5, 10=val_666666, 11=val_11}
    }
}
