package com.ss.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author ChengXiao
 * @date 2021/9/13 16:42
 **/
public class UserJsonAdapter extends TypeAdapter<User> {

    @Override
    public void write(JsonWriter out, User value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.beginObject();
        out.name("name").value("已被序列化为HCX");
        out.name("age").value(28);
        out.endObject();
    }

    @Override
    public User read(JsonReader in) throws IOException {
//        if (in.peek() == JsonToken.NULL) {
//            in.nextNull();
//        }
//        String name = "";
//        int age = 0;
//        in.beginObject();
//        while (in.hasNext()) {
//            switch (in.nextName()) {
//                case "name":
//                    name = in.nextString();
//                    name = "Hcx";
//                    break;
//                case "age":
//                    age = in.nextInt();
//                    break;
//            }
//        }
//        in.endObject();
//        return new User(name, age);

        /**
         * 解析数组
         */
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
        }
        String name = "";
        int age = 0;
        in.beginArray();
        while (in.hasNext()) {
            in.beginObject();
            while (in.hasNext()) {
                switch (in.nextName()) {
                    case "name":
                        name = in.nextString();
                        name = "Hcx";
                        break;
                    case "age":
                        age = in.nextInt();
                        break;
                }
            }
            in.endObject();
        }
        in.endArray();
        return new User(name, age);
    }
}
