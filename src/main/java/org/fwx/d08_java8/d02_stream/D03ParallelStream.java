package org.fwx.d08_java8.d02_stream;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * @ClassName D03ParallelStream
 * @Description java8 并行流
 * @Author Fwx
 * @Date 2024/4/4 20:39
 * @Version 1.0
 */
public class D03ParallelStream {
    @Test
    public void parallelStream(){
        Instant start = Instant.now();
        long reduce = LongStream.rangeClosed(0, 100000000000L)
                .parallel()
                .reduce(0, Long::sum);
        Instant end = Instant.now();

        System.out.println("reduce = " + reduce);
        Duration duration = Duration.between(start, end);
        System.out.println("duration = " + duration.toMillis());
    }
}
