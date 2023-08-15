package com.example.callback;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.callback.demos.web.AESU.AesUtils;
import jakarta.annotation.Resource;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class CallbackApplicationTests {

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
            cipher.init(cipher.ENCRYPT_MODE, key);
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
        String wmsHostAddress = AesUtils.getWmsHostAddress("13083015567");
        System.out.println(wmsHostAddress);
    }







}
