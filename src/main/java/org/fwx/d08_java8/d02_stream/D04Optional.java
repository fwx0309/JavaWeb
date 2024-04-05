package org.fwx.d08_java8.d02_stream;

import org.fwx.d08_java8.d01_lambda.bean.Employee;
import org.junit.Test;

import java.util.Optional;

/**
 * @ClassName D04Optional
 * @Description TODO
 * @Author Fwx
 * @Date 2024/4/4 22:39
 * @Version 1.0
 */
public class D04Optional {
    /*
     * 一、Optional 容器类：用于尽量避免空指针异常
     * 	Optional.of(T t) : 创建一个 Optional 实例
     * 	Optional.empty() : 创建一个空的 Optional 实例
     * 	Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
     * 	isPresent() : 判断是否包含值
     * 	orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
     * 	orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
     * 	map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
     * 	flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
     */
    @Test
    public void test(){
        // Optional.of(T t)
        Optional<String> test = Optional.of("test");
        System.out.println("test = " + test.get());

        // Optional.empty();
        Optional<Object> empty = Optional.empty();
//        System.out.println("empty.get() = " + empty.get());

        // Optional.ofNullable(T t)
        Optional<Employee> employee = Optional.ofNullable(new Employee());
        System.out.println("employee = " + employee.get());

        Optional<Object> ofNullable = Optional.ofNullable(null);
//        System.out.println("ofNullable = " + ofNullable.get());

    }
}
