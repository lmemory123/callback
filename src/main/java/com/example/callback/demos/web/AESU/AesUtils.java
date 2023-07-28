package com.example.callback.demos.web.AESU;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Cipher;
import java.security.Key;

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
public class AesUtils {


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
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
}
