package com.ss.simpleDateFormat;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author ChengXiao
 * @date 2021/6/23 12:15
 **/
public class Convert {

    public static void main(String[] args) throws ParseException {
        // 第一种线程不安全
        String a = "06/12/2021";
//        SimpleDateFormat old = new SimpleDateFormat("dd/MM/yyyy");
//        SimpleDateFormat now = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = old.parse(a);
//        String format = now.format(date);
//        System.out.println(format);

        // 第二种用hutool工具线程不安全
        DateTime parse = DateUtil.parse(a, "dd/MM/yyyy");
        String format = DateUtil.format(parse, "yyyy-MM-dd");
        System.out.println(format);

        // 第三种线程安全
        LocalDate localDateOld = LocalDate.parse(a, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String formatNow = localDateOld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(formatNow);
    }
}
