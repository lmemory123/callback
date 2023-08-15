/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.callback.demos.web;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.example.callback.demos.web.AESU.AesUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.SecretKeySpec;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import static com.example.callback.demos.web.AESU.AesUtils.encrypt;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Controller
public class BasicController {

    // http://127.0.0.1:8080/hello?name=lisi
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Hello " + name;
    }
    @RequestMapping(value = "callback",method = RequestMethod.POST)
    @ResponseBody
    public Object callbakc(@RequestBody JSONArray jsonArray){
//        System.out.println(JSON.toJSONString(map));
        System.out.println(jsonArray.toJSONString());
        Map<String, Object> map = new HashMap<String, Object>() ;
        map.put("result", 200) ;
        map.put("msg", "success") ;
        Object[] objects=new Object[1];
        objects[0]=map;
        return objects ;

    }
//    @RequestMapping(value = "/encrypt",method = RequestMethod.POST)
    @GetMapping(value = "/encrypt/{str}")
    @ResponseBody
    public  String getDecrypt(@PathVariable String str){
        String s="VHBukUI5zjrBV9LPwUwrfN8E+13bQQb+lb+4n5AwrO8=";
        byte[] bytes = Base64.decodeBase64(s);
        Key key = new SecretKeySpec(bytes, "AES");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("phone", str);
//        获取本机ip
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
        return Base64.encodeBase64String(encryData);
    }


    @RequestMapping(value = "/decrypt",method = RequestMethod.POST)
    @ResponseBody
    public void test(@RequestBody String str){
        String s="VHBukUI5zjrBV9LPwUwrfN8E+13bQQb+lb+4n5AwrO8=";
        byte[] bytes = Base64.decodeBase64(s);
        Key key = new SecretKeySpec(bytes, "AES");
        byte[] decrypt = AesUtils.decrypt(Base64.decodeBase64(str), key);
        if (decrypt != null) {
            System.out.println(new String(decrypt));
        }
    }
//获取签名
    @RequestMapping(value = "/getSign",method = RequestMethod.POST)
    @ResponseBody
    public String getSign(@RequestBody String str){
        if(str=="qbs666"){
            return "VHBukUI5zjrBV9LPwUwrfN8E+13bQQb+lb+4n5AwrO8=";
        }
        return "未授权";
    }



    // http://127.0.0.1:8080/user
    @RequestMapping("/user")
    @ResponseBody
    public User user() {
        User user = new User();
        user.setName("theonefx");
        user.setAge(666);
        return user;
    }

    // http://127.0.0.1:8080/save_user?name=newName&age=11
    @RequestMapping("/save_user")
    @ResponseBody
    public String saveUser(User u) {
        return "user will save: name=" + u.getName() + ", age=" + u.getAge();
    }

    // http://127.0.0.1:8080/html
    @RequestMapping("/html")
    public String html() {
        return "index.html";
    }

    @ModelAttribute
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
            , @RequestParam(name = "age", defaultValue = "12") Integer age, User user) {
        user.setName("zhangsan");
        user.setAge(18);
    }
}
