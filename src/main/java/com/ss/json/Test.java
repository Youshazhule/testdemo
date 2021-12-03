package com.ss.json;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author ChengXiao
 * @date 2021/10/12 12:07
 **/
public class Test {

    public static void main(String[] args) {
//        String s = "{\"page\":{\"data_total\":1706,\"page_total\":43,\"page_no\":43,\"page_size\":40},\"code\":0,\"data\":{\"accepted\":[{\"number\":\"1Z4E2W090310050670\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 22:25:25\",\"tt\":\"2021-10-11 21:50:20\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"9214490273564486471725\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 19:25:26\",\"tt\":\"2021-10-11 21:11:09\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"1ZF375V7YW00406090\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 19:25:25\",\"tt\":\"2021-10-11 21:05:13\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"1Z3Y18820305048577\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 19:25:25\",\"tt\":\"2021-10-11 21:38:57\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"1Z3Y18820302895130\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 19:25:25\",\"tt\":\"2021-10-11 21:27:44\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"1Z3Y18820303080697\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 19:25:25\",\"tt\":\"2021-10-11 22:08:31\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"9200190306403905917983\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 18:25:27\",\"tt\":\"2021-10-11 21:53:30\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"9200190306403905918072\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 18:25:27\",\"tt\":\"2021-10-11 21:50:33\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"9200190306403905918089\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 18:25:27\",\"tt\":\"2021-10-11 21:43:53\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"9200190306403905917990\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 18:25:27\",\"tt\":\"2021-10-11 21:55:13\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"420212289361269903507405248414\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 18:25:26\",\"tt\":\"2021-10-11 21:27:57\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"9200190306403905917426\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 18:25:26\",\"tt\":\"2021-10-11 20:59:47\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"9200190306403905918010\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 18:25:26\",\"tt\":\"2021-10-11 21:11:26\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"9200190306403905918041\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 18:25:26\",\"tt\":\"2021-10-11 21:39:00\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"9200190306403905905638\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 18:25:26\",\"tt\":\"2021-10-11 22:16:13\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"420757059361269903507405073849\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 18:25:25\",\"tt\":\"2021-10-11 21:52:44\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"420474299361269903507405065349\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 18:25:25\",\"tt\":\"2021-10-11 21:55:00\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"92748902711539543405003625\",\"w1\":100052,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 18:25:25\",\"tt\":\"2021-10-11 20:00:52\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"420577309374869903507407188257\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 18:25:25\",\"tt\":\"2021-10-11 20:59:09\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"9214490273564486468541\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 17:25:25\",\"tt\":\"2021-10-11 22:09:09\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"9200190306403905918102\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 16:25:25\",\"tt\":\"2021-10-11 21:33:35\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"9200190306403905918096\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 16:25:25\",\"tt\":\"2021-10-11 21:25:32\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"1Z4X554V0321082209\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-12 04:25:24\",\"tt\":\"2021-10-11 21:45:58\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":5,\"tag\":null,\"refer_key\":null},{\"number\":\"92748902711539543404675748\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-02 12:54:26\",\"tt\":\"2021-10-11 23:58:24\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":3,\"tag\":null,\"refer_key\":null},{\"number\":\"92748902711539543404613009\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-02 12:53:42\",\"tt\":\"2021-10-11 23:58:24\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":3,\"tag\":null,\"refer_key\":null},{\"number\":\"92748902711539543404564691\",\"w1\":100003,\"b\":0,\"w2\":0,\"c\":0,\"e\":0,\"rt\":\"2021-09-02 12:53:34\",\"tt\":\"2021-10-11 23:58:24\",\"ps\":0,\"st\":null,\"sr\":0,\"ir\":false,\"ts\":true,\"mc\":3,\"tag\":null,\"refer_key\":null}]}}";
//        Track17UnfoundResponse response = JSONObject.parseObject(s, Track17UnfoundResponse.class);
//        System.out.println(response);

//        String s = "Shipper created a label/information/sent to/";
//        String s1 = "Shipper created a label, UPS has not received the package yet.";
//        List<String> list = Arrays.asList(s.split("/"));
//        list.forEach(v -> {
//            System.out.println(v);
//            if (s1.contains(v)) {
//            }
//        });
//        System.out.println(list);

//        String[] strings = {"Shipper created a label", "information", "sent to"};
//        String s = "Shipper created a label, UPS has not received the package yet.";
//        for (String v : strings) {
//            if (s.contains(v)) {
//                System.out.println("ssssss");
//            }
//        }

        String strCom = " JAVA编程 词典 ";
        // 定义字符串
        String str = strCom.trim();
        // 去除字符串前后的空格
        System.out.println("未去除前后空格的字符串:"+strCom);
        System.out.println("去除前后空格后的字符串:"+str);

        String s = "'sds gdasda" + "\n" + "edaeafd'";
        System.out.println("转换前："+s);
        s = s.replaceAll("\r|\n", "");
        System.out.println("转换后："+s);
    }

    private static void handler(String v) {
        System.out.println(v);
    }
}
class Track17UnfoundResponse {

    private int code;
    private Data data;
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Track17UnfoundResponse{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
class Data {

    private List<Accepted> accepted;
    public void setAccepted(List<Accepted> accepted) {
        this.accepted = accepted;
    }
    public List<Accepted> getAccepted() {
        return accepted;
    }

    @Override
    public String toString() {
        return "Data{" +
                "accepted=" + accepted +
                '}';
    }
}
class Accepted {

    private String number;
    private int w1;
    private int b;
    private int w2;
    private int c;
    private int e;
    private Date rt;
    private Date tt;
    private Date pt;
    private int ps;
    private Date st;
    private int sr;
    private boolean ir;
    private boolean ts;
    private int mc;
    private String tag;
    public void setNumber(String number) {
        this.number = number;
    }
    public String getNumber() {
        return number;
    }

    public void setW1(int w1) {
        this.w1 = w1;
    }
    public int getW1() {
        return w1;
    }

    public void setB(int b) {
        this.b = b;
    }
    public int getB() {
        return b;
    }

    public void setW2(int w2) {
        this.w2 = w2;
    }
    public int getW2() {
        return w2;
    }

    public void setC(int c) {
        this.c = c;
    }
    public int getC() {
        return c;
    }

    public void setE(int e) {
        this.e = e;
    }
    public int getE() {
        return e;
    }

    public void setRt(Date rt) {
        this.rt = rt;
    }
    public Date getRt() {
        return rt;
    }

    public void setTt(Date tt) {
        this.tt = tt;
    }
    public Date getTt() {
        return tt;
    }

    public void setPt(Date pt) {
        this.pt = pt;
    }
    public Date getPt() {
        return pt;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }
    public int getPs() {
        return ps;
    }

    public void setSt(Date st) {
        this.st = st;
    }
    public Date getSt() {
        return st;
    }

    public void setSr(int sr) {
        this.sr = sr;
    }
    public int getSr() {
        return sr;
    }

    public void setIr(boolean ir) {
        this.ir = ir;
    }
    public boolean getIr() {
        return ir;
    }

    public void setTs(boolean ts) {
        this.ts = ts;
    }
    public boolean getTs() {
        return ts;
    }

    public void setMc(int mc) {
        this.mc = mc;
    }
    public int getMc() {
        return mc;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return "Accepted{" +
                "number='" + number + '\'' +
                ", w1=" + w1 +
                ", b=" + b +
                ", w2=" + w2 +
                ", c=" + c +
                ", e=" + e +
                ", rt=" + rt +
                ", tt=" + tt +
                ", pt=" + pt +
                ", ps=" + ps +
                ", st=" + st +
                ", sr=" + sr +
                ", ir=" + ir +
                ", ts=" + ts +
                ", mc=" + mc +
                ", tag='" + tag + '\'' +
                '}';
    }
}
