package com.example.callback;

import cn.hutool.core.io.FileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: TokyoMomao
 * DateTime: 2023-11-20 11:32
 */
@SpringBootTest
public class mainTest {

    @Test
    public void test(){
        //  获取当前项目路径
        // 遍历当前文件夹下面的所有文件夹
        // System.out.println(FileUtil.size(new File("C:\\Users\\ranqiang\\AppData\\Local")));
        // 获取c盘根目录
        File file = new File("C:\\Users\\ranqiang");

        File[] files = file.listFiles();
        Map<Long,String> map=new TreeMap<>();
        if (files != null) {
            for (File file1 : files) {
                // 获取当前文件名称
                String name = file1.getName();
                // 获取当前文件大小
                long length = FileUtil.size(file1);
                map.put(length,name);
                // System.out.println(name+"------>" +length);
            }
        }
        System.out.println("-----------------");
        Optional<Map.Entry<Long, String>> first = map.entrySet().stream().findFirst();
        System.out.println(first.isPresent());
        // 将map 倒序输出
        map.entrySet().stream().sorted(Map.Entry.<Long, String>comparingByKey().reversed()).forEachOrdered((e) -> System.out.println(String.format("%-50s", e.getValue()) + new BigDecimal(e.getKey()).divide(new BigDecimal(1024 * 1024), 2, RoundingMode.HALF_UP) + "M"));

    }

    @Test
    public void testCore(){
//   * ThreadPoolExecutor 原生线程池 推荐
//          * 七大参数：
//          * 1.核心线程数 一般都是服务器CPU的核数
//          * 2.最大线程数 一般不超过服务器CPU创建线程的上限
//          * 3.线程的最大空闲时间 一般都是3秒以内
//          * 4.设置时间单位 秒或毫秒
//          * 5.阻塞队列 如果任务超过核心线程，那么就会存储在阻塞队列中 一般不超过最大线程数
//          * 6.线程工厂，创建线程对象 -固定
//          * 7.拒绝策略 线程池一达到最大上限，新的任务就会拒绝
//          * 工作过程：核心线程数-->阻塞队列-->最大线程数-->拒绝策略*/
        // 获取当前服务器CPU核数
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        // 获取当前服务器CPU创建线程的上限
        int maximumPoolSize = corePoolSize * 2;
        // 创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(maximumPoolSize), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }
}
