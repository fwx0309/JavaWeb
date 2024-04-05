package org.fwx.d08_java8.d02_stream;

import org.fwx.d08_java8.d01_lambda.bean.Employee;
import org.fwx.d08_java8.d01_lambda.bean.Employee.Status;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * @ClassName D01Stream
 * @Description
 *  一、 Stream 的操作步骤
 *
 *  1. 创建 Stream
 *
 *  2. 中间操作
 *
 *  3. 终止操作
 *
 * @Author Fwx
 * @Date 2024/4/3 22:53
 * @Version 1.0
 */
public class D01Stream {
    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Status.BUSY)
    );
    /**
     * 1. 创建 Stream
     */
    @Test
    public void createStreamTest(){
        // 1、可以通 Collection 系列集合提供的 stream 或 paralleStream
        ArrayList<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        // 2、通过 Arrays 中的静态放过 stream() 获取数组流
        Integer[] integers = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(integers);

        // 3、通过 Stream 中的静态方法 of()
        Stream<String> stream2 = Stream.of("aa", "bb", "cc");

        // 4、创建无限流
        Stream<Integer> stream3 = Stream.iterate(0, x -> x + 2);
        stream3.limit(3).forEach(System.out::println);

        System.out.println("-----------");
        Stream.generate(()->new Random().nextDouble()).limit(3).forEach(System.out::println);
    }

    /**
     * 2、中间操作
     * 筛选与切片
     * 	filter——接收 Lambda ， 从流中排除某些元素。
     * 	limit——截断流，使其元素不超过给定数量。
     * 	skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
     * 	distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     */
    @Test
    public void operationTest(){
        emps.stream()
                .filter(o->o.getSalary()>5000)
                .skip(2)
                .limit(2)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 2、中间操作
     * 映射
     * 	map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * 	flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void operationTest1(){
        List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        // map
        strList.stream().map(o -> o.toUpperCase()).forEach(System.out::println);
        System.out.println("----------");
        // flatmap
        strList.stream()
                .map(o -> o.split(""))
                .flatMap(o -> Arrays.stream(o))
                .forEach(System.out::println);
    }

    /**
     * 2、中间操作
     *  sorted()——自然排序
     *  sorted(Comparator com)——定制排序
	 */
    @Test
    public void operationTest2(){
        emps.stream()
                .map(Employee::getName)
                .sorted()
                .forEach(System.out::println);

        System.out.println("------------------------------------");

        emps.stream()
                .sorted((x, y) -> {
                    if(x.getAge() == y.getAge()){
                        return x.getName().compareTo(y.getName());
                    }else{
                        return Integer.compare(x.getAge(), y.getAge());
                    }
                }).forEach(System.out::println);
    }

    /**
     * 3. 终止操作
     * allMatch——检查是否匹配所有元素
     * anyMatch——检查是否至少匹配一个元素
     * noneMatch——检查是否没有匹配的元素
     * findFirst——返回第一个元素
     * findAny——返回当前流中的任意元素
     * count——返回流中元素的总个数
     * max——返回流中最大值
     * min——返回流中最小值
     */
    @Test
    public void terminaOperation(){
        // allMatch——检查是否匹配所有元素
        boolean allMatch = emps.stream()
                .allMatch(x -> x.getStatus().equals(Status.FREE));
        System.out.println("allMatch = " + allMatch);

        // anyMatch——检查是否至少匹配一个元素
        boolean anyMatch = emps.stream()
                .anyMatch(x -> x.getStatus().equals(Status.FREE));
        System.out.println("anyMatch = " + anyMatch);

        // noneMatch——检查是否没有匹配的元素
        boolean noneMatch = emps.stream()
                .noneMatch(x -> x.getStatus().equals(Status.FREE));
        System.out.println("noneMatch = " + noneMatch);

        // findFirst——返回第一个元素
        Optional<Employee> first = emps.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .findFirst();
        System.out.println("first = " + first.get());

        // findAny——返回当前流中的任意元素
        Optional<Employee> any = emps.parallelStream()
        // Optional<Employee> any = emps.stream()
                .findAny();
        System.out.println("any = " + any.get());

        // count——返回流中元素的总个数
        long count = emps.stream().count();
        System.out.println("count = " + count);

        // max——返回流中最大值
        Optional<Double> max = emps.stream()
                .map(o -> o.getSalary())
                .max(Double::compare);
        System.out.println("max = " + max.get());

        // min——返回流中最小值
        Optional<Double> min = emps.stream()
                .map(o -> o.getSalary())
                .min(Double::compare);
        System.out.println("min = " + min.get());
    }
}
