package org.fwx.d08_java8.d02_stream;

/**
 * @ClassName D05DefaultAndStaticFun
 * @Description TODO
 * @Author Fwx
 * @Date 2024/4/4 23:12
 * @Version 1.0
 */
public interface D05DefaultAndStaticFun {

    default void defaultFun(){
        System.out.println("defaultFun");
    }

    public static void staticFun(){
        System.out.println("staticFun");
    }
}
