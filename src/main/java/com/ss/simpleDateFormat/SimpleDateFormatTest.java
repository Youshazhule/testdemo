package com.ss.simpleDateFormat;

import com.google.common.collect.Lists;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author ChengXiao
 * @date 2021/6/23 15:07
 **/
public class SimpleDateFormatTest {

    /**
     * simpleDateFormat线程不安全
     * @param args
     */
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 100, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(1000), Executors.defaultThreadFactory());

        List<String> list = Lists.newArrayList();
        list.add("06/12/2021");
        list.add("06/13/2021");
        list.add("06/14/2021");
        list.add("06/15/2021");
        list.add("06/16/2021");
        list.add("06/17/2021");
        list.add("06/18/2021");
        list.forEach(v -> {
            LocalDate localDateOld = LocalDate.parse(v, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            String formatNow = localDateOld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println(formatNow);
        });

//        for (int i = 0; i < 1; i++) {
//            new Thread(() -> {
//                list.forEach(v -> {
//                    LocalDate localDateOld = LocalDate.parse(v, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//                    v = localDateOld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//                });
//                System.out.println(list);
//            }).start();
//        }

//            poolExecutor.execute(new Runnable() {
//                @Override
//                public void run() {
//                    String dateString = simpleDateFormat.format(new Date());
//                    try {
//                        Date parseDate = simpleDateFormat.parse(dateString);
//                        String dateString2 = simpleDateFormat.format(parseDate);
//                        System.out.println(dateString.equals(dateString2));
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
    }
}
