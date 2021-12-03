package com.ss.jsonReader;

import com.google.gson.stream.JsonReader;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author ChengXiao
 * @date 2021/9/13 14:16
 **/
public class Test {

    public static void main(String[] args) throws IOException {
        String jsonString = "{\"store\":\"107\"}";
        JsonReader jsonReader = new JsonReader(new StringReader(jsonString));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "store" :
                    String s = jsonReader.nextString();
                    System.out.println(s);
//                    if (StringUtils.isNotBlank(jsonReader.nextString())) {
//                        System.out.println(jsonReader.nextString());
//                    }
                    break;
            }
        }
        jsonReader.endObject();
    }
}
