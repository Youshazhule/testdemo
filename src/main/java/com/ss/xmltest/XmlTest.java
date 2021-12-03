package com.ss.xmltest;

import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

public class XmlTest {

    public static void main(String[] args) throws JAXBException, IOException {
//        List<DemoA> list = Lists.newArrayList();
//
//        DemoA demoA = new DemoA();
//        DemoB demoB = new DemoB();
//        demoB.setNote("");
//        demoB.setSubModel("123456");
//        demoB.setOptions(null);
//        demoA.setDemoB(demoB);
//        list.add(demoA);
//
//        DemoA demo2 = new DemoA();
//        DemoB demo1 = new DemoB();
//        demo1.setNote("MES");
//        demo1.setSubModel("9999");
//        demo2.setDemoB(demo1);
//        list.add(demo2);
////        list.forEach(l -> System.out.println(l));
//        matchingOtherAttributes(list);
//        list.forEach(v -> System.out.println(v));

        String path = "F:\\test.xml";
        Object object = XmlUtils.xmlToBean(path,ProcessingReport.class);
        ProcessingReport p = (ProcessingReport)object;
        System.out.println(p);
    }

    public static void matchingOtherAttributes(List<DemoA> list) {
        list.forEach(val -> {
            StringBuilder builder = new StringBuilder();
            DemoB d = val.getDemoB();
            // 反射获取类的属性包括属性值
            Field[] fields = d.getClass().getDeclaredFields();

            String otherAttributes = "";
            for (Field field : fields) {
                // 访问私有属性
                field.setAccessible(true);
                try {
                    if (Optional.ofNullable(field.get(d)).isPresent() && StringUtils.isNoneBlank(field.get(d).toString())) {
                        otherAttributes = builder.append(field.getName()).append("=").append(field.get(d).toString()).append(";").toString();
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            val.setOtherAttributes(otherAttributes);
        });
    }
}
