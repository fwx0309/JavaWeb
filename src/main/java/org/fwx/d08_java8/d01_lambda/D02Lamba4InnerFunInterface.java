package org.fwx.d08_java8.d01_lambda;

import org.junit.Test;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @ClassName D02Lamba4InnerFunInterface
 * @Description 测试 java8 4大内置函数接口
 *   Consumer<T> : 消费型接口
 *   		void accept(T t);
 *
 *   Supplier<T> : 供给型接口
 *   		T get();
 *
 *   Function<T, R> : 函数型接口
 *   		R apply(T t);
 *
 *   Predicate<T> : 断言型接口
 *   		boolean test(T t);
 *
 * @Author Fwx
 * @Date 2024/4/3 18:04
 * @Version 1.0
 */
public class D02Lamba4InnerFunInterface {

    /**
     * Consumer<T> : 消费型接口
     *  void accept(T t);
     */
    @Test
    public void consumerFunTest(){
        Consumer<Integer> consumer = x -> System.out.println("consumer = " + x);
        consumer.accept(100);
    }

    /**
     * Supplier<T> : 供给型接口
     *  T get();
     */
    @Test
    public void supplierFunTest(){
        Supplier<Integer> supplier = () -> new Random().nextInt(10);
        System.out.println("supplier = " + supplier.get());
    }

    /**
     * Function<T, R> : 函数型接口
     *  R apply(T t);
     */
    @Test
    public void functionFunTest(){
        Function<Object,String> function = o -> o.toString();
        System.out.println("function.apply(123) = " + function.apply(123));
    }

    /**
     * Predicate<T> : 断言型接口
     *  boolean test(T t);
     */
    @Test
    public void predicateFunTest(){
        Predicate<String> predicate = o -> o.equals("a");
        System.out.println("predicate.test(\"a\") = " + predicate.test("a"));
        System.out.println("predicate.test(\"b\") = " + predicate.test("b"));
    }
}
