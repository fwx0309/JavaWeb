package org.fwx.d08_java8.d01_lambda;

import org.fwx.d08_java8.d01_lambda.bean.Employee;
import org.fwx.d08_java8.d01_lambda.bean.Employee.Status;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @ClassName D01Lambda
 * @Description
 *   一、Lambda 表达式的基础语法：Java8中引入了一个新的操作符 "->" 该操作符称为箭头操作符或 Lambda 操作符
 *   						    箭头操作符将 Lambda 表达式拆分成两部分：
 *
 *   左侧：Lambda 表达式的参数列表
 *   右侧：Lambda 表达式中所需执行的功能， 即 Lambda 体
 *
 *   语法格式一：无参数，无返回值
 *   		() -> System.out.println("Hello Lambda!");
 *
 *   语法格式二：有一个参数，并且无返回值
 *   		(x) -> System.out.println(x)
 *
 *   语法格式三：若只有一个参数，小括号可以省略不写
 *   		x -> System.out.println(x)
 *
 *   语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
 *  		Comparator<Integer> com = (x, y) -> {
 *  			System.out.println("函数式接口");
 *  			return Integer.compare(x, y);
 *  		};
 *
 *   语法格式五：若 Lambda 体中只有一条语句， return 和 大括号都可以省略不写
 *   		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
 *
 *   语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
 *   		(Integer x, Integer y) -> Integer.compare(x, y);
 *
 *   上联：左右遇一括号省
 *   下联：左侧推断类型省
 *   横批：能省则省
 *
 *   二、Lambda 表达式需要“函数式接口”的支持
 *   函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。 可以使用注解 @FunctionalInterface 修饰
 *   			 可以检查是否是函数式接口
 * @Author Fwx
 * @Date 2024/4/3 15:49
 * @Version 1.0
 */
public class D01Lambda {
    /**
     * 语法格式一：无参数，无返回值
     * () -> System.out.println("Hello Lambda!");
     */
    @Test
    public void lambdaTest1(){
        // 方式一
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable ...");
            }
        };
        runnable.run();

        System.out.println("------");
        // 方式二
        Runnable runnable1 = () -> System.out.println("lambda ...");
        runnable1.run();
    }

    /**
     * 语法格式二：有一个参数，并且无返回值
     * (x) -> System.out.println(x)
     */
    @Test
    public void lambdaTest2(){
        // 方式一
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("s = " + s);
            }
        };
        consumer.accept("old ...");

        System.out.println("------");
        // 方式二
        Consumer<String> consumer1 = (s) -> System.out.println("s = " + s);
        consumer1.accept("new ...");

    }

    /**
     * 语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
     */
    @Test
    public void lamdbaTest4(){
        // 方式一
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println("o1 = " + o1);
                System.out.println("o2 = " + o2);
                return o1.compareTo(o2);
            }
        };
        System.out.println("comparator.compare(1,2) = " + comparator.compare(1, 2));

        System.out.println("------");
        // 方式二
        Comparator<Integer> comparator1 = (o1, o2) -> {
            System.out.println("o1 = " + o1);
            System.out.println("o2 = " + o2);
            return o1.compareTo(o2);
        };
        System.out.println("comparator1.compare(2,1) = " + comparator1.compare(2, 1));
    }

    /**
     * 自定义函数接口测试
     */
    @Test
    public void functionInterfaceTest(){
        MyFunctionInterface mf = i -> i + 1;
        System.out.println("mf.operation(0) = " + mf.operation(0));
    }

    /**
     * 员工数据排序：先按年龄比，年龄相同按姓名比
     */
    @Test
    public void empSort(){
        List<Employee> emps = Arrays.asList(
                new Employee(102, "李四", 59, 6666.66, Status.BUSY),
                new Employee(101, "张三", 18, 9999.99, Status.FREE),
                new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
                new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
                new Employee(104, "赵六", 8, 7777.77, Status.FREE),
                new Employee(104, "赵六", 8, 7777.77, Status.FREE),
                new Employee(105, "田七", 38, 5555.55, Status.BUSY)
        );

        Collections.sort(emps,(o1, o2) -> {
            if (o1.getAge() == o2.getAge()){
                return o1.getName().compareTo(o2.getName());
            } else {
                return Integer.compare(o1.getAge(), o2.getAge());
            }
        });

        emps.stream().forEach(System.out::println);
    }
}
