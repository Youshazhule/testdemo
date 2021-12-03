package j8map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ArrayListMultimap;
import lombok.Data;
import org.junit.Test;

import java.util.*;
import java.util.function.BiFunction;

/**
 * JAVA8 MAP操作
 */
public class MapMethodsTest {

    /***
     * merge统计汇总
     * @throws JsonProcessingException
     */
    @Test
    public void mapMergeTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<StudentScore> studentScoreList = buildATestList();
        // 按照学生分组，求得每个学生的总分
        // 常规做法
        Map<String, Integer> studentScoreMap = new HashMap<>();
        studentScoreList.forEach(studentScore -> {
            if (studentScoreMap.containsKey(studentScore.getStuName())) {
                studentScoreMap.put(studentScore.getStuName(), studentScoreMap.get(studentScore.getStuName()) + studentScore.getScore());
            } else {
                studentScoreMap.put(studentScore.getStuName(), studentScore.getScore());
            }
        });
        // {李四=228, 张三=215, 王五=235}
        System.out.println(studentScoreMap);

        // merge() 方法
        Map<String, Integer> studentScoreMap2 = new HashMap<>();
        studentScoreList.forEach(studentScore -> studentScoreMap2.merge(
          studentScore.getStuName(),
          studentScore.getScore(),
          Integer::sum));
        // JSON输出{"李四":228,"张三":215,"王五":235}
        System.out.println(objectMapper.writeValueAsString(studentScoreMap2));
    }

    /**
     * 假设有如下需求，我们需要统计一段文字中相关单词出现的次数。
     * TODO:说明：如果 java这个值在 countMap中不存在，那么将会其对应的 value 设置为 1
     * TODO:如果 java 在 countMap 中存在，则会调用第三个参数 remappingFunction 函数方法进行计算
     */
    @Test
    public void mapMergeTest2(){
        Map<String, Integer> countMap = new HashMap();

        // 方式一：常规
//        Integer count = countMap.get("java");
//        if (Objects.isNull(count)) {
//            countMap.put("java", 1);
//        } else {
//            countMap.put("java", count++);
//        }

        // 方式二：JDK8 merge
//        countMap.merge("java", 1, Integer::sum);

        // 方式三：将lambda 函数还原成正常类
        for (int o = 0; o < 10; o++) {
            countMap.merge("java", 1, new BiFunction<Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer oldValue, Integer newValue) {
                    return Integer.sum(oldValue,newValue);
                }
            });
        }

        System.out.println(countMap);
    }

    @Test
    public void mapComputeTest() {
        String k = "key";
        Map<String, Integer> map = new HashMap<String, Integer>() {{
            put(k, 1);
        }};
        // 2
        System.out.println(map.compute(k, (key, oldVal) -> oldVal + 1));
    }

    /**
     * 如果 Map中 key 对应的 value 不存在
     * 则会将 mappingFunction 计算产生的值作为保存为该 key 的 value，并且返回该值。否则不作任何计算，将会直接返回 key 对应的 value。
     */
    @Test
    public void mapComputeIfAbsent() {
        // 创建一个 HashMap
        HashMap<String, Integer> prices = new HashMap<String, Integer>() {{
            put("Shoes", 200);
            put("Bag", 300);
            put("Pant", 150);
        }};

        // 计算 Shirt 的值
        int shirtPrice = prices.computeIfAbsent("Shirt", key -> 280);
        System.out.println("Price of Shirt: " + shirtPrice);

        // 输出更新后的HashMap
        System.out.println("Updated HashMap: " + prices);
    }

    /**
     * 如果 Map中 key 对应的 value 不存在，则会将 mappingFunction 计算产生的值作为保存为该 key 的 value，并且返回该值。
     * 否则不作任何计算，将会直接返回 key 对应的 value。
     * 不会存在NPE问题
     */
    @Test
    public void mapComputeIfAbsent2() {
        Map<String, List<String>> map = new HashMap();
        List<String> classify = map.get("java框架");
        if (Objects.isNull(classify)) {
            classify = new ArrayList<>();
            classify.add("Spring");
            map.put("java框架", classify);
        } else {
            classify.add("Spring");
        }

        map.computeIfAbsent("java框架", key -> new ArrayList<>()).add("SpringBoot");

        // ERROR:会有 NPE 问题
        map.putIfAbsent("java框架", new ArrayList<>()).add("Spring");

        System.out.println(map);
    }

    /**
     * 使用 Google Guava 提供的新集合类型 Multiset，以此快速完成一个键需要映射到多个值的场景
     */
    @Test
    public void mapWithMultiset(){
        ArrayListMultimap<Object, Object> multimap = ArrayListMultimap.create();
        multimap.put("java框架", "Spring");
        multimap.put("java框架", "SpringBoot");
        System.out.println(multimap);// {java框架=[Spring, SpringBoot]}
    }

    /**
     * map 预防空指针问题
     */
    @Test
    public void mapNPEHandler(){
        Map<String, String> map = new HashMap<String, String>() {{
            put("李元芳", "国服第一许仙");
            put("元歌", "国服第一怪兽");
            put("成吉思汗", "国服第一小杨子");
        }};
        // 可能存在 NPE 问题
//        System.out.println(map.get("娜可露露").toUpperCase());

        //第一种 if 判空
//        String value = map.get("娜可露露");
//        if (!Objects.isNull(value)) {
//            System.out.println(value.toUpperCase());
//        }

        // 第二种 条件运算符
//        String value = map.get("娜可露露");
//        value = Objects.isNull(value) ? "" : value;
//        System.out.println(value.toUpperCase());

        // 第三种 JDK8
        // 等同于条件运算符的效果：Objects.isNull(value) ? "" : value;
        String value = map.getOrDefault("娜可露露","");
        System.out.println(value.toUpperCase());


    }

    private List<StudentScore> buildATestList() {
        List<StudentScore> studentScoreList = new ArrayList<>();
        StudentScore studentScore1 = new StudentScore() {{
            setStuName("张三");
            setSubject("语文");
            setScore(70);
        }};
        StudentScore studentScore2 = new StudentScore() {{
            setStuName("张三");
            setSubject("数学");
            setScore(80);
        }};
        StudentScore studentScore3 = new StudentScore() {{
            setStuName("张三");
            setSubject("英语");
            setScore(65);
        }};
        StudentScore studentScore4 = new StudentScore() {{
            setStuName("李四");
            setSubject("语文");
            setScore(68);
        }};
        StudentScore studentScore5 = new StudentScore() {{
            setStuName("李四");
            setSubject("数学");
            setScore(70);
        }};
        StudentScore studentScore6 = new StudentScore() {{
            setStuName("李四");
            setSubject("英语");
            setScore(90);
        }};
        StudentScore studentScore7 = new StudentScore() {{
            setStuName("王五");
            setSubject("语文");
            setScore(80);
        }};
        StudentScore studentScore8 = new StudentScore() {{
            setStuName("王五");
            setSubject("数学");
            setScore(85);
        }};
        StudentScore studentScore9 = new StudentScore() {{
            setStuName("王五");
            setSubject("英语");
            setScore(70);
        }};

        studentScoreList.add(studentScore1);
        studentScoreList.add(studentScore2);
        studentScoreList.add(studentScore3);
        studentScoreList.add(studentScore4);
        studentScoreList.add(studentScore5);
        studentScoreList.add(studentScore6);
        studentScoreList.add(studentScore7);
        studentScoreList.add(studentScore8);
        studentScoreList.add(studentScore9);

        return studentScoreList;
    }

    @Data
    private class StudentScore {
        private String stuName;
        private String subject;
        private Integer score;
    }
}