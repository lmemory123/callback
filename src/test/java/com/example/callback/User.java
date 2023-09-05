package com.example.callback;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @Author: TokyoMomao
 * DateTime: 2023-09-04 11:47
 */
@Data
@AllArgsConstructor
public class User {
    private String name;

    private Integer age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
