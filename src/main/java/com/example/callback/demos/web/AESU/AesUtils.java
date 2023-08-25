package com.example.callback.demos.web.AESU;

import com.alibaba.fastjson2.JSON;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * momao
 *
 * @ClassName: AesUtils
 * @Description:
 * @author: momao
 * @date: 2023/7/25 13:17
 * @Blog: 43.143.26.111:888
 */

@Component
public  class AesUtils {


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
    private static final String ALGO = "AES/ECB/PKCS5Padding";
    private static final String key="VHBukUI5zjrBV9LPwUwrfN8E+13bQQb+lb+4n5AwrO8=";

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

   public static byte[] decrypt(byte[] result, Key key) {
        try {
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(result);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;

    }

    public static String GET_WMS_HOST_ADDRESS(String phone ){
        byte[] keyBytes = Base64.decodeBase64(key);
        Key key = new SecretKeySpec(keyBytes, "AES");
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        String hostAddress = null;
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        map.put("ip", hostAddress);
        map.put("createTime", System.currentTimeMillis());
        byte[] encryData = encrypt(JSON.toJSONString(map), key);
        String s = Base64.encodeBase64URLSafeString(encryData);
        return "http://localhost:8666/login?encryptionCode="+s;

    }


}
