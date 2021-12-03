package com.ss.listSub;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author ChengXiao
 * @date 2021/7/1 11:28
 **/
public class ListSubTest {

    public static void main(String[] args) {
//        List<Integer> list = Lists.newArrayList();
//        for (int i = 0; i < 9; i++) {
//            list.add(i);
//        }
//        limitListBatchOperation(list, ListSubTest::handlerDB);

        String s = "￥123.1";
        String after = s.replaceAll("[a-zA-Z$￡]","");
        System.out.println(after);
    }

    public static <T> void limitListBatchOperation(List<T> dateList, Consumer<List<T>> consumer) {
        int pageNo = 0;
        int pageSize = 10;
        while (pageNo < dateList.size()) {
            int endIndex = 0;
            if (dateList.size() - pageSize < pageNo) {
                endIndex = dateList.size();
            } else {
                endIndex = pageNo + pageSize;
            }
            List<T> subList = dateList.subList(pageNo, endIndex);
            consumer.accept(subList);
            pageNo = pageNo + pageSize;
        }
    }

    public static void handlerDB(List<Integer> list) {
        System.out.println("执行数据库操作：当前集合：" + list);
    }
}
