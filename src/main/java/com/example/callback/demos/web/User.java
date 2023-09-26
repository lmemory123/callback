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

import com.alibaba.excel.annotation.ExcelProperty;
import com.example.callback.demos.web.util.MyExcelProperty;
import com.example.callback.demos.web.util.SexConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {


    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("年龄")
    private Integer age;
    @ExcelProperty(value = "性别",converter = SexConverter.class)
    @MyExcelProperty(value = "性别", converter = SexConverter.class, converterMap = "sexMap")
    private Integer sex;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
