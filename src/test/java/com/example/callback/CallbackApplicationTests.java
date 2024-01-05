package com.example.callback;

import com.alibaba.excel.converters.Converter;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.callback.demos.web.AESU.AesUtils;
import com.example.callback.demos.web.Type;
import com.example.callback.demos.web.User;
import com.example.callback.demos.web.util.ExcelUtil;
import jakarta.annotation.Resource;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class CallbackApplicationTests {



    @Test
    public void testSort() {
        List<Long> list = List.of(2L, 9L, 3L, 7L, 5L, 1L, 6L, 4L, 8L);
        list.stream().sorted(Comparator.comparingLong(Long::longValue)).forEach(System.out::println);

    }
    @Test
    public void testMd5(){
        String md5 = cn.hutool.crypto.digest.MD5.create().digestHex("123456");
        System.out.println(md5);

    }

    @Test
    public  void tesTime(){
        // 2023-11-01 00:00:00  获取这个时间 类型为LocalDateTiem
        LocalDateTime parse = LocalDateTime.parse("2024-07-24 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


        long until = LocalDate.now().until(parse, ChronoUnit.DAYS);
        long until2=parse.toLocalDate().until(LocalDate.now(), ChronoUnit.DAYS);
        // pares 是失效日期
        if(until>3){
            System.out.println("未过期");
        }else {
            System.out.println("已过期");
        }
        System.out.println(until);
        System.out.println(until2);
    }

    @Test
    public  void printUser1() {
        User user = new User();
        user.setAge(12);
        user.setName("张三");
        user.setSex(1);
        User user1 = new User();
        user1.setAge(13);
        user1.setName("李四");
        user1.setSex(0);
        User user2 = new User();
        user2.setAge(14);
        user2.setName("王五");
        user2.setSex(1);
        List<User> users = List.of(user, user1, user2);

        ExcelUtil.writeExcel(users, "D://wsfile/", User.class);
    }

    @Test
    public  void printUser(){
        User user = new User();
        user.setAge(12);
        user.setName("张三");
        user.setSex(1);
        List<User> users = List.of(user);
        String realPath = "D://wsfile/";
        File folder = new File(realPath);
        if (!folder.isDirectory()){
            folder.mkdirs();
        }
        // 判断字段是否有ExcelProperty注解 并且获取到注解的值
        Class<? extends User> aClass = user.getClass();
        // 获取到所有的字段
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            // 判断字段是否有ExcelProperty注解
            if (field.isAnnotationPresent(com.alibaba.excel.annotation.ExcelProperty.class)) {
                // 获取到注解的值
                com.alibaba.excel.annotation.ExcelProperty annotation = field.getAnnotation(com.alibaba.excel.annotation.ExcelProperty.class);
                Class<? extends Converter<?>> converter = annotation.converter();
                String value = annotation.value()[0];
                System.out.println(annotation);
                System.out.println(annotation.converter());
//              如果是 SexConverter.class 就将sex值转化为男女
                if (converter.equals(com.example.callback.demos.web.util.SexConverter.class)) {

                }
                System.out.println(value);


            }
        }




    }

    @Test
    void contextLoads() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidParameterSpecException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//       Ktfii5ei43lBwPkE4nSkuQ==         将密匙转化为byte数组
        String key="rhXWNmR76s8yYNbQvAJO736AeC6PezyWy5ZBVjUXO+o=";
        byte[] keyBytes = Base64.decodeBase64(key);
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
// 加密
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(256, secretKey);
        String plainText = "Hello World";
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        System.out.println(Base64.encodeBase64String(encrypted));

    }

    @Test
    void test2(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[32]; // 128位密钥
        secureRandom.nextBytes(key);

        String base64Key = Base64.encodeBase64String(key);
        System.out.println(base64Key);
    }

    @Test
    void test3() throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
// 解密  fc5x0U9fsbVZLy4bKPgm6w==
        String key="rhXWNmR76s8yYNbQvAJO736AeC6PezyWy5ZBVjUXO+o=";
        byte[] keyBytes = Base64.decodeBase64(key);
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES");

        cipher.init(256, secretKey);
        String encryptedText = "agFy0N47DHO53E+MvktGTg==";
        byte[] bytes1 = Base64.decodeBase64(encryptedText);
        byte[] bytes = cipher.doFinal(bytes1);
        System.out.println(Base64.encodeBase64String(bytes));

    }


    // ---------------------------------------------------------------------------

    private static final String ALGO = "AES/ECB/PKCS5Padding";

    public static byte[] encrypt(String data, Key key) {
        try {
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(data.getBytes());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }


    private static byte[] decrypt(byte[] result, Key key) {
        try {
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(result);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    private static Key createKey() {
        try {
            // 生成key
            KeyGenerator keyGenerator;
            // 构造密钥生成器，指定为AES算法,不区分大小写
            keyGenerator = KeyGenerator.getInstance("AES");
            // 生成一个128位的随机源,根据传入的字节数组
            keyGenerator.init(256);  //init最大支持128,192,256位
            // 产生原始对称密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 获得原始对称密钥的字节数组
            byte[] keyBytes = secretKey.getEncoded();
            String s = Base64.encodeBase64String(keyBytes);
            System.out.println(s);
            String s1="VHBukUI5zjrBV9LPwUwrfN8E+13bQQb+lb+4n5AwrO8=";
            byte[] keyBytes1 = Base64.decodeBase64(s1);
            // key转换,根据字节数组生成AES密钥
            return new SecretKeySpec(keyBytes1, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Test
    public void test4(){
        String data = "hello Test symmetric encry";
        Key key = createKey();
        byte[] encryData = encrypt(data, key);
        System.out.println("encryData = " + new String(encryData));
        byte[] decryData = decrypt(encryData, key);
        System.out.println("decryData = " + new String(decryData));
    }

    @Resource
    RestTemplate restTemplate;

    @Test
    public void test5(){
        String data = "hello Test symmetric encry";
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200) ;
        map.put("msg", "success") ;
        map.put("user","13995770499");
        map.put("password","123456");
        String jsonString = JSON.toJSONString(map);
        String s="VHBukUI5zjrBV9LPwUwrfN8E+13bQQb+lb+4n5AwrO8=";
        byte[] keyBytes = Base64.decodeBase64(s);
        Key key = new SecretKeySpec(keyBytes, "AES");
        byte[] encryData = encrypt(jsonString, key);
        restTemplate.postForObject("http://608f90c9.r5.cpolar.top/decrypt",Base64.encodeBase64String(encryData),String.class);

    }

    @Test
    public void sendUserInfo(){
        String s="VHBukUI5zjrBV9LPwUwrfN8E+13bQQb+lb+4n5AwrO8=";
        byte[] keyBytes = Base64.decodeBase64(s);
        Key key = new SecretKeySpec(keyBytes, "AES");
        Map<String, Object> map = new HashMap<>();
        map.put("phone", "18977665544");
        String hostAddress = null;
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        map.put("ip", hostAddress);
        map.put("createTime", System.currentTimeMillis());
        System.out.println(JSON.toJSONString(map));
        byte[] encryData = encrypt(JSON.toJSONString(map), key);



        System.out.println(Base64.encodeBase64String(encryData));
        JSONObject jsonObject = restTemplate.postForObject("Http://localhost:8660/sys/user/system/examine", Base64.encodeBase64String(encryData), JSONObject.class);
        if (jsonObject != null && jsonObject.getString("code").equals("000")) {
            JSONObject data = jsonObject.getJSONObject("data");
            System.out.println(data);
            String body = data.getString("body");
            byte[] bytes = Base64.decodeBase64(body);
            byte[] decrypt = decrypt(bytes, key);
            System.out.println(new String(decrypt));
            System.out.println(data.getString("phone"));
        }else {
            System.out.println(jsonObject.toJSONString());
        }


    }

    @Test
    public void test65(){
        String wmsHostAddress = AesUtils.GET_WMS_HOST_ADDRESS("13083015567");
        System.out.println(wmsHostAddress);
    }
    @Test
    public void test34(){
        if (Type.LCCK.contains("4D-Cxx-08")) {
            System.out.println("true");
        }
    }

    @Test
    public void test821001(){
        String s = formatterPatternSwitch(1);
        System.out.println(s);

    }
    static String formatterPatternSwitch(Object o) {
        return switch (o) {
            case Integer i -> String.format("int %d", i);
            case Long l    -> String.format("long %d", l);
            case Double d  -> String.format("double %f", d);
            case String s  -> String.format("String %s", s);
            default        -> o.toString();
        };
    }






}
