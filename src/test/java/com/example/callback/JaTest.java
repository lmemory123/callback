package com.example.callback;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.example.callback.demos.web.User;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: TokyoMomao
 * DateTime: 2023-08-23 12:17
 */


@SpringBootTest
public class JaTest {



    @Test
    public void test3() {
        String warrantyUnit = "2";
        String warranty = "100";
        String dproducedate = "2023-07-04 22:13:58";
        LocalDateTime localDateTime = LocalDateTimeUtil.parse(dproducedate, "yyyy-MM-dd HH:mm:ss");
        System.out.println(localDateTime.toLocalDate());
        DateTime date = new DateTime(dproducedate);
        LocalDate localDate = LocalDate.now().minusDays(1);




        String dvalidate ;
        switch (warrantyUnit) {
            case "0":
                dvalidate = localDate.plusYears(Long.parseLong(warranty)).toString();
                break;
            case "1":
                dvalidate = localDate.plusMonths(Long.parseLong(warranty)).toString();
                break;
            case "2":
                dvalidate = localDate.plusDays(Long.parseLong(warranty)).toString();
                break;
            default:
                dvalidate = localDate.plusDays(Long.parseLong(warranty)).toString();
                break;

        }
        System.out.println(dvalidate);
        String backCode = "clck" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + String.format("%04d", 1);
        System.out.println(backCode);
    }





    @Test
    public void test1(){
        List<User> users = List.of(new User("TokyoMomao", 18), new User("TokyoMomao", 18),new User("TokyoXiaomao", 18));
        Map<String, Integer> collect = users.stream().collect(Collectors.toMap(User::getName, User::getAge, Integer::sum));
        System.out.println(collect);


    }
    @Test
    public void test2(){
        String format = LocalDateTime.now().minusDays(1).withHour(0).withMinute(0).withSecond(0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endTime=LocalDateTime.now().minusDays(1).withHour(23).withMinute(59).withSecond(59).toLocalDate().atStartOfDay();
        System.out.println(format);
        System.out.println(endTime);

    }

    private Map<String, String> translationMap;


    @Test
    public void test4(){
//        try {
//            // 创建一个随机异常
//            throw new Exception("随机异常");
//
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            System.out.println(e.fillInStackTrace());
//
//        }
        TranslationPair translationPair = new TranslationPair("你好", "Xin chào");
        TranslationPair translationPair1 = new TranslationPair("你好", null);
        translationPair.setVietnamese(translationPair1.getVietnamese());
        System.out.println(translationPair.getChinese());
        System.out.println(translationPair.getVietnamese());




    }

// 创建一个内部类，用于保存翻译映射
    @Getter
    @Setter
    private static class TranslationPair {
        private String chinese;
        private Object vietnamese;

        public TranslationPair(String chinese, String vietnamese) {
            this.chinese = chinese;
            this.vietnamese = vietnamese;
        }

}

    @Test
    public void test(){
        String s = translateChineseToVietnamese("你好，世界！");
        System.out.println(s);
    }

    public void ChineseToVietnameseService() {
        // 创建一个包含中文到越南语翻译的映射表
        translationMap = new HashMap<>();
        translationMap.put("你好", "Xin chào");
        translationMap.put("世界", "thế giới");
        // 添加更多的翻译映射
    }

    public String translateChineseToVietnamese(String input) {
        translationMap = new HashMap<>();
        translationMap.put("你好", "Xin chào");
        translationMap.put("世界", "thế giới");
        // 构建正则表达式模式，匹配所有中文文本
        String regex = "(" + String.join("|", translationMap.keySet()) + ")";
        Pattern pattern = Pattern.compile(regex);

        // 使用正则表达式进行替换
        Matcher matcher = pattern.matcher(input);
        StringBuilder output = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(output, translationMap.get(matcher.group()));
        }
        matcher.appendTail(output);

        return output.toString();
    }
}






