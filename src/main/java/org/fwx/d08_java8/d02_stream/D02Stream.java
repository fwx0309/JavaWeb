package org.fwx.d08_java8.d02_stream;

import org.fwx.d08_java8.d01_lambda.bean.Employee;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/**
 * @ClassName D02Stream
 * @Description 3. 终止操作
 * @Author Fwx
 * @Date 2024/4/4 11:19
 * @Version 1.0
 */
public class D02Stream {
    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Employee.Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
    );
    /**
     * 归约
     * 	reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
     */
    @Test
    public void test(){
        Double reduce = emps.stream()
                .map(o -> o.getSalary())
                .reduce(0.0, (x, y) -> x + y);
        System.out.println("reduce = " + reduce);
    }

    /**
     * collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
     */
    @Test
    public void test1(){
        // *** list
        ArrayList<String> list = emps.stream()
                .map(e -> e.getName())
                .collect(Collectors.toCollection(() -> new ArrayList<String>()));
        System.out.println("list = " + list);

        List<String> list1 = emps.stream()
                .map(e -> e.getName())
                .collect(Collectors.toList());
        System.out.println("list1 = " + list1);
        System.out.println("-----------");

        // *** set
        Set<String> set = emps.stream()
                .map(e -> e.getName())
                .collect(Collectors.toSet());
        System.out.println("set = " + set);
    }

    /**
     * collect——maxBy、summingInt、averagingDouble、counting
     */
    @Test
    public void test2(){
        // *** maxBy
        Optional<Double> maxBy = emps.stream()
                .map(e -> e.getSalary())
                .collect(Collectors.maxBy(Double::compareTo));
        System.out.println("maxBy = " + maxBy.get());

        // *** summingInt
        Integer summingInt = emps.stream()
                // .collect(Collectors.summingInt(e -> e.getAge()));
                .collect(Collectors.summingInt(Employee::getAge));
        System.out.println("summingInt = " + summingInt);

        // *** averagingDouble
        Double averagingDouble = emps.stream()
                .collect(Collectors.averagingDouble(e -> e.getSalary()));
        System.out.println("averagingDouble = " + averagingDouble);

        // *** counting
        Long counting = emps.stream()
                .map(e -> e.getSalary())
                .collect(Collectors.counting());
        System.out.println("counting = " + counting);

        System.out.println("-------------------");
        // *** summarizingDouble
        DoubleSummaryStatistics summarizingDouble = emps.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("summarizingDouble = " + summarizingDouble);
        double max = summarizingDouble.getMax();
        double min = summarizingDouble.getMin();
        double average = summarizingDouble.getAverage();
        long count = summarizingDouble.getCount();
        double sum = summarizingDouble.getSum();
    }

    /**
     * 分组
     */
    @Test
    public void test3(){
        Map<Employee.Status, List<Employee>> group = emps.stream()
                .collect(Collectors.groupingBy(e -> e.getStatus()));
        System.out.println("group = " + group);

        System.out.println("--------");
        Map<Employee.Status, Map<String, List<Employee>>> groups = emps.stream()
                .collect(Collectors.groupingBy(e -> e.getStatus(), Collectors.groupingBy(e -> e.getName())));
        groups.entrySet().stream().forEach(System.out::println);
    }

    /**
     * 分区
     */
    @Test
    public void test4(){
        Map<Boolean, List<Employee>> collect = emps.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 7000));
        System.out.println("collect = " + collect);
    }

    /**
     * jion 拼接
     */
    @Test
    public void test5(){
        String names = emps.stream()
                .map(e -> e.getName())
                .collect(Collectors.joining(",","^","$"));
        System.out.println("names = " + names);
    }

    /**
     * collectors 中的 reducing
     */
    @Test
    public void test6(){
        Double collect = emps.stream()
                .map(e -> e.getSalary())
                .collect(Collectors.reducing(0.0, (x, y) -> x + y));
        System.out.println("collect = " + collect);
    }
}
