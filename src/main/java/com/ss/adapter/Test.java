package com.ss.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * TypeAdapter自定义序列化对象
 * @author ChengXiao
 * @date 2021/9/13 16:41
 **/
public class Test {

    public static void main(String[] args) {
//        writeObject();
        readObject();
    }

    private static void writeObject() {
        Gson gson = new GsonBuilder().create();
        User user = new User("qiububai", 26);
        String s = gson.toJson(user);
        System.out.println(s);
    }

    private static void readObject() {
        Gson gson = new Gson();
        String userString = "[{\"name\":\"RedRose\",\"age\":28}]";
        User user = gson.fromJson(userString, User.class);
        System.out.println(user);
    }
}

