package com.ss.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;

/**
 * @author ChengXiao
 * @date 2021/11/25 18:29
 **/
public class User {

    private String nameInfo;

    private String ageInfo;

    public String getNameInfo() {
        return nameInfo;
    }

    public void setNameInfo(String nameInfo) {
        this.nameInfo = nameInfo;
    }

    public String getAgeInfo() {
        return ageInfo;
    }

    public void setAgeInfo(String ageInfo) {
        this.ageInfo = ageInfo;
    }

    public static void main(String[] args) {
        // 生产环境中，config要做singleton处理，要不然会存在性能问题
        User user = new User();
        user.setNameInfo("coder");
        user.setAgeInfo("28");
        SerializeConfig config = new SerializeConfig();
        config.propertyNamingStrategy = PropertyNamingStrategy.SnakeCase;
        String json = JSON.toJSONString(user, config);
        System.out.println(json);
    }
}
