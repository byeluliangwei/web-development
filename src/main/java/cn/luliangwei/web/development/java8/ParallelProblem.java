package cn.luliangwei.web.development.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 本类用于测试JAVA8中新特性Stream的Parallel中遇到的问题
 * </p>
 * 1、Java并行流处理中采用了fork/join框架，fork/join的思想是分治法，将一个大任务划分成若干个小任务，分别执行，最后将</br>
 *   小任务自行的结果合并起来，就是大任务执行的结果。</br>
 * 2、Parallel并行处理的时候对变量的修改是线程不安全的
 * 
 */
public class ParallelProblem {

    public static void main(String[] args) {
        
        Stream<String> words = Stream.of("a", "b", "a", "c");
        Map<String, Integer> wordsCount = words.collect(Collectors.toMap(s->s, s->1,
                                                              (i, j) -> i + j));
        System.out.println(wordsCount);
        
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        
        IntStream.range(0, 1000).forEach(e -> list1.add(e));
        
        IntStream.range(0, 1000).parallel().forEach(Collections.synchronizedList(list2)::add);
        
        //ArrayIndexOutOfBoundsException
        IntStream.range(0, 1000).parallel().forEach(list3::add);
        
        System.out.println(list1.size());
        System.out.println(list2.size());
        System.out.println(list3.size());
    }
}
