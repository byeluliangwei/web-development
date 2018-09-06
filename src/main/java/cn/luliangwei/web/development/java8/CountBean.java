package cn.luliangwei.web.development.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
/**
 * 测试JAVA 8中Collectors.toMap()的用法
 */
public class CountBean {

    private String status;
    private int count;
    
    public CountBean(String status, int count) {
        super();
        this.status = status;
        this.count = count;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    
    public static class TestToMap{
        
        private List<CountBean> countList = new ArrayList<>();
        @Before
        public void before() {
            CountBean c1 = new CountBean("test1",1);
            CountBean c2 = new CountBean("test2",2);
            CountBean c3 = new CountBean("test3",3);
            CountBean c4 = new CountBean("test4",4);
            CountBean c5 = new CountBean("test5",5);
            CountBean c6 = new CountBean("test6",6);
            countList.add(c1);
            countList.add(c2);
            countList.add(c3);
            countList.add(c4);
            countList.add(c5);
            countList.add(c6);
        }
        @Test
        public void test() {
            Map<String, Integer> map = new HashMap<>();
            map = countList.stream()
                    .parallel()
                    .collect(Collectors.toMap(CountBean::getStatus, CountBean::getCount));
            System.out.println(map);
        }
        
    }
    
}
