package com.ss.datetest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;

/**
 * 特殊时间转格式化
 * @author ChengXiao
 * @date 2021/7/13 11:43
 **/
public class DateTest {

    public static void main(String[] args) {
//        String str = "06/29/2021";
        String str = "07/04/2021";
        LocalDate parse;
        if (str.contains("/")) {
            if (isLegalDate(str)) {
                parse = LocalDate.parse(str, DateTimeFormatter.ofPattern("M/d/yy"));
            } else {
                parse = LocalDate.parse(str, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            }
        } else {
            parse = LocalDate.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        String format = parse.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println(format);
        boolean legalDate = isLegalDate(str);
        System.out.println(legalDate);

        String time = "Tue Jun 01 04:01:01 CST 2021";
        Date date = parse(time, "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));


        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = sdf1.parse("2018-01-22T09:12:43.083Z");//拿到Date对象
            String str1 = sdf2.format(date1);//输出格式：2017-01-22 09:28:33
            System.out.println(str1);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }


    private static boolean isLegalDate(String sDate) {
        DateFormat formatter = new SimpleDateFormat("M/d/yy");
        try {
            Date date = formatter.parse(sDate);
            return sDate.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }
    }

    private static Date parse(String str, String pattern, Locale locale) {
        if(str == null || pattern == null) {
            return null;
        }
        try {
            return new SimpleDateFormat(pattern, locale).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * utc 时间格式转换正常格式 2018-08-07T03:41:59Z
     *
     * @param utcTime 时间
     * @return
     */
    public static String formatStrUTCToDateStr(String utcTime) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.ZZZ'Z'");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeZone utcZone = TimeZone.getTimeZone("UTC");
        sf.setTimeZone(utcZone);
        Date date = null;
        String dateTime = "";
        try {
            date = sf.parse(utcTime);
            dateTime = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }
}
