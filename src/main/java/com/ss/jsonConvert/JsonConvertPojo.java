package com.ss.jsonConvert;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * JSON字符串转实体或LIST
 * @author ChengXiao
 * @date 2021/7/15 17:26
 **/
public class JsonConvertPojo {

    public static void main(String[] args) {
        // json转List实体
//        String userString = "[{'name_str':'xiaoming','age':'18'},{'name_str':'hcx','age':'24'}]";
//        List<A> as1 = JSONArray.parseArray(userString, A.class);
//        System.out.println(as);

        // json转实体
//        String userString = "{'name_str':'xiaoming','age':'18'}";
//        JSONObject userJson = JSONObject.parseObject(userString);
//        A user = JSON.toJavaObject(userJson, A.class);
//        System.out.println(user);

        String response = "{'ask':'success','message':'aaa','data':[{'country_code':'CN','country_name':'中国'},{'country_code':'DZ','country_name':'阿尔及利亚'}]}";
//        JSONObject userJson = JSONObject.parseObject(response);
//        A user = JSON.toJavaObject(userJson, A.class);
//        System.out.println(user);
        A a = JsonUtils.getObject(response, A.class);
        a.getData().forEach(v -> {
            System.out.println(v);
        });

//        JSONArray objects = JSONArray.parseArray(response);
//        System.out.println(objects);

        Map<String, Object> map = Maps.newHashMap();
        B b = new B();
        b.setCountryCode("US");
        b.setCountryName("美国");
        map.put("B", b);
        String s = JSON.toJSONString(map);
        System.out.println(s);

        A aa = new A();
        aa.setAsk("123");
        if (Optional.ofNullable(aa).isPresent()) {
            System.out.println("不为空：" + aa.getAsk());
        }
    }


}

@Data
class A{

    @JsonProperty
    private String ask;

    @JsonProperty
    private String message;
    @JsonProperty
    private List<B> data;
}
@Data
class B{
    private String countryCode;
    private String countryName;
}


