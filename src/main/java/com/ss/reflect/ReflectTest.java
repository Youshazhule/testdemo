package com.ss.reflect;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ChengXiao
 * @date 2021/6/3 14:40
 **/
public class ReflectTest {

    /**
     *反射获取List中的数据
     * @param args
     */

    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        List<Student> list = new ArrayList<Student>();
        list.add(new Student("张三", "男"));
        list.add(new Student("李四", "女"));
        list.add(new Student("王五", "女"));
        teacher.setList(list);
        getList(teacher,Student.class);
    }


    /**
     * 反射获取对象中的list数据
     * @param object
     * @param dateClass
     * @param <T>
     */
    public static<T> void getList(Object object,T dateClass){
        List<T> resultList = new ArrayList<>();
        if(object != null){

            Field[] fields = getAllFields(object.getClass());
            Field[] filterList= filterField(fields);
            Arrays.stream(filterList).forEach(var -> {
                //List集合
                if(List.class.isAssignableFrom(var.getType())){
                    Type type = var.getGenericType();
                    if(type instanceof ParameterizedType){
                        if(!var.isAccessible()){
                            var.setAccessible(true);
                        }
                        //获取到属性值的字节码
                        try {
                            Class<?> clzz = var.get(object).getClass();
                            //反射调用获取到list的size方法来获取到集合的大小
                            Method sizeMethod = clzz.getDeclaredMethod("size");
                            if(!sizeMethod.isAccessible()){
                                sizeMethod.setAccessible(true);
                            }
                            //集合长度
                            int size = (int) sizeMethod.invoke(var.get(object));
                            //循环遍历获取到数据
                            for (int i = 0; i < size; i++) {
                                //反射获取到list的get方法
                                Method getMethod = clzz.getDeclaredMethod("get", int.class);
                                //调用get方法获取数据
                                if(!getMethod.isAccessible()){
                                    getMethod.setAccessible(true);
                                }
                                T var1 = (T) getMethod.invoke(var.get(object), i);
                                resultList.add(var1);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            resultList.stream().forEach(var -> {
                System.out.println("反射获取到的数据是什么:" + var);
            });
        }
    }


    /**
     * 反射获取所有的字段
     * @param c
     * @return
     */
    public static Field[] getAllFields(Class c){
        List<Field> fieldList = new ArrayList<>();
        while (c!= null){
            fieldList.addAll(new ArrayList<>(Arrays.asList(c.getDeclaredFields())));
            c= c.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }





    /**
     * 过滤字段
     * @param
     * @return
     */
    public static Field[] filterField(Field[] fields){
        List<Field> tempList = Arrays.stream(fields).filter(field -> null != field
                && !Modifier.isFinal(field.getModifiers())
                && !Modifier.isStatic(field.getModifiers())
                && !Modifier.isAbstract(field.getModifiers())).collect(Collectors.toList());


        int arrLength = tempList != null ? 1:tempList.size();

        Field[] resultArr = new Field[arrLength];
        if(tempList != null){
            tempList.toArray(resultArr);
        }
        return resultArr;
    }

}

class Student {

    public Student(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    private String name;

    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}

class Teacher {

    List<Student> list;


    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }
}