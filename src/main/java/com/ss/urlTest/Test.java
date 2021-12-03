package com.ss.urlTest;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ChengXiao
 * @date 2021/11/13 11:59
 **/
public class Test {

    public static void main(String[] args) {

//        testUrl("http://1.3.3.3/test");
        //最好使用下面这个，上面那个超时时间不定，所以可能会导致卡住的情况
        List<String> urlList = new ArrayList<String>(){{
            add("https://oms.cloud.suncentgroup.com/");
            add("https://pdm.cloud.suncentgroup.com/");
            add("https://ifs.cloud.suncentgroup.com/");
        }};
        testUrlWithTimeOut(urlList, 5000);
    }

    public static void testUrlWithTimeOut(List<String> urlString, int timeOutMillSeconds){
        urlString.forEach(v -> {
            long lo = System.currentTimeMillis();
            URL url;
            try {
                url = new URL(v);
                URLConnection co =  url.openConnection();
                co.setConnectTimeout(timeOutMillSeconds);
                co.connect();
                System.out.println(v + " 连接可用");
            } catch (Exception e1) {
                System.out.println(v + "连接打不开!");
            }
        });
    }

}
